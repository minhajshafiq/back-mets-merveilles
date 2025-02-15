package cucumber.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.io.File;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DrinksStepdefs {
    @LocalServerPort
    private int port;

    private Response response;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
    }

    @When("I add a Drink with the following details")
    public void iAddADrinkWithTheFollowingDetails(DataTable dataTable) {
        Map<String, String> data = dataTable.transpose().asMap();
        String name = data.get("name");
        String description = data.get("description");
        String price = data.get("price");
        String id = data.get("id");
        String menuId = data.get("menuId");
        String imageUrl = data.get("imageUrl");

        JSONObject drinkDto = new JSONObject();
        drinkDto.put("id", id);
        drinkDto.put("name", name);
        drinkDto.put("description", description);
        drinkDto.put("price", price);
        drinkDto.put("menuId", menuId);

        // Load the image file (make sure the path is correct)
        File imageFile = new File("src/test/resources/test-image/" + imageUrl);
        assertThat(imageFile).exists();

        RequestSpecification request = RestAssured.given();
        response = request
                .header("Content-Type", "multipart/form-data")
                .multiPart("data", drinkDto.toJSONString(), "application/json")
                .multiPart("file", imageFile)
                .post("/drinks");

        assertThat(response.getStatusCode()).isEqualTo(201);
    }

    @Then("the Drink named {string} should be added")
    public void theDrinkShouldBeAdded(String expectedName) {
        RequestSpecification request = RestAssured.given();
        response = request
                .header("Content-Type", "application/json")
                .get("/drinks/1");

        assertThat(response.getStatusCode()).isEqualTo(200);

        JSONObject drink = response.getBody().as(JSONObject.class);

        assertThat(drink.getAsNumber("id")).isEqualTo(1);
        assertThat(drink.getAsString("name")).isEqualTo(expectedName);
        assertThat(drink.getAsString("description")).isEqualTo("Soft Drink");
        assertThat(Double.parseDouble(drink.get("price").toString())).isEqualTo(2.0);
        assertThat(drink.get("menuId")).isNull();
        assertThat(drink.get("imageUrl")).isNotNull();
    }
}

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

public class DessertsStepDefs {

    @LocalServerPort
    private int port;

    private Response response;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
    }

    @When("I add a Dessert with the following details")
    public void iAddADessertWithTheFollowingDetails(DataTable dataTable) {
        Map<String, String> data = dataTable.transpose().asMap();
        String name = data.get("name");
        String description = data.get("description");
        String price = data.get("price");
        String id = data.get("id");
        String menuId = data.get("menuId");
        String imageUrl = data.get("imageUrl");

        JSONObject dessertDto = new JSONObject();
        dessertDto.put("id", id);
        dessertDto.put("name", name);
        dessertDto.put("description", description);
        dessertDto.put("price", price);
        dessertDto.put("menuId", menuId);

        File imageFile = new File("src/test/resources/test-image/" + imageUrl);
        assertThat(imageFile).exists();

        RequestSpecification request = RestAssured.given();
        response = request
                .header("Content-Type", "multipart/form-data")
                .multiPart("data", dessertDto.toJSONString(), "application/json")
                .multiPart("file", imageFile)
                .post("/desserts");

        assertThat(response.getStatusCode()).isEqualTo(201);
    }

    @Then("the Dessert named {string} should be added")
    public void theDessertShouldBeAdded(String dessertName) {
        RequestSpecification request = RestAssured.given();
        response = request
                .header("Content-Type", "application/json")
                .get("/desserts/2");

        assertThat(response.getStatusCode()).isEqualTo(200);

        JSONObject dessert = response.getBody().as(JSONObject.class);

        assertThat(dessert.getAsNumber("id")).isEqualTo(2);
        assertThat(dessert.getAsString("name")).isEqualTo(dessertName);
        assertThat(dessert.getAsString("description")).isEqualTo("Italian");
        assertThat(Double.parseDouble(dessert.get("price").toString())).isEqualTo(7);
        assertThat(dessert.get("menuId")).isNull();
        assertThat(dessert.get("imageUrl")).isNotNull();
    }
}

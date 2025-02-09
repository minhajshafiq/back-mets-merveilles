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

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DessertsStepDefs {

    @LocalServerPort
    private int port;

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

        JSONObject dessertDto = new JSONObject();
        dessertDto.put("id", id);
        dessertDto.put("name", name);
        dessertDto.put("description", description);
        dessertDto.put("price", price);
        dessertDto.put("menuId", menuId);

        RequestSpecification request = RestAssured.given();
        Response response = request
                .header("Content-Type", "application/json")
                .body(dessertDto.toJSONString())
                .post("/desserts");

        assertThat(response.getStatusCode()).isEqualTo(200);
    }

    @Then("the Dessert named {string} should be added")
    public void theDessertShouldBeAdded(String dessertName) {
        int dessertId = getDessertIdFromName(dessertName);
        RequestSpecification request = RestAssured.given();
        Response response = request
                .header("Content-Type", "application/json")
                .get("/desserts/" + dessertId);

        assertThat(response.getStatusCode()).isEqualTo(200);

        JSONObject dessert = response.getBody().as(JSONObject.class);

        assertThat(dessert.getAsNumber("id")).isEqualTo(dessertId);
        assertThat(dessert.getAsString("name")).isEqualTo(dessertName);
        assertThat(dessert.getAsString("description")).isEqualTo("Sweet");
        assertThat(Double.parseDouble(dessert.get("price").toString())).isEqualTo(5.0);
        assertThat(dessert.get("menuId")).isNull();
    }

    private int getDessertIdFromName(String dessertName) {
        RequestSpecification request = RestAssured.given();
        Response response = request
                .header("Content-Type", "application/json")
                .get("/desserts?name=" + dessertName);

        assertThat(response.getStatusCode()).isEqualTo(200);

        JSONObject dessert = response.getBody().as(JSONObject.class);
        return dessert.getAsNumber("id").intValue();
    }
}

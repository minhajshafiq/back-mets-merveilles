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

public class StarterStepDefs {
    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
    }

    @When("I add a Starter with the following details")
    public void iAddAStarterWithTheFollowingDetails(DataTable dataTable) {
        Map<String, String> data = dataTable.transpose().asMap();
        String name = data.get("name");
        String description = data.get("description");
        String price = data.get("price");
        String id = data.get("id");
        String menuId = data.get("menuId");

        JSONObject starterDto = new JSONObject();
        starterDto.put("id", id);
        starterDto.put("name", name);
        starterDto.put("description", description);
        starterDto.put("price", price);
        starterDto.put("menuId", menuId);

        RequestSpecification request = RestAssured.given();
        Response response = request
                .header("Content-Type", "application/json")
                .body(starterDto.toJSONString())
                .post("/starter");

        assertThat(response.getStatusCode()).isEqualTo(200);
    }

    @Then("the Starter should be added")
    public void theStarterShouldBeAdded() {
        RequestSpecification request = RestAssured.given();
        Response response = request
                .header("Content-Type", "application/json")
                .get("/starter/1");

        assertThat(response.getStatusCode()).isEqualTo(200);

        JSONObject mainCourse = response.getBody()
                .as(JSONObject.class);

        assertThat(mainCourse.getAsNumber("id")).isEqualTo(1);
        assertThat(mainCourse.getAsString("name")).isEqualTo("name");
        assertThat(mainCourse.getAsString("description")).isEqualTo("description");
        assertThat(Double.parseDouble(mainCourse.get("price").toString())).isEqualTo(10.0);
        assertThat(mainCourse.get("menuId")).isNull();
    }
}

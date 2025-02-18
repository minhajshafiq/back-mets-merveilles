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

public class StarterStepDefs {
    @LocalServerPort
    private int port;

    private Response response;

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
        String imageUrl = data.get("imageUrl");

        JSONObject starterDto = new JSONObject();
        starterDto.put("id", id);
        starterDto.put("name", name);
        starterDto.put("description", description);
        starterDto.put("price", price);
        starterDto.put("menuId", menuId);

        // Load the image file (make sure the path is correct)
        File imageFile = new File("src/test/resources/test-image/" + imageUrl);
        assertThat(imageFile).exists();

        RequestSpecification request = RestAssured.given();
        response = request
                .header("Content-Type", "multipart/form-data")
                .multiPart("data", starterDto.toJSONString(), "application/json")
                .multiPart("file", imageFile)
                .post("/starter");

        assertThat(response.getStatusCode()).isEqualTo(201);
    }

    @Then("the Starter named {string} should be added")
    public void theStarterShouldBeAdded(String starterName) {
        RequestSpecification request = RestAssured.given();
        response = request
                .header("Content-Type", "application/json")
                .get("/starter/1");

        assertThat(response.getStatusCode()).isEqualTo(200);

        JSONObject starter = response.getBody().as(JSONObject.class);

        assertThat(starter.getAsNumber("id")).isEqualTo(1);
        assertThat(starter.getAsString("name")).isEqualTo(starterName);
        assertThat(starter.getAsString("description")).isEqualTo("Italian");
        assertThat(Double.parseDouble(starter.get("price").toString())).isEqualTo(5.0);
        assertThat(starter.get("menuId")).isNull();
        assertThat(starter.get("imageUrl")).isNotNull();
    }


}

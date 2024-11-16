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

public class MainCourseStepDefs {
    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
    }
    @When("I add a MainCourse with the following details")
    public void iAddAMainCourseWithTheFollowingDetails(DataTable dataTable) {
        Map<String, String> data = dataTable.transpose().asMap();
        String name = data.get("name");
        String description = data.get("description");
        String price = data.get("price");
        String id = data.get("id");
        String menuId = data.get("menuId");

        JSONObject mainCourseDto = new JSONObject();
        mainCourseDto.put("id", id);
        mainCourseDto.put("name", name);
        mainCourseDto.put("description", description);
        mainCourseDto.put("price", price);
        mainCourseDto.put("menuId", menuId);

        RequestSpecification request = RestAssured.given();
        Response response = request
                .header("Content-Type", "application/json")
                .body(mainCourseDto.toJSONString())
                .post("/main-course");

        assertThat(response.getStatusCode()).isEqualTo(200);

    }

    @Then("the MainCourse should be added")
    public void theMainCourseShouldBeAdded() {
        // faire le then avec get et vérifier que le main course a bien été ajouté
    }
}

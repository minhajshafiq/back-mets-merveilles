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

public class MainCourseStepDefs {
    @LocalServerPort
    private int port;

    private Response response;

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
        String imageUrl = data.get("imageUrl");

        JSONObject mainCourseDto = new JSONObject();
        mainCourseDto.put("id", id);
        mainCourseDto.put("name", name);
        mainCourseDto.put("description", description);
        mainCourseDto.put("price", price);
        mainCourseDto.put("menuId", menuId);

        File imageFile = new File("src/test/resources/test-image/" + imageUrl);
        assertThat(imageFile).exists();

        RequestSpecification request = RestAssured.given();
        response = request
                .header("Content-Type", "multipart/form-data")
                .multiPart("data", mainCourseDto.toJSONString(), "application/json")
                .multiPart("file", imageFile)
                .post("/main-course");

        assertThat(response.getStatusCode()).isEqualTo(201);
    }

    @Then("the MainCourse named {string} should be added")
    public void theMainCourseShouldBeAdded(String mainCourseName) {
        RequestSpecification request = RestAssured.given();
        response = request
                .header("Content-Type", "application/json")
                .get("/main-course/1");

        assertThat(response.getStatusCode()).isEqualTo(200);

        JSONObject mainCourse = response.getBody().as(JSONObject.class);

        assertThat(mainCourse.getAsNumber("id")).isEqualTo(1);
        assertThat(mainCourse.getAsString("name")).isEqualTo(mainCourseName);
        assertThat(mainCourse.getAsString("description")).isEqualTo("Italian");
        assertThat(Double.parseDouble(mainCourse.get("price").toString())).isEqualTo(10.0);
        assertThat(mainCourse.get("menuId")).isNull();
        assertThat(mainCourse.get("imageUrl")).isNotNull();
    }
}

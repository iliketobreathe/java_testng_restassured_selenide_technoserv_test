import api.users.UserAddTest;
import api.users.UserGetTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.LinkedHashMap;

public class ApiTests {

    @Test
    public void getUsersTest() {
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");

        Assert.assertEquals(response.getStatusCode(), 200);

        UserGetTest[] users = response.jsonPath().getObject("data", UserGetTest[].class);
        for (UserGetTest user : users) {
            Assert.assertNotNull(user.getFirst_name());
            Assert.assertNotNull(user.getLast_name());
            Assert.assertNotNull(user.getEmail());
            Assert.assertNotNull(user.getAvatar());
        }
    }

    @Test
    public void createUserTest() {
        LinkedHashMap<String, String> requestBody = new LinkedHashMap<>();
        requestBody.put("name", "morpheus");
        requestBody.put("job", "leader");

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(requestBody);

        Response response = request.post("https://reqres.in/api/users");

        Assert.assertEquals(response.getStatusCode(), 201);

        UserAddTest user = response.as(UserAddTest.class);

        Assert.assertEquals(user.getName(), "morpheus");
        Assert.assertEquals(user.getJob(), "leader");
    }
}

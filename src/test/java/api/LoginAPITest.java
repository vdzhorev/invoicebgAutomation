package api;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static utils.Config.*;

public class LoginAPITest {

    @Test
    @Tag("api")
    @DisplayName("Can login with valid credentials")
    public void canLoginWithValidCredentials() {
        LoginAPI loginAPI = new LoginAPI();
        Response response = loginAPI.obtainToken(EMAIL, PASSWORD, DOMAIN);
        Assertions.assertEquals(200, response.statusCode());
        String token = response.then().extract().jsonPath().get("token");
        Assertions.assertFalse(token.isBlank());
    }

    @Test
    @Tag("api")
    @DisplayName("Cant login with invalid password")
    public void cantLoginWithInvalidPassword() {
        LoginAPI loginAPI = new LoginAPI();
        Response response = loginAPI.obtainToken(EMAIL, "WrongPassword1!", DOMAIN);
        Assertions.assertEquals(401, response.statusCode());
        String errorMessage = response.jsonPath().getString("error");
        Assertions.assertEquals("Wrong username or password", errorMessage);
    }

    @Test
    @Tag("api")
    @DisplayName("Cant login with invalid domain")
    public void cantLoginWithInvalidDomain() {
        LoginAPI loginAPI = new LoginAPI();
        Response response = loginAPI.obtainToken(EMAIL, PASSWORD, "wrong-domain");
        Assertions.assertEquals(401, response.statusCode());
        String errorMessage = response.jsonPath().getString("error");
        Assertions.assertEquals("Firm not found", errorMessage);
    }

    @Test
    @Tag("api")
    @DisplayName("Cant login without domain")
    public void cantLoginWithoutDomain() {
        LoginAPI loginAPI = new LoginAPI();
        Response response = loginAPI.obtainToken(EMAIL, PASSWORD, null);
        Assertions.assertEquals(400, response.statusCode());
        String errorMessage = response.jsonPath().getString("error");
        Assertions.assertEquals("POST argument `domain` is missing", errorMessage);
    }


}

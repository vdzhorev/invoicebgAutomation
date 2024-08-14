package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static utils.Config.*;

public class LoginAPI {

    private static final String ENDPOINT = "/login/token";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public Response obtainToken(String email, String password, String domain) {
        Credential myCredentials = new Credential(email, password, domain);
        String requestBody = GSON.toJson(myCredentials);

        return RestAssured
                .given()
                .log()
                .all()
                .baseUri(BASE_URI)
                .basePath(BASE_PATH)
                .header("User-Agent", "Chrome")
                .header("Content-Type", "application/json")
                .accept(ContentType.JSON)
                .body(requestBody)
                .post(ENDPOINT).prettyPeek();
    }

    public static void main(String[] args) {
        LoginAPI loginAPI = new LoginAPI();
        Response response = loginAPI.obtainToken("velislavdzhorev@gmail.com", "Parola123!", "dzhorev-ltd");
        String token = response.jsonPath().getString("token");
        System.out.println(token);


    }
}

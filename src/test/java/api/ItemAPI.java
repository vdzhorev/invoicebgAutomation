package api;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static utils.Config.*;

public class ItemAPI {

    private String token;
    private static final String ENDPOINT = "/items";

    public ItemAPI(String token) {
        this.token = token;
    }

    public Response getAll() {
        return RestAssured
                .given()
                .log()
                .all()
                .auth()
                .oauth2(token)
                .baseUri(BASE_URI)
                .basePath(BASE_PATH)
                .header("Content-Type", "application-json")
                .header("User-Agent", "Chrome")
                .accept(ContentType.JSON)
                .get(ENDPOINT)
                .prettyPeek();
    }

    public Response get(int id) {
        return RestAssured
                .given()
                .log()
                .all()
                .auth()
                .oauth2(token)
                .baseUri(BASE_URI)
                .basePath(BASE_PATH)
                .header("Content-Type", "application-json")
                .header("User-Agent", "Chrome")
                .accept(ContentType.JSON)
                .get(ENDPOINT + "/" + id)
                .prettyPeek();
    }

    public Response delete(int id) {
        return RestAssured
                .given()
                .log()
                .all()
                .auth()
                .oauth2(token)
                .baseUri(BASE_URI)
                .basePath(BASE_PATH)
                .header("Content-Type", "application-json")
                .header("User-Agent", "Chrome")
                .accept(ContentType.JSON)
                .delete(ENDPOINT + "/" + id)
                .prettyPeek();
    }

    public Response create(Item item) {
        Gson gson = new Gson();
        String json = gson.toJson(item);

        return RestAssured
                .given()
                .log()
                .all()
                .auth()
                .oauth2(token)
                .baseUri(BASE_URI)
                .basePath(BASE_PATH)
                .header("User-Agent", "Chrome")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(json)
                .post(ENDPOINT);
    }

    public Response update(int id, Item item) {
        return RestAssured
                .given()
                .log()
                .all()
                .auth()
                .oauth2(token)
                .baseUri(BASE_URI)
                .basePath(BASE_PATH)
                .header("User-Agent", "Chrome")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(item)
                .patch(ENDPOINT + "/" + id);
    }

}



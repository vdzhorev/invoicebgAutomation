package api;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static utils.Config.*;

public class ItemAPITest {

    @Test
    @Tag("api")
    @DisplayName("Can get all items")
    public void canGetAllItems() {
        LoginAPI loginAPI = new LoginAPI();
        Response loginResponse = loginAPI.obtainToken(EMAIL, PASSWORD, DOMAIN);
        Assertions.assertEquals(200, loginResponse.statusCode());
        String token = loginResponse.jsonPath().getString("token");
        ItemAPI itemAPI = new ItemAPI(token);
        Response response = itemAPI.getAll();
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @Tag("api")
    @DisplayName("Can get an item with id")
    public void canGetAnItemWithID() {
        LoginAPI loginAPI = new LoginAPI();
        Response loginResponse = loginAPI.obtainToken(EMAIL, PASSWORD, DOMAIN);
        Assertions.assertEquals(200, loginResponse.statusCode());
        String token = loginResponse.jsonPath().getString("token");
        ItemAPI itemAPI = new ItemAPI(token);
        Item item = Item.builder()
                .name("coffee")
                .price(34.90F)
                .price_for_quantity(1)
                .quantity_unit("kg")
                .currency("BGN")
                .build();
        Response createResponse = itemAPI.create(item);
        String itemID = createResponse.jsonPath().getString("id");
        Assertions.assertEquals(201, createResponse.statusCode());
        Response response = itemAPI.get(Integer.parseInt(itemID));
        Assertions.assertEquals(200, response.statusCode());
        Response deleteResponse = itemAPI.delete(Integer.parseInt(itemID));
        Assertions.assertEquals(204, deleteResponse.statusCode());
    }

    @Test
    @Tag("api")
    @DisplayName("Can't get an item with invalid id")
    public void cantGetAnItemWithInvalidID() {
        LoginAPI loginAPI = new LoginAPI();
        Response loginResponse = loginAPI.obtainToken(EMAIL, PASSWORD, DOMAIN);
        Assertions.assertEquals(200, loginResponse.statusCode());
        String token = loginResponse.jsonPath().getString("token");
        ItemAPI itemAPI = new ItemAPI(token);
        Response response = itemAPI.get(123456789);
        String errorMessage = response.jsonPath().getString("error");
        Assertions.assertEquals("Item Not Found", errorMessage);
    }

    @Test
    @Tag("api")
    @DisplayName("Can create an item")
    public void canCreateAnItem() {
        LoginAPI loginAPI = new LoginAPI();
        Response loginResponse = loginAPI.obtainToken(EMAIL, PASSWORD, DOMAIN);
        Assertions.assertEquals(200, loginResponse.statusCode());
        String token = loginResponse.jsonPath().getString("token");
        ItemAPI itemAPI = new ItemAPI(token);
        Item item = Item.builder()
                .name("coffee")
                .price(34.90F)
                .price_for_quantity(1)
                .quantity_unit("kg")
                .currency("BGN")
                .build();
        Response createResponse = itemAPI.create(item);
        String itemID = createResponse.jsonPath().getString("id");
        Assertions.assertEquals(201, createResponse.statusCode());
        Response deleteResponse = itemAPI.delete(Integer.parseInt(itemID));
        Assertions.assertEquals(204, deleteResponse.statusCode());

    }

    @Test
    @Tag("api")
    @DisplayName("Can delete an item with id")
    public void canDeleteAnItemWIthID() {
        LoginAPI loginAPI = new LoginAPI();
        Response loginResponse = loginAPI.obtainToken(EMAIL, PASSWORD, DOMAIN);
        Assertions.assertEquals(200, loginResponse.statusCode());
        String token = loginResponse.jsonPath().getString("token");
        ItemAPI itemAPI = new ItemAPI(token);
        Item item = Item.builder()
                .name("coffee")
                .price(34.90F)
                .price_for_quantity(1)
                .quantity_unit("kg")
                .currency("BGN")
                .build();
        Response createResponse = itemAPI.create(item);
        String itemID = createResponse.jsonPath().getString("id");
        Assertions.assertEquals(201, createResponse.statusCode());
        Response deleteResponse = itemAPI.delete(Integer.parseInt(itemID));
        Assertions.assertEquals(204, deleteResponse.statusCode());
    }
/* PATCH not implemented yet :D
    @Test
    @Tag("api")
    @DisplayName("Can update an item")
    public void canUpdateAnItem() {
        LoginAPI loginAPI = new LoginAPI();
        Response loginResponse = loginAPI.obtainToken(EMAIL, PASSWORD, DOMAIN);
        Assertions.assertEquals(200, loginResponse.statusCode());
        String token = loginResponse.jsonPath().getString("token");
        ItemAPI itemAPI = new ItemAPI(token);
        Item item = Item.builder()
                .name("coffee")
                .price(34.90F)
                .price_for_quantity(1)
                .quantity_unit("kg")
                .currency("BGN")
                .build();
        Response createResponse = itemAPI.create(item);
        String itemID = createResponse.jsonPath().getString("id");
        Assertions.assertEquals(201, createResponse.statusCode());

        item = Item.builder()
                .name("coffee")
                .price(39.90F)
                .price_for_quantity(1)
                .quantity_unit("kg")
                .currency("BGN")
                .build();


        Response updateResponse = itemAPI.update(Integer.parseInt(itemID), item);
        Assertions.assertEquals(204, updateResponse.statusCode());

        Response deleteResponse = itemAPI.delete(Integer.parseInt(itemID));
        Assertions.assertEquals(204, deleteResponse.statusCode());
    }
    */




}

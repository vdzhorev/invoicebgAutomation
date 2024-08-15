package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;

import static utils.Config.*;

public class ItemAPITest {

    private static String token;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @BeforeAll
    public static void login() {
        LoginAPI loginAPI = new LoginAPI();
        Response loginResponse = loginAPI.obtainToken(EMAIL, PASSWORD, DOMAIN);
        Assertions.assertEquals(200, loginResponse.statusCode());
        token = loginResponse.jsonPath().getString("token");
    }

    @Test
    @Tag("api")
    @DisplayName("Can get all items")
    public void canGetAllItems() {

        ItemAPI itemAPI = new ItemAPI(token);
        Response getAllResponse = itemAPI.getAll();
        Assertions.assertEquals(200, getAllResponse.statusCode());
    }

    @Test
    @Tag("api")
    @DisplayName("Can get an item with id")
    public void canGetAnItemWithID() {

        ItemAPI itemAPI = new ItemAPI(token);
        Item item = Item.builder()
                .name("coffee" + LocalDateTime.now())
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

        ItemAPI itemAPI = new ItemAPI(token);
        Response response = itemAPI.get(123456789);
        String errorMessage = response.jsonPath().getString("error");
        Assertions.assertEquals("Item Not Found", errorMessage);
    }

    @Test
    @Tag("api")
    @DisplayName("Can create an item")
    public void canCreateAnItem() {

        ItemAPI itemAPI = new ItemAPI(token);
        Item item = Item.builder()
                .name("coffee" + LocalDateTime.now())
                .price(34.90F)
                .price_for_quantity(1)
                .quantity_unit("kg")
                .currency("BGN")
                .build();
        Response createResponse = itemAPI.create(item);
        int itemID = createResponse.jsonPath().get("id");
        Assertions.assertEquals(201, createResponse.statusCode());
        Response getResponse = itemAPI.get(itemID);
        String responseBody = getResponse.asString();
        Item newItem = GSON.fromJson(responseBody, Item.class);
        Assertions.assertEquals(item.getName(), newItem.getName());
        Assertions.assertEquals(item.getCurrency(), newItem.getCurrency());
        Assertions.assertEquals(item.getPrice(), newItem.getPrice());
        Assertions.assertEquals(item.getQuantity_unit(), newItem.getQuantity_unit());
        Assertions.assertEquals(item.getPrice_for_quantity(), newItem.getPrice_for_quantity());
        Response deleteResponse = itemAPI.delete(itemID);
        Assertions.assertEquals(204, deleteResponse.statusCode());
    }

    @Test
    @Tag("api")
    @DisplayName("Can delete an item with id")
    public void canDeleteAnItemWIthID() {

        ItemAPI itemAPI = new ItemAPI(token);
        Item item = Item.builder()
                .name("coffee" + LocalDateTime.now())
                .price(34.90F)
                .price_for_quantity(1)
                .quantity_unit("kg")
                .currency("BGN")
                .build();
        Response createResponse = itemAPI.create(item);
        int itemID = createResponse.jsonPath().get("id");
        Assertions.assertEquals(201, createResponse.statusCode());
        Response deleteResponse = itemAPI.delete(itemID);
        Assertions.assertEquals(204, deleteResponse.statusCode());
    }

    @Test
    @Tag("api")
    @DisplayName("Can update an item")
    @Disabled("This test is disabled, because PATCH is not implemented yet.")
    public void canUpdateAnItem() {

        ItemAPI itemAPI = new ItemAPI(token);
        Item item = Item.builder()
                .name("coffee" + LocalDateTime.now())
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

}

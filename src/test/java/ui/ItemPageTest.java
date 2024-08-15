package ui;

import api.ItemAPI;
import api.LoginAPI;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import utils.Browser;

import static utils.Config.EMAIL;
import static utils.Config.PASSWORD;
import static utils.Config.DOMAIN;


public class ItemPageTest {

    @BeforeEach
    public void setup() {
        LoginPage.setup();
        Assertions.assertEquals("Dzhorev LTD", LoginPage.getHeadingText());
    }

    @AfterEach
    public void tearDown() {
        Browser.tearDown();
    }

    @Test
    @Tag("ui")
    @DisplayName("Correct message is displayed when there are no items in the system")
    public void correctMessageIsDisplayedWhenThereAreNoItemsInTheSystem() {
        LoginAPI loginAPI = new LoginAPI();
        Response loginResponse = loginAPI.obtainToken(EMAIL, PASSWORD, DOMAIN);
        String token = loginResponse.jsonPath().getString("token");

        ItemAPI itemAPI = new ItemAPI(token);
        itemAPI.deleteALL();

        LoginPage.enterEmail(EMAIL);
        LoginPage.enterPassword(PASSWORD);
        LoginPage.clickLoginButton();
        Assertions.assertEquals(EMAIL, HomePage.getUserPanelText());

        HomePage.clickItemsButton();

        Assertions.assertEquals("Не са намерени артикули, отговарящи на зададените критерии.", ItemPage.getNoItemsMessage());
    }
}

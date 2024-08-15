package ui;

import org.junit.jupiter.api.*;
import utils.Browser;
import static utils.Config.*;



public class LoginPageTest {


    @BeforeEach
    public void setUp() {
        LoginPage.setup();
    }

    @AfterEach
    public void tearDown() {
        Browser.tearDown();
    }

    @Test
    @Tag("ui")
    @DisplayName("Can login with valid credentials")
    public void canLoginWithValidCredentials() {

        Assertions.assertEquals("Dzhorev LTD", LoginPage.getHeadingText());

        LoginPage.enterEmail(EMAIL);
        LoginPage.enterPassword(PASSWORD);
        LoginPage.clickLoginButton();

        Assertions.assertEquals(EMAIL, HomePage.getUserPanelText());
    }

    @Test
    @Tag("ui")
    @DisplayName("Can't login with invalid password")
    public void cantLoginWithInvalidPassword() {

        Assertions.assertEquals("Dzhorev LTD", LoginPage.getHeadingText());

        LoginPage.enterEmail(EMAIL);
        LoginPage.enterPassword("WrongPassword");
        LoginPage.clickLoginButton();

        Assertions.assertEquals("Грешно потребителско име или парола. Моля, опитайте отново.", LoginPage.getErrorMessage().strip());
    }

    @Test
    @Tag("ui")
    @DisplayName("Can't login with blank credentials")
    public void cantLoginWithBlankCredentials() {

        Assertions.assertEquals("Dzhorev LTD", LoginPage.getHeadingText());

        LoginPage.clickLoginButton();

        Assertions.assertEquals("Моля, попълнете вашия email", LoginPage.getErrorMessage().strip());
    }

    @Test
    @Tag("ui")
    @DisplayName("Can log out")
    public void canLogout() {

        Assertions.assertEquals("Dzhorev LTD", LoginPage.getHeadingText());

        LoginPage.enterEmail(EMAIL);
        LoginPage.enterPassword(PASSWORD);
        LoginPage.clickLoginButton();

        Assertions.assertEquals(EMAIL, HomePage.getUserPanelText());

        HomePage.clickUserPanel();
        HomePage.clickLogoutLink();

        Assertions.assertEquals("Dzhorev LTD", LoginPage.getHeadingText());

        Assertions.assertEquals("Вие излязохте от акаунта си.", HomePage.getLogoutSuccessMessage().strip());
    }


}

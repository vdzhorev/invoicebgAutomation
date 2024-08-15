package ui;

import org.openqa.selenium.By;
import utils.Browser;


public class HomePage {

    private static final By USER_PANEL_HEADER = By.cssSelector("#userpanel");
    private static final By LOGOUT_LINK = By.cssSelector(".selenium-button-logout");
    private static final By LOGOUT_SUCCESS_MESSAGE = By.cssSelector("#okmsg");
    private static final By ITEMS_BUTTON = By.cssSelector("#tabs_objects");

    public static String getUserPanelText() {
        String userPanelText = Browser.driver.findElement(USER_PANEL_HEADER).getText();
        return userPanelText;
    }

    public static void clickUserPanel(){
        Browser.driver.findElement(USER_PANEL_HEADER).click();
    }

    public static void clickLogoutLink() {
        Browser.driver.findElement(LOGOUT_LINK).click();
    }

    public static String getLogoutSuccessMessage(){
        return Browser.driver.findElement(LOGOUT_SUCCESS_MESSAGE).getText();
    }

    public static void clickItemsButton() {
        Browser.driver.findElement(ITEMS_BUTTON).click();
    }
}

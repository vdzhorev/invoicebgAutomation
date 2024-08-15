package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.Browser;

import java.time.Duration;

import static utils.Config.*;

public class LoginPage {


    private static final By HEADING = By.id("wellcome");
    private static final By EMAIL_FIELD = By.id("loginusername");
    private static final By PASSWORD_FIELD = By.id("loginpassword");
    private static final By LOGIN_BUTTON = By.id("loginsubmit");

    public static void setup() {
        Browser.open(LOGIN_PAGE);
    }

    public static void tearDown() {
        Browser.tearDown();
    }

    public static String getHeadingText() {
        WebElement heading = Browser.driver.findElement(HEADING);
        String headingText = heading.getText();
        return headingText;
    }

    public static void enterEmail(String email) {
        WebElement usernameField = Browser.driver.findElement(EMAIL_FIELD);
        usernameField.sendKeys(email);
    }

    public static void enterPassword(String password) {
        WebElement passwordField = Browser.driver.findElement(PASSWORD_FIELD);
        passwordField.sendKeys(password);
    }

    public static void clickLoginButton() {
        WebElement loginButton = Browser.driver.findElement(LOGIN_BUTTON);
        loginButton.click();
    }
    public static String getErrorMessage() {
        WebElement loginError = Browser.driver.findElement(By.id("error"));
        String loginErrorText = loginError.getText();
        return loginErrorText;
    }
}

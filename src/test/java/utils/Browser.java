package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Browser {

    public static WebDriver driver;

    public static void open(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public static void tearDown() {
        driver.quit();
    }
}

package ui;

import org.openqa.selenium.By;
import utils.Browser;

public class ItemPage {

    private static final String ITEM_PAGE = "https://dzhorev-ltd.inv.bg/objects";
    private static final By NO_ITEMS_MESSAGE = By.id("emptylist");

    public static String getNoItemsMessage() {
        return Browser.driver.findElement(NO_ITEMS_MESSAGE).getText().strip();
    }


}

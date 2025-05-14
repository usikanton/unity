package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    private static final String LOGGED_IN_EMAIL = "//section[@data-css='logged-in']//div[text()='%s']";

    @FindBy(xpath = "//div[text()='Happy Folder']")
    private WebElement navMenuDropdown;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage(WebDriver driver, String email) {
        super(driver);
        driver.findElement(By.xpath(LOGGED_IN_EMAIL.formatted(email))).isDisplayed();
    }

    public LeftNavigationMenu openNavigationMenu() {
        navMenuDropdown.click();
        return new LeftNavigationMenu(driver);
    }
}

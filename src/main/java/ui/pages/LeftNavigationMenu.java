package ui.pages;

import common.enums.LeftNavigationItem;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui.pages.post.PostPage;
import ui.pages.profile.ProfilePage;
import ui.pages.publisher.PublisherPage;

@Slf4j
public class LeftNavigationMenu extends BasePage {

    private static final String NAVIGATION_ITEM_XPATH_TEMPLATE = "//a[contains(@href, '%s')]";

    public LeftNavigationMenu(WebDriver driver) {
        super(driver);
    }

    public <T extends BasePage> T openPageFromNavigationMenu(LeftNavigationItem navigationItem) {
        BasePage page = null;
        driver.findElement(By.xpath(String.format(NAVIGATION_ITEM_XPATH_TEMPLATE, navigationItem.getValue()))).click();
        switch (navigationItem) {
            case PUBLISHER -> page = new PublisherPage(driver);
            case POST -> page = new PostPage(driver);
            case PROFILE -> page = new ProfilePage(driver);
            default -> log.info("No navigation items found with name %s", navigationItem.getValue());
        }
        return (T) page;
    }
}

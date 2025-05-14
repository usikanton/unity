package ui.steps;

import org.openqa.selenium.WebDriver;
import ui.pages.HomePage;

public class HomePageSteps {

    WebDriver driver;
    private HomePage homePage;

    public void navigateToPublisherPage() {
        homePage = new HomePage(driver);
        homePage.openNavigationMenu();
    }
}

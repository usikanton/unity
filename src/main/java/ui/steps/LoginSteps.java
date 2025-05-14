package ui.steps;

import org.openqa.selenium.WebDriver;
import ui.pages.LoginPage;

public class LoginSteps {

    WebDriver driver;
    private LoginPage loginPage;

    public void logIn(String loginEmail, String password) {
        loginPage = new LoginPage(driver);
        loginPage.enterEmail(loginEmail);
        loginPage.enterPassword(password);
        loginPage.submitForm(loginEmail);
    }
}

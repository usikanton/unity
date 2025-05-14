package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//form//button[text()='Login']")
    private WebElement loginBtn;

    public LoginPage(WebDriver driver) {
        super(driver);
        emailInput.isDisplayed();
        passwordInput.isDisplayed();
    }

    public void enterEmail(String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public HomePage submitForm(String email) {
        loginBtn.click();
        return new HomePage(driver, email);
    }

    public HomePage logIn(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        return submitForm(email);
    }
}

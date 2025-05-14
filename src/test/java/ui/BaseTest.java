package ui;

import common.enums.URLs;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ui.pages.LoginPage;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

public abstract class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.of(30, SECONDS));
        driver.manage().window().maximize();
        loginPage = openLoginPage();
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }

    private LoginPage openLoginPage() {
        driver.get(URLs.BASE.getUrl());
        return new LoginPage(driver);
    }
}

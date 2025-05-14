package ui.pages.publisher;

import api.models.publisher.PublisherRequest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.pages.BasePage;

public class CreateNewPublisherPage extends BasePage {

    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveBtn;

    public CreateNewPublisherPage(WebDriver driver) {
        super(driver);
    }

    public void enterPublisherName(String name) {
        nameInput.sendKeys(name);
    }

    public void enterPublisherEmail(String email) {
        emailInput.sendKeys(email);
    }

    public PublisherPage saveNewPublisher() {
        saveBtn.click();
        return new PublisherPage(driver);
    }

    public PublisherPage createNewPublisher(PublisherRequest publisher) {
        return createNewPublisher(publisher.getPublisherName(), publisher.getPublisherEmail());
    }

    public PublisherPage createNewPublisher(String publisherName, String publisherEmail) {
        enterPublisherName(publisherName);
        enterPublisherEmail(publisherEmail);
        return saveNewPublisher();
    }
}

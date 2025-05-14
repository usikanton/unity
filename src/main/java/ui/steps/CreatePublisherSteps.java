package ui.steps;

import org.openqa.selenium.WebDriver;
import ui.pages.publisher.CreateNewPublisherPage;

public class CreatePublisherSteps {

    WebDriver driver;
    CreateNewPublisherPage createNewPublisherPage;

    public void createNewPublisher(String publisherName, String publisherEmail) {
        createNewPublisherPage = new CreateNewPublisherPage(driver);
        createNewPublisherPage.enterPublisherName(publisherName);
        createNewPublisherPage.enterPublisherEmail(publisherEmail);
        createNewPublisherPage.saveNewPublisher();
    }
}

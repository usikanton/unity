package ui.pages.post;

import api.models.post.PostRequest;
import api.models.post.SomeJsonItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.pages.BasePage;

import java.util.stream.IntStream;

public class CreateNewPostPage extends BasePage {

    private static final String SOME_JSON_NUMBER_INPUT_XPATH_TEMPLATE = "//label[@for='someJson.%s']/parent::div//label[text()='Some Json Number']/following-sibling::input";
    private static final String SOME_JSON_STRING_INPUT_XPATH_TEMPLATE = "//label[@for='someJson.%s']/parent::div//label[text()='Some Json String']/following-sibling::input";
    private static final String SOME_JSON_BOOLEAN_CHECKBOX_XPATH_TEMPLATE = "//label[@for='someJson.%s']/parent::div//label[text()='Some Json Boolean']/preceding-sibling::span[child::input[@type='checkbox']]";
    private static final String SOME_JSON_DATE_INPUT_XPATH_TEMPLATE = "//label[@for='someJson.%s']/parent::div//label[text()='Some Json Date']/following-sibling::section//input";

    @FindBy(id = "title")
    private WebElement postTitleInput;
    @FindBy(id = "content")
    private WebElement postContentInput;
    @FindBy(xpath = "//button[@data-testid='someJson-add']")
    private WebElement addNewJsonItemBtn;
    @FindBy(xpath = "//section[@data-testid='property-edit-status']")
    private WebElement statusDropdown;
    @FindBy(xpath = "//span[child::input[@id='published']]")
    private WebElement publishedCheckbox;
    @FindBy(xpath = "//section[@data-testid='property-edit-publisher']")
    private WebElement publisherDropdown;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveBtn;

    public CreateNewPostPage(WebDriver driver) {
        super(driver);
    }

    public void enterTitle(String title) {
        postTitleInput.sendKeys(title);
    }

    public void enterContent(String content) {
        postContentInput.sendKeys(content);
    }

    public void selectPostStatus(String status) {
        statusDropdown.click();
        statusDropdown.findElement(By.xpath(String.format(DROPDOWN_RESULTS_XPATH_TEMPLATE, status))).click();
    }

    public void checkPublishedCheckbox() {
        publishedCheckbox.click();
    }

    public void selectPublisher(String publisherEmail) {
        publisherDropdown.click();
        publisherDropdown.findElement(By.xpath(String.format(DROPDOWN_RESULTS_XPATH_TEMPLATE, publisherEmail))).click();
    }

    public void enterNumber(int recordIndex, String number) {
        WebElement numberInput = driver.findElement(By.xpath(String.format(SOME_JSON_NUMBER_INPUT_XPATH_TEMPLATE, recordIndex)));
        numberInput.sendKeys(number);
    }

    public void enterString(int recordIndex, String stringValue) {
        WebElement stringInput = driver.findElement(By.xpath(String.format(SOME_JSON_STRING_INPUT_XPATH_TEMPLATE, recordIndex)));
        stringInput.click();
        stringInput = driver.findElement(By.xpath(String.format(SOME_JSON_STRING_INPUT_XPATH_TEMPLATE, recordIndex)));
        stringInput.sendKeys(stringValue);
    }

    public void selectCheckbox(int recordIndex) {
        WebElement booleanCheckbox = driver.findElement(By.xpath(String.format(SOME_JSON_BOOLEAN_CHECKBOX_XPATH_TEMPLATE, recordIndex)));
        booleanCheckbox.click();
    }

    public void enterDate(int recordIndex, String date) {
        WebElement dateInput = driver.findElement(By.xpath(String.format(SOME_JSON_DATE_INPUT_XPATH_TEMPLATE, recordIndex)));
        dateInput.click();
        dateInput.sendKeys(date);
    }

    public void enterJsonData(int recordIndex, SomeJsonItem someJsonItem) {
        enterNumber(recordIndex, String.valueOf(someJsonItem.getNumber()));
        enterString(recordIndex, someJsonItem.getString());
        if (someJsonItem.getBool())
            selectCheckbox(recordIndex);
        enterDate(recordIndex, someJsonItem.getDate());
    }

    public PostPage savePost() {
        saveBtn.click();
        return new PostPage(driver);
    }

    public PostPage createPost(PostRequest post, String publisherEmail) {
        enterTitle(post.getTitle());
        enterContent(post.getContent());
        if (!post.getSomeJson().isEmpty()) {
            addSomeJsonItems(post);
        }
        selectPostStatus(post.getStatus());
        checkPublishedCheckbox();
        selectPublisher(publisherEmail);
        saveBtn.click();
        return new PostPage(driver);
    }

    private CreateNewPostPage addSomeJsonItems(PostRequest post) {
        IntStream.range(0, post.getSomeJson().size()).forEach(i -> {
            addNewJsonItemBtn.click();
            enterJsonData(i, post.getSomeJson().get(i));
        });
        return new CreateNewPostPage(driver);
    }
}

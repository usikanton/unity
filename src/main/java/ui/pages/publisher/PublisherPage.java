package ui.pages.publisher;

import common.enums.ColumnHeaders;
import common.enums.PublisherColumns;
import common.enums.SortingDirection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.pages.BasePage;

import java.util.List;

public class PublisherPage extends BasePage {

    @FindBy(xpath = "//a[@data-css='Publisher-new-button']")
    private WebElement createNewPublisherBtn;
    @FindBy(xpath = "//section[@data-css='Publisher-list']")
    private WebElement publisherListSection;
    @FindBy(xpath = "//tbody/tr[@data-css='Publisher-table-row']")
    private List<WebElement> publishersList;

    public PublisherPage(WebDriver driver) {
        super(driver);
    }

    public CreateNewPublisherPage clickCreateNewPublisher() {
        createNewPublisherBtn.click();
        return new CreateNewPublisherPage(driver);
    }

    private int getLatestPublisherId() {
        sortBy(ColumnHeaders.ID, SortingDirection.ASC);
        return Integer.parseInt(publishersList.stream().findFirst().orElseThrow(() -> new AssertionError("No posts found on the page"))
                .getAttribute(DATA_ID_ATTRIBUTE));
    }

    public String getLatestPublisherCellValue(PublisherColumns column) {
        return getPublisherCellValueById(getLatestPublisherId(), column);
    }

    public String getPublisherCellValueById(int postId, PublisherColumns column) {
        return driver.findElement(By.xpath(String.format(TABLE_CELL_VALUE_XPATH_TEMPLATE, postId,
                column.getColumnName()))).getAttribute(INNER_TEXT_ATTRIBUTE);
    }

    public CreateNewPublisherPage openCreatePublisherForm() {
        return clickCreateNewPublisher();
    }
}

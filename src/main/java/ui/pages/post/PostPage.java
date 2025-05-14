package ui.pages.post;

import api.models.post.PostRequest;
import common.enums.ColumnHeaders;
import common.enums.PostColumns;
import common.enums.SortingDirection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.pages.BasePage;

import java.time.Duration;
import java.util.List;

import static java.time.temporal.ChronoUnit.SECONDS;

public class PostPage extends BasePage {

    private static final String ROW_ACTION_DROPDOWN_BY_TITLE_TEMPLATE = "//tr/td[@data-property-name='title' and child::section[text()='%s']]/following-sibling::td//a[@data-testid='actions-dropdown']";

    @FindBy(xpath = "//a[@data-css='Post-new-button']")
    private WebElement createNewPostBtn;
    @FindBy(xpath = "//section[contains(@class, 'DropDownMenu')]//a[@data-testid='action-edit']")
    private WebElement editActionItem;
    @FindBy(xpath = "//tbody/tr[@data-css='Post-table-row']")
    private List<WebElement> postsList;


    public PostPage(WebDriver driver) {
        super(driver);
    }

    public CreateNewPostPage clickCreateNewPost() {
        createNewPostBtn.click();
        return new CreateNewPostPage(driver);
    }

    private int getLatestPostId() {
        sortBy(ColumnHeaders.ID, SortingDirection.ASC);
        return Integer.parseInt(postsList.stream().findFirst().orElseThrow(() -> new AssertionError("No posts found on the page"))
                .getAttribute(DATA_ID_ATTRIBUTE));
    }

    public String getLatestPostCellValue(PostColumns column) {
        return getPostCellValueById(getLatestPostId(), column);
    }

    public String getPostCellValueById(int postId, PostColumns column) {
        return driver.findElement(By.xpath(String.format(TABLE_CELL_VALUE_XPATH_TEMPLATE, postId,
                column.getPropertyName()))).getAttribute(INNER_TEXT_ATTRIBUTE);
    }

    public CreateNewPostPage editRecordByTitle(PostRequest post) {
        WebElement actionsDropdown = driver.findElement(By.xpath(String.format(ROW_ACTION_DROPDOWN_BY_TITLE_TEMPLATE, post.getTitle())));
        hoverElement(actionsDropdown);
        editActionItem = new WebDriverWait(driver, Duration.of(10, SECONDS)).until(ExpectedConditions.visibilityOf(editActionItem));
        editActionItem.click();
        return new CreateNewPostPage(driver);
    }


}

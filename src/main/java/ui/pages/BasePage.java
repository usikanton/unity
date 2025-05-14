package ui.pages;

import common.enums.ColumnHeaders;
import common.enums.SortingDirection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.RegexUtils;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

public abstract class BasePage {

    protected static final String DATA_ID_ATTRIBUTE = "data-id";
    protected static final String INNER_TEXT_ATTRIBUTE = "innerText";
    protected static final String REFERENCE_ATTRIBUTE = "href";
    protected static final String TABLE_CELL_VALUE_XPATH_TEMPLATE = "//table//tr[@data-id='%s']/td[@data-property-name='%s']";
    protected static final String TABLE_COLUMN_SORTING_XPATH_TEMPLATE = "//thead//a[@href and text()='%s']";
    protected static final String DROPDOWN_RESULTS_XPATH_TEMPLATE = "//div[contains(@class, 'MenuList')]/div[@id and text()='%s']";
    private static final String SORTING_ARROW_CHILD_XPATH = "./span/*";

    protected WebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected void hoverElement(WebElement actionsDropdown) {
        Actions actions = new Actions(driver);
        actions.moveToElement(actionsDropdown)
                .perform();
    }

    protected void sortBy(ColumnHeaders sortByHeader, SortingDirection expectedDirection) {
        WebElement headerSortedBy = findSortingByHeader(sortByHeader);
        if (headerSortedBy.findElements(By.xpath(SORTING_ARROW_CHILD_XPATH)).isEmpty()) {
            headerSortedBy.click();
            headerSortedBy = findSortingByHeader(sortByHeader);
            new WebDriverWait(driver, Duration.of(10, SECONDS))
                    .until(ExpectedConditions.visibilityOfAllElements(headerSortedBy.findElement(By.xpath(SORTING_ARROW_CHILD_XPATH))));
        }
        String actualDirection = RegexUtils.find(headerSortedBy.getAttribute(REFERENCE_ATTRIBUTE), RegexUtils.SORTING_DIRECTION_PATTERN, 1);
        if (!actualDirection.equals(expectedDirection.getSortingDirection()))
            headerSortedBy.click();
    }

    private WebElement findSortingByHeader(ColumnHeaders sortByHeader) {
        return driver.findElement(By.xpath(String.format(TABLE_COLUMN_SORTING_XPATH_TEMPLATE,
                sortByHeader.getHeaderColumnName())));
    }
}

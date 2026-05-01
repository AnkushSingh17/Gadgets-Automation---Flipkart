package pages;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class SearchResultsPage extends BasePage {

    private By popularityTab = By.xpath("//div[text()='Popularity']");
    private By minPriceDropdown = By.xpath("(//select[@class = 'hbnjE2'])[1]");
    private By maxPriceDropdown = By.xpath("(//select[@class = 'hbnjE2'])[2]");
    private By productNames = By.xpath("//*[@id=\"container\"]//div[2]/div/div/div/div/a[2]");
    private By productPrices = By.xpath("//*[@id=\"container\"]//a[3]/div/div[1]");

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public void sortByPopularity() {
        wait.until(ExpectedConditions.elementToBeClickable(popularityTab)).click();
    }

    public void setPriceRange(String min, String max) {
        // 1. Wait for visibility and scroll
        WebElement minElem = wait.until(ExpectedConditions.visibilityOfElementLocated(minPriceDropdown));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", minElem);

        // 2. Select Min Price (Note the ₹ symbol)
        Select minSelect = new Select(minElem);
        minSelect.selectByVisibleText("₹" + min);

        // 3. IMPORTANT: Wait for the page to refresh/overlay to disappear
        // before interacting with the Max dropdown
        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        // 4. Select Max Price
        WebElement maxElem = wait.until(ExpectedConditions.elementToBeClickable(maxPriceDropdown));
        Select maxSelect = new Select(maxElem);
        maxSelect.selectByVisibleText("₹" + max);
    }
    public void printTopProducts(int count) {
        List<WebElement> names = driver.findElements(productNames);
        List<WebElement> prices = driver.findElements(productPrices);

        System.out.println("Top " + count + " Products:");
        for (int i = 0; i < Math.min(count, names.size()); i++) {
            System.out.println((i + 1) + ". " + names.get(i).getText() + " - " + prices.get(i).getText());
        }
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
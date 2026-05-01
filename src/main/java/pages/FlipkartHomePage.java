package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FlipkartHomePage extends BasePage {

    private By closePopupButton = By.xpath("/html/body/div[5]/div/span");
    private By searchBox = By.name("q");

    public FlipkartHomePage(WebDriver driver) {
        super(driver);
    }

    public void closePopup() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("qc0M7c")));
        driver.findElement(closePopupButton).click();
    }

    public void searchProduct(String product) {
        driver.findElement(searchBox).sendKeys(product);
        driver.findElement(searchBox).submit();
    }
}
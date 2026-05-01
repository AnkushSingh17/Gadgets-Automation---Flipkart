package tests;

import base.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.Test;
import pages.FlipkartHomePage;
import pages.SearchResultsPage;

import java.io.File;

public class FlipkartSearchTest extends BaseTest {

    @Test
    public void testBluetoothHeadphoneSearch() throws InterruptedException {
        FlipkartHomePage homePage = new FlipkartHomePage(driver);
        SearchResultsPage resultsPage = new SearchResultsPage(driver);

        homePage.closePopup();
        homePage.searchProduct("Bluetooth headphone");
        captureScreenshot("BluetoothHeadphoneSearch");

        resultsPage.sortByPopularity();
        resultsPage.setPriceRange("600", "1500");
        resultsPage.printTopProducts(5);
        Thread.sleep(2000);
        captureScreenshot("screenshot");
    }

    public void captureScreenshot(String fileName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File target = new File(System.getProperty("user.dir") + "\\screenshots\\" + fileName + ".png");
        source.renameTo(target);
    }
}
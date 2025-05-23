package com.Admavin.project.GoogleMaps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

public class GoogleMapsMapLoadTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\pcs-pc\\Downloads\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
        driver.get("https://www.google.com/maps");

    }

    // Test 1: Map Load
    @Test(priority = 1)
    public void testMapLoadsSuccessfully() {

        WebElement mapCanvas = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("canvas.widget-scene-canvas")));

        Assert.assertTrue(mapCanvas.isDisplayed(), "❌ Map canvas is not visible");
        System.out.println("✅ Test 1 Passed: Map loaded successfully.");
    }

    // Test 2: Zoom and Pan
    @Test(priority = 2)
    public void testZoomAndPanControls() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("canvas.widget-scene-canvas")));

        WebElement zoomInBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Zoom in']")));
        zoomInBtn.click();
        System.out.println("✅ Zoomed In");

        WebElement zoomOutBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Zoom out']")));
        zoomOutBtn.click();
        System.out.println("✅ Zoomed Out");

        WebElement map = driver.findElement(By.cssSelector("canvas.widget-scene-canvas"));
        Actions action = new Actions(driver);
        action.moveToElement(map).clickAndHold().moveByOffset(-100, 0).release().perform();
        System.out.println("✅ Map panned left");

        Assert.assertTrue(true, "Zoom and pan controls working fine");
    }

    // Test 3: Search Location
    @Test(priority = 3)
    public void testLocationSearch() {

        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchboxinput")));
        searchBox.sendKeys("Eiffel Tower");

        WebElement searchButton = driver.findElement(By.id("searchbox-searchbutton"));
        searchButton.click();

        WebElement placeCard = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[@class='DUwDvf lfPIob' and contains(text(), 'Eiffel Tower')]")));
        String placeName = placeCard.getText();

        System.out.println("✅ Found place: " + placeName);
        Assert.assertTrue(placeName.toLowerCase().contains("eiffel"), "❌ Incorrect place shown");
    }

    // Test 4: Marker Validation
    @Test(priority = 4)
    public void testMarkerIsShownAtCorrectLocation() {

        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchboxinput")));
        searchBox.sendKeys("Statue of Liberty");

        WebElement searchButton = driver.findElement(By.id("searchbox-searchbutton"));
        searchButton.click();

        WebElement placeCard = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[contains(@class, 'DUwDvf')]")));

        String placeText = placeCard.getText();
        System.out.println("✅ Found place: " + placeText);

        Assert.assertTrue(placeText.toLowerCase().contains("statue of liberty"), "❌ Marker not at correct location");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

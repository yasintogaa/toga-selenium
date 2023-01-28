package tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utils.DriverManager;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public abstract class BaseTest extends DriverManager {

    protected JavascriptExecutor js;
    protected Actions actions;
    protected SoftAssert softAssertion;

    //hooks
    @Parameters("browser")
    @BeforeClass
    void setUp(@Optional("browser") String browser){setDriver(browser);}

    @BeforeMethod
    void beforeMethod(){
        driver.get(baseUrl);
        addViewedCookiePolicy();
        softAssertion = new SoftAssert(); }

    @AfterMethod
    void afterMethod(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE){
            takeScreenshot(result.getMethod().getMethodName(),result.getTestClass().getName());
        }
    }
    @AfterClass
    void tearDown(){driver.quit();}

    //helpers
    void takeScreenshot(String testMethod, String testClass){
        Date date = new Date();
        String screenshotsDir = "src/test/resources/ScreenshotsOfDefects/";
        String extension = ".png";
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(screenshotsDir + testClass + "/" + testMethod + "_"
                    + date.getTime() + extension));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void scrollToFooterOfPage() throws InterruptedException {
        while(!driver.findElement(By.xpath("//section[@id='footer']")).isDisplayed()){
            js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        }
    }
    public void scrollToElement(WebElement element) throws InterruptedException {
        actions = new Actions(driver);
        try {
            actions.moveToElement(element).build().perform();
        }
        catch (MoveTargetOutOfBoundsException moveTargetOutOfBoundsException) {
            moveTargetOutOfBoundsException.printStackTrace();

        }
    }
    public void highlightElement(WebElement element){
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
    }
}

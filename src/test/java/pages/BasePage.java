package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverManager;

import java.time.Duration;

public abstract class BasePage {

    //properties
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String pageURL;
    protected String browserTitle;

    //constructor
    protected BasePage(WebDriver driver){
        this.driver = driver;
        this.pageURL = DriverManager.baseUrl;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //behaviours
    public String getPageUrl(){
        return this.pageURL;
    }
    public String getBrowserTitle(){ return this.browserTitle;}
    public WebElement getElement(By selector){
        wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        return driver.findElement(selector);
    }
    public String getElementText(WebElement element, By selector){
        wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        return element.findElement(selector).getText();}

    public void clickWithJS(WebElement element){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }
    public void navigateToHrefValue(By selector){
        driver.get(driver.findElement(selector).getAttribute("href"));
    }

}

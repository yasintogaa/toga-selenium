package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LeverPage extends BasePage{

    public By jobTitleSelector = By.tagName("h2");

    public LeverPage(WebDriver driver){
        super(driver);
        this.pageURL = "useinsider/";
    }
    public String getJobTitleText() {
        return driver.findElement(jobTitleSelector).getText();
    }
}

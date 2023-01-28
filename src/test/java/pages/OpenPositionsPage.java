package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.components.NavigationBarComponent;

import java.util.List;

public class OpenPositionsPage extends BasePage {

    //components
    public NavigationBarComponent navigationBarComponent;

    //selectors
    public By locationFilterDropdownSelector = By.xpath("//span[@id = 'select2-filter-by-location-container']");
    public By locationOptionsSelector; //init in selectLocationFilter() as parametrically
    public By searchResultsSelector = By.xpath("//div[contains(@class,'position-list-item-wrapper')]");
    public By positionTitleSelector = By.xpath("//p[contains(@class,'position-title')]");
    public By positionDepartmentSelector = By.xpath("//span[contains(@class, 'position-department')]");
    public By positionLocationSelector = By.xpath("//div[contains(@class, 'position-location')]");
    public By applyButtonSelector = By.xpath("//a[contains(text(),'Apply Now')]");

    public OpenPositionsPage(WebDriver driver){
        super(driver);
    }

    public void selectLocationFilter(String location) throws InterruptedException {
        //TODO: should it be select differently in gecko and chrome??
        String dropdownOptionsTag = (driver instanceof FirefoxDriver) ? "li" : "option";
        locationOptionsSelector = By.xpath("//" + dropdownOptionsTag + "[contains(text(),'" + location + "')]");

        driver.findElement(locationFilterDropdownSelector).click();
        driver.findElement(locationOptionsSelector).click();
        Thread.sleep(2000);//TODO:couldn't find any solution except Thread.sleep here
    }
    public void applyJob(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(applyButtonSelector));
        element.findElement(applyButtonSelector).click();
    }

    public List<WebElement> getResultItems(){
        //wait.until(ExpectedConditions.elementToBeClickable(searchResultsSelector));
        return driver.findElements(searchResultsSelector);
    }
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.components.NavigationBarComponent;

public class CareersDetailPage extends BasePage {
    //This POM is generic for all departments
    //components
    public NavigationBarComponent navigationBarComponent;

    //selectors
    public By seeAllJobsButtonSelector;

    //constructor
    public CareersDetailPage(WebDriver driver, String departmentName, String departmentLinkName, String departmentButtonLabel){
        super(driver);
        this.seeAllJobsButtonSelector = By.xpath("//a[contains(text(),'See all "+ departmentButtonLabel + " jobs')]");
        this.pageURL += "careers/" + departmentLinkName;
        this.browserTitle = "Insider " + departmentName.toLowerCase() + " job opportunities";
    }
}

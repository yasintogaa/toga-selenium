package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.components.NavigationBarComponent;

public class CareersPage extends BasePage {
    //components
    public NavigationBarComponent navigationBarComponent;

    //selectors of some main parts
    public By locationSectionSelector = By.xpath("//section[@id='career-our-location']");
    public By teamsSectionSelector = By.xpath("//section[@id='career-find-our-calling']");
    public By lifeAtInsiderSectionSelector = By.xpath("//h2[contains(text(),'Life at Insider')]");
    public By seeAllTeamsButtonSelector = By.xpath("//a[contains(text(),'See all teams')]");
    private By teamItemSelector; //init in getter

    //constructor
    public CareersPage(WebDriver driver){
        super(driver);
        this.pageURL += "careers/";
        this.browserTitle = "Insider Careers";
    }

    public By getTeamItemSelector(String departmentName) {
        teamItemSelector = By.xpath("//h3[contains(text(),'" + departmentName + "')]/parent::a");
        return teamItemSelector;
    }
}

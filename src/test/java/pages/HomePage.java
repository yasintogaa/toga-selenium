package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.components.NavigationBarComponent;

public class HomePage extends BasePage {
    //components
    public NavigationBarComponent navigationBarComponent;

    //selectors of some main parts
    public By announceBarSelector = By.xpath("//div[@id='announce']");
    public By navigationBarSelector = By.xpath("//nav[@id='navigation']");
    public By mainHeadSectionSelector = By.xpath("//section[@id='main-head']");
    public By platformBrandsSectionSelector = By.xpath("//section[@id='platform-brands']");
    public By quotesSectionSelector = By.xpath("//section[@id = 'quotes']");
    public By footerSectionSelector = By.xpath("//section[@id='footer']");

    //constructor
    public HomePage(WebDriver driver){
        super(driver);
        navigationBarComponent = new NavigationBarComponent(driver);
        this.browserTitle = "#1 Leader in Individualized, Cross-Channel CX — Insider";
    }

}

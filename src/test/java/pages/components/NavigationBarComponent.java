package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;

public class NavigationBarComponent extends BasePage {

    //menu items
    By moreMenuItemLocator = By.xpath("//span[starts-with(text(),'More')]");

    //subMenu items
    By careersSubMenuItemLocator = By.xpath("//h5[contains(text(),'Careers')]");

    //webElements
    WebElement moreMenuItem;
    WebElement careersSubMenuItem;

    //constructor
    public NavigationBarComponent(WebDriver driver){
        super(driver);
        this.moreMenuItem = driver.findElement(moreMenuItemLocator);
        this.careersSubMenuItem = driver.findElement(careersSubMenuItemLocator);
    }

    //behaviours
    public void navigateToCareersPage(){
        moreMenuItem.click();
        careersSubMenuItem.click();
    }



}

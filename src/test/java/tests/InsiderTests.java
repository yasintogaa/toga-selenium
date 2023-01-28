package tests;

import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import pages.*;
import org.testng.annotations.*;

import java.util.ArrayList;

public class InsiderTests extends BaseTest {

    //references of POMs
    HomePage homePage;
    CareersPage careersPage;
    CareersDetailPage careersDetailPage;
    OpenPositionsPage openPositionsPage;
    LeverPage leverPage;

    //tests
    @Test(description = "Visit Home Page and check some sections of it")
    void checkHomePage() throws InterruptedException {
        //POM initialization
        homePage = new HomePage(driver);

        //steps
        scrollToFooterOfPage();

        //soft assertions
        softAssertion.assertEquals(driver.getTitle(),homePage.getBrowserTitle());
        softAssertion.assertEquals(driver.getCurrentUrl(), homePage.getPageUrl());
        softAssertion.assertTrue(homePage.getElement(homePage.announceBarSelector).isDisplayed());
        softAssertion.assertTrue(homePage.getElement(homePage.navigationBarSelector).isDisplayed());
        softAssertion.assertTrue(homePage.getElement(homePage.mainHeadSectionSelector).isDisplayed());
        softAssertion.assertTrue(homePage.getElement(homePage.platformBrandsSectionSelector).isDisplayed());
        softAssertion.assertTrue(homePage.getElement(homePage.quotesSectionSelector).isDisplayed());
        softAssertion.assertTrue(homePage.getElement(homePage.footerSectionSelector).isDisplayed());

        //perform soft assertions
        softAssertion.assertAll();
    }

    @Test(description = "Visit Careers Page and check some sections of it")
    void checkCareersPage() throws InterruptedException {
        //POM initialization
        homePage = new HomePage(driver);
        careersPage = new CareersPage(driver);

        //steps
        homePage.navigationBarComponent.navigateToCareersPage();
        scrollToFooterOfPage();

        //soft assertions
        softAssertion.assertEquals(driver.getTitle(),careersPage.getBrowserTitle());
        softAssertion.assertEquals(driver.getCurrentUrl(), careersPage.getPageUrl());
        softAssertion.assertTrue(careersPage.getElement(careersPage.locationSectionSelector).isDisplayed());
        softAssertion.assertTrue(careersPage.getElement(careersPage.teamsSectionSelector).isDisplayed());
        softAssertion.assertTrue(careersPage.getElement(careersPage.lifeAtInsiderSectionSelector).isDisplayed());

        //perform soft assertions
        softAssertion.assertAll();
    }

    @Test(description = "Filter QA jobs from Istanbul")
    void filterQAJobResults() throws InterruptedException {
        //POM initialization
        homePage = new HomePage(driver);
        careersPage = new CareersPage(driver);
        careersDetailPage = new CareersDetailPage(driver,
                "Quality Assurance",
                "quality-assurance",
                "QA");
        openPositionsPage = new OpenPositionsPage(driver);

        //steps
        homePage.navigationBarComponent.navigateToCareersPage();
        careersPage.clickWithJS(careersPage.getElement(careersPage.seeAllTeamsButtonSelector));
        careersPage.navigateToHrefValue(careersPage.getTeamItemSelector("Quality Assurance"));
        careersDetailPage.navigateToHrefValue(careersDetailPage.seeAllJobsButtonSelector);
        openPositionsPage.selectLocationFilter("Istanbul, Turkey");

        //iteration for every job result item
        for(WebElement resultItem: openPositionsPage.getResultItems()) {
            //it should be scrolled to element for apply button appear
            scrollToElement(resultItem);

            //soft assertions
            softAssertion
                    .assertTrue(openPositionsPage
                            .getElementText(resultItem,openPositionsPage.positionTitleSelector)
                            .contains("Quality Assurance"));
            softAssertion
                    .assertTrue(openPositionsPage
                            .getElementText(resultItem,openPositionsPage.positionDepartmentSelector)
                            .contains("Quality Assurance"));
            softAssertion
                    .assertTrue(openPositionsPage
                            .getElementText(resultItem,openPositionsPage.positionLocationSelector)
                            .contains("Istanbul, Turkey"));
            softAssertion.
                    assertTrue(openPositionsPage
                            .getElement(openPositionsPage.applyButtonSelector)
                            .isEnabled());
        }
        //perform soft assertions
        softAssertion.assertAll("SoftAssertions performed.");
    }

    @Test(description = "Apply all QA jobs from Istanbul and check lever application forms")
    void checkQAJobsApplyForm() throws InterruptedException {
        //POM initialization
        homePage = new HomePage(driver);
        careersPage = new CareersPage(driver);
        careersDetailPage = new CareersDetailPage(driver,
                "Quality Assurance",
                "quality-assurance",
                "QA");
        leverPage = new LeverPage(driver);
        openPositionsPage = new OpenPositionsPage(driver);


        //steps
        homePage.navigationBarComponent.navigateToCareersPage();
        careersPage.clickWithJS(careersPage.getElement(careersPage.seeAllTeamsButtonSelector));
        careersPage.navigateToHrefValue(careersPage.getTeamItemSelector("Quality Assurance"));
        careersDetailPage.navigateToHrefValue(careersDetailPage.seeAllJobsButtonSelector);
        openPositionsPage.selectLocationFilter("Istanbul, Turkey");

        //browser tab index
        int index = 0;

        //iteration for every job result item
        for(WebElement resultItem: openPositionsPage.getResultItems()) {
            index++;
            //it should be scrolled to element for apply button appear
            scrollToElement(resultItem);

            String jobTitle = openPositionsPage
                            .getElementText(resultItem,openPositionsPage.positionTitleSelector);

            openPositionsPage.applyJob(resultItem);

            //switch to new tab
            ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
            driver.switchTo().window(tabs.get(index));

            //soft assertion
            softAssertion.assertEquals(leverPage.getJobTitleText(),jobTitle);
            softAssertion.assertTrue(driver.getCurrentUrl().contains(leverPage.getPageUrl()));

            //switch to first tab for next iteration
            driver.switchTo().window(tabs.get(0));
        }
        //perform soft assertions
        softAssertion.assertAll("SoftAssertions performed.");
    }
}
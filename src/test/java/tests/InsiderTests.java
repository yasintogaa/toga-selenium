package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import utils.DriverManager;

public class InsiderTests extends DriverManager {

    @Parameters("browser")
    @BeforeClass
    void setup(@Optional("browser") String browser){setDriver(browser);}

    @AfterClass
    void teardown(){driver.quit();}

    @Test
    void firstTestMethod() throws InterruptedException {
        driver.get(baseURL);
        Thread.sleep(5000);
        Assert.assertTrue(true);
    }
}

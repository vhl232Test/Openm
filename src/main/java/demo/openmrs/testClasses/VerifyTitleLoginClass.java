package demo.openmrs.testClasses;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import demo.openmrs.utils.Util;
import demo.openmrs.pageObjectClasses.LoginPage;

import java.util.Properties;

public class VerifyTitleLoginClass {

    String chrome = "Chrome";
    String fireFox = "FireFox";
    String opera = "Opera";
    WebDriver driver;
    Util util = new Util();
    LoginPage verifyLoginPageTitle;
    Properties properties;

    @BeforeClass
    public void setProprty(){
           driver =  util.setProp(chrome);
           driver.get("https://demo.openmrs.org/openmrs/login.htm");
           verifyLoginPageTitle = new LoginPage(driver);
    }

    @Test
    public void verifyTitle(){
        String titleForTest = driver.getTitle();
        Assert.assertEquals(verifyLoginPageTitle.getTitleLoginPage(),titleForTest);
    }
    @AfterClass
    public void closeBrowser(){
        driver.close();
    }
}


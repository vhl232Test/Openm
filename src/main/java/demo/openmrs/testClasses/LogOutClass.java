package demo.openmrs.testClasses;

import demo.openmrs.pageObjectClasses.HomePage;
import demo.openmrs.pageObjectClasses.LoginPage;
import demo.openmrs.utils.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LogOutClass {

    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    Properties properties;

    // "FireFox"; "Opera";
    String chrome = "Chrome";
    Util util = new Util();

    @BeforeClass
    public void setProp(){
        driver = util.setProp(chrome);
        driver.get(util.getUrl());
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);

    }

    @Test
    public void logOutTest(){
        try {
            //fileReader = new FileReader("DataForTest.properties");
            properties = new Properties();
            //properties.load(fileReader);
            //получение данных из проперти, замена пути к файлу данной конструкцией;
            properties.load(getClass().getClassLoader().getResourceAsStream("DataForTest.properties"));
            String name  = properties.getProperty("user_name");
            String password = properties.getProperty("user_password");

            //нахождение элиментов полей ввода user, password, login button;
            WebElement userNameField = loginPage.getUserNameForm();
            WebElement userPasswordField = loginPage.getUserPassword();
            WebElement loginButton = loginPage.getLoginButton();
            WebElement inpatient_Ward = loginPage.getInpatientWard();
            //ввод данных в поля имя и пароля;
            //для входа пользователя необходимо ввести учетные данные и
            //нажать в списке "Inpatient Ward";
            userNameField.sendKeys(name);
            userPasswordField.sendKeys(password);
            inpatient_Ward.click();
            loginButton.click();

            //проверка входа пользователя в систему;
            String titleHomePage = driver.getTitle();
            Assert.assertEquals(homePage.homeTitle,titleHomePage);

            homePage.getLogOutButton().click();

            //проверка что пользователь вышел из системы;
            //пользователь должен быть на странице входа;
            Assert.assertEquals(loginPage.getTitleLoginPage(),driver.getTitle());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @AfterClass
    public void closeBrowser(){
        util.close_Browser();
    }



}

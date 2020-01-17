package demo.openmrs.testClasses;

import demo.openmrs.pageObjectClasses.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import demo.openmrs.utils.Util;
import demo.openmrs.pageObjectClasses.LoginPage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SignInClass {

    Util util = new Util();
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    Properties properties;
    //FileReader fileReader;

    //устновка драйвера и проперти
    @BeforeClass
    public void setProper(){
        driver = util.setProp("Chrome");
        driver.get("https://demo.openmrs.org/openmrs/login.htm");
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    //чтение данных из проперти и авторизация на сайт
    @Test
    public void signIn(){
        try {
            //fileReader = new FileReader("DataForTest.properties");
            properties = new Properties();
            //properties.load(fileReader);
            //получение данных из проперти, замена пути к файлу данной конструкцией
            properties.load(getClass().getClassLoader().getResourceAsStream("DataForTest.properties"));
            String name  = properties.getProperty("user_name");
            String password = properties.getProperty("user_password");

            //нахождение элиментов полей ввода user, password, login button;
            WebElement userNameField = loginPage.getUserNameForm();
            WebElement userPasswordField = loginPage.getUserPassword();
            WebElement loginButton = loginPage.getLoginButton();
            WebElement inpatient_Ward = loginPage.getInpatientWard();
            //ввод данных в поля имя и пароля
            //для входа пользователя необходимо ввести учетные данные и
            //нажать в списке "Inpatient Ward"
            userNameField.sendKeys(name);
            userPasswordField.sendKeys(password);
            inpatient_Ward.click();
            loginButton.click();

            //проверка входа пользователя в систему;
            String userStatus = driver.findElement(By.xpath("//*[@id='home-container']/h4")).getText().trim();
            String titleHomePage = driver.getTitle();
            Assert.assertEquals(homePage.homeTitle,titleHomePage);
            Assert.assertEquals(homePage.users_Status_Message,userStatus);

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

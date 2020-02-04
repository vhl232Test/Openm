package demo.openmrs.testClasses;

import demo.openmrs.pageObjectClasses.HomePage;
import demo.openmrs.pageObjectClasses.LoginPage;
import demo.openmrs.pageObjectClasses.PatientDataPage;
import demo.openmrs.pageObjectClasses.RegisterPatientPage;
import demo.openmrs.utils.Util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class RegisterPatientClass {
    WebDriver driver;
    Properties properties;
    LoginPage loginPage;
    HomePage homePage;
    RegisterPatientPage registerPatientPage;
    Util util = new Util();
    // "FireFox"; "Opera";
    String chrome = "Chrome";
    PatientDataPage patientDataPage;
    FileWriter fileWriter;


    @BeforeClass
    public void setProp(){
        driver = util.setProp(chrome);
        driver.get(util.getUrl());
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        registerPatientPage = new RegisterPatientPage(driver);
        patientDataPage = new PatientDataPage(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test(priority = 0)
    public void logIn(){
        try {
            properties = new Properties();
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
            String titleHomePage = driver.getTitle();
            Assert.assertEquals(homePage.homeTitle,titleHomePage);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 1,dependsOnMethods = "logIn")
    public void goRegisterPage(){
        //проверка что пользователь на странице регистрации пациента;
        homePage.getRegisterPatient().click();
        String regisPat = registerPatientPage.getRegPatientHeader().getText().trim();
        Assert.assertEquals(registerPatientPage.getRegisterPatient_H2(),regisPat);

    }
    @Test(priority = 2,dependsOnMethods = "goRegisterPage")
    public void registerPatient(){
        //данные из проперти;
        String givName = properties.getProperty("givenName");
        String midName = properties.getProperty("middleName");
        String famName = properties.getProperty("familyName");

        //ввод данных пациента;
        registerPatientPage.getGivenName().sendKeys(givName);
        registerPatientPage.getMiddleName().sendKeys(midName);
        registerPatientPage.getFamilyName().sendKeys(famName);

        //выбор пола
        registerPatientPage.getGenderChoice().click();
        Select selectGender = new Select(registerPatientPage.getGender_select());
        selectGender.selectByValue("M");

        //ввод даты рождения
        registerPatientPage.getBirthdateLabel().click();
        String dayBirth = properties.getProperty("birthDate");
        registerPatientPage.getBirthdateDay().sendKeys(dayBirth);
        Select slectMonth = new Select(registerPatientPage.getSelectMonthBirthday());
        slectMonth.selectByVisibleText("January");
        String birthdate_Year = properties.getProperty("birthdateYear");
        registerPatientPage.getBirthdateYear().sendKeys(birthdate_Year);

        //ввод адреса
        registerPatientPage.getContactInfo_Adress().click();
        String adress_1 = properties.getProperty("adress");
        registerPatientPage.getAdress1Field().sendKeys(adress_1);
        //ввод телефона
        registerPatientPage.getContactInfo_PhoneNumber().click();
        String numberPhone = properties.getProperty("phoneNumber");
        registerPatientPage.getPhoneNumber_Field().sendKeys(numberPhone);
        //выбор родственника
        registerPatientPage.getChoice_Raletion().click();
        Select selectRaletion = new Select(registerPatientPage.getSelect_Raletion());
        selectRaletion.selectByVisibleText("Parent");
        //подтверждение регистрации
        registerPatientPage.getConfirmation_label().click();
        registerPatientPage.getConfirmRegistration().click();

        //запись ID пацента  в файл
        String s = patientDataPage.getIdPatient().getText();
        System.out.println(s);

        try {
            fileWriter = new FileWriter("src/main/resources/IdPatient",false);
            fileWriter.write(s);
            fileWriter.flush();
            fileWriter.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @AfterClass
    void closeBrowser(){
        //util.close_Browser();
    }


}

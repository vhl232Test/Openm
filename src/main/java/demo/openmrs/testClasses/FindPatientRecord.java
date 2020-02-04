package demo.openmrs.testClasses;

import demo.openmrs.pageObjectClasses.FindRecordPatientPage;
import demo.openmrs.pageObjectClasses.HomePage;
import demo.openmrs.pageObjectClasses.LoginPage;
import demo.openmrs.utils.Util;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Properties;

//Нахождение записи пациента.
public class FindPatientRecord {
    WebDriver driver;
    Util util = new Util();
    LoginPage loginPage;
    Properties properties = new Properties();
    FileInputStream fileInputStream;
    HomePage homePage;
    FindRecordPatientPage findRecordPatientPage;
    FileReader fileReader;
    {

    }

    // установка драйвера
    @BeforeClass
    public void setProp(){

        driver = util.setProp("Chrome");
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        findRecordPatientPage = new FindRecordPatientPage(driver);
        driver.get(util.getUrl());

    }

    //авторизация админа
    @Test
    public void avutorisAdmin(){
        try {
            fileInputStream = new FileInputStream("src/main/resources/DataForTest.properties");
            properties.load(fileInputStream);

            loginPage.getUserNameForm().sendKeys(properties.getProperty("user_name"));
            loginPage.getUserPassword().sendKeys(properties.getProperty("user_password"));
            loginPage.getInpatientWard().click();
            loginPage.getLoginButton().click();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //поиск записи пациента;
    //считывание с файла id и ввод в поле поиска;
    @Test
    public void findPatientRecord(){
        try {
            Reader reader = new FileReader("src/main/resources/IdPatient");
            BufferedReader bufferedReader = new BufferedReader(reader);
            while (bufferedReader.ready()){
                String idPatient = bufferedReader.readLine();
                homePage.getFindPatientRecord().click();
                findRecordPatientPage.getFindPatientRecordInputField().sendKeys(idPatient);
                findRecordPatientPage.getFindPatientRecordInputField().submit();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

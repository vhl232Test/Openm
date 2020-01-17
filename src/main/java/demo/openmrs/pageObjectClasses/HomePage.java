package demo.openmrs.pageObjectClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    public String homeTitle = "Home";
    public  String users_Status_Message = "Logged in as Super User () at Inpatient Ward.";

    WebDriver driver;

    @FindBy(xpath = "//*[@class='logout']/a")
    WebElement logOutButton;
    @FindBy(xpath = "//a[contains(@id,'referenceapplication-registrationapp')]")
    WebElement registerPatient;

    public WebElement getRegisterPatient() {
        return registerPatient;
    }

    public WebElement getLogOutButton() {
        return logOutButton;
    }

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }


}

package demo.openmrs.pageObjectClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PatientDataPage {
    WebDriver driver;

    @FindBy(xpath = //"//div[@class ='identifiers']/span"
    "//*[@id=\"content\"]/div[6]/div[2]/span"
    )

    WebElement idPatient;



    public WebElement getIdPatient() {
        return idPatient;
    }

    public PatientDataPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
}

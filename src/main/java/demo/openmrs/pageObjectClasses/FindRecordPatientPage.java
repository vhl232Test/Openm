package demo.openmrs.pageObjectClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FindRecordPatientPage {

    WebDriver driver;

    @FindBy(css = "input#patient-search")
    WebElement findPatientRecordInputField;

    public WebElement getFindPatientRecordInputField() {
        return findPatientRecordInputField;
    }

    public FindRecordPatientPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
}

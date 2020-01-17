package demo.openmrs.pageObjectClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPatientPage {
    WebDriver driver;

    String registerPatient_H2 = "Register a patient";

    @FindBy(xpath = "//div[@id='content']/h2")
    WebElement regPatientHeader;
    @FindBy(xpath = "//input[@name='givenName']")
    WebElement givenName;
    @FindBy(xpath = "//input[@name='middleName']")
    WebElement middleName;
    @FindBy(xpath = "//span[@id='genderLabel']")
    WebElement genderChoice;
    @FindBy(xpath = "//select[@id='gender-field']")
    WebElement gender_select;
    @FindBy(xpath = "//span[@id='birthdateLabel']")
    WebElement birthdateLabel;
    @FindBy(css = "input#birthdateDay-field")
    WebElement birthdateDay;
    @FindBy(css = "select#birthdateMonth-field")
    WebElement selectMonthBirthday;
    @FindBy(css = "input#birthdateYear-field")
    WebElement birthdateYear;
    @FindBy(xpath = "//span[text()='Address']")
    WebElement contactInfo_Adress;
    @FindBy(css = "input#address1")
    WebElement adress1Field;
    @FindBy(xpath = "//span[text()='Phone Number']")
    WebElement contactInfo_PhoneNumber;
    @FindBy(xpath = "//input[@name = 'phoneNumber']")
    WebElement phoneNumber_Field;
    @FindBy(xpath = "//span[text()='Relatives']")
    WebElement choice_Raletion;
    @FindBy(css = "select#relationship_type")
    WebElement select_Raletion;
    @FindBy(css = "span#confirmation_label")
    WebElement confirmation_label;

    public WebElement getConfirmation_label() {
        return confirmation_label;
    }

    @FindBy(css = "input#submit")
    WebElement confirmRegistration;


    public WebElement getChoice_Raletion() {
        return choice_Raletion;
    }
    public WebElement getConfirmRegistration() {
        return confirmRegistration;
    }

    public WebElement getSelect_Raletion() {
        return select_Raletion;
    }

    public WebElement getPhoneNumber_Field() {
        return phoneNumber_Field;
    }

    public WebElement getContactInfo_PhoneNumber() {
        return contactInfo_PhoneNumber;
    }

    public WebElement getAdress1Field() {
        return adress1Field;
    }

    public WebElement getContactInfo_Adress() {
        return contactInfo_Adress;
    }

    public WebElement getBirthdateYear() {
        return birthdateYear;
    }

    public WebElement getSelectMonthBirthday() {
        return selectMonthBirthday;
    }

    public WebElement getBirthdateDay() {
        return birthdateDay;
    }

    public WebElement getBirthdateLabel() {
        return birthdateLabel;
    }



    public WebElement getGenderChoice() {
        return genderChoice;
    }

    public WebElement getGender_select() {
        return gender_select;
    }

    public WebElement getGivenName() {
        return givenName;
    }

    public WebElement getMiddleName() {
        return middleName;
    }

    public WebElement getFamilyName() {
        return familyName;
    }

    @FindBy(xpath = "//input[@name='familyName']")
    WebElement familyName;



    public String getRegisterPatient_H2() {
        return registerPatient_H2;
    }

    public WebElement getRegPatientHeader() {
        return regPatientHeader;
    }



    public RegisterPatientPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
}

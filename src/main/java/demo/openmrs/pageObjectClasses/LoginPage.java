package demo.openmrs.pageObjectClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    @FindBy(css = "input#username")
    WebElement userNameForm;
    @FindBy(css = "input#password")
    WebElement userPassword;
    @FindBy(css = "input.confirm")
    WebElement loginButton;
    @FindBy(xpath = "//ul[@id='sessionLocation']/li")
    WebElement inpatientWard;

    public WebElement getInpatientWard() {
        return inpatientWard;
    }



    public WebElement getLoginButton() {
        return loginButton;
    }

    String titleLoginPage = "Login";

    public String getTitleLoginPage() {
        return titleLoginPage;
    }

    public WebElement getUserNameForm() {
        return userNameForm;
    }

    public WebElement getUserPassword() {
        return userPassword;
    }

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
}

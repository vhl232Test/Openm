package demo.openmrs.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

public class Util {
    WebDriver driver;
    String url = "https://demo.openmrs.org/openmrs/";

    public String getUrl() {
        return url;
    }

    // установка Property и драйвера
    public  WebDriver  setProp(String browserName) {
        switch (browserName) {
            case "Chrome":
                System.setProperty("webdriver.chrome.driver", "F:\\selenium drivera\\ChromeDriver\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "FireFox":
                System.setProperty("webdriver.gecko.driver", "F:\\selenium drivera\\Mozilla GeckoDriver\\geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case "Opera":
                System.setProperty("webdriver.opera.driver", "F:\\selenium drivera\\Opera\\operadriver.exe");
                driver = new OperaDriver();
        }
        return driver;

    }

    public  void close_Browser(){
        driver.close();
    }
}

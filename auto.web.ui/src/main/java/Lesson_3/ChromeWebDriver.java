package Lesson_3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class ChromeWebDriver {
    private WebDriver driver;

    public ChromeWebDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");

        if (driver == null){
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }
    }

    public WebDriver getDriver(){
        return driver;
    }

    public void setSizeWindow(int width, int height){
        driver.manage().window().setSize(new Dimension(width, height));
    }

    public void quit(){
        if (driver != null) {
            driver.quit();
        }
    }
}

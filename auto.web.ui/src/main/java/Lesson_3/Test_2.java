package Lesson_3;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Random;

public class Test_2 {
    //Создание контактного лица

    private static final String PAGE_URL = "https://crm.geekbrains.space/user/login";
    private static final String LOGIN = "Applanatest";
    private static final String PASS = "Student2020!";

    public static void main(String[] args) {
        ChromeWebDriver driver = new ChromeWebDriver();
        WebDriverWait driverWait = new WebDriverWait(driver.getDriver(), 10);
        driver.setSizeWindow(1600, 900);
        driver.getDriver().get(PAGE_URL);

        try{
            //вводим логин
            WebElement login = driver.getDriver().findElement(By.id("prependedInput"));
            login.sendKeys(LOGIN);
            //вводим пароль
            WebElement pass = driver.getDriver().findElement(By.id("prependedInput2"));
            pass.sendKeys(PASS);
            //нажимаем "Войти"
            WebElement buttonIn = driver.getDriver().findElement(By.id("_submit"));
            buttonIn.click();
            //нажимаем в меню "Контактные лица"
            WebElement contragents = driver.getDriver().findElement(By.xpath("//*[@id=\"main-menu\"]/ul/li[1]/a/span"));
            driverWait.until(ExpectedConditions.visibilityOf(contragents));
            contragents.click();
            WebElement contactPersons = driver.getDriver().findElement(By.xpath("//*[@id=\"main-menu\"]/ul/li[1]/ul/li[4]/a/span"));
            contactPersons.click();
            //нажимаем кнопку "Создать контактное лицо"
            WebElement contactCreate = driver.getDriver().findElement(By.xpath("//a[@title='Создать контактное лицо']"));
            contactCreate.click();
            //вводим фамилию
            WebElement lastName = driver.getDriver().findElement(By.name("crm_contact[lastName]"));
            lastName.sendKeys("test_lastName_auto_web_ui");
            //вводим имя
            WebElement firstName = driver.getDriver().findElement(By.name("crm_contact[firstName]"));
            firstName.sendKeys("test_firstName_auto_web_ui");
            //выбираем организацию
            WebElement organizationLink = driver.getDriver().findElement(By.xpath("//div[contains(@id, 's2id_crm_contact_company')]/a"));
            organizationLink.click();
            WebElement organizationInputSearch = driver.getDriver().findElement(By.xpath("//*[@id=\"select2-drop\"]/div/input"));
            organizationInputSearch.sendKeys("1234");
            driverWait.until(ExpectedConditions.visibilityOf(driver.getDriver().findElement(By.xpath("//*[@id=\"select2-drop\"]/ul[2]/li/div/span"))));
            organizationInputSearch.sendKeys(Keys.ENTER);
            //вводим должность
            WebElement jobTitle = driver.getDriver().findElement(By.name("crm_contact[jobTitle]"));
            jobTitle.sendKeys("тестовая должность");
            //нажимаем кнопку "сохранить и закрыть"
            WebElement buttonSave = driver.getDriver().findElement(By.xpath("//button[contains(., 'Сохранить и закрыть')]"));
            buttonSave.click();
            System.out.println("Кнопка \"Сохранить\" нажата");
            //проверяем, что контакт создан
            driverWait.until(ExpectedConditions.visibilityOf(driver.getDriver().findElement(By.xpath("//h1[@class='oro-subtitle']"))));
            System.out.println("Тест успешен!");
        } catch (Exception e){
            System.out.println(e.getMessage());
            driver.quit();
        }
    }
}

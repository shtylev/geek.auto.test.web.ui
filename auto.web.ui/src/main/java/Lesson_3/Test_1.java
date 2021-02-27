package Lesson_3;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Random;

public class Test_1 {
    //Создание нового проекта

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
            //нажимаем в меню "Мои проекты"
            WebElement projects = driver.getDriver().findElement(By.xpath("//*[@id=\"main-menu\"]/ul/li[3]/a/span"));
            driverWait.until(ExpectedConditions.visibilityOf(projects));
            projects.click();
            WebElement myProject = driver.getDriver().findElement(By.xpath("//*[@id=\"main-menu\"]/ul/li[3]/ul/li[4]/a/span"));
            myProject.click();
            //нажимаем "Создать проект"
            WebElement createProject = driver.getDriver().findElement(By.xpath("//a[@title='Создать проект']"));
            createProject.click();
            //вводим наименование
            WebElement nameProject = driver.getDriver().findElement(By.name("crm_project[name]"));
            String nameProj = "project_sa_auto_web_ui_" + new Random().nextInt(100);
            nameProject.sendKeys(nameProj);
            //выбираем организацию
            WebElement organizationLink = driver.getDriver().findElement(By.xpath("//div[contains(@id, 's2id_crm_project_company')]/a"));
            organizationLink.click();
            WebElement organizationInputSearch = driver.getDriver().findElement(By.xpath("//div[6]/div/input"));
            organizationInputSearch.sendKeys("1234");
            driverWait.until(ExpectedConditions.visibilityOf(driver.getDriver().findElement(By.xpath("//*[@id=\"select2-drop\"]/ul[2]/li/div/span"))));
            organizationInputSearch.sendKeys(Keys.ENTER);
            //выбираем контактное лицо
            Thread.sleep(2000);
            WebElement contactPersonLink = driver.getDriver().findElement(By.xpath("//div[contains(@id, 's2id_crm_project_contactMain')]/a"));
            contactPersonLink.click();
            WebElement contactPersonInputSearch = driver.getDriver().findElement(By.xpath("//*[@id=\"select2-drop\"]/div/input"));
            contactPersonInputSearch.sendKeys("Familiy Imy");
            driverWait.until(ExpectedConditions.visibilityOf(driver.getDriver().findElement(By.xpath("//*[@id=\"select2-drop\"]/ul[2]/li/div/span"))));
            contactPersonInputSearch.sendKeys(Keys.ENTER);
            //выбираем подразделение
            Select businessUnit = new Select(driver.getDriver().findElement(By.name("crm_project[businessUnit]")));
            businessUnit.selectByValue("1");
            //выбираем куратора проекта
            Select curator = new Select(driver.getDriver().findElement(By.name("crm_project[curator]")));
            curator.selectByValue("5");
            //выбираем руководителя проекта
            Select rp = new Select(driver.getDriver().findElement(By.name("crm_project[rp]")));
            rp.selectByValue("5");
            //выбираем администратора
            Select administrator = new Select(driver.getDriver().findElement(By.name("crm_project[administrator]")));
            administrator.selectByValue("6");
            //выбираем менеджера
            Select manager = new Select(driver.getDriver().findElement(By.name("crm_project[manager]")));
            manager.selectByValue("5");
            //нажимаем кнопку "сохранить и закрыть"
            WebElement buttonSave = driver.getDriver().findElement(By.xpath("//button[contains(., 'Сохранить и закрыть')]"));
            buttonSave.click();
            System.out.println("Кнопка \"Сохранить\" нажата");
            //проверяем, что проект создан
            driverWait.until(ExpectedConditions.visibilityOf(driver.getDriver().findElement(By.xpath("//h1[@class='oro-subtitle']"))));
            System.out.println("Тест успешен!");

        } catch (Exception e){
            System.out.println(e.getMessage());
            driver.quit();
        }
    }
}

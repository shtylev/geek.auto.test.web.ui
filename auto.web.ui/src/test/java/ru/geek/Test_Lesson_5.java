package ru.geek;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Test_Lesson_5 {

    private WebDriver driver;
    private WebDriverWait driverWait;
    private static final String PAGE_URL = "https://crm.geekbrains.space/user/login";
    private static final String LOGIN = "Applanatest";
    private static final String PASS = "Student2020!";

    @BeforeAll
    void setUp() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUpTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");

        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.manage().window().setSize(new Dimension(1600, 900));
        }
        driver.get(PAGE_URL);

        driverWait = new WebDriverWait(driver, 10);
    }

    @AfterEach
    void tearDownTest() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void login() {
        //вводим логин
        WebElement login = driver.findElement(By.id("prependedInput"));
        login.sendKeys(LOGIN);
        //вводим пароль
        WebElement pass = driver.findElement(By.id("prependedInput2"));
        pass.sendKeys(PASS);
        //нажимаем "Войти"
        WebElement buttonIn = driver.findElement(By.id("_submit"));
        buttonIn.click();
        Assertions.assertTrue(driver.findElement(By.xpath("//div[@id='main-menu']")).isDisplayed());
    }

    @Test
    public void createProjectTest() {
        try {
            login();
            //нажимаем в меню "Мои проекты"
            WebElement projects = driver.findElement(By.xpath("//span[@class='title' and text()='Проекты']"));
            driverWait.until(ExpectedConditions.visibilityOf(projects));
            projects.click();
            WebElement myProject = driver.findElement(By.xpath("//span[text()='Мои проекты']"));
            myProject.click();
            //нажимаем "Создать проект"
            WebElement createProject = driver.findElement(By.xpath("//a[@title='Создать проект']"));
            createProject.click();
            //вводим наименование
            WebElement nameProject = driver.findElement(By.name("crm_project[name]"));
            String nameProj = "project_sa_auto_web_ui_" + new Random().nextInt(100);
            nameProject.sendKeys(nameProj);
            //выбираем организацию
            WebElement organizationLink = driver.findElement(By.xpath("//div[contains(@id, 's2id_crm_project_company')]/a"));
            organizationLink.click();
            WebElement organizationInputSearch = driver.findElement(By.xpath("//div[6]/div/input"));
            organizationInputSearch.sendKeys("1234");
            driverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/ul[2]/li/div/span"))));
            organizationInputSearch.sendKeys(Keys.ENTER);
            //выбираем контактное лицо
            Thread.sleep(2000);
            WebElement contactPersonLink = driver.findElement(By.xpath("//div[contains(@id, 's2id_crm_project_contactMain')]/a"));
            contactPersonLink.click();
            WebElement contactPersonInputSearch = driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/div/input"));
            contactPersonInputSearch.sendKeys("Familiy Imy");
            driverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/ul[2]/li/div/span"))));
            contactPersonInputSearch.sendKeys(Keys.ENTER);
            //выбираем подразделение
            Select businessUnit = new Select(driver.findElement(By.name("crm_project[businessUnit]")));
            businessUnit.selectByValue("1");
            //выбираем куратора проекта
            Select curator = new Select(driver.findElement(By.name("crm_project[curator]")));
            curator.selectByValue("5");
            //выбираем руководителя проекта
            Select rp = new Select(driver.findElement(By.name("crm_project[rp]")));
            rp.selectByValue("5");
            //выбираем администратора
            Select administrator = new Select(driver.findElement(By.name("crm_project[administrator]")));
            administrator.selectByValue("6");
            //выбираем менеджера
            Select manager = new Select(driver.findElement(By.name("crm_project[manager]")));
            manager.selectByValue("5");
            //нажимаем кнопку "сохранить и закрыть"
            WebElement buttonSave = driver.findElement(By.xpath("//button[contains(., 'Сохранить и закрыть')]"));
            buttonSave.click();
            System.out.println("Кнопка \"Сохранить\" нажата");
            //проверяем, что проект создан
            Assertions.assertTrue(driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).isDisplayed());
            //driverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1[@class='oro-subtitle']"))));
            System.out.println("Тест успешен!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void createContactPerson() {
        try {
            login();
            //нажимаем в меню "Контактные лица"
            WebElement contragents = driver.findElement(By.xpath("//span[@class='title' and text()='Контрагенты']"));
            driverWait.until(ExpectedConditions.visibilityOf(contragents));
            contragents.click();
            WebElement contactPersons = driver.findElement(By.xpath("//span[text()='Контактные лица']"));
            contactPersons.click();
            //нажимаем кнопку "Создать контактное лицо"
            WebElement contactCreate = driver.findElement(By.xpath("//a[@title='Создать контактное лицо']"));
            contactCreate.click();
            //вводим фамилию
            WebElement lastName = driver.findElement(By.name("crm_contact[lastName]"));
            lastName.sendKeys("test_lastName_auto_web_ui");
            //вводим имя
            WebElement firstName = driver.findElement(By.name("crm_contact[firstName]"));
            firstName.sendKeys("test_firstName_auto_web_ui");
            //выбираем организацию
            WebElement organizationLink = driver.findElement(By.xpath("//div[contains(@id, 's2id_crm_contact_company')]/a"));
            organizationLink.click();
            WebElement organizationInputSearch = driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/div/input"));
            organizationInputSearch.sendKeys("1234");
            driverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/ul[2]/li/div/span"))));
            organizationInputSearch.sendKeys(Keys.ENTER);
            //вводим должность
            WebElement jobTitle = driver.findElement(By.name("crm_contact[jobTitle]"));
            jobTitle.sendKeys("тестовая должность");
            //нажимаем кнопку "сохранить и закрыть"
            WebElement buttonSave = driver.findElement(By.xpath("//button[contains(., 'Сохранить и закрыть')]"));
            buttonSave.click();
            System.out.println("Кнопка \"Сохранить\" нажата");
            //проверяем, что контакт создан
            Assertions.assertTrue(driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).isDisplayed());
            //driverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1[@class='oro-subtitle']"))));
            System.out.println("Тест успешен!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void dragAndDrop() {
        try {
            login();
            //на момент написания теста эта панель была пустая
            Select manager = new Select(driver.findElement(By.id("dashboard_selector")));
            manager.selectByValue("4");

            ArrayList<WebElement> iconsMoveColumn0 = (ArrayList<WebElement>) driver.
                    findElements(By.xpath("//div[@id='dashboard-column-0']//i[contains(@class, 'icon-move')]"));
            ArrayList<WebElement> iconsMoveColumn1 = (ArrayList<WebElement>) driver.
                    findElements(By.xpath("//div[@id='dashboard-column-1']//i[contains(@class, 'icon-move')]"));
            //WebElement iconMoveColumn1 = driver.findElement(By.xpath("//div[@id='dashboard-column-1']//i[contains(@class, 'icon-move')]"));
            WebElement dashboardColumn0 = driver.findElement(By.xpath("//div[@id='dashboard-column-0']"));
            WebElement dashboardColumn1 = driver.findElement(By.xpath("//div[@id='dashboard-column-1']"));

            //какой-то из dashboard может быть "пустым", поэтому сделана проверка на наличие иконки перетаскивания, чтобы тягать виджет с одного места на другое
            if (!iconsMoveColumn0.isEmpty()) {
                new Actions(driver)
                        .click(iconsMoveColumn0.get(0))
                        .dragAndDrop(iconsMoveColumn0.get(0), dashboardColumn1)
                        .release(iconsMoveColumn0.get(0))
                        .build()
                        .perform();
            } else if (!iconsMoveColumn1.isEmpty()) {
                new Actions(driver)
                        .click(iconsMoveColumn1.get(0))
                        .dragAndDrop(iconsMoveColumn1.get(0), dashboardColumn0)
                        .release(iconsMoveColumn1.get(0))
                        .build()
                        .perform();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testFilter(){
        try{
            login();
            //нажимаем в меню "Организации"
            WebElement contragents = driver.findElement(By.xpath("//span[@class='title' and text()='Контрагенты']"));
            driverWait.until(ExpectedConditions.visibilityOf(contragents));
            contragents.click();
            WebElement organizations = driver.findElement(By.xpath("//span[text()='Организации']"));
            organizations.click();
            //ищем по названию, нажимаем на выпадающий список "Название"
            WebElement title = driver.findElement(By.xpath("//span[@class='filter-items']/div[1]"));
            title.click();
            //вводим текст в поле фильтра
            WebElement value = driver.findElement(By.xpath("//span[@class='filter-items']/div[1]//input[@name='value']"));
            value.sendKeys("123");
            //нажимаем кнопку "Обновить"
            WebElement btnUpdate = driver.findElement(By.xpath("//span[@class='filter-items']/div[1]//button[text()='Обновить']"));
            btnUpdate.click();
            //проверка на не пустую таблицу
            ArrayList<WebElement> valueTable = (ArrayList<WebElement>) driver.
                    findElements(By.xpath("//tr/td[1][contains(text(), '123')]"));
            Assertions.assertFalse(valueTable.isEmpty());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}

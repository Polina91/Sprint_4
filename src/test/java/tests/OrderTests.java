package tests;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pageobject.MainPage;
import org.pageobject.OrderPage;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

@RunWith(Parameterized.class)
public class OrderTests {

    private WebDriver driver;

    private final String name;
    private final String lastName;
    private final String address;
    private final String phone;

    public OrderTests(String name, String lastName, String address, String phone) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {"Иван", "Иванов", "ул. Ленина, 1", "+79998887766"},
                {"Мария", "Петрова", "ул. Гагарина, 5", "+79995554433"}
        };
    }

    @Before
    public void setUp() {
        // Тут не срабатывает клик по approveButton для финального подтверждения заказа и смены текста модалки
        driver = new ChromeDriver();
        // В Firefox все тесты с оформлением заказа проходят
        //driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void orderScooterFromTopButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickTopOrderButton();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillOrderForm(name, lastName, address, phone);

        // Обрабатываем модальное окно
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Order_ModalHeader__3FDaJ")));
        String modalText = modal.getText();
        assertThat(modalText, containsString("Заказ оформлен"));
    }

    @Test
    public void orderScooterFromMidButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickMidOrderButton();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillOrderForm(name, lastName, address, phone);

        // Обрабатываем модальное окно
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Order_ModalHeader__3FDaJ")));
        String modalText = modal.getText();
        assertThat(modalText, containsString("Заказ оформлен"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

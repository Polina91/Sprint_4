package tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public abstract class BaseTest {
    protected WebDriver driver = new ChromeDriver();
//    protected WebDriver driver = new FirefoxDriver();
    protected String testUrl = "https://qa-scooter.praktikum-services.ru/";

    @Before
    public void setUp() {
        driver.get(testUrl);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
package org.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private final WebDriver driver;

    // Локаторы формы
    private final By nameField = By.xpath("//input[@placeholder='* Имя']");
    private final By lastNameField = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroField = By.xpath("//input[@placeholder='* Станция метро']"); // или уточни
    private final By stationField = By.xpath("//button[@value='1']");
    private final By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath("//button[text()='Далее']");
    private final By cookieButton = By.id("rcc-confirm-button");
    private final By arrivalField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By dateField = By.xpath("//div[@aria-label='Choose вторник, 1-е апреля 2025 г.']");
    private final By rentField = By.xpath("//div[@class='Dropdown-placeholder']");
    private final By daysField = By.xpath("//div[@class='Dropdown-option']");
    private final By colorField = By.xpath("//label[contains(text(),'чёрный жемчуг')]");
    private final By trueOrderButton = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[3]/button[2]");
    private final By approveButton = By.xpath("//button[contains(text(),'Да')]");
    private final By modalWindow = By.className("Order_ModalHeader__3FDaJ");


    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillOrderForm(String name, String lastName, String address, String phone) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(metroField).click();
        driver.findElement(stationField).click();
        driver.findElement(phoneField).sendKeys(phone);
        // Закрываем плашку с куки чтобы не мешали
        driver.findElement(cookieButton).click();
        driver.findElement(nextButton).click();
        driver.findElement(arrivalField).click();
        driver.findElement(dateField).click();
        driver.findElement(rentField).click();
        driver.findElement(daysField).click();
        driver.findElement(colorField).click();
        driver.findElement(trueOrderButton).click();
        driver.findElement(approveButton).click();
    }
    // Обрабатываем модальное окно
    public String getModalWindowText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(modalWindow));
        return modal.getText();
    }
}

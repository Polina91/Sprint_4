package org.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {
    private final WebDriver driver;

    // Локаторы
    // Кнопка «Заказать» в хедере
    private final By topOrderButton = By.className("Button_Button__ra12g");
    // Кнопка «Заказать» в середине страницы
    private final By midOrderButton = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");

    // Вопросы в FAQ
    private final By faqQuestions = By.className("accordion__button");
    // Ответы в FAQ
    private final By faqAnswers = By.className("accordion__panel");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickTopOrderButton() {
        driver.findElement(topOrderButton).click();
    }

    public void clickMidOrderButton() {
        // Скроллим до кнопки за куками
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(midOrderButton));
        driver.findElement(midOrderButton).click();
    }

    public void clickQuestionByIndex(int index) {
        // Скроллим до вопросов
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(faqQuestions));
        driver.findElements(faqQuestions).get(index).click();
    }

    public String getAnswerByIndex(int index) {
        List<WebElement> answers = driver.findElements(faqAnswers);
        // Даём время, чтобы прогрузился текст
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(answers.get(index)));
        return answers.get(index).getText();
    }
}
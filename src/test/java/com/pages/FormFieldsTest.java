package com.pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormFieldsTest {
    private WebDriver driver;
    private FormFieldsPage formPage;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://practice-automation.com/form-fields/");
        formPage = new FormFieldsPage(driver);
    }

    @Test
    void testFormFields() {
        formPage.fillName("SilentLunar");
        formPage.fillPassword("passWORD123!");
        formPage.selectDrinks("Milk", "Coffee");
        formPage.selectColor("Yellow");
        formPage.selectAutomationOptionByValue("yes");
        formPage.fillEmail("SilentLunar@example.com");
        formPage.fillMessage("Количество иструментов - " + formPage.getAutomationToolsSize() + ". Инструмент из списка с самым длинным названием - " + formPage.findLongestAutomationToolText() + ".");
        formPage.submitForm(driver);

        assertEquals("Message received!", formPage.getAlertText(driver));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}

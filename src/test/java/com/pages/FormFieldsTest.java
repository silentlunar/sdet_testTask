package com.pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormFieldsTest {
    private WebDriver driver;
    private FormFieldsPage formPage;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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

        String tools = "Selenium, Junit5, Maven, WebDriver";
        String longestTool = "";
        for (String tool : tools.split(", ")) {
            if (tool.length() > longestTool.length()) {
                longestTool = tool;
            }
        }
        formPage.fillMessage("4, " + longestTool);
        formPage.submitForm(driver);


        assertEquals("Message received!", formPage.getAlertText(driver));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}

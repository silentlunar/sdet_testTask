package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class FormFieldsPage extends BasePage {

    @FindBy(id = "name-input")
    private WebElement nameField;

    @FindBy(xpath = "//label/input[@type='password']")
    private WebElement passwordField;

    @FindBy(css = "input[type='checkbox'][name='fav_drink']")
    private List<WebElement> drinkCheckboxes;

    @FindBy(css = "input[type='radio'][name='fav_color']")
    private List<WebElement> colorRadioButtons;

    @FindBy(css = "select[data-testid='automation']")
    private WebElement automationDropdown;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(xpath = "//label[text()='Automation tools']/following-sibling::ul/li")
    private List<WebElement> automationTools;

    @FindBy(xpath = "//textarea[@id = 'message']")
    private WebElement messageField;

    @FindBy(css = "button[data-cy='submit-btn'][data-testid='submit-btn']")
    private WebElement submitButton;

    public FormFieldsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void fillName(String name) {
        nameField.sendKeys(name);
    }

    public void fillPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void selectDrinks(String... drinks) {
        for (String drink : drinks) {
            for (WebElement checkbox : drinkCheckboxes) {
                if (Objects.equals(checkbox.getDomAttribute("value"), drink)) {
                    if (!checkbox.isSelected()) {
                        checkbox.click();
                    }
                    break;
                }
            }
        }
    }

    public void selectColor(String color) {
        for (WebElement radioButton : colorRadioButtons) {
            if (Objects.equals(radioButton.getDomAttribute("value"), color)) {
                if (!radioButton.isSelected()) {
                    radioButton.click();
                }
                break;
            }
        }
    }

    public void selectAutomationOptionByValue(String optionValue) {
        Select dropdown = new Select(automationDropdown);
        dropdown.selectByValue(optionValue);
    }

    public void fillEmail(String email) {
        emailField.sendKeys(email);
    }

    public void fillMessage(String message) {
        messageField.sendKeys(message);
    }

    public void submitForm(WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.moveToElement(submitButton).click().build().perform();
    }

    public String getAlertText(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert().getText();
    }

    public String findLongestAutomationToolText() {
        String longestTool = "";
        for (WebElement tool : automationTools) {
            String toolText = tool.getText();
            if (toolText.length() > longestTool.length()) {
                longestTool = toolText;
            }
        }
        return longestTool;
    }

    public int getAutomationToolsSize() {
        return automationTools.size();
    }
}

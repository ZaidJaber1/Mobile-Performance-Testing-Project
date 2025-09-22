package MobileTesting.FacebookApp.Pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.MobileBy;

public class LoginPage {

    private AndroidDriver<MobileElement> driver;
    private WebDriverWait wait;

    public LoginPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    private By alreadyHaveAccount = MobileBy.AndroidUIAutomator(
            "new UiSelector().text(\"I already have an account\")");
    private By emailField = MobileBy.AccessibilityId("Mobile number or email,");
    private By passwordField = MobileBy.AccessibilityId("Password,");
    private By loginButton = MobileBy.AndroidUIAutomator("new UiSelector().text(\"Log in\")");
    private By homePageElement = MobileBy.AccessibilityId("Make a post on Facebook");

    private By emptyFieldsMsg = MobileBy.AndroidUIAutomator(
            "new UiSelector().text(\"Enter your email or mobile number to log in\")");
    private By emptyFieldsOkBtn = MobileBy.AndroidUIAutomator("new UiSelector().text(\"OK\")");

    private By incorrectPasswordMsg = MobileBy.AccessibilityId("The password you entered is incorrect.");
    private By backFromInvalidPassword = MobileBy.AndroidUIAutomator(
            "new UiSelector().className(\"android.widget.ImageView\").instance(3)");

    private By incorrectPasswordBox = MobileBy.AndroidUIAutomator(
            "new UiSelector().text(\"Incorrect Password\")");
    private By incorrectPasswordOk = MobileBy.AndroidUIAutomator("new UiSelector().text(\"OK\")");

    private By bothInvalidMsg = MobileBy.AndroidUIAutomator(
            "new UiSelector().textContains(\"isn't connected to an account\")");
    private By bothInvalidTryAgain = MobileBy.AndroidUIAutomator("new UiSelector().text(\"TRY AGAIN\")");

    private By invalidEmailOk = MobileBy.AndroidUIAutomator("new UiSelector().text(\"OK\")");

    public void clickAlreadyHaveAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(alreadyHaveAccount)).click();
    }

    public void enterEmail(String email) {
        MobileElement emailInput = (MobileElement) wait.until(
                ExpectedConditions.visibilityOfElementLocated(emailField));
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        MobileElement passInput = (MobileElement) wait.until(
                ExpectedConditions.visibilityOfElementLocated(passwordField));
        passInput.clear();
        passInput.sendKeys(password);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public void clearFields() {
        driver.findElement(emailField).clear();
        driver.findElement(passwordField).clear();
    }

    public boolean isEmptyFieldsMsgDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(emptyFieldsMsg)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickEmptyFieldsOk() {
        wait.until(ExpectedConditions.elementToBeClickable(emptyFieldsOkBtn)).click();
    }

    public boolean isIncorrectPasswordDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(incorrectPasswordMsg)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickBackFromInvalidPassword() {
        wait.until(ExpectedConditions.elementToBeClickable(backFromInvalidPassword)).click();
    }

    public boolean isIncorrectPasswordBoxDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(incorrectPasswordBox)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickIncorrectPasswordOk() {
        wait.until(ExpectedConditions.elementToBeClickable(incorrectPasswordOk)).click();
    }

    public boolean isBothInvalidMsgDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(bothInvalidMsg)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickBothInvalidTryAgain() {
        wait.until(ExpectedConditions.elementToBeClickable(bothInvalidTryAgain)).click();
    }

    public boolean isInvalidEmailOkDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(invalidEmailOk)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickInvalidEmailOk() {
        wait.until(ExpectedConditions.elementToBeClickable(invalidEmailOk)).click();
    }

    public boolean isHomePageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(homePageElement));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

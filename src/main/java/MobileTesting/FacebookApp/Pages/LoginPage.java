package MobileTesting.FacebookApp.Pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private AndroidDriver<MobileElement> driver;
    private WebDriverWait wait;

    public LoginPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10); // Appium 7.6.0
    }

    // Locators
    private By alreadyHaveAccount = By.xpath("//android.view.View[@content-desc='I already have an account']");
    private By emailField = By.xpath("//android.widget.EditText[@content-desc='Mobile number or email,']");
    private By passwordField = By.xpath("//android.widget.EditText[@content-desc='Password,']");
    private By loginButton = By.xpath("//android.view.View[@content-desc='Log in']");

    // Popups
    private By invalidPasswordBack = By.xpath("//android.widget.Button[@content-desc='Back']/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageView");
    private By bothInvalidTryAgain = By.xpath("//android.widget.Button[@resource-id='com.facebook.katana:id/(name removed)' and @text='TRY AGAIN']");
    private By invalidEmailOk = By.xpath("//android.widget.Button[@resource-id='com.facebook.katana:id/(name removed)']");

    // Home page element for assertion
    private By homePageElement = By.xpath("//android.view.View[@content-desc='Home, tab 1 of 6']"); // updated xpath
    
    private By uniqueAfterInvalidPassPage = By.xpath("//android.view.View[@content-desc='The password you entered is incorrect.']"); // example


    // Actions
    public void clickAlreadyHaveAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(alreadyHaveAccount)).click();
    }

    public void enterEmail(String email) {
        MobileElement emailInput = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        MobileElement passInput = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
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

    // Handle scenarios
    public void handleInvalidPassword() {
        try {
            // wait for unique page element
            wait.until(ExpectedConditions.visibilityOfElementLocated(uniqueAfterInvalidPassPage));
            // only then click back button
            MobileElement backButton = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(invalidPasswordBack));
            backButton.click();
        } catch (Exception e) {
            // page not displayed, do nothing
        }
    }

    public void handleBothInvalid() {
        try {
            MobileElement tryAgainBtn = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(bothInvalidTryAgain));
            tryAgainBtn.click();
        } catch (Exception e) {
            // Not displayed
        }
    }

    public void handleInvalidEmail() {
        try {
            MobileElement okBtn = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(invalidEmailOk));
            okBtn.click();
        } catch (Exception e) {
            // Not displayed
        }
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

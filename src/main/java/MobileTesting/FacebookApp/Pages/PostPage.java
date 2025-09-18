package MobileTesting.FacebookApp.Pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PostPage {

    private AndroidDriver<MobileElement> driver;
    private WebDriverWait wait;

    public PostPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    private By createButton = By.xpath("//android.widget.Button[@content-desc=\"Create, Double tap to create a new post, story, or reel\"]/android.widget.FrameLayout");
    private By postOption = By.xpath("//android.widget.TextView[@resource-id='com.facebook.katana:id/(name removed)' and @text='Post']");
    private By postTextField = By.xpath("//android.widget.AutoCompleteTextView[@resource-id='com.facebook.katana:id/(name removed)']");
    private By postButton = By.xpath("//android.widget.Button[@content-desc=\"POST\"]");

    public void clickCreateButton() {
        wait.until(ExpectedConditions.elementToBeClickable(createButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(postOption));
    }

    public void selectPostOption() {
        wait.until(ExpectedConditions.elementToBeClickable(postOption)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(postTextField));
    }

    public void enterPostText(String text) {
        MobileElement textField = driver.findElement(postTextField);
        textField.clear();
        textField.sendKeys(text);
    }

    public void clickPostButton() {
        wait.until(ExpectedConditions.elementToBeClickable(postButton)).click();
    }
}

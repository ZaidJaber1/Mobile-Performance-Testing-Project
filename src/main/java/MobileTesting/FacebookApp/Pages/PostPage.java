package MobileTestingProject.Facebook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class PostPage {
	public final AndroidDriver driver;
	public final WebDriverWait wait;

	public PostPage(AndroidDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	private By createButton = By.xpath(
			"//android.widget.Button[@content-desc=\"Create, Double tap to create a new post, story, or reel\"]/android.widget.FrameLayout");
	private By postOption = By
			.xpath("//android.widget.TextView[@resource-id='com.facebook.katana:id/(name removed)' and @text='Post']");
	private By postTextField = By
			.xpath("//android.widget.AutoCompleteTextView[@resource-id='com.facebook.katana:id/(name removed)']");
	private By postButton = AppiumBy.accessibilityId("POST");
	private By BackButton = AppiumBy.accessibilityId("Back");
	private By postProfile = AppiumBy.accessibilityId("Zaid Alshaikh Profile picture");
	private By Like = By
			.xpath("//android.view.ViewGroup[contains(@content-desc,'Like') or contains(@content-desc,'Unlike')]");

	private By LikeCount = By.xpath(
			"//androidx.recyclerview.widget.RecyclerView[@resource-id=\"android:id/list\"]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[4]/android.view.ViewGroup/android.view.ViewGroup[1]");

	private By Comment = AppiumBy.accessibilityId("Comment");
	private By CommentText = By.xpath("//android.widget.AutoCompleteTextView[@text=\"Write a comment…\"]");
	private By SendComment = AppiumBy.accessibilityId("Send");
	private By PostedComment = By.xpath(
			"(//android.view.ViewGroup[@resource-id=\"com.facebook.katana:id/(name removed)\"])[2]/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[2]");
	private By CommentCount = By.xpath(
			"//androidx.recyclerview.widget.RecyclerView[@resource-id=\"android:id/list\"]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup/android.view.ViewGroup[2]");
	private By ReplayButton = AppiumBy.accessibilityId("Reply button. Double tap to reply.");
	private By ShareButton = AppiumBy.accessibilityId("Share");
	private By ShareText = By
			.xpath("//android.widget.AutoCompleteTextView[@resource-id=\"com.facebook.katana:id/(name removed)\"]");
	private By ShareKnow = AppiumBy.accessibilityId("Share now");
	private By PhotoButton = AppiumBy.accessibilityId("Photo/video");
	private By Photo = By.xpath("(//android.view.ViewGroup[@content-desc=\"Photo taken on Sep 20, 2025 23:29\"])[1]");

	public void waitUntilPostVisible() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(postProfile));
	}

	///////////// Create Post/////////////////////
	public void clickCreateButton() {
		wait.until(ExpectedConditions.elementToBeClickable(createButton)).click();
		wait.until(ExpectedConditions.elementToBeClickable(postOption));
	}

	public void selectPostOption() {
		wait.until(ExpectedConditions.elementToBeClickable(postOption)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(postTextField));
	}

	public void enterPostText(String text) {
		WebElement textField = wait.until(ExpectedConditions.elementToBeClickable(postTextField));
		textField.clear();
		textField.sendKeys(text);
	}

	public void clickPostButton() {
		wait.until(ExpectedConditions.elementToBeClickable(postButton)).click();
	}

	public void clickBackButton() {
		wait.until(ExpectedConditions.elementToBeClickable(BackButton)).click();
	}

	public void clickAddPhoto() {
		wait.until(ExpectedConditions.elementToBeClickable(PhotoButton)).click();
	}

	public void SelectPhoto() {
		wait.until(ExpectedConditions.elementToBeClickable(Photo)).click();
	}

	public void swipeUp() {
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
	}

	public boolean isPostSuccessful() {
		try {
			WebElement profile = wait.until(ExpectedConditions.visibilityOfElementLocated(postProfile));
			return profile.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	public boolean isPostButtonEnabled() {
		try {
			WebElement button = wait.until(ExpectedConditions.elementToBeClickable(postButton));
			return button.isDisplayed() && button.isEnabled();
		} catch (Exception e) {

			return false;
		}
	}

	////////////////////////// Comment///////////////////////////////////
	public void clickComment() {
		wait.until(ExpectedConditions.elementToBeClickable(Comment)).click();
	}

	public void TypeComment(String text) {
		WebElement textField = wait.until(ExpectedConditions.visibilityOfElementLocated(CommentText));
		textField.clear();
		textField.sendKeys(text);

	}

	public void SendComment() {
		wait.until(ExpectedConditions.elementToBeClickable(SendComment)).click();
	}

	public boolean isCommentSuccessful() {
		try {
			WebElement profile = wait.until(ExpectedConditions.visibilityOfElementLocated(PostedComment));
			return profile.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	public boolean isCommenttButtonEnabled() {
		try {
			WebElement button = wait.until(ExpectedConditions.elementToBeClickable(SendComment));
			return button.isDisplayed() && button.isEnabled();
		} catch (Exception e) {

			return false;
		}
	}

	//////////////// Like////////////////////
	public void clickLike() {
		wait.until(ExpectedConditions.elementToBeClickable(Like)).click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException ignored) {
		}
	}

	public boolean isLikeButtonClickable() {
		try {
			WebElement likeBtn = wait.until(ExpectedConditions.elementToBeClickable(Like));
			return likeBtn.isDisplayed() && likeBtn.isEnabled();
		} catch (Exception e) {
			return false;
		}
	}

	public int getLikeCount() {
		try {
			WebElement likeCountEl = wait.until(ExpectedConditions.visibilityOfElementLocated(LikeCount));
			String text = likeCountEl.getText().trim();

			if (text.isEmpty()) {
				return 0;
			}

			return Integer.parseInt(text);
		} catch (Exception e) {
			// Element not found or not visible
			return 0;
		}
	}

//////////////////Share/////////////

	public void clickShare() {
		wait.until(ExpectedConditions.elementToBeClickable(ShareButton)).click();

	}

	public void AddCaption(String text) {

		WebElement textField = wait.until(ExpectedConditions.visibilityOfElementLocated(ShareText));
		textField.clear();
		textField.sendKeys(text);
	}

	public void clickShareKnow() {
		wait.until(ExpectedConditions.elementToBeClickable(ShareKnow)).click();

	}

	public boolean isShareSuccessful() {
		try {
			return wait.until(ExpectedConditions.invisibilityOfElementLocated(ShareKnow));
		} catch (Exception e) {
			return false;
		}

	}

	public boolean isSharedPostVisible(String captionText) {
		List<WebElement> posts = driver.findElements(By.xpath("//*[contains(@text,'" + captionText + "')]"));
		return !posts.isEmpty();
	}

}

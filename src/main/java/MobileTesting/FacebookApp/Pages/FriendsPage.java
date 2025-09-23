package MobileTestingProject.Facebook;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class FriendsPage {
	public final AndroidDriver driver;
	public final WebDriverWait wait;

	public FriendsPage(AndroidDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	private By HomeTab = AppiumBy.accessibilityId("Home, tab 1 of 6");
	private By FriendsTab = AppiumBy.accessibilityId("Friends, tab 2 of 6");
	private By DeleteButton = By.xpath("//android.view.ViewGroup[contains(@content-desc, 'Delete')]");
	private By ConfirmButton = By.xpath("//android.view.ViewGroup[contains(@content-desc, 'Confirm')]");
	private By Sugesstions = AppiumBy.accessibilityId("Suggestions");
	private By YourFriends = AppiumBy.accessibilityId("Your friends");
	private By BackButton = By.xpath("//android.widget.ImageView[@content-desc=\"Back\"]");
	private By NoFriends = By.xpath(
			"(//android.view.ViewGroup[@resource-id=\"com.facebook.katana:id/(name removed)\"])[3]/android.view.ViewGroup/android.view.ViewGroup[1]");
	private By NoSugesstions = By.xpath(
			"(//android.view.ViewGroup[@resource-id=\"com.facebook.katana:id/(name removed)\"])[3]/android.view.ViewGroup/android.view.ViewGroup[1]");
	private By ConfirmSucess = By.xpath(
			"/androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.facebook.katana:id/(name removed)\"]/android.view.ViewGroup[3]");
	private By RequesterName = By.xpath(
			"//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.facebook.katana:id/(name removed)\"]/android.view.ViewGroup[3]/android.view.ViewGroup[1]");
	private By MoreOptions = By.xpath("(//android.view.ViewGroup[@content-desc=\"More options\"])[1]");
	private By RequestProfile = By
			.xpath("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[3]/android.view.ViewGroup[1]");
	private By AddFriend = AppiumBy.accessibilityId("Add friend");
	private By CancelRequest = AppiumBy.accessibilityId("Cancel request");
	private By searchButton = AppiumBy.accessibilityId("Search Facebook");
	private By SearchTextBox = By.className("android.widget.EditText");
	private By CloseButton = AppiumBy.accessibilityId("Close");

	public void OpenFriends() {
		wait.until(ExpectedConditions.elementToBeClickable(FriendsTab)).click();
	}

	public void clickHomeTab() {
		wait.until(ExpectedConditions.elementToBeClickable(HomeTab)).click();
	}

	public void clickConfirmRequest() {
		wait.until(ExpectedConditions.elementToBeClickable(ConfirmButton)).click();
	}

	public boolean ConfirmSucess() {
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(ConfirmButton));
	}

	public void clickDeleteRequest() {
		wait.until(ExpectedConditions.elementToBeClickable(DeleteButton)).click();
	}

	public boolean IsDeleted() {
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(DeleteButton));
	}

	public void OpenSugesstions() {
		wait.until(ExpectedConditions.elementToBeClickable(Sugesstions)).click();
	}

	public void ClickSearch() {
		wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
	}

	public void SelectProfile() {
		wait.until(ExpectedConditions.elementToBeClickable(RequestProfile)).click();
	}

	public void click_Add_Cancel_Friend() {
		wait.until(ExpectedConditions.elementToBeClickable(AddFriend)).click();
	}

	public void clickCancelRequest() {
		wait.until(ExpectedConditions.elementToBeClickable(CancelRequest)).click();
	}

	public void SearchFriend(String text) {
		WebElement textField = wait.until(ExpectedConditions.elementToBeClickable(SearchTextBox));
		textField.clear();
		textField.sendKeys(text);

		WebElement firstResult = wait.until(ExpectedConditions.elementToBeClickable(RequestProfile));

	}

	public void clickClose() {
		wait.until(ExpectedConditions.elementToBeClickable(CloseButton)).click();
	}

	public void waitForProfileResult() {
		WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(15));
		shortWait.until(ExpectedConditions.elementToBeClickable(RequestProfile));
	}

	public void OpenFriendsList() {
		wait.until(ExpectedConditions.elementToBeClickable(YourFriends)).click();
	}

	public boolean IsNoFriend() {
		WebElement NoFriend = wait.until(ExpectedConditions.visibilityOfElementLocated(NoFriends));
		return NoFriend.isDisplayed();

	}

	public boolean ViewingFriendList() {
		return driver.findElements(MoreOptions).isEmpty();
	}

	public void ClickBack() {
		wait.until(ExpectedConditions.elementToBeClickable(BackButton)).click();
	}

	public int getConfirmButtonsCount() {
		return driver.findElements(ConfirmButton).size();
	}

	public int getDeleteButtonsCount() {
		return driver.findElements(DeleteButton).size();
	}

	public boolean isAddFriendVisible() {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(AddFriend));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isCancelRequestVisible() {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(AddFriend));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}

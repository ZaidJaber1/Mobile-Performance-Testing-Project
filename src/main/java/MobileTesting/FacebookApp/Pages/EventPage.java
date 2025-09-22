package MobileTesting.FacebookApp.Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import java.time.Duration;
import java.util.List;

public class EventPage {
	private AppiumDriver<MobileElement> driver;
	private WebDriverWait wait;

	public EventPage(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 15);
	}

	private By menuBtn = MobileBy.AccessibilityId("Menu, tab 6 of 6");
	private By seeMoreBtn = MobileBy.AccessibilityId("See more");
	private By eventsCard = MobileBy.AccessibilityId("Events");
	private By addEventBtn = MobileBy.AccessibilityId("Create");

	private By eventNameField = MobileBy
			.AndroidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)");

	private By startDateTimeBtn = MobileBy.AccessibilityId("Start date and time");
	private By startDateBtn = MobileBy.AccessibilityId("Start date");
	private By endDateBtn = MobileBy.AccessibilityId("End date");

	private By dateCalendar = MobileBy
			.AndroidUIAutomator("new UiSelector().className(\"android.widget.LinearLayout\").instance(0)");
	private By dateOkBtn = By.id("android:id/button1");
	private By nextMonthBtn = By.id("android:id/next");

	private By startTimeBtn = MobileBy.AccessibilityId("Start time");
	private By endTimeBtn = MobileBy.AccessibilityId("End time");

	private By timePickerFrame = MobileBy
			.AndroidUIAutomator("new UiSelector().className(\"android.widget.FrameLayout\").instance(0)");
	private By timeOkBtn = By.id("android:id/button1");
	private By doneBtn = MobileBy.AccessibilityId("Done");

	private By addEndTimeBtn = MobileBy.AccessibilityId("Add end time");

	private By inPersonVirtualBtn = MobileBy.AccessibilityId("Is it in person or virtual?");
	private By inPersonField = MobileBy
			.AndroidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)");
	private By firstSearchResult = MobileBy
			.AndroidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").instance(0)");

	private By whoCanSeeBtn = MobileBy
			.AndroidUIAutomator("new UiSelector().description(\"Who can see it? \").instance(0)");
	private By publicRadioBtn = By.xpath(
			"//android.widget.Button[@content-desc=\"Public Anyone on or off Facebook Unselected\"]/android.widget.ImageView");

	private By detailsField = MobileBy
			.AndroidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(2)");
	private By createEventBtn = By.xpath(
			"//android.widget.Button[@content-desc=\"Create event\"]/android.view.ViewGroup/android.view.ViewGroup");
	private By closePopupBtn = By.xpath(
			"(//android.widget.Button[@content-desc=\"Close\"])[3]/android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageView");

	private By numberPickers = MobileBy.className("android.widget.NumberPicker");
	private By pickerEditText = By.className("android.widget.EditText");
	private By pickerButtons = By.className("android.widget.Button");

	private By toastMessage = MobileBy.xpath("//android.widget.Toast[1]");
	private By dialogMessage = By.id("android:id/message");

	private By manageEventBtn = MobileBy.AccessibilityId("Manage");
	private By editEventBtn = MobileBy.AndroidUIAutomator("new UiSelector().text(\"Edit\")");
	private By saveBtn = MobileBy.AccessibilityId("Save");

	private By shareBtn = MobileBy.AccessibilityId("Share");
	private By shareNowBtn = MobileBy.AccessibilityId("Share now");

	private By deleteEventRadioBtn = By.xpath(
			"//android.widget.RadioButton[@content-desc=\"Cancel and delete event details, All posts on the event details page will be deleted.\"]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.ImageView");
	private By cancelEventBtn = By.xpath(
			"//android.widget.Button[@content-desc=\"Cancel event\"]/android.view.ViewGroup/android.view.ViewGroup");
	private By backBtn = MobileBy.AccessibilityId("Back");
	private By eventNotAvailableMsg = MobileBy.AccessibilityId("This event is not available.");
	private By deleteEventBtn = MobileBy.AccessibilityId("Delete event");

	private By searchBtn = MobileBy.AccessibilityId("Search");
	private By searchField = MobileBy.className("android.widget.EditText");

	private By firstSearchResultInSearch = MobileBy
			.xpath("//androidx.recyclerview.widget.StaggeredGridLayoutManager/android.view.ViewGroup[2]");

	private By interestedBtnNotSelected = MobileBy.AccessibilityId("Interested , not selected");
	private By interestedBtnSelected = MobileBy.AccessibilityId("Interested , selected");

	private By goingBtnNotSelected = MobileBy.AccessibilityId("Going , not selected");
	private By goingBtnSelected = MobileBy.AccessibilityId("Going , selected");

	private By dynamicEvent(String eventName) {
		return MobileBy.AccessibilityId(eventName);
	}

	public void openEventsPage() {
		wait.until(ExpectedConditions.elementToBeClickable(menuBtn)).click();
		wait.until(ExpectedConditions.elementToBeClickable(seeMoreBtn)).click();
		wait.until(ExpectedConditions.elementToBeClickable(eventsCard)).click();
	}

	public void clickAddEvent() {
		wait.until(ExpectedConditions.elementToBeClickable(addEventBtn)).click();
	}

	public void setEventName(String name) {
		wait.until(ExpectedConditions.elementToBeClickable(eventNameField)).sendKeys(name);
	}

	public void setStartDateTime(String month, String day, String year, String hour, String minute, String ampm) {
		wait.until(ExpectedConditions.elementToBeClickable(startDateTimeBtn)).click();
		wait.until(ExpectedConditions.elementToBeClickable(startDateBtn)).click();
		selectDateFromCalendar(month, day, year);
		wait.until(ExpectedConditions.elementToBeClickable(dateOkBtn)).click();

		wait.until(ExpectedConditions.elementToBeClickable(startTimeBtn)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(timePickerFrame));
		selectTime(hour, minute, ampm);
		wait.until(ExpectedConditions.elementToBeClickable(timeOkBtn)).click();

		wait.until(ExpectedConditions.elementToBeClickable(doneBtn)).click();
	}

	public void setEndDateTime(String month, String day, String year, String hour, String minute, String ampm) {
		wait.until(ExpectedConditions.elementToBeClickable(addEndTimeBtn)).click();
		wait.until(ExpectedConditions.elementToBeClickable(endDateBtn)).click();
		selectDateFromCalendar(month, day, year);
		wait.until(ExpectedConditions.elementToBeClickable(dateOkBtn)).click();

		wait.until(ExpectedConditions.elementToBeClickable(endTimeBtn)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(timePickerFrame));
		selectTime(hour, minute, ampm);
		wait.until(ExpectedConditions.elementToBeClickable(timeOkBtn)).click();

		wait.until(ExpectedConditions.elementToBeClickable(doneBtn)).click();
	}

	public void setLocation(String location) {
		wait.until(ExpectedConditions.elementToBeClickable(inPersonVirtualBtn)).click();
		wait.until(ExpectedConditions.elementToBeClickable(inPersonField)).sendKeys(location);
		driver.hideKeyboard();

		wait.until(ExpectedConditions.elementToBeClickable(firstSearchResult)).click();
	}

	public void setVisibilityPublic() {
		wait.until(ExpectedConditions.elementToBeClickable(whoCanSeeBtn)).click();
		wait.until(ExpectedConditions.elementToBeClickable(publicRadioBtn)).click();
		wait.until(ExpectedConditions.elementToBeClickable(doneBtn)).click();
	}

	public void setDetails(String details) {
		wait.until(ExpectedConditions.elementToBeClickable(detailsField)).sendKeys(details);
	}

	public void createEvent() {
		wait.until(ExpectedConditions.elementToBeClickable(createEventBtn)).click();
		wait.until(ExpectedConditions.elementToBeClickable(closePopupBtn)).click();
	}

	public boolean isEventCreated(String eventName) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(dynamicEvent(eventName))).isDisplayed();
	}

	private void selectDateFromCalendar(String month, String day, String year) {
		String target = String.format("%02d %s %s", Integer.parseInt(day), month, year);
		int attempts = 0;
		driver.manage().timeouts().implicitlyWait(500, java.util.concurrent.TimeUnit.MILLISECONDS);

		while (attempts < 12) {
			try {
				driver.findElement(MobileBy.AccessibilityId(target)).click();
				driver.manage().timeouts().implicitlyWait(15, java.util.concurrent.TimeUnit.SECONDS);
				return;
			} catch (Exception e) {
				driver.findElement(nextMonthBtn).click();
			}
			attempts++;
		}

		driver.manage().timeouts().implicitlyWait(15, java.util.concurrent.TimeUnit.SECONDS);
		throw new RuntimeException("Target date " + target + " not found!");
	}

	private void setTimePicker(int pickerIndex, String targetValue) {
		List<MobileElement> pickers = driver.findElements(numberPickers);
		if (pickers.size() < 3)
			throw new RuntimeException("NumberPickers not found");

		MobileElement picker = pickers.get(pickerIndex);
		MobileElement editText = picker.findElement(pickerEditText);
		String currentValue = editText.getText();

		if (targetValue.equalsIgnoreCase("AM") || targetValue.equalsIgnoreCase("PM")) {
			if (!currentValue.equalsIgnoreCase(targetValue)) {
				picker.findElement(pickerButtons).click();
			}
			return;
		}

		int current = Integer.parseInt(currentValue);
		int target = Integer.parseInt(targetValue);

		if (current == target)
			return;

		int pickerHeight = picker.getSize().height;
		int startX = picker.getLocation().getX() + picker.getSize().width / 2;
		int startY = picker.getLocation().getY() + pickerHeight / 2;

		int steps = Math.abs(target - current);
		int direction = (target > current) ? -1 : 1;
		int stepHeight = pickerHeight / 5;
		int totalDistance = stepHeight * steps;

		new TouchAction<>(driver).press(PointOption.point(startX, startY))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(150)))
				.moveTo(PointOption.point(startX, startY + direction * totalDistance)).release().perform();

		editText = picker.findElement(pickerEditText);
		int maxAttempts = 5;
		int attempts = 0;
		while (!editText.getText().equals(targetValue) && attempts < maxAttempts) {
			if (Integer.parseInt(editText.getText()) < target) {
				picker.findElements(pickerButtons).get(1).click();
			} else {
				picker.findElements(pickerButtons).get(0).click();
			}
			attempts++;
			editText = picker.findElement(pickerEditText);
		}
	}

	private void selectTime(String hour, String minute, String ampm) {
		setTimePicker(0, hour);
		setTimePicker(1, minute);
		setTimePicker(2, ampm);
	}

	public boolean isCreateEventEnabled() {
		try {
			return wait.until(ExpectedConditions.presenceOfElementLocated(createEventBtn)).getAttribute("clickable")
					.equals("true");
		} catch (Exception e) {
			return false;
		}
	}

	public void clearAllFields() {
		try {
			MobileElement name = driver.findElement(eventNameField);
			name.clear();
		} catch (Exception ignored) {
		}

		try {
			MobileElement details = driver.findElement(detailsField);
			details.clear();
		} catch (Exception ignored) {
		}
	}

	public String getErrorMessage() {
		try {
			MobileElement toast = driver.findElement(toastMessage);
			return toast.getText();
		} catch (Exception ignored) {
		}

		try {
			return driver.findElement(dialogMessage).getText();
		} catch (Exception ignored) {
		}

		return "";
	}

	public void clickCreateEventRaw() {
		wait.until(ExpectedConditions.elementToBeClickable(createEventBtn)).click();
	}

	public void openEventOptions() {
		wait.until(ExpectedConditions.elementToBeClickable(manageEventBtn)).click();
	}

	public void clickEditEvent() {
		wait.until(ExpectedConditions.elementToBeClickable(editEventBtn)).click();
	}

	public void saveEventChanges() {
		wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
	}

	public void shareEvent() {
		wait.until(ExpectedConditions.elementToBeClickable(shareBtn)).click();
		wait.until(ExpectedConditions.elementToBeClickable(shareNowBtn)).click();
	}

	public String waitForToast(String expectedText, int timeoutSeconds) {
		WebDriverWait toastWait = new WebDriverWait(driver, timeoutSeconds);
		return toastWait.until(driver -> {
			try {
				String text = driver.findElement(toastMessage).getText();
				return text.contains(expectedText) ? text : null;
			} catch (Exception e) {
				return null;
			}
		});
	}

	public void deleteEvent() {
		wait.until(ExpectedConditions.elementToBeClickable(manageEventBtn)).click();
		driver.findElement(editEventBtn).click();
		driver.findElement(deleteEventBtn).click();
		wait.until(ExpectedConditions.elementToBeClickable(deleteEventRadioBtn)).click();
		wait.until(ExpectedConditions.elementToBeClickable(cancelEventBtn)).click();
	}

	public boolean isEventDeleted() {
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(eventNotAvailableMsg)).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void goBack() {
		wait.until(ExpectedConditions.elementToBeClickable(backBtn)).click();
	}

	public void clickSearch() {
		wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
	}

	public void enterSearchText(String eventName) {
		wait.until(ExpectedConditions.elementToBeClickable(searchField)).sendKeys(eventName);
		driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));
	}

	public void clickFirstSearchResult() {
		wait.until(ExpectedConditions.elementToBeClickable(firstSearchResultInSearch)).click();
	}

	public void markInterested() {
		wait.until(ExpectedConditions.elementToBeClickable(interestedBtnNotSelected)).click();
	}

	public boolean isInterestedSelected() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(interestedBtnSelected)).isDisplayed();
	}

	public void markGoing() {
		wait.until(ExpectedConditions.elementToBeClickable(goingBtnNotSelected)).click();
	}

	public boolean isGoingSelected() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(goingBtnSelected)).isDisplayed();
	}

}

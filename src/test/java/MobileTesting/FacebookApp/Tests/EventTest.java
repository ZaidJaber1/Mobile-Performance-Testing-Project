package MobileTesting.FacebookApp.Tests;

import MobileTesting.FacebookApp.Pages.EventPage;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class EventTest extends BaseTest {
	private EventPage eventPage;

	@BeforeClass
	public void initPage() {
		eventPage = new EventPage(driver);
		eventPage.openEventsPage();
		eventPage.clickAddEvent();
	}

	@BeforeMethod(onlyForGroups = { "edit" })
	public void navigateToAddEventPage() {
		eventPage.clearAllFields();
	}

	@Test(priority = 1, dataProvider = "eventData", groups = {
			"edit" }, description = "Verify user can't create event with past date")
	public void TC_EVE_002(String eventName, String details, String location, String startMonth, String startDay,
			String startYear, String startHour, String startMinute, String startAmpm, String endMonth, String endDay,
			String endYear, String endHour, String endMinute, String endAmpm) {

		if (eventName.equals("Past Event")) {
			eventPage.setStartDateTime(startMonth, startDay, startYear, startHour, startMinute, startAmpm);

			int width = driver.manage().window().getSize().width;
			int height = driver.manage().window().getSize().height;

			String startError = eventPage.getErrorMessage();
			Assert.assertTrue(startError.contains("The event cannot start in the past"),
					"Expected error for past start date, but got: " + startError);

			new TouchAction<>(driver).tap(PointOption.point(width / 2, height / 2)).perform();

			eventPage.setEndDateTime(endMonth, endDay, endYear, endHour, endMinute, endAmpm);

			String endError = eventPage.getErrorMessage();
			Assert.assertTrue(
					endError.contains("cannot end in the past") || endError.contains("cannot end before it starts"),
					"Expected error for past end date, but got: " + endError);
			new TouchAction<>(driver).tap(PointOption.point(width / 2, height / 2)).perform();
		}
	}

	@Test(priority = 2, dataProvider = "eventData", groups = {
			"edit" }, description = "Verify the user cannot create event with duration longer than 14 days")
	public void TC_EVE_004(String eventName, String details, String location, String startMonth, String startDay,
			String startYear, String startHour, String startMinute, String startAmpm, String endMonth, String endDay,
			String endYear, String endHour, String endMinute, String endAmpm) {

		if (eventName.equals("14 Days")) {
			eventPage.setStartDateTime(startMonth, startDay, startYear, startHour, startMinute, startAmpm);

			int width = driver.manage().window().getSize().width;
			int height = driver.manage().window().getSize().height;

			eventPage.setEndDateTime(endMonth, endDay, endYear, endHour, endMinute, endAmpm);

			String rangeError = eventPage.getErrorMessage();
			Assert.assertTrue(rangeError.contains("The event can't be longer than 14 days."),
					"Expected error for longer than 14 days: " + rangeError);
			new TouchAction<>(driver).tap(PointOption.point(width / 2, height / 2)).perform();
		}
	}

	@Test(priority = 3, dataProvider = "eventData", groups = {
			"edit" }, description = "Verify the user cannot add an event without event name, location, and who can see event ")
	public void TC_EVE_003(String eventName, String details, String location, String startMonth, String startDay,
			String startYear, String startHour, String startMinute, String startAmpm, String endMonth, String endDay,
			String endYear, String endHour, String endMinute, String endAmpm) {

		if (eventName.isEmpty()) {
			eventPage.setDetails(details);

			Assert.assertFalse(eventPage.isCreateEventEnabled(),
					"Create Event button should be disabled when mandatory fields are missing!");
		}
	}

	@Test(priority = 4, dataProvider = "eventData", groups = {
			"edit" }, description = "Verify the user can add Event for spicefcs fields")
	public void TC_EVE_001(String eventName, String details, String location, String startMonth, String startDay,
			String startYear, String startHour, String startMinute, String startAmpm, String endMonth, String endDay,
			String endYear, String endHour, String endMinute, String endAmpm) {

		if (eventName.equals("Test Event 1")) {
			eventPage.setEventName(eventName);
			eventPage.setStartDateTime(startMonth, startDay, startYear, startHour, startMinute, startAmpm);
			eventPage.setEndDateTime(endMonth, endDay, endYear, endHour, endMinute, endAmpm);
			eventPage.setLocation(location);
			eventPage.setVisibilityPublic();
			eventPage.setDetails(details);
			eventPage.createEvent();

			Assert.assertTrue(eventPage.isEventCreated(eventName), "Event was not created successfully!");
		}
	}

	@Test(priority = 5, dataProvider = "eventData", description = "Verify the user can edit an existing event")
	public void TC_EVE_005(String eventName, String details, String location, String startMonth, String startDay,
			String startYear, String startHour, String startMinute, String startAmpm, String endMonth, String endDay,
			String endYear, String endHour, String endMinute, String endAmpm) {

		if (eventName.equals("Test Event 1")) {
			int width = driver.manage().window().getSize().width;
			int height = driver.manage().window().getSize().height;

			eventPage.openEventOptions();
			eventPage.clickEditEvent();

			String updatedName = "Updated";

			eventPage.setEventName(updatedName);

			eventPage.saveEventChanges();
			new TouchAction<>(driver).tap(PointOption.point(width / 2, height / 2)).perform();

			Assert.assertTrue(eventPage.isEventCreated("Test Event 1" + updatedName),
					"Event was not updated successfully!");
		}
	}

	@Test(priority = 6, dataProvider = "eventData", description = "Verify the user can share an event")
	public void TC_EVE_006(String eventName, String details, String location, String startMonth, String startDay,
			String startYear, String startHour, String startMinute, String startAmpm, String endMonth, String endDay,
			String endYear, String endHour, String endMinute, String endAmpm) {

		if (eventName.equals("Test Event 1")) {
			eventPage.shareEvent();

			String sharedToast = eventPage.waitForToast("You shared this post.", 3);
			Assert.assertTrue(sharedToast.contains("You shared this post."),
					"Expected second toast 'You shared this post.'");
		}
	}

	@Test(priority = 7, dataProvider = "eventData", description = "Verify the user can delete an event")
	public void TC_EVE_007(String eventName, String details, String location, String startMonth, String startDay,
			String startYear, String startHour, String startMinute, String startAmpm, String endMonth, String endDay,
			String endYear, String endHour, String endMinute, String endAmpm) {

		if (eventName.equals("Test Event 1")) {
			eventPage.deleteEvent();
			int width = driver.manage().window().getSize().width;
			int height = driver.manage().window().getSize().height;

			new TouchAction<>(driver).tap(PointOption.point(width / 2, height / 2)).perform();

			Assert.assertTrue(eventPage.isEventDeleted(), "Event was not deleted successfully!");
			eventPage.goBack();
		}

	}

	@Test(priority = 8, description = "Verify the user can search for an event by name")
	public void TC_EVE_008() {

		eventPage.clickSearch();
		eventPage.enterSearchText("Palestine");
		eventPage.clickFirstSearchResult();
		Assert.assertTrue(eventPage.isEventCreated("palestine"),
				"Expected event 'Palestine' to appear in search results!");
	}

	@Test(priority = 9, description = "Verify the user can mark themselves as Interested for an event")
	public void TC_EVE_009() {
		eventPage.markInterested();
		Assert.assertTrue(eventPage.isInterestedSelected(), "Event should be marked as Interested!");
	}

	@Test(priority = 10, description = "Verify the user can mark themselves as Going for an event")
	public void TC_EVE_010() {
		eventPage.markGoing();
		Assert.assertTrue(eventPage.isGoingSelected(), "Event should be marked as Going!");
		eventPage.goBack();
		eventPage.goBack();
		eventPage.goBack();
	}

	@DataProvider(name = "eventData")
	public Iterator<Object[]> readEventData() throws IOException {
		String filePath = "src/test/testdata/eventData.csv";
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String line;
		ArrayList<Object[]> data = new ArrayList<>();

		reader.readLine();

		while ((line = reader.readLine()) != null) {
			String[] fields = line.split(",", -1);
			if (fields.length != 15) {
				throw new RuntimeException("CSV row must have exactly 15 columns. Found: " + fields.length);
			}
			data.add(fields);
		}

		reader.close();
		return data.iterator();
	}
}

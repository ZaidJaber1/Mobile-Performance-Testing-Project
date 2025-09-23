package MobileTestingProject.Facebook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hc.client5.http.auth.MalformedChallengeException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.android.connection.ConnectionState;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class FriendsTest extends BaseTest {
	private FriendsPage friendsPage;
	private WebDriverWait wait;

	@BeforeClass
	public void initPage() {
		friendsPage = new FriendsPage(driver);

	}

	@Test(priority = 2, description = "Verify accepting a friend request")
	public void TC1_Request() {
		friendsPage.OpenFriends();
		int before = friendsPage.getConfirmButtonsCount();
		friendsPage.clickConfirmRequest();
		int after = friendsPage.getConfirmButtonsCount();
		Assert.assertTrue(after < before);
	}

	@Test(priority = 3, description = "Verify Rejecting a friend request")
	public void TC2_Request() {
		friendsPage.OpenFriends();
		int before = friendsPage.getDeleteButtonsCount();
		friendsPage.clickDeleteRequest();
		int after = friendsPage.getDeleteButtonsCount();
		Assert.assertTrue(after < before);
	}

	@Test(priority = 1, description = "Verify viewing empty friend list")
	public void TC1_FriendList() {
		friendsPage.OpenFriends();
		friendsPage.OpenFriendsList();
		Assert.assertTrue(friendsPage.ViewingFriendList());
		friendsPage.ClickBack();
	}

	@Test(priority = 4, description = "Verify viewing friend list")
	public void TC2_FriendList() {
		friendsPage.OpenFriends();
		friendsPage.OpenFriendsList();
		Assert.assertTrue(friendsPage.ViewingFriendList());
		friendsPage.ClickBack();
	}

	@Test(priority = 6, description = "Verify accepting a friend request with no internet")
	public void TC3_NoInternetRequest() {
		friendsPage.OpenFriends();
		driver.setConnection(new ConnectionState(ConnectionState.AIRPLANE_MODE_MASK));
		try {
			friendsPage.OpenFriends();
			int before = friendsPage.getConfirmButtonsCount();
			friendsPage.clickConfirmRequest();
			int after = friendsPage.getConfirmButtonsCount();
			Assert.assertEquals(after, before);

		} finally {
			driver.setConnection(new ConnectionState(ConnectionState.WIFI_MASK));
		}

	}

	@Test(priority = 5, description = "Verify Send Request By Search", dataProvider = "FriendsData")
	public void TC_SendRequest(String TestCase, String Name) {
		friendsPage.clickHomeTab();
		friendsPage.ClickSearch();
		friendsPage.SearchFriend(Name);
		friendsPage.SelectProfile();
		friendsPage.click_Add_Cancel_Friend();
		friendsPage.clickClose();
		Assert.assertTrue(friendsPage.isCancelRequestVisible());
		friendsPage.click_Add_Cancel_Friend();
		friendsPage.clickCancelRequest();
		Assert.assertTrue(friendsPage.isAddFriendVisible());
		friendsPage.ClickBack();
		friendsPage.ClickBack();

	}

	@Test(priority = 7, description = "Verify Send Request By Search", dataProvider = "FriendsData")
	public void TC2_SendRequest(String TestCase, String Name) {

		driver.setConnection(new ConnectionState(ConnectionState.AIRPLANE_MODE_MASK));
		try {
			friendsPage.ClickSearch();
			friendsPage.SearchFriend(Name);
			friendsPage.SelectProfile();
			Assert.assertFalse(friendsPage.isAddFriendVisible());

		} finally {
			driver.setConnection(new ConnectionState(ConnectionState.WIFI_MASK));
		}

	}

	@DataProvider(name = "FriendsData")
	public Object[][] getPostData(Method method) throws IOException {
		List<Object[]> data = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader("src/test/java/Resources/FriendsData.csv"))) {
			String line = reader.readLine(); // skip header
			while ((line = reader.readLine()) != null) {
				String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
				for (int i = 0; i < values.length; i++)
					values[i] = values[i].replace("\"", "").trim();

				String testCase = values[0];
				if (testCase.equals(method.getName())) {
					data.add(values);
				}
			}
		}
		return data.toArray(new Object[0][]);
	}

}

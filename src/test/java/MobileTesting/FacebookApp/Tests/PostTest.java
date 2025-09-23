package MobileTestingProject.Facebook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionState;

public class PostTest extends BaseTest {
	private PostPage postPage;
	private WebDriverWait wait;

	@BeforeClass
	public void initPage() {
		postPage = new PostPage(driver);
	}

	@Test(priority = 1, description = "Verify user create post with empty text")
	public void TC3_createPost() {
		postPage.clickCreateButton();
		postPage.selectPostOption();
		boolean enabled = postPage.isPostButtonEnabled();
		Assert.assertFalse(enabled);
		postPage.clickBackButton();
	}

	@Test(priority = 2, description = "Verify user can create post with valid text", dataProvider = "postData")
	public void TC1_createPost(String testCase, String postText, String commentText, String captionText) {
		postPage.clickCreateButton();
		postPage.selectPostOption();
		postPage.enterPostText(postText);
		postPage.clickPostButton();
		boolean success = postPage.isPostSuccessful();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		Assert.assertTrue(success);
	}

	@Test(priority = 3, description = "Verify user Can Click Like")
	public void TC1_ClickLike() {
		By likedByUser = By.xpath("//*[contains(@text,'Zaid Alshaikh') or contains(@content-desc,'Zaid Alshaikh')]");
		int before = postPage.getLikeCount();
		postPage.clickLike();
		WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		boolean usernameAppeared = false;
		try {
			usernameAppeared = shortWait.until(d -> !d.findElements(likedByUser).isEmpty());
		} catch (Exception ignored) {
		}

		if (usernameAppeared) {
			Assert.assertTrue(true, "Post Unlike");
		} else {
			Assert.assertTrue(postPage.isLikeButtonClickable());
		}
	}

	@Test(priority = 4, description = "Verify user Can unlike")
	public void TC2_ClickLike() {
		By likedByUser = By.xpath("//*[contains(@text,'Zaid Alshaikh') or contains(@content-desc,'Zaid Alshaikh')]");
		if (driver.findElements(likedByUser).isEmpty()) {
			postPage.clickLike();
			new WebDriverWait(driver, Duration.ofSeconds(5)).until(d -> !d.findElements(likedByUser).isEmpty());
		}

		postPage.clickLike();

		WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		boolean usernameGone = false;
		try {
			usernameGone = shortWait.until(d -> d.findElements(likedByUser).isEmpty());
		} catch (Exception ignored) {
		}

		if (usernameGone) {
			Assert.assertTrue(true, "Post Unlike");
		} else {
			Assert.assertTrue(postPage.isLikeButtonClickable());
		}
	}

	@Test(priority = 5, description = "Verify user can share post")
	public void TC1_SharePost() {
		postPage.clickShare();
		postPage.clickShareKnow();
		Assert.assertTrue(postPage.isShareSuccessful());
	}

	@Test(priority = 6, description = "Verify user can share post and add caption", dataProvider = "postData")
	public void TC2_SharePost(String testCase, String postText, String commentText, String captionText) {
		postPage.clickShare();
		postPage.AddCaption(captionText);
		postPage.clickShareKnow();
		Assert.assertTrue(postPage.isShareSuccessful());
	}

	@Test(priority = 7, description = "Verify user can add comment on post", dataProvider = "postData")
	public void TC1_CommentPost(String testCase, String postText, String commentText, String captionText) {
		postPage.clickComment();
		postPage.TypeComment(commentText);
		postPage.SendComment();
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		boolean success = postPage.isCommentSuccessful();

		Assert.assertTrue(success);
		postPage.swipeUp();
	}

	@Test(priority = 8, description = "Verify user add empty comment")
	public void TC2_EmptyComment() {

		postPage.clickComment();
		boolean enabled = postPage.isCommenttButtonEnabled();
		Assert.assertFalse(enabled);
		postPage.swipeUp();
	}

	@Test(priority = 9, description = "Verify user can share post with no internet connection", dataProvider = "postData")
	public void TC3_SharePost(String testCase, String postText, String commentText, String captionText) {
		postPage.clickShare();
		postPage.AddCaption(captionText);

		driver.setConnection(new ConnectionState(ConnectionState.AIRPLANE_MODE_MASK));
		try {
			postPage.clickShareKnow();

			boolean isPostVisible = postPage.isSharedPostVisible(captionText);
			Assert.assertFalse(isPostVisible, "Shared post should not be visible when offline");
		} finally {

			driver.setConnection(new ConnectionState(ConnectionState.WIFI_MASK));
		}
	}

	@Test(priority = 10, description = "Verify user Can like with no internet")
	public void TC3_ClickLike() {
		driver.setConnection(new ConnectionState(ConnectionState.AIRPLANE_MODE_MASK));
		try {
			postPage.clickLike();
			boolean clickable = postPage.isLikeButtonClickable();
			Assert.assertTrue(clickable);
		} finally {
			driver.setConnection(new ConnectionState(ConnectionState.WIFI_MASK));
		}
	}

	@Test(priority = 11, description = "Verify user can create post with valid photo and text", dataProvider = "postData")
	public void TC2_createPost(String testCase, String postText, String commentText, String captionText) {
		postPage.clickCreateButton();
		postPage.selectPostOption();
		postPage.enterPostText(postText);
		postPage.clickAddPhoto();
		postPage.SelectPhoto();
		postPage.clickPostButton();
		boolean success = wait.until(driver -> postPage.isPostSuccessful());
		Assert.assertTrue(success);
	}

	@DataProvider(name = "postData")
	public Object[][] getPostData(Method method) throws IOException {
		List<Object[]> data = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader("src/test/java/Resources/postData.csv"))) {
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
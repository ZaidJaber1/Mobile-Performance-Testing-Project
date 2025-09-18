package MobileTesting.FacebookApp.Tests;

import MobileTesting.FacebookApp.Pages.PostPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PostTest extends BaseTest {

    private PostPage postPage;

    @BeforeClass
    public void initPage() {
        postPage = new PostPage(driver);
    }

    @Test(priority = 1)
    public void TC_createPost() {
        postPage.clickCreateButton();
        postPage.selectPostOption();
        postPage.enterPostText("Hello, this is a test post from Appium!");
        postPage.clickPostButton();
    }
}

package MobileTesting.FacebookApp.Tests;

import MobileTesting.FacebookApp.Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeClass
    public void initPage() {
        loginPage = new LoginPage(driver);
    }

    @Test(priority = 1)
    public void TC_invalidPassword() {
        loginPage.clickAlreadyHaveAccount();
        loginPage.enterEmail("eng.zaidjaber@gmail.com"); // valid email
        loginPage.enterPassword("wrongpassword");       // invalid password
        loginPage.clickLogin();
        loginPage.handleInvalidPassword();             // go back to login
        loginPage.clearFields();
    }

    @Test(priority = 2)
    public void TC_bothInvalid() {
        loginPage.enterEmail("invaliduser@example.com"); 
        loginPage.enterPassword("wrongpassword");      
        loginPage.clickLogin();
        loginPage.handleBothInvalid();                // click TRY AGAIN
        loginPage.clearFields();
    }

    @Test(priority = 3)
    public void TC_invalidEmail() {
        loginPage.enterEmail("invaliduser@example.com"); 
        loginPage.enterPassword("validpassword");      
        loginPage.clickLogin();
        loginPage.handleInvalidEmail();               // click OK
        loginPage.clearFields();
    }

    @Test(priority = 4)
    public void TC_validLogin() {
        loginPage.enterEmail("eng.zaidjaber@gmail.com");
        loginPage.enterPassword("Alshaikh@2000");
        loginPage.clickLogin();
        // assertion for home page
        Assert.assertTrue(loginPage.isHomePageDisplayed(), "Home page is not displayed, login failed!");
    }

}

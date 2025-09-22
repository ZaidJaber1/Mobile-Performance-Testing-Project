package MobileTesting.FacebookApp.Tests;

import MobileTesting.FacebookApp.Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeClass
    public void initPage() {
        loginPage = new LoginPage(driver);
        loginPage.clickAlreadyHaveAccount();
    }

    @DataProvider(name = "loginData")
    public Iterator<Object[]> loginDataProvider() throws IOException {
        String filePath = "src/test/Resources/loginData.csv";
        List<Object[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean headerSkipped = false;
            while ((line = br.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }
                String[] values = line.split(",");
                for (int counter = 0; counter < values.length; counter++) {
                    values[counter] = values[counter].replaceAll("^\"|\"$", "").trim();
                }
                if (values.length >= 4) {
                    data.add(new Object[]{values[0], values[1], values[2], values[3]});
                }
            }
        }
        return data.iterator();
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String testCase, String email, String password, String expectedResult) {
        loginPage.clearFields();
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLogin();


        
        switch (expectedResult.toLowerCase()) {
            case "empty_fields":
                loginPage.clickLogin();

                Assert.assertTrue(loginPage.isEmptyFieldsMsgDisplayed(),
                        "Error message for empty fields not displayed!");
                loginPage.clickEmptyFieldsOk();
                break;

            case "valid_email_empty_pass":
                loginPage.clickLogin();

                Assert.assertTrue(loginPage.isIncorrectPasswordDisplayed(),
                        "Error message for empty password not displayed!");
                loginPage.clickBackFromInvalidPassword();
                break;

            case "empty_email_valid_pass":
                Assert.assertTrue(loginPage.isEmptyFieldsMsgDisplayed(),
                        "Error message for empty email not displayed!");
                loginPage.clickEmptyFieldsOk();
                break;

            case "invalid_password":
                Assert.assertTrue(loginPage.isIncorrectPasswordBoxDisplayed(),
                        "Error message 'Incorrect Password' not displayed!");
                loginPage.clickIncorrectPasswordOk();
                break;

            case "both_invalid":
                Assert.assertTrue(loginPage.isBothInvalidMsgDisplayed(),
                        "Both invalid credentials message not displayed!");
                loginPage.clickBothInvalidTryAgain();
                break;

            case "invalid_email":
                Assert.assertTrue(loginPage.isBothInvalidMsgDisplayed(),
                        "Invalid email OK button not displayed!");
                loginPage.clickBothInvalidTryAgain();
                break;

            case "valid_login":
                Assert.assertTrue(loginPage.isHomePageDisplayed(),
                        "Home page is not displayed, login failed!");
                break;

            default:
                Assert.fail("Unexpected expectedResult: " + expectedResult);
        }
    }
}





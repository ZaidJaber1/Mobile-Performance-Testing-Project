package MobileTesting.FacebookApp.Tests;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {

	protected AndroidDriver<MobileElement> driver;

	@BeforeClass
	public void setUp() throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "16.0");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_9_API_36");

		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

		caps.setCapability("appPackage", "com.facebook.katana");
		caps.setCapability("appActivity", "com.facebook.katana.LoginActivity");
		caps.setCapability("noReset", true);

		driver = new AndroidDriver<>(new URL("http:/localhost:4723/wd/hub"), caps);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}

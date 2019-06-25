import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class SignUpPageTest {

    WebDriver driver;
    SignUpPage signUpPage;


    @BeforeTest
    @Parameters({"browser"})
    public void setUp(String browser){
        driver = WebDriverFactory.createDriver(browser);
        driver.get("https://github.com/join");
        signUpPage = new SignUpPage(driver);
    }

    @Test (priority = 1)
    @Description("Verify username validation")
    public void signUpReservedUsernameTest(){
        SignUpPage sp = signUpPage.typeUserName("username");
        String error = sp.getUsernameErrorText();
        Assert.assertEquals(error, "Username 'username' is unavailable.");
    }

    @Test (priority = 2)
    @Description("Verify email validation")
    public void signUpInvalidEmail(){
        SignUpPage sp = signUpPage.typeEmail("test");
        String error = sp.getEmailErrorText();
        Assert.assertEquals(error, "Email is invalid or already taken");
    }

    @Test (priority = 3)
    @Description("Verify password validation")
    public void signUpWithShortPass(){
        SignUpPage sp = signUpPage.typePassword("tst");
        String error = sp.getPasswordErrorText();
        Assert.assertEquals(error, "Make sure it's at least 15 characters OR at least 8 characters including a number and a lowercase letter. Learn more.");
    }

    @Test (priority = 4)
    @Description("Verify disabling the createAccount button")
    public void isDisabledButton(){
        Assert.assertFalse(signUpPage.isEnabled());
    }

    @AfterTest
    public void tearDown(){
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] takeScreenshot(WebDriver driver){
        TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
        return takesScreenshot.getScreenshotAs(OutputType.BYTES);
    }
    @AfterMethod
    public void screenShot(ITestResult result){
        if (!result.isSuccess()) {
            takeScreenshot(driver);
        }
    }
}

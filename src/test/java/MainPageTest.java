import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class MainPageTest {

    private WebDriver driver;
    private MainPage mainPage;

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(String browser){
        driver = WebDriverFactory.createDriver(browser);
        driver.get("http://github.com");
        mainPage = new MainPage(driver);
    }

//    @Test (priority = 1)
//    @Description("Verify signIn link")
//    public void signInTest(){
//        LoginPage loginPage = mainPage.clickSignIn();
//        String heading = loginPage.getHeadingText();
//        Assert.assertEquals("Sign in to GitHub", heading);
//    }
//
//    @Test (priority = 2)
//    @Description("Verify signUp link")
//    public void signUpTest(){
//        SignUpPage signUpPage =  mainPage.clickSignUp();
//        String heading = signUpPage.getHeadingText();
//        Assert.assertEquals("Join GitHub", heading);
//    }
//
    @Test
    @Description("Verify signIn with invalid data")
    public void signUpWithInvalidData(){
        mainPage.registerInv("test", "test", "test");
        String usernameErrorText = mainPage.getUserError();
        Assert.assertEquals(usernameErrorText, "Username test is already taken.");
        String emailErrorText = mainPage.getEmailError();
        Assert.assertEquals(emailErrorText, "Email is invalid or already taken");

    }
    @Test
    @Description("Verify signIn with empty form")
    public void signUpEmptyCreds(){
        SignUpPage signUpPage = mainPage.register("", "", "");
        String mainErrorText = signUpPage.getMainErrorText();
        Assert.assertEquals(mainErrorText, "There were problems creating your account.");
        String usernameEmErrorText = signUpPage.getEmptyUsernameErrorText();
        Assert.assertEquals(usernameEmErrorText, "Username can't be blank");
        String emailEmErrorText = signUpPage.getEmptyEmailErrorText();
        Assert.assertEquals(emailEmErrorText, "Email can't be blank");
        String passwordEmErrorText = signUpPage.getEmptyPasswordErrorText();
        Assert.assertEquals(passwordEmErrorText, "Password can't be blank");
    }

    @AfterMethod
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

import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;


public class LoginPageTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(String browser){
        driver = WebDriverFactory.createDriver(browser);
        driver.get("https://github.com/login");
        loginPage = new LoginPage(driver) ;
    }

    @AfterMethod
    public void tearDown(){
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    @Test(dataProvider = "data")
    @Description("Login with incorrect credentials")
    public void loginWithIncorrectCreds(String name, String password){
        LoginPage newLoginPage =  loginPage.loginWithInvalidCreds(name,password);
        String error = newLoginPage.getErrorText();
        Assert.assertEquals( error, "Incorrect username or password.");
    }

    @DataProvider(name = "data")
    public Object [][] getTestData(){
        return new Object[][]{
                {"test", "test"},
                {"", "test"},
                {"test", ""},
                {"", ""},
        };
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

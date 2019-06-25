
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class UserPageTest {

    private WebDriver driver;
    private UserPage userPage;
    private LoginPage loginPage;

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(String browser){
        driver = WebDriverFactory.createDriver(browser);
        driver.get("https://github.com/login");
        loginPage = new LoginPage(driver);
        loginPage.loginWithValidCreds(PropertyLoader.loadProperty("data.username"), PropertyLoader.loadProperty("data.password"));
        userPage = new UserPage(driver);
    }

    @AfterMethod
    public void tearDown(){
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    @Test (priority = 1)
    @Description ("Login with correct credentials")
    public void loginWithСorrectCreds(){
        String textHeader = userPage.getHeader();
        Assert.assertEquals(textHeader, "Learn Git and GitHub without any code!");
    }

    @Test (priority = 2)
    @Description("Check the user name")
    public void verifyUserName(){

        UserPage newUserPage = userPage.showDropDown();
        String userName = newUserPage.getUserName();
        Assert.assertEquals(userName, "derzhylo");

    }

    @Test (priority = 3)
    @Description("Verify signOut")
    public void verifySignOut(){
       MainPage newMainPage = userPage.SignOut();
        String header = newMainPage.getHeader();
        Assert.assertEquals(header, "Built for developers");
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

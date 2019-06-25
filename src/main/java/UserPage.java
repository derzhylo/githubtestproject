import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserPage {

    private WebDriver driver;
    private WebDriverWait explicitlyWait;

    @FindBy(xpath = "//h2[text()='Learn Git and GitHub without any code!']")
    private WebElement userPageHeader;

    @FindBy(xpath = "//strong[text()='derzhylo']")
    private WebElement userName;

    @FindBy(xpath = "//span[@class='dropdown-caret']/preceding-sibling::img")
    private WebElement userDropDown;

    @FindBy(xpath = "//button[@class='dropdown-item dropdown-signout']")
    private WebElement userSignOut;


    public UserPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    @Step
    public UserPage showDropDown(){
        userDropDown.click();
        return this;
    }

    public String getUserName(){
        explicitlyWait = new WebDriverWait(driver, 10);
        explicitlyWait.until(ExpectedConditions.visibilityOf(userName));
        return userName.getText();
    }

    public String getHeader(){
        return userPageHeader.getText();
    }

    public MainPage SignOut(){
       userDropDown.click();
       userSignOut.click();
       return new MainPage(driver);
    }
}

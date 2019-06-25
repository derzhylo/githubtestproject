
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;

    @FindBy(xpath = "//input[@id='login_field']")
    private WebElement loginField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement signInButton;

    @FindBy(xpath = "//h1[text() = 'Sign in to GitHub']")
    private WebElement heading;

    @FindBy(xpath = "//div[@class='container']")
    private WebElement error;


    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @Step("Verify username value {username}")
    public LoginPage typeUsername(String username) {
        loginField.sendKeys(username);
        return this;
    }

    @Step("Verify password value {password}")
    public LoginPage typePassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public LoginPage loginWithInvalidCreds(String username, String password) {
        this.typeUsername(username);
        this.typePassword(password);
        signInButton.click();
        return new LoginPage(driver);
    }

    public UserPage loginWithValidCreds(String username, String password) {
        this.typeUsername(username);
        this.typePassword(password);
        signInButton.click();
        return new UserPage(driver);
    }

    public String getHeadingText() {
        return heading.getText();
    }

    public String getErrorText() {
        return error.getText();
    }

}






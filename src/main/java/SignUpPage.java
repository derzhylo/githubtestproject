import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage {

    WebDriver driver;
    private WebDriverWait explicitlyWait;

    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;

    }

    @FindBy(xpath = "//h1[text() = 'Join GitHub']")
    private WebElement heading;

    @FindBy(xpath = "//input[@id='user_login']")
    private WebElement userNameField;

    @FindBy(xpath = "//input[@id='user_email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id='user_password']")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id='signup_button']")
    private WebElement createAccountButton;

    @FindBy(xpath = "//div[@class='flash flash-error my-3']")
    private WebElement mainError;

    @FindBy(xpath = "//*[text() = \"Username can't be blank\"]")
    private WebElement userEmptyNameError;

    @FindBy(xpath = "//*[text() = \"Email can't be blank\"]")
    private WebElement emailEmptyError;

    @FindBy(xpath = "//*[text() = \"Password can't be blank\"]")
    private WebElement passwordEmptyError;

    @FindBy(xpath = "//div[@class='mb-1']")
    private WebElement userNameError;

    @FindBy(xpath = "//p[text()='Email is invalid or already taken']")
    private WebElement emailError;

    @FindBy(xpath = "//p[@class='note mb-3']")
    private WebElement passwordError;


    @Step("Verify username value {username}")
    public SignUpPage typeUserName(String username){
        userNameField.sendKeys(username);
        return this;
    }
    @Step("Verify password value {password}")
    public SignUpPage typePassword(String password){
        passwordField.sendKeys(password);
        return this;
    }
    @Step("Verify email value {email}")
    public SignUpPage typeEmail(String email){
        emailField.sendKeys(email);
        return this;
    }
    public Boolean isEnabled(){
        return createAccountButton.isEnabled();
    }

    public String getHeadingText(){
        return heading.getText();
    }

    public String getMainErrorText(){
        return mainError.getText();
    }

    public String getUsernameErrorText(){
        explicitlyWait = new WebDriverWait(driver, 10);
        explicitlyWait.until(ExpectedConditions.visibilityOf(userNameError));
        return userNameError.getText();
    }

    public String getPasswordErrorText(){
        return passwordError.getText();
    }

    public String getEmailErrorText(){
        explicitlyWait = new WebDriverWait(driver, 10);
        explicitlyWait.until(ExpectedConditions.visibilityOf(emailError));
        return emailError.getText();
    }

    public String getEmptyUsernameErrorText(){
        return userEmptyNameError.getText();
    }

    public String getEmptyPasswordErrorText(){
        return passwordEmptyError.getText();
    }

    public String getEmptyEmailErrorText(){
        return emailEmptyError.getText();
    }

}

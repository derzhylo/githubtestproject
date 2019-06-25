
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait explicitlyWait;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//a[@href='/login']")
    private WebElement signInButton;
    @FindBy(xpath = "//a[@href='/login']/following-sibling::a")
    private WebElement signUpButton;
    @FindBy(xpath = "//*[@id='user[login]']")
    private WebElement userNameField;
    @FindBy(xpath = "//*[@id='user[email]']")
    private WebElement emailField;
    @FindBy(xpath = "//*[@id='user[password]']")
    private WebElement passwordField;
    @FindBy(xpath = "//button[text()='Sign up for GitHub']")
    private WebElement signUpFormButton;
    @FindBy(xpath = "//h1[text()='Built for developers']")
    private WebElement mainHeader;
    @FindBy(xpath = "//div[@class='mb-1']")
    private WebElement userNameError;
    @FindBy(xpath = "//dd[text()='Email is invalid or already taken']")
    private WebElement emailError;
    @FindBy(xpath = "//*[@invalid-message = 'Password is invalid.']")
    private WebElement passwordError;


    public String getHeader(){
        return mainHeader.getText();
    }

    public LoginPage clickSignIn(){
        signInButton.click();
        return new LoginPage(driver);
    }

    public SignUpPage clickSignUp(){
        explicitlyWait = new WebDriverWait(driver, 10);
        explicitlyWait.until(ExpectedConditions.visibilityOf(signUpButton));
        signUpButton.click();
        return new SignUpPage(driver);
    }

    public SignUpPage clickSignUpFormButton(){
        signUpFormButton.click();
        return new SignUpPage(driver);
    }
    @Step("Verify username value {username}")
    public MainPage typeUserName(String username){
        userNameField.sendKeys(username);
        return this;
    }
    @Step("Verify password value {password}")
    public MainPage typePassword(String password){
        passwordField.sendKeys(password);
        return this;
    }
    @Step("Verify email value {email}")
    public MainPage typeEmail(String email){
        emailField.sendKeys(email);
        return this;
    }

    public SignUpPage register(String username, String email, String password){
        this.typeUserName(username);
        this.typeEmail(email);
        this.typePassword(password);
        this.clickSignUpFormButton();
        return new SignUpPage(driver);
    }
    public MainPage registerInv(String username, String email, String password){
        this.typeUserName(username);
        this.typeEmail(email);
        this.typePassword(password);
        this.clickSignUpFormButton();
        return new MainPage(driver);
    }

    public String getUserError(){
        explicitlyWait = new WebDriverWait(driver, 10);
        explicitlyWait.until(ExpectedConditions.visibilityOf(userNameError));
        return userNameError.getText();
    }
    public String getEmailError(){
        explicitlyWait = new WebDriverWait(driver, 10);
        explicitlyWait.until(ExpectedConditions.visibilityOf(emailError));
        return emailError.getText();
    }
    public String getPassError(){
        explicitlyWait = new WebDriverWait(driver, 10);
        explicitlyWait.until(ExpectedConditions.visibilityOf(passwordError));
        return passwordError.getText();
    }

}

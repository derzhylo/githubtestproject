import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
    public static WebDriver createDriver(String browser) {
        WebDriver driver;
        switch (browser) {
            case "chrome":
                driver = createChromeDriver();
                break;
            case "firefox":

                driver = createFirefoxDriver();
                break;

            default:
                throw new IllegalArgumentException(String.format(
                        "Browser %s is not supported", browser));
        }
        driver.manage().window().maximize();
        return driver;
    }


    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("headless");
        chromeOptions.addArguments("window-size=1920x1080");
        return new ChromeDriver();
    }

    private static WebDriver createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

}

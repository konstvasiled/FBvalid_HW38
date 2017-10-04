package core;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.support.ui.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;

public class Firefox {
    static WebDriver driver;
    public static void main(String[] args) throws InterruptedException {
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.OFF);

        String driverPath = "";

        String url = "https://www.facebook.com/";
        String email_add = "konstrevan@gmail.com";
        String password = "";

        if (System.getProperty("os.name").toUpperCase().contains("MAC")) {
            driverPath = "./resources/webdriver/mac/geckodriver.sh";
        }
        else if (System.getProperty("os.name").toUpperCase().contains("WINDOWS")) {
            driverPath = "./resources/webdriver/pc/geckodriver.exe";
        }
        else {throw new IllegalArgumentException("Unknown OS");}

        System.setProperty("webdriver.gecko.driver", driverPath);
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        WebDriverWait delay = new WebDriverWait(driver, 15);
        driver.get(url);

        String title = driver.getTitle();
        String copyright = delay.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[3]/div/span"))).getText();
        delay.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).clear();
        delay.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).sendKeys(email_add);
        delay.until(ExpectedConditions.presenceOfElementLocated(By.id("pass"))).clear();
        delay.until(ExpectedConditions.presenceOfElementLocated(By.id("pass"))).sendKeys(password);
        delay.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label/input"))).click();

        delay.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[1]/div/div/div/div[2]/div[1]/div[1]/div/a/span/span"))).click();
        String friendcount = delay.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[2]/ul/li[3]/a/span[1]"))).getText();
        while (friendcount == null) {
            friendcount = delay.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[2]/ul/li[3]/a/span[1]"))).getText(); }
        delay.until(ExpectedConditions.presenceOfElementLocated(By.id("userNavigationLabel"))).click();
        delay.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[14]/a/span/span"))).click();
        driver.quit();

        System.out.println("Your browser is: Firefox");
        System.out.println("Title of the page is: " + title);
        System.out.println("Copyright: " + copyright);
        System.out.println("You have " + friendcount + " friends.");
    }
}
package core;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.*;
import java.util.concurrent.TimeUnit;

public class Chrome {
    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.OFF);

        String driverPath = "";

        String url = "https://www.facebook.com/";
        String email_add = "konstrevan@gmail.com";
        String password = "";

        if (System.getProperty("os.name").toUpperCase().contains("MAC")) {
            driverPath = "./resources/webdriver/mac/chromedriver";}
        else if (System.getProperty("os.name").toUpperCase().contains("WINDOWS")) {
            driverPath = "/resources/webdriver/pc/chromedriver.exe";}
        else {throw new IllegalArgumentException("Unknown OS");}

        System.setProperty("webdriver.chrome.driver", driverPath);
        System.setProperty("webdriver.chrome.silentOutput", "true");
        ChromeOptions option = new ChromeOptions();
        option.addArguments("disaple-infobars");
        option.addArguments("--disbale-notifications");
        if (System.getProperty("os.name").toUpperCase().contains("MAC"))
            option.addArguments("-start-fullscreen");
        else if (System.getProperty("os.name").toUpperCase().contains("WINDOWS"))
            option.addArguments("--start-maximized");
        else throw new IllegalArgumentException("Unknown OS");
        driver = new ChromeDriver(option);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebDriverWait delay = new WebDriverWait(driver, 15);
        driver.get(url);

        String title = driver.getTitle();
        String copyright = delay.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[3]/div/span"))).getText();

        delay.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).clear();
        delay.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).sendKeys(email_add);
        delay.until(ExpectedConditions.presenceOfElementLocated(By.id("pass"))).clear();
        delay.until(ExpectedConditions.presenceOfElementLocated(By.id("pass"))).sendKeys(password);
        delay.until(ExpectedConditions.presenceOfElementLocated(By.id("loginbutton"))).click();

        delay.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='u_0_b']/div[1]/div[1]/div/a/span/span"))).click();
        String freindscount = delay.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[3]/a[1]/span[1]"))).getText();
        while (freindscount == null) {
            freindscount = delay.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[3]/a[1]/span[1]"))).getText(); }
        delay.until(ExpectedConditions.presenceOfElementLocated(By.id("userNavigationLabel"))).click();
        delay.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Log Out"))).click();
        driver.quit();

        System.out.println("Your browser is: Chrome");
        System.out.println("Title of the page is:" + title);
        System.out.println("Copyright:" + copyright);
        System.out.println("You have " + freindscount + " friends.");
    }
}

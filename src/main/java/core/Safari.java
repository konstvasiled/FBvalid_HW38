package core;

import org.openqa.selenium.*;
import org.openqa.selenium.safari.*;
import org.openqa.selenium.support.ui.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;

public class Safari {
    static WebDriver driver;
    public static void main(String[] args) throws InterruptedException {
        Logger.getLogger("").setLevel(Level.OFF);

        String url = "https://www.facebook.com/login/";
        String email_add = "konstrevan@gmail.com";
        String password = "";

        if (!System.getProperty("os.name").toUpperCase().contains("MAC")) {
            throw new IllegalArgumentException("Safari is only available on Mac");
        }
        driver = new SafariDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        WebDriverWait delay = new WebDriverWait(driver, 15);
        driver.get(url);

        String title = driver.getTitle();
        String copyright = delay.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='pageFooter']/div[3]/div/span"))).getText();

        delay.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).clear();
        delay.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).sendKeys(email_add);
        delay.until(ExpectedConditions.presenceOfElementLocated(By.id("pass"))).clear();
        delay.until(ExpectedConditions.presenceOfElementLocated(By.id("pass"))).sendKeys(password);
        delay.until(ExpectedConditions.presenceOfElementLocated(By.id("loginbutton"))).click();
        delay.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='u_0_b']/div[1]/div[1]/div/a/span/span"))).click();
        String friendcount = driver.findElement(By.xpath("//li[3]/a/span[1]")).getText();
        while (friendcount == null) {
            friendcount = delay.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[2]/ul/li[3]/a/span[1]"))).getText(); }

        WebElement settings = driver.findElement(By.id("userNavigationLabel"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", settings);

        WebElement logout = driver.findElement(By.xpath("//li[14]/a/span/span"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logout);

        driver.quit();

        System.out.println("Your browser is Safari");
        System.out.println("Page title is: " + title);
        System.out.println("Copyright:" + copyright);
        System.out.println("You have " + friendcount + " friends.");
    }
}

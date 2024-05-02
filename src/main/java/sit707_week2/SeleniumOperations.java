package sit707_week2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import org.apache.commons.io.FileUtils;

public class SeleniumOperations {

    public static void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void bunnings_registration_page(String url) {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        testValidLogin(driver, url);
        testInvalidLogin(driver, url);

        sleep(2);

        driver.quit();
    }

    public static void testValidLogin(WebDriver driver, String url) {
        driver.get(url);

        WebElement usernameField = driver.findElement(By.id("okta-signin-username"));
        WebElement passwordField = driver.findElement(By.id("okta-signin-password"));

        usernameField.sendKeys("dgopleshbhai@gmail.com");
        passwordField.sendKeys("Dhruv@2002");

        passwordField.submit();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        takeScreenshot(driver, "valid_login");
      
        assertTrue(driver.getCurrentUrl().startsWith("https://www.bunnings.com.au/login"));
        
    }

    private static void assertTrue(boolean startsWith) {
		// TODO Auto-generated method stub
		
	}

	public static void testInvalidLogin(WebDriver driver, String url) {
        driver.get(url);

        WebElement usernameField = driver.findElement(By.id("okta-signin-username"));
        WebElement passwordField = driver.findElement(By.id("okta-signin-password"));

        usernameField.sendKeys("dgopleshbhai@gmail.com");
        passwordField.sendKeys("Dhruv$2002");

        passwordField.submit();

       
        takeScreenshot(driver, "invalid_login");
        

    }

    public static void takeScreenshot(WebDriver driver, String screenshotName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        try {
            String destinationPath = "path/to/screenshots/" + screenshotName + ".png";
            FileUtils.copyFile(source, new File(destinationPath));
            System.out.println("Screenshot taken: " + destinationPath);
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
        }
    }
}

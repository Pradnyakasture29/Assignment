package JavaSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SqlInjectionTest 
{
	@Test
	public void login_Juice_Shop()
	{
        // Set the path to your chromedriver executable
        System.setProperty("webdriver.chrome.ChromeDriver", "C:\\\\Users\\\\PRADNYA\\\\Downloads\\\\chrome-win32\\\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("https://juice-shop.herokuapp.com/#/login");

        // Locate username and password input fields
        WebElement usernameField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("loginButton"));

        // Craft a malicious username string for SQL injection
        String maliciousUsername = "pradnyakasture@gmail.com";
        String validPassword = "12345";

        // Enter crafted username and valid password
        usernameField.sendKeys(maliciousUsername);
        passwordField.sendKeys(validPassword);
        
        loginButton.click();

        try 
        {
            Thread.sleep(2000);
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }

        // Check for error message indicating failed SQLi attempt
        WebElement errorMessage = driver.findElement(By.className("error"));
        if (errorMessage.isDisplayed()) 
        {
            System.out.println("SQL Injection attempt detected!");
        } else 
        {
            System.out.println("No SQL Injection detected.");
        }

        driver.quit();
    }
}

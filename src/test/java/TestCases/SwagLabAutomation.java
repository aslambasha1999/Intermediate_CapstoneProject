package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Base.BaseClass;

public class SwagLabAutomation extends BaseClass{

	
    @Test(priority = 1)
    @Parameters({"validUsername", "validPassword"})
    public void validLoginTest(String username, String password) {
    	
    	
    	// Verifying the page title before attempting to log in
    	verifyTitle();
    	
    	// Attempting to log in with the provided username and password
        loginToSauceDemo(username, password);
        
        //Assert user is logged successfully
        Assert.assertTrue(isUserLoggedIn(), "User should be logged in with valid credentials.");
        
        // Capture a screenshot for the valid login scenario
        captureScreenshot("ValidLogin");
        
        //Click Open Menu
        openMenu();
        
        //Click Logout
        clickLogout();
        
    }

    @Test(priority = 2)
    @Parameters({"invalidUsername", "invalidPassword"})
    public void invalidLoginTest(String username, String password) {
    	
    	// Verifying the page title before attempting to log in
    	verifyTitle();
    	
    	// Attempting to log in with the provided username and password
        loginToSauceDemo(username, password);
        
        //Assert user is login failed and error displayed
        Assert.assertTrue(isErrorDisplayed(), "Error should be displayed for invalid credentials.");
        
        // Capture a screenshot for the invalid login scenario
        captureScreenshot("InvalidLogin");
    }
    
    
    
    // Method to verify the page title is as expected
    public void verifyTitle() {
    	
    	 // Expected and actual title comparison
        String expectedTitle = "Swag Labs";
        String actualTitle = driver.getTitle();
    
        // Assertion to check if the actual title matches the expected title
        Assert.assertEquals(actualTitle, expectedTitle, "Title did not match!");
        
    }

    public void loginToSauceDemo(String username, String password) {
    	
    	 // Locate username and password fields and the login button
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));
        
        // Clear the fields and input the username and password, then click the login button
        usernameField.clear();
        usernameField.sendKeys(username);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.click();
    }
    

    // Method to check if the user is logged in successfully
    public boolean isUserLoggedIn() {
        // Check for a known element only available when logged in (e.g., product page)
        return driver.getCurrentUrl().contains("inventory.html");
    }
    
    
    // Method to check if an error message is displayed after a failed login attempt
    public boolean isErrorDisplayed() {
        // Check if error message is displayed
        return driver.findElement(By.cssSelector("[data-test='error']")).isDisplayed();
    }
    
    
    //Method to open the menu (used in logout sequence if needed)
    public void openMenu()
    {
    	WebElement sideMenu=driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']"));
    	sideMenu.click();	
    }
    
    
    //Method to click the logout button (used in logout sequence if needed)
    public void clickLogout()
    {
    	WebElement logoutBtn=driver.findElement(By.cssSelector("#logout_sidebar_link"));
    	logoutBtn.click();
    }

}

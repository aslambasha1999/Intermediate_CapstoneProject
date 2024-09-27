package Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
    public WebDriver driver;
    public String url;

    @BeforeSuite
    public void setUpSuite() throws FileNotFoundException, IOException {
    	
    	Properties prop=new Properties();
    	prop.load(new FileInputStream("config.properties"));
    	
    	// Setting up ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        
        // Initializing the URL at the suite level from config.prop file
        url=prop.getProperty("url");
   
    }

 // This method runs before each test to set up a new WebDriver instance.
    @BeforeMethod
    public void setUp() {
        // Initialize WebDriver for each test
        
        driver=new ChromeDriver();
        
        //opening URL
        driver.get(url);
        
        //Maximizing Window
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    // This method runs after each test to close the WebDriver instance.
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    
 // Generic function to capture screenshots and save them to the "screenshots" folder
    public void captureScreenshot(String screenshotName) {
    	
    	
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String timestamp = new SimpleDateFormat("yyyy_MM_ddHHmmss").format(new Date());
            String screenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+screenshotName+"_"+timestamp+".png";
            FileUtils.copyFile(screenshot, new File(screenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

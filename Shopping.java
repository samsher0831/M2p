package demowebshop;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Shopping {


    WebDriver driver; 

    
    @BeforeMethod
    public void setup() {
        
        driver = new ChromeDriver();
         
        driver.manage().window().maximize(); 
    }

    
    @Test
    public void registerUser() throws InterruptedException {
       driver.get("https://demowebshop.tricentis.com/register"); 

        driver.findElement(By.xpath("//input[@id='gender-male']")).click();

        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("samsher"); 
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("singh");
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("samthakur31@gmail.com"); 
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("Farishta123"); 
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("Farishta123");

        driver.findElement(By.xpath("//input[@id='register-button']")).click(); 
       
        String message = driver.findElement(By.className("result")).getText();

        
        if (message.contains("completed")) {
            System.out.println("Registration successful."); 
        } else {
            System.out.println("Registration failed.");
        }

        Thread.sleep(2000); 
    }
    @Test
    public void registerWithEmptyFields() {
        driver.get("https://demowebshop.tricentis.com/register"); 

        driver.findElement(By.xpath("//input[@id='register-button']")).click();
        boolean firstNameError = driver.findElement(By.id("FirstName-error")).isDisplayed();
        boolean lastNameError = driver.findElement(By.id("LastName-error")).isDisplayed();

        if (firstNameError && lastNameError) {
            System.out.println("Error messages displayed correctly."); 
        } else {
            System.out.println("Error messages not shown."); 
        }
    }

    
    @Test
    public void searchAndAddToCart() throws InterruptedException {
        driver.get("https://demowebshop.tricentis.com"); 
        driver.findElement(By.id("small-searchterms")).sendKeys("Laptop"); 
        driver.findElement(By.cssSelector("input[value='Search']")).click(); 

        driver.findElement(By.cssSelector(".product-title a")).click(); 

        driver.findElement(By.id("add-to-cart-button-31")).click(); 
        Thread.sleep(2000); 

        String cartMessage = driver.findElement(By.className("content")).getText(); 

        if (cartMessage.contains("added")) {
            System.out.println("Product added to cart."); 
        } else {
            System.out.println("Failed to add product."); 
        }
    }


    @Test
    public void validateSeleniumTitle() {
        driver.get("https://www.selenium.dev"); 

        if (driver.getTitle().contains("Selenium")) {
            System.out.println("Title contains 'Selenium'."); 
        } else {
            System.out.println("Incorrect title."); 
        }
    }

   
    @Test
    public void downloadsLinkValidation() {
        driver.get("https://www.selenium.dev"); 

        driver.findElement(By.xpath("//a[normalize-space()='Downloads']")).click(); 

        if (driver.getCurrentUrl().contains("/downloads")) {
            System.out.println("URL contains '/downloads'."); 
        } else {
            System.out.println("Downloads URL is incorrect."); 
        }
    }

    
    @Test
    public void handleAlert() {
        driver.get("https://demoqa.com/alerts"); 

        driver.findElement(By.id("alertButton")).click(); 

        Alert alert = driver.switchTo().alert(); 

        if (alert.getText().equals("You clicked a button")) {
            System.out.println("Alert text matched.");
        } else {
            System.out.println("Alert text mismatch.");
        }

        alert.accept(); 
    }

    
    @Test
    public void switchToIframe() {
        driver.get("https://demoqa.com/frames"); 

        driver.switchTo().frame("frame1"); 

        String heading = driver.findElement(By.id("sampleHeading")).getText(); 
        if (heading.equals("This is a sample page")) {
            System.out.println("Iframe text matched."); 
        } else {
            System.out.println("Iframe text mismatch.");
        }

        driver.switchTo().defaultContent(); 
    }

    
    @Test
    public void handleMultipleWindows() {
        driver.get("https://the-internet.herokuapp.com/windows"); 
        String parent = driver.getWindowHandle(); 

        driver.findElement(By.linkText("Click Here")).click(); 
        for (String window : driver.getWindowHandles()) {
            if (!window.equals(parent)) {
                driver.switchTo().window(window); 
            }
        }

        if (driver.getTitle().contains("New Window")) {
            System.out.println("New window title matched."); 
        } else {
            System.out.println("Window title incorrect."); 
        }
    }

    
    @Test
    public void scrollToBottom() throws InterruptedException {
        driver.get("https://www.selenium.dev"); 
        JavascriptExecutor js = (JavascriptExecutor) driver; 

        Long beforeScroll = (Long) js.executeScript("return window.pageYOffset;"); 

        js.executeScript("window.scrollTo(0, document.body.scrollHeight);"); 
        Thread.sleep(1000); 
        Long afterScroll = (Long) js.executeScript("return window.pageYOffset;"); 
        if (!beforeScroll.equals(afterScroll)) {
            System.out.println("Page scrolled successfully."); 
        } else {
            System.out.println("Scroll failed."); 
        }
    }

    
    @AfterMethod
    public void teardown() {
        driver.quit(); 

	
}
}

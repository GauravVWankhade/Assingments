package flipkart;

import java.time.Duration;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import net.bytebuddy.utility.RandomString;

public class PlaceOrder {

	WebDriver driver;
	
	@BeforeMethod
	public void setSystemProperty() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.get("https://www.flipkart.com");
	}
	@Test
	public void placeOrder() throws InterruptedException {
		// To get window handle
		String parentWindow = driver.getWindowHandle();
		
		// To close the pop-up
		driver.findElement(By.xpath("//button[text()='✕']")).click();
		Thread.sleep(2000);
		
		// To type "ipad" in search field
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("ipad");
		Thread.sleep(2000);
		
		// To click on search button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(2000);
			
		// Click on first link from all results
		driver.findElement(By.xpath("//div[@class='_4rR01T']")).click();
		Thread.sleep(2000);

		// Get all window handles
		Set<String> windowHandles =driver.getWindowHandles();
		Iterator<String> ITR = windowHandles.iterator();

		// To convert Set<string> to Array 
		String childWindow[] = new String[2];
		while(ITR.hasNext()) {
			int i=0;
			childWindow[i] = ITR.next();
			if(!parentWindow.equals(childWindow[i])) {
				driver.switchTo().window(childWindow[i]);
			i++;
			}
		}
		
		// Add to product to cart
		driver.findElement(By.xpath("//button[text()='Add to cart']")).click();
		Thread.sleep(2000);
		
		// Place the order
		driver.findElement(By.xpath("//span[text()='Place Order']")).click();
		Thread.sleep(2000);
		
		// Enter random email
		Random RD = new Random();
		String randomName = RandomString.make(5);
		int randomNumber = RD.nextInt(3);
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(randomName+randomNumber+"@gamil.com");
		Thread.sleep(2000);
		
		// Click on continue
		driver.findElement(By.xpath("//span[text()='CONTINUE']")).click();
		Thread.sleep(2000);
		
		WebElement mobileNumberField = driver.findElement(By.xpath("//span[text()='Enter OTP']"));
		if(mobileNumberField.isDisplayed()) {
			System.out.println("Test Case passed");
		}
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
		
	}
}

package AlmosaferWeb;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyTestCases {
	
	String TheWebSite = "https://www.almosafer.com/en";
	WebDriver driver = new ChromeDriver();
	SoftAssert softAssert = new SoftAssert();
	
	@BeforeTest
	public void setup() {
		driver.get(TheWebSite);
		driver.manage().window().maximize();
		WebElement WelcomeScreen = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div/button[1]"));
		WelcomeScreen.click();
		
		//assert all 
		
	}	
	
	@Test(enabled = false)
	public void checkTheLanguage() {
		
		String ActualLang = driver.findElement(By.tagName("html")).getAttribute("lang");
		String ExpectedLang = "en";
		softAssert.assertEquals(ActualLang, ExpectedLang, "This is to check language" );
	}
	
	@Test (enabled = false)
	public void checkTheCurrency() {
		
		WebElement Currency = driver.findElement(By.cssSelector(".sc-hUfwpO.kAhsZG"));
		
		String ActualCurrency = Currency.getText();
		String ExpectedCurrency = "SAR";
		
		softAssert.assertEquals(ActualCurrency, ExpectedCurrency, "This is to check Currency" );
		
	}
	
	
	@Test (enabled = false)
	public void checkTheContactNumber() {
		
		WebElement ContactNumer =  driver.findElement(By.tagName("strong"));
		
		String ActualContactNumer = ContactNumer.getText();
		String ExpectedContactNumer = "+966554400000";
		
		softAssert.assertEquals(ActualContactNumer, ExpectedContactNumer, "This is to check Contact Numer" );
		
	}
	
	
	@Test (enabled = false)
	public void checkLogo() {
		
		WebElement  QitafLogo = driver.findElement(By.xpath("//*[name()='g' and contains(@transform,'translate(')]//*[name()='g' and contains(@stroke-width,'1')]"));
		
		boolean isExist = QitafLogo.isDisplayed();
		boolean expectedLogo = true;
		
		softAssert.assertEquals(isExist, expectedLogo, "This is to check Qitaf Logo" );
		
	}
	
	@Test (enabled = false)
	public void checkHotelStatus() {
		
		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		
		boolean ActualTabStatus = HotelTab.isSelected();
		boolean ExpectedlTabStatus = false;
		

		/*
		 * The function could be work by 
		 * 
		 * String ActualTabStatus = HotelTab.getAttribute("aria-selected");
		 * String ExpectedlTabStatus = false;		
		 */
		
		softAssert.assertEquals(ActualTabStatus,ExpectedlTabStatus,"This is to check Hotel tab status");
		
	}
	
	
	//on the following function, the hard assert will be applied, because Departure Date and Arrival date depend on each other.
	@Test (enabled = true)
	public void checkDepartureAndArrivalDate() {
		
		WebElement ActuleDepartureDate = driver.findElement(By.cssSelector("div[class='sc-bYnzgO sc-cPuPxo jNskcH'] span[class='sc-dXfzlN iPVuSG']"));
		WebElement ActuleReturnDate = driver.findElement(By.cssSelector("div[class='sc-bYnzgO sc-hvvHee aiGEY'] span[class='sc-dXfzlN iPVuSG']"));
		
		WebElement ActualDay= driver.findElement(By.cssSelector("div[class='sc-bYnzgO sc-cPuPxo jNskcH'] span[class='sc-aewfc dPjtTY']"));
		
		LocalDate today= LocalDate.now();
		
		 // Check Date-----------------
		int ExpectedDepartureDateValue 
        = today.plusDays(1).getDayOfMonth();
		
		int ExpectedReturneDateValue 
        = today.plusDays(2).getDayOfMonth();
	
		int ActuleDepartureDateValue = Integer.parseInt(ActuleDepartureDate.getText());
		int ActuleReturnDateValue = Integer.parseInt(ActuleReturnDate.getText());
		
		Assert.assertEquals(ActuleDepartureDateValue , ExpectedDepartureDateValue);
		Assert.assertEquals(ActuleReturnDateValue , ExpectedReturneDateValue);
		
		// Check Day----------------------- 
		String ActualDayOfWeek = ActualDay.getText();
		String ExpectedDayOfWeek = today.plusDays(1).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
		
		Assert.assertEquals(ActualDayOfWeek , ExpectedDayOfWeek);
		
		/*
		System.out.println("Today is "+today.getDayOfWeek());
		System.out.println("Tomorrow is "+today.plusDays(1).getDayOfWeek());
	
		System.out.println("Tomorrow date: " + ExpectedDepartureDateValue);
		System.out.println("After tomorrow date: "+ExpectedReturneDateValue);
		
		*/
	}	
	
	
	@AfterTest
	public void myAfterTest(){
		softAssert.assertAll();
	}
	
}

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class database {
	
	
	WebDriver driver = new ChromeDriver();
	Random rand = new Random();
	Connection con ; 
	Statement stmt ;
	ResultSet rs ;
String firstname;
String lastname;
Date timeStamp = new Date();
	
	
	@BeforeTest
	public void mysetup() throws SQLException {
		driver.get("https://automationteststore.com/index.php?rt=account/create");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","farah2001");
		
		
	}
	@Test (priority = 2)
	public void UpdateTheData () throws SQLException, InterruptedException {
		String query = "update customers set customerName = 'Farah Odeh' where customerNumber = 103";
		stmt = con.createStatement();
		int rowupdated = stmt.executeUpdate(query);
		System.out.println(rowupdated);
		Thread.sleep(1000);
	}
	
	
	
	@Test(priority = 3)
	public void ReadTheDataInsideTheBrowser() throws SQLException, IOException {
		String [] customernumber = {"114","119","121"};
		
		int randomcustomernumber = rand.nextInt(customernumber.length); 
		String theselectcustomernumber = customernumber[randomcustomernumber];
		String query = "select * from customers where customerNumber = 103" ;
		
		stmt = con.createStatement();
		rs = stmt.executeQuery(query);
		System.out.println(rs);
		while (rs.next()) {
			firstname = rs.getString("customerName");
			lastname = rs.getString("contactLastName");
			
		}
		
		
		
		WebElement firstnamebox = driver.findElement(By.id("AccountFrm_firstname"));
		firstnamebox.sendKeys(firstname);
		WebElement lastnamebox = driver.findElement(By.id("AccountFrm_lastname"));
		lastnamebox.sendKeys(lastname);
		
		
		TakeTheScreenShotPlease();
		
		
		
	}
	
	@Test(priority = 1)
	public void inserttotable() throws SQLException {
		String query = "INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city, state, postalCode, country, salesRepEmployeeNumber, creditLimit) VALUES (103, 'Ali', 'Schmitt', 'Carine', '40.32.2555', '54, rue Royale', NULL, 'Nantes', NULL, '44000', 'France', 1370, 21000.00);\r\n"
				+ "";
		stmt = con.createStatement();
		int rowupdated = stmt.executeUpdate(query);
		
		
		
	}
	
	@Test(priority = 4)
	public void DeleteQuery() throws SQLException {
		String query = "delete from customers where customerNumber = 103";
		stmt = con.createStatement();
		int rowupdated = stmt.executeUpdate(query);
		
		
		
	}
	
	
public void TakeTheScreenShotPlease() throws IOException {
	TakesScreenshot ts = (TakesScreenshot)driver;
	File screenshotfile = ts.getScreenshotAs(OutputType.FILE);
	String filename = timeStamp.toString().replace(":", "-");
	FileUtils.copyFile(screenshotfile, new File("src/screenshot‬/" + filename + ".jbg"));
	
		
}
	
	
	

}

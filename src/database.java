import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class database {
	
	
	WebDriver driver = new ChromeDriver();
	Connection con ; 
	Statement stmt ;
	ResultSet rs ;
String firstname;
String lastname;
	
	
	@BeforeTest
	public void mysetup() throws SQLException {
		driver.get("https://automationteststore.com/index.php?rt=account/create");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","farah2001");
		
		
	}
	@Test (priority = 1)
	public void UpdateTheData () throws SQLException, InterruptedException {
		String query = "update customers set customerName = 'Ali' where customerNumber = 103";
		stmt = con.createStatement();
		int rowupdated = stmt.executeUpdate(query);
		System.out.println(rowupdated);
		Thread.sleep(1000);
	}
	
	
	
	@Test(priority = 2)
	public void ReadTheDataInsideTheBrowser() throws SQLException {
		String query = "select * from customers where customerNumber = 103";
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
		
		
		
	}
	
	
	
	

	
	
	

}

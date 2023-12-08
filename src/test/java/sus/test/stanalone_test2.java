package sus.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import page_objects.ConfirmationPage;
import page_objects.OrderPage;
import page_objects.cartPage;
import page_objects.catalogpage;
import page_objects.checkOutPage;
import page_objects.landingPage;
import sus.TestComponents.BaseTest;

public class stanalone_test2 extends BaseTest{
	@Test(dataProvider = "dataprovider",groups = "purchase")
	public void e2e(HashMap<String, String> input) throws InterruptedException, IOException{
		
		//landingpage object
		String productName="iphone 13 pro";
		WebDriver	driver=	initilizeDriver();
		landingPage Landpage=new landingPage(driver);
		Landpage.goToLandingpage();
		
		catalogpage prod1=	Landpage.logintoApplication(input.get("email"), input.get("password"));
		
		
	List<WebElement> products=	prod1.getProductList();
		prod1.getdesirableProduct();
		prod1.addElementtoCart();
		
	
	Thread.sleep(3000);
	cartPage Cart=	prod1.goCart();
	
	
	//verify products in cart
	
	Boolean bn=	Cart.verifyProductDisplay(productName);

	Assert.assertTrue(bn);
	Cart.goToCheckout();
	
	checkOutPage cp=new checkOutPage(driver);
	cp.selectCountry("India");
	cp.SubmitOrder();
	
	
	ConfirmationPage confirm=new ConfirmationPage(driver);
	
	

	AssertJUnit.assertEquals(confirm.verifyMessage(), "THANKYOU FOR THE ORDER.");
}
	@Test(dependsOnMethods = "e2e")  //it is depending on above method
	public void OrderHistoryTest() {
		OrderPage op=new OrderPage(driver);
		
		Assert.assertEquals(op.productDisplay("	iphone 13 pro"), true);
	}
	
	
	
	@DataProvider
	public Object[][] dataprovider() {
		
		HashMap<String, String> hm1=new HashMap<>();
		hm1.put("email","sushantanandy@gmail.com");
		hm1.put("password","Santosh@3155");
		
		HashMap<String, String> hm2=new HashMap<>();
		hm2.put("email","123456@gmail.com");
		hm2.put("password","Santosh123@3155");
		
		return new Object[][] {{hm1},{hm2}};
		//return  new Object[][] {{"sushantanandy@gmail.com","Santosh@3155"},{"s12345@gmail.com","Santosh@3155"}};
	}
}

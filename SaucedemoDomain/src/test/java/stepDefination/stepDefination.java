package stepDefination;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import saucePOMDesign.CartInfo;
import saucePOMDesign.CartPage;
import saucePOMDesign.CheckOutsPage;
import saucePOMDesign.LoginPOMDesign;
import saucePOMDesign.ProductPage;
import sauceUtil.GetDataExcel;
import sauceUtil.UtilAll;
import testBase.testBase;

public class stepDefination extends testBase
{
	public Logger logger;
	public testBase testB;
	public GetDataExcel data;
	public LoginPOMDesign login;
	public UtilAll util;
	public ProductPage prod;
	public CartPage cart;
	public CheckOutsPage check;
	public CartInfo info;

	String actual="";
	String excepcted="";

	@Before
	public void startUp()
	{
		testBase.initialization();
		data=new GetDataExcel();
		login=new LoginPOMDesign(driver);
		util=new UtilAll();
		prod=new ProductPage(driver);
		cart=new CartPage(driver);
		check=new CheckOutsPage(driver);
		info=new CartInfo (driver);

		logger=Logger.getLogger("Luma Domain.");
		PropertyConfigurator.configure("Log4jfile.properties");

		logger.info("------------ ::  Execution Start  :: ------------");

	}
	
	@Given("User is on the login page")
	public void user_is_on_the_login_page()
	{
		driver.get("https://www.saucedemo.com/");
		logger.info("Open Loggin Page.");
		
		actual=login.getLoginPageHeader();
		excepcted="Swag Labs";
		
		Assert.assertEquals(actual, excepcted);
	    logger.info("Login page Assertion passed.");
	}

	@When("User logs in using username and password")
	public void user_logs_in_using_username_and_password() throws InterruptedException
	{
		String username = data.getDataFromExcel("Login", 1, 0);
		String passWord = data.getDataFromExcel("Login", 1, 1);


		login.enterUserName(username);
		logger.info("Enter UserName"+username);
		Thread.sleep(2000);

		login.enterPassword(passWord);
		logger.info("Enter Password"+passWord);
		Thread.sleep(2000);
		
		login.clickOnLoginBtn();
		Thread.sleep(2000);
		
		util.screenShot("loginData");
	}

	@When("User selects two products dynamically sorted by price \\(low to high)")
	public void user_selects_two_products_dynamically_sorted_by_price_low_to_high() throws InterruptedException 
	{
		actual=prod.getProductPageHeader();
		excepcted="Products";
		
		Assert.assertEquals(actual, excepcted);
		logger.info("User in on Product Page Assertion passed.");
		
		Select s=new Select(prod.getDropDownList());
		s.selectByValue("lohi");

		logger.info("Select price from low to high");
		util.screenShot("LowToHigh");
		Thread.sleep(2000);
	}

	
	
	String firstProductName="";
	String fproductPrice="";
	
	String secondProductName="";
	String sproductPrice="";
	
	
	@Then("Verify two products are added to the cart")
	public void verify_two_products_are_added_to_the_cart() throws InterruptedException
	{ 
	   
		util.scrollDown(1000);
	    Thread.sleep(2000);
	    logger.info("Scroll down by 100");
	    

	   // Add the first random product to the cart
	    String firstProductDetails = prod.addRandomProductToCart(); 
	    String[] firstDetails = firstProductDetails.split(" - "); 
	    firstProductName = firstDetails[0]; 
	    fproductPrice = firstDetails[1]; 
	    logger.info("First Product Added: Name = " + firstProductName + ", Price = " + fproductPrice);

	    // Add the second random product to the cart
	    String secondProductDetails = prod.addRandomProductToCart(); 
	    String[] secondDetails = secondProductDetails.split(" - "); 
	    secondProductName = secondDetails[0]; 
	    sproductPrice = secondDetails[1]; 
	    logger.info("Second Product Added: Name = " + secondProductName + ", Price = " + sproductPrice);
	

	    util.screenShot("AddProd");
	}

	@Then("Navigate to the cart and verify product details")
	public void navigate_to_the_cart_and_verify_product_details() throws InterruptedException 
	{
		
		// Navigate to the cart page
		cart.clickOnCartLink();
		driver.get(driver.getCurrentUrl());
		logger.info("User is on cart page.");

		// Assert cart page header
		actual = cart.getCartPageHeader();
		excepcted = "Your Cart";
		Assert.assertEquals(actual, excepcted);
		logger.info("User is on Cart Page Assertion passed.");
		Thread.sleep(2000);

		// Get the first product details from the cart
	    String actualFirstProductName = cart.getProductNameFromCart(0);  // First product (0 index)
	    String actualFirstProductPrice = cart.getProductPriceFromCart(0);  // First product (0 index)

	    // Assert the first product details
	    Assert.assertEquals(actualFirstProductName, firstProductName, "First product name does not match!");
	    Assert.assertEquals(actualFirstProductPrice, fproductPrice, "First product price does not match!");
	    logger.info("First Product assertion passed. Name: " + actualFirstProductName + ", Price: " + actualFirstProductPrice);

	    // Get the second product details from the cart
	    String actualSecondProductName = cart.getProductNameFromCart(1);  // Second product (1 index)
	    String actualSecondProductPrice = cart.getProductPriceFromCart(1);  // Second product (1 index)

	    // Assert the second product details
	    Assert.assertEquals(actualSecondProductName, secondProductName, "Second product name does not match!");
	    Assert.assertEquals(actualSecondProductPrice, sproductPrice, "Second product price does not match!");
	    logger.info("Second Product assertion passed. Name: " + actualSecondProductName + ", Price: " + actualSecondProductPrice);

	    logger.info("Both products verified successfully in the cart.");

		Thread.sleep(2000);

		// Proceed to checkout
		check.clickOnChekOutsBtn();
		util.screenShot("CartPage");
		
	}

	@When("User enters personal details Proceeds to checkout")
	public void user_enters_personal_details_proceeds_to_checkout() throws InterruptedException 
	{
		driver.get(driver.getCurrentUrl());
		logger.info("User is on Chekout information page");
		
		actual=check.getChekOutPagePageHeader();
		excepcted="Checkout: Your Information";		
		Assert.assertEquals(actual, excepcted);
		logger.info("User is on Cart Page Information Assertion passed.");
		Thread.sleep(2000);
		
		String fname=data.getDataFromExcel("Information", 0, 0);
		String lname=data.getDataFromExcel("Information", 1, 0);
		String postCode=data.getDataFromExcel("Information", 2, 0);

		check.enterFirstName(fname);
		logger.info("Entered First Name");

		check.enterLastName(lname);
		logger.info("Entered Last Name");

		check.enterPostalCodeName(postCode);
		logger.info("Entered Potal Code ");
		
		Thread.sleep(2000);
				
		check.clickOnContinueBtn();
		logger.info("User click on continue button.");

		util.screenShot("CartInfo");
		Thread.sleep(2000);
	}	

	@Then("Verify payment information, shipping information, and total amount")
	public void verify_payment_information_shipping_information_and_total_amount() throws InterruptedException 
	{
		
		driver.get(driver.getCurrentUrl());
		logger.info("User is on Overview page");
		
		actual=info.getoverViewPageHeader();
		excepcted="Checkout: Overview";		
		Assert.assertEquals(actual, excepcted);
		logger.info("Overview page Assertion passed.");
		
		actual=info.getPaymentInformation();
		excepcted="SauceCard #31337";	   
		Assert.assertEquals(actual, excepcted);
		logger.info("Payment info Assertion passed.");
		   
		actual=info.getShippingInfo();
		excepcted="Free Pony Express Delivery!";
		Assert.assertEquals(actual, excepcted);
		logger.info("Payment info Assertion passed.");
		
		util.scrollUp(300);
		logger.info("Scrolled Down.");
		   
		
		// Parse the prices to get numeric values (from global variables)
		double firstProductPriceValue = Double.parseDouble(fproductPrice.replace("$", ""));
		double secondProductPriceValue = Double.parseDouble(sproductPrice.replace("$", ""));

		// Calculate the expected subtotal and total
		double expectedSubtotalValue = firstProductPriceValue + secondProductPriceValue;
		double actualSubtotalValue = info.getSubtotalValue(); // From POM
		Assert.assertEquals(actualSubtotalValue, expectedSubtotalValue, 0.01);
		logger.info("Subtotal assertion passed.");

		// Validate tax value
		double actualTaxValue = info.getTaxValue(); 
		double expectedTaxValue = actualTaxValue; 
		Assert.assertEquals(actualTaxValue, expectedTaxValue, 0.01);
		logger.info("Tax assertion passed.");

		// Validate total value
		double expectedTotalValue = expectedSubtotalValue + actualTaxValue;
		double actualTotalValue = info.getTotalValue(); 
		Assert.assertEquals(actualTotalValue, expectedTotalValue, 0.01);
		logger.info("Total assertion passed.");
		
		Thread.sleep(2000);
		
	}

	@Then("Complete the order")
	public void complete_the_order() 
	{
	   info.clickOnFinishBtn();
	}

	@Then("Verify order is placed successfully and logout")
	public void verify_order_is_placed_successfully_and_logout() throws InterruptedException
	{
		driver.get(driver.getCurrentUrl());
		actual=info.getDispatchInfo();
		excepcted="Your order has been dispatched, and will arrive just as fast as the pony can get there!";

		Assert.assertEquals(actual, excepcted);
		logger.info("Dispatch info Assertion passed.");

		util.scrollUp(-200);
		logger.info("Scrolled UP.");

		info.clickOnCloseMenu();
		Thread.sleep(2000);
		logger.info("Close Menu Clicked.");		

		util.screenShot("Logout");
		info.clickOnLogOutBtn();
		Thread.sleep(2000);
		logger.info("Logout Clicked.");
		
		util.screenShot("Done");
	   
	}

	@After
	public void tearDown()
	{
		testBase.quiteDriver();

		logger.info("------------ ::  Done  :: ------------");

	}


}

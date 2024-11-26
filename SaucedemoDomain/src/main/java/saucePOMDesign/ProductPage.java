package saucePOMDesign;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage
{
	//----------Initialization------
	public ProductPage (WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	//-----------Declaration---------

	@FindBy(xpath="//select[contains(@class,'prod')]")
	private WebElement prodDrpDwn;
	
	@FindBy(xpath="//span[@class='title']")
	private WebElement prodPageHeader;	
	
	@FindBy(xpath = "//div[@class='inventory_item']")
    private List<WebElement> productItems; 

	
	//----------Usage------------

	public String addRandomProductToCart()
	{
        Random random = new Random();
        int randomIndex = random.nextInt(productItems.size());

        WebElement product = productItems.get(randomIndex);
        String productName = product.findElement(By.xpath(".//div[@class='inventory_item_name ']")).getText();
        String productPrice = product.findElement(By.xpath(".//div[@class='inventory_item_price']")).getText();

        // Click on the 'Add to Cart' button for the selected product
        product.findElement(By.xpath(".//button")).click();

        System.out.println("Added Product: " + productName + " | Price: " + productPrice);

        return productName + " - " + productPrice; // Return details for further use
    }

	public List<String> getAllProductNames()
	{
	    List<String> productNames = new ArrayList<>();
	    for (WebElement product : productItems) 
	    {
	        String name = product.findElement(By.xpath(".//div[@class='inventory_item_name']")).getText();
	        productNames.add(name);
	    }
	    return productNames;
	}

	// Method to get all product prices
	public List<String> getAllProductPrices()
	{
	    List<String> productPrices = new ArrayList<>();
	    for (WebElement product : productItems)
	    {
	        String price = product.findElement(By.xpath(".//div[@class='inventory_item_price']")).getText();
	        productPrices.add(price);
	    }
	    return productPrices;
	}
	
	
	
	public String getProductPageHeader()
	{
		return prodPageHeader.getText();
	}
	public WebElement getDropDownList()
	{
		return prodDrpDwn;
	}
	
	

	


}

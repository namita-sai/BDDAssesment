package saucePOMDesign;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage
{

	//-----------Initialization-------
	public CartPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	//----------Declaration--------
	@FindBy(xpath="//a[contains(@class,'shopping_cart_link')]")
	private WebElement cartbtn;

	@FindBy(xpath="//span[@class='title']")
	private WebElement cartPageHeader;	
	
	@FindBy(xpath = "//div[@class='cart_item']")
    private List<WebElement> cartItems;
	
	
	//----------Usage---------------

    // Method to get product name from the cart
    public String getProductNameFromCart(int productIndex) 
    {
        WebElement product = cartItems.get(productIndex);
        return product.findElement(By.xpath(".//div[@class='inventory_item_name']")).getText();
    }

    // Method to get product price from the cart
    public String getProductPriceFromCart(int productIndex) 
    {
        WebElement product = cartItems.get(productIndex);
        return product.findElement(By.xpath(".//div[@class='inventory_item_price']")).getText();
    }
	

	
	public void clickOnCartLink()
	{
		cartbtn.click();
	}

	public String getCartPageHeader()
	{
		return cartPageHeader.getText();
	}
}

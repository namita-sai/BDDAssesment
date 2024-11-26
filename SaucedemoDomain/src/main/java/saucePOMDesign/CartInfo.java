package saucePOMDesign;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartInfo
{
	//-------------Initialization----------
	public CartInfo(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	//-------------Decalartion--------------
	@FindBy(xpath="(//div[contains(@class,'value')])[1]")
	private WebElement paymentInfo;

	@FindBy(xpath="(//div[contains(@class,'value')])[2]")
	private WebElement shipingInfo;
	
	@FindBy(xpath="//button[contains(@id,'finish')]")
	private WebElement finishBtn;

	@FindBy(xpath="//div[contains(@class,'text')]")
	private WebElement dispathInfo;

	@FindBy(xpath="//button[text()='Open Menu']")
	private WebElement closeMenu;

	@FindBy(xpath="//a[text()='Logout']")
	private WebElement logOutBtn;
	
	@FindBy(xpath="//span[@class='title']")
	private WebElement overViewPageHeader;	
	
	@FindBy(xpath="//div[contains(@class,'summary_subtotal_label')]")
	private WebElement subtotalPrice;
	
	@FindBy(xpath="//div[contains(@class,'summary_total_label')]")
	private WebElement totalPrice;
	
	@FindBy(xpath="//div[contains(@class,'summary_tax_label')]")
	private WebElement taxPrice;		


	//------------Usage----------------------
	public String getoverViewPageHeader()
	{
		return overViewPageHeader.getText();
	}
	
	public String getPaymentInformation()
	{
		return paymentInfo.getText();
	}

	public String getShippingInfo()
	{
		return shipingInfo.getText();
	}

	// Method to get subtotal as a numeric value
	public double getSubtotalValue()
	{
	    String subtotalText = subtotalPrice.getText().replace("Item total: $", "").trim();
	    return Double.parseDouble(subtotalText);
	}

	// Method to get tax as a numeric value
	public double getTaxValue() 
	{
	    String taxText = taxPrice.getText().replace("Tax: $", "").trim();
	    return Double.parseDouble(taxText);
	}

	// Method to get total as a numeric value
	public double getTotalValue() 
	{
	    String totalText = totalPrice.getText().replace("Total: $", "").trim();
	    return Double.parseDouble(totalText);
	}

	public void clickOnFinishBtn()
	{
		finishBtn.click();
	}

	public String getDispatchInfo()
	{
		return dispathInfo.getText();
	}

	public void clickOnCloseMenu()
	{
		closeMenu.click();
	}

	public void clickOnLogOutBtn()
	{
		logOutBtn.click();
	}
}

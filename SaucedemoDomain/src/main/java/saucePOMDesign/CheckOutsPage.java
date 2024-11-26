package saucePOMDesign;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutsPage
{
	//----------------Initialization---------
	public CheckOutsPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	//---------------Declaration-----------
	@FindBy(xpath="//button[@id='checkout']")
	private WebElement chekOutbtn;

	@FindBy(xpath="//input[contains(@id,'first-name')]")
	private WebElement fnameTxt;

	@FindBy(xpath="//input[contains(@id,'last-name')]")
	private WebElement lnameTxt;

	@FindBy(xpath="//input[contains(@id,'postal')]")
	private WebElement postalTxt;

	@FindBy(xpath="//input[contains(@id,'continue')]")
	private WebElement continueBtn;
	
	@FindBy(xpath="//span[@class='title']")
	private WebElement chekOutPageHeader;	

	
	//----------Usage------------

	public String getChekOutPagePageHeader()
	{
		return chekOutPageHeader.getText();
	}
	
	public void clickOnChekOutsBtn()
	{
		chekOutbtn.click();
	}

	public void enterFirstName(String fname)
	{
		fnameTxt.sendKeys(fname);
	}

	public void enterLastName(String lname)
	{
		lnameTxt.sendKeys(lname);
	}

	public void enterPostalCodeName(String code)
	{
		postalTxt.sendKeys(code);
	}

	public void clickOnContinueBtn()
	{
		continueBtn.click();
	}

}

package saucePOMDesign;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPOMDesign
{
	//-------------Initialization------

	public LoginPOMDesign (WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	//-------------Decalartion--------

	@FindBy(xpath="//input[contains(@id,'user')]")
	private WebElement userName;

	@FindBy(xpath="//input[contains(@id,'password')]")
	private WebElement pass;

	@FindBy(xpath="//input[contains(@id,'login')]")
	private WebElement loginBtn;
	
	@FindBy(xpath="//div[@class='login_logo']")
	private WebElement loginHeader;

	//-------------Usage--------------

	public void enterUserName(String uname)
	{
		userName.sendKeys(uname);
	}

	public void enterPassword(String passwrd)
	{
		pass.sendKeys(passwrd);
	}

	public void clickOnLoginBtn()
	{
		loginBtn.click();
	}
	
	public String getLoginPageHeader()
	{
		return loginHeader.getText();
	}

}

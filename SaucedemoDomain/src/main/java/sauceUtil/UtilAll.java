package sauceUtil;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import testBase.testBase;

public class UtilAll extends testBase
{
	public void screenShot(String ssName)
	{
		try
		{
			TakesScreenshot ts=((TakesScreenshot)driver);
			File src = ts.getScreenshotAs(OutputType.FILE);
			File des=new File(".\\ScreenShot\\"+ssName+System.currentTimeMillis()+".png");
			FileHandler.copy(src, des);
		}
		catch (IOException e)
		{
		   e.printStackTrace();
		}
	}

	public void scrollDown(int val)
	{
		JavascriptExecutor js=((JavascriptExecutor)driver);
		js.executeScript("scroll(0,"+val+")");
	}

	public void scrollUp(int val)
	{
		JavascriptExecutor js=((JavascriptExecutor)driver);
		js.executeScript("scroll(0,"+val+")");
	}
}

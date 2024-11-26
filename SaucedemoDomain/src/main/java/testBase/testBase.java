package testBase;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class testBase
{
	public static WebDriver driver;



	public static void initialization()
	{
		ArrayList<String> args=new ArrayList<>();
		args.add("start-maximized");
		args.add("disable-notifications");
		args.add("disable-infobars");
		//args.add("headless");


		 driver=new ChromeDriver();
		 WebDriverManager.chromedriver().setup();

		 driver.manage().window().maximize();
		 driver.manage().deleteAllCookies();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));




	}


	public static void quiteDriver()
	{
		driver.quit();

	}



}

package cucumberOption;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions( features="src/test/java/featureLayer"
                  ,glue="stepDefination"
                  //,dryRun=true
                  ,monochrome=true
                  ,plugin={"pretty", "html:target/cucmber.html","json:target/exp.json"}
		        )

public class SauseRunner extends AbstractTestNGCucumberTests
{

}

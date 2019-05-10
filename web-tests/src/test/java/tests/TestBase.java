package tests;

import manager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class TestBase {


  protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser",BrowserType.CHROME));

  @BeforeSuite
  public void SetUp() throws IOException {
    app.init();

  }

  @AfterSuite
  public void tearDown() {
    app.getSessionHelper().stop();

  }

}

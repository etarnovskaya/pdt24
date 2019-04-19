package tests;

import manager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBase {


  protected final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

  @BeforeClass
  public void SetUp() {
    app.init();

  }

  @AfterClass
  public void tearDown() {
    app.getSessionHelper().stop();

  }

}

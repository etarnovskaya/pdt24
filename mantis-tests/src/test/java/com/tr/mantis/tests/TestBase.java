package com.tr.mantis.tests;

import com.tr.mantis.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class TestBase {
   static  ApplicationManager app = new ApplicationManager(System.getProperty("browser",BrowserType.CHROME));

  @BeforeSuite
  public void SetUp() throws IOException {
    app.init();

  }

  @AfterSuite
  public void tearDown() {
    app.stop();

  }

}

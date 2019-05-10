package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  private final Properties properties;
  WebDriver wd;
  GroupHelper groupHelper;
  SessionHelper sessionHelper;
  ContactHelper contactHelper;
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");

    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties",target))));
    if(browser.equals(BrowserType.CHROME)){
      wd = new ChromeDriver();
    } else if (browser.equals(BrowserType.FIREFOX)){
      wd = new FirefoxDriver();
    }


    wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    groupHelper = new GroupHelper(wd);
    sessionHelper = new SessionHelper(wd);
    contactHelper = new ContactHelper(wd);
  // sessionHelper.openSite("http://localhost/addressbook/");

 sessionHelper.openSite(properties.getProperty("web.baseUrl"));
 //  sessionHelper.login("admin", "secret");
    sessionHelper.login(properties.getProperty("web.adminLogin"),  properties.getProperty("web.adminPassword"));
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public SessionHelper getSessionHelper() {
    return sessionHelper;
  }
}

package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  WebDriver wd;
  GroupHelper groupHelper;
  SessionHelper sessionHelper;
  ContactHelper contactHelper;
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  public void init() {
    if(browser.equals(BrowserType.CHROME)){
      wd = new ChromeDriver();
    } else if (browser.equals(BrowserType.FIREFOX)){
      wd = new FirefoxDriver();
    }

    wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    groupHelper = new GroupHelper(wd);
    sessionHelper = new SessionHelper(wd);
    contactHelper = new ContactHelper(wd);
    sessionHelper.openSite("http://localhost/addressbook/");
    sessionHelper.login("admin", "secret");
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

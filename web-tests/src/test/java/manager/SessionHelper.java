package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {

  public SessionHelper(WebDriver wd) {
    super(wd);

  }

  public void login(String user, String password) {
    type(By.cssSelector("[name=user]"), user);
    type(By.cssSelector("[name=pass]"), password);
    click(By.cssSelector("[value=Login]"));
  }

  protected void openSite(String url) {
    wd.get(url);
  }

  public void stop() {
    wd.quit();
  }
}

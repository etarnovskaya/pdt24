package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperBase {
  WebDriver wd;
  WebDriverWait wait;

  public HelperBase(WebDriver wd) {
    this.wd = wd;
  }

  public boolean isElementPresent(By locator){
    return wd.findElements(locator).size()>1;
  }

  public void confirmAlert() {
    wd.switchTo().alert().accept();
  }


  public void type(By locator, String text) {
    wd.findElement(locator).click();
    if (text != null) {
      String existingText =wd.findElement(locator).getAttribute("value");
      if(!text.equals(existingText)) {
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
      }
    }
  }

  public void click(By locator) {
    wd.findElement(locator).click();
  }
}

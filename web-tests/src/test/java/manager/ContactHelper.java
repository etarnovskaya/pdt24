package manager;

import model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);

  }

  public void submitContactCreation() {
    click(By.cssSelector("[name=submit]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.cssSelector("[name=firstname]"), contactData.getfName());
    type(By.cssSelector("[name=lastname]"), contactData.getlName());
    type(By.cssSelector("[name=home]"), contactData.gethPhone());
    type(By.cssSelector("[name=mobile]"), contactData.getmPhone());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void openContactsPage() {
    if (!isOnHomePage()) ;
    click(By.xpath("//*[@href='./']"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public boolean isOnHomePage() {
    return isElementPresent(By.cssSelector("table"));
  }

  public boolean isContactPresent() {
    return isElementPresent(By.name("selected[]"));
  }

  public void createContact() {
    initContactCreation();
  fillContactForm(new ContactData().withfName("a"), true);
   submitContactCreation();

  }

  public void selectFirstContact() {
    click(By.cssSelector("[name='selected[]']"));
  }

  public void deleteContact() {
    click(By.cssSelector("[value=Delete]"));
  }


  public void initContactModification() {
    click(By.cssSelector("[title=Edit]"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<>();
    List<WebElement> rows = wd.findElements(By.cssSelector("[name=entry]"));
    for (WebElement row : rows) {
      int id = Integer.parseInt(row.findElement(By.name("selected[]")).getAttribute("id"));
      String firstName = row.findElement(By.xpath(".//td[3]")).getText();
      String lastName = row.findElement(By.xpath(".//td[2]")).getText();
      ContactData contact = new ContactData().withId(id). withfName(firstName).withlName(lastName);
      contacts.add(contact);
    }
    return  contacts;
  }
}

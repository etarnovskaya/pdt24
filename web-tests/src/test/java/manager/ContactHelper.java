package manager;

import model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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



  public Set<ContactData> all() {
    Set<ContactData> contacts = new HashSet<>();
    List<WebElement> rows = wd.findElements(By.cssSelector("[name=entry]"));
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id =  Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String firstName = cells.get(2).getText();
      String lastName = cells.get(1).getText();
      String allphones = cells.get(5).getText() ;

      String[] phones = allphones.split("\n");
      ContactData contact = new ContactData().withId(id). withfName(firstName).withlName(lastName).withAllPhones(allphones);
//      ContactData contact = new ContactData().withId(id). withfName(firstName).withlName(lastName)
//              .withhPhone(phones[0]).withmPhone(phones[1]).withwPhone(phones[2]);
      contacts.add(contact);
    }
    return  contacts;
  }

  public  String cleaned(String phone){
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    // \\s - пробел
  }
  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String fName = wd.findElement(By.name("firstname")).getAttribute("value");
    String lName = wd.findElement(By.name("lastname")).getAttribute("value");
    String hPhone = wd.findElement(By.name("home")).getAttribute("value");
    String mPhone = wd.findElement(By.name("mobile")).getAttribute("value");
    String wPhone = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return  new ContactData().withId(contact.getId()).withfName(fName).withlName(lName)
            .withhPhone(hPhone).withmPhone(mPhone).withwPhone(wPhone);
  }

  private void initContactModificationById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List <WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();
  }
}

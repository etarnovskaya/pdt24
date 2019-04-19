package tests;

import model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends  TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    app.getContactHelper().openContactsPage();
    if(!app.getContactHelper().isContactPresent()){
      app.getContactHelper().createContact();
    }
  }

  @Test
  public void testModifyContact(){
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModification();
    ContactData contact = new ContactData(before.get(0).getId(), "", "","","", null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    List<ContactData> after = app.getContactHelper().getContactList();
    before.remove(0);
    before.add(contact);

    Assert.assertEquals(new HashSet<>(after), new HashSet<>(before));

  }
}

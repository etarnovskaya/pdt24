package tests;

import model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.getContactHelper().openContactsPage();
    if (!app.getContactHelper().isContactPresent()) {
      app.getContactHelper().createContact();
    }
  }

  @Test
  public void deleteContactTest() {

    // int before = app.getContactHelper().getContactCount();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectFirstContact();
    app.getContactHelper().deleteContact();
    app.getContactHelper().confirmAlert();
    // int after = app.getContactHelper().getContactCount();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(0);

    Assert.assertEquals(after, before);
  }
}

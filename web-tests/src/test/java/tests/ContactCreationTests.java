package tests;

import model.ContactData;
import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {
  @Test(enabled = false)
  public void createContactTest() {
    app.getContactHelper().openContactsPage();
   // int before = app.getContactHelper().getContactCount();
    List<ContactData> beforeList = app.getContactHelper().getContactList();

    app.getContactHelper().initContactCreation();
    ContactData contact = new ContactData().withId(beforeList.get(0).getId()).withfName("a").withlName("b").withhPhone("1111111");
    app.getContactHelper().fillContactForm(contact, true);
    app.getContactHelper().submitContactCreation();
    int after = app.getContactHelper().getContactCount();
    List<ContactData> afterList = app.getContactHelper().getContactList();
//returnToContactsPage();

    Assert.assertEquals(afterList.size(), beforeList.size()+1);
 //  int max = 0;
//    for(ContactData c  : afterList){
//      if(c.getId()>max){
//        max = c.getId();
//      }
      contact.withId(afterList.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());

    beforeList.add(contact);
    Assert.assertEquals(new HashSet<>(afterList),new HashSet<>(beforeList));
  }

}

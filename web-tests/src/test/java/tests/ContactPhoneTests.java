package tests;


import model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ContactPhoneTests extends TestBase{
  @Test(enabled = false)
  public void testContactPhones(){
    app.getContactHelper().openContactsPage();
    ContactData contact = app.getContactHelper().all().iterator().next();
    ContactData contactInfoFromEditForm = app.getContactHelper().infoFromEditForm(contact);
  Assert.assertEquals(contact.gethPhone(), cleaned(contactInfoFromEditForm.gethPhone()));
//    Assert.assertEquals(contact.getmPhone(), cleaned(contactInfoFromEditForm.getmPhone()));
//    Assert.assertEquals(contact.getwPhone(), cleaned(contactInfoFromEditForm.getwPhone()));

  }

  public static String cleaned(String phone){
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    // \\s - пробел
  }

  @Test
  public void testContactPhonesAfuh(){
    app.getContactHelper().openContactsPage();
    ContactData contact = app.getContactHelper().all().iterator().next();
    ContactData contactInfoFromEditForm = app.getContactHelper().infoFromEditForm(contact);
    Assert.assertEquals(contact.getAllphones(), mergePhones(contactInfoFromEditForm));

//    Assert.assertEquals(contact.getmPhone(), cleaned(contactInfoFromEditForm.getmPhone()));
//    Assert.assertEquals(contact.getwPhone(), cleaned(contactInfoFromEditForm.getwPhone()));

  }

  private String mergePhones(ContactData contact) {
//    String res = "";
//    if (contact.gethPhone() != null){
//      res = res + contact.gethPhone();
//    }
//    return  res;

    //another method

    return Arrays.asList(contact.gethPhone(), contact.getmPhone(), contact.getwPhone())
            .stream().filter((s) -> ! s.equals("")).map(ContactPhoneTests::cleaned).collect(Collectors.joining("\n"));
  }


}

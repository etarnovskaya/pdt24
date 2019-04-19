package tests;

import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.TestBase;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase{
  @BeforeMethod
  public void ensurePreconditions(){
 app.getGroupHelper().openGroupsPage();
//    if(!app.getGroupHelper().isGroupPresent()){
//      app.getGroupHelper().createGroup(new GroupData("a", "b", "c"));
//    }
  }
  @Test
  public void modifyGroup(){
    app.getContactHelper().getContactCount();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("","",""));
    app.getGroupHelper().submitGroupModidfication();
  }

  @Test
  public void modifyGroupByIndex(){
    List<GroupData> before = app.getGroupHelper().getGroupList();
    //int before= app.getGroupHelper().getGroupCount();
    app.getGroupHelper().selectGroupByIndex(before.size()-1);

    app.getGroupHelper().initGroupModification();
    GroupData group = new GroupData(before.get(before.size()-1).getId(),"","","");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupModidfication();
    app.getGroupHelper().returnToGroupsPage();

    List<GroupData> after = app.getGroupHelper().getGroupList();
    //int before= app.getGroupHelper().getGroupCount();

    before.remove(before.size()-1);
    before.add(group);

    Comparator<? super GroupData> byId= (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);

    //Assert.assertEquals(new HashSet<Object>(before) , new HashSet<>(after));
    Assert.assertEquals(before, after);
  }

}

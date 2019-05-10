package tests;

import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupModificationTests extends TestBase{
  @BeforeMethod
  public void ensurePreconditions(){
 app.getGroupHelper().openGroupsPage();
if(!app.getGroupHelper().isGroupPresent()){
 app.getGroupHelper().createGroup(new GroupData().withName("a"));
   }
  }

  @Test
  public void modifyGroup(){
    app.getContactHelper().getContactCount();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData());
    app.getGroupHelper().submitGroupModidfication();
  }

  @Test
  public void modifyGroupByIndex(){
    List<GroupData> before = app.getGroupHelper().list();
    //int before= app.getGroupHelper().getGroupCount();
    GroupData group = new GroupData().withId(before.get(before.size()-1).getId()).withName("ab");
int index = before.size()-1;

    app.getGroupHelper().modifyGroup(group, index);

    List<GroupData> after = app.getGroupHelper().list();
    //int before= app.getGroupHelper().getGroupCount();

    before.remove(index);
    before.add(group);

    Comparator<? super GroupData> byId= (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);

    //Assert.assertEquals(new HashSet<Object>(before) , new HashSet<>(after));
    Assert.assertEquals(before, after);
  }

  @Test
  public void modifyGroupSet(){
    Set<GroupData> before = app.getGroupHelper().all();
    //int before= app.getGroupHelper().getGroupCount();
    GroupData modifyedGroup = before.iterator().next();
    GroupData group = new GroupData().withId(modifyedGroup.getId()).withName("ab");

    app.getGroupHelper().modifyGroupById(group);

    Set<GroupData> after = app.getGroupHelper().all();
    //int before= app.getGroupHelper().getGroupCount();

    before.remove(modifyedGroup);
    before.add(group);

    Assert.assertEquals(before, after);
  }



}

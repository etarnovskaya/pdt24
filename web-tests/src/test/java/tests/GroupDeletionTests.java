package tests;

import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GroupDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    app.getGroupHelper().openGroupsPage();
    if(!app.getGroupHelper().isGroupPresent()){
      app.getGroupHelper().createGroup(new GroupData().withName("a"));
    }
  }

  @Test
  public void deleteGroup(){
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().submitGroupDeletion();
    app.getGroupHelper().returnToGroupsPage();
  }

  @Test
  public void deleteLastGroup(){
    int before= app.getGroupHelper().getGroupCount();
    app.getGroupHelper().selectGroupByIndex(before-1);
    app.getGroupHelper().submitGroupDeletion();
    app.getGroupHelper().returnToGroupsPage();
  }
  @Test
  public void deleteLastGroupList(){
    List<GroupData> before = app.getGroupHelper().list();
    //int before= app.getGroupHelper().getGroupCount();
    app.getGroupHelper().selectGroupByIndex(before.size()-1);
    app.getGroupHelper().submitGroupDeletion();
    app.getGroupHelper().returnToGroupsPage();
  }

  @Test
  public void deleteTestListCompare(){
    List<GroupData> before = app.getGroupHelper().list();
    //int before= app.getGroupHelper().getGroupCount();
    app.getGroupHelper().selectGroupByIndex(before.size()-1);
    app.getGroupHelper().submitGroupDeletion();
    app.getGroupHelper().returnToGroupsPage();
    List<GroupData> after = app.getGroupHelper().list();
    before.remove(before.size()-1);

    Assert.assertEquals(after, before);
  }

}

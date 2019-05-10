package tests;

import model.GroupData;
import model.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

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

  @Test
  public void deleteTestCeche(){
    List<GroupData> before = app.getGroupHelper().list();
    //int before= app.getGroupHelper().getGroupCount();
    app.getGroupHelper().selectGroupByIndex(before.size()-1);
    app.getGroupHelper().submitGroupDeletion();
    app.getGroupHelper().returnToGroupsPage();
    List<GroupData> after = app.getGroupHelper().list();
    before.remove(before.size()-1);

    Assert.assertEquals(after, before);
  }

  @Test
  public void deleteTestListCompareSet(){
    Set<GroupData> before = app.getGroupHelper().all();
    GroupData deletedGroup = before.iterator().next();
    //int before= app.getGroupHelper().getGroupCount();
    app.getGroupHelper().delete(deletedGroup);
    Set<GroupData> after = app.getGroupHelper().all();
    before.remove(deletedGroup);

    Assert.assertEquals(after, before);
  }

  @Test(enabled = false)
  public void deleteTestListCompareGroupsClass(){
    Groups before = app.getGroupHelper().allset();
    GroupData deletedGroup = before.iterator().next();
    //int before= app.getGroupHelper().getGroupCount();
    app.getGroupHelper().delete(deletedGroup);
    Groups after = app.getGroupHelper().allset();
    before.remove(deletedGroup);

    Assert.assertEquals(after, before);
    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(deletedGroup)));
  }



}

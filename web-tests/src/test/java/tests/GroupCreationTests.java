package tests;

import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void GroupCreationTests() {
    app.getGroupHelper().openGroupsPage();
  //  int before = app.getGroupHelper().getGroupCount();
    List<GroupData> before = app.getGroupHelper().getGroupList();

    app.getGroupHelper().initGroupCreation();
    GroupData group= new GroupData("a", "b", "c");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().confirmGroupCreation();
    app.getGroupHelper().returnToGroupsPage();
   // int after =  app.getGroupHelper().getGroupCount();
    List<GroupData> after = app.getGroupHelper().getGroupList();


    Assert.assertEquals(after.size(), before.size()+1 );



    int max = 0;
    for(GroupData g  : after){
      if(g.getId()>max){
        max = g.getId();
      }
      group.setId(max);
    }


    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before) , new HashSet<>(after));
  }

}

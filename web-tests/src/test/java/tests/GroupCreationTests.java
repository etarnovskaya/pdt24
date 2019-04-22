package tests;

import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void GroupCreationTests() {
    app.getGroupHelper().openGroupsPage();
  //  int before = app.getGroupHelper().getGroupCount();
    List<GroupData> before = app.getGroupHelper().list();

    app.getGroupHelper().initGroupCreation();
    GroupData group= new GroupData().withName("a").withHeader("b").withFooter("c");
   app.getGroupHelper(). create(group);
   // int after =  app.getGroupHelper().getGroupCount();
    List<GroupData> after = app.getGroupHelper().list();


    Assert.assertEquals(after.size(), before.size()+1 );



//    int max = 0;
//    for(GroupData g  : after){
//      if(g.getId()>max){
//        max = g.getId();
//      }
//
//    }


    //group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());

    Comparator<? super GroupData> byId= (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    before.add(group);
    //Assert.assertEquals(new HashSet<Object>(before) , new HashSet<>(after));

    Assert.assertEquals(before, after);
  }



}

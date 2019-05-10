package tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import model.GroupData;
import model.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.MatcherAssert.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {
  @DataProvider
  public Iterator<Object[]> validGroupsCSV() throws IOException {
    List<Object[]> list = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")));

    String line = reader.readLine();
    while (line != null){
      String[] split = line.split(";");
      list.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
      line = reader.readLine();
    }
    return  list.iterator();

  }

  @DataProvider
  public Iterator<Object[]> validGroupsJson() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups2.json")));
String json ="";
    String line = reader.readLine();
    while (line != null){
     json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<GroupData> groups  = gson.fromJson(json, new TypeToken<List<GroupData>>() {
    }.getType());

    return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();


  }

  @DataProvider
  public Iterator<Object[]> validGroupsXML() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups2.xml")));

    String xml = "";

    String line = reader.readLine();
    while (line != null){
    xml += line;
      line = reader.readLine();
    }
    XStream xStream = new XStream();
    xStream.processAnnotations(GroupData.class);
    List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml);
    return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
  }

//  @Test
//  public void GroupCreationTests() {
//    app.getGroupHelper().openGroupsPage();
//  //  int before = app.getGroupHelper().getGroupCount();
//    List<GroupData> before = app.getGroupHelper().list();
//
//    app.getGroupHelper().initGroupCreation();
//    GroupData group= new GroupData().withName("a").withHeader("b").withFooter("c");
//   app.getGroupHelper(). create(group);
//   // int after =  app.getGroupHelper().getGroupCount();
//    List<GroupData> after = app.getGroupHelper().list();
//
//    Assert.assertEquals(after.size(), before.size()+1 );
//
//    int max = 0;
//    for(GroupData g  : after){
//      if(g.getId()>max){
//        max = g.getId();
//      }
//    }
//    Assert.assertEquals(new HashSet<Object>(before) , new HashSet<>(after));
//  }

  //@Test(dataProvider = "validGroupsCSV")
 // @Test(dataProvider = "validGroupsXML")
  @Test(dataProvider = "validGroupsJson")
  public void groupCreationFromDataProviderCSV(GroupData group){
    app.getGroupHelper().openGroupsPage();
    List<GroupData> before = app.getGroupHelper().list();

    app.getGroupHelper().initGroupCreation();
    //GroupData group= new GroupData().withName("a").withHeader("b").withFooter("c");
    app.getGroupHelper(). create(group);
    List<GroupData> after = app.getGroupHelper().list();

    Assert.assertEquals(after.size(), before.size()+1 );


    Comparator<? super GroupData> byId= (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    before.add(group);
    Assert.assertEquals(before, after);
  }

  @Test  (enabled =  false)
  public void GroupCreationTestsLambda() {
    app.getGroupHelper().openGroupsPage();
    List<GroupData> before = app.getGroupHelper().list();

    app.getGroupHelper().initGroupCreation();
    GroupData group= new GroupData().withName("a").withHeader("b").withFooter("c");
    app.getGroupHelper(). create(group);
    List<GroupData> after = app.getGroupHelper().list();

    Assert.assertEquals(after.size(), before.size()+1 );

    //group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());

    Comparator<? super GroupData> byId= (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    before.add(group);
    Assert.assertEquals(before, after);
  }

  @Test
  public void GroupCreationTestsSet() {
    app.getGroupHelper().openGroupsPage();
    Set<GroupData> before = app.getGroupHelper().all();

    app.getGroupHelper().initGroupCreation();
    GroupData group= new GroupData().withName("a").withHeader("b").withFooter("c");
    app.getGroupHelper(). create(group);
    Set<GroupData> after = app.getGroupHelper().all();


    Assert.assertEquals(after.size(), before.size()+1 );

group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(group);

    Assert.assertEquals(before, after);
    //Hamcrest
    assertThat(after, equalTo(before));
  }

  @Test
  public void GroupCreationTestsGroupsClass() {
    app.getGroupHelper().openGroupsPage();
   Groups before = app.getGroupHelper().allset();

    app.getGroupHelper().initGroupCreation();
    GroupData group= new GroupData().withName("a").withHeader("b").withFooter("c");
    app.getGroupHelper(). create(group);
    Groups after = app.getGroupHelper().allset();


    Assert.assertEquals(after.size(), before.size()+1 );

    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());

    Assert.assertEquals(before, after);
    //Hamcrest
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }




}

package manager;

import model.GroupData;
import model.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupHelper extends  HelperBase{

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToGroupsPage() {
    click(By.linkText("group page"));
  }

  public void confirmGroupCreation() {
    click(By.cssSelector("[name=submit]"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.cssSelector("[name='group_name']"), groupData.getName());
    type(By.cssSelector("[name='group_header']"), groupData.getHeader());
    type(By.cssSelector("[name='group_footer']"), groupData.getFooter());
  }

  public void initGroupCreation() {
    click(By.cssSelector("[name=new]"));
  }

  public void openGroupsPage() {
    if(isElementPresent(By.tagName("h1"))
            &&wd.findElement(By.tagName("h1")).getText().equals("Groups")
            && isElementPresent(By.name("new"))){
      return;
    }
     wd.findElement(By.xpath("//*[@href='group.php']")).click();
  }

  public boolean isGroupPresent() {
    return isElementPresent(By.name("selected[]"));
  }

  public void createGroup(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    confirmGroupCreation();
    returnToGroupsPage();
  }

  public void selectGroup() {
    click(By.cssSelector("[name='selected[]']"));
  }

  public void submitGroupDeletion() {
click(By.cssSelector("[name=delete]"));
  }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModidfication() {
    click(By.name("update"));
  }

  public int getGroupCount() {
    return wd.findElements(By.cssSelector("[name='selected[]']")).size();
  }

  public void selectGroupByIndex(int i) {
   wd.findElements(By.cssSelector("[name='selected[]']")).get(i).click();
  }

  public List<GroupData> list() {
    List<GroupData> groups = new ArrayList<>();
    List<WebElement> elements= wd.findElements(By.cssSelector("span.group"));
    for( WebElement e : elements ){
      String groupName =  e.getText();
      int id = Integer.parseInt(e.findElement(By.tagName("input")).getAttribute("value"));
      GroupData group = new GroupData().withId(id).withName(groupName);
      groups.add(group);
    }

    return groups;
 }

  public Set<GroupData> all() {
    Set<GroupData> groups = new HashSet<>();
    List<WebElement> elements= wd.findElements(By.cssSelector("span.group"));
    for( WebElement e : elements ){
      String groupName =  e.getText();
      int id = Integer.parseInt(e.findElement(By.tagName("input")).getAttribute("value"));
      GroupData group = new GroupData().withId(id).withName(groupName);
      groups.add(group);
    }

    return groups;
  }

  private Groups groupCache = null;

  public Set<GroupData> allCashe() {
    if (groupCache != null){
      return  new Groups(groupCache);
          }

    groupCache = new Groups();
    List<WebElement> elements= wd.findElements(By.cssSelector("span.group"));
    for( WebElement e : elements ){
      String groupName =  e.getText();
      int id = Integer.parseInt(e.findElement(By.tagName("input")).getAttribute("value"));
      GroupData group = new GroupData().withId(id).withName(groupName);
      groupCache.add(group);
    }

    return new Groups(groupCache);
  }

  public Groups allset() {
   Groups groups = new Groups();
    List<WebElement> elements= wd.findElements(By.cssSelector("span.group"));
    for( WebElement e : elements ){
      String groupName =  e.getText();
      int id = Integer.parseInt(e.findElement(By.tagName("input")).getAttribute("value"));
      GroupData group = new GroupData().withId(id).withName(groupName);
      groups.add(group);
    }

    return groups;
  }

  public void modifyGroup(GroupData group, int index) {
    selectGroupByIndex(index);

    initGroupModification();
    fillGroupForm(group);
    submitGroupModidfication();
    returnToGroupsPage();
  }

  public void modifyGroupById(GroupData group) {
    selectGroupById(group.getId());

    initGroupModification();
    fillGroupForm(group);
    submitGroupModidfication();
    returnToGroupsPage();
  }

  public void create(GroupData group) {
    fillGroupForm(group);
    confirmGroupCreation();
    returnToGroupsPage();
  }

  public void createWithCache(GroupData group) {
    fillGroupForm(group);
    confirmGroupCreation();
    groupCache = null;
    returnToGroupsPage();
  }



  public void selectGroupById(int id) {
    wd.findElement(By.cssSelector("input[value = '"+ id +"']")).click();


  }

  public void delete(GroupData group) {
    selectGroupById(group.getId());
    submitGroupDeletion();
    returnToGroupsPage();
  }

  public void deleteWithCache(GroupData group) {
    selectGroupById(group.getId());
    submitGroupDeletion();
    groupCache = null;
    returnToGroupsPage();
  }
}


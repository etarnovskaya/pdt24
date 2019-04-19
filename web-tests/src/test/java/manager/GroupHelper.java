package manager;

import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

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

  public List<GroupData> getGroupList() {
    List<GroupData> groups = new ArrayList<>();
    List<WebElement> elements= wd.findElements(By.cssSelector("span.group"));
    for( WebElement e : elements ){
      String groupName =  e.getText();
      int id = Integer.parseInt(e.findElement(By.tagName("input")).getAttribute("value"));
      GroupData group = new GroupData(id, groupName, null, null);
      groups.add(group);
    }

    return groups;
 }
}


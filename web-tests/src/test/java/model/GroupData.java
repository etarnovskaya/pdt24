package model;

import java.util.Objects;

public class GroupData {
  private int id;
  private  String name;
  private  String header;
  private  String footer;

  public GroupData(int id, String name, String header, String footer) {
    this.name = name;
    this.header = header;
    this.footer = footer;
    this.id =id;
  }
  public GroupData(String name, String header, String footer) {
    this.name = name;
    this.header = header;
    this.footer = footer;
    this.id = Integer.MAX_VALUE;
  }

  @Override
  public String toString() {
    return "GroupData{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GroupData groupData = (GroupData) o;
    return Objects.equals(name, groupData.name);
  }

  @Override
  public int hashCode() {

    return Objects.hash(name);
  }

  public String getName() {
    return name;
  }

  public String getHeader() {
    return header;
  }

  public String getFooter() {
    return footer;
  }

  public  int getId(){
    return  id;
  }

  public void setId(int id) {
    this.id = id;
  }
}

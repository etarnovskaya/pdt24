package model;

import java.io.File;
import java.util.Objects;

public class ContactData {
  int id = Integer.MAX_VALUE;
  private  String fName;
  private  String lName;
  private  String hPhone;
  private  String mPhone;
  private String group;
  private String wPhone;
  private String allphones;
  private File photo;

  public File getPhoto() {
    return photo;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo;
    return  this;
  }

  public String getAllphones() {
    return allphones;

  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", fName='" + fName + '\'' +
            ", lName='" + lName + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(fName, that.fName) &&
            Objects.equals(lName, that.lName);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, fName, lName);
  }

  public String getGroup() {
    return group;
  }

  public String getfName() {
    return fName;
  }

  public String getlName() {
    return lName;
  }

  public String gethPhone() {
    return hPhone;
  }

  public String getmPhone() {
    return mPhone;
  }

  public int getId() {
    return id;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withfName(String fName) {
    this.fName = fName;
    return this;
  }

  public ContactData withlName(String lName) {
    this.lName = lName;
    return this;
  }

  public ContactData withhPhone(String hPhone) {
    this.hPhone = hPhone;
    return this;
  }

  public ContactData withmPhone(String mPhone) {
    this.mPhone = mPhone;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withwPhone(String wPhone ) {
    this.wPhone = wPhone;
    return this;
  }

  public String getwPhone() {
    return wPhone;
  }

  public ContactData withAllPhones(String allphones) {
    this.allphones = allphones;
    return  this;
  }
}

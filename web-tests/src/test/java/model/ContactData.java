package model;

import java.util.Objects;

public class ContactData {
  int id;
  private final String fName;
  private final String lName;
  private final String hPhone;
  private final String mPhone;
  private String group;

  public ContactData(int id, String fName, String lName, String hPhone, String mPhone, String group) {
    this.id = id;
    this.fName = fName;
    this.lName = lName;
    this.hPhone = hPhone;
    this.mPhone = mPhone;
    this.group = group;
  }
  public ContactData(String fName, String lName, String hPhone, String mPhone, String group) {
    this.id = Integer.parseInt(null);
    this.fName = fName;
    this.lName = lName;
    this.hPhone = hPhone;
    this.mPhone = mPhone;
    this.group = group;
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

  public void setId(int id) {
    this.id = id;
  }
}

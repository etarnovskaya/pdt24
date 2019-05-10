package tests;

import model.GroupData;
import model.Groups;
import org.testng.annotations.Test;

import java.sql.*;

public class DbConnectionTest {
  @Test
  public void testDBConnection(){
    Connection conn = null;

    try {
      conn =
              DriverManager.getConnection("jdbc:mysql://localhost/addressbook?" +
                      "user=root&password=&serverTimezone=UTC");

      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("select group_id, group_name from group_list");
      Groups groups = new Groups();
      while ( rs.next()){
       groups.add(new GroupData().withId(rs.getInt("group_id")).withName(rs.getString("group_name")));
      }
      rs.close();
      st.close();
      conn.close();
      System.out.println(groups);

      // Do something with the Connection

    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
  }
}

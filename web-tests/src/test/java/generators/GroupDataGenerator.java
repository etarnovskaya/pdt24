package generators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {
  public static void main(String[] args) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);

    //generate data
    List<GroupData> groups = generateGroups(count);

    //save to file
    //saveAsCsv(groups, file);
    //saveAsXml(groups, file);
    saveAsJson(groups, file);
  }

  private static void saveAsXml(List<GroupData> groups, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(GroupData.class);
    String xml = xstream.toXML(groups);
    //settings xstream


    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private static void saveAsJson(List<GroupData> groups, File file) throws IOException {
    //Gson gson = new Gson();
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(groups);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();
  }

  private static void saveAsCsv(List<GroupData> groups, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (GroupData group : groups){
      writer.write(String.format("%s; %s; %s\n", group.getName(), group.getHeader(), group.getFooter() ));
    }
    writer.close();
  }

  private static List<GroupData> generateGroups(int count) {
    List<GroupData> groups = new ArrayList<>();
    for (int i= 0; i< count; i++ ){
       groups.add(new GroupData()
               .withName(String.format("test %s", i))
               .withHeader(String.format("test %s", i))
               .withFooter(String.format("test %s", i)));
    }
      return groups;
  }
}

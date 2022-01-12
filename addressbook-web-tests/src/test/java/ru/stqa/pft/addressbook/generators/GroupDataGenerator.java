package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.Annotations;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {

  @Parameter(names = "-c", description = "Group count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public File file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    GroupDataGenerator generator = new GroupDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex){
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<GroupData> groups = generateGroups(count);
    if (format.equals("csv")) {
      saveAsCsv(groups, file);
    } else if (format.equals("xml")) {
      saveAsXml(groups, file);
    } else {
      System.out.println("Unrecognised format " + format);
    }
  }

  private void saveAsXml(List<GroupData> groups, File file) throws IOException {
    XStream xstream = new XStream();
    Annotations.configureAliases(xstream, GroupData.class);
    String xml = xstream.toXML(groups);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private void saveAsCsv(List<GroupData> groups, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (GroupData group : groups) {
      writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
    }
    writer.close();
  }

  private List<GroupData> generateGroups(int count) {
    List<GroupData> groups = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      groups.add(new GroupData().withName(String.format("test %s", i))
              .withHeader(String.format("header %s", i)).withFooter(String.format("footer %s", i)));
    }
    return groups;
  }
}
package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.Annotations;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase{

  @DataProvider
  public Iterator<Object[]> validGroups() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/groups.xml"));
    String xml = "";
    String line = reader.readLine();
    while (line != null) {
      xml += line;
      line = reader.readLine();
    }
    XStream xStream = new XStream();
    Annotations.configureAliases(xStream, GroupData.class);
    List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml);
    return groups.stream().map(g -> new Object[] {g}).collect(Collectors.toList()).iterator();
  }

  @Test(dataProvider = "validGroups")
  public void testGroupCreation(GroupData group) {
    app.goTo().groupPage();
    Groups before = app.group().all();
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size() + 1));
    Set<GroupData> after = app.group().all();
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test
  public void testBadGroupCreation() {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("test1'");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Set<GroupData> after = app.group().all();
    assertThat(after, equalTo(before));
  }
}
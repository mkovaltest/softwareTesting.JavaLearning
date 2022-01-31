package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteFromGroupTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
    Groups groups = app.db().groups();
    if (app.db().contacts().size() == 0) {
      app.goTo().gotoHomePage();
      app.contact().createContact(new ContactData()
              .withFirstname("Michael").withLastname("Koval").withNickname("Hors68")
              .withTitle("tester").withCompany("Cinimex").withAddress("Voronej")
              .withMobilephone("123").withWorkphone("456").withHomephone("789").withSecondphone("777")
              .withEmail("mail1@mail.ru").withEmail2("mail2@mail.ru").withEmail3("mail3@mail.ru")
              .withPhoto(new File(app.getProperties().getProperty("addressbook.photo")))
              .inGroup(groups.iterator().next()));
    }
  }

  @Test
  public void testDeleteContactFromGroup() {
    app.goTo().gotoHomePage();
    ContactData contact = selectContact();
    Groups before = contact.getGroups();
    GroupData deletedGroup = selectGroup(contact);
    app.contact().deleteGroupFromContact(contact, deletedGroup);
    Groups after = app.db().getContact(contact.getId()).getGroups();
    assertThat(after, equalTo(before.without(deletedGroup)));
  }

  private ContactData selectContact() {
    Groups groups = app.db().groups();
    Contacts contacts = app.db().contacts();
    for (ContactData contact: contacts) {
      if (contact.getGroups().size() > 0) {
        return contact;
      }
    }
    ContactData contact = contacts.iterator().next();
    app.contact().addGroupToContact(contact, groups.iterator().next());
    return app.db().getContact(contact.getId());
  }

  private GroupData selectGroup(ContactData contact) {
    Groups groups = app.db().groups();
    for (GroupData g: groups) {
      if (contact.getGroups().contains(g)) {
        return g;
      }
    }
    GroupData group = groups.iterator().next();
    app.contact().addGroupToContact(contact, group);
    return group;
  }
}
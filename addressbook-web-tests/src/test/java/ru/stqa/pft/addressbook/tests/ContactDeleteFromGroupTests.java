package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteFromGroupTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
    if (app.db().contacts().size() == 0) {
      app.goTo().gotoHomePage();
      app.contact().createContact(new ContactData()
              .withFirstname("Michael").withLastname("Koval").withNickname("Hors68").withTitle("tester").withCompany("Cinimex").withAddress("Voronej")
              .withMobilephone("123").withWorkphone("456").withEmail("mail@mail.ru"));
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
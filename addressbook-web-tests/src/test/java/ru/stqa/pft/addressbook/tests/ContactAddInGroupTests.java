package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import java.util.Collection;
import java.util.HashSet;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactAddInGroupTests extends TestBase{

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
  public void testAddContactInGroup() {
    app.goTo().gotoHomePage();
    ContactData addedContact = selectContact();
    Groups before = addedContact.getGroups();
    GroupData addedGroup = selectGroup(addedContact);
    app.contact().addGroupToContact(addedContact, addedGroup);
    Groups after = app.db().getContact(addedContact.getId()).getGroups();
    assertThat(after, equalTo(before.withAdded(addedGroup)));
  }

  private ContactData selectContact() {
    Groups groups = app.db().groups();
    Contacts contacts = app.db().contacts();
    for (ContactData contact: contacts) {
      if (contact.getGroups().size() < groups.size()) {
        return contact;
      }
    }
    app.goTo().groupPage();
    app.group().create(new GroupData().withName("test1"));
    app.goTo().gotoHomePage();
    return contacts.iterator().next();
  }

  private GroupData selectGroup(ContactData contact) {
    Groups groups = app.db().groups();
    Collection<GroupData> groupsWithoutContacts = new HashSet(groups);
    groupsWithoutContacts.removeAll(contact.getGroups());
    return groupsWithoutContacts.iterator().next();
  }
}
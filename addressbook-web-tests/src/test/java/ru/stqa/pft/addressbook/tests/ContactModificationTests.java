package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    Groups groups = app.db().groups();
    if (app.db().contacts().size() == 0) {
      app.contact().createContact(new ContactData()
              .withFirstname("Michael").withLastname("Koval").withNickname("Hors68").withTitle("tester").withCompany("Cinimex").withAddress("Voronej")
              .withMobilephone("123").withWorkphone("456").withEmail("mail@mail.ru").inGroup(groups.iterator().next()));
    }
  }

  @Test (enabled = true)
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    File photo = new File("src/test/resources/stru.png");
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("Michael_edited").withLastname("Koval_edited").withNickname("Hors68_edited").withTitle("tester_edited")
            .withCompany("Cinimex_edited").withAddress("Voronej_edited").withMobilephone("123_edited").withWorkphone("456_edited").withEmail("mail_edited@mail.ru").withPhoto(photo);
    app.contact().modify(contact);
    app.goTo().gotoHomePage();
    Contacts after = app.db().contacts();
    assertThat(after.size(), equalTo(before.size()));
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }
}
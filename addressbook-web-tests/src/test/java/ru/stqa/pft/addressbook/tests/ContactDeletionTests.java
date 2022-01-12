package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

public class ContactDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.contact().createContact(new ContactData()
              .withFirstname("Michael").withLastname("Koval").withNickname("Hors68").withTitle("tester").withCompany("Cinimex").withAddress("Voronej")
              .withMobilephone("123").withWorkphone("456").withEmail("mail@mail.ru").withGroup("test1"));
    }
  }

  @Test (enabled = true)
  public void testAddressDeletion() {
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().gotoHomePage();
    Contacts after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);
    Assert.assertEquals(after, before.without(deletedContact));
  }
}
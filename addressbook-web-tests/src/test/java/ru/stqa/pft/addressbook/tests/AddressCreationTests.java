package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddressData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class AddressCreationTests extends TestBase {

  @Test (enabled = true)
  public void testAddressCreation() {
    Contacts before = app.contact().all();
    AddressData address = new AddressData()
            .withFirstname("Michael").withLastname("Koval").withNickname("Hors68").withTitle("tester").withCompany("Cinimex").withAddress("Voronej")
            .withMobilephone("123").withWorkphone("456").withEmail("mail@mail.ru").withGroup("test1");
    app.contact().createContact(address);
    app.goTo().gotoHomePage();
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(address.withId(after.stream().mapToInt((a) -> a.getId()).max().getAsInt()))));
  }
}
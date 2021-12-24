package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddressData;

public class AddressModificationTests extends TestBase{

  @Test
  public void testAddressModification() {
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new AddressData("Michael", "Koval", "Hors68", "tester", "Cinimex", "Voronej", "123", "456", "mail@mail.ru", "test1"));
    }
    app.getContactHelper().selectAddress();
    app.getContactHelper().initAddressModification();
    app.getContactHelper().fillAddressForm(new AddressData("Michael_edited", "Koval_edited", "Hors68_edited", "tester_edited", "Cinimex_edited", "Voronej_edited", "123_edited", "456_edited", "mail_edited@mail.ru", null), false);
    app.getContactHelper().submitAddressModification();
  }
}
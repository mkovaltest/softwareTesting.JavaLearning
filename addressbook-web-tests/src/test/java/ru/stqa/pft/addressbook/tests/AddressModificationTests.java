package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddressData;

public class AddressModificationTests extends TestBase{

  @Test
  public void testAddressModification() {
    app.getContactHelper().selectAddress();
    app.getContactHelper().initAddressModification();
    app.getContactHelper().fillAddressForm(new AddressData("Michael_edited", "Koval_edited", "Hors68_edited", "tester_edited", "Cinimex_edited", "Voronej_edited", "123_edited", "456_edited", "mail_edited@mail.ru"));
    app.getContactHelper().submitAddressModification();
  }
}
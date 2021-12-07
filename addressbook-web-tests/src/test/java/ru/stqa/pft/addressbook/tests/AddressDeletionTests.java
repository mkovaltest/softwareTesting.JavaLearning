package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddressData;
import ru.stqa.pft.addressbook.model.GroupData;

public class AddressDeletionTests extends TestBase{

  @Test
  public void testAddressDeletion() {
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new AddressData("Michael", "Koval", "Hors68", "tester", "Cinimex", "Voronej", "123", "456", "mail@mail.ru", "test1"), true);
    }
    app.getContactHelper().selectAddress();
    app.getContactHelper().deleteSelectedAddress();
    app.getContactHelper().closeAddressDeletionAlert();
  }
}
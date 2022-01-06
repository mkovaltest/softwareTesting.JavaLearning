package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddressData;

import java.util.List;

public class AddressDeletionTests extends TestBase{

  @Test (enabled = false)
  public void testAddressDeletion() {
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new AddressData("Michael", "Koval", "Hors68", "tester", "Cinimex", "Voronej", "123", "456", "mail@mail.ru", "test1"));
    }
    List<AddressData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectAddress(before.size() - 1);
    app.getContactHelper().deleteSelectedAddress();
    app.getContactHelper().closeAddressDeletionAlert();
    app.getNavigationHelper().gotoHomePage();
    List<AddressData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }
}
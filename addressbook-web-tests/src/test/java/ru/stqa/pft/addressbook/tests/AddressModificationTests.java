package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddressData;

import java.util.Comparator;
import java.util.List;

public class AddressModificationTests extends TestBase{

  @Test (enabled = false)
  public void testAddressModification() {
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new AddressData("Michael", "Koval", "Hors68", "tester", "Cinimex", "Voronej", "123", "456", "mail@mail.ru", "test1"));
    }
    List<AddressData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initAddressModification(before.size() - 1);
    AddressData address = new AddressData("Michael_edited", "Koval_edited", "Hors68_edited", "tester_edited", "Cinimex_edited", "Voronej_edited", "123_edited", "456_edited", "mail_edited@mail.ru", null);
    app.getContactHelper().fillAddressForm(address, false);
    app.getContactHelper().submitAddressModification();
    app.goTo().gotoHomePage();
    List<AddressData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());
    before.remove(before.size() - 1);
    before.add(address);
    Comparator<AddressData> byId = Comparator.comparingInt(AddressData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddressData;

import java.util.Comparator;
import java.util.List;

public class AddressCreationTests extends TestBase {

  @Test
  public void testAddressCreation() {
    List<AddressData> before = app.getContactHelper().getContactList();
    AddressData address = new AddressData("Michael", "Koval", "Hors68", "tester", "Cinimex", "Voronej", "123", "456", "mail@mail.ru", "test1");
    app.getContactHelper().createContact(address);
    List<AddressData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
    before.add(address);
    Comparator<AddressData> byId = (a1, a2) -> Integer.compare(a1.getId(), a2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
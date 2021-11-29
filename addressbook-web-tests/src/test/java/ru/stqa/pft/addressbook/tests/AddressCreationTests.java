package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddressData;

public class AddressCreationTests extends TestBase {

  @Test
  public void testAddressCreation() {
    app.getContactHelper().initAddressCreation();
    app.getContactHelper().fillAddressForm(new AddressData("Michael", "Koval", "Hors68", "tester", "Cinimex", "Voronej", "123", "456", "mail@mail.ru"));
    app.getContactHelper().submitAddressCreation();
    app.getContactHelper().returnToAddressCreation();
  }
}
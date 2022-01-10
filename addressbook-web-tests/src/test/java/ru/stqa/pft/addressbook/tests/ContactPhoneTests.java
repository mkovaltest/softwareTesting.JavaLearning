package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddressData;

public class ContactPhoneTests extends TestBase{

  @Test
  public void testContactPhones() {
    app.goTo().gotoHomePage();
    AddressData contact = app.contact().all().iterator().next();
    AddressData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
  }
}

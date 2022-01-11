package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddressData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactPhoneTests extends TestBase {

  @Test
  public void testContactPhones() {
    app.goTo().gotoHomePage();
    AddressData contact = app.contact().all().iterator().next();
    AddressData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllphones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(spaceCleaned(contact.getAddress()), equalTo(mergeAddress(contactInfoFromEditForm)));
    assertThat(spaceCleaned(contact.getAllemails()), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  private String mergePhones(AddressData contact) {
    return Arrays.asList(contact.getHomephone(), contact.getMobilephone(), contact.getWorkphone())
            .stream()
            .map(ContactPhoneTests::phoneCleaned)
            .filter(s -> !s.equals("")).collect(Collectors.joining("\n"));
  }

  private String mergeAddress(AddressData contact) {
    return Arrays.asList(contact.getAddress())
            .stream()
            .map(ContactPhoneTests::spaceCleaned)
            .filter(s -> !s.equals("")).collect(Collectors.joining(""));
  }

  private String mergeEmails(AddressData contact) {
    return Arrays.asList(contact.getEmail1(), contact.getEmail2(), contact.getEmail3())
            .stream()
            .map(ContactPhoneTests::spaceCleaned)
            .filter(s -> !s.equals("")).collect(Collectors.joining(""));
  }

  public static String phoneCleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

  public static String spaceCleaned(String phone) {
    return phone.replaceAll("\\s", "");
  }
}
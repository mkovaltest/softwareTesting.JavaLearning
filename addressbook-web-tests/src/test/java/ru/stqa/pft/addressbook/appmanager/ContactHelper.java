package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.AddressData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void initAddressCreation() {
    click(By.linkText("add new"));
  }

  public void submitAddressCreation() {
    click(By.name("submit"));
  }

  public void fillAddressForm(AddressData addressData, boolean creation) {
    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(addressData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
    type(By.name("firstname"), addressData.getFirstname());
    type(By.name("lastname"), addressData.getLastname());
    type(By.name("nickname"), addressData.getNickname());
    type(By.name("title"), addressData.getTitle());
    type(By.name("company"), addressData.getCompany());
    type(By.name("address"), addressData.getAddress());
    type(By.name("mobile"), addressData.getMobilephone());
    type(By.name("work"), addressData.getWorkphone());
    type(By.name("email"), addressData.getEmail());
  }

  public void fillAddressForm(AddressData addressData) {
    new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(addressData.getGroup());
    type(By.name("firstname"), addressData.getFirstname());
    type(By.name("lastname"), addressData.getLastname());
    type(By.name("nickname"), addressData.getNickname());
    type(By.name("title"), addressData.getTitle());
    type(By.name("company"), addressData.getCompany());
    type(By.name("address"), addressData.getAddress());
    type(By.name("mobile"), addressData.getMobilephone());
    type(By.name("work"), addressData.getWorkphone());
    type(By.name("email"), addressData.getEmail());
  }

  public void selectAddressById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void deleteSelectedAddress() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void closeAddressDeletionAlert() {
    wd.switchTo().alert().accept();
  }

  public void initAddressModificationById(int id) {
    wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
  }

  public void submitAddressModification() {
    click(By.name("update"));
  }

  public void createContact(AddressData contact) {
    initAddressCreation();
    try {
      fillAddressForm(contact);
    } catch (NoSuchElementException e) {
      new NavigationHelper(wd).groupPage();
      new GroupHelper(wd).create(new GroupData().withName(contact.getGroup()));
      initAddressCreation();
      fillAddressForm(contact);
    }
    submitAddressCreation();
  }

  public void modify(AddressData address) {
    initAddressModificationById(address.getId());
    fillAddressForm(address, false);
    submitAddressModification();
  }

  public void delete(AddressData address) {
    selectAddressById(address.getId());
    deleteSelectedAddress();
    closeAddressDeletionAlert();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      AddressData contact = new AddressData().withId(id).withFirstname(firstname).withLastname(lastname);
      contacts.add(contact);
    }
    return contacts;
  }
}
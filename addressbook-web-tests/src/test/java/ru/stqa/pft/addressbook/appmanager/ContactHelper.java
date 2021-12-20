package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.AddressData;
import ru.stqa.pft.addressbook.model.GroupData;

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

  public void selectAddress() {
    click(By.name("selected[]"));
  }

  public void deleteSelectedAddress() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void closeAddressDeletionAlert() {
    wd.switchTo().alert().accept();
  }

  public void initAddressModification() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void submitAddressModification() {
    click(By.name("update"));
  }

  public void createContact(AddressData contact, boolean creation) {
    initAddressCreation();
    try {
      fillAddressForm(contact, creation);
    } catch (NoSuchElementException e) {
      new NavigationHelper(wd).gotoGroupPage();
      new GroupHelper(wd).createGroup(new GroupData(contact.getGroup(), null, null));
      initAddressCreation();
      fillAddressForm(contact, creation);
    }
    submitAddressCreation();
    new NavigationHelper(wd).gotoHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }
}
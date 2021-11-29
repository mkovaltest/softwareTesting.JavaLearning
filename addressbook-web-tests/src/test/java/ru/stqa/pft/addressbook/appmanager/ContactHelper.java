package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.AddressData;

public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void initAddressCreation() {
    click(By.linkText("add new"));
  }

  public void submitAddressCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillAddressForm(AddressData addressData) {
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

  public void returnToAddressCreation() {
    click(By.linkText("home page"));
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
}
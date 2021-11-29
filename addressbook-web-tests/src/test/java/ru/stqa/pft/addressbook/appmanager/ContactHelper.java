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
    type(By.name("mobile"), addressData.getMobile());
    type(By.name("work"), addressData.getWork());
    type(By.name("email"), addressData.getEmail());
  }

  public void returnToAddressCreation() {
    click(By.linkText("home page"));
  }
}
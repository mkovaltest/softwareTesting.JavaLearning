package ru.stqa.pft.addressbook.model;

import java.io.File;
import java.util.Objects;

public class AddressData {
  private int id = Integer.MAX_VALUE;
  private String firstname;
  private String lastname;
  private String nickname;
  private String title;
  private String company;
  private String address;
  private String homephone;
  private String mobilephone;
  private String workphone;
  private String allphones;
  private String email;
  private String allemails;
  private String email1;
  private String email2;
  private String email3;
  private String group;
  private File photo;

  public AddressData withId(int id) {
    this.id = id;
    return this;
  }

  public AddressData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public AddressData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public AddressData withNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  public AddressData withTitle(String title) {
    this.title = title;
    return this;
  }

  public AddressData withCompany(String company) {
    this.company = company;
    return this;
  }

  public AddressData withAddress(String address) {
    this.address = address;
    return this;
  }

  public AddressData withHomephone(String homephone) {
    this.homephone = homephone;
    return this;
  }

  public AddressData withMobilephone(String mobilephone) {
    this.mobilephone = mobilephone;
    return this;
  }

  public AddressData withWorkphone(String workphone) {
    this.workphone = workphone;
    return this;
  }

  public AddressData withEmail(String email) {
    this.email = email;
    return this;
  }

  public AddressData withGroup(String group) {
    this.group = group;
    return this;
  }

  public AddressData withAllphones(String allphones) {
    this.allphones = allphones;
    return this;
  }

  public AddressData withAllemails(String allemails) {
    this.allemails = allemails;
    return this;
  }

  public AddressData withEmail1(String email1) {
    this.email1 = email1;
    return this;
  }

  public AddressData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public AddressData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public AddressData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }

  public File getPhoto() {
    return photo;
  }

  public String getAllemails() {
    return allemails;
  }

  public String getEmail1() {
    return email1;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getAllphones() {
    return allphones;
  }

  public int getId() {
    return id;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getNickname() {
    return nickname;
  }

  public String getTitle() {
    return title;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public String getHomephone() {
    return homephone;
  }

  public String getMobilephone() {
    return mobilephone;
  }

  public String getWorkphone() {
    return workphone;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AddressData that = (AddressData) o;
    return id == that.id && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname);
  }

  @Override
  public String toString() {
    return "AddressData{" +
            "firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", address='" + address + '\'' +
            ", allphones='" + allphones + '\'' +
            ", allemails='" + allemails + '\'' +
            '}';
  }
}
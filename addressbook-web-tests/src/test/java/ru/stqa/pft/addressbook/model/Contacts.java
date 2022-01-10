package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<AddressData> {

  private Set<AddressData> delegate;

  public Contacts (Contacts contacts) {
    this.delegate = new HashSet<>(contacts.delegate);
  }

  public Contacts() {
    this.delegate = new HashSet<>();
  }

  @Override
  protected Set<AddressData> delegate() {
    return delegate;
  }

  public Contacts withAdded(AddressData address) {
    Contacts contacts = new Contacts(this);
    contacts.add(address);
    return contacts;
  }

  public Contacts without(AddressData address) {
    Contacts contacts = new Contacts(this);
    contacts.remove(address);
    return contacts;
  }
}
package com.syl.snow.bean;

import java.io.Serializable;

/**
 * @author Bright
 * @date 2020/3/31 14:18
 * @describe
 */
public class PersonE2 implements Serializable {
    private int contactId;
    private String displayName;
    private String phoneNumber;
    private String address;

    public PersonE2() {
    }

    public PersonE2(int contactId, String displayName, String phoneNumber, String address) {
        this.contactId = contactId;
        this.displayName = displayName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    @Override
    public String toString() {
        return "PersonE2{" +
                "contactId=" + contactId +
                ", displayName='" + displayName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

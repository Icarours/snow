package com.syl.snow.bean;

/**
 * Created by Bright on 2019/2/12.
 *
 * @Describe dataBinding举例,使用的User
 * @Called
 */
public class User2 {
    private String firstName;
    private String lastName;

    public User2(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

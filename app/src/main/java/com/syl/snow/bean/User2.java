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

    @Override
    public String toString() {
        return "User2{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User2)) return false;

        User2 user2 = (User2) o;

        if (getFirstName() != null ? !getFirstName().equals(user2.getFirstName()) : user2.getFirstName() != null)
            return false;
        return getLastName() != null ? getLastName().equals(user2.getLastName()) : user2.getLastName() == null;
    }

    @Override
    public int hashCode() {
        int result = getFirstName() != null ? getFirstName().hashCode() : 0;
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        return result;
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

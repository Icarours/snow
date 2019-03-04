package com.syl.snow.bean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

/**
 * Created by Bright on 2019/2/12.
 *
 * @Describe dataBinding举例, 使用的User
 * @Called
 */
public class User2 {
    private static final String JSON_ID = "id";
    private static final String JSON_FIRST_NAME = "firstName";
    private static final String JSON_LAST_NAME = "lastName";
    private UUID mId;
    private String firstName;
    private String lastName;

    public User2() {
        mId = UUID.randomUUID();
    }

    public User2(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * 直接将json文件初始化为新的对象
     * @param json
     * @throws JSONException
     */
    public User2(JSONObject json) throws JSONException {
        mId = UUID.fromString(json.getString(JSON_ID));
        if (json.has(JSON_FIRST_NAME)) {
            firstName = json.getString(JSON_FIRST_NAME);
        }
        if (json.has(JSON_LAST_NAME))
            lastName = json.getString(JSON_LAST_NAME);
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

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_ID, mId.toString());
        json.put(JSON_FIRST_NAME, firstName);
        json.put(JSON_LAST_NAME, lastName);
        return json;
    }
}

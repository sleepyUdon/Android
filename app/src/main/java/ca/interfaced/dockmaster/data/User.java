package ca.interfaced.dockmaster.data;

import java.util.UUID;

/**
 * Created by vivianechan on 2017-06-30.
 */

public class User {
    private UUID mID;
    private String mFirstName;
    private String mLastName;
    private String mCompanyName;
    private String mEmail;
    private String mPassword;
    private String mPhoneNumber;

    // TODO: user picture

    public User() {
        mID = UUID.randomUUID();
    }


    public UUID getID() {
        return mID;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getCompanyName() {
        return mCompanyName;
    }

    public void setCompanyName(String companyName) {
        mCompanyName = companyName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }



}

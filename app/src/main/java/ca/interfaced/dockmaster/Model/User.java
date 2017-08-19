package ca.interfaced.dockmaster.Model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.LinkingObjects;

/**
 * Created by vivianechan on 2017-06-30.
 */

public class User extends RealmObject {

    private String lastName;
    private String companyName;
    private String email;
    private String password;
    private String phoneNumber;
    private String mobileNumber;
    private String image;

    private RealmList<Reservation> reservations;
    private RealmList<Project> projects;


    private String id;
    private String firstName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public RealmList<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(RealmList<Reservation> reservations) {
        this.reservations = reservations;
    }

    public RealmList<Project> getProjects() {
        return projects;
    }

    public void setProjects(RealmList<Project> projects) {
        this.projects = projects;
    }





}

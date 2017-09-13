package ca.interfaced.dockmaster.Model;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.LinkingObjects;

/**
 * Created by vivianechan on 2017-08-16.
 */

public class Reservation {

    private String address;
    private String companyName;
    private String endDate;
    private String fullName;
    private String itemName;
    private String notes;
    private String projectName;
    private String startDate;
    private String userId;

    public Reservation() {
        // Default constructor required for calls to DataSnapshot.getValue(Comment.class)
    }

    public Reservation(String address,String companyName,
                       String endDate, String fullName, String itemName,
                       String notes, String projectName, String startDate,
                       String userId) {

        this.address = address;
        this.companyName = companyName;
        this.endDate = endDate;
        this.fullName = fullName;
        this.itemName = itemName;
        this.notes = notes;
        this.projectName = projectName;
        this.startDate = startDate;
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
package ca.interfaced.dockmaster.Model;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;

/**
 * Created by vivianechan on 2017-08-16.
 */

public class Reservation extends RealmObject {

    private String id;
    private String startDate;
    private String endDate;
    private String assetName;
    private String notes;
    private String projectName;

    private RealmList<User> Users;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
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

    public RealmList<User> getUsers() {
        return Users;
    }

    public void setUsers(RealmList<User> users) {
        Users = users;
    }
}

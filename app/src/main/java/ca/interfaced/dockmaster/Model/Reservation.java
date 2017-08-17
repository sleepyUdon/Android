package ca.interfaced.dockmaster.Model;

import io.realm.RealmList;

/**
 * Created by vivianechan on 2017-08-16.
 */

public class Reservation {

    private String startDate;
    private String endDate;
    private String assetName;
    private String notes;
    private String projectName;


    private RealmList<User> User;
    private RealmList<Project> Project;
    private RealmList<Asset> Asset;

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

    public RealmList<ca.interfaced.dockmaster.Model.User> getUser() {
        return User;
    }

    public void setUser(RealmList<ca.interfaced.dockmaster.Model.User> user) {
        User = user;
    }

    public RealmList<ca.interfaced.dockmaster.Model.Project> getProject() {
        return Project;
    }

    public void setProject(RealmList<ca.interfaced.dockmaster.Model.Project> project) {
        Project = project;
    }

    public RealmList<ca.interfaced.dockmaster.Model.Asset> getAsset() {
        return Asset;
    }

    public void setAsset(RealmList<ca.interfaced.dockmaster.Model.Asset> asset) {
        Asset = asset;
    }
}

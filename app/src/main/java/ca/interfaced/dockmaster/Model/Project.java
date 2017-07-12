package ca.interfaced.dockmaster.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by vivianechan on 2017-06-30.
 */

public class Project {
    private UUID mID;
    private String mProjectName;
    private String mProjectStatus;
    private String mProjectAddress;
    private List<Item> mItems = new ArrayList<>();
    private Date mDate;
    private List<User> mUsers = new ArrayList<>();

    // TODO: project image

    public String getProjectStatus() {
        return mProjectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        mProjectStatus = projectStatus;
    }

    public List<Item> getItems() {
        return mItems;
    }

    public void setItems(List<Item> items) {
        mItems = items;
    }




    public Project() {
        this(UUID.randomUUID());
    }

    public Project(UUID id) {
        mID = id;
        mDate = new Date();
    }

    public UUID getID() {
        return mID;
    }

    public String getProjectName() {
        return mProjectName;
    }

    public void setProjectName(String projectName) {
        mProjectName = projectName;
    }

    public String getProjectAddress() {
        return mProjectAddress;
    }

    public void setProjectAddress(String projectAddress) {
        mProjectAddress = projectAddress;
    }


    // TODO: modify date

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }


    public List<User> getUsers() {
        return mUsers;
    }

    public void setUsers(List<User> users) {
        mUsers = users;
    }

}

package ca.interfaced.dockmaster;

import java.util.Date;
import java.util.UUID;

/**
 * Created by vivianechan on 2017-06-30.
 */

public class Project {
    private UUID mID;
    private String mProjectName;
    private String mProjectAddress;
    private Date mDate;
    // TODO: array of project contacts
    private String mProjectContact;
    // TODO: project image

    public Project() {
        mID = UUID.randomUUID();

        // TODO: modify date

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

    public String getProjectContact() {
        return mProjectContact;
    }

    public void setProjectContact(String projectContact) {
        mProjectContact = projectContact;
    }

    // TODO: modify date

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }
}

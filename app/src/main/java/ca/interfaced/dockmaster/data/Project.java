package ca.interfaced.dockmaster.data
        ;

import java.util.UUID;

/**
 * Created by vivianechan on 2017-06-30.
 */

public class Project {
    private UUID mID;
    private String mProjectName;
    private String mProjectAddress;
    // TODO: array of project contacts
    private String mProjectContact;
    // TODO: project image

    public Project() {
        mID = UUID.randomUUID();
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
}

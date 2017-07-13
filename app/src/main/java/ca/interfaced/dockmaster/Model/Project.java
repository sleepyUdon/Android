package ca.interfaced.dockmaster.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by vivianechan on 2017-06-30.
 */

public class Project extends RealmObject {
    @PrimaryKey
    private long id;
    private String projectName;
    private String ProjectAddress;
    private RealmList<Asset>assets;
    private RealmList<User>users;

//    private String ProjectStatus;
//    private Date mDate;
//    private List<User> mUsers = new ArrayList<>();

    // TODO: project image

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectAddress() {
        return ProjectAddress;
    }

    public void setProjectAddress(String projectAddress) {
        ProjectAddress = projectAddress;
    }

    public RealmList<Asset> getAssets() {
        return assets;
    }

    public void setAssets(RealmList<Asset> assets) {
        this.assets = assets;
    }

    public RealmList<User> getUsers() {
        return users;
    }

    public void setUsers(RealmList<User> users) {
        this.users = users;
    }




}

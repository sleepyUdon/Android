package ca.interfaced.dockmaster.Model;


import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by vivianechan on 2017-06-30.
                    */

public class Project extends RealmObject {

    @PrimaryKey
    private long id;
    private String mProjectName;
    private String mProjectAddress;
    private RealmList<Asset>mAssets;
    private RealmList<User>mUsers;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public RealmList<Asset> getAssets() {
        return mAssets;
    }

    public void setAssets(RealmList<Asset> assets) {
        mAssets = assets;
    }

    public RealmList<User> getUsers() {
        return mUsers;
    }

    public void setUsers(RealmList<User> users) {
        mUsers = users;
    }
//    private String ProjectStatus;
//    private Date mDate;
//    private List<User> mUsers = new ArrayList<>();

    // TODO: project image






}

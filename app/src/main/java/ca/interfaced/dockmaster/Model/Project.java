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
    private String ProjectName;
    private String ProjectAddress;
    private String ProjectContactName;
    private String ProjectAssetName;

//    private RealmList<Asset>Assets;
//    private RealmList<User>Users;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public String getProjectAddress() {
        return ProjectAddress;
    }

    public void setProjectAddress(String projectAddress) {
        ProjectAddress = projectAddress;
    }

    public String getProjectContactName() {
        return ProjectContactName;
    }

    public void setProjectContactName(String projectContactName) {
        ProjectContactName = projectContactName;
    }

    public String getProjectAssetName() {
        return ProjectAssetName;
    }

    public void setProjectAssetName(String projectAssetName) {
        ProjectAssetName = projectAssetName;
    }

//    public RealmList<Asset> getAssets() {
//        return Assets;
//    }
//
//    public void setAssets(RealmList<Asset> assets) {
//        Assets = assets;
//    }
//
//    public RealmList<User> getUsers() {
//        return Users;
//    }
//
//    public void setUsers(RealmList<User> users) {
//        Users = users;
//    }
//    private String ProjectStatus;
//    private Date mDate;
//    private List<User> mUsers = new ArrayList<>();

    // TODO: project image






}

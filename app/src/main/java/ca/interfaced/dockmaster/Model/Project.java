package ca.interfaced.dockmaster.Model;


import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by vivianechan on 2017-06-30.
                    */

public class Project extends RealmObject {

    private String id;
    private String ProjectName;
    private String ProjectAddress;
    private String image;

    private RealmList<User> Users;

    private RealmList<User> Contacts;
    private RealmList<Asset> Assets;



    public String getId() {
        return id;
    }

    public void setId(String id) {
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


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public RealmList<User> getUsers() {
        return Users; }

    public void setUsers(RealmList<User> users) {
        Users = users;
    }

    public RealmList<Asset> getAssets() {
        return Assets;
    }

    public void setAssets(RealmList<Asset> assets) {
        Assets = assets;
    }

    public RealmList<User> getContacts() {
        return Contacts;
    }

    public void setContacts(RealmList<User> contacts) {
        Contacts = contacts;
    }








}

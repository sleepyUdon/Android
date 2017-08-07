package ca.interfaced.dockmaster.Model;



import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.LinkingObjects;

/**
 * Created by vivianechan on 2017-07-10.
 */

public class Asset extends RealmObject {

    private String id;
    private String assetName;
    private String image;
    private String sitePlan;
    private String description;

    //    private String mType;
//    private String mKey;
//    private String mOpeningTime;
//    private String mClosingTime;
//    private String mNotes;
//    private List<String> images = new ArrayList<>();



    @LinkingObjects("Assets")
    private final RealmResults<Project> projects = null;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public RealmResults<Project> getProjects() {
        return projects;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSitePlan() {
        return sitePlan;
    }

    public void setSitePlan(String sitePlan) {
        this.sitePlan = sitePlan;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}

package ca.interfaced.dockmaster.Model;



import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.LinkingObjects;

/**
 * Created by vivianechan on 2017-07-10.
 */

public class Asset extends RealmObject {

    private long id;
    private String assetName;




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }


//    private String mType;
//    private String mKey;
//    private String mOpeningTime;
//    private String mClosingTime;
//    private String mNotes;
//    private List<String> images = new ArrayList<>();


}

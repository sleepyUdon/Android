package ca.interfaced.dockmaster.Model;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vivianechan on 2017-07-10.
 */

public class Item {

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
    }

    public String getOpeningTime() {
        return mOpeningTime;
    }

    public void setOpeningTime(String openingTime) {
        mOpeningTime = openingTime;
    }

    public String getClosingTime() {
        return mClosingTime;
    }

    public void setClosingTime(String closingTime) {
        mClosingTime = closingTime;
    }

    public String getNotes() {
        return mNotes;
    }

    public void setNotes(String notes) {
        mNotes = notes;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    private String mName;
    private String mType;
    private String mKey;
    private String mOpeningTime;
    private String mClosingTime;
    private String mNotes;
    private List<String> images = new ArrayList<>();


}

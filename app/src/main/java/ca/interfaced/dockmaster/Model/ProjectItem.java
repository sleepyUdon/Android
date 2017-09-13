package ca.interfaced.dockmaster.Model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

/**
 * Created by vivianechan on 2017-09-05.
 */
//@IgnoreExtraProperties

public class ProjectItem {

    public String address;
    public String bannerImage;

    public Double lat;
    public String listingImage;
    public Double lon;
    public String name;
    public String projectName;
    public String status;

    public ProjectItem() {
        // Default constructor required for calls to DataSnapshot.getValue(Comment.class)
    }

    public ProjectItem(String address,String bannerImage,
                        Double lat, String listingImage, Double lon, String name,
                       String projectName, String status) {

        this.address = address;
        this.bannerImage = bannerImage;
        this.lat = lat;
        this.listingImage = listingImage;
        this.lon = lon;
        this.name = name;
        this.projectName = projectName;
        this.status = status;

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getListingImage() {
        return listingImage;
    }

    public void setListingImage(String listingImage) {
        this.listingImage = listingImage;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

 }

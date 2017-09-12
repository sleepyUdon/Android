package ca.interfaced.dockmaster.Model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

/**
 * Created by vivianechan on 2017-09-05.
 */
@IgnoreExtraProperties

public class ProjectItem {

    public String address;
    public ArrayList<User> authorizedUsers;
    public String bannerImage;
//    public ArrayList<User> contacts;
//    public ArrayList<Asset> items;
//    public String lat;
    public String listingImage;
//    public String lon;
    public String name;
    public String projectName;
    public String status;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<User> getAuthorizedUsers() {
        return authorizedUsers;
    }

    public void setAuthorizedUsers(ArrayList<User> authorizedUsers) {
        this.authorizedUsers = authorizedUsers;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

//    public ArrayList<User> getContacts() {
//        return contacts;
//    }
//
//    public void setContacts(ArrayList<User> contacts) {
//        this.contacts = contacts;
//    }
//
//    public ArrayList<Asset> getItems() {
//        return items;
//    }
//
//    public void setItems(ArrayList<Asset> items) {
//        this.items = items;
//    }

//    public String getLat() {
//        return lat;
//    }
//
//    public void setLat(String lat) {
//        this.lat = lat;
//    }

    public String getListingImage() {
        return listingImage;
    }

    public void setListingImage(String listingImage) {
        this.listingImage = listingImage;
    }

//    public String getLon() {
//        return lon;
//    }
//
//    public void setLon(String lon) {
//        this.lon = lon;
//    }

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


    public ProjectItem() {
        // Default constructor required for calls to DataSnapshot.getValue(Comment.class)
    }

    public ProjectItem(String address, ArrayList<User>authorizedUsers, String bannerImage,  String listingImage, String name, String projectName, String status) {
        this.address = address;
        this.authorizedUsers = authorizedUsers;
        this.bannerImage = bannerImage;
//        this.contacts = contacts;
//        this.items = items;
        this.listingImage = listingImage;
//        this.lon = lon;
        this.name = name;
        this.projectName = projectName;
        this.status = status;

    }


 }

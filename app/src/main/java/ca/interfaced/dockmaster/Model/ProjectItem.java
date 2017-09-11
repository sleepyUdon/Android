package ca.interfaced.dockmaster.Model;

/**
 * Created by vivianechan on 2017-09-05.
 */

public class ProjectItem {

    public String name;
    public String id;


    public ProjectItem() {
        // Default constructor required for calls to DataSnapshot.getValue(Comment.class)
    }

    public ProjectItem(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public ProjectItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
 }

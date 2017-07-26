package ca.interfaced.dockmaster.Model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by vivianechan on 2017-07-26.
 */

public class Projects {

    private static Projects sProjects;
    private List<Project> mProjects;


    //    Check if there is already a Projects singleton if not create one
    public static  Projects get(Context context) {
        if (sProjects == null) {
            sProjects = new Projects(context);
        }
        return sProjects;
    }


//    Create dummy projects
    private Projects(Context context) {
        mProjects = new ArrayList<>();

        Project project = new Project();
        project.setProjectName("111 Richmond");
        project.setProjectAddress("111 Richmond Street");
        mProjects.add(project);


        project = new Project();
        project.setProjectName("55 Spadina");
        project.setProjectAddress("55 Spadina Avenue");
        mProjects.add(project);
    }


    //    Get all projects
    public List<Project> getProjects() {
        return mProjects;
    }

    //    Get a project by its id
    public Project getProject(UUID id) {
        for (Project project: mProjects) {
            if (project.getId().equals(id)) {
                return project;
            }
        }
        return null;
    }

}


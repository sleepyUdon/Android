package ca.interfaced.dockmaster.data;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by vivianechan on 2017-06-30.
 */

public class ProjectsList {

    private static ProjectsList sProjectsList;
    private final List<Project> mProjects;

    public static ProjectsList get(Context context) {
        if (sProjectsList == null) {
            sProjectsList = new ProjectsList(context);
        }
        return sProjectsList;
    }

    private ProjectsList(Context context) {
        mProjects = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Project project = new Project();
            project.setProjectName("Project #" + i);
            project.setProjectAddress("Address #" + i);
            project.setProjectContact("Contact #" + i);
            mProjects.add(project);
            }
        }

    public List<Project> getProjects(){
        return mProjects;
    }

    public Project getProject(UUID id) {
        for (Project project : mProjects) {
            if (project.getID().equals(id)) {
                return project;
            }
        }
        return null;
    }
}

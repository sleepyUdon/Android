package ca.interfaced.dockmaster;

import android.content.Intent;
import android.support.v4.app.Fragment;

import ca.interfaced.dockmaster.Model.Project;

// NOT USED


public class ProjectsList_Activity extends SingleFragment_Activity {

    @Override
    protected Fragment createFragment() {
        return new Projectslist_Fragment();
    }
}




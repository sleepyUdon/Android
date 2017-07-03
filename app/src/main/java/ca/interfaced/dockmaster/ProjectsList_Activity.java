package ca.interfaced.dockmaster;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;


public class ProjectsList_Activity extends SingleFragment_Activity {

    @Override
    protected Fragment createFragment() {
        return new Projectslist_Fragment();
    }
}




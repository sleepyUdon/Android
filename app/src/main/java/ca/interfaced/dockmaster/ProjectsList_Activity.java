package ca.interfaced.dockmaster;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;


public class ProjectsList_Activity extends FragmentActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.projects_list);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.projects_list_fragment_container);

        if (fragment == null) {
            fragment = new fragment_projectslist();
            fm.beginTransaction()
                    .add(R.id.projects_list_fragment_container, fragment)
                    .commit();
        }
    }
}
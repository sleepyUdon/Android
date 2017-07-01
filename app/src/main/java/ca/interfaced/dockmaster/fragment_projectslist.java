package ca.interfaced.dockmaster;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by vivianechan on 2017-07-01.
 */

public class fragment_projectslist extends Fragment{

    private Project mProject;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProject = new Project();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.projects_list_fragment, container, false);


        return v;

    }
}

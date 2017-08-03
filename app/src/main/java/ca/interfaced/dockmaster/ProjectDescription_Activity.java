package ca.interfaced.dockmaster;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;


public class ProjectDescription_Activity extends SingleFragment_Activity {


    private static final String EXTRA_PROJECT_ID = "ca.interfaced.dockmaster.project_id";

    public static Intent newIntent(Context packageContext, String projectID) {
        Intent intent = new Intent(packageContext, ProjectDescription_Activity.class);
        intent.putExtra(EXTRA_PROJECT_ID, projectID);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        String projectID = (String)getIntent().getSerializableExtra(EXTRA_PROJECT_ID);
        return ProjectDescription_Fragment.newInstance(projectID);
    }

}
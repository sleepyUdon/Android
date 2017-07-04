package ca.interfaced.dockmaster;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;


import java.util.UUID;

public class ProjectDescription_Activity extends SingleFragment_Activity {


    private static final String EXTRA_PROJECT_ID = "ca.interfaced.dockmaster.project_id";

    public static Intent newIntent(Context packageContext, UUID projectID) {
        Intent intent = new Intent(packageContext, ProjectDescription_Activity.class);
        intent.putExtra(EXTRA_PROJECT_ID, projectID);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID projectID = (UUID)getIntent().getSerializableExtra(EXTRA_PROJECT_ID);
        return ProjectDescription_Fragment.newInstance(projectID);
    }

}
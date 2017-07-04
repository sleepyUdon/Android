package ca.interfaced.dockmaster;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by vivianechan on 2017-07-03.
 */

public class ProjectPager_Activity extends AppCompatActivity {

    private static final String EXTRA_PROJECT_ID = "ca.interfaced.dockmaster.project_id";


    private ViewPager mViewPager;
    private List<Project> mProjects;

    public static Intent newIntent(Context packageContext, UUID projectID) {
        Intent intent = new Intent(packageContext, ProjectPager_Activity.class);
        intent.putExtra(EXTRA_PROJECT_ID, projectID);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_pager);

        UUID projectID = (UUID)getIntent().getSerializableExtra(EXTRA_PROJECT_ID);

        mViewPager = (ViewPager) findViewById(R.id.project_pager);

        mProjects = ProjectsList.get(this).getProjects();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Project project = mProjects.get(position);
                return ProjectDescription_Fragment.newInstance(project.getID());
            }

            @Override
            public int getCount() {
                return mProjects.size();
            }
        });

        for (int i = 0; i < mProjects.size(); i++) {
            if (mProjects.get(i).getID().equals(projectID)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}

package ca.interfaced.dockmaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;
import java.util.UUID;

/**
 * Created by vivianechan on 2017-07-03.
 */

public class ProjectDescription_Fragment extends Fragment{

    private static final String ARG_PROJECT_ID = "crime_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;



    private Project mProject;
    private TextView mProjectName_textView;
    private TextView mProjectAddress_textView;
    private TextView mProjectDate_textView;
    private Button mBook_button;

    public static ProjectDescription_Fragment newInstance(UUID projectID) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_PROJECT_ID, projectID);

        ProjectDescription_Fragment fragment = new ProjectDescription_Fragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID projectID = (UUID) getArguments().getSerializable(ARG_PROJECT_ID);
        mProject = ProjectsList.get(getActivity()).getProject(projectID);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.project_description_fragment, container, false);

        mProjectName_textView = (TextView) v.findViewById(R.id.projectDescription_projectName);
        mProjectName_textView.setText(mProject.getProjectName());

        mProjectAddress_textView = (TextView) v.findViewById(R.id.projectDescription_projectAddress);
        mProjectAddress_textView.setText(mProject.getProjectAddress());

        mProjectDate_textView = (TextView) v.findViewById(R.id.projectDescription_projectDate);

        mBook_button = (Button) v.findViewById(R.id.projectDescription_bookButton);
        updateDate();
        mBook_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePicker_Fragment dialog = DatePicker_Fragment.newInstance(mProject.getDate());
                dialog.setTargetFragment(ProjectDescription_Fragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePicker_Fragment.EXTRA_DATE);
            mProject.setDate(date);
            updateDate();
        }
    }

    private void updateDate() {
        mBook_button.setText(mProject.getDate().toString());
    }


}



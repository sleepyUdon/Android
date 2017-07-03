package ca.interfaced.dockmaster;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.UUID;

/**
 * Created by vivianechan on 2017-07-03.
 */

public class ProjectDescription_Fragment extends Fragment{

    private Project mProject;
    private TextView mProjectName_textView;
    private TextView mProjectAddress_textView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID projectID = (UUID) getActivity().getIntent().getSerializableExtra(ProjectDescription_Activity.EXTRA_PROJECT_ID);
        mProject = ProjectsList.get(getActivity()).getProject(projectID);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.project_description_fragment, container, false);

        mProjectName_textView = (TextView) v.findViewById(R.id.projectDescription_projectName);
        mProjectName_textView.setText(mProject.getProjectName());

        mProjectAddress_textView = (TextView) v.findViewById(R.id.projectDescription_projectAddress);
        mProjectAddress_textView.setText(mProject.getProjectAddress());


        return v;

    }
}



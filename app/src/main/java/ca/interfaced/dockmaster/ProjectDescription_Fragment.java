package ca.interfaced.dockmaster;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import ca.interfaced.dockmaster.Model.Project;
import ca.interfaced.dockmaster.Model.User;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by vivianechan on 2017-07-03.
 */

public class ProjectDescription_Fragment extends Fragment{

    private static final String ARG_PROJECT_ID = "crime_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;

    private String mProjectName;
    private String mProjectAddress;


    private TextView mProjectName_ediText;
    private TextView mProjectAddress_editText;





    public static ProjectDescription_Fragment newInstance(long projectID) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_PROJECT_ID, projectID);

        ProjectDescription_Fragment fragment = new ProjectDescription_Fragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        long projectID = (long) getArguments().getSerializable(ARG_PROJECT_ID);
        Realm realm = Realm.getDefaultInstance();
        Project project = realm.where(Project.class)
                .equalTo("id", projectID)
                .findFirst();
        mProjectName = project.getProjectName();
        mProjectAddress = project.getProjectAddress();

    }

    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.projectdescription_fragment, container, false);


//        mProjectName_ediText = (EditText) v.findViewById(R.id.project);
//        mProjectName_ediText.setText(mProjectName);
//
//
//        mProjectAddress_editText = (EditText) v.findViewById(R.id.projectDescription_projectAddress);
//        mProjectAddress_editText.setText(mProjectAddress);

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode != Activity.RESULT_OK) {
//            return;
//        }
//        if (requestCode == REQUEST_DATE) {
//            Date date = (Date) data.getSerializableExtra(DatePicker_Fragment.EXTRA_DATE);
//            mProject.setDate(date);
//            updateDate();
//        }
    }

//    private void updateDate() {
//        mBook_button.setText(mProject.getDate().toString());
//    }


}



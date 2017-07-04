package ca.interfaced.dockmaster;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by vivianechan on 2017-07-03.
 */


public class UserSettings_Fragment extends Fragment {

    private User mUser;
    private TextView mfirstName_textView;
    private TextView mlastName_textView;
    private TextView mCompany_textView;
    private TextView mEmail_textView;
    private TextView mPassword_textView;
    private TextView mPhoneNumber_textView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mUser = new User();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.usersettings_fragment, container, false);

        mfirstName_textView = (TextView) v.findViewById(R.id.userSettings_firstName);
        mlastName_textView = (TextView) v.findViewById(R.id.userSettings_lastName);
        mCompany_textView = (TextView) v.findViewById(R.id.userSettings_companyName);
        mEmail_textView = (TextView) v.findViewById(R.id.userSettings_email);
        mPassword_textView = (TextView) v.findViewById(R.id.userSettings_password);
        mPhoneNumber_textView = (TextView) v.findViewById(R.id.userSettings_password);

        return v;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu , MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.usersettings_fragment,  menu);
    }


}
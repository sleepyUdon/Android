package ca.interfaced.dockmaster;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ca.interfaced.dockmaster.Model.User;
import io.realm.Realm;
import io.realm.RealmResults;

public class Settings_Fragment extends Fragment {


    private static String mContactID ;
    private static String firstName ;
    private static String lastName ;
    private static String companyName ;
    private static String phoneNumber ;
    private static String mobileNumber ;
    private static String email ;

    private TextView firstName_textView;
    private TextView lastName_textView;
    private TextView companyName_textView;
    private TextView phoneNumber_textView;
    private TextView mobileNumber_textView;
    private TextView email_textView;

    public static ca.interfaced.dockmaster.Settings_Fragment newInstance() {
        return new ca.interfaced.dockmaster.Settings_Fragment();
    }
    public Settings_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Realm realm = Realm.getDefaultInstance();


        RealmResults<User> user = realm.where(User.class)
                .equalTo("id","1")
                .findAll();
        firstName = user.first().getFirstName();
        lastName = user.first().getLastName();
        companyName = user.first().getCompanyName();
        phoneNumber = user.first().getPhoneNumber();
        mobileNumber = user.first().getMobileNumber();
        email = user.first().getEmail();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.settings_fragment, container, false);

        firstName_textView = (TextView) v.findViewById(R.id.firstName);
        firstName_textView.setText(firstName);

        lastName_textView = (TextView) v.findViewById(R.id.lastName);
        lastName_textView.setText(lastName);

        companyName_textView = (TextView) v.findViewById(R.id.companyName);
        companyName_textView.setText(companyName);

        phoneNumber_textView = (TextView) v.findViewById(R.id.phoneNumber);
        phoneNumber_textView.setText(phoneNumber);

        mobileNumber_textView = (TextView) v.findViewById(R.id.mobileNumber);
        mobileNumber_textView.setText(mobileNumber);

        email_textView = (TextView) v.findViewById(R.id.email);
        email_textView.setText(email);

        return v;
    }
}





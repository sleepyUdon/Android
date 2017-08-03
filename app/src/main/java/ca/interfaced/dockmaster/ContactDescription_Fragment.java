package ca.interfaced.dockmaster;


import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import ca.interfaced.dockmaster.Model.User;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import ca.interfaced.dockmaster.Model.User;



/**
 * Created by vivianechan on 2017-08-01.
 */

public class ContactDescription_Fragment extends Fragment {

    private static final String ARG_CONTACT_ID = "contact_id";

    private static String mContactID ;
    private static String picture;
    private static String firstName ;
    private static String lastName ;
    private static String companyName ;
    private static String phoneNumber ;
    private static String mobileNumber ;
    private static String email ;

    private ImageView picture_imageView;
    private TextView firstName_textView;
    private TextView lastName_textView;
    private TextView companyName_textView;
    private TextView phoneNumber_textView;
    private TextView mobileNumber_textView;
    private TextView email_textView;



    public static ContactDescription_Fragment newInstance(String contactID) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CONTACT_ID, contactID);
        mContactID = contactID;
        ContactDescription_Fragment fragment = new ContactDescription_Fragment();
        fragment.setArguments(args);
        return fragment;
    }


    public static ca.interfaced.dockmaster.ContactDescription_Fragment newInstance() {
        return new ca.interfaced.dockmaster.ContactDescription_Fragment();
    }

    public ContactDescription_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Realm realm = Realm.getDefaultInstance();


        RealmResults<User> user = realm.where(User.class)
                .equalTo("id",mContactID)
                .findAll();
        picture = user.first().getImage();
        firstName = user.first().getFirstName();
        lastName = user.first().getLastName();
        companyName = user.first().getCompanyName();
        phoneNumber = user.first().getPhoneNumber();
        mobileNumber = user.first().getMobileNumber();
        email = user.first().getEmail();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.contact_description_fragment, container, false);

        picture_imageView = (ImageView) v.findViewById(R.id.profile_image);
        int resId = getResources().getIdentifier(picture,"drawable",getActivity().getPackageName());
        Drawable sitePlan = getActivity().getResources().getDrawable(resId);
        picture_imageView.setImageDrawable(sitePlan);

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





package ca.interfaced.dockmaster;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import ca.interfaced.dockmaster.Model.User;
import io.realm.Realm;
import io.realm.RealmResults;

public class Settings_Fragment extends Fragment {
    
    private static String firstName ;
    private static String lastName ;
    private static String companyName ;
    private static String phoneNumber ;
    private static String mobileNumber ;
    private static String email ;
    private static String picture;
    private User mUser ;

    private ImageView picture_imageView;
    private TextView firstName_textView;
    private TextView lastName_textView;
    private TextView companyName_textView;
    private TextView phoneNumber_textView;
    private TextView mobileNumber_textView;
    private TextView email_textView;

    private Button logout_button;

    public static ca.interfaced.dockmaster.Settings_Fragment newInstance() {
        return new ca.interfaced.dockmaster.Settings_Fragment();
    }
    public Settings_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String userID = getActivity().getIntent().getExtras().getString("userID");
        Log.d("extraFromLogin", userID);

        Realm realm = Realm.getDefaultInstance();
        User user = realm.where(User.class)
                .equalTo("email", userID)
                .findFirst();
        mUser = user;

        picture = user.getImage();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        companyName = user.getCompanyName();
        phoneNumber = user.getPhoneNumber();
        mobileNumber = user.getMobileNumber();
        email = user.getEmail();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.settings_fragment, container, false);

        picture_imageView = (ImageView) v.findViewById(R.id.profile_image);
        int resId = getResources().getIdentifier(mUser.getImage(),"drawable",getActivity().getPackageName());
        Drawable contactThumbnail = getActivity().getResources().getDrawable(resId);
        picture_imageView.setImageDrawable(contactThumbnail);


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

        logout_button = (Button) v.findViewById(R.id.logoutButton);
        logout_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Login_Activity.class);
                Settings_Fragment.this.startActivity(intent);
            }
        });

        return v;
    }
}





package ca.interfaced.dockmaster;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ca.interfaced.dockmaster.Model.Project;
import ca.interfaced.dockmaster.Model.User;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import ca.interfaced.dockmaster.Model.User;

import static android.R.id.message;


/**
 * Created by vivianechan on 2017-08-01.
 */

public class ContactDescription_Fragment extends Fragment {

    private static final String ARG_CONTACT_ID = "contact_id";
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;

    private static User mUser;
    private static String mContactID;
    private static String picture;
    private static String firstName;
    private static String lastName;
    private static String companyName;
    private static String phoneNumber;
    private static String mobileNumber;
    private static String email;

    private ImageView picture_imageView;
    private Button call_button;
    private Button message_button;
    private Button email_button;
    private TextView firstName_textView;
    private TextView lastName_textView;
    private TextView companyName_textView;
    private TextView phoneNumber_textView;
    private TextView mobileNumber_textView;
    private TextView email_textView;


    public static ca.interfaced.dockmaster.ContactDescription_Fragment newInstance() {
        return new ca.interfaced.dockmaster.ContactDescription_Fragment();
    }

    public ContactDescription_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String contactID = getActivity().getIntent().getExtras().getString("userID");
        Log.d("extraFromLogin", contactID);
        mContactID = contactID;

        Realm realm = Realm.getDefaultInstance();
        User user = realm.where(User.class)
                .equalTo("email", mContactID)
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
        View v = inflater.inflate(R.layout.contact_description_fragment, container, false);



        picture_imageView = (ImageView) v.findViewById(R.id.profile_image);
        int resId = getResources().getIdentifier(picture, "drawable", getActivity().getPackageName());
        Drawable sitePlan = getActivity().getResources().getDrawable(resId);
        picture_imageView.setImageDrawable(sitePlan);

        call_button = (Button) v.findViewById(R.id.callButton);
        call_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Call button selected", Toast.LENGTH_SHORT).show();
                dialContactPhone(phoneNumber);
            }
        });

        message_button = (Button) v.findViewById(R.id.messageButton);
        message_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Message button selected", Toast.LENGTH_SHORT).show();
                sendSMSMessage();
            }
        });

        email_button = (Button) v.findViewById(R.id.emailButton);
        email_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });

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

    private void dialContactPhone(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }

    protected void sendSMSMessage() {

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }

    protected void sendEmail() {
        Intent emailApp = new Intent(Intent.ACTION_SEND);
        emailApp.putExtra(Intent.EXTRA_EMAIL, email);
        emailApp.setType("message/rfc822");
        startActivity(Intent.createChooser(emailApp, "Send Email Via"));

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNumber, null, "", null, null);
                    Toast.makeText(getActivity(), "SMS Sent", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getActivity(), "SMS failed", Toast.LENGTH_SHORT).show();

                    return;
                }
            }
        }
    }
}






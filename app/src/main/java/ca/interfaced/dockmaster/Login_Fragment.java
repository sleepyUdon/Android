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
import android.widget.Toast;

import java.util.HashMap;

import ca.interfaced.dockmaster.Model.Asset;
import ca.interfaced.dockmaster.Model.Project;
import ca.interfaced.dockmaster.Model.Reservation;
import ca.interfaced.dockmaster.Model.User;
import ca.interfaced.dockmaster.app.SessionManager;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by vivianechan on 2017-06-30.
 */

public class Login_Fragment extends Fragment {

    SessionManager session;

    private String mUserName;
    private String mPassword;

    private EditText muserName_textField;
    private EditText mpassword_textField;
    private Button mlogin_button;
    private Button mcreateAccount_button;
    private Button mforgotPassword_button;
    public static final String EXTRA_MESSAGE = "com.interfaced.dockmaster.MESSAGE";



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
            }
        });

        realm.beginTransaction();

        Project project1 = realm.createObject(Project.class);
        project1.setId("1");
        project1.setProjectName("111 Richmond");
        project1.setProjectAddress("111 Richmond Street, Toronto");
        project1.setImage("project1");
        realm.insertOrUpdate(project1);

        Project project2 = realm.createObject(Project.class);
        project2.setId("2");
        project2.setProjectName("Manulife Center");
        project2.setProjectAddress("50 Bloor Street West, Toronto");
        project2.setImage("project2");
        realm.insertOrUpdate(project2);

        Project project3 = realm.createObject(Project.class);
        project3.setId("3");
        project3.setProjectName("Yonge and Sheppard");
        project3.setProjectAddress("1856 Sheppard Avenue, Toronto");
        project3.setImage("project3");
        realm.insertOrUpdate(project3);

        User user1 = realm.createObject(User.class);
        user1.setId("1");
        user1.setFirstName("Viviane");
        user1.setLastName("Chan");
        user1.setCompanyName("Interfaced");
        user1.setEmail("vivianechan@hotmail.com");
        user1.setPassword("password");
        user1.setPhoneNumber("6478365162");
        user1.setMobileNumber("6478365162");
        user1.setImage("vivianechan");
        realm.insertOrUpdate(user1);
        User user2 = realm.createObject(User.class);

        user2.setId("2");
        user2.setFirstName("John");
        user2.setLastName("Smith");
        user2.setCompanyName("PCL Constructors Inc.");
        user2.setEmail("john.smith@pcl.com");
        user2.setPassword("password");
        user2.setPhoneNumber("6471234567");
        user2.setMobileNumber("6471234567");
        user2.setImage("johnsmith");
        realm.insertOrUpdate(user2);

        Asset asset1 = realm.createObject(Asset.class);
        asset1.setId("1");
        asset1.setAssetName("Elevator A");
        asset1.setImage("elevator");
        asset1.setSitePlan("map");
        asset1.setDescription("Capacity: 2,500 kg\n" +
                "Accessible from loading dock A \n" +
                "Serves 2nd to 5th floor");
        realm.insertOrUpdate(asset1);

        Asset asset2 = realm.createObject(Asset.class);
        asset2.setId("2");
        asset2.setAssetName("Crane 1");
        asset2.setImage("crane");
        asset2.setSitePlan("map");
        asset2.setDescription("20-storeys crane");
        realm.insertOrUpdate(asset2);

        Reservation reservation1 = realm.createObject(Reservation.class);
        reservation1.setId("1");
        reservation1.setDate("15 July 2017");
        reservation1.setStartTime("10:30 AM");
        reservation1.setEndTime("11:30 AM");
        reservation1.setNotes("Delivery of tables");
        realm.insertOrUpdate(reservation1);

        project1.getUsers().add(user1);
        project1.getUsers().add(user2);
        project2.getUsers().add(user1);
        project2.getUsers().add(user2);
        project3.getUsers().add(user2);

        project1.getContacts().add(user2);
        project2.getContacts().add(user1);
        project3.getContacts().add(user2);

        user1.getProjects().add(project2);
        user2.getProjects().add(project1);
        user2.getProjects().add(project3);

        project1.getAssets().add(asset1);
        project2.getAssets().add(asset2);

        asset1.setProject(project1);
        asset2.setProject(project2);

        reservation1.setUser(user1);
        reservation1.setProject(project1);
        reservation1.setAsset(asset2);


        realm.commitTransaction();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.login_fragment, container, false);

        session = new SessionManager(getActivity().getApplicationContext());
        muserName_textField = (EditText)v.findViewById(R.id.userName_textField);
        mpassword_textField = (EditText)v.findViewById(R.id.password_textField);

        HashMap<String, String> user = session.getUserDetails();

        String email = user.get(SessionManager.KEY_EMAIL);
        mUserName = email;
        String password = user.get(SessionManager.KEY_PASSWORD);
        mPassword = password;

        if (email != null && password != null) {
            muserName_textField.setText(email);
            mpassword_textField.setText(password);
        }

        muserName_textField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mUserName = muserName_textField.getText().toString();
            }
        });

        mpassword_textField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                mPassword = mpassword_textField.getText().toString();
            }
        });


        mlogin_button = (Button)v.findViewById(R.id.login_button);
        // check if userName/password combination exists in database
        // return message is it doesn't exist
        mlogin_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Realm realm = Realm.getDefaultInstance();
                RealmQuery<User> userQuery = realm.where(User.class);
                userQuery.equalTo("email", mUserName)
                        .equalTo("password", mPassword)
                        .findAll();
                if (userQuery.count()!= 0) {
                    String userID = userQuery.findFirst().getEmail();
                    Intent intent = new Intent(getActivity(), Main_Activity.class);
                    Toast.makeText(getActivity(), userID, Toast.LENGTH_SHORT).show();
                    intent.putExtra("userID", userID);
                    session.createLoginSession(mUserName,mPassword);
                    Login_Fragment.this.startActivity(intent);

                } else {
                    Toast.makeText(getActivity(), "Invalid username / password", Toast.LENGTH_SHORT).show();
                }
            }
        });


        mcreateAccount_button = (Button)v.findViewById(R.id.createAccount_button);
        mcreateAccount_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreateAccount_Activity.class);
                Login_Fragment.this.startActivity(intent);
            }
        });

        mforgotPassword_button = (Button)v.findViewById(R.id.forgotPassword_button);
        mforgotPassword_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ForgotPassword_Activity.class);
                Login_Fragment.this.startActivity(intent);
            }
        });

        return v;

    }
}


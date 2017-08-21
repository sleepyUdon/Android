package ca.interfaced.dockmaster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ca.interfaced.dockmaster.Model.Project;
import ca.interfaced.dockmaster.Model.User;
import io.realm.Realm;
import io.realm.RealmQuery;

/**
 * Created by vivianechan on 2017-06-30.
 */

public class Createaccount_Fragment extends Fragment {

    private String mFirstName;
    private String mLastName;
    private String mCompanyName;
    private String mEmail;
    private String mPassword;
    private String mPhoneNumber;

    private EditText mfirstName_textField;
    private EditText mlastName_textField;
    private EditText mcompanyName_textField;
    private EditText memail_textField;
    private EditText mpassword_textField;
    private EditText mphoneNumber_textField;

    private Button mcreateAccount_button;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.createaccount_fragment, container, false);

        mfirstName_textField = (EditText) v.findViewById(R.id.firstName_textEdit);
        mfirstName_textField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mFirstName = s.toString();
            }
        });

        mlastName_textField = (EditText) v.findViewById(R.id.lastName_textEdit);
        mlastName_textField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mLastName = s.toString();
            }
        });

        mcompanyName_textField = (EditText) v.findViewById(R.id.companyName_textEdit);
        mcompanyName_textField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mCompanyName = s.toString();
            }

        });

        memail_textField = (EditText) v.findViewById(R.id.email_textEdit);
        memail_textField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mEmail = s.toString();
            }
        });

        mpassword_textField = (EditText) v.findViewById(R.id.password_textEdit);
        mpassword_textField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mPassword = s.toString();
            }
        });

        mphoneNumber_textField = (EditText) v.findViewById(R.id.phoneNumber_textEdit);
        mphoneNumber_textField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mPhoneNumber = s.toString();
            }
        });

        mcreateAccount_button = (Button) v.findViewById(R.id.createAccount_button);
        mcreateAccount_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Realm realm = Realm.getDefaultInstance();
                RealmQuery<User> userQuery = realm.where(User.class);
                userQuery.equalTo("email", mEmail)
                        .findAll();
                if (userQuery.count()!= 0) {
                    Toast.makeText(getActivity(), "This account already exists", Toast.LENGTH_SHORT).show();
                } else if ((mFirstName == "") || (mFirstName == null) ||
                        (mLastName == "") || (mLastName == null) ||
                        (mCompanyName == "") || (mCompanyName == null) ||
                        (mEmail == "") || (mEmail == null) ||
                        (mPassword == "") || (mPassword == null) ||
                        (mPhoneNumber == "") || (mPhoneNumber == null)) {
                    Toast.makeText(getActivity(), "Missing or invalid fields", Toast.LENGTH_SHORT).show();
                } else {
                    realm.beginTransaction();
                    User user = realm.createObject(User.class);
                    user.setFirstName(mFirstName);
                    user.setLastName(mLastName);
                    user.setCompanyName(mCompanyName);
                    user.setEmail(mEmail);
                    user.setPassword(mPassword);
                    user.setPhoneNumber(mPhoneNumber);
                    realm.insertOrUpdate(user);
                    realm.commitTransaction();

                    Toast.makeText(getActivity(), "Account created", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getActivity(), Login_Activity.class);
                    Createaccount_Fragment.this.startActivity(intent);
                }
            }
        });
        return v;
    }
}

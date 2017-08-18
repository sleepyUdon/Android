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

import ca.interfaced.dockmaster.Model.Project;
import ca.interfaced.dockmaster.Model.User;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by vivianechan on 2017-06-30.
 */

public class Login_Fragment extends Fragment {


    private EditText muserName_textField;
    private EditText mpassword_textField;
    private Button mlogin_button;
    private Button mcreateAccount_button;
    private Button mforgotPassword_button;
    public static final String EXTRA_MESSAGE = "com.interfaced.dockmaster.MESSAGE";



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.login_fragment, container, false);

        muserName_textField = (EditText)v.findViewById(R.id.userName_textField);
        muserName_textField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // check if userName exists in database
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mpassword_textField = (EditText)v.findViewById(R.id.password_textField);
        mpassword_textField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // check if password exists in database

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mlogin_button = (Button)v.findViewById(R.id.login_button);
        // check if userName/password combination exists in database
        // return message is it doesn't exist
        mlogin_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Main_Activity.class);
                Login_Fragment.this.startActivity(intent);
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


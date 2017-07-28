package ca.interfaced.dockmaster;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import ca.interfaced.dockmaster.Model.User;

/**
 * Created by vivianechan on 2017-06-30.
 */

public class Createaccount_Fragment extends Fragment {

    private User mUser;
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
        mUser = new User();
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
                mUser.setFirstName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

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
                mUser.setLastName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

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
                mUser.setCompanyName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

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
                mUser.setEmail(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

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
                mUser.setPassword(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mphoneNumber_textField = (EditText) v.findViewById(R.id.phoneNumber_textEdit);
        mphoneNumber_textField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mUser.setPhoneNumber(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mcreateAccount_button = (Button) v.findViewById(R.id.createAccount_button);

        return v;

    }
}

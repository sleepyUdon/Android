package ca.interfaced.dockmaster;

import android.support.v4.app.Fragment;


public class ForgotPasswordActivity extends SingleFragment_Activity {

    @Override
    protected Fragment createFragment() {
        return new fragment_forgotpassword();
    }

}
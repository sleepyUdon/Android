package ca.interfaced.dockmaster;

import android.support.v4.app.Fragment;

public class ForgotPassword_Activity extends SingleFragment_Activity {

    @Override
    protected Fragment createFragment() {
        return new Forgotpassword_Fragment();
    }

}
package ca.interfaced.dockmaster;

import android.support.v4.app.Fragment;


/**
 * Created by vivianechan on 2017-06-29.
 */

public class createAccountActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new fragment_createaccount();
    }


}

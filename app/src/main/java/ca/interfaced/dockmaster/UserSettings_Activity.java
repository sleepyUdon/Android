package ca.interfaced.dockmaster;

import android.support.v4.app.Fragment;



public class UserSettings_Activity extends SingleFragment_Activity {

    @Override
    protected Fragment createFragment() {
        return new UserSettings_Fragment();
    }
}

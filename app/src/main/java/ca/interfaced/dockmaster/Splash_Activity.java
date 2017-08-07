package ca.interfaced.dockmaster;


import android.support.v4.app.Fragment;

/**
 * Created by vivianechan on 2017-08-06.
 */


public class Splash_Activity extends SingleFragment_Activity {

    @Override
    protected Fragment createFragment() {
        return new Splash_Fragment();
    }


}
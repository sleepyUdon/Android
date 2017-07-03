package ca.interfaced.dockmaster;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;


/**
 * Created by vivianechan on 2017-06-28.
 */

public class Login_Activity extends SingleFragment_Activity {

@Override

protected Fragment createFragment() {
    return new Login_Fragment();
}

}

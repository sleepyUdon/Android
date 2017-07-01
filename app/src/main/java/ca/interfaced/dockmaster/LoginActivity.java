package ca.interfaced.dockmaster;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;


/**
 * Created by vivianechan on 2017-06-28.
 */

public class LoginActivity extends FragmentActivity {

@Override

protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.login);

    FragmentManager fm = getSupportFragmentManager();
    Fragment fragment = fm.findFragmentById(R.id.login_fragment_container);

    if (fragment == null) {
        fragment = new fragment_login();
        fm.beginTransaction()
                .add(R.id.login_fragment_container, fragment)
                .commit();
    }
}
//protected Fragment createFragment() {
//    return new fragment_login();
//}

}

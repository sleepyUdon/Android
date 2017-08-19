package ca.interfaced.dockmaster;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by vivianechan on 2017-08-01.
 */


public class ContactDescription_Activity extends SingleFragment_Activity {


    @Override
    protected Fragment createFragment() {

        return new ContactDescription_Fragment().newInstance();
    }
}






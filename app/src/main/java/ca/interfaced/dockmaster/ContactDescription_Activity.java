package ca.interfaced.dockmaster;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by vivianechan on 2017-08-01.
 */


public class ContactDescription_Activity extends SingleFragment_Activity {

    private static final String EXTRA_CONTACT_ID = "ca.interfaced.dockmaster.contact_id";

    public static Intent newIntent(Context packageContext, String contactID) {
        Intent intent = new Intent(packageContext, ContactDescription_Activity.class);
        intent.putExtra(EXTRA_CONTACT_ID, contactID);
        return intent;
    }

    @Override
    protected Fragment createFragment() {

        String contactID = (String) getIntent().getSerializableExtra(EXTRA_CONTACT_ID);

        return new ContactDescription_Fragment().newInstance(contactID);
    }
}




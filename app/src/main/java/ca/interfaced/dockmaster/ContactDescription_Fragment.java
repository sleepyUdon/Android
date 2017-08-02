package ca.interfaced.dockmaster;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by vivianechan on 2017-08-01.
 */

public class ContactDescription_Fragment extends Fragment {

    private static final String ARG_CONTACT_ID = "contact_id";

    private static String mContactID ;


    public static ContactDescription_Fragment newInstance(String contactID) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CONTACT_ID, contactID);
        mContactID = contactID;
        ContactDescription_Fragment fragment = new ContactDescription_Fragment();
        fragment.setArguments(args);
        return fragment;
        }


    public static ca.interfaced.dockmaster.ContactDescription_Fragment newInstance() {
        return new ca.interfaced.dockmaster.ContactDescription_Fragment();
    }
    public ContactDescription_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.contact_description_fragment, container, false);

        return v;

    }
}

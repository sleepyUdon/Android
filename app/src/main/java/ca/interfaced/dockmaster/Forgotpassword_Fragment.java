package ca.interfaced.dockmaster;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by vivianechan on 2017-06-30.
 */

public class Forgotpassword_Fragment extends Fragment {

    public static Forgotpassword_Fragment newInstance() {
        return new Forgotpassword_Fragment();
    }

    public Forgotpassword_Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.forgotpassword_fragment, container, false);

        return v;

    }
}
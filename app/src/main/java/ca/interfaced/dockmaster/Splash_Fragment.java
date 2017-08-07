package ca.interfaced.dockmaster;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by vivianechan on 2017-08-06.
 */

public class Splash_Fragment extends Fragment {

    public static ca.interfaced.dockmaster.Splash_Fragment newInstance() {
        return new ca.interfaced.dockmaster.Splash_Fragment();
    }

    public Splash_Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.splash_fragment, container, false);

        return v;

    }
}
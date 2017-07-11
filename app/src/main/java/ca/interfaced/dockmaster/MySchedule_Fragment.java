package ca.interfaced.dockmaster;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MySchedule_Fragment extends Fragment {

    public static ca.interfaced.dockmaster.MySchedule_Fragment newInstance() {
        return new ca.interfaced.dockmaster.MySchedule_Fragment();
    }

    public MySchedule_Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.myschedule_fragment, container, false);

        return v;

    }
}

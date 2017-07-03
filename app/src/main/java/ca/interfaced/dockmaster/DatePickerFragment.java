package ca.interfaced.dockmaster;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by vivianechan on 2017-07-03.
 */

public class DatePickerFragment extends android.support.v4.app.DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.date_dialog, null);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }

}

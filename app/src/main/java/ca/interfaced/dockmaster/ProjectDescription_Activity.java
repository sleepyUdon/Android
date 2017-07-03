package ca.interfaced.dockmaster;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ProjectDescription_Activity extends SingleFragment_Activity {

    @Override
    protected Fragment createFragment() {
        return new ProjectDescription_Fragment();
    }

}
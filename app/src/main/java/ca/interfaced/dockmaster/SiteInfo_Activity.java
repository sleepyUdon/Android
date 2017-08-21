package ca.interfaced.dockmaster;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by vivianechan on 2017-08-02.
 */


public class SiteInfo_Activity extends SingleFragment_Activity {

    private static final String EXTRA_ASSET_ID = "ca.interfaced.dockmaster.asset_id";

    public static Intent newIntent(Context packageContext, String assetID) {
        Intent intent = new Intent(packageContext, SiteInfo_Activity.class);
        intent.putExtra(EXTRA_ASSET_ID, assetID);
        return intent;
    }

    @Override
    protected Fragment createFragment() {

        String assetID = (String) getIntent().getSerializableExtra(EXTRA_ASSET_ID);

        return new SiteInfo_Fragment().newInstance(assetID);
    }
}

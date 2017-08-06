package ca.interfaced.dockmaster;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by vivianechan on 2017-08-05.
 */


public class AssetDescription_Activity extends SingleFragment_Activity {

    private static final String EXTRA_ASSET_ID = "ca.interfaced.dockmaster.asset_id";

    public static Intent newIntent(Context packageContext, String assetID) {
        Intent intent = new Intent(packageContext, AssetDescription_Activity.class);
        intent.putExtra(EXTRA_ASSET_ID, assetID);
        return intent;
    }

    @Override
    protected Fragment createFragment() {

        String assetID = (String) getIntent().getSerializableExtra(EXTRA_ASSET_ID);

        return new AssetDescription_Fragment().newInstance(assetID);
    }
}
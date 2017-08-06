package ca.interfaced.dockmaster;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ca.interfaced.dockmaster.Model.Asset;
import ca.interfaced.dockmaster.Model.User;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by vivianechan on 2017-08-05.
 */

public class AssetDescription_Fragment extends Fragment {

    private static final String ARG_ASSET_ID = "asset_id";

    private static String mAssetID;
    private static String assetImage;
    private static String assetName;
    private static TextView assetName_textView;


    public static AssetDescription_Fragment newInstance(String assetID) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_ASSET_ID, assetID);
        mAssetID = assetID;
        AssetDescription_Fragment fragment = new AssetDescription_Fragment();
        fragment.setArguments(args);
        return fragment;
    }


    public static ca.interfaced.dockmaster.AssetDescription_Fragment newInstance() {
        return new ca.interfaced.dockmaster.AssetDescription_Fragment();
    }

    public AssetDescription_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Realm realm = Realm.getDefaultInstance();


        RealmResults<Asset> asset = realm.where(Asset.class)
                .equalTo("id", mAssetID)
                .findAll();
        assetImage = asset.first().getImage();
        assetName = asset.first().getAssetName();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.asset_description_fragment, container, false);

        assetName_textView = (TextView) v.findViewById(R.id.assetTitleDescription);
        assetName_textView.setText(assetName);

        return v;
    }
}




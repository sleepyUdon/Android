package ca.interfaced.dockmaster;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import ca.interfaced.dockmaster.Model.Asset;
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
    private static String assetDescription;

    private static TextView assetName_textView;
    private static TextView assetDescription_textView;
    private static ImageView assetImage_imageView;


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
        assetDescription = asset.first().getDescription();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.asset_description_fragment, container, false);

        assetName_textView = (TextView) v.findViewById(R.id.assetTitleDescription);
        assetName_textView.setText(assetName);

        assetDescription_textView = (TextView) v.findViewById(R.id.assetDescription);
        assetDescription_textView.setText(assetDescription);

        assetImage_imageView = (ImageView) v.findViewById(R.id.assetImage);
        int resId = getResources().getIdentifier(assetImage, "drawable", getActivity().getPackageName());
        Drawable image = getActivity().getResources().getDrawable(resId);
        assetImage_imageView.setImageDrawable(image);
        return v;
    }
}



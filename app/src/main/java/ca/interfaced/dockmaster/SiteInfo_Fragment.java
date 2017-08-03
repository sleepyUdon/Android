package ca.interfaced.dockmaster;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import ca.interfaced.dockmaster.Model.Asset;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by vivianechan on 2017-08-02.
 */



    public class SiteInfo_Fragment extends Fragment {

        private static final String ARG_ASSET_ID = "asset_id";

        private static String mAssetID ;
        private static String sitePlanImage ;

        private ImageView sitePlan_imageView;



        public static ca.interfaced.dockmaster.SiteInfo_Fragment newInstance(String assetID) {
            Bundle args = new Bundle();
            args.putSerializable(ARG_ASSET_ID, assetID);
            mAssetID = assetID;
            ca.interfaced.dockmaster.SiteInfo_Fragment fragment = new ca.interfaced.dockmaster.SiteInfo_Fragment();
            fragment.setArguments(args);
            return fragment;
        }


        public static ca.interfaced.dockmaster.SiteInfo_Fragment newInstance() {
            return new ca.interfaced.dockmaster.SiteInfo_Fragment();
        }

        public SiteInfo_Fragment() {
            // Required empty public constructor
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            Realm realm = Realm.getDefaultInstance();


            RealmResults<Asset> asset = realm.where(Asset.class)
                    .equalTo("id",mAssetID)
                    .findAll();
            sitePlanImage = asset.first().getSitePlan();
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.site_info_fragment, container, false);

            sitePlan_imageView = (ImageView) v.findViewById(R.id.sitePlan);
            int resId = getResources().getIdentifier(sitePlanImage,"drawable",getActivity().getPackageName());
            Drawable sitePlan = getActivity().getResources().getDrawable(resId);
            sitePlan_imageView.setImageDrawable(sitePlan);

            return v;
        }
    }
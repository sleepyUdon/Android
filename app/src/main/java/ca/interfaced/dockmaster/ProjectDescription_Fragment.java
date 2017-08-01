package ca.interfaced.dockmaster;


import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.lang.reflect.Field;

import ca.interfaced.dockmaster.Model.Asset;
import ca.interfaced.dockmaster.Model.Project;
import ca.interfaced.dockmaster.Model.User;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by vivianechan on 2017-07-03.
 */

public class ProjectDescription_Fragment extends Fragment {

    private static final String ARG_PROJECT_ID = "crime_id";
    private static final String TAG = "Project ID";


    private String mProjectContactName;
    private String mProjectAssetName;

    public TextView mContactNameTextView;
    public ImageView mContactImageImageView;

    private RecyclerView mContactRecyclerView;
    private RecyclerView mAssetRecyclerView;
    private ContactAdapter mContactAdapter;
    private AssetAdapter mAssetAdapter;
    private RealmResults<Project> mProjects;
    private RealmResults<User> mContacts;
    private RealmResults<Asset> mAssets;
    private static String mprojectID ;


    public static ProjectDescription_Fragment newInstance(String projectID) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_PROJECT_ID, projectID);
        mprojectID = projectID;
        ProjectDescription_Fragment fragment = new ProjectDescription_Fragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String projectID = (String) getArguments().getSerializable(ARG_PROJECT_ID);
        Realm realm = Realm.getDefaultInstance();
        Project project = realm.where(Project.class)
                .equalTo("id", projectID)
                .findFirst();

        Log.d(TAG, projectID);
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.projectdescription_fragment, container, false);


        mContactRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerViewContacts);
        mContactRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAssetRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerViewAssets);
        mAssetRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Realm realm = Realm.getDefaultInstance();

        RealmQuery<Project> queryProjects = realm.where(Project.class);
        RealmResults<Project> projects = queryProjects.findAll();

        RealmQuery<User> queryContacts = realm.where(User.class);
        RealmResults<User> contacts = queryContacts.findAll();

        RealmQuery<Asset> queryAssets = realm.where(Asset.class);
        RealmResults<Asset> assets = queryAssets.findAll();

        mContactAdapter = new ContactAdapter(contacts);
        mAssetAdapter = new AssetAdapter(assets);
        
        mContactRecyclerView.setAdapter(mContactAdapter);
        mAssetRecyclerView.setAdapter(mAssetAdapter);


        return v;
    }

    private class ContactHolder extends RecyclerView.ViewHolder {
        private User mUser;

        public void bindProject(User user) {
            mUser = user;
            mContactNameTextView.setText(mUser.getFirstName());

            int resId = getResources().getIdentifier(mUser.getImage(),"drawable",getActivity().getPackageName());
            Drawable contactThumbnail = getActivity().getResources().getDrawable(resId);

            mContactImageImageView.setImageDrawable(contactThumbnail);
        }





        public ContactHolder(View itemView) {
            super(itemView);
            // Define click listener for the ViewHolder's View.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "Contact selected", Toast.LENGTH_SHORT).show();
//                    Intent intent = ProjectDescription_Activity.newIntent(getActivity(), mProject.getId());
//                    startActivity(intent);
                }
            });
            mContactNameTextView = (TextView) itemView.findViewById(R.id.ContactName);
            mContactImageImageView = (ImageView) itemView.findViewById(R.id.contactThumbnail);

        }

    }

    private class AssetHolder extends RecyclerView.ViewHolder {
        private Asset mAsset;

        public void bindProject(Asset asset) {
            mAsset = asset;
            mAssetNameTextView.setText(mAsset.getAssetName());
        }

        public TextView mAssetNameTextView;


        public AssetHolder(View itemView) {
            super(itemView);
            // Define click listener for the ViewHolder's View.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "Asset selected", Toast.LENGTH_SHORT).show();
//                    Intent intent = ProjectDescription_Activity.newIntent(getActivity(), mProject.getId());
//                    startActivity(intent);
                }
            });
            mAssetNameTextView = (TextView) itemView.findViewById(R.id.AssetName);
        }

    }

    private class ContactAdapter extends RecyclerView.Adapter<ContactHolder> {
        Realm realm = Realm.getDefaultInstance();

        RealmResults<User> contacts = realm.where(User.class)
                .equalTo("projects.id",mprojectID)
                .findAll();

        public ContactAdapter(RealmResults<User> contacts) {
            mContacts = contacts;
        }


        @Override
        public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.contact_item, parent, false);

            return new ContactHolder(view);
        }

        @Override
        public void onBindViewHolder(ContactHolder holder, final int position) {
            User user = contacts.get(position);
            holder.bindProject(user);
        }

        @Override
        public int getItemCount() {
            return contacts.size();
        }
    }

    private class AssetAdapter extends RecyclerView.Adapter<AssetHolder> {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Asset> assets = realm.where(Asset.class)
                .equalTo("projects.id",mprojectID)
                .findAll();

        public AssetAdapter(RealmResults<Asset> assets) {
            mAssets = assets;
        }


        @Override
        public AssetHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.asset_item, parent, false);

            return new AssetHolder(view);
        }

        @Override
        public void onBindViewHolder(AssetHolder holder, final int position) {
            Asset asset = assets.get(position);
            holder.bindProject(asset);
        }

        @Override
        public int getItemCount() {
            return assets.size();
        }
    }

}
package ca.interfaced.dockmaster;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



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

    private static final String ARG_PROJECT_ID = "project_id";

    private static String mprojectID ;
    public TextView mContactNameTextView;
    public TextView mAssetNameTextView;

    public ImageView mContactImageImageView;
    public ImageView mAssetImageImageView;

    private RecyclerView mContactRecyclerView;
    private RecyclerView mAssetRecyclerView;

    private ContactAdapter mContactAdapter;
    private AssetAdapter mAssetAdapter;

    private RealmResults<User> mContacts;
    private RealmResults<Asset> mAssets;


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
            String firstName = mUser.getFirstName();
            String lastName  = mUser.getLastName();
            mContactNameTextView.setText(firstName + " " + lastName);

            int resId = getResources().getIdentifier(mUser.getImage(),"drawable",getActivity().getPackageName());
            Drawable contactThumbnail = getActivity().getResources().getDrawable(resId);

            mContactImageImageView.setImageDrawable(contactThumbnail);
        }





        public ContactHolder(View itemView) {
            super(itemView);
            // Define click listener for the ViewHolder's View.
            itemView.findViewById(R.id.contactButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = ContactDescription_Activity.newIntent(getActivity(), mUser.getId());
                    startActivity(intent);
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

            int resId = getResources().getIdentifier(mAsset.getImage(),"drawable",getActivity().getPackageName());
            Drawable contactThumbnail = getActivity().getResources().getDrawable(resId);

            mAssetImageImageView.setImageDrawable(contactThumbnail);
        }



        public AssetHolder(View itemView) {
            super(itemView);
            // Define click listener for the ViewHolder's View.
            itemView.findViewById(R.id.infoButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = AssetDescription_Activity.newIntent(getActivity(), mAsset.getId());
                    startActivity(intent);
                }
            });
            itemView.findViewById(R.id.bookButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = AssetDescription_Activity.newIntent(getActivity(), mAsset.getId());
//                    startActivity(intent);
                }
            });
            mAssetNameTextView = (TextView) itemView.findViewById(R.id.AssetName);
            mAssetImageImageView = (ImageView) itemView.findViewById(R.id.assetThumbnail);
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
                .equalTo("project.id",mprojectID)
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
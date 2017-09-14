
package ca.interfaced.dockmaster;



import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;


import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import ca.interfaced.dockmaster.Model.ProjectItem;

import static ca.interfaced.dockmaster.R.id.image;


public class Projectslist_Fragment extends Fragment {

    private RecyclerView mProjectRecyclerView;
    private DatabaseReference mFirebaseDatabaseReference;
    private StorageReference mStorageReference;
    private FirebaseRecyclerAdapter<ProjectItem, ProjectHolder> mFirebaseAdapter;
    private LinearLayoutManager mLinearLayoutManager;


    public static ca.interfaced.dockmaster.Projectslist_Fragment newInstance() {
        return new ca.interfaced.dockmaster.Projectslist_Fragment();
    }


    public Projectslist_Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
    }


    public static class ProjectHolder extends RecyclerView.ViewHolder {

        public Context context;
        public ProjectItem projectItem;
        public TextView mProjectAddressTextView;
        public TextView mProjectNameTextView;
        public ImageView mImageView;

        public ProjectHolder(View v) {
            super(v);

            mProjectAddressTextView = (TextView) itemView.findViewById(R.id.project_address);
            mProjectNameTextView = (TextView) itemView.findViewById(R.id.project_name);
            mImageView = (ImageView) itemView.findViewById(R.id.projectThumbnail);

            v.setOnClickListener(new View.OnClickListener() {

                ProjectItem mProjectItem;

                @Override
                public void onClick(View v) {

                    context = v.getContext();

                    Intent intent = new Intent(context, ProjectDescription_Activity.class);
                    intent.putExtra("ARG_PROJECT_ID", "P0");
                    context.startActivity(intent);
            }
            });

        }

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String userID = getActivity().getIntent().getExtras().getString("userID");
        Log.d("extraFromLogin", userID);

        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

        //TODO: ADD QUERY

        mFirebaseDatabaseReference.child("projects").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot projectSnapShot : dataSnapshot.getChildren()) {
                    ProjectItem projectItem = projectSnapShot.getValue(ProjectItem.class);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("test", "test");
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.projectslist_fragment, container, false);

        mProjectRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewProjectList);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mProjectRecyclerView.setLayoutManager(mLinearLayoutManager);

        return view;
    }



    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void onActivityCreated(Bundle saved) {
        super.onActivityCreated(saved);

        //TODO: ADD QUERY


        mFirebaseAdapter = new FirebaseRecyclerAdapter<ProjectItem, ProjectHolder>(
                ProjectItem.class,
                R.layout.project_item,
                ProjectHolder.class,
                mFirebaseDatabaseReference.child("projects")) {

//            @Override
//            protected ProjectItem parseSnapshot(DataSnapshot snapshot) {
//                ProjectItem projectItem = super.parseSnapshot(snapshot);
//                if (projectItem != null) {
////                    projectItem.setName(snapshot.child("name"));
////                    projectItem.setAddress(snapshot.child("address"));
//                }
//                return projectItem;
//            }

            @Override
            protected void populateViewHolder(final ProjectHolder viewHolder,
                                              ProjectItem projectItem, int position) {
                viewHolder.mProjectAddressTextView.setText(projectItem.getAddress());
                viewHolder.mProjectAddressTextView.setVisibility(TextView.VISIBLE);

                viewHolder.mProjectNameTextView.setText(projectItem.getName());
                viewHolder.mProjectNameTextView.setVisibility(TextView.VISIBLE);

//                viewHolder.mImageView.setImageResource(R.drawable.johnsmith);

                Uri url = Uri.parse(projectItem.getListingImage());

                Glide.with(getActivity())
                        .load(url)
                        .centerCrop()
                        //TODO: Add placeholder image
//                        .placeholder(R.drawable.project1)
                        .into(viewHolder.mImageView);
            }
        };

        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int friendlyMessageCount = mFirebaseAdapter.getItemCount();
                int lastVisiblePosition =
                        mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                // If the recycler view is initially being loaded or the
                // user is at the bottom of the list, scroll to the bottom
                // of the list to show the newly added message.
                if (lastVisiblePosition == -1 ||
                        (positionStart >= (friendlyMessageCount - 1) &&
                                lastVisiblePosition == (positionStart - 1))) {
                    mProjectRecyclerView.scrollToPosition(positionStart);
                }
            }
        });

        mProjectRecyclerView.setAdapter(mFirebaseAdapter);
    }

}

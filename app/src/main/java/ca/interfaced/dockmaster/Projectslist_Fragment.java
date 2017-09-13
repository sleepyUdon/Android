
package ca.interfaced.dockmaster;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ca.interfaced.dockmaster.Model.ProjectItem;


public class Projectslist_Fragment extends Fragment {


    public static class ProjectHolder extends RecyclerView.ViewHolder {
        public TextView mProjectAddressTextView;
        public TextView mProjectNameTextView;

        public ProjectHolder(View v) {
            super(v);
            mProjectAddressTextView = (TextView) itemView.findViewById(R.id.project_address);
            mProjectNameTextView = (TextView) itemView.findViewById(R.id.project_name);
        }
    }


    private RecyclerView mProjectRecyclerView;
    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseRecyclerAdapter<ProjectItem, ProjectHolder>
            mFirebaseAdapter;
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


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String userID = getActivity().getIntent().getExtras().getString("userID");
        Log.d("extraFromLogin", userID);

        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
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
            protected void populateViewHolder(ProjectHolder viewHolder,
                                              ProjectItem project, int position) {
                viewHolder.mProjectAddressTextView.setText(project.getAddress());
                viewHolder.mProjectAddressTextView.setVisibility(TextView.VISIBLE);
                viewHolder.mProjectNameTextView.setText(project.getName());
                viewHolder.mProjectNameTextView.setVisibility(TextView.VISIBLE);
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

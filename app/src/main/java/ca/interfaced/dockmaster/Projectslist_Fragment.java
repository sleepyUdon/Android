
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
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

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

//    private String mUserID;



    private RecyclerView mProjectRecyclerView;
    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseRecyclerAdapter<ProjectItem, ProjectHolder>
            mFirebaseAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    ArrayList<ProjectItem> projectsArraylist = new ArrayList<>();


    public ImageView mProjectImageImageView;
    public TextView mProjectNameTextView;
    public TextView mProjectAddressTextView;



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
//        mUserID = userID;

//        Realm realm = Realm.getDefaultInstance();
//        User user = realm.where(User.class)
//                .equalTo("email", mUserID)
//                .findFirst();
//        RealmList<Project> projects = user.getProjects();
//        mProjects = projects;



    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.projectslist_fragment, container, false);

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mProjectRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewProjectList);
        mLinearLayoutManager.setStackFromEnd(true);
        mProjectRecyclerView.setLayoutManager(mLinearLayoutManager);


        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        //ValueEventListener projectsListener =
        mFirebaseDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ProjectItem projectItem = dataSnapshot.getValue(ProjectItem.class);
                Log.d(projectItem.getId(), projectItem.getName());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mFirebaseAdapter = new FirebaseRecyclerAdapter<ProjectItem, ProjectHolder>(
                ProjectItem.class,
                R.layout.project_item,
                ProjectHolder.class,
                mFirebaseDatabaseReference.child("Dockmaster").child("projects")) {

            @Override
            protected ProjectItem parseSnapshot(DataSnapshot snapshot) {
                ProjectItem projectItem = super.parseSnapshot(snapshot);
                if (projectItem != null) {
                    projectItem.setId(snapshot.getKey());
                }
                return projectItem;
            }

            @Override
            protected void populateViewHolder(ProjectHolder viewHolder,
                                              ProjectItem project, int position) {
                viewHolder.mProjectAddressTextView.setText(project.getName());
                viewHolder.mProjectAddressTextView.setVisibility(TextView.VISIBLE);
                viewHolder.mProjectNameTextView.setText("HELLo");
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


        return view;
    }




    @Override
    public void onResume(){
        super.onResume();

    }


//    private class ProjectHolder extends RecyclerView.ViewHolder {
//        private Project mProject;
//
//        public void bindProject(Project project) {
//            mProject = project;
//            mProjectNameTextView.setText(mProject.getProjectName().toUpperCase());
//            mProjectAddressTextView.setText(mProject.getProjectAddress());
//
//            int resId = getResources().getIdentifier(mProject.getImage(),"drawable",getActivity().getPackageName());
//            Drawable contactThumbnail = getActivity().getResources().getDrawable(resId);
//
//            mProjectImageImageView.setImageDrawable(contactThumbnail);        }
//
//
//
//
//
//        public ProjectHolder(View itemView) {
//            super(itemView);
//            // Define click listener for the ViewHolder's View.
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(getActivity(), "Project selected", Toast.LENGTH_SHORT).show();
//                    Intent intent = ProjectDescription_Activity.newIntent(getActivity(), mProject.getId());
//                    startActivity(intent);
//                }
//            });
//            mProjectNameTextView = (TextView) itemView.findViewById(R.id.project_name);
//            mProjectAddressTextView = (TextView) itemView.findViewById(R.id.project_address);
//            mProjectImageImageView = (ImageView) itemView.findViewById(R.id.projectThumbnail);
//
//        }
//
//    }
//
//
//    private class ProjectAdapter extends RecyclerView.Adapter<ProjectHolder> {
//
//        public ProjectAdapter(RealmList<Project> projects) {
//                        mProjects = projects;
//        }
//
//
//        @Override
//        public ProjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.project_item, parent, false);
//
//            return new ProjectHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(ProjectHolder holder, final int position) {
//            Project project = mProjects.get(position);
//            holder.bindProject(project);
//        }
//
//        @Override
//        public int getItemCount() {
//            Log.d("count", String.format("%d", mProjects.size()));
//            return mProjects.size();
//        }
//    }


}

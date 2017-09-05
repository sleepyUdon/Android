
package ca.interfaced.dockmaster;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ca.interfaced.dockmaster.Model.Project;
import ca.interfaced.dockmaster.Model.User;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;


public class Projectslist_Fragment extends Fragment {


    public static class ProjectHolder extends RecyclerView.ViewHolder {
        TextView mProjectAddressTextView;

        public ProjectHolder(View v) {
            super(v);
            mProjectAddressTextView = (TextView) itemView.findViewById(R.id.project_address);

        }
    }

    private String mUserID;
    private RealmList<Project> mProjects;

    private RecyclerView mProjectRecyclerView;
    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseRecyclerAdapter<Project, ProjectHolder>
            mFirebaseAdapter;

    private LinearLayoutManager mLinearLayoutManager;

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
        mUserID = userID;

        Realm realm = Realm.getDefaultInstance();
        User user = realm.where(User.class)
                .equalTo("email", mUserID)
                .findFirst();
        RealmList<Project> projects = user.getProjects();
        mProjects = projects;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.projectslist_fragment, container, false);

//        fab = (FloatingActionButton) view.findViewById(R.id.fab);


        mProjectRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewProjectList);
        mProjectRecyclerView.setLayoutManager(mLinearLayoutManager);

        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Project, ProjectHolder>(
                Project.class,
                R.layout.project_item,
                ProjectHolder.class,
                mFirebaseDatabaseReference.child("projects")) {

            @Override
            protected void populateViewHolder(final ProjectHolder viewHolder,
                                              Project project, int position) {
                if (project.getProjectName() != null) {
                    viewHolder.mProjectAddressTextView.setText(project.getProjectName());
//                    viewHolder.messageTextView.setVisibility(TextView.VISIBLE);
//                    viewHolder.messageImageView.setVisibility(ImageView.GONE);
                } else {
                    viewHolder.mProjectAddressTextView.setText("placeholderAddress");

//                    String imageUrl = friendlyMessage.getImageUrl();
//                    if (imageUrl.startsWith("gs://")) {
//                        StorageReference storageReference = FirebaseStorage.getInstance()
//                                .getReferenceFromUrl(imageUrl);
//                        storageReference.getDownloadUrl().addOnCompleteListener(
//                                new OnCompleteListener<Uri>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<Uri> task) {
//                                        if (task.isSuccessful()) {
//                                            String downloadUrl = task.getResult().toString();
//                                            Glide.with(viewHolder.messageImageView.getContext())
//                                                    .load(downloadUrl)
//                                                    .into(viewHolder.messageImageView);
//                                        } else {
//                                            Log.w(TAG, "Getting download url was not successful.",
//                                                    task.getException());
//                                        }
//                                    }
//                                });
//                    } else {
//                        Glide.with(viewHolder.messageImageView.getContext())
//                                .load(friendlyMessage.getImageUrl())
//                                .into(viewHolder.messageImageView);
//                    }
//                    viewHolder.messageImageView.setVisibility(ImageView.VISIBLE);
//                    viewHolder.messageTextView.setVisibility(TextView.GONE);
//                }
//
//
//                viewHolder.messengerTextView.setText(friendlyMessage.getName());
//                if (friendlyMessage.getPhotoUrl() == null) {
//                    viewHolder.messengerImageView.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,
//                            R.drawable.ic_account_circle_black_36dp));
//                } else {
//                    Glide.with(MainActivity.this)
//                            .load(friendlyMessage.getPhotoUrl())
//                            .into(viewHolder.messengerImageView);
//                }
                }
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

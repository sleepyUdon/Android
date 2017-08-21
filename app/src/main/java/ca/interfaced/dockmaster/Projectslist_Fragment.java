
package ca.interfaced.dockmaster;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import android.widget.TextView;
import android.widget.Toast;

import ca.interfaced.dockmaster.Model.Project;
import ca.interfaced.dockmaster.Model.User;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;


public class Projectslist_Fragment extends Fragment {

    private String mUserID;
    private RealmList<Project> mProjects;

    private RecyclerView mProjectRecyclerView;
    private ProjectAdapter mAdapter;

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
        mProjectRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        mAdapter = new ProjectAdapter(mProjects);
        mProjectRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();

    }


    private class ProjectHolder extends RecyclerView.ViewHolder {
        private Project mProject;

        public void bindProject(Project project) {
            mProject = project;
            mProjectNameTextView.setText(mProject.getProjectName().toUpperCase());
            mProjectAddressTextView.setText(mProject.getProjectAddress());

            int resId = getResources().getIdentifier(mProject.getImage(),"drawable",getActivity().getPackageName());
            Drawable contactThumbnail = getActivity().getResources().getDrawable(resId);

            mProjectImageImageView.setImageDrawable(contactThumbnail);        }





        public ProjectHolder(View itemView) {
            super(itemView);
            // Define click listener for the ViewHolder's View.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "Project selected", Toast.LENGTH_SHORT).show();
                    Intent intent = ProjectDescription_Activity.newIntent(getActivity(), mProject.getId());
                    startActivity(intent);
                }
            });
            mProjectNameTextView = (TextView) itemView.findViewById(R.id.project_name);
            mProjectAddressTextView = (TextView) itemView.findViewById(R.id.project_address);
            mProjectImageImageView = (ImageView) itemView.findViewById(R.id.projectThumbnail);

        }

    }


    private class ProjectAdapter extends RecyclerView.Adapter<ProjectHolder> {

        public ProjectAdapter(RealmList<Project> projects) {
                        mProjects = projects;
        }


        @Override
        public ProjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.project_item, parent, false);

            return new ProjectHolder(view);
        }

        @Override
        public void onBindViewHolder(ProjectHolder holder, final int position) {
            Project project = mProjects.get(position);
            holder.bindProject(project);
        }

        @Override
        public int getItemCount() {
            Log.d("count", String.format("%d", mProjects.size()));
            return mProjects.size();
        }
    }


}

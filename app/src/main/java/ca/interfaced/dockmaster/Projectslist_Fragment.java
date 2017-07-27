
package ca.interfaced.dockmaster;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.UUID;

import ca.interfaced.dockmaster.Model.Project;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;


public class Projectslist_Fragment extends Fragment {

    private RecyclerView mProjectRecyclerView;
    private ProjectAdapter mAdapter;
    private RealmResults<Project> mProjects;
    private Realm realm;


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

        Realm.init(getContext());
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();

        Project project = realm.createObject(Project.class, 1);
        project.setProjectName("111 Richmond");
        project.setProjectAddress("111 Richmond Street");

        Project project2 = realm.createObject(Project.class, 2);
        project2.setProjectName("222 Richmond");
        project2.setProjectAddress("222 Richmond Street");

        realm.commitTransaction();

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.projectslist_fragment, container, false);

        mProjectRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mProjectRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Realm realm = Realm.getDefaultInstance();

        RealmQuery<Project>query = realm.where(Project.class);
        RealmResults<Project> projects = query.findAll();

        mAdapter = new ProjectAdapter(projects);
        mProjectRecyclerView.setAdapter(mAdapter);

        return view;
    }


    private class ProjectHolder extends RecyclerView.ViewHolder {
        private Project mProject;

        public void bindProject(Project project) {
            mProject = project;
            mProjectNameTextView.setText(mProject.getProjectName());
            mProjectAddressTextView.setText(mProject.getProjectAddress());
        }

        public TextView mProjectNameTextView;
        public TextView mProjectAddressTextView;


        public ProjectHolder(View itemView) {
            super(itemView);
            // Define click listener for the ViewHolder's View.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            mProjectNameTextView = (TextView) itemView.findViewById(R.id.project_name);
            mProjectAddressTextView = (TextView) itemView.findViewById(R.id.project_address);

        }

    }


    private class ProjectAdapter extends RecyclerView.Adapter<ProjectHolder> {
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<Project>query = realm.where(Project.class);
        RealmResults<Project> projects = query.findAll();

        public ProjectAdapter(RealmResults<Project> projects) {
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
            Project project = projects.get(position);
            holder.bindProject(project);
        }

        @Override
        public int getItemCount() {
            return projects.size();
        }
    }


}

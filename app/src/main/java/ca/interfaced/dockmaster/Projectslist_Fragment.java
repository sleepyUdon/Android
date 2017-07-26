
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

import ca.interfaced.dockmaster.Model.Project;
import ca.interfaced.dockmaster.Model.Projects;


public class Projectslist_Fragment extends Fragment {

    private RecyclerView mProjectRecyclerView;
    private ProjectAdapter mAdapter;

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
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.projectslist_fragment, container, false);

        mProjectRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mProjectRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }


    private void updateUI() {
        Projects projects = Projects.get(getActivity());
        List<Project> projectList = projects.getProjects();

        mAdapter = new ProjectAdapter(projectList);
        mProjectRecyclerView.setAdapter(mAdapter);
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
        private List<Project> mProjects;

        public ProjectAdapter(List<Project> projects) {
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
            return mProjects.size();
        }
    }


}

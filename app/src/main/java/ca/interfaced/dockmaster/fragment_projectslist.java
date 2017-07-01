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

/**
 * Created by vivianechan on 2017-07-01.
 */

public class fragment_projectslist extends Fragment{

    private Project mProject;
    private RecyclerView mProjectsRecyclerView;
    private ProjectAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProject = new Project();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.projects_list_fragment, container, false);

        mProjectsRecyclerView = (RecyclerView) view.findViewById(R.id.projects_list_fragment_container);
        mProjectsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        ProjectsList projectsList = ProjectsList.get(getActivity());
        List<Project>projects = projectsList.getProjects();

        mAdapter = new ProjectAdapter(projects);
        mProjectsRecyclerView.setAdapter(mAdapter);
    }



    private class ProjectHolder extends RecyclerView.ViewHolder {
        public TextView mProjectName;

        public ProjectHolder(View itemView) {
            super(itemView);
            mProjectName = (TextView) itemView;
        }
    }

    private class ProjectAdapter extends RecyclerView.Adapter<ProjectHolder> {
        private List<Project> mProjects;

        public ProjectAdapter(List<Project>projects) {
            mProjects = projects;
        }

        @Override
        public ProjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            return new ProjectHolder(view);
        }

        @Override
        public void onBindViewHolder(ProjectHolder holder, int position) {
            Project project = mProjects.get(position);
            holder.mProjectName.setText(project.getProjectName());
        }

        @Override
        public int getItemCount() {
            return mProjects.size();
        }



    }


}

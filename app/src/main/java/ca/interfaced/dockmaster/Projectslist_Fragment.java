package ca.interfaced.dockmaster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ca.interfaced.dockmaster.Model.Project;
import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by vivianechan on 2017-07-01.
 */

public class Projectslist_Fragment extends Fragment{

    private RecyclerView mProjectsRecyclerView;
    private ProjectAdapter mAdapter;
    private List<Project> mProjects;

    public static Projectslist_Fragment newInstance() {
        return new Projectslist_Fragment();
    }

    public Projectslist_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        Realm realm = Realm.getDefaultInstance();
        RealmQuery<Project>query = realm.where(Project.class);
        RealmResults<Project> projects = query.findAll();
        mProjects = projects;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.projectslist_fragment, container, false);

        mProjectsRecyclerView = (RecyclerView) view.findViewById(R.id.projects_list_fragment_container);
        mProjectsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
//        updateUI();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu , MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.projectlist_fragment,  menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Realm realm = Realm.getDefaultInstance();
        int id = item.getItemId();
        switch(id) {
            case R.id.menuitem_addproject:
                realm.beginTransaction();
                Project project = new Project();
                project.setProjectName("111 Richmond");
                project.setProjectAddress("111 Richmond Street");

                realm.commitTransaction();


//            ProjectsList.get(getActivity()).addProject(project);
//            Intent intent = ProjectPager_Activity.newIntent(getActivity(), project.getID());
//            startActivity(intent);
            return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




//    private void updateUI() {
//        ProjectsList projectsList = ProjectsList.get(getActivity());
//        List<Project> projects = projectsList.getProjects();
//
//        if (mAdapter == null) {
//            mAdapter = new ProjectAdapter(projects);
//            mProjectsRecyclerView.setAdapter(mAdapter);
//        } else {
//            mAdapter.setProjects(projects);
//            mAdapter.notifyDataSetChanged();
//        }
//    }



    private class ProjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Project mProject;

        public TextView mProjectName;
        public TextView mProjectAddress;

        public ProjectHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.project_item, parent, false));
            itemView.setOnClickListener(this);

            mProjectName = (TextView) itemView.findViewById(R.id.project_item_projectName);
            mProjectAddress = (TextView) itemView.findViewById(R.id.project_item_projectAddress);
        }

        public void bind(Project project) {
            mProject = project;

//            mProjectName.setText(mProject.getProjectName());
//            mProjectAddress.setText(mProject.getProjectAddress());
        }

        @Override
        public void onClick(View view) {

            // open project description
//            Intent intent = ProjectPager_Activity.newIntent(getActivity(), mProject.getID());
//            startActivity(intent);

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
            return new ProjectHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(ProjectHolder holder, int position) {
//            Project project = mProjects.get(position);
//            holder.bind(project);
        }

        @Override
        public int getItemCount() {
            return mProjects.size();
        }

//        public void setProjects(List<Project> projects) {
//            mProjects = projects;
//        }

    }






}

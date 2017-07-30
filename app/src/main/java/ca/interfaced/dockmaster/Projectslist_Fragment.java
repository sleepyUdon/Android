
package ca.interfaced.dockmaster;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ca.interfaced.dockmaster.Model.Project;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;


public class Projectslist_Fragment extends Fragment {

    private RecyclerView mProjectRecyclerView;
    private ProjectAdapter mAdapter;
    private FloatingActionButton fab;
    private LayoutInflater dialogInflater;
    private RealmResults<Project> mProjects;



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

        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogInflater = getActivity().getLayoutInflater();
                View content = dialogInflater.inflate(R.layout.add_project_item, null);
                final EditText editProjectName = (EditText) content.findViewById(R.id.project_name);
                final EditText editProjectAddress = (EditText) content.findViewById(R.id.project_address);
//                final EditText editThumbnail = (EditText) content.findViewById(R.id.thumbnail);
//
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(content)
                        .setTitle("Add project")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (editProjectName.getText() == null || editProjectAddress.getText().toString().equals("")) {
                                    Toast.makeText(getActivity(), "Entry not saved, missing title", Toast.LENGTH_SHORT).show();
                                } else {
                                    Realm realm = Realm.getDefaultInstance();
                                    realm.beginTransaction();
                                    Project project = realm.createObject(Project.class, System.currentTimeMillis());
                                    // TODO: set ID
                                    project.setProjectName(editProjectName.getText().toString());
                                    project.setProjectAddress(editProjectAddress.getText().toString());
                                    // TODO: set image
                                    realm.commitTransaction();
//
                                    mAdapter.notifyDataSetChanged();
//
//                                    // scroll the recycler view to bottom
//                                    recycler.scrollToPosition(RealmController.getInstance().getBooks().size() - 1);
                                }
                            }
                        })
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        mProjectRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewProjectList);
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
            mProjectNameTextView.setText(mProject.getProjectName().toUpperCase());
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
                    Toast.makeText(getActivity(), "Project selected", Toast.LENGTH_SHORT).show();
                    Intent intent = ProjectDescription_Activity.newIntent(getActivity(), mProject.getId());
                    startActivity(intent);
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

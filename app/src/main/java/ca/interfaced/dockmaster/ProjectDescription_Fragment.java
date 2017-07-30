package ca.interfaced.dockmaster;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import ca.interfaced.dockmaster.Model.Project;
import ca.interfaced.dockmaster.Model.User;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by vivianechan on 2017-07-03.
 */

public class ProjectDescription_Fragment extends Fragment {

    private static final String ARG_PROJECT_ID = "crime_id";

    private String mProjectContactName;
    private String mProjectAssetName;

    private TextView mContactNameTextView;

    private RecyclerView mContactRecyclerView;
    private ContactsAdapter mAdapter;
    private RealmResults<Project> mProjects;


    public static ProjectDescription_Fragment newInstance(long projectID) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_PROJECT_ID, projectID);

        ProjectDescription_Fragment fragment = new ProjectDescription_Fragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        long projectID = (long) getArguments().getSerializable(ARG_PROJECT_ID);
        Realm realm = Realm.getDefaultInstance();
        Project project = realm.where(Project.class)
                .equalTo("id", projectID)
                .findFirst();
        mProjectContactName = project.getProjectContactName();
//        mProjectAssetName = project.getProjectAssetName();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.projectdescription_fragment, container, false);


        mContactRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerViewContacts);
        mContactRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Realm realm = Realm.getDefaultInstance();

        RealmQuery<Project> query = realm.where(Project.class);
        RealmResults<Project> projects = query.findAll();

        mAdapter = new ContactsAdapter(projects);
        mContactRecyclerView.setAdapter(mAdapter);

        return v;
    }

    private class ContactHolder extends RecyclerView.ViewHolder {
        private Project mProject;

        public void bindProject(Project project) {
            mProject = project;
            mContactNameTextView.setText(mProject.getProjectContactName());
        }

        public TextView mContactNameTextView;


        public ContactHolder(View itemView) {
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
            mContactNameTextView = (TextView) itemView.findViewById(R.id.ContactName);
        }

    }


    private class ContactsAdapter extends RecyclerView.Adapter<ContactHolder> {
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<Project> query = realm.where(Project.class);
        RealmResults<Project> projects = query.findAll();

        public ContactsAdapter(RealmResults<Project> projects) {
            mProjects = projects;
        }


        @Override
        public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.contact_item, parent, false);

            return new ContactHolder(view);
        }

        @Override
        public void onBindViewHolder(ContactHolder holder, final int position) {
            Project project = projects.get(position);
            holder.bindProject(project);
        }

        @Override
        public int getItemCount() {
            return projects.size();
        }
    }

}
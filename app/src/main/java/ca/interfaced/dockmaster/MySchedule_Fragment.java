package ca.interfaced.dockmaster;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ca.interfaced.dockmaster.Model.Project;
import io.realm.Realm;
import io.realm.RealmResults;


public class MySchedule_Fragment extends Fragment {

    private RecyclerView mScheduleRecyclerView;
    private MySchedule_Fragment.ScheduleAdapter mAdapter;
    private RealmResults<Project> mProjects;
    public TextView mTimeTextView;
    public TextView mProjectNameTextView;
    public TextView mAssetNameTextView;
    public TextView mCompanyNameTextView;
    public TextView mNotesTextView;






    public static ca.interfaced.dockmaster.MySchedule_Fragment newInstance() {
        return new ca.interfaced.dockmaster.MySchedule_Fragment();
    }

    public MySchedule_Fragment() {
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
        View view = inflater.inflate(R.layout.myschedule_fragment, container, false);



        mScheduleRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewScheduleList);
        mScheduleRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Realm realm = Realm.getDefaultInstance();
        RealmResults<Project> projects = realm.where(Project.class)
                .findAll();

        mAdapter = new MySchedule_Fragment.ScheduleAdapter(projects);
        mScheduleRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();

    }



    private class ScheduleHolder extends RecyclerView.ViewHolder {
        private Project mProject;

        public void bindProject(Project project) {
            mProject = project;
            mTimeTextView.setText(mProject.getProjectName().toUpperCase());
            mProjectNameTextView.setText(mProject.getProjectName().toUpperCase());
            mCompanyNameTextView.setText(mProject.getProjectName().toUpperCase());
            mAssetNameTextView.setText(mProject.getProjectName().toUpperCase());
            mNotesTextView.setText(mProject.getProjectName().toUpperCase());

        }





        public ScheduleHolder(View itemView) {
            super(itemView);
            // Define click listener for the ViewHolder's View.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "Project selected", Toast.LENGTH_SHORT).show();
//                    Intent intent = ProjectDescription_Activity.newIntent(getActivity(), mProject.getId());
//                    startActivity(intent);
                }
            });
            mTimeTextView = (TextView) itemView.findViewById(R.id.time_textView);
            mProjectNameTextView = (TextView) itemView.findViewById(R.id.project_name_textView);
            mCompanyNameTextView = (TextView) itemView.findViewById(R.id.company_name_textView);
            mAssetNameTextView = (TextView) itemView.findViewById(R.id.asset_name_textView);
            mNotesTextView = (TextView) itemView.findViewById(R.id.notes_textView);

        }

    }


    private class ScheduleAdapter extends RecyclerView.Adapter<MySchedule_Fragment.ScheduleHolder> {
        Realm realm = Realm.getDefaultInstance();

        RealmResults<Project> projects = realm.where(Project.class)
                .equalTo("Users.email", "vivianechan@hotmail.com")
                .findAll();
        public ScheduleAdapter(RealmResults<Project> projects) {
            mProjects = projects;
        }


        @Override
        public MySchedule_Fragment.ScheduleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.schedule_item, parent, false);

            return new MySchedule_Fragment.ScheduleHolder(view);
        }

        @Override
        public void onBindViewHolder(MySchedule_Fragment.ScheduleHolder holder, final int position) {
            Project project = projects.get(position);
            holder.bindProject(project);
        }

        @Override
        public int getItemCount() {
            return projects.size();
        }
    }


}

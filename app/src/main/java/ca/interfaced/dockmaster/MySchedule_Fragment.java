package ca.interfaced.dockmaster;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import ca.interfaced.dockmaster.Model.Project;
import ca.interfaced.dockmaster.Model.Reservation;
import ca.interfaced.dockmaster.Model.User;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;


public class MySchedule_Fragment extends Fragment {

    private String mUserID;
    private RealmList<Reservation> mReservations;


    private RecyclerView mScheduleRecyclerView;
    private MySchedule_Fragment.ScheduleAdapter mAdapter;
    public TextView mStartTimeTextView;
    public TextView mEndStartTimeTextView;
    public TextView mProjectNameTextView;
    public TextView mAssetNameTextView;
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

        String userID = getActivity().getIntent().getExtras().getString("userID");
        Log.d("extraFromLogin", userID);
        mUserID = userID;

        Realm realm = Realm.getDefaultInstance();
        User user = realm.where(User.class)
                .equalTo("email", mUserID)
                .findFirst();
        RealmList<Reservation> reservations = user.getReservations();
        mReservations = reservations;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myschedule_fragment, container, false);


        mScheduleRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewScheduleList);
        mScheduleRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new MySchedule_Fragment.ScheduleAdapter(mReservations);
        mScheduleRecyclerView.setAdapter(mAdapter);

        return view;
    }



    private class ScheduleHolder extends RecyclerView.ViewHolder {
        private Reservation mReservation;

        public void bindProject(Reservation reservation) {
            mReservation = reservation;
            mStartTimeTextView.setText(mReservation.getStartTime());
            mEndStartTimeTextView.setText(mReservation.getEndTime());
            mProjectNameTextView.setText(mReservation.getProject().getProjectName());
            mAssetNameTextView.setText(mReservation.getAsset().getAssetName());
            mNotesTextView.setText(mReservation.getNotes());
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
            mStartTimeTextView = (TextView) itemView.findViewById(R.id.start_time_textView);
            mEndStartTimeTextView = (TextView) itemView.findViewById(R.id.end_time_textView);
            mProjectNameTextView = (TextView) itemView.findViewById(R.id.project_name_textView);
            mAssetNameTextView = (TextView) itemView.findViewById(R.id.asset_name_textView);
            mNotesTextView = (TextView) itemView.findViewById(R.id.notes_textView);

        }

    }


    private class ScheduleAdapter extends RecyclerView.Adapter<MySchedule_Fragment.ScheduleHolder> {

        public ScheduleAdapter(RealmList<Reservation> reservations) {
            mReservations = reservations;
        }


        @Override
        public ScheduleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.schedule_item, parent, false);

            return new ScheduleHolder(view);
        }

        @Override
        public void onBindViewHolder(MySchedule_Fragment.ScheduleHolder holder, final int position) {
            Reservation reservation = mReservations.get(position);
            holder.bindProject(reservation);
        }

        @Override
        public int getItemCount() {
            return mReservations.size();
        }
    }


}

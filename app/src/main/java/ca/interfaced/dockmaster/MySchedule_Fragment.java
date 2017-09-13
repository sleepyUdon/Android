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

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ca.interfaced.dockmaster.Model.ProjectItem;
import ca.interfaced.dockmaster.Model.Reservation;


public class MySchedule_Fragment extends Fragment {

    private RecyclerView mScheduleRecyclerView;
    public TextView mStartTimeTextView;
    public TextView mEndStartTimeTextView;
    public TextView mProjectNameTextView;
    public TextView mAssetNameTextView;
    public TextView mNotesTextView;
    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseRecyclerAdapter<Reservation, ScheduleHolder> mFirebaseAdapter;
    private LinearLayoutManager mLinearLayoutManager;


    public static ca.interfaced.dockmaster.MySchedule_Fragment newInstance() {
        return new ca.interfaced.dockmaster.MySchedule_Fragment();
    }


    public MySchedule_Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
    }


    public static class ScheduleHolder extends RecyclerView.ViewHolder {

        public Reservation mReservation;
        public TextView mStartTimeTextView;
        public TextView mEndStartTimeTextView;
        public TextView mProjectNameTextView;
        public TextView mAssetNameTextView;
        public TextView mNotesTextView;

        public ScheduleHolder(View v) {
            super(v);

            mStartTimeTextView = (TextView) itemView.findViewById(R.id.start_time_textView);
            mEndStartTimeTextView = (TextView) itemView.findViewById(R.id.end_time_textView);
            mProjectNameTextView = (TextView) itemView.findViewById(R.id.project_name_textView);
            mAssetNameTextView = (TextView) itemView.findViewById(R.id.asset_name_textView);
            mNotesTextView = (TextView) itemView.findViewById(R.id.notes_textView);

            v.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                }
            });
        }

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String userID = getActivity().getIntent().getExtras().getString("userID");
        Log.d("extraFromLogin", userID);

        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        //TODO: ADD QUERY
        mFirebaseDatabaseReference.child("reservations").child("P0").child("I0").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot projectSnapShot : dataSnapshot.getChildren()) {
                    Reservation reservation = projectSnapShot.getValue(Reservation.class);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("test", "test");
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.myschedule_fragment, container, false);

        mScheduleRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewScheduleList);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mScheduleRecyclerView.setLayoutManager(mLinearLayoutManager);

        return view;
    }



    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void onActivityCreated(Bundle saved) {
        super.onActivityCreated(saved);

        //TODO: ADD QUERY

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Reservation, ScheduleHolder>(
                Reservation.class,
                R.layout.schedule_item,
                ScheduleHolder.class,
                mFirebaseDatabaseReference.child("reservations").child("P0").child("I0")) {

//            @Override
//            protected ProjectItem parseSnapshot(DataSnapshot snapshot) {
//                ProjectItem projectItem = super.parseSnapshot(snapshot);
//                if (projectItem != null) {
////                    projectItem.setName(snapshot.child("name"));
////                    projectItem.setAddress(snapshot.child("address"));
//                }
//                return projectItem;
//            }

            @Override
            protected void populateViewHolder(ScheduleHolder viewHolder,
                                              Reservation project, int position) {
                viewHolder.mStartTimeTextView.setText(project.getStartDate());
                viewHolder.mStartTimeTextView.setVisibility(TextView.VISIBLE);

                viewHolder.mEndStartTimeTextView.setText(project.getEndDate());
                viewHolder.mEndStartTimeTextView.setVisibility(TextView.VISIBLE);

                viewHolder.mProjectNameTextView.setText(project.getProjectName());
                viewHolder.mProjectNameTextView.setVisibility(TextView.VISIBLE);

                viewHolder.mAssetNameTextView.setText(project.getItemName());
                viewHolder.mAssetNameTextView.setVisibility(TextView.VISIBLE);

                viewHolder.mNotesTextView.setText(project.getNotes());
                viewHolder.mNotesTextView.setVisibility(TextView.VISIBLE);

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
                    mScheduleRecyclerView.scrollToPosition(positionStart);
                }
            }
        });

        mScheduleRecyclerView.setAdapter(mFirebaseAdapter);
    }

}

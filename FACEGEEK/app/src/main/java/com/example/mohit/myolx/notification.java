package com.example.mohit.myolx;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class notification extends Fragment {
    private notificationlistadapter adapter;
    private ListView lv;
    private List<notification_message> list;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String name = firebaseAuth.getCurrentUser().getEmail();
        final Activity context = getActivity();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("notification");
        lv = (ListView) view.findViewById(R.id.list20);
        list = new ArrayList<>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    notification_message abc = snapshot.getValue(notification_message.class);
                    list.add(abc);
                }
                Collections.reverse(list);
                adapter = new notificationlistadapter(context,R.layout.notification_dikhega,list);
                lv.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }
}
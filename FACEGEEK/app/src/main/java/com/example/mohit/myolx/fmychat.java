package com.example.mohit.myolx;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class fmychat extends Fragment {

    private List<chatbbox> chatbboxList;
    private ListView lv;
    private Activity context;
    private chatlistadapter adapter;
    AlertDialog.Builder b;

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fmychat, container, false);

        chatbboxList = new ArrayList<>();
        lv = (ListView) v.findViewById(R.id.list21);
        context = getActivity();
        final ProgressDialog progressdialog = new ProgressDialog(getContext());
        progressdialog.setMessage("please wait loading users...");
        progressdialog.show();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String email = user.getEmail();
        int length = email.length();

        b = new AlertDialog.Builder(getContext());

        email = email.substring(0, length - 4);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref = ref.child("users");
        ref.keepSynced(true);
        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    userinfo x = snapshot.getValue(userinfo.class);
                    chatbbox y = new chatbbox(x.getName(),x.getProfurl(),x.getEmail());
                    chatbboxList.add(y);
                }

                adapter = new chatlistadapter(context,R.layout.userdikhega, chatbboxList);
                lv.setAdapter(adapter);
                progressdialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

/*
//access independant features of listview example
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                TextView te = (TextView) view.findViewById(R.id.addfriend);
                te.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(),"Added "+chatbboxList.get(position).getEname()+ " as friend.",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
*/
        return v;
    }

}
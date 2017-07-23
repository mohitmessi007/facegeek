package com.example.mohit.myolx;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class myprofile extends Fragment {

    private DatabaseReference mDatabase;

    private TextView name;
    private TextView scho;
    private TextView col;
    private TextView stat;
    private TextView birth;
    private TextView count;
    private Button up;
    private Button down;
    private ImageView img1;
    private ImageView img2;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myprofile, container, false);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        final String emai = user.getEmail();

        mDatabase = FirebaseDatabase.getInstance().getReference("users");
        mDatabase.keepSynced(true);
        up = (Button) view.findViewById(R.id.editpicture);
        down = (Button) view.findViewById(R.id.edittimeline);
        name = (TextView) view.findViewById(R.id.myname);
        img1 = (ImageView) view.findViewById(R.id.timeline);
        img2 = (ImageView) view.findViewById(R.id.profilepic);
        scho = (TextView) view.findViewById(R.id.school);
        col = (TextView) view.findViewById(R.id.college);
        stat = (TextView) view.findViewById(R.id.state);
        birth = (TextView) view.findViewById(R.id.dob);
        count = (TextView) view.findViewById(R.id.country);
        System.out.println(name.getText().toString());


        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), profilepicture.class));
            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), timelinepicture.class));
            }
        });

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //image upload class requires default constructor
                    userinfo x = snapshot.getValue(userinfo.class);
                       if(emai.equals(x.getEmail().trim())){
                           name.setText(x.getName());
                           scho.setText("Went to School :"+x.getScho());
                           col.setText("Went to College :"+x.getColl());
                           stat.setText("Lives in "+x.getStat()+","+x.getCoun());
                           birth.setText("BirthDay :"+x.getBday());
                           count.setText("From "+x.getStat()+","+x.getCoun());
                           try {
                               Glide.with(myprofile.this).load(x.getTimeurl()).into(img1);
                               Glide.with(myprofile.this).load(x.getProfurl()).into(img2);
                           }
                           catch (Exception e)
                           {
                               e.printStackTrace();
                           }
                           break;
                       }
                }

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }

}
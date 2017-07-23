package com.example.mohit.myolx;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class fmyupload extends Fragment {

    private List<ImageUpload> imglist;
    private ListView lv;
    private imagelistadapter adapter;
    private ImageView img;
    AlertDialog.Builder b;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fmyupload, container, false);
        imglist = new ArrayList<>();
        lv = (ListView) view.findViewById(R.id.list4);

/////////////////////show progress dialog box during list image upload

        final ProgressDialog progressdialog = new ProgressDialog(getContext());
        progressdialog.setMessage("please wait loading list image...");
        progressdialog.show();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String email = user.getEmail();
        int length = email.length();

        b = new AlertDialog.Builder(getContext());


        email = email.substring(0, length - 4);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(uploadimage.FB_DATABASE_PATH);
        ref = ref.child(email).child("images");


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //fetch image data from firebase database
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //image upload class requires default constructor
                    ImageUpload img = snapshot.getValue(ImageUpload.class);
                    imglist.add(img);
                }

                progressdialog.dismiss();
                adapter = new imagelistadapter(getActivity(), R.layout.imagedikhega, imglist);
                //set adapter for listview
                lv.setAdapter(adapter);

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        img = (ImageView) view.findViewById(R.id.imgview);
                        String item2 = ((TextView) view.findViewById(R.id.imgname)).getText().toString();;
                        Intent i = new Intent(getContext(), inlarge.class);
                        String p = imglist.get(position).getUrl();
                        String no = "yes";
                        i.putExtra("url", p);
                        i.putExtra("name", item2);
                        i.putExtra("no",no);
                        startActivity(i);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                progressdialog.dismiss();
            }
        });

        return view;
    }
}

package com.example.mohit.myolx;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class notificationlistadapter extends ArrayAdapter<notification_message> {

    private int resource;
    private Activity context;
    private DatabaseReference mDatabase;
    private List<notification_message> listimage;

    public notificationlistadapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<notification_message> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        listimage = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertview, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        @SuppressLint("ViewHolder") View v = inflater.inflate(resource, null);

        TextView noti = (TextView) v.findViewById(R.id.noti);
        String uploader = listimage.get(position).getUploader();
        if(listimage.get(position).getType().equals("status"))
        {
            noti.setText(uploader + " has uploaded a status");
        }

        if(listimage.get(position).getType().equals("photo"))
        {
            noti.setText(uploader + " has uploaded a photo");
        }
        return v;
    }
}
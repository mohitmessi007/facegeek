package com.example.mohit.myolx;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class fhome extends Fragment {
    private List<ImageUpload> imglist;
    private ListView lv;
    private imagelistadapter adapter;
    private ImageView gal;
    private ImageView stat;
    private boolean saved;
    private Bundle savedState;
    private static final String _FRAGMENT_STATE = "FRAGMENT_STATE";
    private TextView blue;
    public ImageView img;
    public static int i;
    AlertDialog.Builder b;
    public String abc;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


        @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fhome, container, false);
        imglist = new ArrayList<>();
        lv = (ListView) view.findViewById(R.id.list5);
        gal = (ImageView) view.findViewById(R.id.gal);
        final Activity context = getActivity();
        abc = getContext().getPackageName();
        adapter = new imagelistadapter(context, R.layout.imagedikhega, imglist);
        final Context v= getContext();
        i=0;
        stat = (ImageView) view.findViewById(R.id.stat);
        gal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), uploadimage.class));
            }
        });

        stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), statusyahanhai.class));
            }
        });

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
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("img");
        ref.keepSynced(true);
        final DatabaseReference ref1 = ref;

        lv.setAdapter(adapter);
        ref.addChildEventListener(new ChildEventListener() {
                                      @Override
                                      public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                          final int firstPosition = lv.getFirstVisiblePosition();
                                          View v = lv.getChildAt(0);
                                          int top = (v == null) ? 0 : (v.getTop());
                                          final DataSnapshot snapshot = dataSnapshot;
                                          ImageUpload img = dataSnapshot.getValue(ImageUpload.class);
                                          imglist.add(0, img);
                                          adapter.notifyDataSetChanged();
                                          lv.setSelectionFromTop(firstPosition,top);
                                          progressdialog.dismiss();
                                              RemoteViews remoteViews = new RemoteViews(abc,
                                                      R.layout.widget2);
                                              NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getContext()).setSmallIcon(R.drawable.common_google_signin_btn_icon_light).setContent(
                                                      remoteViews);
                                              if(getContext() != null) {
                                                  Intent resultIntent = new Intent(getContext(), Main2Activity.class);
                                                  TaskStackBuilder stackBuilder = TaskStackBuilder.create(getContext());
                                                  stackBuilder.addParentStack(Main2Activity.class);
                                                  stackBuilder.addNextIntent(resultIntent);
                                                  PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
                                                          PendingIntent.FLAG_UPDATE_CURRENT);
                                                  remoteViews.setOnClickPendingIntent(R.id.button1, resultPendingIntent);
                                                  NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                                                  mNotificationManager.notify(100, mBuilder.build());
                                              }

                                          lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                              @Override
                                              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                  String item2 = ((TextView) view.findViewById(R.id.imgname)).getText().toString();;
                                                  Intent i = new Intent(getContext(), inlarge.class);
                                                  String p = imglist.get(position).getUrl();
                                                  String no = "no";
                                                  i.putExtra("url", p);
                                                  i.putExtra("name", item2);
                                                  i.putExtra("no", no);
                                                  startActivity(i);
                                              }
                                          });
                                      }
                                      @Override
                                      public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                                          adapter.notifyDataSetChanged();
                                      }
                                      @Override
                                      public void onChildRemoved(DataSnapshot dataSnapshot) {
                                      }
                                      @Override
                                      public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                                      }
                                      @Override
                                      public void onCancelled(DatabaseError databaseError) {

                                      }
                                  });
        return view;
    }
}
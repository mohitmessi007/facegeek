package com.example.mohit.myolx;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class statusyahanhai extends AppCompatActivity {
    private EditText tv1;
    private Button b1;
    FirebaseAuth firebaseAuth;
    String stat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statusyahanhai);

        tv1 = (EditText) findViewById(R.id.status1);
        b1 = (Button) findViewById(R.id.statusbutton);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stat = tv1.getText().toString();
                Toast.makeText(getApplicationContext(),"Status Uploaded",Toast.LENGTH_LONG).show();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                firebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser user = firebaseAuth.getCurrentUser();
                String email = user.getEmail();
                ImageUpload imageUpload = new ImageUpload(stat,"null",email,0);
                int length = email.length();
                email = email.substring(0,length-4);
                DatabaseReference data = FirebaseDatabase.getInstance().getReference();
                notification_message noti = new notification_message(email,"status");
                data.child("notification").push().setValue(noti);
                databaseReference.child("images").child(email).child("images").push().setValue(imageUpload);
                databaseReference.child("img").push().setValue(imageUpload);

                ///notification

                RemoteViews remoteViews = new RemoteViews(getPackageName(),
                        R.layout.widget);
                NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(getApplicationContext()).setSmallIcon(R.drawable.com_facebook_button_icon).setContent(
                        remoteViews);
                Intent resultIntent = new Intent(getApplicationContext(), Main2Activity.class);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
                stackBuilder.addParentStack(Main2Activity.class);
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                remoteViews.setOnClickPendingIntent(R.id.button1, resultPendingIntent);
                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(100, mBuilder.build());
                startActivity(new Intent(getApplicationContext(), Main2Activity.class));

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            firebaseAuth = FirebaseAuth.getInstance();
            Intent i = new Intent(this, LoginActivity.class);
            i.putExtra("finish", true);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            firebaseAuth.signOut();
            startActivity(i);
            finish();
        }
        else {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

}
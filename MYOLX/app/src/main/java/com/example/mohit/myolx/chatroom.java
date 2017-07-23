package com.example.mohit.myolx;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class chatroom extends AppCompatActivity {

    private FirebaseListAdapter adapter;
    FirebaseAuth firebaseAuth;
    FloatingActionButton fab;
    RelativeLayout activity_main;
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("chat");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);

        activity_main = (RelativeLayout) findViewById(R.id.main);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        displaychatmessage();
        ref.keepSynced(true);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input = (EditText) findViewById(R.id.input);
                firebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser user = firebaseAuth.getCurrentUser();
                String email = user.getEmail();
                ref.push().setValue(new chatmessage(input.getText().toString(), email));

                input.setText("");
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tabbed, menu);
        return true;
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

    public void displaychatmessage() {
        ListView listofmessages = (ListView) findViewById(R.id.listofmessages);
        adapter = new FirebaseListAdapter<chatmessage>(this,chatmessage.class,R.layout.messageitem,ref) {
            @Override
            protected void populateView(View v, chatmessage model, int position) {
                TextView messagetext,messageuser,messagetime;
                messageuser = (TextView) v.findViewById(R.id.message_text);
                messagetext = (TextView) v.findViewById(R.id.message_user);
                messagetime = (TextView) v.findViewById(R.id.message_time);

                messagetext.setText(model.getMessage());
                messageuser.setText(model.getSender());
                messagetime.setText(DateFormat.format("dd-MM-yyyy (HH:MM)",model.getTime()));
            }
        };
        listofmessages.setAdapter(adapter);
        listofmessages.setStackFromBottom(true);
    }
}
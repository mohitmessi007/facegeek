package com.example.mohit.myolx;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private Button buttonswitch;
    private Button buttonLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        FirebaseUser user = firebaseAuth.getCurrentUser();

        //initializing views
        TextView textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonswitch = (Button) findViewById(R.id.dashboard);
        textViewUserEmail.setText("Welcome "+user.getEmail());

        buttonLogout.setOnClickListener(this);
        buttonswitch.setOnClickListener(this);
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
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if(view == buttonLogout){
            Intent i = new Intent(this, LoginActivity.class);
            i.putExtra("finish", true);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            firebaseAuth.signOut();
            startActivity(i);
            finish();
        }

        if(view == buttonswitch){
            startActivity(new Intent(this, Main2Activity.class));
        }
    }
}

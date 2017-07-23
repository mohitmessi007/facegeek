package com.example.mohit.myolx;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private FirebaseAuth firebaseAuth;
    private LinearLayout txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String email = user.getEmail();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("FaceGeek");
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View navHeaderView = navigationView.inflateHeaderView(R.layout.nav_header_main2);
        TextView name = (TextView) navHeaderView.findViewById(R.id.textViewz);
        name.setText(email);
        wush();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tabbed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            firebaseAuth = FirebaseAuth.getInstance();
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

    public void wush(){
        Fragment frag1 = new fhome();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_main2, frag1).commit();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        final int id = item.getItemId();


        Fragment frag1 = new fhome();
        Fragment frag2 = new fupload();
        Fragment frag3 = new fmyupload();
        Fragment frag7 = new notification();
        Fragment frag4 = new myprofile();
        Fragment frag5 = new mystatus();
        Fragment frag6 = new fmychat();
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.home) {
            fragmentManager.beginTransaction().replace(R.id.content_main2, frag1).commit();
        }
        else if (id == R.id.upload) {
            startActivity(new Intent(getApplicationContext(), uploadimage.class));

        } else if (id == R.id.myupload) {
            fragmentManager.beginTransaction().replace(R.id.content_main2, frag3).commit();
        }
        else if (id == R.id.myprofile) {
            fragmentManager.beginTransaction().replace(R.id.content_main2, frag4).commit();
        }
        else if(id == R.id.feedback){
            startActivity(new Intent(getApplicationContext(), chatroom.class));
        }
        else if(id == R.id.status) {
            startActivity(new Intent(getApplicationContext(), statusyahanhai.class));
        }
        else if(id == R.id.users){
            fragmentManager.beginTransaction().replace(R.id.content_main2, frag6).commit();
        }
        else if(id == R.id.notification)
        {
            fragmentManager.beginTransaction().replace(R.id.content_main2, frag7).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        System.out.print(id);
        return true;
    }
}

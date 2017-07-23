package com.example.mohit.myolx;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ////////declare all my variables///////

    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText school;
    private EditText college;
    private EditText country;
    private EditText state;
    private EditText birthdate;
    public String school2;
    public String college2;
    public String country2;
    public String state2;
    public String birthdate2;
    private Button buttonSignup;
    private Button recover;

    private TextView textViewSignin;

    private String email;
    private ProgressDialog progressDialog;

    private DatabaseReference databaseReference;
    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;
    private EditText user_name,mob;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //initializing firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        //if getCurrentUser does not returns null
        if(firebaseAuth.getCurrentUser() != null){
            //that means user is already logged in
            //so close this activity
            finish();

            //and open profile activity
            startActivity(new Intent(getApplicationContext(), Main2Activity.class));
        }

        databaseReference = FirebaseDatabase.getInstance().getReference();
        user_name = (EditText) findViewById(R.id.username);
        mob = (EditText) findViewById(R.id.mobile);
        school = (EditText) findViewById(R.id.schoo11);
        college = (EditText) findViewById(R.id.college1);
        state = (EditText) findViewById(R.id.state1);
        country = (EditText) findViewById(R.id.country1);
        birthdate = (EditText) findViewById(R.id.bday1);

        //initializing views
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewSignin = (TextView) findViewById(R.id.textViewSignin);

        buttonSignup = (Button) findViewById(R.id.buttonSignup);

        progressDialog = new ProgressDialog(this);

        //attaching listener to button
        buttonSignup.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);
    }


    private void registerUser(){
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-YYYY");
        email = editTextEmail.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();
        school2 = school.getText().toString().trim();
        college2 = college.getText().toString().trim();
        state2 = state.getText().toString().trim();
        country2 = country.getText().toString().trim();
        birthdate2 = birthdate.getText().toString();

        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(school2)){
            Toast.makeText(this,"Please enter School else NULL",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(college2)){
            Toast.makeText(this,"Please enter College else NULL",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(state2)){
            Toast.makeText(this,"Please enter your State",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(country2)){
            Toast.makeText(this,"Please enter your Country",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(birthdate2)){
            Toast.makeText(this,"Please enter BirthDate",Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            save();
                            finish();
                            startActivity(new Intent(getApplicationContext(), Main2Activity.class));
                        }else{
                            //display some message here
                            Toast.makeText(MainActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    private void save()
    {
        String name = user_name.getText().toString().trim();
        String mobi = mob.getText().toString().trim();
        userinfo x = new userinfo(name,mobi,school2,college2,state2,country2,birthdate2,email,"null","null");
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String email = user.getEmail();
        int length = email.length();
        ArrayList<String> friends = new ArrayList<String>();
        friends.add("null");
        email = email.substring(0,length-4);
        System.out.println(email);
        databaseReference.child("users").child(email).setValue(x);
        databaseReference.child("users").child(email).child("list").push().setValue(friends);
    }


//    @Override
//    public void onBackPressed() {
//        finish();
//    }

    @Override
    public void onClick(View view) {

        if(view == buttonSignup){
            registerUser();
        }

        if(view == textViewSignin){
            //open login activity when user taps on the already registered textview
            startActivity(new Intent(this, LoginActivity.class));

        }

    }

    public void next(View v){
        startActivity(new Intent(this, LoginActivity.class));
    }
}

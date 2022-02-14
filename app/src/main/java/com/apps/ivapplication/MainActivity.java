package com.apps.ivapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtemail, edtPassword;
    private TextView registerTV, fotgotpass;
    private Button LoginBTN;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        edtemail = findViewById(R.id.editemail);
        edtPassword = findViewById(R.id.editTextTextPassword);

        LoginBTN = findViewById(R.id.signinbtn);
        LoginBTN.setOnClickListener(this);


        fotgotpass = (TextView) findViewById(R.id.forgotpassTV);
        fotgotpass.setOnClickListener(this);

        registerTV = (TextView) findViewById(R.id.signupTV);
        registerTV.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signupTV:
                startActivity(new Intent(this, Register.class));
                break;
            case R.id.signinbtn:
                userLogin();
                break;
            case R.id.forgotpassTV:
                startActivity(new Intent(this, ForgotPassword.class));
                break;
        }
    }



    private void userLogin() {
        String email = edtemail.getText().toString().trim();
        String pass = edtPassword.getText().toString().trim();

        if (email.isEmpty()){
            edtemail.setError("UserName is Required");
            edtemail.requestFocus();
            return;
        }
        if (pass.isEmpty()){
            edtPassword.setError("Password is Required");
            edtPassword.requestFocus();
            return;
        }
        if (pass.length() < 6){
            edtPassword.setError("Password length Minimum is 6 characters");
            edtPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(MainActivity.this, Home_activity.class));

                }else{
                    Toast.makeText(MainActivity.this, "Failed to login! check credentials", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
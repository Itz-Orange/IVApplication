package com.apps.ivapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;



public class Register extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    private TextView backTV;
    private EditText edtName, edtpass, edtemail;
    private ProgressBar progressBar;
    private Button ContinueonClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        edtName = (EditText) findViewById(R.id.editTextFullName);
        edtemail = (EditText) findViewById(R.id.editTextUsername);
        edtpass = (EditText) findViewById(R.id.editTextPassword);

        ContinueonClick = (Button) findViewById(R.id.ContineBTN);
        ContinueonClick.setOnClickListener(this);

        backTV = (TextView) findViewById(R.id.back);
        backTV.setOnClickListener(this);


        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.ContineBTN:
                ContinueBTN();
                break;

        }
    }

    private void ContinueBTN() {
        String name = edtName.getText().toString().trim();
        String email = edtemail.getText().toString().trim();
        String pass = edtpass.getText().toString().trim();

        if(name.isEmpty()){
            edtName.setError("Full Name is required");
            edtName.requestFocus();
            return;
        }
        if(email.isEmpty()){
            edtemail.setError("Email is required");
            edtemail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edtemail.setError("Please provide valid email");
            edtemail.requestFocus();
            return;
        }
        if(pass.isEmpty()){
            edtpass.setError("Password is required");
            edtpass.requestFocus();
            return;
        }
        if(pass.length() < 6){
            edtpass.setError("Password length Minimum is 6 characters");
        }


        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            user user = new user(name, email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()){
                                        Toast.makeText(Register.this, "Sign Up Successful", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);

                                        startActivity(new Intent(Register.this, MainActivity.class));
                                    }else{
                                        Toast.makeText(Register.this,"Failed to Sign-Up! try again", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(Register.this,"Failed to Sign-Up try again", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

    }
}
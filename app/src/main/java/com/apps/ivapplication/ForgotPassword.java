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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.badge.BadgeUtils;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private EditText emailedttxt;
    private Button resetpassBTN;
    private ProgressBar progressBar;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailedttxt = (EditText) findViewById(R.id.editemail);
        resetpassBTN = (Button) findViewById(R.id.forgotpassBTN);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
         auth = FirebaseAuth.getInstance();
         
         resetpassBTN.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 resetpassBTN();
             }

             private void resetpassBTN() {
                 String email = emailedttxt.getText().toString().trim();

                 if (email.isEmpty()){
                     emailedttxt.setError("Email is Required");
                     emailedttxt.requestFocus();
                     return;
                 }
                 if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                     emailedttxt.setError("Provide Valid Email");
                     emailedttxt.requestFocus();
                     return;
                 }
                 progressBar.setVisibility(View.VISIBLE);
                 auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                     @Override
                     public void onComplete(@NonNull Task<Void> task) {

                         if (task.isSuccessful()){
                             Toast.makeText(ForgotPassword.this, "Check Email to reset password", Toast.LENGTH_LONG).show();
                             startActivity(new Intent(ForgotPassword.this, MainActivity.class));
                         }else {
                             Toast.makeText(ForgotPassword.this, "Try again!", Toast.LENGTH_LONG).show();

                         }
                     }
                 });
             }
         });
    }
}
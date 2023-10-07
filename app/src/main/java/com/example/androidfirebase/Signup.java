package com.example.androidfirebase;

import static android.content.ContentValues.TAG;

import static java.lang.String.valueOf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseUser;

public class Signup extends AppCompatActivity {


    EditText email, password;
    Button SignupBtn;
    ProgressBar progressBar;

    FirebaseAuth mAuth;

    TextView loginNow;

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser != null) {
//            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            startActivity(intent);
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();//initialize FirebaseAuth


        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        SignupBtn = findViewById(R.id.SignupBtn);
        progressBar = findViewById(R.id.progressBar);
        loginNow = findViewById(R.id.loginNow);

        loginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        SignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);

                String emails, passwords;
                
               emails = String.valueOf(email.getText());
               passwords = String.valueOf(password.getText());




               if(TextUtils.isEmpty(emails)){
                    Toast.makeText(Signup.this, "Email can not be empty", Toast.LENGTH_SHORT).show();
                    return ;
                }

               if (TextUtils.isEmpty(passwords)){
                   Toast.makeText(Signup.this,"Passward can not be empty",Toast.LENGTH_LONG);
                   return;
               }




                mAuth.createUserWithEmailAndPassword(emails, passwords)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);

                                if (task.isSuccessful()) {
                                    Toast.makeText(Signup.this, "Account Created", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(Signup.this, Login.class);
                                    startActivity(intent);
                                    finish();
                                } else {

                                    Toast.makeText(Signup.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });

    }
}
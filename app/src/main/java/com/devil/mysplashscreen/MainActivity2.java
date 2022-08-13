package com.devil.mysplashscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2 extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private TextView passTV;
    private TextView emailTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mAuth = FirebaseAuth.getInstance();

        Button  crtAcc = findViewById(R.id.crtACC);
        Button login = findViewById(R.id.login);
        emailTV = findViewById(R.id.emailTV);
        passTV = findViewById(R.id.passTV);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailTV.getText().toString();
                String password = passTV.getText().toString();
                FirebaseAuth.getInstance().getCurrentUser().getEmail();
                loginAccount(email,password);
            }
        });

        crtAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailTV.getText().toString();
                String password = passTV.getText().toString();
                createAccount(email,password);
            }
        });

    }

    private void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this,
                                                                                    new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user,"createAccount");
                }
            }
        });

    }

    private void loginAccount(String email, String Password){
        mAuth.signInWithEmailAndPassword(email,Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user,"login");
                }
            }
        });
    }

    private void updateUI(FirebaseUser user, String status) {
        if(status =="login"){if(user != null) {
            Intent intent = new Intent(MainActivity2.this,Dashboard.class);
            startActivity(intent);
            Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Login Failed...", Toast.LENGTH_SHORT).show();
            emailTV.setText("");
            passTV.setText("");
        }}
        else{
            Intent intent = new Intent(MainActivity2.this,createAccount.class);
            startActivity(intent);
        }
    }


    @Override
    protected void onStart() {
        if(mAuth.getCurrentUser() != null){
            Intent intent = new Intent(MainActivity2.this, Dashboard.class);
            startActivity(intent);
        }
        super.onStart();
    }

}
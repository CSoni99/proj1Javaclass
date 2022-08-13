package com.devil.mysplashscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.devil.mysplashscreen.databinding.ActivityCreateAccountBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class createAccount extends AppCompatActivity {
    private ActivityCreateAccountBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadValues();
            }
        });
    }

    private void uploadValues() {
        String Fname = binding.firstNameTv.getText().toString();
        String Lname = binding.lastNameTv.getText().toString();
        String department = binding.departmentTv.getText().toString();
        String contactNum = binding.contactTv.getText().toString();
        String collegeName = binding.collegeTv.getText().toString();

        crtAccData  valUpload = new crtAccData(Fname,Lname,department,contactNum,collegeName);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbref = database.getReference("userDetails");
        dbref.child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser().getEmail())).setValue(valUpload).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(createAccount.this, "Sucessfully uploaded data", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(createAccount.this,"Failed",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
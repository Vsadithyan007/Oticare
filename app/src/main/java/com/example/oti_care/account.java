package com.example.oti_care;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class account extends AppCompatActivity {
TextView fnm,lnm,em,cps;
Button sout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        FirebaseAuth auth=FirebaseAuth.getInstance();
        FirebaseUser currentUser=auth.getCurrentUser();
        if(currentUser == null){
            Intent i=new Intent(this,home_page.class);
            startActivity(i);
            finish();
        }
        fnm=findViewById(R.id.fname);
        lnm=findViewById(R.id.lname);
        em=findViewById(R.id.eml);
        cps=findViewById(R.id.cp);
        sout=findViewById(R.id.st);
        cps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(account.this,forgot_pass.class);
                startActivity(i);
            }
        });
        sout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        DatabaseReference ref=db.getReference("users").child(currentUser.getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user=snapshot.getValue(User.class);
                if(user != null){
                    fnm.setText("First Name :" +user.ftname);
                    lnm.setText("Last Name :" +user.ltname);
                    em.setText("E-Mail :" +user.email);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    private void logoutUser(){
        FirebaseAuth.getInstance().signOut();
        Intent i=new Intent(account.this,log_in.class);
        startActivity(i);
    }
}
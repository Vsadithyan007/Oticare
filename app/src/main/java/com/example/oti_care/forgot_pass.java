package com.example.oti_care;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class forgot_pass extends AppCompatActivity {
    EditText em;
    Button fo;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_forgot_pass);
        em=findViewById(R.id.semail);
        mAuth= FirebaseAuth.getInstance();

        fo = findViewById(R.id.fo);
        fo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotpassword();
                }
        });
    }
    public void forgotpassword(){
        String A=em.getText().toString();
        if(A.isEmpty()){
            em.setError("Email Not Entered!");
            em.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(A).matches()){
            em.setError("Enter Valid Email Address!");
            em.requestFocus();
            return;
        }
        mAuth.sendPasswordResetEmail(A).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(forgot_pass.this, "Check your email to reset your password!", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(forgot_pass.this,log_in.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(forgot_pass.this, "Try Again! Something wrong happened!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void reload(){}
}
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class log_in extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText e, p;
    TextView fp, s;
    Button l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_log_in);
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            reload();
        }
        l = findViewById(R.id.log);
        s = findViewById(R.id.sp);
        SpannableString con1 = new SpannableString(s.getText().toString());
        con1.setSpan(new UnderlineSpan(), 0, con1.length(), 0);
        s.setText(con1);
        fp = findViewById(R.id.forpass);
        SpannableString con = new SpannableString(fp.getText().toString());
        con.setSpan(new UnderlineSpan(), 0, con.length(), 0);
        fp.setText(con);
        fp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(log_in.this, forgot_pass.class);
                startActivity(i);
            }
        });
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(log_in.this, s_up.class);
                startActivity(i);
            }
        });
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e = findViewById(R.id.email);
                p = findViewById(R.id.pass);
                String A = e.getText().toString();
                String B = p.getText().toString();
                if (A.isEmpty()) {
                    e.setError("Email is Required!");
                    e.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(A).matches()) {
                    e.setError("Enter Valid Email Address!");
                    e.requestFocus();
                    return;
                }
                if (B.isEmpty()) {
                    p.setError("Password is Required!");
                    p.requestFocus();
                    return;
                }
                if (B.length() < 8) {
                    p.setError("Minimum Password Length Should be 8 Characters!");
                    p.requestFocus();
                    return;
                }
                mAuth.signInWithEmailAndPassword(A, B)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(log_in.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(log_in.this, home_page.class);
                                    startActivity(i);
                                } else {
                                    e.setText("");
                                    p.setText("");
                                    Toast.makeText(log_in.this, "Authentication Failed!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        });

    }



        private void reload () {
        }
    }
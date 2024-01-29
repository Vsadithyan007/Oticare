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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class s_up extends AppCompatActivity {

    Button sp;
    TextView ac;
    EditText fnm, lnm, em, ps;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sup);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
        fnm = findViewById(R.id.fname);
        lnm = findViewById(R.id.lname);
        em = findViewById(R.id.semail);
        ps = findViewById(R.id.pass);

        sp = findViewById(R.id.sn);
        ac = findViewById(R.id.acc);
        SpannableString con = new SpannableString(ac.getText().toString());
        con.setSpan(new UnderlineSpan(), 0, con.length(), 0);
        ac.setText(con);
        sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String A = fnm.getText().toString();
                String B = lnm.getText().toString();
                String C = em.getText().toString();
                String D = ps.getText().toString();

                if (A.isEmpty()) {
                    fnm.setError("First Name is Required!");
                    fnm.requestFocus();
                    return;
                }
                if (B.isEmpty()) {
                    lnm.setError("Last Name is Required!");
                    lnm.requestFocus();
                    return;
                }
                if (C.isEmpty()) {
                    em.setError("Email is Required!");
                    em.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(C).matches()) {
                    em.setError("Enter Valid Email Address!");
                    em.requestFocus();
                    return;
                }
                if (D.isEmpty()) {
                    ps.setError("Password is Required!");
                    ps.requestFocus();
                    return;
                }
                if (D.length() < 8) {
                    ps.setError("Minimum Password Length Should be 8 Characters!");
                    ps.requestFocus();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(C, D)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(s_up.this, "New User Created!", Toast.LENGTH_SHORT).show();
                                        User user = new User(A, B, C);
                                        FirebaseDatabase.getInstance().getReference("users")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        Toast.makeText(s_up.this, "Used Saved", Toast.LENGTH_SHORT).show();
                                                        Intent i = new Intent(s_up.this, home_page.class);
                                                        startActivity(i);
                                                        Toast.makeText(s_up.this, "New page opened!", Toast.LENGTH_SHORT).show();
                                                        FirebaseUser user= mAuth.getCurrentUser();
                                                    }
                                                });
                                    } else {
                                        fnm.setText("");
                                        lnm.setText("");
                                        em.setText("");
                                        ps.setText("");
                                        Toast.makeText(s_up.this, "Authentication Failed!", Toast.LENGTH_SHORT).show();
                                    }

                                    }
                                });

                            }
                        });

        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(s_up.this, log_in.class);
                startActivity(i);
            }
        });
    }

    private void reload(){}
}
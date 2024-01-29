package com.example.oti_care;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;


public class home_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decodeView=getWindow().getDecorView();
        int options=View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |View.SYSTEM_UI_FLAG_FULLSCREEN;
        decodeView.setSystemUiVisibility(options);
        setContentView(R.layout.activity_home_page);

        final LinearLayout au=findViewById(R.id.audio);
        final LinearLayout em=findViewById(R.id.emo);
        final LinearLayout vo=findViewById(R.id.voc);
        final LinearLayout ac=findViewById(R.id.acc);


        au.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(home_page.this,audio_player.class);
                startActivity(i);
                finish();
            }
        });

        em.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i1=new Intent(home_page.this,emotions.class);
            startActivity(i1);
        }
    });
        vo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(home_page.this,vocabulary.class);
                startActivity(i2);
            }
        });
        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3=new Intent(home_page.this,account.class);
                startActivity(i3);
            }
        });
    }
    }
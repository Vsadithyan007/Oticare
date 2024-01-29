package com.example.oti_care;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.WindowManager;

import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Locale;

import pl.droidsonroids.gif.GifImageView;

public class emotions extends AppCompatActivity {
    LinearLayout l1,l2,l3,l4,l5,l6,l7,l8;
    GifImageView im1,im2,im3,im4,im5,im6,im7,im8;
    TextToSpeech ts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_emotions);
        im1=findViewById(R.id.hap);
        im2=findViewById(R.id.laugh);
        im3=findViewById(R.id.sa);
        im4=findViewById(R.id.an);
        im5=findViewById(R.id.cr);
        im6=findViewById(R.id.cray);
        im7=findViewById(R.id.con);
        im8=findViewById(R.id.love);
        l1=findViewById(R.id.hpy);
        l2=findViewById(R.id.luh);
        l3=findViewById(R.id.sd);
        l4=findViewById(R.id.agry);
        l5=findViewById(R.id.cy);
        l6=findViewById(R.id.czy);
        l7=findViewById(R.id.cfd);
        l8=findViewById(R.id.lvd);


        ts=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS) {
                    ts.setLanguage(Locale.UK);

                }
            }
        });
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l1.setBackgroundResource(R.drawable.bg_green);
                l2.setBackgroundResource(R.drawable.bg_yellow);
                l3.setBackgroundResource(R.drawable.bg_yellow);
                l4.setBackgroundResource(R.drawable.bg_yellow);
                l5.setBackgroundResource(R.drawable.bg_yellow);
                l6.setBackgroundResource(R.drawable.bg_yellow);
                l7.setBackgroundResource(R.drawable.bg_yellow);
                l8.setBackgroundResource(R.drawable.bg_yellow);
                String toSpeak = "Happy";
                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                ts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l1.setBackgroundResource(R.drawable.bg_yellow);
                l2.setBackgroundResource(R.drawable.bg_green);
                l3.setBackgroundResource(R.drawable.bg_yellow);
                l4.setBackgroundResource(R.drawable.bg_yellow);
                l5.setBackgroundResource(R.drawable.bg_yellow);
                l6.setBackgroundResource(R.drawable.bg_yellow);
                l7.setBackgroundResource(R.drawable.bg_yellow);
                l8.setBackgroundResource(R.drawable.bg_yellow);
                String toSpeak = "Laughing Loud";
                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                ts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l3.setBackgroundResource(R.drawable.bg_green);
                l2.setBackgroundResource(R.drawable.bg_yellow);
                l1.setBackgroundResource(R.drawable.bg_yellow);
                l4.setBackgroundResource(R.drawable.bg_yellow);
                l5.setBackgroundResource(R.drawable.bg_yellow);
                l6.setBackgroundResource(R.drawable.bg_yellow);
                l7.setBackgroundResource(R.drawable.bg_yellow);
                l8.setBackgroundResource(R.drawable.bg_yellow);
                String toSpeak = "Sad";
                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                ts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

            }
        });
        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l4.setBackgroundResource(R.drawable.bg_green);
                l2.setBackgroundResource(R.drawable.bg_yellow);
                l3.setBackgroundResource(R.drawable.bg_yellow);
                l1.setBackgroundResource(R.drawable.bg_yellow);
                l5.setBackgroundResource(R.drawable.bg_yellow);
                l6.setBackgroundResource(R.drawable.bg_yellow);
                l7.setBackgroundResource(R.drawable.bg_yellow);
                l8.setBackgroundResource(R.drawable.bg_yellow);
                String toSpeak = "Angry";
                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                ts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        l5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l5.setBackgroundResource(R.drawable.bg_green);
                l2.setBackgroundResource(R.drawable.bg_yellow);
                l3.setBackgroundResource(R.drawable.bg_yellow);
                l4.setBackgroundResource(R.drawable.bg_yellow);
                l1.setBackgroundResource(R.drawable.bg_yellow);
                l6.setBackgroundResource(R.drawable.bg_yellow);
                l7.setBackgroundResource(R.drawable.bg_yellow);
                l8.setBackgroundResource(R.drawable.bg_yellow);
                String toSpeak = "Crying";
                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                ts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

            }
        });
        l6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l6.setBackgroundResource(R.drawable.bg_green);
                l2.setBackgroundResource(R.drawable.bg_yellow);
                l3.setBackgroundResource(R.drawable.bg_yellow);
                l4.setBackgroundResource(R.drawable.bg_yellow);
                l5.setBackgroundResource(R.drawable.bg_yellow);
                l1.setBackgroundResource(R.drawable.bg_yellow);
                l7.setBackgroundResource(R.drawable.bg_yellow);
                l8.setBackgroundResource(R.drawable.bg_yellow);
                String toSpeak = "Crazy";
                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                ts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        l7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l7.setBackgroundResource(R.drawable.bg_green);
                l2.setBackgroundResource(R.drawable.bg_yellow);
                l3.setBackgroundResource(R.drawable.bg_yellow);
                l4.setBackgroundResource(R.drawable.bg_yellow);
                l5.setBackgroundResource(R.drawable.bg_yellow);
                l6.setBackgroundResource(R.drawable.bg_yellow);
                l1.setBackgroundResource(R.drawable.bg_yellow);
                l8.setBackgroundResource(R.drawable.bg_yellow);
                String toSpeak = "Confused";
                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                ts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        l8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l8.setBackgroundResource(R.drawable.bg_green);
                l2.setBackgroundResource(R.drawable.bg_yellow);
                l3.setBackgroundResource(R.drawable.bg_yellow);
                l4.setBackgroundResource(R.drawable.bg_yellow);
                l5.setBackgroundResource(R.drawable.bg_yellow);
                l6.setBackgroundResource(R.drawable.bg_yellow);
                l7.setBackgroundResource(R.drawable.bg_yellow);
                l1.setBackgroundResource(R.drawable.bg_yellow);
                String toSpeak = "Feeling Loved";
                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                ts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }
    public void onPause(){
        if(ts !=null){
            ts.stop();
            ts.shutdown();
        }
        super.onPause();
    }
}
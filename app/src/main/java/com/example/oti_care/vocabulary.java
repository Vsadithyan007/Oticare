package com.example.oti_care;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import pl.droidsonroids.gif.GifImageView;

public class vocabulary extends AppCompatActivity {
    LinearLayout l1,l2,l3,l4,l5,l6,l7;
    GifImageView im1,im2,im3,im4,im5,im6;
    EditText e1;
    Button b1;
    TextToSpeech ts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_vocabulary);
        im1 = findViewById(R.id.hi);
        im2 = findViewById(R.id.hw);
        im3 = findViewById(R.id.sy);
        im4 = findViewById(R.id.tk);
        im5 = findViewById(R.id.mg);
        im6 = findViewById(R.id.whts);

        e1 = findViewById(R.id.ed);
        b1 = findViewById(R.id.sp);

        l1 = findViewById(R.id.l1);
        l2 = findViewById(R.id.l2);
        l3 = findViewById(R.id.l3);
        l4 = findViewById(R.id.l4);
        l5 = findViewById(R.id.l5);
        l6 = findViewById(R.id.l6);
        l7 = findViewById(R.id.cus);

        ts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    ts.setLanguage(Locale.UK);
                }
            }
        });
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l1.setBackgroundResource(R.drawable.bg_green);
                l2.setBackgroundResource(R.drawable.bg_red);
                l3.setBackgroundResource(R.drawable.bg_red);
                l4.setBackgroundResource(R.drawable.bg_red);
                l5.setBackgroundResource(R.drawable.bg_red);
                l6.setBackgroundResource(R.drawable.bg_red);
                l7.setBackgroundResource(R.drawable.bg_red);
                String toSpeak = "Hai";
                Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                ts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l1.setBackgroundResource(R.drawable.bg_red);
                l2.setBackgroundResource(R.drawable.bg_green);
                l3.setBackgroundResource(R.drawable.bg_red);
                l4.setBackgroundResource(R.drawable.bg_red);
                l5.setBackgroundResource(R.drawable.bg_red);
                l6.setBackgroundResource(R.drawable.bg_red);
                l7.setBackgroundResource(R.drawable.bg_red);
                String toSpeak = "How's it going?";
                Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                ts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l3.setBackgroundResource(R.drawable.bg_green);
                l2.setBackgroundResource(R.drawable.bg_red);
                l7.setBackgroundResource(R.drawable.bg_red);
                l4.setBackgroundResource(R.drawable.bg_red);
                l5.setBackgroundResource(R.drawable.bg_red);
                l6.setBackgroundResource(R.drawable.bg_red);
                l1.setBackgroundResource(R.drawable.bg_red);
                String toSpeak = "Sorry!";
                Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                ts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

            }
        });
        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l4.setBackgroundResource(R.drawable.bg_green);
                l7.setBackgroundResource(R.drawable.bg_red);
                l2.setBackgroundResource(R.drawable.bg_red);
                l3.setBackgroundResource(R.drawable.bg_red);
                l5.setBackgroundResource(R.drawable.bg_red);
                l6.setBackgroundResource(R.drawable.bg_red);
                l1.setBackgroundResource(R.drawable.bg_red);
                String toSpeak = "Thank You!";
                Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                ts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        l5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l5.setBackgroundResource(R.drawable.bg_green);
                l7.setBackgroundResource(R.drawable.bg_red);
                l2.setBackgroundResource(R.drawable.bg_red);
                l3.setBackgroundResource(R.drawable.bg_red);
                l4.setBackgroundResource(R.drawable.bg_red);
                l6.setBackgroundResource(R.drawable.bg_red);
                l1.setBackgroundResource(R.drawable.bg_red);
                String toSpeak = "Good Morning";
                Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                ts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        l6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l6.setBackgroundResource(R.drawable.bg_green);
                l7.setBackgroundResource(R.drawable.bg_red);
                l2.setBackgroundResource(R.drawable.bg_red);
                l3.setBackgroundResource(R.drawable.bg_red);
                l4.setBackgroundResource(R.drawable.bg_red);
                l5.setBackgroundResource(R.drawable.bg_red);
                l1.setBackgroundResource(R.drawable.bg_red);
                String toSpeak = "What's Up";
                Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                ts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l7.setBackgroundResource(R.drawable.bg_green);
                l2.setBackgroundResource(R.drawable.bg_red);
                l3.setBackgroundResource(R.drawable.bg_red);
                l4.setBackgroundResource(R.drawable.bg_red);
                l5.setBackgroundResource(R.drawable.bg_red);
                l6.setBackgroundResource(R.drawable.bg_red);
                l1.setBackgroundResource(R.drawable.bg_red);
                String toSpeak = e1.getText().toString();
                Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
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
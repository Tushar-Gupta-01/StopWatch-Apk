package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.Toast;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {
    GifImageView gifImageView;
    Button b1,b2,b3;
    Chronometer chronometer;
    ImageView i;
    private boolean isrunning=false;
    private long pause=0;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=findViewById(R.id.button);
        b2=findViewById(R.id.button2);
        b3=findViewById(R.id.button3);
        i=findViewById(R.id.imageView);
        gifImageView=(GifImageView)findViewById(R.id.gif);
        chronometer=findViewById(R.id.custom_chronometer);
        chronometer.setFormat("Timer:%s");
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((SystemClock.elapsedRealtime() - chronometer.getBase() >= 15_000))
                {
                    Toast.makeText(MainActivity.this, "First lap Completed", Toast.LENGTH_SHORT).show();
                }
                else if(((SystemClock.elapsedRealtime() - chronometer.getBase() >= 30_000)))
                {
                    Toast.makeText(MainActivity.this, "Second lap completed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isrunning)
                {
                    chronometer.setBase(SystemClock.elapsedRealtime() - pause);
                    chronometer.start();
                    gifImageView.setVisibility(View.VISIBLE);
                    i.setVisibility(View.INVISIBLE);
                    isrunning=true;
                }

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isrunning)
                {
                    chronometer.stop();
                    pause=SystemClock.elapsedRealtime() - chronometer.getBase();
                    gifImageView.setVisibility(View.INVISIBLE);
                    i.setVisibility(View.VISIBLE);
                    isrunning=false;
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.stop();
                i.setVisibility(View.INVISIBLE);
                gifImageView.setVisibility(View.INVISIBLE);
                pause=0;
                isrunning=false;
            }
        });

    }
}
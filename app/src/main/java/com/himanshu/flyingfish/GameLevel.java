package com.himanshu.flyingfish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class GameLevel extends AppCompatActivity {

    private Button LowLevel, MediumLevel, HighLevel;
    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;
    private ImageButton VolumeUp, VolumeOff;

    private MediaPlayer click;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_level);

        LowLevel = findViewById(R.id.low_level_btn);
        MediumLevel = findViewById(R.id.medium_level_btn);
        HighLevel = findViewById(R.id.high_level_btn);

        VolumeUp = findViewById(R.id.volume_up_btn);
        VolumeOff = findViewById(R.id.volume_off_btn);

        click = MediaPlayer.create(this, R.raw.button_click);

        if(Volume.Sound.equals("on")){
            VolumeUp.setVisibility(View.VISIBLE);
            VolumeOff.setVisibility(View.INVISIBLE);
        } else if(Volume.Sound.equals("off")){
            VolumeUp.setVisibility(View.INVISIBLE);
            VolumeOff.setVisibility(View.VISIBLE);
        }

        LowLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Volume.Sound.equals("on")) {
                    click.start();
                }
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("level", "low");
                startActivity(i);
            }
        });

        MediumLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Volume.Sound.equals("on")) {
                    click.start();
                }
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("level", "medium");
                startActivity(i);
            }
        });

        HighLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Volume.Sound.equals("on")) {
                    click.start();
                }
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("level", "high");
                startActivity(i);
            }
        });

        VolumeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VolumeUp.setVisibility(View.INVISIBLE);
                VolumeOff.setVisibility(View.VISIBLE);
                Volume.Sound = "off";
            }
        });

        VolumeOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VolumeOff.setVisibility(View.INVISIBLE);
                VolumeUp.setVisibility(View.VISIBLE);
                click.start();
                Volume.Sound = "on";
            }
        });
    }

    public void Score(View view){
        if(Volume.Sound.equals("on")) {
            click.start();
        }
        startActivity(new Intent(getApplicationContext(), Score.class));
    }

    public void Share(View view) {
        if(Volume.Sound.equals("on")) {
            click.start();
        }
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody ="The Below link is for download The Flying Fish Game."+
                "\nhttps://play.google.com/store/apps/details?id=" + getPackageName();
        String shareSub = "Flying Fish Game";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share using"));
    }

    @Override
    public void onBackPressed() {
        if(mBackPressed + TIME_INTERVAL > System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }else {
//            Toast.makeText(this, "Touch again to exit", Toast.LENGTH_SHORT).show();
        }
        mBackPressed = System.currentTimeMillis();
    }
}

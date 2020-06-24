package com.himanshu.flyingfish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.himanshu.flyingfish.GameView.FlyingFishViewHigh;
import com.himanshu.flyingfish.GameView.FlyingFishViewLow;
import com.himanshu.flyingfish.GameView.FlyingFishViewMedium;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private FlyingFishViewLow gameViewLow;
    private FlyingFishViewMedium gameViewMedium;
    private FlyingFishViewHigh gameViewHigh;
    private String Level;
    public MediaPlayer BackgroundMusic, YellowBallMusic, RedBallMusic;

    private Handler handler = new Handler();
    private final static long Interval = 30;

    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameViewLow = new FlyingFishViewLow(this);
        gameViewMedium = new FlyingFishViewMedium(this);
        gameViewHigh = new FlyingFishViewHigh(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Level = getIntent().getExtras().get("level").toString();
        }

        switch (Level) {
            case "low":
                setContentView(gameViewLow);
                break;
            case "medium":
                setContentView(gameViewMedium);
                break;
            case "high":
                setContentView(gameViewHigh);
                break;
        }

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        switch (Level) {
                            case "low":
                                gameViewLow.invalidate();
                                break;
                            case "medium":
                                gameViewMedium.invalidate();
                                break;
                            case "high":
                                gameViewHigh.invalidate();
                                break;
                        }

                    }
                });
            }
        }, 0, Interval);

        BackgroundMusic = MediaPlayer.create(MainActivity.this, R.raw.game_background);

    }

    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(this, "Touch again to exit game", Toast.LENGTH_SHORT).show();
        }
        mBackPressed = System.currentTimeMillis();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(Volume.Sound.equals("on")) {
            BackgroundMusic.start();
            BackgroundMusic.setLooping(true);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(BackgroundMusic.isPlaying()) {
            BackgroundMusic.stop();
        }
    }
}

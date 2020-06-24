package com.himanshu.flyingfish;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GameOver extends AppCompatActivity {

    private Button StartGmeAgain;
    private TextView ScoreTxt;
    private String score, level;
    private EditText NameTxt;
    private MediaPlayer ring, click;
    private DatabaseHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        ring = MediaPlayer.create(GameOver.this, R.raw.game_over);
        click = MediaPlayer.create(GameOver.this, R.raw.button_click);

        if (Volume.Sound.equals("on")) {
            ring.start();
        }

        StartGmeAgain = findViewById(R.id.play_again_btn);
        NameTxt = findViewById(R.id.name_txt);
        ScoreTxt = findViewById(R.id.score_txt);

        myDb = new DatabaseHelper(GameOver.this);

        level = getIntent().getExtras().get("level").toString();
        score = getIntent().getExtras().get("score").toString();
        ScoreTxt.setText("Your Score : " + score);

        StartGmeAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Volume.Sound.equals("on")) {
                    click.start();
                }
                startActivity(new Intent(getApplicationContext(), GameLevel.class));
                finish();
            }
        });
    }

    public void SaveInfo(View view) {
        if(Volume.Sound.equals("on")) {
            click.start();
        }
        String Name = NameTxt.getText().toString();
        String SaveCurrentDate, SaveCurrentTime;
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        SaveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:MM:SS a");
        SaveCurrentTime = currentTime.format(calendar.getTime());

        if (TextUtils.isEmpty(Name)) {
            NameTxt.setError("Name is required");
        } else {
            boolean IsInserted = myDb.insertData(Name, score, level, SaveCurrentDate, SaveCurrentTime);
            if (IsInserted) {
                Toast.makeText(GameOver.this, "Stored", Toast.LENGTH_SHORT).show();
                NameTxt.setText("");
            } else {
                Toast.makeText(GameOver.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void ShowInfo(View view) {
        if(Volume.Sound.equals("on")) {
            click.start();
        }
        startActivity(new Intent(getApplicationContext(), Score.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), GameLevel.class));
        finish();
    }
}

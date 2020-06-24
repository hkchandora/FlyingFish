package com.himanshu.flyingfish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.view.View;
import android.widget.Toast;

import com.himanshu.flyingfish.Adapter.DatabaseRecyclerAdapter;
import com.himanshu.flyingfish.Model.UserScore;

import java.util.ArrayList;

public class Score extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MediaPlayer click;
    ArrayList<UserScore> objModelClassArrayList;

    DatabaseRecyclerAdapter objDatabaseRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);


        recyclerView = findViewById(R.id.recyclerView_score);
        objModelClassArrayList = new ArrayList<>();

        click = MediaPlayer.create(Score.this, R.raw.button_click);

    }

    @Override
    protected void onStart() {
        super.onStart();
        try
        {
           DatabaseHelper objMyDatabaseClass = new DatabaseHelper(this);
            SQLiteDatabase objSqliteDatabase = objMyDatabaseClass.getReadableDatabase();
            if(objSqliteDatabase != null) {
                Cursor objCursor = objSqliteDatabase.rawQuery("select * from ScoreTable", null);
                if (objCursor.getCount() == 0) {
                    Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
                } else {
                    while (objCursor.moveToNext()) {
                        objModelClassArrayList.add(new UserScore(objCursor.getString(0),
                                objCursor.getString(1),
                                objCursor.getString(2),
                                objCursor.getString(3),
                                objCursor.getString(4),
                                objCursor.getString(5)
                                ));
                    }
                    objDatabaseRecyclerAdapter = new DatabaseRecyclerAdapter(objModelClassArrayList);
                    recyclerView.hasFixedSize();
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    recyclerView.setAdapter(objDatabaseRecyclerAdapter);
                }

            }
            else {
                Toast.makeText(this, "Database is Null", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this, "ShowValueFromDb: "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void BackBtn(View view) {
        if(Volume.Sound.equals("on")) {
            click.start();
        }
        finish();
    }
}

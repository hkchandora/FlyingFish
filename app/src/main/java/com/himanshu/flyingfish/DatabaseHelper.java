package com.himanshu.flyingfish;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Score.db";
    private static final String TABLE_NAME = "ScoreTable";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "Name";
    private static final String COL_3 = "Score";
    private static final String COL_4 = "Level";
    private static final String COL_5 = "Date";
    private static final String COL_6 = "Time";

    private static  String DB_PATE = "";
    private SQLiteDatabase myDatabase;
    private final Context myContext;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        if(Build.VERSION.SDK_INT >=15) {
            DB_PATE = context.getApplicationInfo().dataDir+ "/database/";
        } else {
            DB_PATE = Environment.getDataDirectory()+ "/data/"+ context.getPackageName()+"/database/";
        }
        this.myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ( Date TEXT, Time TEXT, ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SCORE TEXT, LEVEL TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String Name, String Score, String Level, String Date, String Time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, Name);
        contentValues.put(COL_3, Score);
        contentValues.put(COL_4, Level);
        contentValues.put(COL_5, Date);
        contentValues.put(COL_6, Time);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }

    public void checkAddCopyDatabase() {
        boolean dbExist = checkDatabase();
        if(dbExist){
            Log.d("TAG", "Database Already Exist.");
        } else {
            this.getReadableDatabase();
        }
        try {
            copyDatabase();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("TAG","Error Copy Database.");
        }
    }
    public boolean checkDatabase() {
        SQLiteDatabase checkdb = null;
        String myPath = DB_PATE+DATABASE_NAME;
        checkdb = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        if (checkdb != null){
            checkdb.close();
        }
        return checkdb!= null ? true : false;
    }

    public void copyDatabase() throws IOException {
        InputStream myInput= myContext.getAssets().open(DATABASE_NAME);
        String outFileName = DB_PATE + DATABASE_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public synchronized void close(){
        if(myDatabase != null) {
            myDatabase.close();
        }
        super.close();
    }

    public Cursor QueryData(String query) {
        return myDatabase.rawQuery(query,null);
    }
}

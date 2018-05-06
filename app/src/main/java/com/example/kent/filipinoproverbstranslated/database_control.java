package com.example.kent.filipinoproverbstranslated;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 2olage06 on 03/05/2018.
 */

public class database_control extends SQLiteOpenHelper {
    public database_control(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,"FILIPINO_PROVERBS.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE PROVERBS (ID INTEGER PRIMARY KEY AUTOINCREMENT, FILIPINO TEXT UNIQUE, ENGLISH TEXT, MEANING TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PROVERBS;");
        onCreate(sqLiteDatabase);
    }


//insert
    public void insertProverb(String filipino, String english, String meaning)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("FILIPINO", filipino);
        contentValues.put("ENGLISH", english);
        contentValues.put("MEANING", meaning);
        this.getWritableDatabase().insertOrThrow("PROVERBS","",contentValues);
    }

//delete
    public void deleteProverb(String filipino)
    {
        this.getWritableDatabase().delete("PROVERBS","FILIPINO='"+filipino+"'",null);
    }

//update
    public void updateProverb(String oldFil, String newFil, String oldEng, String newEng, String oldMng, String newMng)
    {
        this.getWritableDatabase().execSQL("UPDATE PROVERBS SET FILIPINO='"+newFil+"' ENGLISH='"+newEng+"' " +
                "MEANING='"+newMng+"' WHERE FILIPINO='"+oldFil+"' ENGLISH='"+oldEng+"' MEANING="+oldMng+"'");
    }

//list all data
    public Cursor listAll(TextView textView)
    {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM PROVERBS", null);
        textView.setText("");
        while(cursor.moveToNext()){
            textView.append(cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3)+"\n");
        }
        return cursor;
    }

    public Cursor listAllMain()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM PROVERBS", null);
        return data;
    }




}

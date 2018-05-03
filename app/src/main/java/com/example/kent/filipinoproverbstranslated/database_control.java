package com.example.kent.filipinoproverbstranslated;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

/**
 * Created by 2olage06 on 03/05/2018.
 */

public class database_control extends SQLiteOpenHelper {
    public database_control(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "FILIPINO_PROVERBS.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE PROVERBS (ID INTEGER PRIMARY KEY AUTOINCREMENT, FILIPINO VARCHAR(MAX), ENGLISH VARCHAR(MAX), MEANING VARCHAR(MAX)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS PROVERBS;");
        onCreate(db);
    }

    public void insertProverb(String filipino, String english, String meaning)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("FILIPINO", filipino);
        contentValues.put("ENGLISH", english);
        contentValues.put("MEANING", meaning);
        this.getWritableDatabase().insertOrThrow("FILIPINO","",contentValues);
    }

    public void deleteProverb(String filipino)
    {
        this.getWritableDatabase().delete("PROVERBS","FILIPINO='"+filipino+"'",null);
    }

    public void updateProverb(String old_fil, String new_fil, String old_eng, String new_eng, String old_mng, String new_mng)
    {
        this.getWritableDatabase().execSQL("UPDATE PROVERBS SET FILIPINO='"+new_fil+"' ENGLISH='"+new_eng+"' " +
                "MEANING='"+new_mng+"' WHERE FILIPINO='"+old_fil+"' ENGLISH='"+old_eng+"' MEANING="+old_mng+"'");
    }

    public void listAll(TextView textView)
    {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM PROVERBS", null);
        while(cursor.moveToNext()){
            textView.append(cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3));
        }
    }

}

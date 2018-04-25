package com.example.kent.filipinoproverbstranslated;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Kent on 15/04/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    static final int VERSION = 1;
    static final String DATABASE_NAME = "Proverbs_Database";

    public DatabaseHelper(Context ctx)
    {
        super(ctx, DATABASE_NAME, null, VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Proverbs (ID INTEGER PRIMARY KEY, categoryID INTEGER, filipino VARCHAR(max), english VARCHAR(max), meaning VARCHAR(max) ");

    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Proverbs");
        onCreate(db);
    }

    public long insertProverb(long categoryID ,String filipino, String english, String meaning)
    {
        SQLiteDatabase db = getWritableDatabase();
        SQLiteStatement st = db.compileStatement(
                "INSERT INTO Proverbs(categoryID, filipino, english,meaning) values(?,?,?,?)"
        );
        st.bindLong(1, categoryID);
        st.bindString(2, filipino);
        st.bindString(3, english);
        st.bindString(4, meaning);
        long ID = st.executeInsert();

        return ID;
    }

    public  long updateProverb(long ID, long categoryID, String filipino, String english, String meaning)
    {
        SQLiteDatabase db = getWritableDatabase();
        SQLiteStatement st = db.compileStatement(
                "UPDATE Proverbs SET categoryID = ?, filipino = ?, english = ?, meaning = ? WHERE filipino = ?"
        );
        st.bindLong(1, categoryID);
        st.bindString(2, filipino);
        st.bindString(3, english);
        st.bindString(4, meaning);
        int affectedRows = st.executeUpdateDelete();

        return affectedRows;
    }




}

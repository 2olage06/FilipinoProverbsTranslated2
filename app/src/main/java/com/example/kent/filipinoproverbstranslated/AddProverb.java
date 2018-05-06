package com.example.kent.filipinoproverbstranslated;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Kent on 24/04/2018.
 */

public class AddProverb extends Activity {

    EditText filipino, english, meaning;
    TextView textView;
    database_control controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_proverb);

        filipino = (EditText)findViewById(R.id.filipino_input);
        english = (EditText)findViewById(R.id.english_input);
        meaning = (EditText)findViewById(R.id.meaning_input);
        textView = (TextView)findViewById(R.id.textView1);

        controller = new database_control(this,"",null,1);
    }

    public void onClick(View v)
    {
        switch (v.getId())
        {
            //adding to database
            case R.id.addButton:
                try{

                }catch(SQLiteException e){
                    Toast.makeText(AddProverb.this, "This proverb already exists",Toast.LENGTH_SHORT).show();
                }
                controller.insertProverb(filipino.getText().toString(), english.getText().toString(),meaning.getText().toString());
                break;

            //deleting data
            case R.id.deleteButton:
                controller.deleteProverb(filipino.getText().toString());
                break;

            //updating proverbs from database
            case R.id.updateButton:
                AlertDialog.Builder dialog = new AlertDialog.Builder(AddProverb.this);
                dialog.setTitle("ENTER NEW PROVERB");

                final EditText newFil = new EditText(this);
                final EditText newEng = new EditText(this);
                final EditText newMng = new EditText(this);
                dialog.setView(newFil);
                dialog.setView(newEng);
                dialog.setView(newMng);

                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        controller.updateProverb(filipino.getText().toString(),newFil.getText().toString(),
                                english.getText().toString(), newEng.getText().toString(),
                                meaning.getText().toString(),newMng.getText().toString());
                    }
                });

                break;

            //listing all rows in text view
            case R.id.listAll:
                controller.listAll(textView);
                break;
        }

    }



}
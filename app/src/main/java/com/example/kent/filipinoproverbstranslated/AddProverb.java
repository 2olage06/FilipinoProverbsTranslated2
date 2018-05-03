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

        filipino = (EditText)findViewById(R.id.filipino);
        english = (EditText)findViewById(R.id.english);
        meaning = (EditText)findViewById(R.id.meaning);
        textView = (TextView)findViewById(R.id.textView1);

        controller = new database_control(this,"",null,1);
    }

    public void click(View v)
    {
        switch (v.getId())
        {
            case R.id.addButton:
                try{

                }catch(SQLiteException e){
                    Toast.makeText(AddProverb.this, "This proverb already exists",Toast.LENGTH_SHORT).show();
                }
                controller.insertProverb(filipino.getText().toString(), english.getText().toString(),meaning.getText().toString());
                break;
            case R.id.deleteButton:
                controller.deleteProverb(filipino.getText().toString());
                break;
            case R.id.updateButton:
                AlertDialog.Builder dialog = new AlertDialog.Builder(AddProverb.this);
                dialog.setTitle("ENTER NEW PROVERB");

                final EditText new_fil = new EditText(this);
                final EditText new_eng = new EditText(this);
                final EditText new_mng = new EditText(this);
                dialog.setView(new_fil);
                dialog.setView(new_eng);
                dialog.setView(new_mng);

                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        controller.updateProverb(filipino.getText().toString(),new_fil.getText().toString(),
                                english.getText().toString(), new_eng.getText().toString(),
                                meaning.getText().toString(),new_mng.getText().toString());
                    }
                });

                break;
            case R.id.listAll:
                controller.listAll(textView);
                break;
        }

    }



}
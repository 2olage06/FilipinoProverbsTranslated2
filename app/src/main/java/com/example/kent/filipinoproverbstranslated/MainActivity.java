package com.example.kent.filipinoproverbstranslated;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import org.w3c.dom.Text;
import java.util.ArrayList;
import java.util.List;

import static com.example.kent.filipinoproverbstranslated.R.id.all;
import static com.example.kent.filipinoproverbstranslated.R.id.listView;

public class MainActivity extends AppCompatActivity {

    database_control controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.floatingActionButton1);
        fab1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent addIntent = new Intent(MainActivity.this, AddProverb.class);
                startActivity(addIntent);
            }
        });

        showListview();




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        // MenuItem item = menu.findItem(R.id.search);
        // SearchView sv = (SearchView) MenuItemCompat.getActionView(item);
        // sv.setOnQueryTextListener(new SearchHandler());
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.category:
                Toast.makeText(this, "Choose a Category", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }



    public void showListview() {
        ListView listview = (ListView) findViewById(listView);
        controller = new database_control(this, "", null, 1);

        ArrayList<String> proverbsList = new ArrayList<>();
        Cursor data = controller.listAllMain();

        if (data.getCount() == 0) {
            Toast.makeText(MainActivity.this, "DATABASE_EMPTY", Toast.LENGTH_SHORT).show();
        } else {
            while (data.moveToNext()) {
                proverbsList.add(data.getString(1));
                proverbsList.add(data.getString(2));
                proverbsList.add(data.getString(3));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, proverbsList);
                listview.setAdapter(listAdapter);
            }
        }


    }
}

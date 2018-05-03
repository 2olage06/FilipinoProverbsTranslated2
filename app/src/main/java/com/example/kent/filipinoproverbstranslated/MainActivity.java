package com.example.kent.filipinoproverbstranslated;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.floatingActionButton1);
        fab1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view) {
            Intent addIntent = new Intent(MainActivity.this, AddProverb.class);
            startActivity(addIntent);
        }
        });


      /* ListView listView = (ListView) findViewById(R.id.listView1);


        ArrayList<String> theList = new ArrayList<>();
        Cursor data = Proverbs_Database.getListContent();

        if(data.getCount()== 0)
        {
            Toast.makeText(MainActivity.this, "database empty", Toast.LENGTH_LONG).show();
        }
        else{
            while(data.moveToNext()){
                theList.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, theList);
                listView.setAdapter(listAdapter);
            }*/



    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView sv = (SearchView) MenuItemCompat.getActionView(item);
        sv.setOnQueryTextListener(new SearchHandler());
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.category:
                Toast.makeText(this, "Choose a Category", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }

    class SearchHandler implements SearchView.OnQueryTextListener {


        public SearchHandler(){

        }

        public boolean onQueryTextChange(String txt) {
            // do nothing... (this method runs when the user types a new character)
            return true;
        }

        public boolean onQueryTextSubmit(String txt) {
            // show the search text in an alert dialog
            new AlertDialog.Builder(MainActivity.this).setPositiveButton("OK", null).
                    setMessage(txt).show();
            return true;
        }
    }

}

package com.eae.kipper.jung.gabriel.mitgliederverwaltung;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton add;
    MyDBManager db = new MyDBManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = (FloatingActionButton)findViewById(R.id.foating_add);
        add.setOnClickListener(handler);
        //db.insertMitglied("Mustermann", "Max", "0689511111", "017688888888", "kontakt@bla.bla", "Musterstraße 66", "66976", "MusterOrt", "aktiv");
        //Start-CursorAdapter (2 Zeilige Liste!)        !NOT YET FINISHED!
        ListView listView = (ListView)findViewById(R.id.list_2sp);
        db = new MyDBManager(this);
        Context cxt = this;
        int itemLayout = android.R.layout.simple_list_item_2;
        Cursor cursor = db.selectAll();
        String[] from = new String[] {db.SPALTE_VORNAME + " " + db.SPALTE_NAME, db.SPALTE_PLZ + " " + db.SPALTE_ORT};

        //display in long period of time
        Toast.makeText(getApplicationContext(), from.toString(),
                Toast.LENGTH_LONG).show();

        //int[] to = new int[] {android.R.id.text1, android.R.id.text2};
        //SimpleCursorAdapter adapter = new SimpleCursorAdapter(cxt, itemLayout, cursor, from, to, 0);
        //listView.setAdapter(adapter);
        //End-CursorAdapter


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == add.getId()){
                Intent intent = new Intent(getApplicationContext(), AddEdit.class);
                startActivity(intent);
            }
        }
    };

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_Name:
                //anch Namen sortieren
                Toast.makeText(getApplicationContext(),
                        R.string.toast_sort_name,
                        Toast.LENGTH_LONG).show();
                return true;
            case R.id.sort_Vorname:
                //nach Vornamen sortieren
                Toast.makeText(getApplicationContext(),
                        R.string.toast_sort_vorname,
                        Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

package com.eae.kipper.jung.gabriel.mitgliederverwaltung;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton add;
    MyDBManager db = new MyDBManager(this);
    SimpleCursorAdapter adapter;
    int listID;
    Context cxt = this;

    private static final int NAME = 0;
    private static final int SURNAME = 1;
    private static final int NONE = 2;
    private static boolean nameAsc = true;
    private static boolean surnameAsc = true;

    private static int currentSorting = SURNAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = (FloatingActionButton) findViewById(R.id.foating_add);
        add.setOnClickListener(handler);

       // db.insertMitglied("Mustermann", "Max", "0689511111", "017688888888", "kontakt@bla.bla", "Musterstraße 66", "66976", "MusterOrt" );

        fillList(db.sortName(surnameAsc));
    }


    public void fillList(Cursor c) {

        c.moveToFirst();
        ListView listView = (ListView) findViewById(R.id.list_2sp);

        int itemLayout = R.layout.main_layout;

        Cursor cursor = c;
        String[] from = new String[]{"_id", MyDBManager.SPALTE_VORNAME, MyDBManager.SPALTE_NAME, MyDBManager.SPALTE_ORT, MyDBManager.SPALTE_PLZ};

        int[] to = new int[]{R.id.textid, R.id.text1, R.id.text2, R.id.text3, R.id.text4};
        adapter = new SimpleCursorAdapter(cxt, itemLayout, cursor, from, to, 0);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView textview = (TextView) view.findViewById(R.id.textid);
                String ID = textview.getText().toString();

                Log.e("TAG", "ID: " + Integer.parseInt(ID));
                int listID = Integer.parseInt(ID);

                Intent intent = new Intent(getApplicationContext(), Details.class);
                intent.putExtra("ID", listID);

                startActivity(intent);
            }
        });
    }


    //Menü einfügen
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    // Hinzufügen FAB Aktion
    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == add.getId()) {
                Intent intent = new Intent(getApplicationContext(), AddEdit.class);
                startActivity(intent);
            }
        }
    };

    //Sortierungs-Menü
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_Name:
                //nach Namen sortieren
                currentSorting = SURNAME;

                surnameAsc = !surnameAsc;

                Toast.makeText(getApplicationContext(),
                        R.string.toast_sort_name,
                        Toast.LENGTH_LONG).show();

                fillList(db.sortName(surnameAsc));
                return true;
            case R.id.sort_Vorname:
                currentSorting = NAME;

                nameAsc = !nameAsc;

                //nach Vornamen sortieren
                Toast.makeText(getApplicationContext(),
                        R.string.toast_sort_vorname,
                        Toast.LENGTH_LONG).show();

                fillList(db.sortVorname(nameAsc));
                return true;//
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (currentSorting == NAME)
            adapter.changeCursor(db.sortVorname(nameAsc));
        else if (currentSorting == SURNAME)
            adapter.changeCursor(db.sortName(surnameAsc));
        else
            adapter.changeCursor(db.selectAll());
        adapter.notifyDataSetChanged();
    }
}

package com.eae.kipper.jung.gabriel.mitgliederverwaltung;

import android.app.Activity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = (FloatingActionButton)findViewById(R.id.foating_add);
        add.setOnClickListener(handler);
        // db.insertMitglied("Mustermann", "Max", "0689511111", "017688888888", "kontakt@bla.bla", "Musterstraße 66", "66976", "MusterOrt", "aktiv");
        //Start-CursorAdapter (2 Zeilige Liste!)        !NOT YET FINISHED!
        /*final ListView listView = (ListView)findViewById(R.id.list_2sp);
        db = new MyDBManager(this);

        //int itemLayout = android.R.layout.simple_list_item_2;
        int itemLayout = R.layout.main_layout;
        Cursor cursor = db.selectAll();
        String[] from = new String[] {"_id",MyDBManager.SPALTE_VORNAME,MyDBManager.SPALTE_NAME , MyDBManager.SPALTE_ORT,MyDBManager.SPALTE_PLZ, };




        //int[] to = new int[] {android.R.id.text1, android.R.id.text2};
        int[] to = new int[] {R.id.textid,R.id.text1, R.id.text2,R.id.text3,R.id.text4};
        adapter = new SimpleCursorAdapter(cxt, itemLayout, cursor, from, to, 0);
        listView.setAdapter(adapter);*/
        fillList(db.selectAll());

        //End-CursorAdapter





    }
    public void fillList(Cursor c){
        final ListView listView = (ListView)findViewById(R.id.list_2sp);
        Cursor cursor = c;
        int itemLayout = R.layout.main_layout;
        String[] from = new String[] {"_id",MyDBManager.SPALTE_VORNAME,MyDBManager.SPALTE_NAME , MyDBManager.SPALTE_ORT,MyDBManager.SPALTE_PLZ, };




        //int[] to = new int[] {android.R.id.text1, android.R.id.text2};
        int[] to = new int[] {R.id.textid,R.id.text1, R.id.text2,R.id.text3,R.id.text4};
        adapter = new SimpleCursorAdapter(cxt, itemLayout, cursor, from, to, 0);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView textview =(TextView) view.findViewById(R.id.textid);
                String ID= textview.getText().toString();


                Log.e("TAG", "ID: " + Integer.parseInt(ID));
                int listID= Integer.parseInt(ID);



                Intent intent = new Intent(getApplicationContext(),Details.class);
                intent.putExtra("ID",listID);


                startActivity(intent);
                //putExtra(String name,int value)

            }
        });


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
                //nach Namen sortieren
                Toast.makeText(getApplicationContext(),
                        R.string.toast_sort_name,
                        Toast.LENGTH_LONG).show();
                    fillList(db.sortName());



                return true;
            case R.id.sort_Vorname:
                //nach Vornamen sortieren
                Toast.makeText(getApplicationContext(),
                        R.string.toast_sort_vorname,
                        Toast.LENGTH_LONG).show();
                    fillList(db.sortVorname());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        adapter.changeCursor(db.selectAll());
        adapter.notifyDataSetChanged();


    }
}

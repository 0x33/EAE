package com.eae.kipper.jung.gabriel.mitgliederverwaltung;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //TODO: Mithilfe des MenuInflaters Die Menu-Ressource (Optionsmenu) start_activity_menu zuweisen
        //MenuInflater(Context){
        //    void inflate (int start_activity_menu, Menu menu)
        //}

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.details_menu, menu);


        return true;
    }
}

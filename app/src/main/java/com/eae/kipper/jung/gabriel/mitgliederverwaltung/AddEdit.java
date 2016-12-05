package com.eae.kipper.jung.gabriel.mitgliederverwaltung;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AddEdit extends AppCompatActivity {

    FloatingActionButton save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        save = (FloatingActionButton)findViewById(R.id.foating_save);

        save.setOnClickListener(handler);
    }

    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == save.getId()){
                Intent intent = new Intent(getApplicationContext(), Details.class);
                startActivity(intent);
            }
        }
    };
}

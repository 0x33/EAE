package com.eae.kipper.jung.gabriel.mitgliederverwaltung;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddEdit extends AppCompatActivity {

    //TextView test;

    FloatingActionButton save;
    EditText nameInputText, vornameInputText, nummerPrivatInputText, nummerMobilInputText, emailInputText, strasseInputText, plzInputText, ortInputText;
    MyDBManager db = new MyDBManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        save = (FloatingActionButton)findViewById(R.id.foating_save);
        save.setOnClickListener(handler);
        //updateSaveFAB();

        nameInputText = (EditText)findViewById(R.id.Input_Text_Name);
        vornameInputText = (EditText)findViewById(R.id.Input_Text_Vorname);
        nummerPrivatInputText = (EditText)findViewById(R.id.Input_Text_Nummer_Privat);
        nummerMobilInputText = (EditText)findViewById(R.id.Input_Text_Nummer_Mobil);
        emailInputText = (EditText)findViewById(R.id.Input_Text_Email);
        strasseInputText = (EditText)findViewById(R.id.Input_Text_Strasse);
        plzInputText = (EditText)findViewById(R.id.Input_Text_Plz);
        ortInputText = (EditText)findViewById(R.id.Input_Text_Ort);

/*  UNNÖTIG? zumindest in der Form.. toDELETE
        nameInputText.toString();
        vornameInputText.toString();
        nummerPrivatInputText.toString();
        nummerMobilInputText.toString();
        emailInputText.toString();
        strasseInputText.toString();
        plzInputText.toString();
        ortInputText.toString();

        test = (TextView)findViewById(R.id.testString);
        */
    }

    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == save.getId()){
                /*
                Intent intent = new Intent(getApplicationContext(), Details.class);
                startActivity(intent);

                String name = nameInputText.getText().toString();
                String vname = vornameInputText.getText().toString();
                String nummerp = nummerPrivatInputText.getText().toString();

                test.setText(name + " " + vname + " " + nummerp);
                */
                //testEintrag!
              //  db.insertMitglied("Mustermann", "Max", "0689511111", "017688888888", "kontakt@bla.bla", "Musterstraße 66", "66976", "MusterOrt", "aktiv");
                Toast.makeText(getApplicationContext(), "Toast: Button pressed!",
                        Toast.LENGTH_LONG).show();


                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                //Eintrag per Felder in AddEdit
               db.insertMitglied(nameInputText.getText().toString(),
                        vornameInputText.getText().toString(),
                        nummerPrivatInputText.getText().toString(),
                        nummerMobilInputText.getText().toString(),
                        emailInputText.getText().toString(),
                        strasseInputText.getText().toString(),
                        plzInputText.getText().toString(),
                        ortInputText.getText().toString(),
                        "aktiv");

            }
        }
    };

    //FAB erscheint nur, wenn mitglied ein name und vorname zugeiesen wurde
    private void updateSaveFAB() {

        /*String inputName = nameInputText.toString();
        String inputVname = vornameInputText.toString();

        if (inputName.trim().length() != 0 && inputVname.trim().length() != 0)
            save.show();
        else
            save.hide();
            */

        save.show();
    }
}

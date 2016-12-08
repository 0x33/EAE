package com.eae.kipper.jung.gabriel.mitgliederverwaltung;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddEdit extends AppCompatActivity {

    TextView test;

    FloatingActionButton save;
    EditText nameInputText, vornameInputText, nummerPrivatInputText, nummerMobilInputText, emailInputText, strasseInputText, plzInputText, ortInputText;
    MyDBManager db = new MyDBManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        save = (FloatingActionButton)findViewById(R.id.foating_save);
        save.setOnClickListener(handler);

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
*/
        test = (TextView)findViewById(R.id.testString);


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
                db.insertMitglied("Mustermann", "Max", "0689511111", "017688888888", "kontakt@bla.bla", "Musterstraße 66", "66976", "MusterOrt", "aktiv");
                //Eintrag per Felder in AddEdit
        /*        db.insertMitglied(nameInputText.toString(),
                        vornameInputText.toString(),
                        nummerPrivatInputText.toString(),
                        nummerMobilInputText.toString(),
                        emailInputText.toString(),
                        strasseInputText.toString(),
                        plzInputText.toString(),
                        ortInputText.toString(),
                        "aktiv");
        */
            }
        }
    };

    private void updateSaveFAB() {
        String inputName = nameInputText.toString();
        String inputVname = vornameInputText.toString();

        // if there is a name for the contact, show the FloatingActionButton
        if (inputName.trim().length() != 0 && inputVname.trim().length() != 0)
            save.show();
        else
            save.hide();
    }
}

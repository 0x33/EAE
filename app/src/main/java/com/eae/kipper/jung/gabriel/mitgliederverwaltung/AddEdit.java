package com.eae.kipper.jung.gabriel.mitgliederverwaltung;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddEdit extends AppCompatActivity {

    FloatingActionButton save;
    EditText nameInputText, vornameInputText, nummerPrivatInputText, nummerMobilInputText, emailInputText, strasseInputText, plzInputText, ortInputText;
    MyDBManager db = new MyDBManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        final boolean[] textvalid = new boolean[2];

        save = (FloatingActionButton)findViewById(R.id.foating_save);
        save.setOnClickListener(handler);
        save.hide();

        nameInputText = (EditText)findViewById(R.id.Input_Text_Name);
        vornameInputText = (EditText)findViewById(R.id.Input_Text_Vorname);
        nummerPrivatInputText = (EditText)findViewById(R.id.Input_Text_Nummer_Privat);
        nummerMobilInputText = (EditText)findViewById(R.id.Input_Text_Nummer_Mobil);
        emailInputText = (EditText)findViewById(R.id.Input_Text_Email);
        strasseInputText = (EditText)findViewById(R.id.Input_Text_Strasse);
        plzInputText = (EditText)findViewById(R.id.Input_Text_Plz);
        ortInputText = (EditText)findViewById(R.id.Input_Text_Ort);

        nameInputText.addTextChangedListener(new TextChangeManager(textvalid,0));
        vornameInputText.addTextChangedListener(new TextChangeManager(textvalid,1));
    }

    public class TextChangeManager implements TextWatcher{
        boolean[] valid;
        int index;

    public TextChangeManager(final boolean[] valid,int index){
        this.valid=valid;
        this.index = index;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    //Speichern FAB erscheint nur, wenn Name & Vorname nicht leer sind
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(s.length()>=1){
            valid[index]=true;

        }else{
            valid[index]=false;
        }

        boolean visible = true;
        for(int i = 0;i<valid.length;i++){
            visible=visible&&valid[i];
        }
        if(visible){
            save.show();
        }else{
            save.hide();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}
    //Speichern FAB Aktion
    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == save.getId()){
                Toast.makeText(getApplicationContext(), R.string.mitglied_hinzugefuegt,
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
                        ortInputText.getText().toString());
            }
        }
    };
}

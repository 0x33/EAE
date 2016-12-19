package com.eae.kipper.jung.gabriel.mitgliederverwaltung;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.eae.kipper.jung.gabriel.mitgliederverwaltung.MyDBManager.DATENBANK_NAMEN;

public class Details extends AppCompatActivity {



    EditText nameText, vornameText, nummerpText, nummermText, emailText, strasseText, plzText, ortText;
    MyDBManager db;
    Cursor cursor;
    int receivID;

    FloatingActionButton save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //TODO FloatingActionButton
        save = (FloatingActionButton)findViewById(R.id.foating_save);
        save.setOnClickListener(handler);
        save.hide();


        nameText = (EditText) findViewById(R.id.Text_Name);
        vornameText = (EditText) findViewById(R.id.Text_Vorname);
        nummerpText = (EditText) findViewById(R.id.Text_Nummer_Privat);
        nummermText = (EditText) findViewById(R.id.Text_Nummer_Mobil);
        emailText = (EditText) findViewById(R.id.Text_Email);
        strasseText = (EditText) findViewById(R.id.Text_Strasse);
        plzText = (EditText) findViewById(R.id.Text_Plz);
        ortText = (EditText) findViewById(R.id.Text_Ort);

        setEditable();




        Intent receiver = getIntent();
        receivID = receiver.getIntExtra("ID", 1);


        db = new MyDBManager(this);
        cursor = db.selectData(receivID);


        cursor.moveToFirst();
        showValues();

    }
    public void setEditable(){

        nameText.setInputType(InputType.TYPE_NULL);
        vornameText.setInputType(InputType.TYPE_NULL);
        nummerpText.setInputType(InputType.TYPE_NULL);
        nummermText.setInputType(InputType.TYPE_NULL);
        emailText.setInputType(InputType.TYPE_NULL);
        strasseText.setInputType(InputType.TYPE_NULL);
        plzText.setInputType(InputType.TYPE_NULL);
        ortText.setInputType(InputType.TYPE_NULL);

    }




    public void showValues() {

        Log.e("Anzahl",": "+cursor.getColumnCount());
        Log.e("Count",": "+cursor.getColumnCount());
        Log.e("CName",": "+cursor.getColumnName(0));


        String id = cursor.getString(0).toString();
        String name= cursor.getString(1);
        String vorname = cursor.getString(2);
        String pnr = cursor.getString(3);
        String mnr = cursor.getString(4);
        String email = cursor.getString(5);
        String strasse = cursor.getString(6);
        String plz = cursor.getString(7);
        String ort = cursor.getString(8);



        nameText.setText(name);
        vornameText.setText(vorname);
        nummerpText.setText(pnr);
        nummermText.setText(mnr);
        emailText.setText(email);
        strasseText.setText(strasse);
        plzText.setText(plz);
        ortText.setText(ort);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.details_menu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
              //  Intent intent = new Intent(getApplicationContext(), AddEdit.class);
              //  startActivity(intent);
                editContact();

                return true;
            case R.id.action_delete:
                deleteContact();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void moveNext() {
        if (!cursor.isLast())
            cursor.moveToNext();

        showValues();

    }

    public void movePrev() {
        if (!cursor.isFirst())
            cursor.moveToPrevious();

        showValues();

    }
    private void editContact(){
        nameText.setInputType(InputType.TYPE_CLASS_TEXT);
        vornameText.setInputType(InputType.TYPE_CLASS_TEXT);
        nummerpText.setInputType(InputType.TYPE_CLASS_PHONE);
        nummermText.setInputType(InputType.TYPE_CLASS_PHONE);
        emailText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        strasseText.setInputType(InputType.TYPE_CLASS_TEXT);
        plzText.setInputType(InputType.TYPE_CLASS_NUMBER);
        ortText.setInputType(InputType.TYPE_CLASS_TEXT);
        nameText.setSelection(nameText.getText().length());
        openKeyboard();

        save.show();
        //TODO AddHandler






    }

    private void deleteContact() {

        //Kontakt löschen

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(R.string.confirm_title);
        alertDialog.setMessage(R.string.confirm_message);
        TextView text = new TextView(this);
        text.setPadding(10, 10, 10, 10);
        text.setGravity(Gravity.CENTER);
        text.setTextSize(20);
        alertDialog.setPositiveButton(R.string.button_loschen,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        db.deleteRow(receivID);
                      cursor=  db.selectAll();


                        Toast.makeText(getApplicationContext(),
                                R.string.toast_loschen,
                                Toast.LENGTH_LONG).show();

                        Details.this.finish();


                    }
                });

        alertDialog.setNegativeButton(R.string.button_abbrechen,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getApplicationContext(),
                                R.string.toast_abbrechen,
                                Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });

        alertDialog.create();
        alertDialog.show();
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
                Toast.makeText(getApplicationContext(), "@string/mitglied_geändert",
                        Toast.LENGTH_LONG).show();

                db.Update(receivID,nameText,vornameText,nummerpText,nummermText,emailText,strasseText,plzText,ortText);
                cursor=  db.selectAll();

                save.hide();
                closeKeyboard();
                setEditable();

                //Eintrag per Felder in AddEdit

            }
        }
    };
    public void openKeyboard(){
        InputMethodManager keyboard = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        keyboard.showSoftInput(nameText, 0);
    }
    public void closeKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}

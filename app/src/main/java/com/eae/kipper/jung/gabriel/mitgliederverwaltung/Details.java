package com.eae.kipper.jung.gabriel.mitgliederverwaltung;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        nameText = (EditText) findViewById(R.id.Text_Name);
        vornameText = (EditText) findViewById(R.id.Text_Vorname);
        nummerpText = (EditText) findViewById(R.id.Text_Nummer_Privat);
        nummermText = (EditText) findViewById(R.id.Text_Nummer_Mobil);
        emailText = (EditText) findViewById(R.id.Text_Email);
        strasseText = (EditText) findViewById(R.id.Text_Strasse);
        plzText = (EditText) findViewById(R.id.Text_Plz);
        ortText = (EditText) findViewById(R.id.Text_Ort);





        Intent receiver = getIntent();
        receivID = receiver.getIntExtra("ID", 1);


        db = new MyDBManager(this);
        cursor = db.selectData(receivID);


        cursor.moveToFirst();
        showValues();

    }


    public void showValues() {




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
}

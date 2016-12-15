package com.eae.kipper.jung.gabriel.mitgliederverwaltung;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Details extends AppCompatActivity {

    TextView nameText, vornameText, nummerpText, nummermText, emailText, strasseText, plzText, ortText;
    MyDBManager db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        nameText = (TextView) findViewById(R.id.Text_Name);
        vornameText = (TextView) findViewById(R.id.Text_Vorname);
        nummerpText = (TextView) findViewById(R.id.Text_Nummer_Privat);
        nummermText = (TextView) findViewById(R.id.Text_Nummer_Mobil);
        emailText = (TextView) findViewById(R.id.Text_Email);
        strasseText = (TextView) findViewById(R.id.Text_Strasse);
        plzText = (TextView) findViewById(R.id.Text_Plz);
        ortText = (TextView) findViewById(R.id.Text_Ort);

        Intent receiver = getIntent();
        int receivID = receiver.getIntExtra("ID", 1);


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
                Intent intent = new Intent(getApplicationContext(), AddEdit.class);
                startActivity(intent);
                return true;
            case R.id.action_delete:
                deleteContact();
                return true;
        }
        return super.onOptionsItemSelected(item);
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
                        Toast.makeText(getApplicationContext(),
                                R.string.toast_loschen,
                                Toast.LENGTH_LONG).show();
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

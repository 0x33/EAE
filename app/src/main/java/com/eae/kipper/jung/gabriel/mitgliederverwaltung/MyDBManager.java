package com.eae.kipper.jung.gabriel.mitgliederverwaltung;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBManager extends SQLiteOpenHelper {

    public static final int DATENBANK_VERSION = 1;
    public static final String DATENBANK_NAMEN = "MVW.db";

    public static final String TABELLE_MITGLIED = "mitglied";


    public static final String SPALTE_MITGLIED_NR = "mitglied_nr";
    public static final String SPALTE_NAME = "name";
    public static final String SPALTE_VORNAME = "vorname";
    public static final String SPALTE_PRIVATNR = "privatnr";
    public static final String SPALTE_MOBILNR = "mobilnr";
    public static final String SPALTE_EMAIL = "email";
    public static final String SPALTE_STRASSE ="strasse";
    public static final String SPALTE_PLZ = "plz";
    public static final String SPALTE_ORT = "ort";
    public static final String SPALTE_STATUS = "status";

    public MyDBManager(Context cxt){
        super(cxt, DATENBANK_NAMEN, null, DATENBANK_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //alle Tabellen erzeugen
        //Tabellen mit Grunddaten füllen
        db.execSQL(
                "CREATE TABLE " + TABELLE_MITGLIED + " (" +
                        SPALTE_MITGLIED_NR + " INTEGER PRIMARY KEY AUTOINCREMENT," +

                        SPALTE_NAME + " TEXT," +
                        SPALTE_VORNAME + " TEXT," +
                        SPALTE_PRIVATNR + " TEXT," +
                        SPALTE_MOBILNR + " TEXT," +
                        SPALTE_EMAIL + " TEXT," +
                        SPALTE_STRASSE + " TEXT," +
                        SPALTE_PLZ + " TEXT," +
                        SPALTE_ORT + " TEXT," +
                        SPALTE_STATUS + " TEXT" +
                        ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
    }

    public Cursor selectData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        SQLiteCursor meinZeiger = (SQLiteCursor) db.rawQuery("SELECT * FROM "+
                TABELLE_MITGLIED +
                " WHERE "
                +SPALTE_MITGLIED_NR +
                "="+id,null);

                return meinZeiger;
    }



    public Cursor selectAll(){
        SQLiteDatabase db = this.getWritableDatabase();

        SQLiteCursor meinZeiger = (SQLiteCursor) db.rawQuery("SELECT " +
                SPALTE_MITGLIED_NR + " AS _id, " +
               // "" + SPALTE_MITGLIED_NR+","+
                "" + SPALTE_VORNAME + ", " +
                "" + SPALTE_NAME + ", " +
                "" + SPALTE_ORT+ ", " +
                "" + SPALTE_PLZ +
                " FROM " + TABELLE_MITGLIED, null);
        meinZeiger.moveToFirst();
        return meinZeiger;
    }

    public void insertMitglied(String name, String vorname, String privatnr, String mobilnr, String email, String strasse, String plz, String ort, String status){
        ContentValues neueZeile = new ContentValues();
        neueZeile.put(SPALTE_NAME, name);
        neueZeile.put(SPALTE_VORNAME, vorname);
        neueZeile.put(SPALTE_PRIVATNR, privatnr);
        neueZeile.put(SPALTE_MOBILNR, mobilnr);
        neueZeile.put(SPALTE_EMAIL, email);
        neueZeile.put(SPALTE_STRASSE, strasse);
        neueZeile.put(SPALTE_PLZ, plz);
        neueZeile.put(SPALTE_ORT, ort);
        neueZeile.put(SPALTE_STATUS, status);

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABELLE_MITGLIED,null,neueZeile);
    }
}

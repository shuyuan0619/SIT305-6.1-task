package com.example.trucksharing;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    public static final String CREATE_PERSON = "create table Person ("
            +"u_id text primary key,"
            +"fullName text,"
            +"username text,"
            +"password text,"
            +"head text,"
            +"phone text)";
    public static final String CREATE_ORDER_LIST = "create table OrderList ("
            +"u_id text primary key,"
            +"sender text,"
            +"receiver text,"
            +"pickUpDate text,"
            +"pickUpTime text,"
            +"pickUpLocation text,"
            +"goodType text,"
            +"Weight text,"
            +"width text,"
            +"length text,"
            +"height text,"
            +"vehicleType text,"
            +"isPublic text)";



    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PERSON);
        db.execSQL(CREATE_ORDER_LIST);
        Toast.makeText(context,"create succeed",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Person");
        db.execSQL("drop table if exists OrderList");
        onCreate(db);
    }
}

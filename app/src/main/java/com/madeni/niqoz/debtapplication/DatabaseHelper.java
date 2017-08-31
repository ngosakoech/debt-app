package com.madeni.niqoz.debtapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by niqoz on 8/12/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "debt_manager";
    public static final String TABLE_NAME_CRED = "creditors";
    public static final String TABLE_NAME_DEBT = "debtors";
    public static final String col_1 = "id";
    public static final String col_name = "name";
    public static final String col_location = "location";
    public static final String col_number = "phone_number";
    public static final String col_Lamount = "loan_amount";
    public static final String col_Pamount = "repaid_amount";
    public static final String col_Ramount = "remain_amount";
    
    SQLiteDatabase db;

    public static final String query_cred = "create table " +TABLE_NAME_CRED+ "(id integer PRIMARY KEY not null, name text not null, " +
            "phone_number text not null, location text, loan_amount integer not null, repaid_amount integer, remain_amount integer )";
    public static final String query_debt = "create table " +TABLE_NAME_DEBT+ "(id integer primary key not null, name text not null, " +
            "phone_number text not null, location text, loan_amount integer not null, repaid_amount integer, remain_amount integer )";

    public DatabaseHelper(Context context){super(
        context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        db.execSQL(query_cred);
        db.execSQL(query_debt);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop if exists "+TABLE_NAME_CRED);
        db.execSQL("drop if exists "+TABLE_NAME_DEBT);
        onCreate(db);
    }

    public boolean InsertDataCred(String name, String phone_number, String loan_amount, String location){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(col_name,name);
        contentValues.put(col_location,location);
        contentValues.put(col_Lamount,loan_amount);
       // contentValues.put(col_Pamount,paid_amount);
       // contentValues.put(col_Ramount,remain_amount);
        contentValues.put(col_number,phone_number);
        long result = db.insert(TABLE_NAME_CRED,null,contentValues);

        return result != -1;
    }

    public boolean InsertDataDebt(String name, String phone_number, String loan_amount, String location){
      SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(col_name,name);
        contentValues.put(col_location,location);
        contentValues.put(col_Lamount,loan_amount);
        //contentValues.put(col_Pamount,paid_amount);
        //contentValues.put(col_Ramount,remain_amount);
        contentValues.put(col_number,phone_number);
        long result = db.insert(TABLE_NAME_DEBT,null,contentValues);

        return result != -1;
    }


}

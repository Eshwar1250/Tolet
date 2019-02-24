package com.abc.tolet.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.abc.tolet.House;
import com.abc.tolet.model.Banquet_Post;
import com.abc.tolet.model.House_Post;

import java.util.ArrayList;

public class HouseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "HouseManager.db";
    private static final String TABLE_HOUSE = "post";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_TYPE = "House_type";
    private static final String COLUMN_BHK = "bhk";
    private static final String COLUMN_LOCATION = "location";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_WATER_FACIL = "Water_facil";
    private static final String COLUMN_ELECTRICITY_FACIL = "Electricity_facil";
    private static final String COLUMN_PARKING_FACIL = "Parking_facil";
    private static final String COLUMN_SECURITY_FACIL = "Security_facil";
    private static final String COLUMN_RENT="Rent";
    private static final String COLUMN_IMG="Image";
    private static final String COLUMN_PHN="Phno";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_HOUSE;
    public HouseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_POST_TABLE = "CREATE TABLE " + TABLE_HOUSE + "(" + COLUMN_NAME + " TEXT, " + COLUMN_TYPE + " TEXT, " +
                COLUMN_BHK + " TEXT," + COLUMN_LOCATION + " TEXT," + COLUMN_ADDRESS + " TEXT," + COLUMN_WATER_FACIL + " TEXT, " +
                COLUMN_ELECTRICITY_FACIL + " TEXT," + COLUMN_PARKING_FACIL + " TEXT," + COLUMN_SECURITY_FACIL + " TEXT, " +
                COLUMN_RENT + " INTEGER," + COLUMN_PHN + " LONG PRIMARY KEY, " + COLUMN_IMG + " BLOB " + ")";
        db.execSQL(CREATE_POST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);

        // Create tables again
        onCreate(db);
    }

    public Boolean addPost(House_Post hpost) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, hpost.getName());
        values.put(COLUMN_TYPE, hpost.getType());
        values.put(COLUMN_BHK, hpost.getBhk());
        values.put(COLUMN_LOCATION, hpost.getLocation());
        values.put(COLUMN_ADDRESS, hpost.getAddress());
        values.put(COLUMN_WATER_FACIL, hpost.getWater());
        values.put(COLUMN_ELECTRICITY_FACIL, hpost.getElectricity());
        values.put(COLUMN_PARKING_FACIL, hpost.getParking());
        values.put(COLUMN_SECURITY_FACIL, hpost.getSecurity());
        values.put(COLUMN_RENT, hpost.getRent());
        values.put(COLUMN_PHN, hpost.getPhn());
        values.put(COLUMN_IMG,hpost.getImg());


        // Inserting Row
        db.insert(TABLE_HOUSE, null, values);
        db.close();
        return true;
    }

    public int getCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        int profile_counts = (int) DatabaseUtils.queryNumEntries(db, TABLE_HOUSE);
        db.close();
        return profile_counts;
    }

    public ArrayList<House_Post> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_NAME,
                COLUMN_LOCATION,
                COLUMN_ADDRESS,
                COLUMN_WATER_FACIL,
                COLUMN_ELECTRICITY_FACIL,
                COLUMN_PARKING_FACIL,
                COLUMN_SECURITY_FACIL,
                COLUMN_RENT,
                COLUMN_PHN,
                COLUMN_IMG
        };
        // sorting orders
        String sortOrder = COLUMN_NAME + " ASC";
        ArrayList<House_Post> hlist = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /*
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_HOUSE, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                House_Post hpost = new House_Post();
                hpost.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
               // hpost.setAc(cursor.getString(cursor.getColumnIndex(COLUMN_AC)));
                hpost.setLocation(cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION)));
                hpost.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
                //hpost.setCapacity(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CAPACITY))));
                hpost.setWater(cursor.getString(cursor.getColumnIndex(COLUMN_WATER_FACIL)));
                hpost.setElectricity(cursor.getString(cursor.getColumnIndex(COLUMN_ELECTRICITY_FACIL)));
                hpost.setParking(cursor.getString(cursor.getColumnIndex(COLUMN_PARKING_FACIL)));
                hpost.setSecurity(cursor.getString(cursor.getColumnIndex(COLUMN_SECURITY_FACIL)));
               // hpost.setDecoration(cursor.getString(cursor.getColumnIndex(COLUMN_DECOR_FACIL)));
                hpost.setRent(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_RENT))));
                //hpost.setPhn(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PHN))));
                hpost.setPhn(Long.parseLong(cursor.getString(cursor.getColumnIndex(COLUMN_PHN))));
                hpost.setImage(cursor.getBlob(cursor.getColumnIndex(COLUMN_IMG)));
                // Adding user record to list
                hlist.add(hpost);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return hlist;
    }
}

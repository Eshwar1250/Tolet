package com.abc.tolet.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.abc.tolet.model.Banquet_Post;
import com.abc.tolet.model.House_Post;
import com.abc.tolet.model.User;

import java.util.ArrayList;
import java.util.List;

public class BanquetHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "Banquet.db";
    private static final String TABLE_BANQUET = "bank";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AC = "ac";
    private static final String COLUMN_LOCATION = "location";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_CAPACITY = "capacity";
    private static final String COLUMN_WATER_FACIL = "Water_facil";
    private static final String COLUMN_ELECTRICITY_FACIL = "Electricity_facil";
    private static final String COLUMN_PARKING_FACIL = "Parking_facil";
    private static final String COLUMN_SECURITY_FACIL = "Security_facil";
    private static final String COLUMN_DECOR_FACIL = "Decor_facil";
    private static final String COLUMN_RENT="Rent";
    private static final String COLUMN_PHN="Phno";
    private static final String COLUMN_IMG="Image";
   // private Banquet_Post bpost = new Banquet_Post();
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_BANQUET;
    public BanquetHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_POST_TABLE = "CREATE TABLE " + TABLE_BANQUET + "(" + COLUMN_NAME + " TEXT, " + COLUMN_AC + " TEXT, "
                + " TEXT," + COLUMN_LOCATION + " TEXT," + COLUMN_ADDRESS + " TEXT," + COLUMN_CAPACITY + " TEXT, " + COLUMN_WATER_FACIL + " TEXT, " +
                COLUMN_ELECTRICITY_FACIL + " TEXT," + COLUMN_PARKING_FACIL + " TEXT," + COLUMN_SECURITY_FACIL + " TEXT, " + COLUMN_DECOR_FACIL + " TEXT, " +
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

    public Boolean addPost(Banquet_Post bpost) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(DROP_USER_TABLE);

        // Create tables again
        onCreate(db);
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, bpost.getName());
        values.put(COLUMN_AC, bpost.getAc());
        values.put(COLUMN_LOCATION, bpost.getLocation());
        values.put(COLUMN_ADDRESS, bpost.getAddress());
        values.put(COLUMN_CAPACITY, bpost.getCapacity());
        values.put(COLUMN_WATER_FACIL, bpost.getWater());
        values.put(COLUMN_ELECTRICITY_FACIL, bpost.getElectricity());
        values.put(COLUMN_PARKING_FACIL, bpost.getParking());
        values.put(COLUMN_SECURITY_FACIL, bpost.getSecurity());
        values.put(COLUMN_DECOR_FACIL, bpost.getDecoration());
        values.put(COLUMN_RENT, bpost.getRent());
        values.put(COLUMN_PHN, bpost.getPhn());
        values.put(COLUMN_IMG,bpost.getImg());


        // Inserting Row
        db.insert(TABLE_BANQUET, null, values);
        db.close();
        return true;
    }

    public int getProfilesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_BANQUET;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public int getCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        int profile_counts = (int) DatabaseUtils.queryNumEntries(db, TABLE_BANQUET);
        db.close();
        return profile_counts;
    }

    public ArrayList<Banquet_Post> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_NAME,
                COLUMN_AC,
                COLUMN_LOCATION,
                COLUMN_ADDRESS,
                COLUMN_CAPACITY,
                COLUMN_WATER_FACIL,
                COLUMN_ELECTRICITY_FACIL,
                COLUMN_PARKING_FACIL,
                COLUMN_SECURITY_FACIL,
                COLUMN_DECOR_FACIL,
                COLUMN_RENT,
                COLUMN_PHN,
                COLUMN_IMG
        };
        // sorting orders
        String sortOrder = COLUMN_NAME + " ASC";
        ArrayList<Banquet_Post> blist = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_BANQUET, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Banquet_Post bpost = new Banquet_Post();
                bpost.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                bpost.setAc(cursor.getString(cursor.getColumnIndex(COLUMN_AC)));
                bpost.setLocation(cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION)));
                bpost.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
                bpost.setCapacity(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CAPACITY))));
                bpost.setWater(cursor.getString(cursor.getColumnIndex(COLUMN_WATER_FACIL)));
                bpost.setElectricity(cursor.getString(cursor.getColumnIndex(COLUMN_ELECTRICITY_FACIL)));
                bpost.setParking(cursor.getString(cursor.getColumnIndex(COLUMN_PARKING_FACIL)));
                bpost.setSecurity(cursor.getString(cursor.getColumnIndex(COLUMN_SECURITY_FACIL)));
                bpost.setDecoration(cursor.getString(cursor.getColumnIndex(COLUMN_DECOR_FACIL)));
                bpost.setRent(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_RENT))));
                //bpost.setPhn(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PHN))));
                bpost.setPhn(Long.parseLong(cursor.getString(cursor.getColumnIndex(COLUMN_PHN))));
                bpost.setImage(cursor.getBlob(cursor.getColumnIndex(COLUMN_IMG)));
                // Adding user record to list
                blist.add(bpost);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return blist;
    }


}

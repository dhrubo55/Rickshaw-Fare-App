package com.example.dhruboandroid.bashundhara;



/**
 * Created by Dhrubo Android on 7/21/2018.
 */

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * {@link SQLiteOpenHelper} implementation that creates and upgrades the
 * application database.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "bashundharaRouteFare.db";
    private static final int DB_VERSION = 1;
    private static final String TAG = "DatabaseHelper";

    private Context mContext;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Database creation scripts should be placed in assets/sql
        String createDbFile = "bashundharaRouteFare.db"+ ".sql";
        Log.v(TAG, "Creating new database from " + createDbFile + ".");

        AssetManager am = mContext.getAssets();
        StringBuilder createStatement = new StringBuilder();
        long startTime = System.currentTimeMillis();
        db.beginTransaction();
        try {
            InputStream is = am.open(createDbFile);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            /* Cache the file line by line, when the line ends with a
             * semi-colon followed by a line break (end of a SQL command),
             * execute it against the database and move on. */
            while ((line = br.readLine()) != null) {
                String lineTrimmed = line.trim();
                if (lineTrimmed.length() == 0)
                    continue;
                createStatement.append(line).append("\r\n");
                if (lineTrimmed.endsWith(";")) {
                    Log.d(TAG, "Executing SQL: \r\n" + createStatement.toString());
                    db.execSQL(createStatement.toString());
                    createStatement.setLength(0);
                }
            }
            br.close();
        } catch (IOException e) {
            Log.e(TAG, "IOException thrown while attempting to "
                    + "create database from " + createDbFile + ".");
            return;
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        Log.i(TAG, "New database created from script "
                + createDbFile + " in " +
                (System.currentTimeMillis() - startTime) +"ms.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int from, int to) {
        // Nothing to do yet!
    }




    public String getNotesCount(String from, String to) {
        String countQuery = "select fare from Fare where source='"+from+"'"+" "+"and destination='"+to+"'"+";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        String fare= "";
        try{
            if (cursor != null)
                cursor.moveToFirst();
            fare = cursor.getString(cursor.getColumnIndex("fare"));
        }catch (Exception e){
            Toast.makeText(mContext,"No data found",Toast.LENGTH_LONG).show();

        }finally {
            cursor.close();
            return fare;

        }
    }


    public double getPlaceLat(String place_name){

        String querey1 = "select lat from Place where place='"+place_name+"';";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(querey1, null);
        String lat="";
        try{
            if (cursor != null)
                cursor.moveToFirst();
            lat = cursor.getString(cursor.getColumnIndex("lat"));
        }catch (Exception e){
            Toast.makeText(mContext,"No data found",Toast.LENGTH_LONG).show();

        }finally {
            cursor.close();
            return Double.parseDouble(lat);

        }



    }



    public double getPlaceLng(String place_name) {
        String querey2 = "select long from Place where place='"+place_name+"';";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor1 = db.rawQuery(querey2, null);
        String lng = "";
        try{
            if (cursor1 != null)
                cursor1.moveToFirst();
            lng = cursor1.getString(cursor1.getColumnIndex("long"));

        }catch (Exception e){
            Toast.makeText(mContext,"No data found",Toast.LENGTH_LONG).show();
        }finally {
            cursor1.close();
            return Double.parseDouble(lng);

        }


    }
}


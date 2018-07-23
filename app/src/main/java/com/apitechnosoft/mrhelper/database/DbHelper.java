package com.apitechnosoft.mrhelper.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.apitechnosoft.mrhelper.models.Bookservicelist;
import com.apitechnosoft.mrhelper.models.User;
import com.apitechnosoft.mrhelper.utilities.Contants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Neeraj on 7/25/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = Contants.DATABASE_NAME;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS userData");
        db.execSQL("DROP TABLE IF EXISTS MyBookingData");

        onCreate(db);

    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);

    }

    public void onCreate(SQLiteDatabase db) {
        String CREATE_user_TABLE = "CREATE TABLE userData(PhoneNumber TEXT,emailid TEXT,houseno TEXT,loc TEXT,landmark TEXT,email TEXT,name TEXT,password TEXT)";
        db.execSQL(CREATE_user_TABLE);


        String CREATE_MyBooking_TABLE = "CREATE TABLE MyBookingData(servicesno TEXT,totalservice TEXT,name TEXT,mobile TEXT,serviceamount TEXT,aftertaxamount TEXT,email TEXT,timepicker TEXT,txtdate1 TEXT,houseno TEXT,loc TEXT,jobId INTEGER,landmark TEXT,sno INTEGER,entrydate TEXT,serviceName TEXT,providerResponse TEXT, bookingStatus TEXT)";
        db.execSQL(CREATE_MyBooking_TABLE);
    }


    public boolean upsertUserData(User user) {
        boolean done = false;
        User data = null;
        if (!user.getMobileno().equals("")) {
            data = getUserData(user.getMobileno());
            if (data == null) {
                done = insertUserData(user);
            } else {
                done = updateUserData(user);
            }
        }
        return done;
    }

    private void populateUserData(Cursor cursor, User ob) {
        ob.setMobileno(cursor.getString(0));
        ob.setEmailid(cursor.getString(1));
        ob.setHouseno(cursor.getString(2));
        ob.setLoc(cursor.getString(3));
        ob.setLandmark(cursor.getString(4));
        ob.setEmail(cursor.getString(5));
        ob.setName(cursor.getString(6));
        ob.setPassword(cursor.getString(7));
    }


    public List<User> GetAllUserData() {
        ArrayList<User> list = new ArrayList<User>();
        String query = "Select * FROM userData";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {

            while (cursor.isAfterLast() == false) {
                User ob = new User();
                populateUserData(cursor, ob);
                list.add(ob);
                cursor.moveToNext();
            }
        }
        db.close();
        return list;
    }

    //insert Store data
    public boolean insertUserData(User ob) {
        ContentValues values = new ContentValues();
        populateUserValue(ob, values);
        SQLiteDatabase db = this.getWritableDatabase();

        long i = db.insert("userData", null, values);
        db.close();
        return i > 0;
    }

    public User getUserData(String pno) {
        String query = "Select * FROM userData WHERE PhoneNumber= '" + pno + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User data = new User();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            populateUserData(cursor, data);
            cursor.close();
        } else {
            data = null;
        }
        db.close();
        return data;
    }

    private void populateUserValue(User ob, ContentValues values) {
        values.put("PhoneNumber", ob.getMobileno());
        values.put("emailid", ob.getEmailid());
        values.put("houseno", ob.getHouseno());
        values.put("loc", ob.getLoc());
        values.put("landmark", ob.getLandmark());
        values.put("email", ob.getEmail());
        values.put("name", ob.getName());
        values.put("password", ob.getPassword());
    }

    //update Store data
    public boolean updateUserData(User ob) {
        ContentValues values = new ContentValues();
        populateUserValue(ob, values);

        SQLiteDatabase db = this.getWritableDatabase();
        long i = 0;
        i = db.update("userData", values, "PhoneNumber= '" + ob.getMobileno() + "'", null);

        db.close();
        return i > 0;
    }

    //update Store favority value data
    public boolean updateStoreFavorityValueData(int storeId, String favValue) {
        ContentValues values = new ContentValues();
        values.put("FavouriteStore", favValue);
        SQLiteDatabase db = this.getWritableDatabase();
        long i = 0;
        i = db.update("userData", values, "StoreId = " + storeId + " ", null);
        db.close();
        return i > 0;
    }

    //Store data by id
    public List<User> getAllUserData() {

        String query = "Select * FROM userData";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        List<User> list = new ArrayList<User>();

        if (cursor.moveToFirst()) {
            while (cursor.isAfterLast() == false) {
                User ob = new User();
                populateUserData(cursor, ob);
                list.add(ob);
                cursor.moveToNext();
            }
        }
        db.close();
        return list;
    }


    public boolean deleteUserData() {
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("userData", null, null);
        db.close();
        return result;
    }


    public boolean upsertMyBookingData(Bookservicelist ob) {
        boolean done = false;
        Bookservicelist data = null;
        if (ob.getSno() != 0) {
            data = getMyBookingData(ob.getSno());
            if (data == null) {
                done = insertMyBookingData(ob);
            } else {
                done = updateMyBookingData(ob);
            }
        }
        return done;
    }

    private void populateMyBookingData(Cursor cursor, Bookservicelist ob) {
        ob.setServicesno(cursor.getString(0));
        ob.setTotalservice(cursor.getString(1));
        ob.setName(cursor.getString(2));
        ob.setMobile(cursor.getString(3));
        ob.setServiceamount(cursor.getString(4));
        ob.setAftertaxamount(cursor.getString(5));
        ob.setEmail(cursor.getString(6));
        ob.setTimepicker(cursor.getString(7));
        ob.setTxtdate1(cursor.getString(8));
        ob.setHouseno(cursor.getString(9));
        ob.setLoc(cursor.getString(10));
        ob.setJobId(cursor.getInt(11));
        ob.setLandmark(cursor.getString(12));
        ob.setSno(cursor.getInt(13));
        ob.setEntrydate(cursor.getString(14));
        ob.setServiceName(cursor.getString(15));
        ob.setProviderResponse(cursor.getString(16));
        ob.setBookingStatus(cursor.getString(17));
    }


    public List<Bookservicelist> GetAllMyBookingData() {
        ArrayList<Bookservicelist> list = new ArrayList<Bookservicelist>();
        String query = "Select * FROM MyBookingData";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {

            while (cursor.isAfterLast() == false) {
                Bookservicelist ob = new Bookservicelist();
                populateMyBookingData(cursor, ob);
                list.add(ob);
                cursor.moveToNext();
            }
        }
        db.close();
        return list;
    }

    //insert MyBooking data
    public boolean insertMyBookingData(Bookservicelist ob) {
        ContentValues values = new ContentValues();
        populateMyBookingValue(ob, values);
        SQLiteDatabase db = this.getWritableDatabase();

        long i = db.insert("MyBookingData", null, values);
        db.close();
        return i > 0;
    }

    public Bookservicelist getMyBookingData(int id) {
        String query = "Select * FROM MyBookingData WHERE sno= " + id + "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Bookservicelist data = new Bookservicelist();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            populateMyBookingData(cursor, data);
            cursor.close();
        } else {
            data = null;
        }
        db.close();
        return data;
    }

    private void populateMyBookingValue(Bookservicelist ob, ContentValues values) {
        values.put("servicesno", ob.getServicesno());
        values.put("totalservice", ob.getTotalservice());
        values.put("name", ob.getName());
        values.put("mobile", ob.getMobile());
        values.put("serviceamount", ob.getServiceamount());
        values.put("aftertaxamount", ob.getAftertaxamount());
        values.put("email", ob.getEmail());
        values.put("timepicker", ob.getTimepicker());
        values.put("txtdate1", ob.getTxtdate1());
        values.put("houseno", ob.getHouseno());
        values.put("loc", ob.getLoc());
        values.put("jobId", ob.getJobId());
        values.put("landmark", ob.getLandmark());
        values.put("sno", ob.getSno());
        values.put("entrydate", ob.getEntrydate());
        values.put("serviceName", ob.getServiceName());
        values.put("providerResponse", ob.getProviderResponse());
        values.put("bookingStatus", ob.getBookingStatus());
    }

    //update MyBooking data
    public boolean updateMyBookingData(Bookservicelist ob) {
        ContentValues values = new ContentValues();
        populateMyBookingValue(ob, values);

        SQLiteDatabase db = this.getWritableDatabase();
        long i = 0;
        i = db.update("MyBookingData", values, "sno= " + ob.getSno() + "", null);

        db.close();
        return i > 0;
    }

    //update MyBooking value data
    public boolean updateMyBookingValueData(int id, String favValue) {
        ContentValues values = new ContentValues();
        values.put("FavouriteStore", favValue);
        SQLiteDatabase db = this.getWritableDatabase();
        long i = 0;
        i = db.update("MyBookingData", values, "sno = " + id + " ", null);
        db.close();
        return i > 0;
    }

    //MyBooking data by id
    public List<Bookservicelist> getAllMyBookingData() {

        String query = "Select * FROM MyBookingData";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        List<Bookservicelist> list = new ArrayList<Bookservicelist>();

        if (cursor.moveToFirst()) {
            while (cursor.isAfterLast() == false) {
                Bookservicelist ob = new Bookservicelist();
                populateMyBookingData(cursor, ob);
                list.add(ob);
                cursor.moveToNext();
            }
        }
        db.close();
        return list;
    }


    public boolean deleteMyBookingData() {
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("MyBookingData", null, null);
        db.close();
        return result;
    }

    public boolean deleteMyBookingDataWithSno(int sno) {
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("MyBookingData", "sno = " + sno + " ", null);
        db.close();
        return result;
    }

}

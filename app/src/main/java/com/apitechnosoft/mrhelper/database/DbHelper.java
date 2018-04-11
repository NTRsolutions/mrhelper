package com.apitechnosoft.mrhelper.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.apitechnosoft.mrhelper.utilities.Contants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Neeraj on 7/25/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 19;
    public static final String DATABASE_NAME = Contants.DATABASE_NAME;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS userData");
        db.execSQL("DROP TABLE IF EXISTS GetAllAddressEntity");
        db.execSQL("DROP TABLE IF EXISTS CityDataEntity");
        db.execSQL("DROP TABLE IF EXISTS LocalityDataEntity");
        db.execSQL("DROP TABLE IF EXISTS CacheServiceCall");
        db.execSQL("DROP TABLE IF EXISTS StoreListDataEntity");
        db.execSQL("DROP TABLE IF EXISTS CategoryListDataEntity");
        db.execSQL("DROP TABLE IF EXISTS ProductListDataEntity");
        db.execSQL("DROP TABLE IF EXISTS MyOrderDataEntity");
        db.execSQL("DROP TABLE IF EXISTS FavouriteStoresDataEntity");
        db.execSQL("DROP TABLE IF EXISTS MyOrderHistoryDataEntity");
        db.execSQL("DROP TABLE IF EXISTS TrackOrderDataEntity");
        db.execSQL("DROP TABLE IF EXISTS SelectedStoreDataEntity");
        db.execSQL("DROP TABLE IF EXISTS MenuListDataEntity");
        onCreate(db);

    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);

    }

    public void onCreate(SQLiteDatabase db) {
        String CREATE_user_TABLE = "CREATE TABLE userData(LoginID INTEGER,PhoneNumber TEXT,Name TEXT,Otp INTEGER,EmailID TEXT,Role INTEGER,ProfilePictureUrl TEXT)";
        db.execSQL(CREATE_user_TABLE);
        String CREATE_getAllAddress_TABLE = "CREATE TABLE GetAllAddressEntity(AddressId INTEGER,CompleteAddress TEXT,CityId INTEGER,ZipCode TEXT,LoginID INTEGER,PhoneNumber TEXT,LandMark TEXT,LocalityId INTEGER,LocalityName TEXT)";//
        db.execSQL(CREATE_getAllAddress_TABLE);
        String CREATE_City_TABLE = "CREATE TABLE CityDataEntity(CityId INTEGER, CityName TEXT)";
        db.execSQL(CREATE_City_TABLE);
        String CREATE_Locality_TABLE = "CREATE TABLE LocalityDataEntity(localityId INTEGER, localityName TEXT,CityId INTEGER)";
        db.execSQL(CREATE_Locality_TABLE);
        String CREATE_CacheServiceCalls_TABLE = "CREATE TABLE CacheServiceCall(id INTEGER PRIMARY KEY, url TEXT, ServerRequestDateTime Text)";
        db.execSQL(CREATE_CacheServiceCalls_TABLE);
        String CREATE_StoreList_TABLE = "CREATE TABLE StoreListDataEntity(StoreId INTEGER, StoreName TEXT,StorePhoneNumber TEXT,StoreEmailId TEXT,StoreAddress TEXT,LocalityId INTEGER,StorePicturesUrl TEXT,FavouriteStore TEXT,OpeningTime TEXT,ClosingTime TEXT,StoreStatus TEXT)";
        db.execSQL(CREATE_StoreList_TABLE);
        String CREATE_CategoryList_TABLE = "CREATE TABLE CategoryListDataEntity(CategoryId INTEGER, CategoryName TEXT,StoreId INTEGER, ImageUrl TEXT,CategoryDescription TEXT)";
        //CategoryId,CategoryName,StoreId,ImageUrl,CategoryDescription CategoryListDataEntity
        db.execSQL(CREATE_CategoryList_TABLE);
        String CREATE_ProductList_TABLE = "CREATE TABLE ProductListDataEntity(ProductId INTEGER, ProductName TEXT,CategoryId INTEGER,UnitPrice REAL,Discount REAL,GST REAL,TaxType TEXT,UOM TEXT,ProductDetails TEXT,ImageUrl TEXT)";
        //ProductId,ProductName,CategoryId,UnitPrice,Discount,GST,TaxType,ProductDetails ProductListDataEntity
        db.execSQL(CREATE_ProductList_TABLE);

        String CREATE_MyOrder_TABLE = "CREATE TABLE MyOrderDataEntity(ProductId INTEGER,ProductName TEXT, StoreId INTEGER,Quantity REAL,Price REAL, OrderTime TEXT,CategoryId INTEGER,discount REAL,UOM TEXT)";
        //ProductId,ProductName,StoreId,Quantity,Price,OrderTime,CategoryId,discount  MyOrderDataEntity
        db.execSQL(CREATE_MyOrder_TABLE);
        String CREATE_FavouriteStores_TABLE = "CREATE TABLE FavouriteStoresDataEntity(StoreId INTEGER,StoreName TEXT,StorePhoneNumber TEXT,StoreEmailId TEXT,StoreAddress TEXT,LocalityId INTEGER,StorePicturesUrl TEXT,FavouriteStore TEXT,OpeningTime TEXT,ClosingTime TEXT,StoreStatus TEXT)";
        // StoreId , StoreName, StorePhoneNumber ,StoreEmailId , StoreAddress , LocalityId , StorePicturesUrl
        db.execSQL(CREATE_FavouriteStores_TABLE);
        String CREATE_MyOrderHistory_TABLE = "CREATE TABLE MyOrderHistoryDataEntity(OrderId INTEGER,OrderNumber TEXT,StoreId INTEGER,ProductId INTEGER,LoginId INTEGER,Quantity REAL,OrderTime TEXT,TotalPrice REAL,NetPrice REAL,SpecialDiscount REAL,SubTotal REAL,TotalGST REAL,GrandTotal REAL,shippingCharge REAL,PromoDiscount REAL,OrderStatus TEXT,UOM TEXT,OrderDetails TEXT,StoreName TEXT)";//NetPrice REAL,SpecialDiscount REAL,SubTotal REAL,TotalGST REAL,GrandTotal REAL,shippingCharge REAL,PromoDiscount REAL,
        db.execSQL(CREATE_MyOrderHistory_TABLE);
        String CREATE_TrackOrder_TABLE = "CREATE TABLE TrackOrderDataEntity(OrderId INTEGER,OrderNumber TEXT,StoreId INTEGER,ProductId INTEGER,LoginId INTEGER,Quantity REAL,OrderTime TEXT,TotalPrice REAL,NetPrice REAL,SpecialDiscount REAL,SubTotal REAL,TotalGST REAL,GrandTotal REAL,shippingCharge REAL,PromoDiscount REAL,OrderStatus TEXT,UOM TEXT,OrderDetails TEXT,StoreName TEXT)";//UOM TEXT,
        db.execSQL(CREATE_TrackOrder_TABLE);

        String CREATE_Selected_Store_TABLE = "CREATE TABLE SelectedStoreDataEntity(StoreId INTEGER, storeName TEXT,categoryId INTEGER)";
        //StoreId,storeName SelectedStoreDataEntity
        db.execSQL(CREATE_Selected_Store_TABLE);

        String CREATE_Menu_List_TABLE = "CREATE TABLE MenuListDataEntity(MenuId INTEGER, MenuName TEXT,ImageUrl TEXT)";
        //            "MenuId","MenuName","ImageUrl"
        db.execSQL(CREATE_Menu_List_TABLE);

    }


    //--------------------------GetAllStore---------------
  /*  public boolean upsertAllStore(Data ob) {
        boolean done = false;
        Data data = null;
        if (ob.getStoreId() != 0) {
            data = getStoreData(ob.getStoreId());
            if (data == null) {
                done = insertStoreData(ob);
            } else {
                done = updateStoreData(ob);
            }
        }
        return done;
    }

    //GetAll Store
    private void populateStoreData(Cursor cursor, Data ob) {
        ob.setStoreId(cursor.getInt(0));
        ob.setStoreName(cursor.getString(1));
        ob.setStorePhoneNumber(cursor.getString(2));
        ob.setStoreEmailId(cursor.getString(3));
        ob.setStoreAddress(cursor.getString(4));
        ob.setLocalityId(cursor.getInt(5));
        ob.setStorePicturesUrl(cursor.getString(6));

        boolean favorite = Boolean.parseBoolean(cursor.getString(7));
        ob.setFavouriteStore(favorite);
        ob.setOpeningTime(cursor.getString(8));
        ob.setClosingTime(cursor.getString(9));
        ob.setStoreStatus(cursor.getString(10));

    }

    //show  Store list data
    public List<Data> GetAllStoreData() {
        ArrayList<Data> list = new ArrayList<Data>();
        String query = "Select * FROM StoreListDataEntity";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {

            while (cursor.isAfterLast() == false) {
                Data ob = new Data();
                populateStoreData(cursor, ob);
                list.add(ob);
                cursor.moveToNext();
            }
        }
        db.close();
        return list;
    }

    //insert Store data
    public boolean insertStoreData(Data ob) {
        ContentValues values = new ContentValues();
        populateStoreValue(ob, values);
        SQLiteDatabase db = this.getWritableDatabase();

        long i = db.insert("StoreListDataEntity", null, values);
        db.close();
        return i > 0;
    }

    //Store data by id
    public Data getStoreData(int StoreId) {
        String query = "Select * FROM StoreListDataEntity WHERE StoreId= " + StoreId + "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Data data = new Data();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            populateStoreData(cursor, data);
            cursor.close();
        } else {
            data = null;
        }
        db.close();
        return data;
    }

    //StoreTiming TEXT,StoreStatus TEXT
    private void populateStoreValue(Data ob, ContentValues values) {
        values.put("StoreId", ob.getStoreId());
        values.put("StoreName", ob.getStoreName());
        values.put("StorePhoneNumber", ob.getStorePhoneNumber());
        values.put("StoreEmailId", ob.getStoreEmailId());
        values.put("StoreAddress", ob.getStoreAddress());
        values.put("LocalityId", ob.getLocalityId());
        values.put("StorePicturesUrl", ob.getStorePicturesUrl());
        values.put("FavouriteStore", String.valueOf(ob.isFavouriteStore()));
//        OpeningTime TEXT,ClosingTime TEXT,StoreStatus TEXT
        values.put("OpeningTime", String.valueOf(ob.getOpeningTime()));
        values.put("ClosingTime", String.valueOf(ob.getClosingTime()));
        values.put("StoreStatus", String.valueOf(ob.getStoreStatus()));
    }

    //update Store data
    public boolean updateStoreData(Data ob) {
        ContentValues values = new ContentValues();
        populateStoreValue(ob, values);

        SQLiteDatabase db = this.getWritableDatabase();
        long i = 0;
        i = db.update("StoreListDataEntity", values, "StoreId = " + ob.getStoreId() + " ", null);

        db.close();
        return i > 0;
    }

    //update Store favority value data
    public boolean updateStoreFavorityValueData(int storeId, String favValue) {
        ContentValues values = new ContentValues();
        values.put("FavouriteStore", favValue);
        SQLiteDatabase db = this.getWritableDatabase();
        long i = 0;
        i = db.update("StoreListDataEntity", values, "StoreId = " + storeId + " ", null);
        db.close();
        return i > 0;
    }

    //Store data by id
    public List<Data> getAllStoreData() {

        String query = "Select * FROM StoreListDataEntity";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        List<Data> list = new ArrayList<Data>();

        if (cursor.moveToFirst()) {
            while (cursor.isAfterLast() == false) {
                Data ob = new Data();
                populateStoreData(cursor, ob);
                list.add(ob);
                cursor.moveToNext();
            }
        }
        db.close();
        return list;
    }


    public boolean deleteAllStoreData() {
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("StoreListDataEntity", null, null);
        db.close();
        return result;
    }
*/


}

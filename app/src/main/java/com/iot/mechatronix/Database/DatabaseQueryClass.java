package com.iot.mechatronix.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.Toast;

import com.iot.mechatronix.Config;
import com.iot.mechatronix.responceLocal.Product;
import com.iot.mechatronix.responceLocal.ProductType;
import com.iot.mechatronix.responceLocal.ResponseColorLocal;
import com.iot.mechatronix.responceLocal.ResponseProductBaseLocal;
import com.iot.mechatronix.responceLocal.ResponseProductPackLocal;
import com.iot.mechatronix.responceLocal.Shade;
import com.iot.mechatronix.retrofit.RresponceFetchBase;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabaseQueryClass {

    private static final String TAG = "DatabaseQueryClass";
    private Context context;

    public DatabaseQueryClass(Context context){
        this.context = context;
        Logger.addLogAdapter(new AndroidLogAdapter());
    }



    public List<ProductType> getAllProductType(Context context){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {

            cursor = sqLiteDatabase.query(Config.TABLE_PRODUCT_CATEGORY, null, null, null, null, null, null, null);

            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above line.

             String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_STUDENT_ID, Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_EMAIL, Config.COLUMN_STUDENT_PHONE, Config.TABLE_STUDENT);
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if(cursor!=null)
                if(cursor.moveToFirst()){
                    List<ProductType> productTpeList = new ArrayList<>();
                    do {
                        @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_PRODUCT_CATEGORY_ID));
                        @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PRODUCT_CATEGORY_NAME));

                        productTpeList.add(new ProductType(id, name));
                    }   while (cursor.moveToNext());
                    Log.e(TAG, "getAllProductType: productTpeList ::"+productTpeList.size() );

                    return productTpeList;
                }
        } catch (Exception e){
            Logger.d("Exception: " + e.getMessage());
          //  Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }

    public List<Product> getAllProduct(Context context,String ID){
        Log.e(TAG, "getAllProduct: IDDDD ::"+ID );

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {

            String myQuery = "SELECT  * FROM " + Config.TABLE_PRODUCT + " WHERE " + Config.COLUMN_PRODUCT_CATEGORY_ID_REL + " LIKE '%" + ID + "%'";

        //    String myQuery = "SELECT  * FROM " + Config.TABLE_PRODUCT + " WHERE " + Config.COLUMN_PRODUCT_ID_REL + " = " + ID;

            cursor = sqLiteDatabase.rawQuery(myQuery, null);
            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above line.

             String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_STUDENT_ID, Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_EMAIL, Config.COLUMN_STUDENT_PHONE, Config.TABLE_STUDENT);
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if(cursor!=null)
                if(cursor.moveToFirst()){
                    List<Product> productList = new ArrayList<>();
                    do {
                        @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_PRODUCT_ID));
                        @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PRODUCT_NAME));

                        productList.add(new Product(id, name));
                    }   while (cursor.moveToNext());
                    Log.e(TAG, "getAllProduct: productList ::"+productList.size() );

                    return productList;
                }
        } catch (Exception e){
            Logger.d("Exception: " + e.getMessage());
         //   Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }
    public List<Shade> getAllShade(Context context,String ID){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {

            String myQuery = "SELECT  * FROM " + Config.TABLE_SHADE + " WHERE " + Config.COLUMN_PRODUCT_ID_REL + " = " + ID;

            cursor = sqLiteDatabase.rawQuery(myQuery, null);
            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above line.

             String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_STUDENT_ID, Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_EMAIL, Config.COLUMN_STUDENT_PHONE, Config.TABLE_STUDENT);
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if(cursor!=null)
                if(cursor.moveToFirst()){
                    List<Shade> productList = new ArrayList<>();
                    do {
                        @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_SHADE_ID));
                        @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(Config.COLUMN_SHADE_NAME));

                        productList.add(new Shade(id, name));
                    }   while (cursor.moveToNext());
                    Log.e(TAG, "getAllProduct: productList ::"+productList.size() );

                    return productList;
                }
        } catch (Exception e){
            Logger.d("Exception: " + e.getMessage());
            //   Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }
    @SuppressLint("Range")
    public String getTintCost(String tintID) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        Cursor cursor = null;
         String TintPriseLtr = "";
        try {
            String countQuery = "SELECT * FROM " + Config.TABLE_TINT + " WHERE " + Config.COLUMN_TINT_ID + " = ?";
            cursor = sqLiteDatabase.rawQuery(countQuery, new String[]{tintID});
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                  TintPriseLtr = cursor.getString(cursor.getColumnIndex(Config.COLUMN_TINT_COST_LTR));

            }
            return TintPriseLtr;
        }finally {
            cursor.close();
        }
    }
    public List<ResponseColorLocal> getAllColor(Context context, String ID){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {

            String myQuery = "SELECT  * FROM " + Config.TABLE_COLOR + " WHERE " + Config.COLUMN_SHADE_ID_REL + " LIKE '%" + ID + "%'";

            cursor = sqLiteDatabase.rawQuery(myQuery, null);
            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above line.

             String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_STUDENT_ID, Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_EMAIL, Config.COLUMN_STUDENT_PHONE, Config.TABLE_STUDENT);
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if(cursor!=null)
                if(cursor.moveToFirst()){
                    List<ResponseColorLocal> productList = new ArrayList<>();
                    do {
                        @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_COLOR_ID));
                        @SuppressLint("Range") String ColorName = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COLOR_NAME));
                        @SuppressLint("Range") String ColorCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COLOR_CODE));

                        @SuppressLint("Range") String Tint1 = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COLOR_TINT_TYPE_1));
                        @SuppressLint("Range") String Tint1Value = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COLOR_TINT_TYPE_1_VALUE));

                        @SuppressLint("Range") String Tint2 = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COLOR_TINT_TYPE_2));
                        @SuppressLint("Range") String Tint2Value = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COLOR_TINT_TYPE_2_VALUE));


                        @SuppressLint("Range") String Tint3 = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COLOR_TINT_TYPE_3));
                        @SuppressLint("Range") String Tint3Value = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COLOR_TINT_TYPE_3_VALUE));


                        @SuppressLint("Range") String Tint4 = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COLOR_TINT_TYPE_4));
                        @SuppressLint("Range") String Tint4Value = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COLOR_TINT_TYPE_4_VALUE));


                        @SuppressLint("Range") String Tint5 = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COLOR_TINT_TYPE_5));
                        @SuppressLint("Range") String Tint5Value = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COLOR_TINT_TYPE_5_VALUE));

                        productList.add(new ResponseColorLocal(id,ColorName,ColorCode,Tint1,Tint1Value,Tint2,Tint2Value,Tint3,Tint3Value,Tint4,Tint4Value,Tint5,Tint5Value));
                    }   while (cursor.moveToNext());
                    Log.e(TAG, "getAllColor: "+productList.size() );

                    return productList;
                }
        } catch (Exception e){
            Logger.d("Exception: " + e.getMessage());
            //   Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }

    public List<ResponseProductBaseLocal> getAllBase(Context context){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {
            cursor = sqLiteDatabase.query(Config.TABLE_BASE, null, null, null, null, null, null, null);

            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above line.

             String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_STUDENT_ID, Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_EMAIL, Config.COLUMN_STUDENT_PHONE, Config.TABLE_STUDENT);
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if(cursor!=null)
                if(cursor.moveToFirst()){
                    List<ResponseProductBaseLocal> productList = new ArrayList<>();
                    do {


                        @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_BASE_ID));
                        @SuppressLint("Range") String BaseName = cursor.getString(cursor.getColumnIndex(Config.COLUMN_BASE_NAME));
                        @SuppressLint("Range") String BaseCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_BASE_CODE));

                        productList.add(new ResponseProductBaseLocal(id,BaseName,BaseCode));
                    }   while (cursor.moveToNext());
                    Log.e(TAG, "getAllColor: "+productList.size() );

                    return productList;
                }
        } catch (Exception e){
            Logger.d("Exception: " + e.getMessage());
            //   Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }
    public List<ResponseProductPackLocal> getAllPack(Context context, String ID){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {

            String myQuery = "SELECT  * FROM " + Config.TABLE_PACK + " WHERE " + Config.COLUMN_BASE_ID_REL + " LIKE '%" + ID + "%'";

            cursor = sqLiteDatabase.rawQuery(myQuery, null);
            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above line.

             String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_STUDENT_ID, Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_EMAIL, Config.COLUMN_STUDENT_PHONE, Config.TABLE_STUDENT);
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if(cursor!=null)
                if(cursor.moveToFirst()){
                    List<ResponseProductPackLocal> packList = new ArrayList<>();
                    do {
                        @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_PACK_ID));
                        @SuppressLint("Range") String PackName = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PACK_NAME));
                        @SuppressLint("Range") String PackSize = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PACK_SIZE));
                        @SuppressLint("Range") String PackAmount = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PACK_COST));

                        packList.add(new ResponseProductPackLocal(id,PackName,PackSize,PackAmount));
                    }   while (cursor.moveToNext());
                    Log.e(TAG, "getAllColor: "+packList.size() );

                    return packList;
                }
        } catch (Exception e){
            Logger.d("Exception: " + e.getMessage());
            //   Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }

}
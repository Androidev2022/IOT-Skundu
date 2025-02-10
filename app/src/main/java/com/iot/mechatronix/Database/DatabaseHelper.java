package com.iot.mechatronix.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.iot.mechatronix.Config;
import com.iot.mechatronix.responceLocal.ResponseProductBaseLocal;
import com.iot.mechatronix.retrofit.ReesponceFetchColorNameDB;
import com.iot.mechatronix.retrofit.ReesponceFetchProductName;
import com.iot.mechatronix.retrofit.ReesponceFetchShadeCard;
import com.iot.mechatronix.retrofit.ResponceFetchProductType;
import com.iot.mechatronix.retrofit.ResponseFetchTints;
import com.iot.mechatronix.retrofit.RresponceFetchBase;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static DatabaseHelper databaseHelper;

    // All Static variables
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = Config.DATABASE_NAME;

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public static synchronized DatabaseHelper getInstance(Context context){
        if(databaseHelper==null){
            databaseHelper = new DatabaseHelper(context);
        }
        return databaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create tables SQL execution
        String CREATE_PRODUCT_TYPE_TABLE = "CREATE TABLE " + Config.TABLE_PRODUCT_CATEGORY + "("
                + Config.COLUMN_PRODUCT_CATEGORY_ID + " INTEGER, "
                + Config.COLUMN_PRODUCT_CATEGORY_NAME + " TEXT "
                + ")";
        db.execSQL(CREATE_PRODUCT_TYPE_TABLE);
        Logger.d("DB created!");

        // Create tables SQL execution
        String CREATE_PRODUCT_TABLE = "CREATE TABLE " + Config.TABLE_PRODUCT + "("
                + Config.COLUMN_PRODUCT_CATEGORY_ID_REL + " INTEGER, "
                + Config.COLUMN_PRODUCT_ID + " INTEGER, "
                + Config.COLUMN_PRODUCT_NAME + " TEXT "
                + ")";
        db.execSQL(CREATE_PRODUCT_TABLE);
        // Create tables SQL execution
        String CREATE_SHADE_TABLE = "CREATE TABLE " + Config.TABLE_SHADE + "("
                + Config.COLUMN_PRODUCT_ID_REL + " INTEGER, "
                + Config.COLUMN_SHADE_ID + " INTEGER, "
                + Config.COLUMN_SHADE_NAME + " TEXT "
                + ")";
        db.execSQL(CREATE_SHADE_TABLE);

        String CREATE_COLOR_TABLE = "CREATE TABLE " + Config.TABLE_COLOR + "("
                +Config.COLUMN_SHADE_ID_REL + " INTERGER, "
                + Config.COLUMN_COLOR_ID + " INTEGER, "
                + Config.COLUMN_COLOR_NAME + " TEXT, "
                + Config.COLUMN_COLOR_CODE + " TEXT, "

                + Config.COLUMN_COLOR_TINT_TYPE_1 + " TEXT, "
                + Config.COLUMN_COLOR_TINT_TYPE_1_VALUE + " TEXT, "

                + Config.COLUMN_COLOR_TINT_TYPE_2 + " TEXT, "
                + Config.COLUMN_COLOR_TINT_TYPE_2_VALUE + " TEXT, "

                + Config.COLUMN_COLOR_TINT_TYPE_3 + " TEXT, "
                + Config.COLUMN_COLOR_TINT_TYPE_3_VALUE + " TEXT, "

                + Config.COLUMN_COLOR_TINT_TYPE_4 + " TEXT, "
                + Config.COLUMN_COLOR_TINT_TYPE_4_VALUE + " TEXT, "

                + Config.COLUMN_COLOR_TINT_TYPE_5 + " TEXT, "
                + Config.COLUMN_COLOR_TINT_TYPE_5_VALUE + " TEXT "

                + ")";
        db.execSQL(CREATE_COLOR_TABLE);

        String CREATE_TINT_TABLE = "CREATE TABLE " + Config.TABLE_TINT + "("
                + Config.COLUMN_TINT_ID + " INTEGER, "
                + Config.COLUMN_TINT_NAME + " TEXT, "
                + Config.COLUMN_TINT_COST_LTR + " TEXT, "
                + Config.COLUMN_TINT_STATUS + " TEXT "
                + ")";
        db.execSQL(CREATE_TINT_TABLE);

        String CREATE_PRODUCT_BASE_TABLE = "CREATE TABLE " + Config.TABLE_BASE + "("
                +Config.COLUMN_BASE_ID + " INTERGER, "
                + Config.COLUMN_BASE_NAME + " TEXT, "
                + Config.COLUMN_BASE_CODE + " TEXT "
                + ")";
        db.execSQL(CREATE_PRODUCT_BASE_TABLE);

        String CREATE_PRODUCT_PACK_TABLE = "CREATE TABLE " + Config.TABLE_PACK + "("
                +Config.COLUMN_BASE_ID_REL + " INTERGER, "
                +Config.COLUMN_PACK_ID + " INTERGER, "
                + Config.COLUMN_PACK_NAME + " TEXT, "
                + Config.COLUMN_PACK_SIZE + " TEXT, "
                + Config.COLUMN_PACK_COST + " TEXT "
                + ")";
        db.execSQL(CREATE_PRODUCT_PACK_TABLE);

        Logger.d("DB created!");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_PRODUCT_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_SHADE);

//        // Create tables again
        onCreate(db);
    }

    public void insertCategory(List<ResponceFetchProductType.Data.Category> productCategory) {

        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        Log.e(TAG, "insert: " + productCategory.size());
        for (int i = 0; i < productCategory.size(); i++) {
            String id = String.valueOf(productCategory.get(i).getId());
            String CategoryName = productCategory.get(i).getProductCategoryName();
            values.put(Config.COLUMN_PRODUCT_CATEGORY_ID, id);
            values.put(Config.COLUMN_PRODUCT_CATEGORY_NAME, CategoryName);
            db.insert(Config.TABLE_PRODUCT_CATEGORY, null, values);

        }
    }

    public void insertProduct(List<ReesponceFetchProductName.Data.Product> productNameDB) {
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        Log.e(TAG, "insert: " + productNameDB.size());

        for (int i = 0; i < productNameDB.size(); i++) {
            String CategoryId="";
            for (int j= 0; j < productNameDB.get(i).getCategories().size(); j++) {
                if(CategoryId.equalsIgnoreCase("")){
                    CategoryId = String.valueOf(productNameDB.get(i).getCategories().get(j).getId());

                }else {
                    CategoryId = CategoryId+","+String.valueOf(productNameDB.get(i).getCategories().get(j).getId());

                }

            }
            Log.e(TAG, "insertProductCategory : "+CategoryId );
            String id = String.valueOf(productNameDB.get(i).getId());
            String Name = productNameDB.get(i).getProductName();
            values.put(Config.COLUMN_PRODUCT_CATEGORY_ID_REL, CategoryId);
            values.put(Config.COLUMN_PRODUCT_ID, id);
            values.put(Config.COLUMN_PRODUCT_NAME, Name);
            db.insert(Config.TABLE_PRODUCT, null, values);

        }
    }
    public void insertShadeCard(List<ReesponceFetchShadeCard.Data.ProductsShadeCard> productShadeList) {
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        Log.e(TAG, "insert: " + productShadeList.size());
        for (int i = 0; i < productShadeList.size(); i++) {
            String P_id = String.valueOf(productShadeList.get(i).getProductId());
            String id = String.valueOf(productShadeList.get(i).getId());
            String ShadeName = productShadeList.get(i).getShadeCardName();
            values.put(Config.COLUMN_PRODUCT_ID_REL, P_id);
            values.put(Config.COLUMN_SHADE_ID, id);
            values.put(Config.COLUMN_SHADE_NAME, ShadeName);
            db.insert(Config.TABLE_SHADE, null, values);

        }
    }
    public void insertColor(List<ReesponceFetchColorNameDB.Data.Colour> colorList) {
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        Log.e(TAG, "insert: " + colorList.size());
        for (int i = 0; i < colorList.size(); i++) {
            String ShadeId="";
            for (int j= 0; j < colorList.get(i).getShadeCards().size(); j++) {
                if(ShadeId.equalsIgnoreCase("")){
                    ShadeId = String.valueOf(colorList.get(i).getShadeCards().get(j).getId());

                }else {
                    ShadeId = ShadeId+","+String.valueOf(colorList.get(i).getShadeCards().get(j).getId());

                }

            }
            String color_id = String.valueOf(colorList.get(i).getId());
            String color_name = String.valueOf(colorList.get(i).getColourName());
            String color_code = colorList.get(i).getColourCode();

            int tintType_1 = colorList.get(i).getTintType1();
            int tintType_1_value = colorList.get(i).getTintType1Value();

            int tintType_2 = colorList.get(i).getTintType2();
            int tintType_2_value = colorList.get(i).getTintType2Value();

            int tintType_3 = colorList.get(i).getTintType3();
            int tintType_3_value = colorList.get(i).getTintType3Value();

            int tintType_4 = colorList.get(i).getTintType4();
            int tintType_4_value = colorList.get(i).getTintType4Value();

            int tintType_5 = colorList.get(i).getTintType5();
            int tintType_5_value = colorList.get(i).getTintType5Value();

            values.put(Config.COLUMN_SHADE_ID_REL, ShadeId);
            values.put(Config.COLUMN_COLOR_ID, color_id);
            values.put(Config.COLUMN_COLOR_NAME, color_name);
            values.put(Config.COLUMN_COLOR_CODE, color_code);

            values.put(Config.COLUMN_COLOR_TINT_TYPE_1, tintType_1);
            values.put(Config.COLUMN_COLOR_TINT_TYPE_1_VALUE, tintType_1_value);

            values.put(Config.COLUMN_COLOR_TINT_TYPE_2, tintType_2);
            values.put(Config.COLUMN_COLOR_TINT_TYPE_2_VALUE, tintType_2_value);

            values.put(Config.COLUMN_COLOR_TINT_TYPE_3, tintType_3);
            values.put(Config.COLUMN_COLOR_TINT_TYPE_3_VALUE, tintType_3_value);

            values.put(Config.COLUMN_COLOR_TINT_TYPE_4, tintType_4);
            values.put(Config.COLUMN_COLOR_TINT_TYPE_4_VALUE, tintType_4_value);

            values.put(Config.COLUMN_COLOR_TINT_TYPE_5, tintType_5);
            values.put(Config.COLUMN_COLOR_TINT_TYPE_5_VALUE, tintType_5_value);

            db.insert(Config.TABLE_COLOR, null, values);

        }
    }
    public void insertTint(List<ResponseFetchTints.Data.Tint> tintList) {
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        Log.e(TAG, "insert: " + tintList.size());
        for (int i = 0; i < tintList.size(); i++) {

            int tint_id = tintList.get(i).getId();
            String tint_name = String.valueOf(tintList.get(i).getTintName());
            String tint_cost_Ltr = String.valueOf(tintList.get(i).getTintCostLt());
            String tint_status = String.valueOf(tintList.get(i).getStatus());
            values.put(Config.COLUMN_TINT_ID, tint_id);
            values.put(Config.COLUMN_TINT_NAME, tint_name);
            values.put(Config.COLUMN_TINT_COST_LTR, tint_cost_Ltr);
            values.put(Config.COLUMN_TINT_STATUS, tint_status);
            db.insert(Config.TABLE_TINT, null, values);

        }
    }
    public void insertProductBase(List<RresponceFetchBase.Data.Basis> productBaseList) {
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        Log.e(TAG, "insert: " + productBaseList.size());
        for (int i = 0; i < productBaseList.size(); i++) {
            String Bse_id = String.valueOf(productBaseList.get(i).getId());
            String BaseName = String.valueOf(productBaseList.get(i).getProductBaseName());
            String BaseCode = productBaseList.get(i).getProductBaseCode();
            values.put(Config.COLUMN_BASE_ID, Bse_id);
            values.put(Config.COLUMN_BASE_NAME, BaseName);
            values.put(Config.COLUMN_BASE_CODE, BaseCode);
            db.insert(Config.TABLE_BASE, null, values);

        }
    }
    public void insertProductPack(List<RresponceFetchBase.Data.Basis> productPackList) {
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        Log.e(TAG, "insert: " + productPackList.size());
        for (int i = 0; i < productPackList.size(); i++) {

            for (int j= 0; j < productPackList.get(i).getPacks().size(); j++) {

                String BaseID = String.valueOf(productPackList.get(i).getPacks().get(j).getPivot().getBaseId());
                String Pack_id = String.valueOf(productPackList.get(i).getPacks().get(j).getId());
                String PackName = String.valueOf(productPackList.get(i).getPacks().get(j).getProductPackName());
                String PackSize = String.valueOf(productPackList.get(i).getPacks().get(j).getProductPackSize());
                String PackCost = String.valueOf(productPackList.get(i).getPacks().get(j).getPivot().getCost());

                values.put(Config.COLUMN_BASE_ID_REL,BaseID);
                values.put(Config.COLUMN_PACK_ID, Pack_id);
                values.put(Config.COLUMN_PACK_NAME, PackName);
                values.put(Config.COLUMN_PACK_SIZE, PackSize);
                values.put(Config.COLUMN_PACK_COST, PackCost);
                db.insert(Config.TABLE_PACK, null, values);
            }


        }
    }
}

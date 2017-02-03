package com.inerun.dropinsta.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.inerun.dropinsta.constant.AppConstant;
import com.inerun.dropinsta.data.POD;
import com.inerun.dropinsta.helper.DIHelper;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by vineet on 12/1/2016.
 */

public class PODDao {

    protected Context mContext;
    protected SQLiteDatabase mSQLiteDatabase;
    protected SQLiteStatement mInsertStatement;
    private OpenHelper lOpenHelper;

    public static final int POD_STATUS_PENDING = 0;
    public static final int POD_STATUS_UPLOADED = 1;
    public static final int POD_STATUS_FAILED = 2;

    public PODDao(Context mContext) {
        this.mContext = mContext;
        closeDatabase();
        lOpenHelper = new OpenHelper(mContext);
        mSQLiteDatabase = lOpenHelper.getWritableDatabase();

    }

    // code to insert the Delivery Info
    public void insertPODInfo(POD pod) {
        mSQLiteDatabase = lOpenHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DataUtils.POD_NAME, pod.getName());
        values.put(DataUtils.POD_CREATED_TIME, System.currentTimeMillis());
        values.put(DataUtils.POD_STATUS, POD_STATUS_PENDING );
//        values.put(DataUtils.POD_RECEIVER_NAME, pod.getReceiverName() );

        Log.i("consignee_name", pod.getName());

//        // Inserting Row
        mSQLiteDatabase.insert(DataUtils.TABLE_NAME_POD, null, values);
        //2nd argument is String containing nullColumnHack
        mSQLiteDatabase.close(); // Closing database connection
    }


    // code to update the single POD STATUS
    public int updatePODStatus(int id, String podNameOnServer ,int status) {

        mSQLiteDatabase = lOpenHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataUtils.POD_STATUS, status);
        values.put(DataUtils.POD_UPDATED_TIME, DIHelper.getDateTime(AppConstant.DATEIME_FORMAT));

        if(podNameOnServer != null && podNameOnServer.trim().length() > 0){
            values.put(DataUtils.POD_NAME_ON_SERVER, podNameOnServer);
        }
        // updating row
        int value = mSQLiteDatabase.update(DataUtils.TABLE_NAME_POD, values, DataUtils.COLUMN_ID +" = " + id, null);

        mSQLiteDatabase.close();

        return value;

    }


    // code to get all Delivery Info for Delivery Listing
    public ArrayList<POD> getAllPendingPODs() {
        ArrayList<POD> list = new ArrayList<>();

        // Select All Query
//        String selectQuery = "SELECT  * FROM " + DataUtils.TABLE_NAME_DELIVERY ;
        String selectQuery = "SELECT * FROM " + DataUtils.TABLE_NAME_POD + " WHERE "+DataUtils.POD_STATUS + " = "+POD_STATUS_PENDING +" OR "+ DataUtils.POD_STATUS + " = "+POD_STATUS_FAILED  ;
        Log.i("getAllPendingPODs", selectQuery);

        mSQLiteDatabase = lOpenHelper.getWritableDatabase();
        Cursor cursor = mSQLiteDatabase.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Log.i("POD_ROW_ID", "" + cursor.getInt(0));
//                UpdatedParcelData updatedParcelData = new UpdatedParcelData(cursor.getString(1),Integer.parseInt(cursor.getString(5)),cursor.getString(6),Integer.parseInt(cursor.getString(8)),cursor.getString(26));
                    POD pod = new POD(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
                list.add(pod);
            } while (cursor.moveToNext());
        }
        mSQLiteDatabase.close();

        return list;
    }


    // code to get all Delivery Info for Delivery Listing
    public boolean isPendingPODs() {
        ArrayList<POD> list = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT count(*) FROM " + DataUtils.TABLE_NAME_POD + " WHERE "+DataUtils.POD_STATUS + " = "+POD_STATUS_PENDING +" OR "+ DataUtils.POD_STATUS + " = "+POD_STATUS_FAILED  ;
        Log.i("getAllVideo", selectQuery);

        mSQLiteDatabase = lOpenHelper.getWritableDatabase();
        Cursor cursor = mSQLiteDatabase.rawQuery(selectQuery, null);

        int count = 0;
        if(null != cursor) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                count = cursor.getInt(0);
            }
            cursor.close();
        }

        mSQLiteDatabase.close();

        if(count > 0){
            return true;
        }else{
            return false;
        }

    }

    // code to get all Delivery Info for Delivery Listing
    public int getPODIdByName(String Pod_name) {

        int pod_id = 0;
        // Select All Query
        String selectQuery = "SELECT * FROM " + DataUtils.TABLE_NAME_POD + " WHERE "+DataUtils.POD_NAME + " = '"+ Pod_name +"' "  ;
        Log.i("getAllVideo", selectQuery);

        mSQLiteDatabase = lOpenHelper.getWritableDatabase();
        Cursor cursor = mSQLiteDatabase.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Log.i("POD_ROW_ID", "" + cursor.getInt(0));
//                UpdatedParcelData updatedParcelData = new UpdatedParcelData(cursor.getString(1),Integer.parseInt(cursor.getString(5)),cursor.getString(6),Integer.parseInt(cursor.getString(8)),cursor.getString(26));
            pod_id = cursor.getInt(0);

            } while (cursor.moveToNext());
        }
        mSQLiteDatabase.close();

        return pod_id;
    }


    // code to get all Delivery Info for Delivery Listing
    public POD getPODById(int id) {

        POD pod = new POD();
        // Select All Query
        String selectQuery = "SELECT * FROM " + DataUtils.TABLE_NAME_POD + " WHERE "+DataUtils.COLUMN_ID + " = "+ id  ;
        Log.i("getPODById", selectQuery);

        mSQLiteDatabase = lOpenHelper.getWritableDatabase();
        Cursor cursor = mSQLiteDatabase.rawQuery(selectQuery, null);



        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Log.i("POD_ROW_ID", "" + cursor.getInt(0));
                pod = new POD(cursor.getInt(0),  cursor.getString(4), cursor.getString(1));

            } while (cursor.moveToNext());
        }
        mSQLiteDatabase.close();

        return pod;
    }


    // code to get Old PODs list 
    public ArrayList<POD> getOldPODsListByDate() {
        ArrayList<POD> list = new ArrayList<>();

        long currentTime = System.currentTimeMillis();
        Log.i("currentTime",""+ currentTime);
        long periodDays = TimeUnit.MILLISECONDS.convert(AppConstant.POD_Delete_NoOfDays, TimeUnit.DAYS); //gives 86400000;
        Log.i("periodDays",""+ periodDays);
        long dateRemain = (Long)currentTime - (Long)periodDays;
        Log.i("dateRemain",""+ dateRemain);
        // Select All Query
        String selectQuery = "SELECT * FROM " + DataUtils.TABLE_NAME_POD + " WHERE "+DataUtils.POD_CREATED_TIME + " < "+ dateRemain  ;
        Log.i("getOldPODsListByDate", selectQuery);

        mSQLiteDatabase = lOpenHelper.getWritableDatabase();
        Cursor cursor = mSQLiteDatabase.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Log.i("POD_ROW_ID", "" + cursor.getInt(0));
                POD pod = new POD(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
                list.add(pod);
            } while (cursor.moveToNext());
        }
        mSQLiteDatabase.close();

        return list;
    }

    // code to delete Old PODs
    public int deleteOldPodsData(ArrayList<POD> podArrayList) {

        int status = -1;

        String values = "";
        for(POD pod: podArrayList){
            values = values + "'"+pod.getId()+"'" +", ";
        }

        if(values.contains(",")) {
            values = values.trim().substring(0, values.lastIndexOf(","));
        }
        // Select Delete Query
        String selectQuery = "DELETE FROM "+ DataUtils.TABLE_NAME_POD+" where "+DataUtils.COLUMN_ID+" IN ( "+ values +" )";
        Log.i("deleteOldPodsData", selectQuery);

        mSQLiteDatabase = lOpenHelper.getWritableDatabase();
        mSQLiteDatabase.execSQL(selectQuery);
//        int value =   mSQLiteDatabase.delete(DataUtils.TABLE_NAME_POD,DataUtils.COLUMN_ID+" IN ( "+ values +" )", null);
//        int value = mSQLiteDatabase.update(DataUtils.TABLE_NAME_POD, values, DataUtils.COLUMN_ID +" = " + id, null);

        mSQLiteDatabase.close();

        return status;
    }

//    // code to insert the Delivery Info
//    public void insertDeliveryInfo(ParcelListingData.ParcelData parcelData) {
//        mSQLiteDatabase = lOpenHelper.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//
//        values.put(DataUtils.PARCEL_BARCODE, parcelData.getBarcode());
//        values.put(DataUtils.POD_NAME, parcelData.getName());
//
//        Log.i("consignee_name",parcelData.getName());
//
////        // Inserting Row
//        mSQLiteDatabase.insert(DataUtils.TABLE_NAME_DELIVERY, null, values);
//        //2nd argument is String containing nullColumnHack
//        mSQLiteDatabase.close(); // Closing database connection
//    }

    // Deleting Table Delivery
    public void deleteDeliveryTable() {
        mSQLiteDatabase = lOpenHelper.getWritableDatabase();

        mSQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DataUtils.TABLE_NAME_POD);

        lOpenHelper.onCreate(mSQLiteDatabase);

        mSQLiteDatabase.close();
    }



//    // code to insert the new Category
//    public void insertDeliveryInfoList(ArrayList<ParcelListingData.ParcelData>  list) {
//        mSQLiteDatabase = lOpenHelper.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//
//        for(ParcelListingData.ParcelData p: list){
//            values.put(DataUtils.PARCEL_BARCODE, p.getBarcode());
//            values.put(DataUtils.POD_NAME, p.getName());
//
//            Log.i("consignee_name",p.getName());
//
//            // Inserting Row
//            mSQLiteDatabase.insert(DataUtils.TABLE_NAME_DELIVERY, null, values);
//
//        }
//
//        //2nd argument is String containing nullColumnHack
//        mSQLiteDatabase.close(); // Closing database connection
//    }


    public void closeDatabase() {
        if ((mSQLiteDatabase != null) && mSQLiteDatabase.isOpen()) {
            mSQLiteDatabase.close();
        }
    }

}

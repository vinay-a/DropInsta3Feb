package com.inerun.dropinsta.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.inerun.dropinsta.constant.AppConstant;
import com.inerun.dropinsta.data.POD;
import com.inerun.dropinsta.data.ParcelListingData;
import com.inerun.dropinsta.data.ParcelStatus;
import com.inerun.dropinsta.data.UpdatedParcelData;
import com.inerun.dropinsta.helper.DIHelper;

import java.util.ArrayList;

/**
 * Created by vineet on 12/1/2016.
 */

public class ParcelDao {

    protected Context mContext;
    protected SQLiteDatabase mSQLiteDatabase;
    protected SQLiteStatement mInsertStatement;
    private OpenHelper lOpenHelper;

    public ParcelDao(Context mContext) {
        this.mContext = mContext;
        closeDatabase();
        lOpenHelper = new OpenHelper(mContext);
        mSQLiteDatabase = lOpenHelper.getWritableDatabase();

    }

    // code to insert the Parcel Info
    public void insertParcelInfo(ParcelListingData.ParcelData parcelData) {
        mSQLiteDatabase = lOpenHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DataUtils.PARCEL_BARCODE, parcelData.getBarcode());
        values.put(DataUtils.CONSIGNEE_NAME, parcelData.getName());
        values.put(DataUtils.CONSIGNEE_ID, parcelData.getCustid());
        values.put(DataUtils.PARCEL_WEIGHT, parcelData.getWeight());
        values.put(DataUtils.PARCEL_SPECIAL_INSTRUCTION, parcelData.getSpecialinstructions());
        values.put(DataUtils.PARCEL_DELIVERY_STATUS, parcelData.getDeliverystatus());
        values.put(DataUtils.PARCEL_DELIVERY_COMMENT, parcelData.getDeliverycomments());
        values.put(DataUtils.PARCEL_PAYMENT_TYPE, parcelData.getPayment_type());
        values.put(DataUtils.PARCEL_PAYMENT_STATUS, parcelData.getPayment_status());
        values.put(DataUtils.PARCEL_TYPE, parcelData.getParcel_type());
        values.put(DataUtils.PARCEL_AMOUNT, parcelData.getAmount());
        values.put(DataUtils.PARCEL_CURRENCY, parcelData.getCurrency());
        values.put(DataUtils.PARCEL_DATE, parcelData.getDate());

        values.put(DataUtils.SOURCE_ADDRESS1, parcelData.getSource_address1());
        values.put(DataUtils.SOURCE_ADDRESS2, parcelData.getSource_address2());
        values.put(DataUtils.SOURCE_CITY, parcelData.getSource_city());
        values.put(DataUtils.SOURCE_STATE, parcelData.getSource_state());
        values.put(DataUtils.SOURCE_PIN, parcelData.getSource_pin());
        values.put(DataUtils.SOURCE_PHONE, parcelData.getSource_phone());

        values.put(DataUtils.DELIVERY_ADDRESS1, parcelData.getDelivery_address1());
        values.put(DataUtils.DELIVERY_ADDRESS2, parcelData.getDelivery_address2());
        values.put(DataUtils.DELIVERY_CITY, parcelData.getDelivery_city());
        values.put(DataUtils.DELIVERY_STATE, parcelData.getDelivery_state());
        values.put(DataUtils.DELIVERY_PIN, parcelData.getDelivery_pin());
        values.put(DataUtils.DELIVERY_PHONE, parcelData.getDelivery_phone());


        //Log.i("consignee_name", parcelData.getName());

//        // Inserting Row
        mSQLiteDatabase.insert(DataUtils.TABLE_NAME_PARCEL, null, values);
        //2nd argument is String containing nullColumnHack
        mSQLiteDatabase.close(); // Closing database connection
    }


    // code to get all Parcel Info for Delivery Listing
    public ArrayList<ParcelListingData.ParcelData> getParcelInfoForListing() {
        ArrayList<ParcelListingData.ParcelData> list = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + DataUtils.TABLE_NAME_PARCEL;
        //Log.i("DeliveryInfoForList", selectQuery);

        mSQLiteDatabase = lOpenHelper.getWritableDatabase();
        Cursor cursor = mSQLiteDatabase.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                //Log.i("Parcel_ID", "" + cursor.getInt(0));
                ParcelListingData p = new ParcelListingData();
//                ParcelListingData.ParcelData v = p.new ParcelData(cursor.getString(1),cursor.getString(2),Integer.parseInt(cursor.getString(3)),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10),cursor.getString(11),cursor.getString(12));
                ParcelListingData.ParcelData v = p.new ParcelData(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),
                        Integer.parseInt(cursor.getString(5)), cursor.getString(6), Integer.parseInt(cursor.getString(7)), Integer.parseInt(cursor.getString(8)),
                        cursor.getString(9), cursor.getString(10), cursor.getString(11), cursor.getString(12),
                        cursor.getString(13), cursor.getString(14), cursor.getString(15), cursor.getString(16), cursor.getString(17), cursor.getString(18),
                        cursor.getString(19), cursor.getString(20), cursor.getString(21), cursor.getString(22), cursor.getString(23), cursor.getString(24), cursor.getString(27));
                list.add(v);
            } while (cursor.moveToNext());
        }

        return list;
    }
    // code to get column id for Delivery from barcode
    public int getColumnIdFromBarcode(String barcode) {
        int columnid=-1;

        // Select All Query
        String selectQuery = "SELECT  * FROM " + DataUtils.TABLE_NAME_PARCEL+ " WHERE " + DataUtils.PARCEL_BARCODE + " = '"+ barcode +"' ";
        //Log.i("DeliveryInfoForList", selectQuery);

        mSQLiteDatabase = lOpenHelper.getWritableDatabase();
        Cursor cursor = mSQLiteDatabase.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                //Log.i("Parcel_ID", "" + cursor.getInt(0));
                columnid=cursor.getInt(0);

//
            } while (cursor.moveToNext());
        }

        return columnid;
    }


    // code to get all Delivered Parcel for Delivery Listing
    public ArrayList<ParcelListingData.ParcelData> getDeliveredParcelForListing() {
        ArrayList<ParcelListingData.ParcelData> list = new ArrayList<>();

        // Select All Query
//        String selectQuery = "SELECT  * FROM " + DataUtils.TABLE_NAME_DELIVERY;
        String selectQuery = "SELECT * FROM " + DataUtils.TABLE_NAME_PARCEL + " WHERE " + DataUtils.PARCEL_DELIVERY_STATUS + " = " + AppConstant.StatusKeys.DELIVERED_STATUS;
        //Log.i("getDeliveredParcelList", selectQuery);

        mSQLiteDatabase = lOpenHelper.getWritableDatabase();
        Cursor cursor = mSQLiteDatabase.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                //Log.i("Parcel_ID", "" + cursor.getInt(0));
                ParcelListingData p = new ParcelListingData();
                ParcelListingData.ParcelData v = p.new ParcelData(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),
                        Integer.parseInt(cursor.getString(5)), cursor.getString(6), Integer.parseInt(cursor.getString(7)), Integer.parseInt(cursor.getString(8)),
                        cursor.getString(9), cursor.getString(10), cursor.getString(11), cursor.getString(12),
                        cursor.getString(13), cursor.getString(14), cursor.getString(15), cursor.getString(16), cursor.getString(17), cursor.getString(18),
                        cursor.getString(19), cursor.getString(20), cursor.getString(21), cursor.getString(22), cursor.getString(23), cursor.getString(24), cursor.getString(27));
                list.add(v);
            } while (cursor.moveToNext());
        }

        return list;
    }

    // code to get all Pending Parcels for Delivery Listing
    public ArrayList<ParcelListingData.ParcelData> getPendingParcelForListing() {
        ArrayList<ParcelListingData.ParcelData> list = new ArrayList<>();

        // Select All Query
//        String selectQuery = "SELECT  * FROM " + DataUtils.TABLE_NAME_DELIVERY;
        String selectQuery = "SELECT * FROM " + DataUtils.TABLE_NAME_PARCEL + " WHERE " + DataUtils.PARCEL_DELIVERY_STATUS + " != " + AppConstant.StatusKeys.DELIVERED_STATUS;
        //Log.i("getPendingParcelForList", selectQuery);

        mSQLiteDatabase = lOpenHelper.getWritableDatabase();
        Cursor cursor = mSQLiteDatabase.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                //Log.i("Parcel_ID", "" + cursor.getInt(0));
                ParcelListingData p = new ParcelListingData();
                ParcelListingData.ParcelData v = p.new ParcelData(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),
                        Integer.parseInt(cursor.getString(5)), cursor.getString(6), Integer.parseInt(cursor.getString(7)), Integer.parseInt(cursor.getString(8)),
                        cursor.getString(9), cursor.getString(10), cursor.getString(11), cursor.getString(12),
                        cursor.getString(13), cursor.getString(14), cursor.getString(15), cursor.getString(16), cursor.getString(17), cursor.getString(18),
                        cursor.getString(19), cursor.getString(20), cursor.getString(21), cursor.getString(22), cursor.getString(23), cursor.getString(24), cursor.getString(27));
                list.add(v);
            } while (cursor.moveToNext());
        }

        return list;
    }

    // code to get all Delivery Info for SYNC
    public ArrayList<UpdatedParcelData> getDeliveryInfoForUpdateAndSYNC(Context context) {
        ArrayList<UpdatedParcelData> list = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + DataUtils.TABLE_NAME_PARCEL + " WHERE " + DataUtils.UPDATE_STATUS + " = 1";
        //Log.i("DeliveryForUpdateSYNC", selectQuery);

        mSQLiteDatabase = lOpenHelper.getWritableDatabase();
        Cursor cursor = mSQLiteDatabase.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                int pod_id = cursor.getInt(27);
                POD pod = DIDbHelper.getPODById(context, pod_id);
                //Log.i("pod_id", ""+pod_id);
                //Log.i("podonServer", pod.getPodNameOnServer()+ " receverName : "+pod.getReceiverName());
                String podNameonServer = "";
                String receiverName = "";
                if(pod_id > 0){
                    podNameonServer = pod.getPodNameOnServer();
                    receiverName = pod.getReceiverName();
                }

                ArrayList<ParcelStatus> statusDataList= new ArrayList<>();
                UpdatedParcelData updatedParcelData = new UpdatedParcelData(cursor.getString(1), Integer.parseInt(cursor.getString(5)), cursor.getString(6), Integer.parseInt(cursor.getString(8)), cursor.getString(26), podNameonServer, receiverName,statusDataList);

                list.add(updatedParcelData);
            } while (cursor.moveToNext());
        }
        mSQLiteDatabase.close();

        return list;
    }

    // code to get all Delivered Parcels
    public int getDeliveredParcelCount() {

        String selectQuery = "SELECT count(*) FROM " + DataUtils.TABLE_NAME_PARCEL + " WHERE " + DataUtils.PARCEL_DELIVERY_STATUS + " = " + AppConstant.StatusKeys.DELIVERED_STATUS;
        //Log.i("getDeliveredParcelCount", selectQuery);

        mSQLiteDatabase = lOpenHelper.getWritableDatabase();
        Cursor cursor = mSQLiteDatabase.rawQuery(selectQuery, null);

        int count = 0;
        if (null != cursor) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                count = cursor.getInt(0);
            }
            cursor.close();
        }

        return count;
    }

    // code to get all Pending Parcels
    public int getPendingParcelCount() {

        String selectQuery = "SELECT count(*) FROM " + DataUtils.TABLE_NAME_PARCEL + " WHERE " + DataUtils.PARCEL_DELIVERY_STATUS + " != " + AppConstant.StatusKeys.DELIVERED_STATUS;
        //Log.i("getPendingParcelCount", selectQuery);

        mSQLiteDatabase = lOpenHelper.getWritableDatabase();
        Cursor cursor = mSQLiteDatabase.rawQuery(selectQuery, null);

        int count = 0;
        if (null != cursor) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                count = cursor.getInt(0);
            }
            cursor.close();
        }

        return count;
    }


    // code to update the delivery Comment
    public int updateDeliveryComment(int id,int status, String delivery_comment) {

        mSQLiteDatabase = lOpenHelper.getWritableDatabase();
        //Log.i("id", "" + id);
        //Log.i("delivery_comment", "" + delivery_comment);
        ContentValues values = new ContentValues();
        values.put(DataUtils.PARCEL_DELIVERY_STATUS, status);
        values.put(DataUtils.PARCEL_DELIVERY_COMMENT, delivery_comment);
        values.put(DataUtils.UPDATE_DATE, DIHelper.getDateTime(AppConstant.DATEIME_FORMAT));
        values.put(DataUtils.UPDATE_STATUS, 1);

        // updating row
        int value = mSQLiteDatabase.update(DataUtils.TABLE_NAME_PARCEL, values, DataUtils.COLUMN_ID + " = " + id, null);
        //Log.i("values", "" + values.toString());

        mSQLiteDatabase.close();

        return value;

    }
    public int giveDelivery(int id,int status,int transrowid,String updateDate) {

        mSQLiteDatabase = lOpenHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataUtils.PARCEL_DELIVERY_STATUS, status);

        values.put(DataUtils.TRANSCROWID,transrowid);
        values.put(DataUtils.UPDATE_DATE, updateDate);
        values.put(DataUtils.UPDATE_STATUS, 1);

        // updating row
        int value = mSQLiteDatabase.update(DataUtils.TABLE_NAME_PARCEL, values, DataUtils.COLUMN_ID + " = " + id, null);
        //Log.i("values", "" + values.toString());

        mSQLiteDatabase.close();

        return value;

    }

    public int insertTransactionInfoForBarcode(String barcode,int transrowid,String updateDate) {

        mSQLiteDatabase = lOpenHelper.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(DataUtils.TRANSCROWID,transrowid);
        values.put(DataUtils.UPDATE_DATE, updateDate);


        // updating row
        int value = mSQLiteDatabase.update(DataUtils.TABLE_NAME_PARCEL, values, DataUtils.PARCEL_BARCODE + " = ?"  , new String[]{barcode});
        //Log.i("values", "" + values.toString()+" where "+DataUtils.PARCEL_BARCODE + " = " + barcode+" returned"+value);

        mSQLiteDatabase.close();

        return value;

    }


    public void insertTransactionInfoForMultipleBarcodes(String cs_barcodes,int transrowid,String updateDate) {

        cs_barcodes= cs_barcodes.replace(",","','");

        String query = "UPDATE "+DataUtils.TABLE_NAME_PARCEL+" SET "+DataUtils.TRANSCROWID+" = '"+transrowid+"' , "+DataUtils.UPDATE_DATE+" = '"+updateDate+"' WHERE "+DataUtils.PARCEL_BARCODE +" IN ('"+cs_barcodes+"')";

        Log.i("Parcerdao", "insertTransactionInfoForMultipleBarcodes "+query);
        mSQLiteDatabase = lOpenHelper.getWritableDatabase();
        //Log.i("StatusDao","Deleting Table"+  DataUtils.TABLE_NAME_STATUS);
//        Log.i("insertDeliveryStatus", "execSQL " + System.currentTimeMillis());
        mSQLiteDatabase.execSQL(query);
    }

    // code to update the delivery info for delivered parcel
    public int updateDeliveryInfoDelivered(int parcel_id, int pod_id) {

        mSQLiteDatabase = lOpenHelper.getWritableDatabase();
        //Log.i("id", "" + parcel_id);
        //Log.i("pod_id", "" + pod_id);
        ContentValues values = new ContentValues();
        values.put(DataUtils.POD_ROW_ID, pod_id);
        values.put(DataUtils.UPDATE_DATE, DIHelper.getDateTime(AppConstant.DATEIME_FORMAT));
        values.put(DataUtils.UPDATE_STATUS, 1);
        values.put(DataUtils.PARCEL_DELIVERY_STATUS, AppConstant.StatusKeys.DELIVERED_STATUS);


        // updating row
        int value = mSQLiteDatabase.update(DataUtils.TABLE_NAME_PARCEL, values, DataUtils.PARCEL_ID + " = " + parcel_id, null);
        //Log.i("updateDeliveryDelivered", "" + value);

        mSQLiteDatabase.close();

        return value;

    }

    // Deleting Table Delivery
    public void deleteDeliveryTable() {
        mSQLiteDatabase = lOpenHelper.getWritableDatabase();

        mSQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DataUtils.TABLE_NAME_PARCEL);

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
//            //Log.i("consignee_name",p.getName());
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

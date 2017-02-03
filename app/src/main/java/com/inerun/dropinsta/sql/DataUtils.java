package com.inerun.dropinsta.sql;

/**
 * Created by vineet on 8/2/2016.
 */
public class DataUtils {

    public static final String COLUMN_ID = "id";

    public static final String TABLE_NAME_PARCEL = "PARCEL";

    public static final String PARCEL_ID = "parcel_id";
    public static final String PARCEL_BARCODE = "barcode";
    public static final String CONSIGNEE_NAME = "name";
    public static final String CONSIGNEE_ID = "ConsigneeId"; //Added
    public static final String PARCEL_WEIGHT = "weight";
    public static final String PARCEL_SPECIAL_INSTRUCTION = "specialinstructions";
    public static final String PARCEL_DELIVERY_STATUS = "deliverystatus"; //Pending/Delivered
    public static final String PARCEL_DELIVERY_COMMENT = "deliverycomments";
    public static final String PARCEL_PAYMENT_TYPE = "payment_type";     //Prepaid/PostPaid
    public static final String PARCEL_PAYMENT_STATUS = "payment_status";
    public static final String PARCEL_TYPE = "parcel_type";               // BAG/Box,Letter
    public static final String PARCEL_AMOUNT = "amount";
    public static final String PARCEL_CURRENCY = "currency";
    public static final String PARCEL_DATE = "date";

    public static final String SOURCE_ADDRESS1 = "source_address1";
    public static final String SOURCE_ADDRESS2 = "source_address2";
    public static final String SOURCE_CITY = "source_city";
    public static final String SOURCE_STATE = "source_state";
    public static final String SOURCE_PIN = "source_pin";
    public static final String SOURCE_PHONE = "source_phone";

    public static final String DELIVERY_ADDRESS1 = "delivery_address1";
    public static final String DELIVERY_ADDRESS2 = "delivery_address2";
    public static final String DELIVERY_CITY = "delivery_city";
    public static final String DELIVERY_STATE = "delivery_state";
    public static final String DELIVERY_PIN = "delivery_pin";
    public static final String DELIVERY_PHONE = "delivery_phone";

    public static final String UPDATE_STATUS = "update_status";
    public static final String UPDATE_DATE = "update_date";
    public static final String TRANSCROWID = "trans_row_id";

    /*===========POD TABLE============================*/
    public static final String TABLE_NAME_POD = "PROOF_OF_DELIVERY";

    public static final String POD_ROW_ID = "pod_id";
    public static final String POD_NAME = "pod_name";
    public static final String POD_STATUS = "pod_status";
    public static final String POD_CREATED_TIME = "pod_created_time";
    public static final String POD_UPDATED_TIME = "pod_updated_time";
    public static final String POD_NAME_ON_SERVER = "pod_name_on_server";

    /*============Transaction Table==========*/
    public static final String TABLE_NAME_TRANSACTION = "TRANSCTABLE";

    public static final String TRANS_ID = "transId";
    public static final String TRANS_TYPE = "transType";
    public static final String TRANS_TIME_STAMP = "transTimeStamp";
    public static final String TRANS_RECEIVER_NAME = "receiver_name";
    public static final String TRANS_TOTAL_AMOUNT = "total_amt";
    public static final String TRANS_CURRENCY = "currency";

    /*============Status Table==========*/
    public static final String TABLE_NAME_STATUS = "STATUS";

    public static final String STATUS_TYPE = "statusType"; //Pending/Delivered/Closed
    public static final String STATUS_COMMENT = "statusComment";
    public static final String STATUS_TIME_STAMP = "statusTimeStamp";


    //Put Barcode in each table






}

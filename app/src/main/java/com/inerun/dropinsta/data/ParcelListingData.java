package com.inerun.dropinsta.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by vineet on 11/29/2016.
 */

public class ParcelListingData implements Serializable {
    private boolean status;
    private String message;
    private long delivered_num;
    private long pending_num;
    private ArrayList<ParcelData> deliveryData;
    private ArrayList<TransactionData> transdata;

    public long getDelivered_num() {
        return delivered_num;
    }

    public ArrayList<TransactionData> getTransdata() {
        return transdata;
    }

    public long getPending_num() {
        return pending_num;
    }

    public ArrayList<ParcelData> getDeliveryData() {
        return deliveryData;
    }

    public ParcelListingData(long delivered_num, long pending_num) {
        this.delivered_num = delivered_num;
        this.pending_num = pending_num;
    }

    public ParcelListingData(long delivered_num, long pending_num, ArrayList<ParcelData> deliveryData) {
        this.delivered_num = delivered_num;
        this.pending_num = pending_num;
        this.deliveryData = deliveryData;
    }

    public ParcelListingData() {
    }

    public class ParcelData implements Serializable {
        private int column_id;
        private String barcode;
        private String custid;
        private String name;
        private String weight;
        private String specialinstructions;
        private int deliverystatus;
        private String deliverycomments;
        private int payment_type;
        private int payment_status;
        private String parcel_type;
        private String amount;
        private String currency;
        private String date;

        private String source_address1;
        private String source_address2;
        private String source_city;
        private String source_state;
        private String source_pin;
        private String source_phone;

        private String delivery_address1;
        private String delivery_address2;
        private String delivery_city;
        private String delivery_state;
        private String delivery_pin;
        private String delivery_phone;
        private String reciever_name;
        private String transaction_timestamp;
        private String transc_type;


        public ParcelData(String barcode, String amount, String currency) {
            this.barcode = barcode;
            this.amount = amount;
            this.currency = currency;
        }

        private String transcid;
        private String rackno;
        private String binno;


        private boolean isselected;


        public final static int DELIVERED = 9;


        public final static int PENDING = 12;
        public final static int ATTEMPTED = 14;
        public final static int TRANSACTION_CARD = 1;
        public final static int TRANSACTION_CASH = 2;

        public ArrayList<ParcelStatus> status;


//

        public String deliveryAddress;


        public ParcelData(String barcode, String specialinstructions, int deliverystatus, String
                amount, String currency, String date, String delivery_address1, String
                                  delivery_address2, String delivery_city, String delivery_state, String
                                  delivery_pin, String delivery_phone) {
            this.barcode = barcode;
            this.specialinstructions = specialinstructions;
            this.deliverystatus = deliverystatus;
            this.amount = amount;
            this.currency = currency;
            this.date = date;
            this.delivery_address1 = delivery_address1;
            this.delivery_address2 = delivery_address2;
            this.delivery_city = delivery_city;
            this.delivery_state = delivery_state;
            this.delivery_pin = delivery_pin;
            this.delivery_phone = delivery_phone;
        }


        public ParcelData(int column_id, String barcode, String name, String weight, String
                specialinstructions, int deliverystatus, String deliverycomments, int payment_type,
                          int payment_status, String parcel_type, String amount, String currency, String
                                  date, String source_address1, String source_address2, String source_city, String
                                  source_state, String source_pin, String source_phone, String delivery_address1, String
                                  delivery_address2, String delivery_city, String delivery_state, String
                                  delivery_pin, String delivery_phone,String custid) {
            this.column_id = column_id;
            this.barcode = barcode;
            this.name = name;
            this.weight = weight;
            this.specialinstructions = specialinstructions;
            this.deliverystatus = deliverystatus;
            this.deliverycomments = deliverycomments;
            this.payment_type = payment_type;
            this.payment_status = payment_status;
            this.parcel_type = parcel_type;
            this.amount = amount;
            this.currency = currency;
            this.date = date;
            this.source_address1 = source_address1;
            this.source_address2 = source_address2;
            this.source_city = source_city;
            this.source_state = source_state;
            this.source_pin = source_pin;
            this.source_phone = source_phone;
            this.delivery_address1 = delivery_address1;
            this.delivery_address2 = delivery_address2;
            this.delivery_city = delivery_city;
            this.delivery_state = delivery_state;
            this.delivery_pin = delivery_pin;
            this.delivery_phone = delivery_phone;
            this.custid = custid;
        }

        public int getColumn_id() {
            return column_id;
        }

        public String getBarcode() {
            return barcode;
        }

        public String getName() {
            return name;
        }

        public String getWeight() {
            return weight;
        }

        public String getSpecialinstructions() {
            return specialinstructions;
        }

        public int getDeliverystatus() {
            return deliverystatus;
        }

        public String getDeliverycomments() {
            return deliverycomments;
        }

        public void setDeliverycomments(String deliverycomments) {
            this.deliverycomments = deliverycomments;
        }

        public int getPayment_type() {
            return payment_type;
        }
        public String getCustid() {
            return custid;
        }

        public int getPayment_status() {
            return payment_status;
        }

        public String getParcel_type() {
            return parcel_type;
        }

        public String getAmount() {
            return amount;
        }

        public String getCurrency() {
            return currency;
        }

        public String getDate() {
            return date;
        }

        public String getSource_address1() {
            return source_address1;
        }

        public String getSource_address2() {
            return source_address2;
        }

        public String getSource_city() {
            return source_city;
        }

        public String getSource_state() {
            return source_state;
        }

        public String getSource_pin() {
            return source_pin;
        }

        public String getSource_phone() {
            return source_phone;
        }

        public String getDelivery_address1() {
            return delivery_address1;
        }

        public String getDelivery_address2() {
            return delivery_address2;
        }

        public String getDelivery_city() {
            return delivery_city;
        }

        public String getDelivery_state() {
            return delivery_state;
        }

        public String getDelivery_pin() {
            return delivery_pin;
        }

        public String getDelivery_phone() {
            return delivery_phone;
        }


        public boolean isDelivered() {
            return deliverystatus == DELIVERED;
        }

        public boolean isselected() {
            return isselected;
        }

        public void setIsselected(boolean isselected) {
            this.isselected = isselected;
        }


        public String getRackno() {
            return rackno;
        }

        public String getBinno() {
            return binno;
        }

        public String getReciever_name() {
            return reciever_name;
        }

        public String getTransaction_timestamp() {
            return transaction_timestamp;
        }

        public String getTransc_type() {
            return transc_type;
        }

        public String getTranscid() {
            return transcid;
        }

        public ArrayList<ParcelStatus> getStatus() {
            return status;
        }

        public String getDeliveryAddress() {

            String add = "";

            if (getDelivery_address1() != null && getDelivery_address1().trim().length() > 0) {
                add = getDelivery_address1() + ", ";
            }
            if (getDelivery_address2() != null && getDelivery_address2().trim().length() > 0) {
                add = add + getDelivery_address2() + ", ";
            }
            if (getDelivery_city() != null && getDelivery_city().trim().length() > 0) {
                add = add + getDelivery_city() + ", ";
            }
            if (getDelivery_state() != null && getDelivery_state().trim().length() > 0) {
                add = add + getDelivery_state() + ", ";
            }
            if (getDelivery_pin() != null && getDelivery_pin().trim().length() > 0) {
                add = getDelivery_pin() + ", ";
            }

//            if(add.length() == add.lastIndexOf(",") + 1){
//                add = add.substring(0, add.lastIndexOf(","));
//            }

            return add;
        }public String getSourceAddress() {

            String add = "";

            if (getSource_address1() != null && getSource_address1().trim().length() > 0) {
                add = getSource_address1() + ", ";
            }
            if (getSource_address2() != null && getSource_address2().trim().length() > 0) {
                add = add + getSource_address2() + ", ";
            }
            if (getSource_city() != null && getSource_city().trim().length() > 0) {
                add = add + getSource_city() + ", ";
            }
            if (getSource_state() != null && getSource_state().trim().length() > 0) {
                add = add + getSource_state() + ", ";
            }
            if (getSource_pin() != null && getSource_pin().trim().length() > 0) {
                add = getSource_pin() + ", ";
            }

//            if(add.length() == add.lastIndexOf(",") + 1){
//                add = add.substring(0, add.lastIndexOf(","));
//            }

            return add;
        }

        public void setColumn_id(int column_id) {
            this.column_id = column_id;
        }
    }
}

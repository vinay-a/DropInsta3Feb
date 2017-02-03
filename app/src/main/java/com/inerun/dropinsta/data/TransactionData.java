package com.inerun.dropinsta.data;

/**
 * Created by vinay on 23/01/17.
 */

public class TransactionData {
    boolean iscard;
    String totalamount, transcid, collectedby,currency;
    String barcode;
    String transtype;
    String transTimeStamp;

    public TransactionData(boolean iscard, String totalamount, String transcid, String collectedby, String currency, String barcode,String transtype,String transTimeStamp) {
        this.iscard = iscard;
        this.totalamount = totalamount;
        this.transcid = transcid;
        this.collectedby = collectedby;
        this.currency = currency;
        this.barcode = barcode;
        this.transtype = transtype;
        this.transTimeStamp = transTimeStamp;

    }

    public String getCurrency() {
        return currency;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getTranstype() {
        return transtype;
    }

    public String getTransTimeStamp() {
        return transTimeStamp;
    }

    public TransactionData(boolean iscard, String totalamount, String transcid, String collectedby) {
        this.iscard = iscard;
        this.totalamount = totalamount;
        this.transcid = transcid;

        this.collectedby = collectedby;
    }

    public boolean iscard() {
        return iscard;
    }

    public void setIscard(boolean iscard) {
        this.iscard = iscard;
    }



    public String getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(String totalamount) {
        this.totalamount = totalamount;
    }

    public String getTranscid() {
        return transcid;
    }

    public void setTranscid(String transcid) {
        this.transcid = transcid;
    }

    public String getCollectedby() {
        return collectedby;
    }

    public void setCollectedby(String collectedby) {
        this.collectedby = collectedby;
    }
}

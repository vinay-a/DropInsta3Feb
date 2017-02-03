package com.inerun.dropinsta.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by vineet on 12/2/2016.
 */

public class UpdatedParcelData implements Serializable {

    private String barcode;
    private int deliverystatus;
    private String deliverycomments;
    private int payment_status;
    private String update_date;
    private String pod_name_on_server;
    private String receiver_name;
    private ArrayList<ParcelStatus> statusdata;

    public UpdatedParcelData(String barcode, int deliverystatus, String deliverycomments, int payment_status, String update_date, String pod_name_on_server, String receiver_name,ArrayList<ParcelStatus> statusdata) {
        this.barcode = barcode;
        this.deliverystatus = deliverystatus;
        this.deliverycomments = deliverycomments;
        this.payment_status = payment_status;
        this.update_date = update_date;
        this.pod_name_on_server = pod_name_on_server;
        this.receiver_name = receiver_name;
        this.statusdata = statusdata;
    }
    public UpdatedParcelData(String barcode, int deliverystatus, String deliverycomments, int payment_status, String update_date, String pod_name_on_server, String receiver_name) {
        this.barcode = barcode;
        this.deliverystatus = deliverystatus;
        this.deliverycomments = deliverycomments;
        this.payment_status = payment_status;
        this.update_date = update_date;
        this.pod_name_on_server = pod_name_on_server;
        this.receiver_name = receiver_name;
        this.statusdata = statusdata;
    }

}

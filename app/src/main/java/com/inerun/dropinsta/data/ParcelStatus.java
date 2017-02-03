package com.inerun.dropinsta.data;

import com.inerun.dropinsta.constant.AppConstant;
import com.inerun.dropinsta.helper.DIHelper;

/**
 * Created by vinay on 19/01/17.
 */

public class ParcelStatus {

    private String status_type;
    private String status_comment;
    private String status_timestamp;
    private String barcode;

    public ParcelStatus(String status_type, String status_comment, String barcode, String status_timestamp) {
        this.status_type = status_type;
        this.status_comment = status_comment;
        this.barcode = barcode;
        this.status_timestamp = status_timestamp;
    }

    public ParcelStatus(String status_type, String status_comment) {
        this.status_type = status_type;
        this.status_comment = status_comment;
        this.status_timestamp = DIHelper.getDateTime(AppConstant.DATEIME_FORMAT);
    }


    public String getStatus_type() {
        return status_type;
    }

    public String getStatus_comment() {
        return status_comment;
    }

    public String getStatus_timestamp() {
        return status_timestamp;
    }
}

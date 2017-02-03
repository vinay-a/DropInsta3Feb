package com.inerun.dropinsta.data;

/**
 * Created by vineet on 12/19/2016.
 */

public class StatusData {

    private String value;
    private String lable;

    public StatusData(String value, String lable) {
        this.value = value;
        this.lable = lable;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }
}

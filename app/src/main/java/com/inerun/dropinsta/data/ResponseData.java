package com.inerun.dropinsta.data;

import org.json.JSONObject;

public class ResponseData {

    /**
     * Class to define Response DTO
     */
    public static final int SUCCESS = 1;
    public static final int FAIL = 1;
    JSONObject json;
    private int result = FAIL;
    private String message;
    private Exception exception;
    private Object object;


    public JSONObject getJson() {
        return json;
    }

    public void setJson(JSONObject json) {
        this.json = json;
    }

    /**
     * @return the result
     */
    public int getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(int result) {
        this.result = result;
    }

    /**
     * @param message the message to set
     */
    public void setDesc(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    /*
     * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

}

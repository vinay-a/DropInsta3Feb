package com.inerun.dropinsta.Exception;
public class ServerException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static String message = "";

    public static int errorCode = 0;

    public ServerException(String message) {
        ServerException.message = message;
    }

    public ServerException(int errorCode) {
        ServerException.errorCode = errorCode;
    }

    public String toString() {
        return "Server Error: - Unable to fetch data";
    }

}

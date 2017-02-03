package com.inerun.dropinsta.Exception;
public class NetworkException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 3785433837426670516L;

    public String toString() {
        return "Network Error: - Either Internet Connectivity Lost or URL unavailable";
    }


}

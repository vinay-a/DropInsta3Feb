package com.inerun.dropinsta.data;

/**
 * Created by vineet on 12/10/2016.
 */

public class POD {
    private int id;
    private String path;
    private int status;
    private String name;
    private String receiverName;
    private String podNameOnServer;

    public POD() {
    }

    public POD(int id, String name, int status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public POD(int id, String path) {
        this.id = id;
        this.path = path;
    }

    public POD(String name, String receiverName) {
        this.name = name;
        this.receiverName = receiverName;
    }

    public POD(int id, String receiverName, String podNameOnServer, String name) {
        this.id = id;
        this.receiverName = receiverName;
        this.podNameOnServer = podNameOnServer;
        this.name = name;
    }public POD(int id, String podNameOnServer, String name) {
        this.id = id;

        this.podNameOnServer = podNameOnServer;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getPodNameOnServer() {
        return podNameOnServer;
    }
}

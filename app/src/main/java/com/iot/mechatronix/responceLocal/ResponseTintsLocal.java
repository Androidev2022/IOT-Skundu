package com.iot.mechatronix.responceLocal;

public class ResponseTintsLocal {
    private int id;
    private String tintName;

    private String tintCostLtr;

    private String tintStatus;

    public ResponseTintsLocal(int id, String tintName, String tintCostLtr, String tintStatus) {
        this.id = id;
        this.tintName = tintName;
        this.tintCostLtr = tintCostLtr;
        this.tintStatus = tintStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTintName() {
        return tintName;
    }

    public void setTintName(String tintName) {
        this.tintName = tintName;
    }

    public String getTintCostLtr() {
        return tintCostLtr;
    }

    public void setTintCostLtr(String tintCostLtr) {
        this.tintCostLtr = tintCostLtr;
    }

    public String getTintStatus() {
        return tintStatus;
    }

    public void setTintStatus(String tintStatus) {
        this.tintStatus = tintStatus;
    }
}

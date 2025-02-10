package com.iot.mechatronix.responceLocal;

public class ResponseProductPackLocal {
    private int id;
    private String name;

    private String size;
    private String amount;


    public ResponseProductPackLocal(int id, String name, String size, String amount) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}

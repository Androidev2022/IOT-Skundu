package com.iot.mechatronix.responceLocal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseHistory {
    @SerializedName("error")
    @Expose
    public Boolean error;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("products")
    @Expose
    public List<Product> products;

    public ResponseHistory(Boolean error, String message, List<Product> products) {
        this.error = error;
        this.message = message;
        this.products = products;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public class Product {

        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("product_name")
        @Expose
        public String productName;
        @SerializedName("color_name")
        @Expose
        public String colorName;
        @SerializedName("color_code")
        @Expose
        public String colorCode;
        @SerializedName("Date")
        @Expose
        public String date;
        @SerializedName("time")
        @Expose
        public String time;
        @SerializedName("customer_name")
        @Expose
        public String customerName;
        @SerializedName("painter")
        @Expose
        public String painter;
        @SerializedName("packsize")
        @Expose
        public String packsize;
        @SerializedName("no_of_can")
        @Expose
        public String noOfCan;
        @SerializedName("address")
        @Expose
        public String address;
        @SerializedName("city")
        @Expose
        public String city;
        @SerializedName("pin")
        @Expose
        public String pin;
        @SerializedName("phone")
        @Expose
        public String phone;
        @SerializedName("email")
        @Expose
        public String email;
        @SerializedName("base_price")
        @Expose
        public String basePrice;
        @SerializedName("colorent_price")
        @Expose
        public String colorentPrice;
        @SerializedName("margin")
        @Expose
        public String margin;
        @SerializedName("gst")
        @Expose
        public String gst;
        @SerializedName("total")
        @Expose
        public String total;

        public Product(Integer id, String productName, String colorName, String colorCode, String date, String time, String customerName, String painter, String packsize, String noOfCan, String address, String city, String pin, String phone, String email, String basePrice, String colorentPrice, String margin, String gst, String total) {
            this.id = id;
            this.productName = productName;
            this.colorName = colorName;
            this.colorCode = colorCode;
            this.date = date;
            this.time = time;
            this.customerName = customerName;
            this.painter = painter;
            this.packsize = packsize;
            this.noOfCan = noOfCan;
            this.address = address;
            this.city = city;
            this.pin = pin;
            this.phone = phone;
            this.email = email;
            this.basePrice = basePrice;
            this.colorentPrice = colorentPrice;
            this.margin = margin;
            this.gst = gst;
            this.total = total;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getColorName() {
            return colorName;
        }

        public void setColorName(String colorName) {
            this.colorName = colorName;
        }

        public String getColorCode() {
            return colorCode;
        }

        public void setColorCode(String colorCode) {
            this.colorCode = colorCode;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getPainter() {
            return painter;
        }

        public void setPainter(String painter) {
            this.painter = painter;
        }

        public String getPacksize() {
            return packsize;
        }

        public void setPacksize(String packsize) {
            this.packsize = packsize;
        }

        public String getNoOfCan() {
            return noOfCan;
        }

        public void setNoOfCan(String noOfCan) {
            this.noOfCan = noOfCan;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getPin() {
            return pin;
        }

        public void setPin(String pin) {
            this.pin = pin;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getBasePrice() {
            return basePrice;
        }

        public void setBasePrice(String basePrice) {
            this.basePrice = basePrice;
        }

        public String getColorentPrice() {
            return colorentPrice;
        }

        public void setColorentPrice(String colorentPrice) {
            this.colorentPrice = colorentPrice;
        }

        public String getMargin() {
            return margin;
        }

        public void setMargin(String margin) {
            this.margin = margin;
        }

        public String getGst() {
            return gst;
        }

        public void setGst(String gst) {
            this.gst = gst;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }
    }
}

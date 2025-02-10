package com.iot.mechatronix.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReesponceFetchShadeCard {
    @SerializedName("error")
    @Expose
    public Boolean error;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public Data data;

    public ReesponceFetchShadeCard(Boolean error, String message, Data data) {
        this.error = error;
        this.message = message;
        this.data = data;
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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        @SerializedName("products_shade_cards")
        @Expose
        public List<ProductsShadeCard> productsShadeCards;

        public Data(List<ProductsShadeCard> productsShadeCards) {
            this.productsShadeCards = productsShadeCards;
        }

        public List<ProductsShadeCard> getProductsShadeCards() {
            return productsShadeCards;
        }

        public void setProductsShadeCards(List<ProductsShadeCard> productsShadeCards) {
            this.productsShadeCards = productsShadeCards;
        }

        public class ProductsShadeCard {

            @SerializedName("id")
            @Expose
            public Integer id;
            @SerializedName("product_id")
            @Expose
            public Integer productId;
            @SerializedName("shade_card_name")
            @Expose
            public String shadeCardName;

            public ProductsShadeCard(Integer id, Integer productId, String shadeCardName) {
                this.id = id;
                this.productId = productId;
                this.shadeCardName = shadeCardName;
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public Integer getProductId() {
                return productId;
            }

            public void setProductId(Integer productId) {
                this.productId = productId;
            }

            public String getShadeCardName() {
                return shadeCardName;
            }

            public void setShadeCardName(String shadeCardName) {
                this.shadeCardName = shadeCardName;
            }
        }
    }
}

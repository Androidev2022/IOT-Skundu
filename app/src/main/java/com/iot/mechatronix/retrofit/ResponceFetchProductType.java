package com.iot.mechatronix.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponceFetchProductType {

    @SerializedName("error")
    @Expose
    public Boolean error;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public Data data;

    public ResponceFetchProductType(Boolean error, String message, Data data) {
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

        @SerializedName("categories")
        @Expose
        public List<Category> categories;

        public Data(List<Category> categories) {
            this.categories = categories;
        }

        public List<Category> getCategories() {
            return categories;
        }

        public void setCategories(List<Category> categories) {
            this.categories = categories;
        }

        public class Category {

            @SerializedName("id")
            @Expose
            public Integer id;
            @SerializedName("product_category_name")
            @Expose
            public String productCategoryName;
            @SerializedName("products")
            @Expose
            public List<Product> products;

            public Category(Integer id, String productCategoryName, List<Product> products) {
                this.id = id;
                this.productCategoryName = productCategoryName;
                this.products = products;
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getProductCategoryName() {
                return productCategoryName;
            }

            public void setProductCategoryName(String productCategoryName) {
                this.productCategoryName = productCategoryName;
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
                @SerializedName("pivot")
                @Expose
                public Pivot pivot;

                public Product(Integer id, String productName, Pivot pivot) {
                    this.id = id;
                    this.productName = productName;
                    this.pivot = pivot;
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

                public Pivot getPivot() {
                    return pivot;
                }

                public void setPivot(Pivot pivot) {
                    this.pivot = pivot;
                }
                public class Pivot {

                    @SerializedName("product_category_id")
                    @Expose
                    public Integer productCategoryId;
                    @SerializedName("product_id")
                    @Expose
                    public Integer productId;

                    public Pivot(Integer productCategoryId, Integer productId) {
                        this.productCategoryId = productCategoryId;
                        this.productId = productId;
                    }

                    public Integer getProductCategoryId() {
                        return productCategoryId;
                    }

                    public void setProductCategoryId(Integer productCategoryId) {
                        this.productCategoryId = productCategoryId;
                    }

                    public Integer getProductId() {
                        return productId;
                    }

                    public void setProductId(Integer productId) {
                        this.productId = productId;
                    }
                }
            }
        }
    }
}

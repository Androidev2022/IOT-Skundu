package com.iot.mechatronix.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReesponceFetchProductName {
    @SerializedName("error")
    @Expose
    public Boolean error;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public Data data;

    public ReesponceFetchProductName(Boolean error, String message, Data data) {
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

        @SerializedName("products")
        @Expose
        public List<Product> products;

        public Data(List<Product> products) {
            this.products = products;
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
            @SerializedName("categories")
            @Expose
            public List<Category> categories;


            public Product(Integer id, String productName, List<Category> categories) {
                this.id = id;
                this.productName = productName;
                this.categories = categories;
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
                @SerializedName("user_id")
                @Expose
                public Integer userId;
                @SerializedName("product_category_name")
                @Expose
                public String productCategoryName;
                @SerializedName("status")
                @Expose
                public Integer status;
                @SerializedName("created_at")
                @Expose
                public String createdAt;
                @SerializedName("updated_at")
                @Expose
                public String updatedAt;
                @SerializedName("pivot")
                @Expose
                public Pivot pivot;

                public Category(Integer id, Integer userId, String productCategoryName, Integer status, String createdAt, String updatedAt, Pivot pivot) {
                    this.id = id;
                    this.userId = userId;
                    this.productCategoryName = productCategoryName;
                    this.status = status;
                    this.createdAt = createdAt;
                    this.updatedAt = updatedAt;
                    this.pivot = pivot;
                }

                public Integer getId() {
                    return id;
                }

                public void setId(Integer id) {
                    this.id = id;
                }

                public Integer getUserId() {
                    return userId;
                }

                public void setUserId(Integer userId) {
                    this.userId = userId;
                }

                public String getProductCategoryName() {
                    return productCategoryName;
                }

                public void setProductCategoryName(String productCategoryName) {
                    this.productCategoryName = productCategoryName;
                }

                public Integer getStatus() {
                    return status;
                }

                public void setStatus(Integer status) {
                    this.status = status;
                }

                public String getCreatedAt() {
                    return createdAt;
                }

                public void setCreatedAt(String createdAt) {
                    this.createdAt = createdAt;
                }

                public String getUpdatedAt() {
                    return updatedAt;
                }

                public void setUpdatedAt(String updatedAt) {
                    this.updatedAt = updatedAt;
                }

                public Pivot getPivot() {
                    return pivot;
                }

                public void setPivot(Pivot pivot) {
                    this.pivot = pivot;
                }

                public class Pivot {

                    @SerializedName("product_id")
                    @Expose
                    public Integer productId;
                    @SerializedName("product_category_id")
                    @Expose
                    public Integer productCategoryId;

                    public Pivot(Integer productId, Integer productCategoryId) {
                        this.productId = productId;
                        this.productCategoryId = productCategoryId;
                    }

                    public Integer getProductId() {
                        return productId;
                    }

                    public void setProductId(Integer productId) {
                        this.productId = productId;
                    }

                    public Integer getProductCategoryId() {
                        return productCategoryId;
                    }

                    public void setProductCategoryId(Integer productCategoryId) {
                        this.productCategoryId = productCategoryId;
                    }
                }
            }
        }
    }
}

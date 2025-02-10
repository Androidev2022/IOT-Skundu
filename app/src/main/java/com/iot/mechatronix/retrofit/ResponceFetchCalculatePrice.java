package com.iot.mechatronix.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponceFetchCalculatePrice {

    @SerializedName("error")
    @Expose
    public Boolean error;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public Data data;

    public ResponceFetchCalculatePrice(Boolean error, String message, Data data) {
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

        @SerializedName("colour")
        @Expose
        public Colour colour;
        @SerializedName("margin")
        @Expose
        public Integer margin;
        @SerializedName("tax")
        @Expose
        public Integer tax;
        @SerializedName("base")
        @Expose
        public Base base;
        @SerializedName("price")
        @Expose
        public Price price;

        public Data(Colour colour, Integer margin, Integer tax, Base base, Price price) {
            this.colour = colour;
            this.margin = margin;
            this.tax = tax;
            this.base = base;
            this.price = price;
        }

        public Colour getColour() {
            return colour;
        }

        public void setColour(Colour colour) {
            this.colour = colour;
        }

        public Integer getMargin() {
            return margin;
        }

        public void setMargin(Integer margin) {
            this.margin = margin;
        }

        public Integer getTax() {
            return tax;
        }

        public void setTax(Integer tax) {
            this.tax = tax;
        }

        public Base getBase() {
            return base;
        }

        public void setBase(Base base) {
            this.base = base;
        }

        public Price getPrice() {
            return price;
        }

        public void setPrice(Price price) {
            this.price = price;
        }

        public class Colour {

            @SerializedName("id")
            @Expose
            public Integer id;
            @SerializedName("user_id")
            @Expose
            public Integer userId;
            @SerializedName("colour_name")
            @Expose
            public String colourName;
            @SerializedName("colour_code")
            @Expose
            public String colourCode;
            @SerializedName("colour_hex")
            @Expose
            public Object colourHex;
            @SerializedName("tint_type_1")
            @Expose
            public Integer tintType1;
            @SerializedName("tint_type_1_value")
            @Expose
            public Integer tintType1Value;
            @SerializedName("tint_type_2")
            @Expose
            public Integer tintType2;
            @SerializedName("tint_type_2_value")
            @Expose
            public Integer tintType2Value;
            @SerializedName("tint_type_3")
            @Expose
            public Integer tintType3;
            @SerializedName("tint_type_3_value")
            @Expose
            public Integer tintType3Value;
            @SerializedName("tint_type_4")
            @Expose
            public Integer tintType4;
            @SerializedName("tint_type_4_value")
            @Expose
            public Integer tintType4Value;
            @SerializedName("tint_type_5")
            @Expose
            public Integer tintType5;
            @SerializedName("tint_type_5_value")
            @Expose
            public Integer tintType5Value;

            public Colour(Integer id, Integer userId, String colourName, String colourCode, Object colourHex, Integer tintType1, Integer tintType1Value, Integer tintType2, Integer tintType2Value, Integer tintType3, Integer tintType3Value, Integer tintType4, Integer tintType4Value, Integer tintType5, Integer tintType5Value) {
                this.id = id;
                this.userId = userId;
                this.colourName = colourName;
                this.colourCode = colourCode;
                this.colourHex = colourHex;
                this.tintType1 = tintType1;
                this.tintType1Value = tintType1Value;
                this.tintType2 = tintType2;
                this.tintType2Value = tintType2Value;
                this.tintType3 = tintType3;
                this.tintType3Value = tintType3Value;
                this.tintType4 = tintType4;
                this.tintType4Value = tintType4Value;
                this.tintType5 = tintType5;
                this.tintType5Value = tintType5Value;
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

            public String getColourName() {
                return colourName;
            }

            public void setColourName(String colourName) {
                this.colourName = colourName;
            }

            public String getColourCode() {
                return colourCode;
            }

            public void setColourCode(String colourCode) {
                this.colourCode = colourCode;
            }

            public Object getColourHex() {
                return colourHex;
            }

            public void setColourHex(Object colourHex) {
                this.colourHex = colourHex;
            }

            public Integer getTintType1() {
                return tintType1;
            }

            public void setTintType1(Integer tintType1) {
                this.tintType1 = tintType1;
            }

            public Integer getTintType1Value() {
                return tintType1Value;
            }

            public void setTintType1Value(Integer tintType1Value) {
                this.tintType1Value = tintType1Value;
            }

            public Integer getTintType2() {
                return tintType2;
            }

            public void setTintType2(Integer tintType2) {
                this.tintType2 = tintType2;
            }

            public Integer getTintType2Value() {
                return tintType2Value;
            }

            public void setTintType2Value(Integer tintType2Value) {
                this.tintType2Value = tintType2Value;
            }

            public Integer getTintType3() {
                return tintType3;
            }

            public void setTintType3(Integer tintType3) {
                this.tintType3 = tintType3;
            }

            public Integer getTintType3Value() {
                return tintType3Value;
            }

            public void setTintType3Value(Integer tintType3Value) {
                this.tintType3Value = tintType3Value;
            }

            public Integer getTintType4() {
                return tintType4;
            }

            public void setTintType4(Integer tintType4) {
                this.tintType4 = tintType4;
            }

            public Integer getTintType4Value() {
                return tintType4Value;
            }

            public void setTintType4Value(Integer tintType4Value) {
                this.tintType4Value = tintType4Value;
            }

            public Integer getTintType5() {
                return tintType5;
            }

            public void setTintType5(Integer tintType5) {
                this.tintType5 = tintType5;
            }

            public Integer getTintType5Value() {
                return tintType5Value;
            }

            public void setTintType5Value(Integer tintType5Value) {
                this.tintType5Value = tintType5Value;
            }
        }

        public class Base {

            @SerializedName("id")
            @Expose
            public Integer id;
            @SerializedName("user_id")
            @Expose
            public Integer userId;
            @SerializedName("product_base_name")
            @Expose
            public String productBaseName;
            @SerializedName("product_base_code")
            @Expose
            public String productBaseCode;
            @SerializedName("status")
            @Expose
            public Integer status;
            @SerializedName("created_at")
            @Expose
            public String createdAt;
            @SerializedName("updated_at")
            @Expose
            public String updatedAt;
            @SerializedName("packs")
            @Expose
            public List<Pack> packs;

            public Base(Integer id, Integer userId, String productBaseName, String productBaseCode, Integer status, String createdAt, String updatedAt, List<Pack> packs) {
                this.id = id;
                this.userId = userId;
                this.productBaseName = productBaseName;
                this.productBaseCode = productBaseCode;
                this.status = status;
                this.createdAt = createdAt;
                this.updatedAt = updatedAt;
                this.packs = packs;
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

            public String getProductBaseName() {
                return productBaseName;
            }

            public void setProductBaseName(String productBaseName) {
                this.productBaseName = productBaseName;
            }

            public String getProductBaseCode() {
                return productBaseCode;
            }

            public void setProductBaseCode(String productBaseCode) {
                this.productBaseCode = productBaseCode;
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

            public List<Pack> getPacks() {
                return packs;
            }

            public void setPacks(List<Pack> packs) {
                this.packs = packs;
            }
            public class Pack {

                @SerializedName("id")
                @Expose
                public Integer id;
                @SerializedName("user_id")
                @Expose
                public Integer userId;
                @SerializedName("product_pack_name")
                @Expose
                public String productPackName;
                @SerializedName("product_pack_size")
                @Expose
                public Integer productPackSize;
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

                public Pack(Integer id, Integer userId, String productPackName, Integer productPackSize, Integer status, String createdAt, String updatedAt, Pivot pivot) {
                    this.id = id;
                    this.userId = userId;
                    this.productPackName = productPackName;
                    this.productPackSize = productPackSize;
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

                public String getProductPackName() {
                    return productPackName;
                }

                public void setProductPackName(String productPackName) {
                    this.productPackName = productPackName;
                }

                public Integer getProductPackSize() {
                    return productPackSize;
                }

                public void setProductPackSize(Integer productPackSize) {
                    this.productPackSize = productPackSize;
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

                    @SerializedName("base_id")
                    @Expose
                    public Integer baseId;
                    @SerializedName("pack_id")
                    @Expose
                    public Integer packId;
                    @SerializedName("cost")
                    @Expose
                    public Integer cost;

                    public Pivot(Integer baseId, Integer packId, Integer cost) {
                        this.baseId = baseId;
                        this.packId = packId;
                        this.cost = cost;
                    }

                    public Integer getBaseId() {
                        return baseId;
                    }

                    public void setBaseId(Integer baseId) {
                        this.baseId = baseId;
                    }

                    public Integer getPackId() {
                        return packId;
                    }

                    public void setPackId(Integer packId) {
                        this.packId = packId;
                    }

                    public Integer getCost() {
                        return cost;
                    }

                    public void setCost(Integer cost) {
                        this.cost = cost;
                    }
                }
            }
        }

        public class Price {

            @SerializedName("single_pack_base_price")
            @Expose
            public String singlePackBasePrice;
            @SerializedName("single_pack_tint_price")
            @Expose
            public String singlePackTintPrice;
            @SerializedName("single_pack_base_tint_price")
            @Expose
            public String singlePackBaseTintPrice;
            @SerializedName("single_pack_margin")
            @Expose
            public String singlePackMargin;
            @SerializedName("single_pack_tax")
            @Expose
            public String singlePackTax;
            @SerializedName("single_pack_total")
            @Expose
            public String singlePackTotal;
            @SerializedName("all_pack_base_price")
            @Expose
            public String allPackBasePrice;
            @SerializedName("all_pack_base_tint_price")
            @Expose
            public String allPackBaseTintPrice;
            @SerializedName("all_pack_total")
            @Expose
            public String allPackTotal;

            public Price(String singlePackBasePrice, String singlePackTintPrice, String singlePackBaseTintPrice, String singlePackMargin, String singlePackTax, String singlePackTotal, String allPackBasePrice, String allPackBaseTintPrice, String allPackTotal) {
                this.singlePackBasePrice = singlePackBasePrice;
                this.singlePackTintPrice = singlePackTintPrice;
                this.singlePackBaseTintPrice = singlePackBaseTintPrice;
                this.singlePackMargin = singlePackMargin;
                this.singlePackTax = singlePackTax;
                this.singlePackTotal = singlePackTotal;
                this.allPackBasePrice = allPackBasePrice;
                this.allPackBaseTintPrice = allPackBaseTintPrice;
                this.allPackTotal = allPackTotal;
            }

            public String getSinglePackBasePrice() {
                return singlePackBasePrice;
            }

            public void setSinglePackBasePrice(String singlePackBasePrice) {
                this.singlePackBasePrice = singlePackBasePrice;
            }

            public String getSinglePackTintPrice() {
                return singlePackTintPrice;
            }

            public void setSinglePackTintPrice(String singlePackTintPrice) {
                this.singlePackTintPrice = singlePackTintPrice;
            }

            public String getSinglePackBaseTintPrice() {
                return singlePackBaseTintPrice;
            }

            public void setSinglePackBaseTintPrice(String singlePackBaseTintPrice) {
                this.singlePackBaseTintPrice = singlePackBaseTintPrice;
            }

            public String getSinglePackMargin() {
                return singlePackMargin;
            }

            public void setSinglePackMargin(String singlePackMargin) {
                this.singlePackMargin = singlePackMargin;
            }

            public String getSinglePackTax() {
                return singlePackTax;
            }

            public void setSinglePackTax(String singlePackTax) {
                this.singlePackTax = singlePackTax;
            }

            public String getSinglePackTotal() {
                return singlePackTotal;
            }

            public void setSinglePackTotal(String singlePackTotal) {
                this.singlePackTotal = singlePackTotal;
            }

            public String getAllPackBasePrice() {
                return allPackBasePrice;
            }

            public void setAllPackBasePrice(String allPackBasePrice) {
                this.allPackBasePrice = allPackBasePrice;
            }

            public String getAllPackBaseTintPrice() {
                return allPackBaseTintPrice;
            }

            public void setAllPackBaseTintPrice(String allPackBaseTintPrice) {
                this.allPackBaseTintPrice = allPackBaseTintPrice;
            }

            public String getAllPackTotal() {
                return allPackTotal;
            }

            public void setAllPackTotal(String allPackTotal) {
                this.allPackTotal = allPackTotal;
            }
        }
    }
}

package com.iot.mechatronix.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReesponceFetchColorNameDB {
    @SerializedName("error")
    @Expose
    public Boolean error;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public Data data;

    public ReesponceFetchColorNameDB(Boolean error, String message, Data data) {
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

        @SerializedName("colours")
        @Expose
        public List<Colour> colours;

        public Data(List<Colour> colours) {
            this.colours = colours;
        }

        public List<Colour> getColours() {
            return colours;
        }

        public void setColours(List<Colour> colours) {
            this.colours = colours;
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
            public String colourHex;
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
            @SerializedName("status")
            @Expose
            public Integer status;
            @SerializedName("created_at")
            @Expose
            public String createdAt;
            @SerializedName("updated_at")
            @Expose
            public String updatedAt;
            @SerializedName("shade_cards")
            @Expose
            public List<ShadeCard> shadeCards;

            public Colour(Integer id, Integer userId, String colourName, String colourCode, String colourHex, Integer tintType1, Integer tintType1Value, Integer tintType2, Integer tintType2Value, Integer tintType3, Integer tintType3Value, Integer tintType4, Integer tintType4Value, Integer tintType5, Integer tintType5Value, Integer status, String createdAt, String updatedAt, List<ShadeCard> shadeCards) {
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
                this.status = status;
                this.createdAt = createdAt;
                this.updatedAt = updatedAt;
                this.shadeCards = shadeCards;
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

            public String getColourHex() {
                return colourHex;
            }

            public void setColourHex(String colourHex) {
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

            public List<ShadeCard> getShadeCards() {
                return shadeCards;
            }

            public void setShadeCards(List<ShadeCard> shadeCards) {
                this.shadeCards = shadeCards;
            }
            public class ShadeCard {

                @SerializedName("id")
                @Expose
                public Integer id;
                @SerializedName("user_id")
                @Expose
                public Integer userId;
                @SerializedName("product_id")
                @Expose
                public Integer productId;
                @SerializedName("shade_card_name")
                @Expose
                public String shadeCardName;
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

                public ShadeCard(Integer id, Integer userId, Integer productId, String shadeCardName, Integer status, String createdAt, String updatedAt, Pivot pivot) {
                    this.id = id;
                    this.userId = userId;
                    this.productId = productId;
                    this.shadeCardName = shadeCardName;
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

                    @SerializedName("colour_id")
                    @Expose
                    public Integer colourId;
                    @SerializedName("product_shade_card_id")
                    @Expose
                    public Integer productShadeCardId;

                    public Pivot(Integer colourId, Integer productShadeCardId) {
                        this.colourId = colourId;
                        this.productShadeCardId = productShadeCardId;
                    }

                    public Integer getColourId() {
                        return colourId;
                    }

                    public void setColourId(Integer colourId) {
                        this.colourId = colourId;
                    }

                    public Integer getProductShadeCardId() {
                        return productShadeCardId;
                    }

                    public void setProductShadeCardId(Integer productShadeCardId) {
                        this.productShadeCardId = productShadeCardId;
                    }
                }
            }
        }
    }
}

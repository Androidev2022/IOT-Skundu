package com.iot.mechatronix.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RresponceFetchBase {

    @SerializedName("error")
    @Expose
    public Boolean error;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public Data data;

    public RresponceFetchBase(Boolean error, String message, Data data) {
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

        @SerializedName("bases")
        @Expose
        public List<Basis> bases;

        public Data(List<Basis> bases) {
            this.bases = bases;
        }

        public List<Basis> getBases() {
            return bases;
        }

        public void setBases(List<Basis> bases) {
            this.bases = bases;
        }
        public class Basis {

            @SerializedName("id")
            @Expose
            public Integer id;
            @SerializedName("product_base_name")
            @Expose
            public String productBaseName;
            @SerializedName("product_base_code")
            @Expose
            public String productBaseCode;
            @SerializedName("packs")
            @Expose
            public List<Pack> packs;

            public Basis(Integer id, String productBaseName, String productBaseCode, List<Pack> packs) {
                this.id = id;
                this.productBaseName = productBaseName;
                this.productBaseCode = productBaseCode;
                this.packs = packs;
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
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
                @SerializedName("product_pack_name")
                @Expose
                public String productPackName;
                @SerializedName("product_pack_size")
                @Expose
                public Integer productPackSize;
                @SerializedName("pivot")
                @Expose
                public Pivot pivot;

                public Pack(Integer id, String productPackName, Integer productPackSize, Pivot pivot) {
                    this.id = id;
                    this.productPackName = productPackName;
                    this.productPackSize = productPackSize;
                    this.pivot = pivot;
                }

                public Integer getId() {
                    return id;
                }

                public void setId(Integer id) {
                    this.id = id;
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


    }
}

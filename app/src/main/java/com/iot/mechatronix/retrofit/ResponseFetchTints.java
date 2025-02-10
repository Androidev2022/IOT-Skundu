package com.iot.mechatronix.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseFetchTints {
    @SerializedName("error")
    @Expose
    public Boolean error;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public Data data;

    public ResponseFetchTints(Boolean error, String message, Data data) {
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

        @SerializedName("tints")
        @Expose
        public List<Tint> tints;

        public Data(List<Tint> tints) {
            this.tints = tints;
        }

        public List<Tint> getTints() {
            return tints;
        }

        public void setTints(List<Tint> tints) {
            this.tints = tints;
        }

        public class Tint {

            @SerializedName("id")
            @Expose
            public Integer id;
            @SerializedName("user_id")
            @Expose
            public Integer userId;
            @SerializedName("row_order")
            @Expose
            public Integer rowOrder;
            @SerializedName("tint_name")
            @Expose
            public String tintName;
            @SerializedName("tint_cost_lt")
            @Expose
            public Integer tintCostLt;
            @SerializedName("status")
            @Expose
            public Integer status;

            public Tint(Integer id, Integer userId, Integer rowOrder, String tintName, Integer tintCostLt, Integer status) {
                this.id = id;
                this.userId = userId;
                this.rowOrder = rowOrder;
                this.tintName = tintName;
                this.tintCostLt = tintCostLt;
                this.status = status;
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

            public Integer getRowOrder() {
                return rowOrder;
            }

            public void setRowOrder(Integer rowOrder) {
                this.rowOrder = rowOrder;
            }

            public String getTintName() {
                return tintName;
            }

            public void setTintName(String tintName) {
                this.tintName = tintName;
            }

            public Integer getTintCostLt() {
                return tintCostLt;
            }

            public void setTintCostLt(Integer tintCostLt) {
                this.tintCostLt = tintCostLt;
            }

            public Integer getStatus() {
                return status;
            }

            public void setStatus(Integer status) {
                this.status = status;
            }
        }
    }
}

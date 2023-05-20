package cn.com.aratek.demo.featuresrequest;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataSn implements Serializable {
    public String getIs_online() {
        return is_online;
    }

    public DataSn(String  sn){
        this.is_online = sn;
    }

    public void setIs_online(String is_online) {
        this.is_online = is_online;
    }

    @SerializedName("is_online")
    public String is_online;
}

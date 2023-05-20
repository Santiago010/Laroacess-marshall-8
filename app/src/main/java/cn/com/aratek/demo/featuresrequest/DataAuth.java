package cn.com.aratek.demo.featuresrequest;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.String;

public class DataAuth implements Serializable {
  @SerializedName("auth_method")
  private String auth_method;

  @SerializedName("data")
  private String data;

  @SerializedName("sn")
  private String sn;

  public DataAuth(String sn,String auth_method,String data){
    this.auth_method =auth_method;
    this.data = data;
    this.sn= sn;
  }

  public String getAuth_method() {
    return this.auth_method;
  }

  public void setAuth_method(String auth_method) {
    this.auth_method = auth_method;
  }

  public String getData() {
    return this.data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public String getSn() {
    return this.sn;
  }

  public void setSn(String sn) {
    this.sn = sn;
  }
}

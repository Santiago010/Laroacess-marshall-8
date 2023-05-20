package cn.com.aratek.demo.featuresrequest;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.String;

public class DataAuthenticationRecord implements Serializable {

  @SerializedName("authentication_method")
  private String authentication_method;

  @SerializedName("reason")
  private String reason;

  @SerializedName("data")
  private String data;

  @SerializedName("sn")
  private String sn;

  public String getEmployee() {
    return employee;
  }

  public void setEmployee(String employee) {
    this.employee = employee;
  }

  @SerializedName("employee")
  private String employee;

  public DataAuthenticationRecord( String authentication_method, String reason,@Nullable String data, String sn,@Nullable String employee){
    this.authentication_method = authentication_method;
    this.reason = reason;
    this.data = data;
    this.sn= sn;
    this.employee = employee;
  }

  public String getAuthentication_method() {
    return this.authentication_method;
  }

  public void setAuthentication_method(String authentication_method) {
    this.authentication_method = authentication_method;
  }

  public String getReason() {
    return this.reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
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

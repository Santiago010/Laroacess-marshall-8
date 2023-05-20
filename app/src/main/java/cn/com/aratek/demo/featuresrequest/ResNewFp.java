package cn.com.aratek.demo.featuresrequest;

import java.io.Serializable;
import java.lang.String;

public class ResNewFp implements Serializable {
  private String createdAt;

  private String fingerprint;

  private String _id;

  private String employee;

  private String updatedAt;

  public String getCreatedAt() {
    return this.createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getFingerprint() {
    return this.fingerprint;
  }

  public void setFingerprint(String fingerprint) {
    this.fingerprint = fingerprint;
  }

  public String get_id() {
    return this._id;
  }

  public void set_id(String _id) {
    this._id = _id;
  }

  public String getEmployee() {
    return this.employee;
  }

  public void setEmployee(String employee) {
    this.employee = employee;
  }

  public String getUpdatedAt() {
    return this.updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }
}

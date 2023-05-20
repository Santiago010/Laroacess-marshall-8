package cn.com.aratek.demo.featuresrequest;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.String;

public class resSn implements Serializable {
  private Boolean err;

  private String message;

  public Boolean getErr() {
    return this.err;
  }

  public void setErr(Boolean err) {
    this.err = err;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}

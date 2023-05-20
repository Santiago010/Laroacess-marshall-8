package cn.com.aratek.demo.featuresrequest;

import java.io.Serializable;
import java.lang.String;

public class Token implements Serializable {
  private String token;

  public String getToken() {
    return this.token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}

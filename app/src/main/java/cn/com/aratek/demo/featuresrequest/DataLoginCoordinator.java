package cn.com.aratek.demo.featuresrequest;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.String;

public class DataLoginCoordinator implements Serializable {
  @SerializedName("password")
  private String password;

  @SerializedName("username")
  private String username;

  public  DataLoginCoordinator(String username,String password){
    this.username = username;
    this.password = password;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}

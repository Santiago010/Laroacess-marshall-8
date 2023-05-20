package cn.com.aratek.demo.featuresrequest;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.String;

public class ResListFp implements Serializable {
  private String createdAt;

  private String fingerprint;

  private String _id;

  private Employee employee;

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

  public Employee getEmployee() {
    return this.employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public String getUpdatedAt() {
    return this.updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  public static class Employee implements Serializable {
    private String country;

    private Boolean is_active;

    private String role;

    private String city;

    private String sub_company;

    private String first_category;

    private String contract_start_date;

    private String contract_end_date;

    private String phone;

    private String _id;

    private String dni_type;

    private String first_name;

    private String dni;

    private String email;

    private String first_lastname;

    public String getCountry() {
      return this.country;
    }

    public void setCountry(String country) {
      this.country = country;
    }

    public Boolean getIs_active() {
      return this.is_active;
    }

    public void setIs_active(Boolean is_active) {
      this.is_active = is_active;
    }

    public String getRole() {
      return this.role;
    }

    public void setRole(String role) {
      this.role = role;
    }

    public String getCity() {
      return this.city;
    }

    public void setCity(String city) {
      this.city = city;
    }

    public String getSub_company() {
      return this.sub_company;
    }

    public void setSub_company(String sub_company) {
      this.sub_company = sub_company;
    }

    public String getFirst_category() {
      return this.first_category;
    }

    public void setFirst_category(String first_category) {
      this.first_category = first_category;
    }

    public String getContract_start_date() {
      return this.contract_start_date;
    }

    public void setContract_start_date(String contract_start_date) {
      this.contract_start_date = contract_start_date;
    }

    public String getContract_end_date() {
      return this.contract_end_date;
    }

    public void setContract_end_date(String contract_end_date) {
      this.contract_end_date = contract_end_date;
    }

    public String getPhone() {
      return this.phone;
    }

    public void setPhone(String phone) {
      this.phone = phone;
    }

    public String get_id() {
      return this._id;
    }

    public void set_id(String _id) {
      this._id = _id;
    }

    public String getDni_type() {
      return this.dni_type;
    }

    public void setDni_type(String dni_type) {
      this.dni_type = dni_type;
    }

    public String getFirst_name() {
      return this.first_name;
    }

    public void setFirst_name(String first_name) {
      this.first_name = first_name;
    }

    public String getDni() {
      return this.dni;
    }

    public void setDni(String dni) {
      this.dni = dni;
    }

    public String getEmail() {
      return this.email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public String getFirst_lastname() {
      return this.first_lastname;
    }

    public void setFirst_lastname(String first_lastname) {
      this.first_lastname = first_lastname;
    }
  }
}

package cn.com.aratek.demo.featuresrequest;

import java.io.Serializable;
import java.lang.String;

public class ResListFpsDevice implements Serializable {
  private String fingerprint;

  private String _id;

  private Employee employee;

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

  public static class Employee implements Serializable {
    private City city;

    private City.Country campus;

    private City.Country sub_company;

    private City.Country first_category;

    private String contract_start_date;

    private String contract_end_date;

    private String phone;

    private City.Country company;

    private String _id;

    private City.Country dni_type;

    private String first_name;

    private String dni;

    private String email;

    private String first_lastname;

    public City getCity() {
      return this.city;
    }

    public void setCity(City city) {
      this.city = city;
    }

    public City.Country getCampus() {
      return this.campus;
    }

    public void setCampus(City.Country campus) {
      this.campus = campus;
    }

    public City.Country getSub_company() {
      return this.sub_company;
    }

    public void setSub_company(City.Country sub_company) {
      this.sub_company = sub_company;
    }

    public City.Country getFirst_category() {
      return this.first_category;
    }

    public void setFirst_category(City.Country first_category) {
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

    public City.Country getCompany() {
      return this.company;
    }

    public void setCompany(City.Country company) {
      this.company = company;
    }

    public String get_id() {
      return this._id;
    }

    public void set_id(String _id) {
      this._id = _id;
    }

    public City.Country getDni_type() {
      return this.dni_type;
    }

    public void setDni_type(City.Country dni_type) {
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

    public static class City implements Serializable {
      private Country country;

      private String name;

      private String _id;

      public Country getCountry() {
        return this.country;
      }

      public void setCountry(Country country) {
        this.country = country;
      }

      public String getName() {
        return this.name;
      }

      public void setName(String name) {
        this.name = name;
      }

      public String get_id() {
        return this._id;
      }

      public void set_id(String _id) {
        this._id = _id;
      }

      public static class Country implements Serializable {
        private String name;

        private String _id;

        public String getName() {
          return this.name;
        }

        public void setName(String name) {
          this.name = name;
        }

        public String get_id() {
          return this._id;
        }

        public void set_id(String _id) {
          this._id = _id;
        }
      }
    }
  }
}

package cn.com.aratek.demo.featuresrequest;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Object;
import java.lang.String;
import java.util.List;

public class ResAuth implements Serializable {
  @Nullable
  private Employee employee;

  private String message;

  @Nullable
  private Vehicle vehicle;

  public Employee getEmployee() {
    return this.employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Vehicle getVehicle() {
    return this.vehicle;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }

  public static class Employee implements Serializable {
    private String second_lastname;

    private Role role;

    private Boolean is_active;

    private City city;

    private Campus campus;

    private List<? extends Profile_picture> profile_picture;

    private String contract_start_date;

    private String contract_end_date;

    private String phone;

    private String employee_id;

    private String second_name;

    private String rfid;

    private String _id;

    private Role dni_type;

    private List<? extends Role> categories;

    private List<? extends Access_group> access_group;

    private String first_name;

    private String barcode;

    private String dni;

    private String email;

    private String first_lastname;

    public String getSecond_lastname() {
      return this.second_lastname;
    }

    public void setSecond_lastname(String second_lastname) {
      this.second_lastname = second_lastname;
    }

    public Role getRole() {
      return this.role;
    }

    public void setRole(Role role) {
      this.role = role;
    }

    public Boolean getIs_active() {
      return this.is_active;
    }

    public void setIs_active(Boolean is_active) {
      this.is_active = is_active;
    }

    public City getCity() {
      return this.city;
    }

    public void setCity(City city) {
      this.city = city;
    }

    public Campus getCampus() {
      return this.campus;
    }

    public void setCampus(Campus campus) {
      this.campus = campus;
    }

    public List<? extends Profile_picture> getProfile_picture() {
      return this.profile_picture;
    }

    public void setProfile_picture(List<? extends Profile_picture> profile_picture) {
      this.profile_picture = profile_picture;
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

    public String getEmployee_id() {
      return this.employee_id;
    }

    public void setEmployee_id(String employee_id) {
      this.employee_id = employee_id;
    }

    public String getSecond_name() {
      return this.second_name;
    }

    public void setSecond_name(String second_name) {
      this.second_name = second_name;
    }

    public String getRfid() {
      return this.rfid;
    }

    public void setRfid(String rfid) {
      this.rfid = rfid;
    }

    public String get_id() {
      return this._id;
    }

    public void set_id(String _id) {
      this._id = _id;
    }

    public Role getDni_type() {
      return this.dni_type;
    }

    public void setDni_type(Role dni_type) {
      this.dni_type = dni_type;
    }

    public List<? extends Role> getCategories() {
      return this.categories;
    }

    public void setCategories(List<? extends Role> categories) {
      this.categories = categories;
    }

    public List<? extends Access_group> getAccess_group() {
      return this.access_group;
    }

    public void setAccess_group(List<? extends Access_group> access_group) {
      this.access_group = access_group;
    }

    public String getFirst_name() {
      return this.first_name;
    }

    public void setFirst_name(String first_name) {
      this.first_name = first_name;
    }

    public String getBarcode() {
      return this.barcode;
    }

    public void setBarcode(String barcode) {
      this.barcode = barcode;
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

    public static class Role implements Serializable {
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

    public static class City implements Serializable {
      private Role country;

      private String name;

      private String _id;

      public Role getCountry() {
        return this.country;
      }

      public void setCountry(Role country) {
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
    }

    public static class Campus implements Serializable {
      private String name;

      private Sub_company sub_company;

      private String _id;

      public String getName() {
        return this.name;
      }

      public void setName(String name) {
        this.name = name;
      }

      public Sub_company getSub_company() {
        return this.sub_company;
      }

      public void setSub_company(Sub_company sub_company) {
        this.sub_company = sub_company;
      }

      public String get_id() {
        return this._id;
      }

      public void set_id(String _id) {
        this._id = _id;
      }

      public static class Sub_company implements Serializable {
        private City city;

        private String name;

        private Role company;

        private String _id;

        public City getCity() {
          return this.city;
        }

        public void setCity(City city) {
          this.city = city;
        }

        public String getName() {
          return this.name;
        }

        public void setName(String name) {
          this.name = name;
        }

        public Role getCompany() {
          return this.company;
        }

        public void setCompany(Role company) {
          this.company = company;
        }

        public String get_id() {
          return this._id;
        }

        public void set_id(String _id) {
          this._id = _id;
        }
      }
    }

    public static class Profile_picture implements Serializable {
      private String _id;

      private String url;

      public String get_id() {
        return this._id;
      }

      public void set_id(String _id) {
        this._id = _id;
      }

      public String getUrl() {
        return this.url;
      }

      public void setUrl(String url) {
        this.url = url;
      }
    }

    public static class Access_group implements Serializable {
      private String name;

      private String _id;

      private List<? extends Device> device;

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

      public List<? extends Device> getDevice() {
        return this.device;
      }

      public void setDevice(List<? extends Device> device) {
        this.device = device;
      }

      public static class Device implements Serializable {
        private String zone;

        private Campus campus;

        private String name;

        private String _id;

        private String sn;

        private Boolean is_online;

        private Role direction;

        public String getZone() {
          return this.zone;
        }

        public void setZone(String zone) {
          this.zone = zone;
        }

        public Campus getCampus() {
          return this.campus;
        }

        public void setCampus(Campus campus) {
          this.campus = campus;
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

        public String getSn() {
          return this.sn;
        }

        public void setSn(String sn) {
          this.sn = sn;
        }

        public Boolean getIs_online() {
          return this.is_online;
        }

        public void setIs_online(Boolean is_online) {
          this.is_online = is_online;
        }

        public Role getDirection() {
          return this.direction;
        }

        public void setDirection(Role direction) {
          this.direction = direction;
        }
      }
    }
  }
}

package cn.com.aratek.demo.featuresrequest;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.String;
import java.util.List;

public class Vehicle implements Serializable {
  private Boolean is_active;

  private Role role;

  private String fabricator;

  private String color;

  private String description;

  private String plate;

  private List<? extends Profile_picture> profile_picture;

  private String type;

  private String contract_start_date;

  private String contract_end_date;

  private String model;

  private String _id;

  private List<? extends Access_group> access_group;

  private String barcode;

  public Boolean getIs_active() {
    return this.is_active;
  }

  public void setIs_active(Boolean is_active) {
    this.is_active = is_active;
  }

  public Role getRole() {
    return this.role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public String getFabricator() {
    return this.fabricator;
  }

  public void setFabricator(String fabricator) {
    this.fabricator = fabricator;
  }

  public String getColor() {
    return this.color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPlate() {
    return this.plate;
  }

  public void setPlate(String plate) {
    this.plate = plate;
  }

  public List<? extends Profile_picture> getProfile_picture() {
    return this.profile_picture;
  }

  public void setProfile_picture(List<? extends Profile_picture> profile_picture) {
    this.profile_picture = profile_picture;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
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

  public String getModel() {
    return this.model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String get_id() {
    return this._id;
  }

  public void set_id(String _id) {
    this._id = _id;
  }

  public List<? extends Access_group> getAccess_group() {
    return this.access_group;
  }

  public void setAccess_group(List<? extends Access_group> access_group) {
    this.access_group = access_group;
  }

  public String getBarcode() {
    return this.barcode;
  }

  public void setBarcode(String barcode) {
    this.barcode = barcode;
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
        }
      }
    }
  }
}

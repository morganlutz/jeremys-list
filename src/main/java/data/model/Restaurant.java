package jeremyslist;

public class Restaurant {
  private int id;
  private String name;
  private String address;
  private String phone;
  private String website;
  private String yelp;
  private String hours;
  private int quadrant_id;

  public Restaurant(String name, String address, String phone, String website, String yelp, String hours) {
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.website = website;
    this.yelp = yelp;
    this.hours = hours;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public String getPhone() {
    return phone;
  }

  public String getWebsite() {
    return website;
  }

  public String getYelp() {
    return yelp;
  }

  public String getHours() {
    return hours;
  }

  public int getQuadrantId() {
    return quadrant_id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  public void setYelp(String yelp) {
    this.yelp = yelp;
  }

  public void setHours(String hours) {
    this.hours = hours;
  }

  public int getQuadrant_id() {
    return quadrant_id;
  }

  public void setQuadrant_id(int quadrant_id) {
    this.quadrant_id = quadrant_id;
  }
}

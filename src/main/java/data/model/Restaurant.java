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
}

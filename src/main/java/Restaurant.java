import java.util.List;
import org.sql2o.*;

public class Restaurant {
  private int id;
  private String name;
  private String address;
  private String phone;
  private String website;
  private String yelp;
  private String hours;
  private int quadrant_id;

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

  public Restaurant(String name, String address, String phone, String website, String yelp, String hours) {
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.website = website;
    this.yelp = yelp;
    this.hours = hours;
  }

  @Override
  public boolean equals(Object otherRestaurant) {
    if (!(otherRestaurant instanceof Restaurant)) {
      return false;
    } else {
      Restaurant newRestaurant = (Restaurant) otherRestaurant;
      return this.getId() == newRestaurant.getId() &&
      this.getName().equals(newRestaurant.getName()) &&
      this.getAddress().equals(newRestaurant.getAddress()) &&
      this.getPhone().equals(newRestaurant.getPhone()) &&
      this.getWebsite().equals(newRestaurant.getWebsite()) &&
      this.getYelp().equals(newRestaurant.getYelp()) &&
      this.getHours().equals(newRestaurant.getHours()) &&
    }
  }

  public static List<Restaurant> all() {
    String sql = "SELECT * FROM restaurants";
    try(Connection con = DB.sql2o.open()) {
    return con.createQuery(sql).executeAndFetch(Restaurant.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO restaurants (name, address, phone, website, yelp, hours)" +
      " VALUES (:name, :address, :phone, :website, :yelp, :hours)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("address", this.address)
        .addParameter("phone", this.phone)
        .addParameter("website", this.website)
        .addParameter("yelp", this.yelp)
        .addParameter("hours", this.hours)
        .executeUpdate()
        .getKey();
    }
  }

  public static Restaurant find(int id) {
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM restaurants where id=:id";
      Restaurant restaurant = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Restaurant.class);
      return restaurant;
    }
  }

  public void update(String name, String address, String phone, String website, String yelp, String hours){
    try(Connection con = DB.sql2o.open()){
      String sql = "UPDATE restaurants SET name =:name, address=:address, phone=:phone, website=:website, yelp=:yelp, hours=:hours WHERE id=:id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("address", address)
        .addParameter("phone", phone)
        .addParameter("website", website)
        .addParameter("yelp", yelp)
        .addParameter("hours", hours)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public String getCategory() {
    String sql = "SELECT categories.name FROM restaurants JOIN restaurant_category ON"+
    "(restaurants.id = restaurant_category.restaurant_id) JOIN categories ON "+
    "(restaurant_category.category_id = categories.id) where restaurants.id=:restaurant_id;";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("id", cuisine_id) //:id variable replaced with cuisine_id
        .executeAndFetchFirst(String.class);
    }
  }

  public void addCategory(Category category) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO restaurant_category (restaurant_id, category_id) VALUES (:restaurant_id, :category_id)";
    con.createQuery(sql)
      .addParameter("restaurant_id", this.getId())
      .addParameter("category", category.getId())
      .executeUpdate();
  }
}

  public String getQuadrant() {
    String sql = "SELECT quadrant FROM quadrants WHERE id=:quadrant_id";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("quadrant_id", quadrant_id)
        .executeAndFetchFirst(String.class);
    }
  }

  public void deleteListAssociationsOnly(Category category) {
    try(Connection con = DB.sql2o.open()) {
      String joinDeleteQuery = "DELETE FROM restaurant_category WHERE restaurant_id=:restaurant_id AND category_id:=category_id";
        con.createQuery(joinDeleteQuery)
          .addParameter("restaurant_id", this.getId())
          .addParameter("category_id", category.getId())
          .executeUpdate();
    }
  }

  public void totalDeletion() {
    try(Connection con = DB.sql2o.open()) {
      String deleteQuery = "DELETE FROM restaurants WHERE id=:id";
        con.createQuery(deleteQuery)
          .addParameter("id", id)
          .executeUpdate();

      String joinDeleteQuery = "DELETE FROM restaurant_category WHERE restaurant_id=:restaurant_id";
        con.createQuery(joinDeleteQuery)
          .addParameter("restaurant_id", this.getId())
          .executeUpdate();
     }
   }


  public List<Diet> getDietaryRestrictions() {
    String sql = "SELECT dietary_restrictions.restriction FROM restaurants JOIN restaurants_for_diets ON"+
    "(restaurants.id = restaurants_for_diets.restaurant_id) JOIN dietary_restrictions ON "+
    "(restaurants_for_diets.dietary_id = dietary_restrictions.id) where restaurants.id=:restaurant_id;";
    List<Diet> restrictions = con.createQuery(sql)
      .addParameter("restaurant_id", this.getId())
      .executeAndFetch(Diet.class);
    return restrictions;
    }
  }


  // public static List<Restaurant> search(String address, int cuisine) {
  //   try(Connection con = DB.sql2o.open()) {
  //    String sql = "SELECT id, name, address, hours, cuisine_id FROM restaurants WHERE address=:address AND cuisine_id=:cuisine;";
  //    return con.createQuery(sql)
  //     .addParameter("address", address)
  //     .addParameter("cuisine", cuisine)
  //     .executeAndFetch(Restaurant.class);
  //   }
  // }




}

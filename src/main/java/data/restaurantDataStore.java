package jeremyslist;
import java.util.List;
import org.sql2o.*;


public class restaurantDataStore {

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
      this.getHours().equals(newRestaurant.getHours());
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

  public void addCategory(Category category) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO restaurant_category (restaurant_id, category_id) "+
      "SELECT :restaurant_id, :category_id WHERE NOT EXISTS (SELECT restaurant_id, category_id "+
      "FROM restaurant_category WHERE restaurant_id=:restaurant_id AND category_id=:category_id)";
      con.createQuery(sql)
        .addParameter("restaurant_id", this.id)
        .addParameter("category_id", category.getId())
        .executeUpdate();
      }
    }

  public List<Category> getCategories() {
    String sql = "SELECT categories.* FROM restaurants JOIN restaurant_category ON"+
    "(restaurants.id = restaurant_category.restaurant_id) JOIN categories ON "+
    "(restaurant_category.category_id = categories.id) where restaurants.id=:restaurant_id;";
    try(Connection con = DB.sql2o.open()) {
      List<Category> categories = con.createQuery(sql)
        .addParameter("restaurant_id", this.id)
        .executeAndFetch(Category.class);
      return categories;
    }
  }

  public void addQuadrant(Quadrant quadrant) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO restaurant_location (restaurant_id, quadrant_id) "+
      "SELECT :restaurant_id, :quadrant_id WHERE NOT EXISTS (SELECT restaurant_id, quadrant_id "+
      "FROM restaurant_category WHERE restaurant_id=:restaurant_id AND quadrant_id=:quadrant_id)";
      con.createQuery(sql)
        .addParameter("restaurant_id", this.id)
        .addParameter("quadrant_id", quadrant.getId())
        .executeUpdate();
      }
    }

  public String getQuadrant() {
    String sql = "SELECT quadrants.quadrant FROM restaurants JOIN restaurant_location ON"+
    "(restaurants.id = restaurant_location.restaurant_id) JOIN quadrants ON "+
    "(restaurant_location.quadrant_id = quadrants.id) where restaurants.id=:restaurant_id;";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("restaurant_id", this.id) //:id variable replaced with cuisine_id
        .executeAndFetchFirst(String.class);
    }
  }

  public void addTag(Tag tag) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO restaurants_tags (restaurant_id, tag_id) "+
      "SELECT :restaurant_id, :tag_id WHERE NOT EXISTS (SELECT restaurant_id, tag_id "+
      "FROM restaurants_tags WHERE restaurant_id=:restaurant_id AND tag_id=:tag_id)";
      con.createQuery(sql)
        .addParameter("restaurant_id", this.id)
        .addParameter("tag_id", tag.getId())
        .executeUpdate();
      }
    }

  public List<Tag> getTags() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT restaurants_tags.* FROM restaurants JOIN restaurants_tags ON"+
      "(restaurants.id = restaurants_tags.restaurant_id) JOIN tags ON "+
      "(restaurants_tags.tag_id = tags.id) WHERE restaurants.id=:restaurant_id;";
      List<Tag> restrictions = con.createQuery(sql)
        .addParameter("restaurant_id", this.getId())
        .executeAndFetch(Tag.class);
      return restrictions;
      }
    }

  public void deleteCategory(Category category) {
    try(Connection con = DB.sql2o.open()) {
      String joinDeleteQuery = "DELETE FROM restaurant_category WHERE restaurant_id=:restaurant_id AND category_id:=category_id";
        con.createQuery(joinDeleteQuery)
          .addParameter("restaurant_id", this.getId())
          .addParameter("category_id", category.getId())
          .executeUpdate();
    }
  }

  public void deleteQuadrant(Quadrant quadrant) {
    try(Connection con = DB.sql2o.open()) {
      String joinDeleteQuery = "DELETE FROM restaurant_location WHERE restaurant_id=:restaurant_id AND quadrant_id:=quadrant_id";
        con.createQuery(joinDeleteQuery)
          .addParameter("restaurant_id", this.getId())
          .addParameter("quadrant_id", quadrant.getId())
          .executeUpdate();
    }
  }

  public void deleteTag (Tag tag) {
    try(Connection con = DB.sql2o.open()) {
      String joinDeleteQuery = "DELETE FROM restaurants_tags WHERE restaurant_id=:restaurant_id AND tag_id:=tag_id";
        con.createQuery(joinDeleteQuery)
          .addParameter("restaurant_id", this.getId())
          .addParameter("tag_id", tag.getId())
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
 }

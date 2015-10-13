import java.util.List;
import org.sql2o.*;


public class Category {
  private String type;
  private int id;

  public int getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  public Category(String type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object otherCategory) {
    if(!(otherCategory instanceof Category)) {
      return false;
    } else {
      Category newCategory = (Category) otherCategory;
      return this.getType().equals(newCategory.getType()) &&
             this.getId() == newCategory.getId();
    }
  }

  public static List<Category> all() {
    String sql = "SELECT * FROM categories";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Category.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql ="INSERT INTO categories (type) VALUES (:type)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("type", this.type)
      .executeUpdate()
      .getKey();
    }
  }

  public static Category find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql ="SELECT * FROM categories WHERE id=:id";
      Category category = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Category.class);
      return category;
    }
  }

  public static Category findByType(String type) {
    try(Connection con = DB.sql2o.open()) {
      String sql ="SELECT * FROM categories WHERE type=:type";
      Category category = con.createQuery(sql)
        .addParameter("type", type)
        .executeAndFetchFirst(Category.class);
      return category;
    }
  }

  public void update(String type) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE categories SET type=:type WHERE id=:id";
      con.createQuery(sql)
      .addParameter("type", type)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public List<Restaurant> getRestaurants() {
    String sql = "SELECT DISTINCT restaurants.* FROM categories JOIN restaurant_category ON (categories.id = restaurant_category.category_id) JOIN restaurants ON (restaurant_category.restaurant_id = restaurants.id) WHERE category_id=:category_id;";

      // SELECT DISTINCT restaurants.* FROM categories JOIN restaurant_category ON (categories.id = restaurant_category.category_id)
      //  JOIN restaurants ON (restaurant_category.restaurant_id = restaurants.id) WHERE category_id=:category_id;

    try(Connection con = DB.sql2o.open()) {
      List<Restaurant> restaurants = con.createQuery(sql)
        .addParameter("category_id", this.getId())
        .executeAndFetch(Restaurant.class);
      return restaurants;
    }
  }

  public void deleteListAssociationsOnly(Restaurant restaurant) {
    try(Connection con = DB.sql2o.open()) {
      String joinDeleteQuery = "DELETE FROM restaurant_category WHERE restaurant_id=:restaurant_id AND category_id:=category_id";
        con.createQuery(joinDeleteQuery)
          .addParameter("category_id", this.getId())
          .addParameter("restaurant_id", restaurant.getId())
          .executeUpdate();
    }
  }

  public void addRestaurant(Restaurant restaurant) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO restaurant_category (restaurant_id, category_id) VALUES (:restaurant_id, :category_id)";
      con.createQuery(sql)
        .addParameter("restaurant_id", restaurant.getId())
        .addParameter("category_id", this.getId())
        .executeUpdate();
   }
 }

  public void totalDeletion() {
    try(Connection con = DB.sql2o.open()) {
      String deleteQuery = "DELETE FROM categories WHERE id=:id";
        con.createQuery(deleteQuery)
          .addParameter("id", id)
          .executeUpdate();

      String joinDeleteQuery = "DELETE FROM restaurant_category WHERE category_id=:category_id";
        con.createQuery(joinDeleteQuery)
          .addParameter("category_id", this.getId())
          .executeUpdate();
     }
   }

}

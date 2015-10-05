import jeremys-list.model.Category;
import java.util.List;
import org.sql2o.*;

public class categoryDataStore {

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
    String sql = "SELECT restaurants.* FROM categories JOIN restaurant_category ON"+
    "(restaurants.id = restaurant_category.restaurant_id) JOIN categories ON "+
    "(restaurant_category.category_id = categories.id) WHERE categories.id=:category_id;";
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

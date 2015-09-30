import java.util.List;
import org.sql2o.*;


public class Diet {
  private String restriction;
  private int id;

  public int getId() {
    return id;
  }

  public String getRestriction() {
    return restriction;
  }

  public Diet(String restriction) {
    this.restriction = restriction;
  }

  @Override
  public boolean equals(Object otherDiet) {
    if(!(otherDiet instanceof Diet)) {
      return false;
    } else {
      Diet newDiet = (Diet) otherDiet;
      return this.getRestriction().equals(newDiet.getRestriction()) &&
             this.getId() == newDiet.getId();
    }
  }

  public static List<Diet> all() {
    String sql = "SELECT * FROM dietary_restrictions";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Diet.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO dietary_restrictions (restriction) VALUES (:restriction)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("restriction", this.restriction)
        .executeUpdate()
        .getKey();
    }
  }

  public static Diet find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM dietary_restrictions WHERE id=:id";
      Diet diet = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Diet.class);
      return diet;
    }
  }

  public void update() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE dietary_restrictions SET restriction=:restriction WHERE id=:id";
      con.createQuery(sql)
        .addParameter("restriction", restriction)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void addRestaurant(Restaurant restaurant) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO restaurants_for_diets (restaurant_id, dietary_id) VALUES (:restaurant_id, :dietary_id)";
      con.createQuery(sql)
        .addParameter("restaurant_id", restaurant.getId())
        .addParameter("dietary_id", this.getId())
        .executeUpdate();
    }
  }

  public List<Restaurant> getRestaurants() {
    String sql = "SELECT restaurants.* FROM dietary_restrictions JOIN restaurants_for_diets ON"+
    "(restaurants.id = restaurants_for_diets.restaurant_id) JOIN dietary_restrictions ON "+
    "(restaurants_for_diets.dietary_id = dietary_restrictions.id) WHERE dietary_restrictions.id=:dietary_id;";
    try(Connection con = DB.sql2o.open()) {
      List<Restaurant> restaurants = con.createQuery(sql)
        .addParameter("dietary_id", this.getId())
        .executeAndFetch(Restaurant.class);
      return restaurants;
    }
  }

  public void deleteListAssociationsOnly(Restaurant restaurant) {
    try(Connection con = DB.sql2o.open()) {
      String joinDeleteQuery = "DELETE FROM restaurants_for_diets WHERE restaurant_id=:restaurant_id AND dietary_id:=dietary_id";
        con.createQuery(joinDeleteQuery)
          .addParameter("dietary_id", this.getId())
          .addParameter("restaurant_id", restaurant.getId())
          .executeUpdate();
    }
  }

  public void totalDeletion() {
    try(Connection con = DB.sql2o.open()) {
      String deleteQuery = "DELETE FROM dietary_restrictions WHERE id=:id";
        con.createQuery(deleteQuery)
          .addParameter("id", id)
          .executeUpdate();

      String joinDeleteQuery = "DELETE FROM restaurants_for_diets WHERE dietary_id=:dietary_id";
        con.createQuery(joinDeleteQuery)
          .addParameter("dietary_id", this.getId())
          .executeUpdate();
     }
   }
}//end of Diet

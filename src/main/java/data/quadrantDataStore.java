import java.util.List;
import org.sql2o.*;

public class quadrantDataStore {
    
  @Override
  public boolean equals(Object otherQuadrant) {
    if(!(otherQuadrant instanceof Quadrant)) {
      return false;
    } else {
      Quadrant newQuadrant = (Quadrant) otherQuadrant;
      return this.getQuadrant().equals(newQuadrant.getQuadrant()) &&
             this.getId() == newQuadrant.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO quadrants (quadrant) VALUES (:quadrant)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("quadrant", this.quadrant)
        .executeUpdate()
        .getKey();
    }
  }

  public static Quadrant find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM quadrants WHERE id=:id";
      Quadrant quadrant = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Quadrant.class);
      return quadrant;
    }
  }

  public void update() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE quadrants SET quadrant=:quadrant WHERE id=:id";
      con.createQuery(sql)
        .addParameter("quadrant", quadrant)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public List<Restaurant> getRestaurants() {
    String sql = "SELECT restaurants.* FROM quadrants JOIN restaurant_location ON"+
    "(restaurants.id = restaurant_location.restaurant_id) JOIN quadrants ON "+
    "(restaurant_location.quadrant_id = quadrants.id) WHERE quadrants.id=:quadrant_id;";
    try(Connection con = DB.sql2o.open()) {
      List<Restaurant> restaurants = con.createQuery(sql)
        .addParameter("quadrant_id", this.getId())
        .executeAndFetch(Restaurant.class);
      return restaurants;
    }
  }

  public void deleteListAssociationsOnly(Restaurant restaurant) {
    try(Connection con = DB.sql2o.open()) {
      String joinDeleteQuery = "DELETE FROM restaurant_location WHERE restaurant_id=:restaurant_id AND quadrant_id:=quadrant_id";
        con.createQuery(joinDeleteQuery)
          .addParameter("quadrant_id", this.getId())
          .addParameter("restaurant_id", restaurant.getId())
          .executeUpdate();
    }
  }

  public void totalDeletion() {
    try(Connection con = DB.sql2o.open()) {
      String deleteQuery = "DELETE FROM quadrants WHERE id=:id";
        con.createQuery(deleteQuery)
          .addParameter("id", id)
          .executeUpdate();

      String joinDeleteQuery = "DELETE FROM restaurant_location WHERE quadrant_id=:quadrant_id";
        con.createQuery(joinDeleteQuery)
          .addParameter("quadrant_id", this.getId())
          .executeUpdate();
     }
   }

}

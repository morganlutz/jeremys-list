import java.util.List;
import org.sql2o.*;

public class tagDataStore {

@Override
public boolean equals(Object otherTag) {
  if(!(otherTag instanceof Tag)) {
    return false;
  } else {
    Tag newTag = (Tag) otherTag;
    return this.getDescription().equals(newTag.getDescription()) &&
           this.getId() == newTag.getId();
  }
}

public static List<Tag> all() {
  String sql = "SELECT * FROM tags";
  try(Connection con = DB.sql2o.open()) {
    return con.createQuery(sql).executeAndFetch(Tag.class);
  }
}

public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO tags (description) VALUES (:description)";
    this.id = (int) con.createQuery(sql, true)
      .addParameter("description", this.description)
      .executeUpdate()
      .getKey();
  }
}

public static Tag find(int id) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT * FROM tags WHERE id=:id";
    Tag tag = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Tag.class);
    return tag;
  }
}

public void update() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "UPDATE tags SET description=:description WHERE id=:id";
    con.createQuery(sql)
      .addParameter("description", description)
      .addParameter("id", id)
      .executeUpdate();
  }
}

public void addRestaurant(Restaurant restaurant) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO restaurants_tags (restaurant_id, tag_id) VALUES (:restaurant_id, :tag_id)";
    con.createQuery(sql)
      .addParameter("restaurant_id", restaurant.getId())
      .addParameter("tag_id", this.getId())
      .executeUpdate();
  }
}

public List<Restaurant> getRestaurants() {
  String sql = "SELECT restaurants.* FROM tags JOIN restaurants_tags ON"+
  "(restaurants.id = restaurants_tags.restaurant_id) JOIN tags ON "+
  "(restaurants_tags.tag_id = tags.id) WHERE tags.id=:tag_id;";
  try(Connection con = DB.sql2o.open()) {
    List<Restaurant> restaurants = con.createQuery(sql)
      .addParameter("tag_id", this.getId())
      .executeAndFetch(Restaurant.class);
    return restaurants;
  }
}

public void deleteListAssociationsOnly(Restaurant restaurant) {
  try(Connection con = DB.sql2o.open()) {
    String joinDeleteQuery = "DELETE FROM restaurants_tags WHERE restaurant_id=:restaurant_id AND tag_id:=tag_id";
      con.createQuery(joinDeleteQuery)
        .addParameter("tag_id", this.getId())
        .addParameter("restaurant_id", restaurant.getId())
        .executeUpdate();
  }
}

public void totalDeletion() {
  try(Connection con = DB.sql2o.open()) {
    String deleteQuery = "DELETE FROM tags WHERE id=:id";
      con.createQuery(deleteQuery)
        .addParameter("id", id)
        .executeUpdate();

    String joinDeleteQuery = "DELETE FROM restaurants_tags WHERE tag_id=:tag_id";
      con.createQuery(joinDeleteQuery)
        .addParameter("tag_id", this.getId())
        .executeUpdate();
   }
 }
}//end of Tag

import java.util.List;
import org.sql2o.*;


public class Cuisine {
  private String foodtype;
  private int id;

  public int getId() {
    return id;
  }

  public String getFoodType() {
    return foodtype;
  }

  public Cuisine(String foodtype) {
    this.foodtype = foodtype;
  }

  @Override
  public boolean equals(Object otherCuisine) {
    if(!(otherCuisine instanceof Cuisine)) {
      return false;
    } else {
      Cuisine newCuisine = (Cuisine) otherCuisine;
      return this.getFoodType().equals(newCuisine.getFoodType());
    }
  }

  public static List<Cuisine> all() {
    String sql = "SELECT id, foodtype FROM cuisines";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Cuisine.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql ="INSERT INTO cuisines (foodtype) values (:foodtype)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("foodtype", this.foodtype)
      .executeUpdate()
      .getKey();
    }
  }

  public static Cuisine find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql ="select * from cuisines where id=:id";
      Cuisine cuisine = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Cuisine.class);
      return cuisine;
    }
  }

  public List<Restaurant> getRestaurants() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM restaurants WHERE cuisine_id= :id";
      return con.createQuery(sql)
       .addParameter("id", this.id)
       .executeAndFetch(Restaurant.class);
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM cuisines WHERE id = :id;";
      con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void update(String foodtype) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE cuisines SET foodtype= :foodtype WHERE id= :id";
      con.createQuery(sql)
      .addParameter("foodtype", foodtype)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

}

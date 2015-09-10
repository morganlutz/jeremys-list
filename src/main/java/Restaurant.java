import java.util.List;
import org.sql2o.*;

public class Restaurant {
  public static int TAG_COUNTER = 1;
  private int id;
  private String name;
  private String city;
  private String hours;
  private int cuisine_id;

  public int getId() {
    return id;
  }

  public int getCuisineId() {
    return cuisine_id;
  }

  public String getName() {
    return name;
  }

  public String getCity() {
    return city;
  }

  public String getHours() {
    return hours;
  }

  public String getCuisineName() {
    String sql = "SELECT foodtype FROM cuisines WHERE id=:id";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("id", cuisine_id) //:id variable replaced with cuisine_id
        .executeAndFetchFirst(String.class);
    }
  }


  public Restaurant(String name, String city, String hours, int cuisine_id) {
    this.name = name;
    this.cuisine_id = cuisine_id;
    this.city = city;
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
      this.getCity().equals(newRestaurant.getCity()) &&
      this.getHours().equals(newRestaurant.getHours()) &&
      this.getCuisineId() == newRestaurant.getCuisineId();
    }
  }

  public static List<Restaurant> all() {
    String sql = "SELECT id, name, city, hours, cuisine_id FROM restaurants";
    try(Connection con = DB.sql2o.open()) {
    return con.createQuery(sql).executeAndFetch(Restaurant.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO restaurants (name, city, hours, cuisine_id) VALUES (:name, :city, :hours, :cuisine_id)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("city", this.city)
        .addParameter("hours", this.hours)
        .addParameter("cuisine_id", this.cuisine_id)
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

  public void delete() {
    try(Connection con = DB.sql2o.open()){
      String sql = "DELETE FROM restaurants WHERE id = :id";
        con.createQuery(sql)
          .addParameter("id", id)
          .executeUpdate();
    }
  }

  public void update(String name, String city, String hours){
    try(Connection con = DB.sql2o.open()){
      String sql = "UPDATE restaurants SET name =:name, city=:city, hours=:hours WHERE id=:id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("city", city)
        .addParameter("hours", hours)
        .addParameter("id", id)
        .executeUpdate();

    }
  }

  public static List<Restaurant> search(String city, int cuisine) {
    try(Connection con = DB.sql2o.open()) {
     String sql = "SELECT id, name, city, hours, cuisine_id FROM restaurants WHERE city=:city AND cuisine_id=:cuisine;";
     return con.createQuery(sql)
      .addParameter("city", city)
      .addParameter("cuisine", cuisine)
      .executeAndFetch(Restaurant.class);
    }
  }

}

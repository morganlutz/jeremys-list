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
      return this.getType().equals(newCategory.getType());
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
      String sql ="INSERT INTO categories (type) values (:type)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("type", this.type)
      .executeUpdate()
      .getKey();
    }
  }

  public static Category find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql ="select * from categories where id=:id";
      Category category = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Category.class);
      return category;
    }
  }

  public List<Restaurant> getRestaurants() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM restaurants WHERE category_id=:id";
      return con.createQuery(sql)
       .addParameter("id", this.id)
       .executeAndFetch(Restaurant.class);
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM categories WHERE id = :id;";
      con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
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

}

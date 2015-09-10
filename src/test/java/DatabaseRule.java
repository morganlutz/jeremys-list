import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/cuisine_test", null, null);
   }

  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteCuisineQuery = "DELETE FROM cuisines *;";
      String deleteRestaurantQuery = "DELETE FROM restaurants *;";
      con.createQuery(deleteRestaurantQuery).executeUpdate();
      con.createQuery(deleteCuisineQuery).executeUpdate();
    }
  }
}

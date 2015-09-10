import java.util.Arrays;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;


public class RestaurantTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Restaurant.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfDescriptionsAreTheSame() {
    Restaurant firstRestaurant = new Restaurant("Burgerville", "Portland", "10 a.m. - 10 p.m.", 1);
    Restaurant secondRestaurant = new Restaurant("Burgerville", "Portland", "10 a.m. - 10 p.m.", 1);
    assertTrue(firstRestaurant.equals(secondRestaurant));
  }

  @Test
  public void equals_returnsFalseIfDescriptionsAreTheSame() {
    Restaurant firstRestaurant = new Restaurant("Burgerville", "Portland", "10 a.m. - 10 p.m.", 1);
    Restaurant secondRestaurant = new Restaurant("Burgerville", "Portland", "10 a.m. - 10 p.m.", 2);
    assertTrue(!firstRestaurant.equals(secondRestaurant));
  }

  @Test
   public void save_returnsTrueIfDescriptionsAretheSame() {
    Restaurant myRestaurant = new Restaurant("Burgerville", "Portland", "10 a.m. - 10 p.m.", 1);
    myRestaurant.save();
    assertTrue(Restaurant.all().get(0).equals(myRestaurant));
   }

   @Test
   public void save_assignsIdToObject() {
     Restaurant myRestaurant = new Restaurant("Burgerville", "Portland", "10 a.m. - 10 p.m.", 1);
     myRestaurant.save();
     Restaurant savedRestaurant = Restaurant.all().get(0);
     assertEquals(myRestaurant.getId(), savedRestaurant.getId());
   }

   @Test
   public void find_findsTaskInDatabase_true() {
     Restaurant myRestaurant = new Restaurant("Burgerville", "Portland", "10 a.m. - 10 p.m.", 1);
     myRestaurant.save();
     Restaurant savedRestaurant = Restaurant.find(myRestaurant.getId());
     assertTrue(myRestaurant.equals(savedRestaurant));
   }

   @Test
   public void delete_deletesTaskFromDatabase_true() {
     Cuisine myCuisine = new Cuisine("Banking");
     myCuisine.save();
     Restaurant myRestaurant = new Restaurant("Burgerville", "Portland", "10 a.m. - 10 p.m.", 1);
     myRestaurant.save();
     myRestaurant.delete();
     assertEquals(Restaurant.all().size(), 0);
   }

//    @Test
//    public void update_changesTaskNameInDatabase_true() {
//      Cuisine myCuisine = new Cuisine("Morping");
//      myCuisine.save();
//      Restaurant myRestaurant = new Restaurant("Burgerville", "Portland", "10 a.m. - 10 p.m.", 1);
//      myRestaurant.save();
//
//
//      //name, city, hours, cuisine_id
//
//
//      String name = "Fart", city ="";
//      myRestaurant.update(name, city, hours, cuisine_id);
//      assertTrue(Restaurant.all().get(0).getName().equals(name));
//    }
}

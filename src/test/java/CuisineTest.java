import java.util.Arrays;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;

public class CuisineTest {

     @Rule
     public DatabaseRule database = new DatabaseRule();

     @Test
     public void all_emptyAtFirst() {
       assertEquals(Cuisine.all().size(), 0);
     }

     @Test
     public void equals_returnsTrueIfFoodTypesAreTheSame() {
       Cuisine firstCuisine = new Cuisine("Yummy");
       Cuisine secondCuisine = new Cuisine("Yummy");
       assertTrue(firstCuisine.equals(secondCuisine));
     }

     @Test
     public void save_savesIntoDatabase_true () {
       Cuisine newCuisine = new Cuisine("Wretched");
       newCuisine.save();
       assertTrue(Cuisine.all().get(0).equals(newCuisine));
     }

      @Test
      public void find_findsCuisineInDatabase_true() {
      Cuisine myCuisine = new Cuisine("Banking");
      myCuisine.save();
      Cuisine savedCuisine = Cuisine.find(myCuisine.getId());
      assertTrue(myCuisine.equals(savedCuisine));
      }

      @Test
      public void getRestaurants_retrievesAllRestaurantsFromDatabase_restaurantList() {
        Cuisine myCuisine = new Cuisine("Banking");
        myCuisine.save();
        Restaurant firstRestaurant = new Restaurant("steal money","blah", "bob", myCuisine.getId());
        firstRestaurant.save();
        Restaurant secondRestaurant = new Restaurant("lots of money", "clovis", "hours", myCuisine.getId());
        secondRestaurant.save();
        Restaurant[] restaurants = new Restaurant[] { firstRestaurant, secondRestaurant };
        assertTrue(myCuisine.getRestaurants().containsAll(Arrays.asList(restaurants)));
      }

      @Test
      public void delete_deletesCuisineFromDatabase_true() {
        Cuisine myCuisine = new Cuisine("MOO");
        myCuisine.save();
        myCuisine.delete();
        assertEquals(Cuisine.all().size(), 0);
      }

      @Test
      public void update_changesCuisineNameInDatabase_true() {
        Cuisine myCuisine = new Cuisine("Mabler");
        myCuisine.save();
        String foodtype = "Mabler";
        myCuisine.update(foodtype);
        assertTrue(Cuisine.all().get(0).getFoodType().equals(foodtype));
      }
    }

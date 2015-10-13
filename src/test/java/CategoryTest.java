import java.util.Arrays;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;

public class CategoryTest {

     @Rule
     public DatabaseRule database = new DatabaseRule();

     @Test
     public void all_emptyAtFirst() {
       assertEquals(Category.all().size(), 0);
     }

    //  @Test
    //  public void equals_returnsTrueIfFoodTypesAreTheSame() {
    //    Cuisine firstCuisine = new Cuisine("Yummy");
    //    Cuisine secondCuisine = new Cuisine("Yummy");
    //    assertTrue(firstCuisine.equals(secondCuisine));
    //  }
     //
    //  @Test
    //  public void save_savesIntoDatabase_true () {
    //    Cuisine newCuisine = new Cuisine("Wretched");
    //    newCuisine.save();
    //    assertTrue(Cuisine.all().get(0).equals(newCuisine));
    //  }
     //
    //   @Test
    //   public void find_findsCuisineInDatabase_true() {
    //   Cuisine myCuisine = new Cuisine("Banking");
    //   myCuisine.save();
    //   Cuisine savedCuisine = Cuisine.find(myCuisine.getId());
    //   assertTrue(myCuisine.equals(savedCuisine));
    //   }

      @Test
      public void getRestaurants_retrievesAllRestaurantsFromDatabase_restaurantList() {
        Category myCategory = new Category("Banking");
        myCategory.save();
        Restaurant firstRestaurant = new Restaurant("steal money","blah", "bob", "..", "..", "..");
        firstRestaurant.save();
        Restaurant secondRestaurant = new Restaurant("steal money","blah", "bob", "..", "..", "..");
        secondRestaurant.save();
        myCategory.addRestaurant(firstRestaurant);
        myCategory.addRestaurant(secondRestaurant);
        assertEquals(myCategory.getRestaurants().size(), 2);
      }
      //
      // @Test
      // public void delete_deletesCategoryFromDatabase_true() {
      //   Cuisine myCuisine = new Cuisine("MOO");
      //   myCuisine.save();
      //   myCuisine.delete();
      //   assertEquals(Cuisine.all().size(), 0);
      // }
      //
      // @Test
      // public void update_changesCuisineNameInDatabase_true() {
      //   Cuisine myCuisine = new Cuisine("Mabler");
      //   myCuisine.save();
      //   String foodtype = "Mabler";
      //   myCuisine.update(foodtype);
      //   assertTrue(Cuisine.all().get(0).getFoodType().equals(foodtype));
      // }
    }

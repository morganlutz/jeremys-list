package jeremyslist;
import com.google.gson.Gson;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;
import java.util.List;

public class App {
  public static void main(String[] args) {
  staticFileLocation("/public");
  String layout = "templates/layout.vtl";

  get("/", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    model.put("restaurants", Restaurant.all());
    model.put("categories", CategoryDataStore.all());
    model.put("template", "templates/home.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  // post("/new-category", (request, response) -> {
  //   HashMap<String, Object> model = new HashMap<String, Object>();
  //   String type = request.queryParams("type");
  //   Category newCategory = new Category(type);
  //   newCategory.save();
  //
  //   model.put("restaurants", Restaurant.all());
  //   model.put("categories", Category.all());
  //   model.put("template", "templates/admin.vtl");
  //   return new ModelAndView(model, layout);
  // }, new VelocityTemplateEngine());
  //
  // post("/new-restaurant", (request, response) -> {
  //   HashMap<String, Object> model = new HashMap<String, Object>();
  //
  //   String name = request.queryParams("name");
  //   String address = request.queryParams("address");
  //   String phone = request.queryParams("phone");
  //   String website = request.queryParams("website");
  //   String yelp = request.queryParams("yelp");
  //   String hours = request.queryParams("hours");
  //   Restaurant newRestaurant = new Restaurant(name, address, phone, website, yelp, hours);
  //   newRestaurant.save();
  //   int categoryId = Integer.parseInt(request.queryParams("categoryId"));
  //   Category newCategory = Category.find(categoryId);
  //
  //   newRestaurant.addCategory(newCategory);
  //
  //   model.put("restaurants", Restaurant.all());
  //   model.put("categories", Category.all());
  //   model.put("template", "templates/admin.vtl");
  //   return new ModelAndView(model, layout);
  // }, new VelocityTemplateEngine());
  //
  // post("/add-category", (request, response) -> {
  //   HashMap<String, Object> model = new HashMap<String, Object>();
  //   Category newCategory = Category.find(Integer.parseInt(request.queryParams("categoryId")));
  //   Restaurant newRestaurant = Restaurant.find(Integer.parseInt(request.queryParams("restaurantId")));
  //   newRestaurant.addCategory(newCategory);
  //
  //   model.put("restaurants", Restaurant.all());
  //   model.put("categories", Category.all());
  //   model.put("template", "templates/admin.vtl");
  //   return new ModelAndView(model, layout);
  // }, new VelocityTemplateEngine());

  }//end of main
}//end of app

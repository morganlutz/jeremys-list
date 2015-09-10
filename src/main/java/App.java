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
    //categories here can be anything as long as it matches $categories
    model.put("restaurants", Restaurant.all());
    model.put("cuisines", Cuisine.all());
    model.put("template", "templates/index.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/cuisines", (request, response) -> { //post route must match form action
    HashMap<String,Object> model = new HashMap<String, Object>();
    String foodtype = request.queryParams("foodtype");
    Cuisine newCuisine = new Cuisine(foodtype);
    newCuisine.save();
    model.put("cuisines", Cuisine.all());
    //put arraylist of cuisines on page
    model.put("template", "templates/index.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/cuisines", (request, response) -> {
  //need to put :id in the url so that we can grab it below
    HashMap<String, Object> model = new HashMap<String, Object>();

    model.put("restaurants", Restaurant.all());
    model.put("cuisines", Cuisine.all());
    model.put("template", "templates/admin.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/restaurants", (request, response) -> {
    ///restaurants in index.vtl
    //posts information from restaurant form on homepage.
    HashMap<String, Object> model = new HashMap<String, Object>();
    Cuisine cuisine = Cuisine.find(Integer.parseInt(request.queryParams("cuisine_id")));
    String name = request.queryParams("name");
    String city = request.queryParams("city");
    String hours = request.queryParams("hours");
    Restaurant newRestaurant = new Restaurant(name, city, hours, cuisine.getId());
    newRestaurant.save();
    model.put("cuisine", cuisine);
    model.put("cuisines", Cuisine.all());
    model.put("template", "templates/index.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/cuisines/:id/update", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    Cuisine cuisine = Cuisine.find(Integer.parseInt(request.params(":id")));
    model.put("cuisine", cuisine);
    model.put("template", "templates/cuisine-form.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/cuisines/:id/update", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    Cuisine cuisine = Cuisine.find(Integer.parseInt(request.params(":id")));
    String foodtype = request.queryParams("foodtype");
    cuisine.update(foodtype);
    response.redirect("/cuisines");
    return null;
  });

  post("cuisines/:id/delete", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    Cuisine cuisine = Cuisine.find(Integer.parseInt(request.params(":id")));
    cuisine.delete();
  //  model.put("cuisines", Cuisine.all());
    response.redirect("/cuisines");
    return null;
  });

  get("/:cuisine_id/restaurants/:id/update", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    Cuisine cuisine = Cuisine.find(Integer.parseInt(request.params(":cuisine_id")));
    Restaurant restaurant = Restaurant.find(Integer.parseInt(request.params(":id")));
    model.put("cuisine", cuisine);
    model.put("cuisines", Cuisine.all());
    model.put("restaurant", restaurant);
    model.put("template", "templates/restaurant-form.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/:cuisine_id/restaurants/:id/update", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    Cuisine cuisine = Cuisine.find(Integer.parseInt(request.params(":cuisine_id")));
    Restaurant restaurant = Restaurant.find(Integer.parseInt(request.params(":id")));
    model.put("cuisines", Cuisine.all());
    String name = request.queryParams("name");
    String city = request.queryParams("city");
    String hours = request.queryParams("hours");
    restaurant.update(name, city, hours);
    response.redirect("/cuisines");
    return null;
  });

   post("/:cuisine_id/restaurants/:id/delete", (request, response) -> {
   HashMap<String, Object> model = new HashMap<String, Object>();
   Cuisine cuisine = Cuisine.find(Integer.parseInt(request.params(":cuisine_id")));
   Restaurant restaurant = Restaurant.find(Integer.parseInt(request.params(":id")));
   model.put("template", "templates/admin.vtl");
   restaurant.delete();
   model.put("restaurants", Restaurant.all());
   model.put("cuisines", Cuisine.all());
   return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/search", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    //categories here can be anything as long as it matches $categories
    model.put("restaurants", Restaurant.all());
    model.put("cuisines", Cuisine.all());
    model.put("template", "templates/search.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/search", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    int cuisine = Integer.parseInt(request.queryParams("cuisine_id"));
    String city = request.queryParams("city");

    List<Restaurant> searchResults = Restaurant.search(city, cuisine);
    model.put("searchResults", searchResults);
    model.put("cuisine", cuisine);
    model.put("restaurants", Restaurant.all());
    model.put("cuisines", Cuisine.all());
    model.put("template", "templates/search.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  }//end of main
}//end of app

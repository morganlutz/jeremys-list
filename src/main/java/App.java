import com.google.gson.Gson;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class App {
  static String APP_ROOT;
  static Boolean PRODUCTION = false;

  public static void main(String[] args) {
  staticFileLocation("/public");
  String layout = "templates/layout.vtl";
  List<String> CATEGORIES = Arrays.asList("coffee", "bakery","breakfast","foodcart","lunch", "dinner", "happyhour","dessert", "drinks");

  // This lets us pass in a variable from gradle to set the APP_ROOT from the command line like so:
  // gradle run -PAPP_ROOT="/jeremys-list/"
  // Yes, there's no space between the -P and APP_ROOT.
  if (args.length > 0) {
    APP_ROOT = args[0];
  } else {
    if (PRODUCTION) {
      APP_ROOT = "/jeremys-list-dev/";
    } else {
      APP_ROOT = "";
    }
  }

  // get("css/app.css", (request, response) -> {
  //   LessSource.FileSource less = new LessSource.FileSource(new File("src/main/resources/public/app.css"));
  //   LessCompiler compiler = new DefaultLessCompiler();
  //   Configuration config = new Configuration();
  //
  //   config.addExternalVariable("@APP_PATH", "TEXT");
  //   return compiler.compile(less, configuration).getCss();
  // });

  get("/", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    model.put("APP_ROOT", APP_ROOT);
    model.put("restaurants", Restaurant.all());
    model.put("categories", Category.all());
    model.put("template", "templates/home.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/category/:type", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    String category = request.params("type");
    Category newCategory = Category.findByType(category);
    model.put("APP_ROOT", APP_ROOT);
    model.put("newCategory", newCategory);
    model.put("restaurants", Restaurant.all());
    model.put("categories", Category.all());
    model.put("newCategory", newCategory);
    model.put("template", "templates/home.vtl");
    return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());;

  get("/category/:type/restaurants", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    String category = request.params("type");
    Category newCategory = Category.findByType(category);
    model.put("APP_ROOT", APP_ROOT);
    model.put("newCategory", newCategory);
    model.put("restaurants", Restaurant.all());
    model.put("categories", Category.all());
    return new ModelAndView(model, "templates/restaurants.vtl");
    }, new VelocityTemplateEngine());;

  get("/add-restaurants", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    model.put("APP_ROOT", APP_ROOT);
    model.put("restaurants", Restaurant.all());
    model.put("categories", Category.all());
    model.put("template", "templates/admin.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/new-category", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    String type = request.queryParams("type");
    Category newCategory = new Category(type);
    newCategory.save();

    model.put("restaurants", Restaurant.all());
    model.put("categories", Category.all());
    model.put("template", "templates/admin.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/new-restaurant", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();

    String name = request.queryParams("name");
    String address = request.queryParams("address");
    String phone = request.queryParams("phone");
    String website = request.queryParams("website");
    String yelp = request.queryParams("yelp");
    String hours = request.queryParams("hours");
    Restaurant newRestaurant = new Restaurant(name, address, phone, website, yelp, hours);
    newRestaurant.save();
    int categoryId = Integer.parseInt(request.queryParams("categoryId"));
    Category newCategory = Category.find(categoryId);

    newRestaurant.addCategory(newCategory);

    model.put("restaurants", Restaurant.all());
    model.put("categories", Category.all());
    model.put("template", "templates/admin.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/add-category", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    Category newCategory = Category.find(Integer.parseInt(request.queryParams("categoryId")));
    Restaurant newRestaurant = Restaurant.find(Integer.parseInt(request.queryParams("restaurantId")));
    newRestaurant.addCategory(newCategory);

    model.put("restaurants", Restaurant.all());
    model.put("categories", Category.all());
    model.put("template", "templates/admin.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());




  }//end of main
}//end of app

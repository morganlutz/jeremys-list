package jeremyslist;

public class Category {
  private String type;
  private int id;

  public Category(String type) {
    this.type = type;
  }

  public int getId() {
    return id;
  }

  public String getType() {
    return type;
  }

}

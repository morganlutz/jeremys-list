package jeremyslist;

public class Tag {
  private String description;
  private int id;

  public Tag(String description) {
    this.description = description;
  }

  public int getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setId(int id) {
    this.id = id;
  }
}

package jeremys-list.data.models;

public class Quadrant {
  private int id;
  private String quadrant;

  public Quadrant(String quadrant) {
    this.quadrant = quadrant;
  }

  public int getId() {
    return id;
  }

  public String getQuadrant() {
    return quadrant;
  }
}

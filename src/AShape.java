import java.awt.Color;
import java.util.Objects;

public class AShape {

  private int id;
  private int x;
  private int y;
  private int w;
  private int h;
  private int r;
  private int g;
  private int b;

  public AShape(int id, int x, int y, int w, int h, int r, int g, int b) {
    if (w <= 0 || h <= 0) {
      throw new IllegalArgumentException("Width and height cannot be equal or below 0.");
    }

    if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
      throw new IllegalArgumentException("RGB values must be between 0 and 255.");
    }

    this.id = id;
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.r = r;
    this.g = g;
    this.b = b;
  }

  public int getId() {
    return this.id;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public int getW() {
    return this.w;
  }

  public int getH() {
    return this.h;
  }

  public int getR() {
    return this.r;
  }

  public int getG() {
    return this.g;
  }

  public int getB() {
    return this.b;
  }

  public int getArea() {
    return w * h;
  }

  /**
   * Stub method. How we represent color could change.
   * @return the color of the AShape.
   */
  public Color getColor() {
    return new Color(r, g, b);
  }


  @Override
  public boolean equals(Object that) {
    if (that == this) {
      return true;
    }

    if (!(that instanceof AShape)) {
      return false;
    }

    AShape shape = (AShape) that;

    return (shape.id == this.id && shape.x == this.x && shape.y == this.y && shape.w == this.w && shape.h == this.h &&
        shape.r == this.r && shape.g == this.g && shape.b == this.b);

  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y, w, h, r, g, b);
  }

}

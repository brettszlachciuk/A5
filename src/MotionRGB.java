public class MotionRGB implements IMotion{

  private final int r;
  private final int g;
  private final int b;
  private final int startTick;
  private final int endTick;

  public MotionRGB(int r, int g, int b, int startTick, int endTick) {
    if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
      throw new IllegalArgumentException("RGB values must be between 0 and 255.");
    }
    this.r = r;
    this.g = g;
    this.b = b;

    if (endTick <= startTick) {
      throw new IllegalArgumentException("The start tick must be less than the end tick.");
    }

    this.startTick = startTick;
    this.endTick = endTick;
  }

  @Override
  public AShape apply(AShape shape) {
    return new AShape(shape.getId(), shape.getX(), shape.getY(), shape.getW(), shape.getH(), this.r, this.g,
        this.b);
  }

  @Override
  public int getStartTick() {
    return this.startTick;
  }

  @Override
  public int getEndTick() {
    return this.endTick;
  }
}

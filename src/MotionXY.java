public class MotionXY implements IMotion{

  private final int x;
  private final int y;
  private int startTick;
  private int endTick;

  public MotionXY(int x, int y, int startTick, int endTick) {
    this.x = x;
    this.y = y;

    if (endTick <= startTick) {
      throw new IllegalArgumentException("The start tick must be less than the end tick.");
    }

    this.startTick = startTick;
    this.endTick = endTick;
  }

  public int getStartTick() {
    return this.startTick;
  }

  public int getEndTick() {
    return this.endTick;
  }

  @Override
  public AShape apply(AShape shape) {
    return new AShape(shape.getId(), this.x, this.y, shape.getW(), shape.getH(), shape.getR(), shape.getG(),
        shape.getB());
  }

  @Override
  public String toString() {
    String out = "";

    return "" + startTick + shape.get
  }
}

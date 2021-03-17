public class MotionWH implements IMotion{

  private final int w;
  private final int h;
  private int startTick;
  private int endTick;

  public MotionWH(int w, int h, int startTick, int endTick) {
    if (w <= 0 || h<= 0) {
      throw new IllegalArgumentException("Width and height must be greater than 0");
    }
    this.w = w;
    this.h = h;

    if (endTick <= startTick) {
      throw new IllegalArgumentException("The start tick must be less than the end tick.");
    }

    this.startTick = startTick;
    this.endTick = endTick;
  }

  @Override
  public AShape apply(AShape shape) {
    return new AShape(shape.getId(), shape.getX(), shape.getY(), this.w, this.h, shape.getR(), shape.getG(),
        shape.getB());
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

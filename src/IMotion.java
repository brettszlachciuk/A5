public interface IMotion {

  AShape apply(AShape shape);

  public int getStartTick();

  public int getEndTick();

  public String toString();
}

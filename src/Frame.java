public class Frame implements IFrame{

  private final AShape shape;

  private final IMotion motion;

  public Frame(AShape shape, IMotion motion) {
    this.shape = shape;
    this.motion = motion;
  }


  @Override
  public AShape getShape() {
    return new AShape(shape.getId(), shape.getX(), shape.getY(), shape.getW(),
        shape.getH(), shape.getR(), shape.getG(), shape.getB());
  }

  @Override
  public IMotion getMotion() {
    return motion;
  }


}

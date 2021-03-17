import java.util.List;

public interface IAnimatorModel {

  List<AShape> getAllShapesAtFrame() throws UnsupportedOperationException;

  List<AShape> getAllShapes();

  boolean validAnimation();

  void addShape(AShape Shape, List<IMotion> motions);

  void removeShape(AShape shape);

  void addMotion(AShape shape, IMotion motion);

  String toString();
}

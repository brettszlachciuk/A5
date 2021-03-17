import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AnimatorModel implements IAnimatorModel{


  //I think we need the frame class. After, we will have a
  //Map<AShape, List<Frame>. this way we can see the beginning state of the shape, represented by the
  //AShape in the frame class, then we can obtain the info of the shape's state after the motion and how the motion
  //transformed it by using the new arguments in the motion class.
  //I ran into these problems when thinking about toString, but I think these changes would fix them.

  private Map<AShape, List<IMotion>> motionsByShape;

  private List<AShape> allShapes;

  private final int minX;

  private final int maxX;

  private final int minY;

  private final int maxY;

  private final int maxFrame;


  public AnimatorModel(int minX, int maxX, int minY, int maxY, int maxFrame) {
    this.minX = minX;
    this.maxX = maxX;
    this.minY = minY;
    this.maxY = maxY;
    this.maxFrame = maxFrame;
  }

  public AnimatorModel(int minX, int maxX, int minY, int maxY, int maxFrame,
      Map<AShape, List<IMotion>> motionsByShape, List<AShape> allShapes) {
    this.minX = minX;
    this.maxX = maxX;
    this.minY = minY;
    this.maxY = maxY;
    this.maxFrame = maxFrame;
    this.motionsByShape = motionsByShape;
    this.allShapes = allShapes;
    this.validAnimation();

    for (Map.Entry<AShape, List<IMotion>> element : motionsByShape.entrySet()) {
      List<IMotion> motions = element.getValue();
      sortByTick(motions);
    }
  }


  @Override
  public List<AShape> getAllShapesAtFrame() throws UnsupportedOperationException {
    return null;
  }

  @Override
  public List<AShape> getAllShapes() {
    List<AShape> shapesCopy = new ArrayList<>();

    for (AShape shape : allShapes) {
      shapesCopy.add(shape);
    }

    return shapesCopy;
  }


  @Override
  public boolean validAnimation() {
    for (int i = 0; i <= maxFrame; i++) {
      if (!checkMotions(i)) {
        return false;
      }
    }

    if (!noOverlaps()) {
      return false;
    }

    return true;
  }

  @Override
  public void addShape(AShape shape, List<IMotion> motions) {
    for (AShape s: getAllShapes()) {
      if (shape.equals(s)) {
        throw new IllegalArgumentException("Cannot add duplicate shapes.");
      }
    }
    allShapes.add(shape);

    if (motions.size() == 0) {
      motionsByShape.put(shape, new ArrayList<>());
    }

    motionsByShape.put(shape, motions);
  }

  @Override
  public void removeShape(AShape shape) {
    if (shape == null) {
      throw new IllegalArgumentException("Shape cannot be null.");
    }

    boolean containsShape = false;

    for (AShape s: getAllShapes()) {
      if (shape.equals(s)) {
        containsShape = true;
      }
    }

    if (!containsShape) {
      throw new IllegalArgumentException("Shape does not exist.");
    }

    allShapes.remove(shape);
    motionsByShape.remove(shape);
  }

  @Override
  public void addMotion(AShape shape, IMotion motion) {
    List<IMotion> currMotions = motionsByShape.get(shape);
    currMotions.add(motion);
    sortByTick(currMotions);
    motionsByShape.put(shape, currMotions);
  }

  @Override
  public String toString() {
    //Improvement: come up with a way to organize all motions by class in the toString method.
    String out = "";

    for (Map.Entry<AShape, List<IMotion>> element : motionsByShape.entrySet()) {
      AShape shape = element.getKey();
      List<IMotion> motions = element.getValue();

      //figuring out how to obtain the before and after shape details of each motion
      String motioninfo = shape.toString() + " " +
    }

    return out;
  }

  private boolean checkMotions(int frame) {
    int target = allShapes.size() - 1;
    int correctMotions = 0;

    for (Map.Entry<AShape, List<IMotion>> element : motionsByShape.entrySet()) {
      List<IMotion> motions = element.getValue();

      for (IMotion motion: motions) {
        if (motion.getStartTick() <= frame && motion.getEndTick() >= frame) {
          correctMotions += 1;
          break;
        }
      }
    }
    return correctMotions == target;
  }

  private boolean noOverlaps() {
    for (Map.Entry<AShape, List<IMotion>> element : motionsByShape.entrySet()) {
      List<IMotion> motions = element.getValue();

      //separate motions in the list of motions by class and check this for each class
      for (int i = 0; i < motions.size() - 2; i++) {
        //check if the end tick of a motion is not greater than the start tick of the next one.
        if (motions.get(i).getEndTick() > motions.get(i + 1).getStartTick()) {
          return false;
        }
        if (i > 0) {
          //check if the start tick of a motion is not greater than the end tick of the previous.
          if (motions.get(i).getStartTick() < motions.get(i - 1).getEndTick()) {
            return false;
          }
        }
      }

    }

    return false;
  }

  private List<IMotion> sortByTick(List<IMotion> motions) {
    List<Integer> sortedStartTicks = new ArrayList<>();
    List<IMotion> sortedMotions = new ArrayList<>();

    for (IMotion motion : motions) {
      sortedStartTicks.add(motion.getStartTick());
    }

    Collections.sort(sortedStartTicks);

    for (Integer i : sortedStartTicks) {
      for (IMotion motion : motions) {
        if (i == motion.getStartTick()) {
          sortedMotions.add(motion);
          break;
        }
      }
    }
    return sortedMotions;
  }



}

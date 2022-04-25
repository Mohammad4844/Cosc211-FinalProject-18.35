package application;

import javafx.scene.layout.Region;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

/**
 * An object that draws out an H-Branch given a size and coordinates in an xy plane.
 */
public class HBranch extends Region {
  /**
   * Size of the H Branch. Specifically, half the distance on any one of the hands.
   */
  private double size;
  /**
   * An array containing the X and Y coordinates of the center of the H Branch.
   */
  private double[] center = new double[2];

  /**
   * Default constructor for H Branch.
   */
  public HBranch(double size, double centerX, double centerY) {
    center[0] = centerX;
    center[1] = centerY;
    this.size = size;
    createHBranch();
  }

  /**
   * Helper method that maps out and creates the H Branch.
   */
  private void createHBranch() {
    Path rightSide = new Path(new MoveTo(getCenterX(), getCenterY()),
        new LineTo(getCenterX() + size, getCenterY()),
        new LineTo(getEndPointX(1), getEndPointY(1)), new LineTo(getEndPointX(2), getEndPointY(2)));

    Path leftSide = new Path(new MoveTo(getCenterX(), getCenterY()),
        new LineTo(getCenterX() - size, getCenterY()),
        new LineTo(getEndPointX(3), getEndPointY(3)), new LineTo(getEndPointX(4), getEndPointY(4)));

    getChildren().addAll(rightSide, leftSide);
  }

  /**
   * Returns the X coordinate of the nth endpoint. Endpoints are numbered 1-4, where 1 is the top
   * right end, and the rest are followed in a clockwise direction
   */
  public double getEndPointX(int n) {
    return switch (n) {
      case 1, 2 -> getCenterX() + size;
      case 3, 4 -> getCenterX() - size;
      default -> {
        throw new IllegalArgumentException("Incorrect endpoint number entered");
      }
    };
  }

  /**
   * Returns the Y coordinate of the nth endpoint. Endpoints are numbered 1-4, where 1 is the top
   * right end, and the rest are followed in a clockwise direction.
   */
  public double getEndPointY(int n) {
    return switch (n) {
      case 1, 4 -> getCenterY() - size;
      case 2, 3 -> getCenterY() + size;
      default -> {
        throw new IllegalArgumentException("Incorrect endpoint number entered");
      }
    };
  }

  /**
   * Returns the X coorinate of the center
   */
  public double getCenterX() {
    return center[0];
  }

  /**
   * Returns the Y coorinate of the center
   */
  public double getCenterY() {
    return center[1];
  }

  public double getSize() {
    return size;
  }
}

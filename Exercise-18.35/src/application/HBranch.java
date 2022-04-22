package application;

import java.util.ArrayList;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Region;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class HBranch extends Region {
  private DoubleProperty sizeProperty = new SimpleDoubleProperty(); 
  private ArrayList<double[]> endPoints = new ArrayList<>();
  private double[] center = new double[2];

  public HBranch(double size, double centerX, double centerY) {
    center[0] = centerX;
    center[1] = centerY;
    sizeProperty.set(size);
    calculateEndPoints();

    createHBranch();
  }

  private void createHBranch() {
    Path rightSide = new Path(new MoveTo(getCenterX(), getCenterY()), 
        new LineTo(getCenterX() + getSize(), getCenterY()),
        new LineTo(getEndPointX(1), getEndPointY(1)),
        new LineTo(getEndPointX(2), getEndPointY(2)));

    Path leftSide = new Path(new MoveTo(getCenterX(), getCenterY()),
        new LineTo(getCenterX() - getSize(), getCenterY()),
        new LineTo(getEndPointX(3), getEndPointY(3)),
        new LineTo(getEndPointX(4), getEndPointY(4)));

    getChildren().addAll(rightSide,leftSide);
   }

  private void calculateEndPoints() {
    endPoints.add(new double[] {getCenterX() + getSize(), getCenterY() - getSize()});
    endPoints.add(new double[] {getCenterX() + getSize(), getCenterY() + getSize()});
    endPoints.add(new double[] {getCenterX() - getSize(), getCenterY() - getSize()});
    endPoints.add(new double[] {getCenterX() - getSize(), getCenterY() + getSize()});
  }


  /**
   * Returns the x coordinate of the endpoint specified
   * 
   * @param endPoint Specify which endpoint is wanted, 1-4 (where 1 is the top right end, and the
   *        rest are followed by a clockwise direction)
   * @return
   */
  public double getEndPointX(int endPoint) {
    return endPoints.get(endPoint - 1)[0];
  }

  /**
   * Returns the y coordinate of the endpoint specified
   * 
   * @param endPoint Specify which endpoint is wanted, 1-4 (where 1 is the top right end, and the
   *        rest are followed by a clockwise direction)
   * @return
   */
  public double getEndPointY(int endPoint) {
    return endPoints.get(endPoint - 1)[1];
  }

  public double getCenterX() {
    return center[0];
  }

  public double getCenterY() {
    return center[1];
  }
  
  public double getSize() {
    return sizeProperty.doubleValue();
  }
}

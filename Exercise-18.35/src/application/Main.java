package application;

import java.util.Scanner;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


public class Main extends Application {
  @Override
  public void start(Stage primaryStage) {
    StackPane pane = new StackPane();
    // Any order of 7 or more will probably break
    System.out.print("Enter an order:");
    Scanner s =new Scanner(System.in) ;
    int order = s.nextInt();

    createHTree(pane, order);

    Scene scene = new Scene(pane, 700, 700);

    setupStage(primaryStage, scene);
    
    
  }

  private void setupStage(Stage primaryStage, Scene scene) {
    primaryStage.setTitle("Cosc211 | Final Project | H-Tree Fractal");
    primaryStage.setMinWidth(700);
    primaryStage.setMinHeight(700);
    primaryStage.setMaxWidth(700);
    primaryStage.setMaxHeight(700);
    primaryStage.setScene(scene);
    primaryStage.show();
    
  }

  public void createHTree(Pane pane, int order) {
    createHTree(pane, order, 80, 350, 250);
  }

  public void createHTree(Pane pane, int order, double size, double centerX, double centerY) {
    HBranch branch = new HBranch(size, centerX, centerY);
    pane.getChildren().add(branch);
    if (order == 0)
      return;
    else
      for (int i = 1; i <= 4; i++) {
        createHTree(pane, order - 1, size / 2, branch.getEndPointX(i), branch.getEndPointY(i));
      }
  }

  public static void main(String[] args) {
    launch(args);
  }
}

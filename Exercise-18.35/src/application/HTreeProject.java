package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class HTreeProject extends Application {
  @Override
  public void start(Stage primaryStage) {
    StackPane mainBodyPane = new StackPane();
    addTitle(mainBodyPane);
    TextField promptBox = addPromptBox(mainBodyPane);
    Text errorMessage = addErrorMessage(mainBodyPane);
    mainBodyPane.getChildren().add(new Text()); //Dummy child (Text) is added to make sure our event runs smoothly
    
    promptBox.setOnKeyPressed((event) -> {
      if (event.getCode() == KeyCode.ENTER) {
        try {
          int order = Integer.parseInt(promptBox.getText());
          if (order < 0 || order > 6)
            throw new Exception();
          mainBodyPane.getChildren().remove(3);
          createHTree(order);
          mainBodyPane.getChildren().add(createHTree(order));
          errorMessage.setText("");
        } catch (Exception exception) {
          errorMessage.setText("*You entered an incorrect value");
        }
      }
    });
    
    mainBodyPane.getChildren();
    
    Scene scene = new Scene(mainBodyPane, 700, 700);

    setupStage(primaryStage, scene);
    }

  private void addTitle(StackPane pane) {
    Text title = new Text("H-Tree Fractal");
    title.setTranslateY(50);
    title.setFill(Color.LIGHTBLUE);
    title.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 30));
    title.setStroke(Color.BLACK);
    title.setStrokeWidth(1.5);
    title.setUnderline(true);
    title.setTranslateY(-250);
    pane.getChildren().add(title);
    
  }

  private Text addErrorMessage(Pane pane) {
    Text errorMessage = new Text();
    errorMessage.setTranslateY(50);
    errorMessage.setFill(Color.DARKRED);
    errorMessage.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.ITALIC, 13));
    errorMessage.setTranslateY(200);
    pane.getChildren().add(errorMessage);
    return errorMessage;
  }

  private TextField addPromptBox(Pane pane) {
    TextField promptBox = new TextField();
    promptBox.setMaxWidth(200);
    promptBox.setPromptText("Enter a number from 0 to 6");
    promptBox.setFocusTraversable(false);
    promptBox.setTranslateY(170); 
    pane.getChildren().add(promptBox);
    return promptBox;
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

  public StackPane createHTree(int order) {
    StackPane pane = new StackPane();
    createHTree(pane, order, 80, 350, 300);
   
    return pane;
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

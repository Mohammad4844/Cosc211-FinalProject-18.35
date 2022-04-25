package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * A program that draws out an H Tree of order n, input by the user. Only orders of 0 to 6 are
 * allowed as anything more breaks the program.
 */
public class Main extends Application {

  @Override
  public void start(Stage primaryStage) {
    StackPane mainBodyPane = new StackPane();
    addTitle(mainBodyPane);
    TextField promptBox = addPromptBox(mainBodyPane);
    Text errorMessage = addErrorMessage(mainBodyPane);
    // Dummy node (Text) is added to make sure our later event runs smoothly first time around
    mainBodyPane.getChildren().add(new Text());
    // Action when user presses enter key in the box
    promptBox.setOnKeyPressed((event) -> {
      if (event.getCode() == KeyCode.ENTER) {
        try {
          int order = Integer.parseInt(promptBox.getText());
          if (order < 0 || order > 6)
            throw new Exception();
          mainBodyPane.getChildren().remove(3); // Remove the previous H-Tree
          createHTree(order);
          mainBodyPane.getChildren().add(createHTree(order));
          errorMessage.setVisible(false);
        } catch (Exception exception) {
          errorMessage.setVisible(true);
        }
      }
    });
    setupStage(primaryStage, new Scene(mainBodyPane));
  }

  /**
   * Helper method that adds the Title (of type Text) to a given pane.
   */
  private void addTitle(StackPane pane) {
    Text title = new Text("H-Tree Fractal");
    title.setStyle("-fx-font: italic bold 30px Arial; -fx-stroke: Black; -fx-stroke-width: 1.5; "
        + "-fx-fill: LightBlue;-fx-underline: true; -fx-translate-y: -280px;");
    pane.getChildren().add(title);
  }

  /**
   * Helper method that adds an Error Message (of type Text) to a given pane. Also returns a
   * reference to the Text.
   */
  private Text addErrorMessage(Pane pane) {
    Text errorMessage = new Text();
    errorMessage
        .setStyle("-fx-fill: DarkRed; -fx-font: italic 13px Arial; -fx-translate-y: 330px;");
    errorMessage.setVisible(false);
    errorMessage.setText("*You entered an incorrect value");
    pane.getChildren().add(errorMessage);
    return errorMessage;
  }

  /**
   * Helper method that adds a Prompt Box (of type TextField) to a given pane. Also returns a
   * reference to the TextField.
   */
  private TextField addPromptBox(Pane pane) {
    TextField promptBox = new TextField();
    promptBox
        .setStyle("-fx-max-width: 200px; -fx-focus-traversable: false; -fx-translate-y: 300px; "
            + "-fx-border-width: 1px; -fx-border-color: darkgrey; -fx-border-radius: 3px; ");
    promptBox.setPromptText("Enter a number from 0 to 6");
    pane.getChildren().add(promptBox);
    return promptBox;
  }

  /**
   * Helper method that sets up and displays a stage with a given scene.
   */
  private void setupStage(Stage stage, Scene scene) {
    stage.setTitle("Cosc211 | Final Project | H-Tree Fractal");
    stage.setWidth(800);
    stage.setHeight(800);
    stage.setResizable(false);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Starter method that creates and returns an H Tree of the order requested. H Tree is returned in
   * the form of a StackPane.
   */
  private StackPane createHTree(int order) {
    StackPane pane = new StackPane();
    createHTree(pane, order, 120, 400, 400);
    return pane;
  }

  /**
   * Method that recursively calls itself to create an H Tree of the order required, given the size
   * and center coordinates. This method uses tail-recursion. Time complexity of method is
   * exponential / O(2^n), details of explanation is in Time_Complexity_of_H-Tree.pdf (linked in the
   * README file)
   */
  private void createHTree(Pane pane, int order, double size, double centerX, double centerY) {
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

package com.crystalpixel.application.window;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;

import com.goxr3plus.fxborderlessscene.borderless.BorderlessScene;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainWindow extends Application {

    VBox tabBox;
    HBox currentTab;
    HBox tabs;
    VBox activeBox;

    @Override
    public void start(Stage primaryStage) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        BorderPane root = new BorderPane();
        BorderlessScene scene = new BorderlessScene(primaryStage, StageStyle.UNDECORATED, root, 480, 420);

        Method method = Scene.class.getDeclaredMethod("init", double.class, double.class);
        method.setAccessible(true);
        method.invoke(scene, 1240, 500);

        scene.removeDefaultCSS();
        scene.setFill(Color.web("0x282D33"));

        HBox title = new TitleBar(primaryStage);
        scene.setMoveControl(title);

        Pane rectangle = new Pane();
        rectangle.setPrefSize(35, scene.getHeight() - 55);
        rectangle.setStyle("-fx-background-color: #202529;");

        Pane rectangle2 = new Pane();
        rectangle2.setPrefSize(300, scene.getHeight() - 55);
        rectangle2.setStyle("-fx-background-color: #343B43;");

        ComponentResizer.makeResizable(rectangle2);

        Pane rectangle3 = new Pane();
        rectangle3.setStyle("-fx-background-color: #282D33;");
        rectangle3.setPrefSize(scene.getWidth() - rectangle2.getWidth() - 335, scene.getHeight() - 55);

        tabBox = new VBox();
        tabBox.setStyle("-fx-background-color: #202529;");
        tabBox.setPrefSize(scene.getWidth() - rectangle2.getWidth() - 335, 25);

        currentTab = new HBox();

        tabs = new HBox();

        activeBox = new VBox(0);

        HBox activeBar = new HBox();

        PaneList panes = new PaneList(new StackPane(new Label("Main Window")), new StackPane(new Label("+")));

        Stream.of(panes.getStackPanes().stream()).flatMap(paneStream -> paneStream).forEach(pane -> {
            pane.setPrefHeight(19);
            Label label = getLabelFromPane(pane);
            Font customFont = Font.loadFont(this.getClass().getResourceAsStream("/font/OCR-A.ttf"), 10);
            label.setPadding(new Insets(8, 20, 8, 20));
            label.setFont(customFont);
            label.setTextFill(Color.WHITE);
            label.setAlignment(Pos.CENTER);
            tabs.getChildren().add(pane);
            StackPane  Spane = new StackPane ();
            Spane.setUserData(pane);
            activeBar.getChildren().add(Spane);

            if (label.getText().equals("+")) {
                pane.setOnMouseClicked(event -> {
                    int index = tabs.getChildren().size() - 1;
                    StackPane newButton = new StackPane(new Label("New Button"));
                    Label labelnew = getLabelFromPane(newButton);
                    labelnew.setPadding(new Insets(5, 20, 5, 20));
                    labelnew.setFont(customFont);
                    labelnew.setTextFill(Color.WHITE);
                    labelnew.setAlignment(Pos.CENTER);
                    tabs.getChildren().add(index, newButton);
                });
            } else {
                pane.setOnMouseClicked(event -> {
                    String paneText = label.getText();
                    switch (paneText) {
                        case "Main Window":
                        for (Node node : activeBar.getChildren()) {
                            StackPane indicator = (StackPane ) node;
                            if (indicator.getUserData() == pane) {
                                System.out.println("Yes");
                                pane.setStyle("-fx-background-color: #343B43;");
                                label.setFont(customFont);
                                indicator.setPrefSize(pane.getWidth(), 1);
                                indicator.setStyle("-fx-background-color: #EC407A; -fx-margin: 0; -fx-border-width: 0;");
                            } else {
                                
                            }
                        }
                            break;
                        default:
                            break;
                    }
                });
            }
        });

        
        activeBox.getChildren().addAll(tabs, activeBar);
        tabBox.getChildren().addAll(activeBox, currentTab);

        rectangle3.getChildren().add(tabBox);

        HBox middle = new HBox();
        middle.getChildren().addAll(rectangle, rectangle2, rectangle3);

        root.setTop(title);
        root.setCenter(middle);

        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            double newHeight = newValue.doubleValue() - 55;
            rectangle.setPrefHeight(newHeight);
            rectangle2.setPrefHeight(newHeight);
            rectangle3.setPrefHeight(newHeight);
        });

        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            double newWidth = newValue.doubleValue() - rectangle2.getWidth() - 335;
            rectangle3.setPrefWidth(newWidth);
            tabBox.setPrefWidth(scene.getWidth());
        });

        rectangle2.widthProperty().addListener((observable, oldValue, newValue) -> {
            double newWidth = scene.getWidth() - newValue.doubleValue() - 335;
            rectangle3.setPrefWidth(newWidth);
            tabBox.setPrefWidth(scene.getWidth() - newValue.doubleValue());
        });

        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("/img/Icons/icon.png")));
        primaryStage.show();
    }

    private Label getLabelFromPane(Pane pane) {
        for (Node node : pane.getChildren()) {
            if (node instanceof Label) {
                return (Label) node;
            }
        }
        return null; // Retourne null si aucun Label n'est trouvé dans le Pane
    }

    public static void main(String[] args) {
        launch();
    }
}


package com.crystalpixel.application.window;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;

import com.goxr3plus.fxborderlessscene.borderless.BorderlessScene;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
        tabBox.setPrefSize(scene.getWidth() - rectangle2.getWidth() - 335, 20);

        currentTab = new HBox();

        tabs = new HBox();

        activeBox = new VBox(0);

        HBox activeBar = new HBox();

        ButtonList byButton = new ButtonList(new Button("Main Window"), new Button("+"));
        byButton.customizeButton();

        Stream.of(byButton.getButtons().stream()).flatMap(buttonStream -> buttonStream).forEach(button -> {
            button.setPrefHeight(19);
            tabs.getChildren().add(button);
            StackPane  pane = new StackPane ();
            pane.setUserData(button);
            activeBar.getChildren().add(pane);

            if (button.getText().equals("+")) {
                button.setOnAction(event -> {
                    int index = tabs.getChildren().size() - 1;
                    Button newButton = new Button("New Button");
                    newButton.setTextFill(Color.WHITE);
                    newButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-font-size: 12px; -fx-background-radius: 0;");
                    tabs.getChildren().add(index, newButton);
                });
            } else {
                button.setOnAction(event -> {
                    String buttonText = button.getText();
                    switch (buttonText) {
                        case "Main Window":
                        for (Node node : activeBar.getChildren()) {
                            StackPane indicator = (StackPane ) node;
                            if (indicator.getUserData() == button) {
                                button.setStyle("-fx-background-color: #343B43; -fx-background-radius: 0; -fx-margin: 0; -fx-border-width: 0;");
                                indicator.setPrefSize(button.getWidth() - 1, 1);
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
        primaryStage.getIcons().add(new Image(MainWindow.class.getResourceAsStream("/resources/img/icons/icon.png")));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}


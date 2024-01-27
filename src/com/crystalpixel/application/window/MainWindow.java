package com.crystalpixel.application.window;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.goxr3plus.fxborderlessscene.borderless.BorderlessScene;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainWindow extends Application {

    @Override
    public void start(Stage primaryStage) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        BorderPane root = new BorderPane();
        BorderlessScene scene = new BorderlessScene(primaryStage, StageStyle.UNDECORATED, root, 480, 420);

        Method method = Scene.class.getDeclaredMethod("init", double.class, double.class);
        method.setAccessible(true);
        method.invoke(scene, 1240, 500);

        scene.removeDefaultCSS();
        scene.setFill(Color.web("0x282D33"));

        HBox title = createTitleBar(primaryStage);
        scene.setMoveControl(title);

        Rectangle rectangle = new Rectangle(35, scene.getHeight() - 55);
        rectangle.setFill(Color.web("0x202529"));

        Rectangle rectangle2 = new Rectangle(300, scene.getHeight() - 55);
        rectangle2.setFill(Color.web("0x343B43"));

        Rectangle rectangle3 = new Rectangle(scene.getWidth() - 335, scene.getHeight() - 55);
        rectangle3.setFill(Color.web("0x282D33"));

        HBox middle = new HBox();
        middle.getChildren().addAll(rectangle, rectangle2, rectangle3);

        root.setTop(title);
        root.setCenter(middle);

        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            rectangle.setHeight(newValue.doubleValue() - 55);
            rectangle2.setHeight(newValue.doubleValue() - 55);
            rectangle3.setHeight(newValue.doubleValue() - 55);
        });

        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            rectangle3.setWidth(newValue.doubleValue() - 335);
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createTitleBar(Stage stage) {
        return new TitleBar(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

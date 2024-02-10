package com.crystalpixel.application.window;

import java.util.stream.Stream;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

public class TitleBar extends HBox {
    public TitleBar(Stage stage) {
        this.setStyle("-fx-background-color: rgb(53, 56, 64);");
        this.setMinHeight(35);

        ButtonList fileButtons = new ButtonList(new Button(), new Button("File"),
                new Button("Edit"), new Button("Window"),
                new Button("Tools"), new Button("Help"));

        fileButtons.customizeButton();

        ButtonList sysButtons = new ButtonList(new Button("-"),
                new Button("+"), new Button("X"));

        fileButtons.setStyle("-fx-background-color: rgb(53, 56, 64);");
        sysButtons.setStyle("-fx-background-color: rgb(53, 56, 64);");

        sysButtons.customizeButton();
        sysButtons.initializeSysButtons(stage);

        Stream.of(fileButtons.getButtons().stream()).flatMap(buttonStream -> buttonStream).forEach(button -> {
            if (button.getText().equals("File")) {
                button.setPadding(new Insets(10,20,0,15));
            }
            else if (button.getText().isEmpty()) {

                Image icon = new Image(this.getClass().getResourceAsStream("/img/Icons/icon.png"));
                    
                ImageView iconView = new ImageView(icon);
                iconView.setFitWidth(20);
                iconView.setFitHeight(20); 
                button.setGraphic(iconView);
                button.setPadding(new Insets(10, 0, 0, 5));
            } else {
                button.setPadding(new Insets(10, 20, 0, 0));
            }
            Font customFont = Font.font("Consolas", FontPosture.REGULAR, 12);
            button.setFont(customFont);
        });

        Stream.of(fileButtons.getButtons().stream(), Stream.of(new Spacer()), Stream.of(new TitleName()), Stream.of(new Spacer()), 
            sysButtons.getButtons().stream()).flatMap(buttonStream -> buttonStream).forEach(button -> this.getChildren().add(button));

    }
}

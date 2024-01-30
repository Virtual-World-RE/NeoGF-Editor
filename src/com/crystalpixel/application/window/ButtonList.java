package com.crystalpixel.application.window;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ButtonList extends AbstractList<Button> {

    private List<Button> buttons;

    public ButtonList() {
        this.buttons = new ArrayList<>();
    }

    public ButtonList(Button... buttons) {
        this.buttons = new ArrayList<>(Arrays.asList(buttons));
    }

    @Override
    public int size() {
        return buttons.size();
    }

    @Override
    public Button get(int index) {
        if (index < 0 || index >= this.buttons.size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.buttons.size());
        }
        return this.buttons.get(index);
    }

    public void addButton(Button button) {
        buttons.add(button);
    }

    public List<Button> getButtons() {
        return Collections.unmodifiableList(buttons);
    }

    public void customizeButton() {
        for (Button button : this.buttons) {
            button.setTextFill(Color.WHITE);
            button.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-font-size: 12px; -fx-background-radius: 0; -fx-background-radius: 0");
        }
    }

    public void createImageButton() {
        for (int i = 0; i < buttons.size(); i++) {
            Button button = buttons.get(i);
            int position = i;
            switch (position) {
                case 0:
                    Image image = new Image(new File("resources/img/NewFile.png").toURI().toString());
                    button.setGraphic(new ImageView(image));
            }
        }
    }

    public void initializeSysButtons(Stage stage) {
        for (int i = 0; i < buttons.size(); i++) {
            Button button = buttons.get(i);
            int position = i;
            switch (position) {
                case 0:
                    button.setOnAction(e -> stage.setIconified(true));
                    break;
                case 1:
                    button.setOnAction(e -> {
                        if (stage.isMaximized()) {
                            stage.setMaximized(false);
                        } else {
                            stage.setMaximized(true);
                            stage.setFullScreen(false);
                        }
                    });
                    break;
                case 2:
                    button.setOnAction(e -> System.exit(0));
                    break;
                default:
                    break;
            }
        }
    }

    public void setStyle(String string) {
        for (Button button : buttons) {
            button.setStyle(string);
        }
    }
}


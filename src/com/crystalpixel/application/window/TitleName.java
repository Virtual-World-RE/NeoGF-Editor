package com.crystalpixel.application.window;

import java.io.File;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TitleName extends HBox {
    public TitleName() {
        Text titleText = new Text("NeoGF Editor");
        titleText.setStyle("-fx-fill: white;");
        this.getChildren().add(titleText);
        this.setAlignment(Pos.TOP_CENTER);
        this.setPadding(new Insets(8,0,0,0));
        Font customFont = Font.loadFont(new File("resources/font/OCR-A.ttf").toURI().toString(), 12);
        titleText.setFont(customFont);
    }
}

package com.crystalpixel.application.window;

import javafx.scene.layout.HBox;

public class Spacer extends HBox {
    public Spacer() {
        HBox.setHgrow(this, javafx.scene.layout.Priority.ALWAYS);
    }
}

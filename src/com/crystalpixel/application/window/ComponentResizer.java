package com.crystalpixel.application.window;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ComponentResizer {
	private double startX, startY;
    private boolean dragging = false;
    private boolean resizing = false;
    private double mouseOffsetX, mouseOffsetY;

    private final double border = 4;
    private double minWidth;
    private double minHeight;

    private boolean top, right, bottom, left;
    private Stage stage;
    private Node titleBar;

    public ComponentResizer(Stage stage, Node titleBar) {
        this.stage = stage;
		this.titleBar = titleBar;
        this.minWidth = stage.getMinWidth();
        this.minHeight = stage.getMinHeight();

		stage.addEventHandler(MouseEvent.MOUSE_MOVED, mouseMoved);
        stage.addEventHandler(MouseEvent.MOUSE_PRESSED, mousePressed);
        stage.addEventHandler(MouseEvent.MOUSE_DRAGGED, mouseDragged);
		stage.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseReleased);

		titleBar.addEventHandler(MouseEvent.MOUSE_PRESSED, titleBarMousePressed);
        titleBar.addEventHandler(MouseEvent.MOUSE_DRAGGED, titleBarMouseDragged);
    }

    private EventHandler<MouseEvent> mouseMoved = event -> {
        if (resizing) {
            event.consume();
            return;
        }

        Bounds bounds = titleBar.localToScreen(titleBar.getBoundsInLocal());
        Point2D mouseLocation = new Point2D(event.getScreenX(), event.getScreenY());

        top = mouseLocation.getY() < bounds.getMinY() + border;
        bottom = mouseLocation.getY() > bounds.getMaxY() - border;
        left = mouseLocation.getX() < bounds.getMinX() + border;
        right = mouseLocation.getX() > bounds.getMaxX() - border;

        if (top && left) {
            stage.getScene().setCursor(Cursor.NW_RESIZE);
        } else if (top && right) {
            stage.getScene().setCursor(Cursor.NE_RESIZE);
        } else if (bottom && left) {
            stage.getScene().setCursor(Cursor.SW_RESIZE);
        } else if (bottom && right) {
            stage.getScene().setCursor(Cursor.SE_RESIZE);
        } else if (top) {
            stage.getScene().setCursor(Cursor.N_RESIZE);
        } else if (bottom) {
            stage.getScene().setCursor(Cursor.S_RESIZE);
        } else if (left) {
            stage.getScene().setCursor(Cursor.W_RESIZE);
        } else if (right) {
            stage.getScene().setCursor(Cursor.E_RESIZE);
        } else {
            stage.getScene().setCursor(Cursor.DEFAULT);
        }
    };

    private EventHandler<MouseEvent> mousePressed = event -> {
		if (!top && !bottom && !left && !right) {
            dragging = true;
        startX = event.getX();
        startY = event.getY();
		}
    };

    private EventHandler<MouseEvent> mouseDragged = event -> {
        double deltaX = event.getX() - startX;
        double deltaY = event.getY() - startY;
		if (!dragging && (top || right || bottom || left)) {
            resizing = true;
            event.consume();
        if (top || (left && right)) {
            double newHeight = stage.getHeight() - deltaY;
            if (newHeight > minHeight) {
                stage.setHeight(newHeight);
                stage.setY(stage.getY() + deltaY);
            }
        } else if (bottom) {
            double newHeight = stage.getHeight() + deltaY;
            if (newHeight > minHeight) {
                stage.setHeight(newHeight);
            }
        }

        if (left || (top && bottom)) {
            double newWidth = stage.getWidth() - deltaX;
            if (newWidth > minWidth) {
                stage.setWidth(newWidth);
                stage.setX(stage.getX() + deltaX);
            }
        } else if (right) {
            double newWidth = stage.getWidth() + deltaX;
            if (newWidth > minWidth) {
                stage.setWidth(newWidth);
            }
        }

        startX = event.getX();
        startY = event.getY();
	}
    };

	private EventHandler<MouseEvent> mouseReleased = event -> {
        dragging = false;
        resizing = false;
    };

	private EventHandler<MouseEvent> titleBarMousePressed = event -> {
        if (!top && !bottom && !left && !right) {
            dragging = true;
            mouseOffsetX = event.getScreenX() - stage.getX();
            mouseOffsetY = event.getScreenY() - stage.getY();
        }
    };

    private EventHandler<MouseEvent> titleBarMouseDragged = event -> {
        if (dragging) {
            stage.setX(event.getScreenX() - mouseOffsetX);
            stage.setY(event.getScreenY() - mouseOffsetY);
        }
    };
}

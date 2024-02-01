package com.crystalpixel.application.window;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javafx.scene.layout.StackPane;


public class PaneList extends AbstractList<StackPane> {

    private List<StackPane> panes;

    public PaneList() {
        this.panes = new ArrayList<>();
    }

    public PaneList(StackPane... panes) {
        this.panes = new ArrayList<>(Arrays.asList(panes));
    }

    @Override
    public int size() {
        return panes.size();
    }

    @Override
    public StackPane get(int index) {
        if (index < 0 || index >= this.panes.size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.panes.size());
        }
        return this.panes.get(index);
    }

    public void addButton(StackPane pane) {
        panes.add(pane);
    }

    public List<StackPane> getStackPanes() {
        return Collections.unmodifiableList(panes);
    }

    public void setStyle(String string) {
        for (StackPane pane : panes) {
            pane.setStyle(string);
        }
    }
}

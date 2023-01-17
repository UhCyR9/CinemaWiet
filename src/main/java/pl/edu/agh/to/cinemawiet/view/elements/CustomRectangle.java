package pl.edu.agh.to.cinemawiet.view.elements;

import javafx.scene.shape.Rectangle;

public class CustomRectangle extends Rectangle {

    private final Vector gridPosition;

    public CustomRectangle(Vector gridPosition) {
        this.gridPosition = gridPosition;
    }

    public Vector getGridPosition() {
        return gridPosition;
    }
}

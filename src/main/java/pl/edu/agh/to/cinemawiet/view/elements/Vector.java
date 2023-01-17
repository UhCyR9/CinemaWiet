package pl.edu.agh.to.cinemawiet.view.elements;

import java.util.Objects;

public record Vector(int row, int col) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return row == vector.row && col == vector.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}

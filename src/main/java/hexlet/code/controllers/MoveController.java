package hexlet.code.controllers;

import hexlet.code.exceptions.AlreadyOccupiedException;
import hexlet.code.exceptions.InvalidPointException;
import hexlet.code.model.Field;
import hexlet.code.model.Figure;
import hexlet.code.model.Point;

public class MoveController {
    public void applyFigure(final Field field,
                            final Point point,
                            final Figure figure) throws InvalidPointException,
            AlreadyOccupiedException {
        if (field.getFigure(point) != null) {
            throw new AlreadyOccupiedException();
        }
        field.setFigure(point, figure);
    }
}

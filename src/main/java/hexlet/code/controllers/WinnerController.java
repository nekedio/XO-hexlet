package hexlet.code.controllers;

import hexlet.code.exceptions.InvalidPointException;
import hexlet.code.model.Field;
import hexlet.code.model.Figure;
import hexlet.code.model.Point;

public class WinnerController {
    public Figure getWinner(final Field field) {
        try {
            for (int i = 0; i < 3; i++)
                if (check(field, new Point(i, 0), p -> new Point(p.getX(), p.getY() + 1)))
                    return field.getFigure(new Point(i, 0));

            for (int i = 0; i < 3; i++)
                if (check(field, new Point(0, i), p -> new Point(p.getX() + 1, p.getY())))
                    return field.getFigure(new Point(0, i));

            if (check(field, new Point(0, 0), p -> new Point(p.getX() + 1, p.getY() + 1)))
                return field.getFigure(new Point(0, 0));

            if (check(field, new Point(0, 2), p -> new Point(p.getX() + 1, p.getY() - 1)))
                return field.getFigure(new Point(1, 1));

        } catch (final InvalidPointException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean check(final Field field,
                          final Point currentPoint,
                          final IPointGenerator pointGenerator) {
        final Figure currentFigure;
        final Figure nextFigure;
        final Point nextPoint = pointGenerator.next(currentPoint);
        try {
            currentFigure = field.getFigure(currentPoint);

            if (currentFigure == null)
                return false;

            nextFigure = field.getFigure(nextPoint);
        } catch (final InvalidPointException e) {
            return true;
        }

        if (currentFigure != nextFigure)
            return false;

        return check(field, nextPoint, pointGenerator);
    }

    private interface IPointGenerator {

        Point next(final Point point);

    }
}

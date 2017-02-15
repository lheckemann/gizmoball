package gizmoball.view;

import gizmoball.model.Model;
import gizmoball.model.gizmos.ReadGizmo;

import java.awt.*;

public class TriangleView {

    public static void paint(Graphics2D graphics, ReadGizmo triangle) {
        graphics.setColor(Color.red);

        int numPoints = 3;

        int[] xCoords = new int[numPoints];
        xCoords[0] = triangle.getX();

        int[] yCoords = new int[numPoints];
        yCoords[0] = triangle.getY();

        switch (triangle.getRotation()) {
            case NORTH:
                xCoords[1] = triangle.getX() + triangle.getWidth();
                yCoords[1] = triangle.getY();
                xCoords[2] = triangle.getX();
                yCoords[2] = triangle.getY() + triangle.getHeight();
                break;
            case EAST:
                xCoords[1] = triangle.getX() + triangle.getWidth();
                yCoords[1] = triangle.getY();
                xCoords[2] = triangle.getX() + triangle.getWidth();
                yCoords[2] = triangle.getY() + triangle.getHeight();
                break;
            case SOUTH:
                xCoords[0] = triangle.getX() + triangle.getWidth();
                yCoords[0] = triangle.getY();
                xCoords[1] = triangle.getX();
                yCoords[1] = triangle.getY() + triangle.getHeight();
                xCoords[2] = triangle.getX() + triangle.getWidth();
                yCoords[2] = triangle.getY() + triangle.getHeight();
                break;
            case WEST:
                xCoords[1] = triangle.getX();
                yCoords[1] = triangle.getY() + triangle.getHeight();
                xCoords[2] = triangle.getX() + triangle.getWidth();
                yCoords[2] = triangle.getY() + triangle.getHeight();
                break;
        }

        for(int i=0; i < numPoints; i++) {
            xCoords[i] = xCoords[i] * Model.L_TO_PIXELS;
            yCoords[i] = yCoords[i] * Model.L_TO_PIXELS;
        }

        graphics.fillPolygon(xCoords, yCoords, numPoints);

    }
}

package gizmoball.model;

import java.io.*;
import java.util.*;

import gizmoball.model.gizmos.ReadGizmo;
import gizmoball.model.gizmos.ReadGizmo.GizmoType;

public class Saver {
    private BuildModel model;
    private Map<ReadGizmo, String> taggedGizmos;
    private Map<ReadBall, String> taggedBalls;

    public Saver(BuildModel model) {
        this.model = model;
        this.taggedGizmos = this.getTaggedGizmos(model.getGizmos());
        this.taggedBalls = this.getTaggedBalls(model.getBalls());
    }

    protected void save(OutputStream output) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(output);
        this.dumpGizmoDeclarations(writer);
        this.dumpBallDeclarations(writer);
        this.dumpRotateCommands(writer);
        this.dumpConnectCommands(writer);
        this.dumpKeyConnectCommands(writer);
        this.dumpFrictionGravityDeclarations(writer);
        writer.close();
    }

    private String getTypeIdPrefix(GizmoType type) {
        switch (type) {
            case TRIANGLE:
                return "T";
            case SQUARE:
                return "S";
            case CIRCLE:
                return "C";
            case ABSORBER:
                return "A";
            case LEFT_FLIPPER:
                return "LF";
            case RIGHT_FLIPPER:
                return "RF";
            default:
                return "";
        }
    }

    private Map<ReadGizmo,String> getTaggedGizmos(Collection<ReadGizmo> gizmos) {
        int i = 0;
        Map<ReadGizmo,String> tagged = new HashMap<>();
        for (ReadGizmo gizmo : gizmos) {
            tagged.put(gizmo, this.getTypeIdPrefix(gizmo.getType()) + i);
        }
        return tagged;
    }

    private Map<ReadBall,String> getTaggedBalls(Collection<ReadBall> balls) {
        int i = 0;
        Map<ReadBall,String> tagged = new HashMap<>();
        for (ReadBall ball : balls) {
            tagged.put(ball, "B" + i);
        }
        return tagged;
    }

    private void dumpGizmoDeclarations(PrintWriter writer) {
        for (ReadGizmo gizmo : this.taggedGizmos.keySet()) {
            String id = this.taggedGizmos.get(gizmo);
            if (gizmo.getType() == GizmoType.ABSORBER) {
                writer.format("%s %s %d %d %d %d\n",
                        this.convertGizmoTypeToString(gizmo.getType()),
                        id,
                        gizmo.getX(),
                        gizmo.getY(),
                        gizmo.getX() + gizmo.getWidth(),
                        gizmo.getY() + gizmo.getHeight());
            } else {
                writer.format("%s %s %d %d\n",
                        this.convertGizmoTypeToString(gizmo.getType()),
                        id,
                        gizmo.getX(),
                        gizmo.getY());
            }
        }
    }

    private String convertGizmoTypeToString(GizmoType type) {
        switch (type) {
            case SQUARE:
                return "Square";
            case TRIANGLE:
                return "Triangle";
            case CIRCLE:
                return "Circle";
            case ABSORBER:
                return "Absorber";
            case RIGHT_FLIPPER:
                return "RightFlipper";
            case LEFT_FLIPPER:
                return "LeftFlipper";
            default:
                return "";
        }
    }

    private void dumpBallDeclarations(PrintWriter writer) {
        for (ReadBall ball: this.taggedBalls.keySet()) {
            writer.format("Ball %s %f %f %f %f\n",
                    this.taggedBalls.get(ball),
                    ball.getX(),
                    ball.getY(),
                    ball.getVelocityX(),
                    ball.getVelocityY()
            );
        }
    }

    private void dumpRotateCommands(PrintWriter writer) {
        for (ReadGizmo gizmo: this.taggedGizmos.keySet()) {
            for (int i = 0; i < gizmo.getRotation().getTurns(); i++) {
                writer.format("Rotate %s\n", this.taggedGizmos.get(gizmo));
            }
        }
    }

    private void dumpConnectCommands(PrintWriter writer) {
        Map<ReadGizmo, Set<ReadGizmo>> map = this.model.getGizmoToGizmoMap();
        for (ReadGizmo from : map.keySet()) {
            for (ReadGizmo to : map.get(from)) {
                writer.format("Connect %s %s\n", this.taggedGizmos.get(from), this.taggedGizmos.get(to));
            }
        }
    }

    private void dumpKeyConnectCommands(PrintWriter writer) {
        Map<Integer, Set<ReadGizmo>> pressMap = this.model.getKeyPressToGizmoMap();
        for (Integer key : pressMap.keySet()) {
            for (ReadGizmo to : pressMap.get(key)) {
                writer.format("KeyConnect key %d down %s\n", key, this.taggedGizmos.get(to));
            }
        }
        Map<Integer, Set<ReadGizmo>> releaseMap = this.model.getKeyReleaseToGizmoMap();
        for (Integer key : releaseMap.keySet()) {
            for (ReadGizmo to : releaseMap.get(key)) {
                writer.format("KeyConnect key %d up %s\n", key, this.taggedGizmos.get(to));
            }
        }
    }

    private void dumpFrictionGravityDeclarations(PrintWriter writer) {
        writer.format("Gravity %f\n", this.model.getGravity());
        writer.format("Friction %f %f\n", this.model.getFrictionMu(), this.model.getFrictionMu2());
    }

}

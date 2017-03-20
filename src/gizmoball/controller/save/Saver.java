package gizmoball.controller.save;

import java.io.*;
import java.util.*;

import gizmoball.model.gizmos.ReadGizmo;
import gizmoball.model.BuildModel;
import gizmoball.model.ReadBall;
import gizmoball.model.gizmos.GizmoType;

public class Saver {
    protected BuildModel model;
    protected Map<ReadGizmo, String> taggedGizmos;
    protected Map<ReadBall, String> taggedBalls;

    public Saver(BuildModel model) {
        this.model = model;
        this.taggedGizmos = this.getTaggedGizmos(model.getGizmos());
        this.taggedBalls = this.getTaggedBalls(model.getBalls());
    }

    public void save(OutputStream output) {
        try (PrintWriter writer = new PrintWriter(output)) {
            this.dumpGizmoDeclarations(writer);
            this.dumpBallDeclarations(writer);
            this.dumpRotateCommands(writer);
            this.dumpConnectCommands(writer);
            this.dumpKeyConnectCommands(writer);
            this.dumpOuterwallConnectCommands(writer);
            this.dumpFrictionGravityDeclarations(writer);
        }
    }

    protected Map<ReadGizmo,String> getTaggedGizmos(Collection<ReadGizmo> gizmos) {
       
        Map<ReadGizmo,String> tagged = new HashMap<>();
        Map<GizmoType, Integer> typeCounter = initTypeCounterMap();
        for (ReadGizmo gizmo : gizmos) {
            tagged.put(gizmo, gizmo.getType().saveName() + typeCounter.get(gizmo.getType()));
            typeCounter.put(gizmo.getType(), typeCounter.get(gizmo.getType()) + 1);
        }
        return tagged;
    }
    
    private Map<GizmoType, Integer> initTypeCounterMap() {
        Map<GizmoType, Integer> typeCounter = new HashMap<>();
        typeCounter.put(GizmoType.ABSORBER, 0);
        typeCounter.put(GizmoType.CIRCLE, 0);
        typeCounter.put(GizmoType.LEFT_FLIPPER, 0);
        typeCounter.put(GizmoType.RIGHT_FLIPPER, 0);
        typeCounter.put(GizmoType.SQUARE, 0);
        typeCounter.put(GizmoType.TRIANGLE, 0);

        typeCounter.put(GizmoType.LEFT_SPINNING_FLIPPER, 0);
        typeCounter.put(GizmoType.RIGHT_SPINNING_FLIPPER, 0);
        typeCounter.put(GizmoType.SINK, 0);
        typeCounter.put(GizmoType.SPAWNER, 0);
        
        return typeCounter;
    }

    protected Map<ReadBall,String> getTaggedBalls(Collection<ReadBall> balls) {
        int i = 0;
        Map<ReadBall,String> tagged = new HashMap<>();
        for (ReadBall ball : balls) {
            tagged.put(ball, "B" + i);
            i++;
        }
        return tagged;
    }

    protected void dumpGizmoDeclarations(PrintWriter writer) {
        for (ReadGizmo gizmo : this.taggedGizmos.keySet()) {
            String id = this.taggedGizmos.get(gizmo);
            if (gizmo.getType() == GizmoType.ABSORBER) {
                writer.format("%s %s %d %d %d %d\n",
                        gizmo.getType().saveName(),
                        id,
                        gizmo.getX(),
                        gizmo.getY(),
                        gizmo.getX() + gizmo.getWidth(),
                        gizmo.getY() + gizmo.getHeight());
            } else {
                writer.format("%s %s %d %d\n",
                        gizmo.getType().saveName(),
                        id,
                        gizmo.getX(),
                        gizmo.getY());
            }
        }
    }

    protected void dumpBallDeclarations(PrintWriter writer) {
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

    protected void dumpRotateCommands(PrintWriter writer) {
        for (ReadGizmo gizmo: this.taggedGizmos.keySet()) {
            for (int i = 0; i < gizmo.getRotation().getTurns(); i++) {
                if (this.taggedGizmos.containsKey(gizmo)) {
                    writer.format("Rotate %s\n", this.taggedGizmos.get(gizmo));
                }
            }
        }
    }

    protected void dumpConnectCommands(PrintWriter writer) {
        Map<ReadGizmo, Set<ReadGizmo>> map = this.model.getGizmoToGizmoMap();
        for (ReadGizmo from : map.keySet()) {
            for (ReadGizmo to : map.get(from)) {
                if (this.taggedGizmos.containsKey(from) && this.taggedGizmos.containsKey(to)) {
                    writer.format("Connect %s %s\n", this.taggedGizmos.get(from), this.taggedGizmos.get(to));
                }
            }
        }
    }

    protected void dumpKeyConnectCommands(PrintWriter writer) {
        Map<Integer, Set<ReadGizmo>> pressMap = this.model.getKeyPressToGizmoMap();
        for (Integer key : pressMap.keySet()) {
            for (ReadGizmo to : pressMap.get(key)) {
                if (this.taggedGizmos.containsKey(to)) {
                    writer.format("KeyConnect key %d down %s\n", key, this.taggedGizmos.get(to));
                }
            }
        }
        Map<Integer, Set<ReadGizmo>> releaseMap = this.model.getKeyReleaseToGizmoMap();
        for (Integer key : releaseMap.keySet()) {
            for (ReadGizmo to : releaseMap.get(key)) {
                if (this.taggedGizmos.containsKey(to)) {
                    writer.format("KeyConnect key %d up %s\n", key, this.taggedGizmos.get(to));
                }
            }
        }
    }
    
    protected void dumpOuterwallConnectCommands(PrintWriter writer) {
        Set<ReadGizmo> outerWallGizmos = this.model.getOuterwallTriggeredGizmos();
        
        for (ReadGizmo gizmo: outerWallGizmos) {
            if (this.taggedGizmos.containsKey(gizmo)) {
                writer.format("Connect OuterWalls %s\n", this.taggedGizmos.get(gizmo));
            }
        }
    }

    protected void dumpFrictionGravityDeclarations(PrintWriter writer) {
        writer.format("Gravity %f\n", this.model.getGravity());
        writer.format("Friction %f %f\n", this.model.getFrictionMu(), this.model.getFrictionMu2());
    }

}

package gizmoball.model;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

import gizmoball.model.gizmos.Gizmo;
import gizmoball.model.gizmos.ReadGizmo;
import gizmoball.model.gizmos.ReadGizmo.GizmoType;

public class Saver {

	private Model model;
	
	public Saver(Model model) {
		this.model = model;
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

    //Returns an output stream containing all of
    //the Gizmo declarations for Gizmos in this model
    private void dumpGizmoDeclarations(PrintWriter writer) {
        for (ReadGizmo gizmo : this.model.getGizmos()) {
            String id  = this.model.getId(gizmo);

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
        for (String id : this.balls.keySet()) {
            Ball ball = this.balls.get(id);
            writer.format("Ball %s %f %f %f %f\n",
                    id,
                    ball.getX(),
                    ball.getY(),
                    ball.getVelocityX(),
                    ball.getVelocityY()
            );
        }
    }

    private void dumpRotateCommands(PrintWriter writer) {
        for (ReadGizmo gizmo : this.model.getGizmos()) {
            String id = this.model.getGizmoId(gizmo);
            for (int i = 0; i < gizmo.getRotation().getTurns(); i++) {
                writer.format("Rotate %s\n", id);
            }
        }
    }

    private void dumpConnectCommands(PrintWriter writer) {
    	Map<ReadGizmo, Set<ReadGizmo>> gizmoMap = this.model.getGizmoToGizmoMapping();
        for (ReadGizmo from : gizmoMap.keySet()) {
            String fromId = this.model.getGizmoId(from);
            for (ReadGizmo to : gizmoMap.get(from)) {
                String toId = this.model.getGizmoId(to);
                writer.format("Connect %s %s\n", fromId, toId);
            }
        }
    }

    private void dumpKeyConnectCommands(PrintWriter writer) {
    	Map<Integer, Set<ReadGizmo>> keyPressMap = this.model.getKeyPressToGizmoMapping();
        for (Integer key : keyPressMap.keySet()) {
            for (ReadGizmo to : keyPressMap.get(key)) {
                writer.format("KeyConnect key %d down %s\n", key, this.model.getGizmoId(to));
            }
        }
        Map<Integer, Set<ReadGizmo>> keyReleaseMap = this.model.getKeyReleaseToGizmoMapping();
        for (Integer key : keyReleaseMap.keySet()) {
            for (ReadGizmo to : keyReleaseMap.get(key)) {
                writer.format("KeyConnect key %d up %s\n", key, this.model.getGizmoId(to));
            }
        }
    }

    private void dumpFrictionGravityDeclarations(PrintWriter writer) {
        writer.format("Gravity %f\n", this.model.getGravity());
        writer.format("Friction %f %f\n", this.model.getFrictionMu(), this.model.getFrictionMu2());
    }

}

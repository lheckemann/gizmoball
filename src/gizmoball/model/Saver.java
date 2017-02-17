package gizmoball.model;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import gizmoball.model.gizmos.ReadGizmo;
import gizmoball.model.gizmos.ReadGizmo.GizmoType;

public class Saver {

	private BuildModel model;
	private Map<String, ReadGizmo> idToGizmo;
	private Map<String, ReadBall> idToBall;
	
	public Saver(BuildModel model) {
		this.model = model;
		this.idToGizmo = new HashMap<>();
		this.idToBall = new HashMap<>();
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
	
	private Set<String> getIdsForType(GizmoType type) {
		Set<String> idsOfSelectedType = new HashSet<>();
		
		GizmoType currGizmoType;
		for (Map.Entry<String, ReadGizmo> entry: this.idToGizmo.entrySet()) {
			currGizmoType = entry.getValue().getType();
			if (currGizmoType.equals(type)) {
				idsOfSelectedType.add(entry.getKey());
			}
		}
		
		return idsOfSelectedType;
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
	
	private int extractIdNumber(String existingId) {
		String idNumber = "";
		for (int i=0; i < existingId.length(); i++) {
			if (Character.isDigit(existingId.charAt(i))) {
				idNumber += existingId.charAt(i);
			}
		}
		
		return Integer.parseInt(idNumber);
	}
	
	private String createGizmoId(ReadGizmo gizmo) {
		String idPrefix = this.getTypeIdPrefix(gizmo.getType());
		
		int highestIdNumber = -1;
		int currentIdNumber = 0;
		
		for(String existingId: this.getIdsForType(gizmo.getType())) {
			currentIdNumber = this.extractIdNumber(existingId);
			if (currentIdNumber > highestIdNumber) {
				highestIdNumber = currentIdNumber;
			}
		}
		
		highestIdNumber++;
		
		return idPrefix + highestIdNumber;
	}
	
	private String createBallId(ReadBall ball) {
		int highestIdNumber = -1;
		int currentIdNumber = 0;
		
		for(String existingId: this.idToBall.keySet()) {
			currentIdNumber = this.extractIdNumber(existingId);
			if (currentIdNumber > highestIdNumber) {
				highestIdNumber = currentIdNumber;
			}
		}
		
		highestIdNumber++;
		
		return "B" + highestIdNumber;
	}
	
	private String getGizmoId(ReadGizmo gizmo) {
		for (Map.Entry<String, ReadGizmo> entry: this.idToGizmo.entrySet()) {
			if (entry.getValue().equals(gizmo)) {
				return entry.getKey();
			}
		}
		
		return "";
	}


    //Returns an output stream containing all of
    //the Gizmo declarations for Gizmos in this model
   private void dumpGizmoDeclarations(PrintWriter writer) {
        for (ReadGizmo gizmo : this.model.getGizmos()) {
        	
        	String id = this.createGizmoId(gizmo);
        	this.idToGizmo.put(id, gizmo);
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
        for (ReadBall ball: this.model.getBalls()) {
            
        	String id = this.createBallId(ball);
        	this.idToBall.put(id, ball);
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
        for (ReadGizmo gizmo: model.getGizmos()) {
            String id = this.getGizmoId(gizmo);
            for (int i = 0; i < gizmo.getRotation().getTurns(); i++) {
                writer.format("Rotate %s\n", id);
            }
        }
    }

    private void dumpConnectCommands(PrintWriter writer) {
    	Map<ReadGizmo, Set<ReadGizmo>> gizmoMap = this.model.getGizmoToGizmoMapping();
        for (ReadGizmo from : gizmoMap.keySet()) {
            String fromId = this.getGizmoId(from);
            for (ReadGizmo to : gizmoMap.get(from)) {
                String toId = this.getGizmoId(to);
                writer.format("Connect %s %s\n", fromId, toId);
            }
        }
    }

    private void dumpKeyConnectCommands(PrintWriter writer) {
    	Map<Integer, Set<ReadGizmo>> keyPressMap = this.model.getKeyPressToGizmoMapping();
        for (Integer key : keyPressMap.keySet()) {
            for (ReadGizmo to : keyPressMap.get(key)) {
                writer.format("KeyConnect key %d down %s\n", key, this.getGizmoId(to));
            }
        }
        Map<Integer, Set<ReadGizmo>> keyReleaseMap = this.model.getKeyReleaseToGizmoMapping();
        for (Integer key : keyReleaseMap.keySet()) {
            for (ReadGizmo to : keyReleaseMap.get(key)) {
                writer.format("KeyConnect key %d up %s\n", key, this.getGizmoId(to));
            }
        }
    }

    private void dumpFrictionGravityDeclarations(PrintWriter writer) {
        writer.format("Gravity %f\n", this.model.getGravity());
        writer.format("Friction %f %f\n", this.model.getFrictionMu(), this.model.getFrictionMu2());
    }

}

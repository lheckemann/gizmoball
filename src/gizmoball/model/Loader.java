package gizmoball.model;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import gizmoball.model.gizmos.Gizmo;
import physics.Vect;

public class Loader {
	
	private static final Set<String> DEPENDENT = new HashSet<>(Arrays.asList(
            "Rotate", "Delete", "Move", "Connect", "KeyConnect"));
	
	private Model model;
	protected Loader(Model model) {
		this.model = model;
	}

	protected void load(InputStream input) throws SyntaxError {
        Map<String,Gizmo> gizmos = new HashMap<>(this.model.gizmos);
        Map<String,Ball> balls = new HashMap<>(this.model.balls);
        Map<Integer,Set<Gizmo>> keyPressMap = new HashMap<>(this.model.keyPressMap);
        Map<Integer,Set<Gizmo>> keyReleaseMap = new HashMap<>(this.model.keyReleaseMap);
        Map<Gizmo,Set<Gizmo>> gizmoMap = new HashMap<>(this.model.gizmoMap);
        Set<Gizmo> wallTriggers = new HashSet<>(this.model.wallTriggers);
    	model.reset();

        int n = 0;
        Map<Integer, List<String>> dependent = new HashMap<>();
        try {
            try (Scanner scanner = new Scanner(input)) {
                while (scanner.hasNextLine()) {
                    n++;
                    String line = scanner.nextLine().trim();
                    if (!line.isEmpty()) {
                        List<String> tokens = Arrays.asList(line.split("\\s+"));
                        if (DEPENDENT.contains(tokens.get(0))) {
                            dependent.put(n, tokens);
                        } else if(tokens.get(0).equals("Gravity") || tokens.get(0).equals("Friction")){
                            this.frictionGravityCommand(n, tokens);
                        } else {
                        	this.creationCommand(n, tokens);
                        }
                    }
                }
            }
            for (Integer lineNumber : dependent.keySet()) {
                this.dependentCommand(lineNumber, dependent.get(lineNumber));
            }
        } catch (SyntaxError e) {
            this.gizmos = gizmos;
            this.balls = balls;
            this.keyPressMap = keyPressMap;
            this.keyReleaseMap = keyReleaseMap;
            this.gizmoMap = gizmoMap;
            this.wallTriggers = wallTriggers;
            throw e;
        }
    }
    
    private void frictionGravityCommand(Integer lineNum, List<String> tokens) throws SyntaxError {
	   
    	SyntaxError error = new SyntaxError(lineNum, String.join(" ", tokens), "Invalid command.");
    	try {
	    	switch (tokens.get(0)) {
		    	case "Gravity":
			        error.setMessage("Gravity <float>");
			        if (tokens.size() != 2) {
			            throw error;
			        }
			        model.setGravity(Double.parseDouble(tokens.get(1)));
			        break;
			
			    case "Friction":
			        error.setMessage("Friction <float> <float>");
			        if (tokens.size() != 3) {
			            throw error;
			        }
			        model.setFriction(Double.parseDouble(tokens.get(1)),
			                Double.parseDouble(tokens.get(2)));
			        break;
		    }
	    }
	    catch(IndexOutOfBoundsException|NumberFormatException e) {
	    	throw error;
	    }
    }

    private void creationCommand(Integer lineNum, List<String> tokens) throws SyntaxError {
        SyntaxError error = new SyntaxError(lineNum, String.join(" ", tokens), "Invalid command.");
        try {
            if (tokens.get(0).equals("Ball")) {
                error.setMessage("Ball <identifier> <float> <float> <float> <float>");
                if (tokens.size() != 6) {
                    throw error;
                }
                Double x = Double.parseDouble(tokens.get(2));
                Double y = Double.parseDouble(tokens.get(3));
                model.select(x, y);
                model.addBall(tokens.get(1));
                model.setBallVelocity(Double.parseDouble(tokens.get(4))
                					  ,Double.parseDouble(tokens.get(5)));
                return;
            }
            Integer x = Integer.parseInt(tokens.get(2));
            Integer y = Integer.parseInt(tokens.get(3));
            model.select(x, y);
            switch (tokens.get(0)) {
            
                case "Circle":
                    error.setMessage("Circle <identifier> <int> <int>");
                    if (tokens.size() != 4) {
                        throw error;
                    }
                    model.addCircle(tokens.get(1));
                    break;

                case "Triangle":
                    error.setMessage("Triangle <identifier> <int> <int>");
                    if (tokens.size() != 4) {
                        throw error;
                    }
                    model.addTriangle(tokens.get(1));
                    break;

                case "Square":
                    error.setMessage("Square <identifier> <int> <int>");
                    if (tokens.size() != 4) {
                        throw error;
                    }
                    model.addSquare(tokens.get(1));
                    break;

                case "LeftFlipper":
                    error.setMessage("LeftFlipper <identifier> <int> <int>");
                    if (tokens.size() != 4) {
                        throw error;
                    }
                    model.addLeftFlipper(tokens.get(1));
                    break;

                case "RightFlipper":
                    error.setMessage("RightFlipper <identifier> <int> <int>");
                    if (tokens.size() != 4) {
                        throw error;
                    }
                    model.addRightFlipper(tokens.get(1));
                    break;

                case "Absorber":
                    error.setMessage("Absorber <identifier> <int> <int> <int> <int>");
                    if (tokens.size() != 6) {
                        throw error;
                    }
                    Integer x1 = Integer.parseInt(tokens.get(4));
                    Integer y1 = Integer.parseInt(tokens.get(5));
                    model.addAbsorber(tokens.get(1), x1 - x, y1 - y);
                    break;
                
                default:
                	System.out.println("Hello");
                    throw error;
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw error;
        }
    }

    private void dependentCommand(Integer lineNum, List<String> tokens) throws SyntaxError {
        Gizmo gizmo;
        Ball ball;
        SyntaxError error = new SyntaxError(lineNum, String.join(" ", tokens), "Invalid command.");
        try {
            switch (tokens.get(0)) {

                case "Move":
                    error.setMessage("Move <identifier> <number> <number>");
                    if (tokens.size() != 4) {
                        throw error;
                    }
                    ball = model.balls.get(tokens.get(1));
                    if (ball != null) {
                        model.select(ball.getX(), ball.getY());
                        model.move(Double.parseDouble(tokens.get(2)),
                                Double.parseDouble(tokens.get(3)));
                        break;
                    }
                    gizmo = model.gizmos.get(tokens.get(1));
                    if (gizmo != null) {
                        model.select(gizmo.getX(), gizmo.getY());
                        model.move(Integer.parseInt(tokens.get(2)),
                                Integer.parseInt(tokens.get(3)));
                        break;
                    }
                    error.setMessage(tokens.get(1) + " does not refer to an existing object.");
                    throw error;

                case "Rotate":
                    error.setMessage("Rotate <identifier>");
                    if (tokens.size() != 2) {
                        throw error;
                    }
                    gizmo = model.gizmos.get(tokens.get(1));
                    if (gizmo != null) {
                        model.select(gizmo.getX(), gizmo.getY());
                        model.rotateGizmo();
                        break;
                    }
                    error.setMessage(tokens.get(1) + " does not refer to an existing gizmo.");
                    throw error;

                case "Delete":
                    error.setMessage("Delete <identifier>");
                    if (tokens.size() != 2) {
                        throw error;
                    }
                    ball = model.balls.get(tokens.get(1));
                    if (ball != null) {
                        model.select(ball.getX(), ball.getY());
                        model.delete();
                        break;
                    }
                    gizmo = model.gizmos.get(tokens.get(1));
                    if (gizmo != null) {
                        this.select(gizmo.getX(), gizmo.getY());
                        this.delete();
                        break;
                    }
                    error.setMessage(tokens.get(1) + " does not refer to an existing object.");
                    throw error;

                case "Connect":
                    error.setMessage("Connect <identifier> <identifier>");
                    if (tokens.size() != 3) {
                        throw error;
                    }
                    Gizmo destination = model.gizmos.get(tokens.get(2));
                    if (destination == null) {
                        error.setMessage(tokens.get(2) + " is not an existing gizmo.");
                        throw error;
                    }
                    model.select(destination.getX(), destination.getY());
                    if (tokens.get(1).equals("OuterWalls")) {
                        model.triggerOnOuterWalls();
                        break;
                    }
                    Gizmo source = model.gizmos.get(tokens.get(1));
                    if (source == null) {
                        error.setMessage(tokens.get(1) + " is not an existing gizmo.");
                        throw error;
                    }
                    model.triggerOnGizmo(source.getX(), source.getY());
                    break;

                case "KeyConnect":
                    error.setMessage("KeyConnect key <keynum> <up-or-down> <identifier>");
                    if (tokens.size() != 5 || !tokens.get(1).equals("key")) {
                        throw error;
                    }
                    gizmo = model.gizmos.get(tokens.get(4));
                    if (gizmo == null) {
                        error.setMessage(tokens.get(4) + " is not an existing gizmo.");
                        throw error;
                    }
                    model.select(gizmo.getX(), gizmo.getY());
                    Integer keyCode = Integer.parseInt(tokens.get(2));
                    if (tokens.get(3).equals("up")) {
                        model.triggerOnKeyRelease(keyCode);
                    } else if (tokens.get(3).equals("down")) {
                        model.triggerOnKeyPress(keyCode);
                    } else {
                        error.setMessage("KeyConnect key <keynum> <up-or-down> <identifier>");
                        throw error;
                    }
                    break;

                default:
                    throw error;
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw error;
        }
    }

}

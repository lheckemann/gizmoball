package gizmoball.model;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import gizmoball.model.gizmos.ReadGizmo;

public class Loader {
    
    private static final Set<String> DEPENDENT = new HashSet<>(Arrays.asList(
            "Rotate", "Delete", "Move", "Connect", "KeyConnect"));
    
    private BuildModel model;
    private Map<String, ReadGizmo> idToGizmos;
    private Map<String, ReadBall> idToBalls;
    
    protected Loader(BuildModel model) {
        this.model = model;
        this.idToGizmos = new HashMap<>();
        this.idToBalls = new HashMap<>();
    }

    protected void load(InputStream input) throws SyntaxError {
        BuildModel tempModel = model;
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
            this.model = tempModel;
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
                model.addBall(Double.parseDouble(tokens.get(4)), Double.parseDouble(tokens.get(5)));
                this.idToBalls.put(tokens.get(1), this.getBallAt(x, y));
          
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
                    model.addCircle();
                    this.idToGizmos.put(tokens.get(1), this.getGizmoAt(x, y));
                    break;

                case "Triangle":
                    error.setMessage("Triangle <identifier> <int> <int>");
                    if (tokens.size() != 4) {
                        throw error;
                    }
                    model.addTriangle();
                    this.idToGizmos.put(tokens.get(1), this.getGizmoAt(x, y));
                    break;

                case "Square":
                    error.setMessage("Square <identifier> <int> <int>");
                    if (tokens.size() != 4) {
                        throw error;
                    }
                    model.addSquare();
                    this.idToGizmos.put(tokens.get(1), this.getGizmoAt(x, y));
                    break;

                case "LeftFlipper":
                    error.setMessage("LeftFlipper <identifier> <int> <int>");
                    if (tokens.size() != 4) {
                        throw error;
                    }
                    model.addLeftFlipper();
                    this.idToGizmos.put(tokens.get(1), this.getGizmoAt(x, y));
                    break;

                case "RightFlipper":
                    error.setMessage("RightFlipper <identifier> <int> <int>");
                    if (tokens.size() != 4) {
                        throw error;
                    }
                    model.addRightFlipper();
                    this.idToGizmos.put(tokens.get(1), this.getGizmoAt(x, y));
                    break;

                case "Absorber":
                    error.setMessage("Absorber <identifier> <int> <int> <int> <int>");
                    if (tokens.size() != 6) {
                        throw error;
                    }
                    Integer x1 = Integer.parseInt(tokens.get(4));
                    Integer y1 = Integer.parseInt(tokens.get(5));
                    model.addAbsorber(x1 - x, y1 - y);
                    this.idToGizmos.put(tokens.get(1), this.getGizmoAt(x, y));
                    break;
                
                default:
                    throw error;
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw error;
        } catch (PositionOverlapException | PositionOutOfBoundsException e) {
            throw error;
        }
    }

    private void dependentCommand(Integer lineNum, List<String> tokens) throws SyntaxError {
        SyntaxError error = new SyntaxError(lineNum, String.join(" ", tokens), "Invalid command.");
        try {
            switch (tokens.get(0)) {

                case "Move":
                    error.setMessage("Move <identifier> <number> <number>");
                    if (tokens.size() != 4) {
                        throw error;
                    }
                    
                    if (this.idToBalls.containsKey(tokens.get(1))) {
                        ReadBall ball = this.idToBalls.get(tokens.get(1));
                        model.select(ball.getX(), ball.getY());
                        model.move(Double.parseDouble(tokens.get(2)), Double.parseDouble(tokens.get(3)));
                        break;
                    }
                    else if (this.idToGizmos.containsKey(tokens.get(1))) {
                        ReadGizmo gizmo = this.idToGizmos.get(tokens.get(1));
                        model.select(gizmo.getX(), gizmo.getY());
                        model.move(Integer.parseInt(tokens.get(2)), Integer.parseInt(tokens.get(3)));
                        break;
                    }
                    error.setMessage(tokens.get(1) + " does not refer to an existing object.");
                    throw error;

                case "Rotate":
                    error.setMessage("Rotate <identifier>");
                    if (tokens.size() != 2) {
                        throw error;
                    }
                    
                    if (this.idToGizmos.containsKey(tokens.get(1))) {
                        ReadGizmo gizmo = this.idToGizmos.get(tokens.get(1));
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
                    
                    if (this.idToGizmos.containsKey(tokens.get(1))) {
                        ReadGizmo gizmo = this.idToGizmos.get(tokens.get(1));
                        model.select(gizmo.getX(), gizmo.getY());
                        model.delete();
                        break;
                    } else if (this.idToBalls.containsKey(tokens.get(1))){
                        ReadBall ball = this.idToBalls.get(tokens.get(1));
                        model.select(ball.getX(), ball.getY());
                        model.delete();
                        break;
                    }
                    
                    error.setMessage(tokens.get(1) + " does not refer to an existing object.");
                    throw error;

                case "Connect":
                    error.setMessage("Connect <identifier> <identifier>");
                    if (tokens.size() != 3) {
                        throw error;
                    }
                    
                    if (this.idToGizmos.containsKey(tokens.get(2)) == false) {
                        error.setMessage(tokens.get(2) + " is not an existing gizmo.");
                        throw error;
                    } else {
                        ReadGizmo gizmo = this.idToGizmos.get(tokens.get(2));
                        model.select(gizmo.getX(), gizmo.getY());
                    }
                    
                    if (tokens.get(1).equals("OuterWalls")) {
                        model.triggerOnOuterWalls();
                        break;
                    }
                    
                    if (this.idToGizmos.containsKey(tokens.get(1)) == false) {
                        error.setMessage(tokens.get(1) + " is not an existing gizmo.");
                        throw error;
                    } else {
                        ReadGizmo source = this.idToGizmos.get(tokens.get(1));
                        this.model.triggerOnGizmo(source);
                    }
                    
                    break;

                case "KeyConnect":
                    error.setMessage("KeyConnect key <keynum> <up-or-down> <identifier>");
                    if (tokens.size() != 5 || !tokens.get(1).equals("key")) {
                        throw error;
                    }
                    
                    if (this.idToGizmos.containsKey(tokens.get(4)) == false) {
                        error.setMessage(tokens.get(4) + " is not an existing gizmo.");
                        throw error;
                    } else {
                        ReadGizmo gizmo = this.idToGizmos.get(tokens.get(4));
                        this.model.select(gizmo.getX(), gizmo.getY());
                    }
                    
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
        } catch (PositionOverlapException | PositionOutOfBoundsException e) {
            throw error;
        }
    }
    
    private ReadGizmo getGizmoAt(int x, int y) {
        Collection<ReadGizmo> gizmos = this.model.getGizmos();
        return gizmos.stream()
                .filter(g -> (g.getX() == x) && (g.getY() == y))
                .findFirst().orElse(null);
    }
    
    private ReadBall getBallAt(double x, double y) {
        Collection<ReadBall> balls = this.model.getBalls();
        return balls.stream()
                .filter(b -> (b.getX() == x) && (b.getY() == y))
                .findFirst().orElse(null);
    }

}

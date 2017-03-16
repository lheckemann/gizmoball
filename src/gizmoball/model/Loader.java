package gizmoball.model;

import java.io.InputStream;
import java.util.*;

import gizmoball.model.gizmos.*;

public class Loader {
    private static final Set<String> DEPENDENT = new HashSet<>(Arrays.asList(
            "Rotate", "Delete", "Move", "Connect", "KeyConnect"));
    private static final Set<String> GLOBAL = new HashSet<>(Arrays.asList(
            "Gravity", "Friction"));

    private BuildModel model;
    private Map<String, ReadGizmo> idToGizmos;
    private Map<String, ReadBall> idToBalls;

    public Loader(BuildModel model) {
        this.model = model;
        this.idToGizmos = new HashMap<>();
        this.idToBalls = new HashMap<>();
    }

    public void load(InputStream input) throws SyntaxError {
        int n = 0;
        Map<Integer, List<String>> dependent = new HashMap<>();
        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNextLine()) {
                n++;
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    List<String> tokens = Arrays.asList(line.split("\\s+"));
                    if (DEPENDENT.contains(tokens.get(0))) {
                        dependent.put(n, tokens);
                    } else if (GLOBAL.contains(tokens.get(0))) {
                        this.globalCommand(n, tokens);
                    } else {
                        this.creationCommand(n, tokens);
                    }
                }
            }
        }
        for (Integer lineNumber : dependent.keySet()) {
            this.dependentCommand(lineNumber, dependent.get(lineNumber));
        }
    }

    private void globalCommand(Integer lineNum, List<String> tokens) throws SyntaxError {
        SyntaxError error = new SyntaxError(lineNum, String.join(" ", tokens), "Invalid command.");
        try {
            switch (tokens.get(0)) {
                case "Gravity":
                    error.setMessage("Gravity <float>");
                    if (tokens.size() != 2) {
                        throw error;
                    }
                    model.setGravity(Double.parseDouble(tokens.get(1)));
                    return;

                case "Friction":
                    error.setMessage("Friction <float> <float>");
                    if (tokens.size() != 3) {
                        throw error;
                    }
                    model.setFriction(Double.parseDouble(tokens.get(1)),
                            Double.parseDouble(tokens.get(2)));
                    return;
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
                    model.addGizmo(new Circle());
                    this.idToGizmos.put(tokens.get(1), this.getGizmoAt(x, y));
                    return;

                case "Triangle":
                    error.setMessage("Triangle <identifier> <int> <int>");
                    if (tokens.size() != 4) {
                        throw error;
                    }
                    model.addGizmo(new Triangle());
                    this.idToGizmos.put(tokens.get(1), this.getGizmoAt(x, y));
                    return;

                case "Square":
                    error.setMessage("Square <identifier> <int> <int>");
                    if (tokens.size() != 4) {
                        throw error;
                    }
                    model.addGizmo(new Square());
                    this.idToGizmos.put(tokens.get(1), this.getGizmoAt(x, y));
                    return;

                case "LeftFlipper":
                    error.setMessage("LeftFlipper <identifier> <int> <int>");
                    if (tokens.size() != 4) {
                        throw error;
                    }
                    model.addGizmo(new StandardFlipper(true));
                    this.idToGizmos.put(tokens.get(1), this.getGizmoAt(x, y));
                    return;

                case "RightFlipper":
                    error.setMessage("RightFlipper <identifier> <int> <int>");
                    if (tokens.size() != 4) {
                        throw error;
                    }
                    model.addGizmo(new StandardFlipper(false));
                    this.idToGizmos.put(tokens.get(1), this.getGizmoAt(x, y));
                    return;

                case "Absorber":
                    error.setMessage("Absorber <identifier> <int> <int> <int> <int>");
                    if (tokens.size() != 6) {
                        throw error;
                    }
                    Integer x1 = Integer.parseInt(tokens.get(4));
                    Integer y1 = Integer.parseInt(tokens.get(5));
                    model.addGizmo(new Absorber(x1 - x, y1 - y));
                    this.idToGizmos.put(tokens.get(1), this.getGizmoAt(x, y));
                    return;
                case "LeftSpinningFlipper":
                    error.setMessage("LeftSpinningFlipper <identifier> <int> <int>");
                    if (tokens.size() != 4) {
                        throw error;
                    }
                    model.addGizmo(new SpinningFlipper(true));
                    this.idToGizmos.put(tokens.get(1), this.getGizmoAt(x, y));
                    return;
                case "RightSpinningFlipper":
                    error.setMessage("RightSpinningFlipper <identifier> <int> <int>");
                    if (tokens.size() != 4) {
                        throw error;
                    }
                    model.addGizmo(new SpinningFlipper(false));
                    this.idToGizmos.put(tokens.get(1), this.getGizmoAt(x, y));
                    return;
                case "Sink":
                    error.setMessage("Sink <identifier> <int> <int>");
                    if (tokens.size() != 4) {
                        throw error;
                    }
                    model.addGizmo(new Sink());
                    this.idToGizmos.put(tokens.get(1), this.getGizmoAt(x, y));
                    return;
                case "Spawner":
                    error.setMessage("Spawner <identifier> <int> <int>");
                    if (tokens.size() != 4) {
                        throw error;
                    }
                    model.addGizmo(new Spawner());
                    this.idToGizmos.put(tokens.get(1), this.getGizmoAt(x, y));
                    return;
                default:
                    throw error;
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw error;
        } catch (PositionOutOfBoundsException e) {
            error.setMessage("Position out of arena bounds.");
            throw error;
        } catch (PositionOverlapException e) {
            error.setMessage("Position overlaping with another element.");
            throw error;
        } catch (InvalidAbsorberWidthHeight e) {
            error.setMessage("Absorber must be at least 1L by 1L");
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
                        return;
                    }
                    else if (this.idToGizmos.containsKey(tokens.get(1))) {
                        ReadGizmo gizmo = this.idToGizmos.get(tokens.get(1));
                        model.select(gizmo.getX(), gizmo.getY());
                        model.move(Integer.parseInt(tokens.get(2)), Integer.parseInt(tokens.get(3)));
                        return;
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
                        return;
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
                        return;
                    } else if (this.idToBalls.containsKey(tokens.get(1))){
                        ReadBall ball = this.idToBalls.get(tokens.get(1));
                        model.select(ball.getX(), ball.getY());
                        model.delete();
                        return;
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
                        return;
                    }

                    if (this.idToGizmos.containsKey(tokens.get(1)) == false) {
                        error.setMessage(tokens.get(1) + " is not an existing gizmo.");
                        throw error;
                    } else {
                        ReadGizmo source = this.idToGizmos.get(tokens.get(1));
                        this.model.triggerOnGizmo(source);
                    }

                    return;

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
                    return;

                default:
                    throw error;
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw error;
        } catch (PositionOutOfBoundsException e) {
            error.setMessage("Position out of arena bounds.");
            throw error;
        } catch (PositionOverlapException e) {
            error.setMessage("Position overlaping with another element.");
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

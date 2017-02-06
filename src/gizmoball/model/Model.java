package gizmoball.model;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observer;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import physics.Vect;

import gizmoball.model.gizmos.Absorber;
import gizmoball.model.gizmos.Square;
import gizmoball.model.gizmos.Circle;
import gizmoball.model.gizmos.Triangle;
import gizmoball.model.gizmos.RightFlipper;
import gizmoball.model.gizmos.LeftFlipper;
import gizmoball.model.gizmos.Gizmo;
import gizmoball.model.gizmos.ReadGizmo;

public class Model implements BuildModel, RunModel {
    private static final Set<String> DEPENDENT = new HashSet<>(Arrays.asList(
                "Rotate", "Delete", "Move", "Connect", "KeyConnect"));

    private final double width;
    private final double height;
    private double selX;
    private double selY;
    private double gravity = 25;
    private double mu = 0.025;
    private double mu2 = 0.025;

    private Map<String, Gizmo> gizmos;
    private Map<String, Ball> balls;
    private Map<Integer, Set<Gizmo>> keyPressMap;
    private Map<Integer, Set<Gizmo>> keyReleaseMap;
    private Map<Gizmo, Set<Gizmo>> gizmoMap;
    private Set<Gizmo> wallTriggers;
    private Set<Observer> observers;

    public Model(double width, double height) {
        this.width = width;
        this.height = height;
        this.reset();
        this.observers = new HashSet<>();
    }

    public void reset() {
        this.gizmos = new HashMap<>();
        this.balls = new HashMap<>();
        this.keyPressMap = new HashMap<>();
        this.keyReleaseMap = new HashMap<>();
        this.gizmoMap = new HashMap<>();
        this.wallTriggers = new HashSet<>();
    }

    private Gizmo getGizmoAt(double x, double y) {
        return this.gizmos.values().stream()
                          .filter(g -> g.contains(x, y))
                          .findFirst().orElse(null);
    }

    private Ball getBallAt(double x, double y) {
        return this.balls.values().stream()
                         .filter(b -> b.contains(x, y))
                         .findFirst().orElse(null);
    }

    public void select(double x, double y) {
        this.selX = x;
        this.selY = y;
    }

    public void move(double dX, double dY) {
        Gizmo gizmo = this.getGizmoAt(this.selX, this.selY);
        if (gizmo != null) {
            gizmo.setPosition((int) dX, (int) dY);
            return;
        }
        Ball ball = this.getBallAt(this.selX, this.selY);
        if (ball != null) {
            ball.setPosition(dX, dY);
        }
    }

    public void delete() {
        // TODO: we still have to remove gizmos from the trigger mappings
        this.gizmos = this.gizmos.entrySet().stream()
                                 .filter(e -> !e.getValue().contains(this.selX, this.selY))
                                 .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
        this.balls = this.balls.entrySet().stream()
                               .filter(e -> !e.getValue().contains(this.selX, this.selY))
                               .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
    }

    public void addAbsorber(String identifier, int width, int height) {
        Gizmo gizmo = new Absorber(width, height);
        gizmo.setPosition((int) this.selX, (int) this.selY);
        this.gizmos.put(identifier, gizmo);
    }

    public void addSquare(String identifier) {
        Gizmo gizmo = new Square();
        gizmo.setPosition((int) this.selX, (int) this.selY);
        this.gizmos.put(identifier, gizmo);
    }

    public void addCircle(String identifier) {
        Gizmo gizmo = new Circle();
        gizmo.setPosition((int) this.selX, (int) this.selY);
        this.gizmos.put(identifier, gizmo);
    }

    public void addTriangle(String identifier) {
        Gizmo gizmo = new Triangle();
        gizmo.setPosition((int) this.selX, (int) this.selY);
        this.gizmos.put(identifier, gizmo);
    }

    public void addRightFlipper(String identifier) {
        Gizmo gizmo = new RightFlipper();
        gizmo.setPosition((int) this.selX, (int) this.selY);
        this.gizmos.put(identifier, gizmo);
    }

    public void addLeftFlipper(String identifier) {
        Gizmo gizmo = new LeftFlipper();
        gizmo.setPosition((int) this.selX, (int) this.selY);
        this.gizmos.put(identifier, gizmo);
    }

    public void rotateGizmo() {
        Gizmo gizmo = this.getGizmoAt(this.selX, this.selY);
        if (gizmo != null) {
            gizmo.rotate();
        }
    }

    public void addBall(String identifier) {
        Ball ball = new Ball();
        ball.setPosition(this.selX, this.selY);
        // TODO: manage conflicting identifiers?
        this.balls.put(identifier, ball);
    }

    public void setBallVelocity(double vX, double vY) {
        Ball ball = this.getBallAt(this.selX, this.selY);
        if (ball != null) {
            ball.setVelocity(new Vect(vX, vY));
        }
    }

    public double getGravity() {
        return this.gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public double getFrictionMu() {
        return this.mu;
    }

    public double getFrictionMu2() {
        return this.mu2;
    }

    public void setFriction(double mu, double mu2) {
        this.mu = mu;
        this.mu2 = mu2;
    }

    public void triggerOnKeyPress(int key) {
        Gizmo gizmo = this.getGizmoAt(this.selX, this.selY);
        if (gizmo != null) {
            this.keyPressMap.computeIfAbsent(key, k -> new HashSet<>()).add(gizmo);
        }
    }

    public void triggerOnKeyRelease(int key) {
        Gizmo gizmo = this.getGizmoAt(this.selX, this.selY);
        if (gizmo != null) {
            this.keyReleaseMap.computeIfAbsent(key, k -> new HashSet<>()).add(gizmo);
        }
    }

    public void triggerOnOuterWalls() {
        Gizmo gizmo = this.getGizmoAt(this.selX, this.selY);
        if (gizmo != null) {
            this.wallTriggers.add(gizmo);
        }
    }

    public void triggerOnGizmo(double sX, double sY) {
        Gizmo source = this.getGizmoAt(sX, sY);
        Gizmo destination = this.getGizmoAt(this.selX, this.selY);
        if (source != null && destination != null) {
            this.gizmoMap.computeIfAbsent(source, s -> new HashSet<>()).add(destination);
        }
    }

    private void loadCommand(List<String> tokens) throws SyntaxError {
        Gizmo gizmo;
        Ball ball;
        try {
            switch (tokens.get(0)) {
                case "Gravity":
                    if (tokens.size() != 2) {
                        throw new SyntaxError("Gravity <float>");
                    }
                    this.setGravity(Double.parseDouble(tokens.get(1)));
                    break;
                case "Friction":
                    if (tokens.size() != 3) {
                        throw new SyntaxError("Friction <float> <float>");
                    }
                    this.setFriction(Double.parseDouble(tokens.get(1)),
                                     Double.parseDouble(tokens.get(2)));
                    break;
                case "Ball":
                    if (tokens.size() != 3) {
                        throw new SyntaxError("Ball <identifier> <float> <float>");
                    }
                    this.select(Double.parseDouble(tokens.get(2)),
                                Double.parseDouble(tokens.get(3)));
                    this.addBall(tokens.get(1));
                    break;
                case "Move":
                    if (tokens.size() != 4) {
                        throw new SyntaxError("Move <identifier> <number> <number>");
                    }
                    ball = this.balls.get(tokens.get(1));
                    if (ball != null) {
                        this.select(ball.getX(), ball.getY());
                        this.move(Double.parseDouble(tokens.get(2)),
                                  Double.parseDouble(tokens.get(3)));
                        break;
                    }
                    gizmo = this.gizmos.get(tokens.get(1));
                    if (gizmo != null) {
                        this.select(gizmo.getX(), gizmo.getY());
                        this.move(Integer.parseInt(tokens.get(2)),
                                  Integer.parseInt(tokens.get(3)));
                        break;
                    }
                    throw new SyntaxError(tokens.get(1) + " does not refer to an existing object.");
                case "Rotate":
                    if (tokens.size() != 2) {
                        throw new SyntaxError("Rotate <identifier>");
                    }
                    gizmo = this.gizmos.get(tokens.get(1));
                    if (gizmo != null) {
                        this.select(gizmo.getX(), gizmo.getY());
                        this.rotateGizmo();
                        break;
                    }
                    throw new SyntaxError(tokens.get(1) + " does not refer to an existing gizmo.");
                case "Delete":
                    if (tokens.size() != 2) {
                        throw new SyntaxError("Delete <identifier>");
                    }
                    ball = this.balls.get(tokens.get(1));
                    if (ball != null) {
                        this.select(ball.getX(), ball.getY());
                        this.delete();
                        break;
                    }
                    gizmo = this.gizmos.get(tokens.get(1));
                    if (gizmo != null) {
                        this.select(gizmo.getX(), gizmo.getY());
                        this.delete();
                        break;
                    }
                    throw new SyntaxError(tokens.get(1) + " does not refer to an existing object.");
                case "Connect":
                    if (tokens.size() != 3) {
                        throw new SyntaxError("Connect <identifier> <identifier>");
                    }
                    Gizmo destination = this.gizmos.get(tokens.get(2));
                    if (destination == null) {
                        throw new SyntaxError(tokens.get(2) + " is not an existing gizmo.");
                    }
                    this.select(destination.getX(), destination.getY());
                    if (tokens.get(1).equals("OuterWalls")) {
                        this.triggerOnOuterWalls();
                        break;
                    }
                    Gizmo source = this.gizmos.get(tokens.get(1));
                    if (source == null) {
                        throw new SyntaxError(tokens.get(1) + " is not an existing gizmo.");
                    }
                    this.triggerOnGizmo(source.getX(), source.getY());
                    break;
                case "KeyConnect":
                    if (tokens.size() != 5 || !tokens.get(1).equals("key")) {
                        throw new SyntaxError("KeyConnect key <keynum> <up-or-down> <identifier>");
                    }
                    gizmo = this.gizmos.get(tokens.get(4));
                    if (gizmo == null) {
                        throw new SyntaxError(tokens.get(4) + " is not an existing gizmo.");
                    }
                    this.select(gizmo.getX(), gizmo.getY());
                    Integer keyCode = Integer.parseInt(tokens.get(2));
                    if (tokens.get(3).equals("up")) {
                        this.triggerOnKeyRelease(keyCode);
                    } else if (tokens.get(3).equals("down")) {
                        this.triggerOnKeyPress(keyCode);
                    } else {
                        throw new SyntaxError("KeyConnect key <keynum> <up-or-down> <identifier>");
                    }
                    break;
                default:
                    Integer x = Integer.parseInt(tokens.get(2));
                    Integer y = Integer.parseInt(tokens.get(3));
                    this.select(x, y);
                    switch (tokens.get(0)) {
                        case "Circle":
                            if (tokens.size() != 4) {
                                throw new SyntaxError("Circle expects an identifier followed by two integers.");
                            }
                            this.addCircle(tokens.get(1));
                            break;
                        case "Triangle":
                            if (tokens.size() != 4) {
                                throw new SyntaxError("Triangle expects an identifier followed by two integers.");
                            }
                            this.addTriangle(tokens.get(1));
                            break;
                        case "Square":
                            if (tokens.size() != 4) {
                                throw new SyntaxError("Square expects an identifier followed by two integers.");
                            }
                            this.addSquare(tokens.get(1));
                            break;
                        case "LeftFlipper":
                            if (tokens.size() != 4) {
                                throw new SyntaxError("LeftFlipper expects an identifier followed by two integers.");
                            }
                            this.addLeftFlipper(tokens.get(1));
                            break;
                        case "RightFlipper":
                            if (tokens.size() != 4) {
                                throw new SyntaxError("RightFlipper expects an identifier followed by two integers.");
                            }
                            this.addRightFlipper(tokens.get(1));
                            break;
                        case "Absorber":
                            if (tokens.size() != 6) {
                                throw new SyntaxError("Absorber expects an identifier followed by four integers.");
                            }
                            Integer x1 = Integer.parseInt(tokens.get(4));
                            Integer y1 = Integer.parseInt(tokens.get(5));
                            this.addAbsorber(tokens.get(1), x1 - x, y1 - y);
                            break;
                        default:
                            throw new SyntaxError("Invalid command.");
                    }
            }
        } catch (NumberFormatException|IndexOutOfBoundsException e) {
            throw new SyntaxError(e.toString());  // FIXME: Should be nested
        }
    }

    public void load(InputStream input) throws SyntaxError {
        List<List<String>> dependent = new LinkedList<>();
        Scanner scanner = new Scanner(input);
        while (scanner.hasNextLine()) {
            List<String> tokens = Arrays.asList(scanner.nextLine().split("\\s+"));
            if (!tokens.isEmpty()) {
                if (Model.DEPENDENT.contains(tokens.get(0))) {
                    dependent.add(tokens);
                } else {
                    this.loadCommand(tokens);
                }
            }
        }
        for (List<String> tokens : dependent) {
            this.loadCommand(tokens);
        }
    }

    public OutputStream save() {
        return null;  // TODO
    }

    public Set<ReadGizmo> getGizmos() {
        return new HashSet<>(this.gizmos.values());
    }

    public Set<Vect> getBallPositions() { // FIXME: we don't want to use vect here
        return this.balls.values()
                         .stream()
                         .map(b -> new Vect(b.getX(), b.getY()))
                         .collect(Collectors.toSet());
    }

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void deleteObserver(Observer observer) {
        this.observers.remove(observer);
    }

    public void tick() {
        // TODO
    }

    public void keyPressed(int keyCode) {
        this.keyPressMap.getOrDefault(keyCode, new HashSet<>()).stream().forEach(Gizmo::trigger);
    }

    public void keyReleased(int keyCode) {
        this.keyReleaseMap.getOrDefault(keyCode, new HashSet<>()).stream().forEach(Gizmo::trigger);
    }
}

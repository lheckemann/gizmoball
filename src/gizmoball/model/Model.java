package gizmoball.model;

import java.io.PrintWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

import gizmoball.model.gizmos.*;
import gizmoball.model.gizmos.Gizmo;
import gizmoball.model.gizmos.ReadGizmo;
import gizmoball.model.gizmos.Rotation;
import physics.Vect;

import gizmoball.model.gizmos.ReadGizmo.GizmoType;

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

    //A map from Gizmo Id to Gizmos
    private Map<String, Gizmo> gizmos;
    //A map from Ball Id to Balls
    private Map<String, Ball> balls;
    //A map from Key Id to Gizmo
    private Map<Integer, Set<Gizmo>> keyPressMap = new HashMap<>();
    //A map from Key Id to Gizmo
    private Map<Integer, Set<Gizmo>> keyReleaseMap = new HashMap<>();
    private Map<Gizmo, Set<Gizmo>> gizmoMap;
    private Set<Gizmo> wallTriggers;

    public Model(double width, double height) {
        this.width = width;
        this.height = height;
        this.reset();
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

    private String getGizmoId(Gizmo gizmo) {
        for (String id : this.gizmos.keySet()) {
            if (this.gizmos.get(id).equals(gizmo)) {
                return id;
            }
        }
        return null;
    }

    private Ball getBallAt(double x, double y) {
        return this.balls.values().stream()
                .filter(b -> b.contains(x, y))
                .findFirst().orElse(null);
    }

    private String getBallId(Ball ball) {
        for (String id : this.balls.keySet()) {
            if (this.balls.get(id).equals(ball)) {
                return id;
            }
        }
        return null;
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
        Gizmo gizmo = this.getGizmoAt(this.selX, this.selY);
        if (gizmo != null) {
            this.gizmos.remove(this.getGizmoId(gizmo));
            this.gizmoMap.remove(gizmo);
            for (Set<Gizmo> listeners : this.gizmoMap.values()) {
                listeners.remove(gizmo);
            }
            for (Set<Gizmo> listeners : this.keyPressMap.values()) {
                listeners.remove(gizmo);
            }
            for (Set<Gizmo> listeners : this.keyReleaseMap.values()) {
                listeners.remove(gizmo);
            }
            this.wallTriggers.remove(gizmo);
            return;
        }
        Ball ball = this.getBallAt(this.selX, this.selY);
        if (ball != null) {
            this.balls.remove(this.getBallId(ball));
        }
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
        Gizmo gizmo = new Flipper(false);
        gizmo.setPosition((int) this.selX, (int) this.selY);
        this.gizmos.put(identifier, gizmo);
    }

    public void addLeftFlipper(String identifier) {
        Gizmo gizmo = new Flipper(true);
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

    public void load(InputStream input) throws SyntaxError {
        List<List<String>> dependent = new LinkedList<>();
        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    List<String> tokens = Arrays.asList(line.split("\\s+"));
                    if (Model.DEPENDENT.contains(tokens.get(0))) {
                        dependent.add(tokens);
                    } else {
                        this.creationCommand(tokens);
                    }
                }
            }
        }
        for (List<String> tokens : dependent) {
            this.dependentCommand(tokens);
        }
    }

    private void creationCommand(List<String> tokens) throws SyntaxError {
        String SYNTAX_ERROR = "Invalid command.";
        try {
            if (tokens.get(0).equals("Ball")) {
                SYNTAX_ERROR = "Ball <identifier> <float> <float>";
                if (tokens.size() != 4) {
                    throw new SyntaxError(SYNTAX_ERROR);
                }
                this.select(Double.parseDouble(tokens.get(2)),
                        Double.parseDouble(tokens.get(3)));
                this.addBall(tokens.get(1));
                return;
            }
            Integer x = Integer.parseInt(tokens.get(2));
            Integer y = Integer.parseInt(tokens.get(3));
            this.select(x, y);
            switch (tokens.get(0)) {

                case "Circle":
                    SYNTAX_ERROR = "Circle <identifier> <int> <int>";
                    if (tokens.size() != 4) {
                        throw new SyntaxError(SYNTAX_ERROR);
                    }
                    this.addCircle(tokens.get(1));
                    break;

                case "Triangle":
                    SYNTAX_ERROR = "Triangle <identifier> <int> <int>";
                    if (tokens.size() != 4) {
                        throw new SyntaxError(SYNTAX_ERROR);
                    }
                    this.addTriangle(tokens.get(1));
                    break;

                case "Square":
                    SYNTAX_ERROR = "Square <identifier> <int> <int>";
                    if (tokens.size() != 4) {
                        throw new SyntaxError(SYNTAX_ERROR);
                    }
                    this.addSquare(tokens.get(1));
                    break;

                case "LeftFlipper":
                    SYNTAX_ERROR = "LeftFlipper <identifier> <int> <int>";
                    if (tokens.size() != 4) {
                        throw new SyntaxError(SYNTAX_ERROR);
                    }
                    this.addLeftFlipper(tokens.get(1));
                    break;

                case "RightFlipper":
                    SYNTAX_ERROR = "RightFlipper <identifier> <int> <int>";
                    if (tokens.size() != 4) {
                        throw new SyntaxError(SYNTAX_ERROR);
                    }
                    this.addRightFlipper(tokens.get(1));
                    break;

                case "Absorber":
                    SYNTAX_ERROR = "Absorber <identifier> <int> <int> <int> <int>";
                    if (tokens.size() != 6) {
                        throw new SyntaxError(SYNTAX_ERROR);
                    }
                    Integer x1 = Integer.parseInt(tokens.get(4));
                    Integer y1 = Integer.parseInt(tokens.get(5));
                    this.addAbsorber(tokens.get(1), x1 - x, y1 - y);
                    break;

                default:
                    throw new SyntaxError(String.format("Invalid command %s.", tokens.get(0)));
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new SyntaxError(SYNTAX_ERROR);
        }
    }

    private void dependentCommand(List<String> tokens) throws SyntaxError {
        Gizmo gizmo;
        Ball ball;
        String SYNTAX_ERROR = "Invalid command.";
        try {
            switch (tokens.get(0)) {

                case "Gravity":
                    SYNTAX_ERROR = "Gravity <float>";
                    if (tokens.size() != 2) {
                        throw new SyntaxError(SYNTAX_ERROR);
                    }
                    this.setGravity(Double.parseDouble(tokens.get(1)));
                    break;

                case "Friction":
                    SYNTAX_ERROR = "Friction <float> <float>";
                    if (tokens.size() != 3) {
                        throw new SyntaxError(SYNTAX_ERROR);
                    }
                    this.setFriction(Double.parseDouble(tokens.get(1)),
                            Double.parseDouble(tokens.get(2)));
                    break;

                case "Move":
                    SYNTAX_ERROR = "Move <identifier> <number> <number>";
                    if (tokens.size() != 4) {
                        throw new SyntaxError(SYNTAX_ERROR);
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
                    SYNTAX_ERROR = "Rotate <identifier>";
                    if (tokens.size() != 2) {
                        throw new SyntaxError(SYNTAX_ERROR);
                    }
                    gizmo = this.gizmos.get(tokens.get(1));
                    if (gizmo != null) {
                        this.select(gizmo.getX(), gizmo.getY());
                        this.rotateGizmo();
                        break;
                    }
                    throw new SyntaxError(tokens.get(1) + " does not refer to an existing gizmo.");

                case "Delete":
                    SYNTAX_ERROR = "Delete <identifier>";
                    if (tokens.size() != 2) {
                        throw new SyntaxError(SYNTAX_ERROR);
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
                    SYNTAX_ERROR = "Connect <identifier> <identifier>";
                    if (tokens.size() != 3) {
                        throw new SyntaxError(SYNTAX_ERROR);
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
                    SYNTAX_ERROR = "KeyConnect key <keynum> <up-or-down> <identifier>";
                    if (tokens.size() != 5 || !tokens.get(1).equals("key")) {
                        throw new SyntaxError(SYNTAX_ERROR);
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
                    throw new SyntaxError(String.format("Invalid command %s.", tokens.get(0)));
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new SyntaxError(SYNTAX_ERROR);
        }
    }

    public void save(OutputStream output) {
        PrintWriter writer = new PrintWriter(output);
        this.dumpGizmoDeclarations(writer);
        this.dumpBallDeclarations(writer);
        this.dumpRotateCommands(writer);
        this.dumpConnectCommands(writer);
        this.dumpKeyConnectCommands(writer);
        this.dumpFrictionGravityDeclarations(writer);
    }

    //Returns an output stream containing all of
    //the Gizmo declarations for Gizmos in this model
    private void dumpGizmoDeclarations(PrintWriter writer) {
        for (String id : this.gizmos.keySet()) {
            Gizmo gizmo = this.gizmos.get(id);

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
                return "Asborber";
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
        for (String id : this.gizmos.keySet()) {
            Gizmo gizmo = this.gizmos.get(id);
            for (int i = 0; i < gizmo.getRotation().getTurns(); i++) {
                writer.format("Rotate %s\n", id);
            }
        }
    }

    private void dumpConnectCommands(PrintWriter writer) {
        for (Gizmo from : this.gizmoMap.keySet()) {
            String fromId = this.getGizmoId(from);
            for (Gizmo to : this.gizmoMap.get(from)) {
                String toId = this.getGizmoId(to);
                writer.format("Connect %s %s\n", fromId, toId);
            }
        }
    }

    private void dumpKeyConnectCommands(PrintWriter writer) {
        for (Integer key : this.keyPressMap.keySet()) {
            for (Gizmo to : this.keyPressMap.get(key)) {
                writer.format("KeyConnect key %d down %s\n", key, this.getGizmoId(to));
            }
        }
        for (Integer key : this.keyReleaseMap.keySet()) {
            for (Gizmo to : this.keyReleaseMap.get(key)) {
                writer.format("KeyConnect key %d up %s\n", key, this.getGizmoId(to));
            }
        }
    }

    private void dumpFrictionGravityDeclarations(PrintWriter writer) {
        writer.format("Gravity %f\n", this.gravity);
        writer.format("Friction %f %f\n", this.getFrictionMu(), this.getFrictionMu2());
    }

    public Set<Vect> getBallPositions() { // FIXME: we don't want to use vect here
        return this.balls.values()
                .stream()
                .map(b -> new Vect(b.getX(), b.getY()))
                .collect(Collectors.toSet());
    }

    public void keyPressed(int keyCode) {
        this.keyPressMap.getOrDefault(keyCode, Collections.emptySet()).forEach(Gizmo::trigger);
    }

    public void keyReleased(int keyCode) {
        this.keyReleaseMap.getOrDefault(keyCode, Collections.emptySet()).forEach(Gizmo::trigger);
    }

    @Override
    public void tick() {
        gizmos.values().forEach(Gizmo::tick);
    }

    public static Model prototype1Example() {
        Model model = new Model(16, 16);
        int x = 1;
        for (Rotation rot : Rotation.values()) {
            Flipper flipper = new Flipper(true);
            flipper.setRotation(rot);
            flipper = new Flipper(false);
            flipper.setRotation(rot);
            flipper.setPosition(x, 2);
            model.gizmos.put("flipper" + x, flipper);
            x += 3;
        }
        return model;
    }

    @Override
    public Collection<ReadGizmo> getGizmos() {
        return Collections.unmodifiableCollection(gizmos.values());
    }
}

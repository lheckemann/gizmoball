package gizmoball.model;

import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

import physics.Vect;
import gizmoball.model.gizmos.*;
import gizmoball.model.gizmos.Gizmo;
import gizmoball.model.gizmos.ReadGizmo;
import gizmoball.model.gizmos.Rotation;
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

    @Override
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

    @Override
    public void select(double x, double y) {
        this.selX = x;
        this.selY = y;
    }

    @Override
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

    @Override
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

    @Override
    public void addAbsorber(String identifier, int width, int height) {
        Gizmo gizmo = new Absorber(width, height);
        gizmo.setPosition((int) this.selX, (int) this.selY);
        this.gizmos.put(identifier, gizmo);
    }

    @Override
    public void addSquare(String identifier) {
        Gizmo gizmo = new Square();
        gizmo.setPosition((int) this.selX, (int) this.selY);
        this.gizmos.put(identifier, gizmo);
    }

    @Override
    public void addCircle(String identifier) {
        Gizmo gizmo = new Circle();
        gizmo.setPosition((int) this.selX, (int) this.selY);
        this.gizmos.put(identifier, gizmo);
    }

    @Override
    public void addTriangle(String identifier) {
        Gizmo gizmo = new Triangle();
        gizmo.setPosition((int) this.selX, (int) this.selY);
        this.gizmos.put(identifier, gizmo);
    }

    @Override
    public void addRightFlipper(String identifier) {
        Gizmo gizmo = new Flipper(false);
        gizmo.setPosition((int) this.selX, (int) this.selY);
        this.gizmos.put(identifier, gizmo);
    }

    @Override
    public void addLeftFlipper(String identifier) {
        Gizmo gizmo = new Flipper(true);
        gizmo.setPosition((int) this.selX, (int) this.selY);
        this.gizmos.put(identifier, gizmo);
    }

    @Override
    public void rotateGizmo() {
        Gizmo gizmo = this.getGizmoAt(this.selX, this.selY);
        if (gizmo != null) {
            gizmo.rotate();
        }
    }

    @Override
    public void addBall(String identifier) {
        Ball ball = new Ball();
        ball.setPosition(this.selX, this.selY);
        // TODO: manage conflicting identifiers?
        this.balls.put(identifier, ball);
    }

    @Override
    public void setBallVelocity(double vX, double vY) {
        Ball ball = this.getBallAt(this.selX, this.selY);
        if (ball != null) {
            ball.setVelocity(new Vect(vX, vY));
        }
    }

    @Override
    public double getGravity() {
        return this.gravity;
    }

    @Override
    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    @Override
    public double getFrictionMu() {
        return this.mu;
    }

    @Override
    public double getFrictionMu2() {
        return this.mu2;
    }

    @Override
    public void setFriction(double mu, double mu2) {
        this.mu = mu;
        this.mu2 = mu2;
    }

    @Override
    public void triggerOnKeyPress(int key) {
        Gizmo gizmo = this.getGizmoAt(this.selX, this.selY);
        if (gizmo != null) {
            this.keyPressMap.computeIfAbsent(key, k -> new HashSet<>()).add(gizmo);
        }
    }

    @Override
    public void triggerOnKeyRelease(int key) {
        Gizmo gizmo = this.getGizmoAt(this.selX, this.selY);
        if (gizmo != null) {
            this.keyReleaseMap.computeIfAbsent(key, k -> new HashSet<>()).add(gizmo);
        }
    }

    @Override
    public void triggerOnOuterWalls() {
        Gizmo gizmo = this.getGizmoAt(this.selX, this.selY);
        if (gizmo != null) {
            this.wallTriggers.add(gizmo);
        }
    }

    @Override
    public void triggerOnGizmo(double sX, double sY) {
        Gizmo source = this.getGizmoAt(sX, sY);
        Gizmo destination = this.getGizmoAt(this.selX, this.selY);
        if (source != null && destination != null) {
            this.gizmoMap.computeIfAbsent(source, s -> new HashSet<>()).add(destination);
        }
    }

    @Override
    public Set<Vect> getBallPositions() {
        return this.balls.values()
                .stream()
                .map(b -> new Vect(b.getX(), b.getY()))
                .collect(Collectors.toSet());
    }

    @Override
    public void keyPressed(int keyCode) {
        this.keyPressMap.getOrDefault(keyCode, Collections.emptySet()).forEach(Gizmo::trigger);
    }

    @Override
    public void keyReleased(int keyCode) {
        this.keyReleaseMap.getOrDefault(keyCode, Collections.emptySet()).forEach(Gizmo::trigger);
    }

    @Override
    public void tick() {
        gizmos.values().forEach(Gizmo::tick);
    }

    @Override
    public Collection<ReadGizmo> getGizmos() {
        return Collections.unmodifiableCollection(gizmos.values());
    }
 
    @Override
    public Map<Integer, Set<ReadGizmo>> getKeyPressToGizmoMapping() {
    	Map<Integer, Set<ReadGizmo>> keyPressToGizmo = new HashMap<>();
    	
    	for(Map.Entry<Integer, Set<Gizmo>> entry: this.keyPressMap.entrySet()) {
    		Set<ReadGizmo> triggeredGizmos = new HashSet<>();
    		for(Gizmo gizmo: entry.getValue()) {
    			triggeredGizmos.add(gizmo);
    		}
    		keyPressToGizmo.put(entry.getKey(), triggeredGizmos);
    	}
    	
    	return keyPressToGizmo;
    }
    
    @Override
    public Map<Integer, Set<ReadGizmo>> getKeyReleaseToGizmoMapping() {
    	Map<Integer, Set<ReadGizmo>> keyReleaseToGizmo = new HashMap<>();
    	
    	for(Map.Entry<Integer, Set<Gizmo>> entry: this.keyReleaseMap.entrySet()) {
    		Set<ReadGizmo> triggeredGizmos = new HashSet<>();
    		for(Gizmo gizmo: entry.getValue()) {
    			triggeredGizmos.add(gizmo);
    		}
    		keyReleaseToGizmo.put(entry.getKey(), triggeredGizmos);
    	}
    	
    	return keyReleaseToGizmo;
    }
    
    @Override
    public Map<ReadGizmo, Set<ReadGizmo>> getGizmoToGizmoMapping() {
    	Map<ReadGizmo, Set<ReadGizmo>> keyReleaseToGizmo = new HashMap<>();
    	
    	for(Map.Entry<Gizmo, Set<Gizmo>> entry: this.gizmoMap.entrySet()) {
    		Set<ReadGizmo> triggeredGizmos = new HashSet<>();
    		for(Gizmo gizmo: entry.getValue()) {
    			triggeredGizmos.add(gizmo);
    		}
    		keyReleaseToGizmo.put(entry.getKey(), triggeredGizmos);
    	}
    	
    	return keyReleaseToGizmo;
    }
    
    public void load(InputStream stream) throws SyntaxError {
    	Loader loader = new Loader(this);
    	loader.load(stream);
    }
    
    public void save(OutputStream stream) throws FileNotFoundException {
    	Saver saver = new Saver(this);
    	saver.save(stream);
    }
}

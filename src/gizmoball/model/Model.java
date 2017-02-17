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

    //A map from Gizmo position to Gizmos
    private Map<Vect, Gizmo> gizmos;
    //A map from Ball position to Balls
    private Map<Vect, Set<Ball>> balls;
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
        this.keyPressMap = new HashMap<>();
        this.keyReleaseMap = new HashMap<>();
        this.gizmoMap = new HashMap<>();
        this.wallTriggers = new HashSet<>();
        this.balls = new HashMap<>();
    }

    private Gizmo getGizmoAt(double x, double y) {
    	
        return this.gizmos.get(new Vect((int)x, (int)y));
    }


    /*Multiple balls could be at the same position e.g. inside an absorber*/
    /*private Ball getBallAt(double x, double y) {
        return this.balls.values().stream()
                .filter(b -> b.contains(x, y))
                .findFirst().orElse(null);
    }*/

    @Override
    public void select(double x, double y) {
        this.selX = x;
        this.selY = y;
    }

    @Override
    public void move(double dX, double dY) {
        Gizmo gizmo = this.getGizmoAt(this.selX, this.selY);
        if (gizmo != null) {
            this.gizmos.remove(new Vect((int)this.selX, (int)this.selY));
            this.gizmos.put(new Vect((int)dX, (int)dY), gizmo);
            return;
        }
        Ball ball = this.balls.get(new Vect(this.selX, this.selY)).iterator().next();
        if (ball != null) {
        	//If the ball != null then that means the Set of balls pointed at by this position must be at
        	//least 1
        	if (this.balls.get(new Vect(this.selX, this.selY)).size() == 1) {
        		this.balls.remove(new Vect(this.selX, this.selY));
        	} else /*If the number of balls at the selected position is greater than 1*/{
        		this.balls.get(new Vect(this.selX, this.selY)).remove(ball);
        	}        	
        	Set<Ball> newBallSet = new HashSet<>();
    		newBallSet.add(ball);
    		this.balls.put(new Vect(dX, dY), newBallSet);
        }
    }

    @Override
    public void delete() {
        Gizmo gizmo = this.getGizmoAt(this.selX, this.selY);
        if (gizmo != null) {
            this.gizmos.remove(new Vect(this.selX, this.selY));
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
        Ball ball = this.balls.get(new Vect(this.selX, this.selY)).iterator().next();
        if (ball != null) {
            this.balls.remove(new Vect(this.selX, this.selY));
        }
    }

    @Override
    public void addAbsorber(int width, int height) throws PositionOverlapException{
        Gizmo gizmo = new Absorber(width, height);
        
        if (this.gizmos.containsKey(new Vect((int)this.selX, (int) this.selY))) {
        	throw new PositionOverlapException();
        }
        else {
        	this.gizmos.put(new Vect((int)this.selX, (int)this.selY), gizmo);
        }
    }

    @Override
    public void addSquare() throws PositionOverlapException {
        Gizmo gizmo = new Square();
        
        if (this.gizmos.containsKey(new Vect((int)this.selX, (int)this.selY))) {
        	throw new PositionOverlapException();
        }
        else {
        	this.gizmos.put(new Vect((int)this.selX, (int)this.selY), gizmo);
        }
    }

    @Override
    public void addCircle() throws PositionOverlapException {
        Gizmo gizmo = new Circle();
        
        if (this.gizmoMap.containsKey(new Vect((int)this.selX, (int)this.selY))) {
        	throw new PositionOverlapException();
        }
        else {
        	this.gizmos.put(new Vect((int)this.selX, (int) this.selY), gizmo);
        }
    }

    @Override
    public void addTriangle() throws PositionOverlapException {
        Gizmo gizmo = new Triangle();
        
        if (this.gizmoMap.containsKey(new Vect((int) this.selX, (int) this.selY))) {
        	throw new PositionOverlapException();
        }
        else {
        	this.gizmos.put(new Vect((int)this.selX, (int)this.selY), gizmo);
        }
    }

    @Override
    public void addRightFlipper() throws PositionOverlapException {
        Gizmo gizmo = new Flipper(false);
        
        if (this.gizmoMap.containsKey(new Vect((int) this.selX, (int) this.selY))) {
        	throw new PositionOverlapException();
        }
        else {
        	this.gizmos.put(new Vect((int)this.selX, (int)this.selY), gizmo);
        }
    }

    @Override
    public void addLeftFlipper() throws PositionOverlapException {
        Gizmo gizmo = new Flipper(true);
        
        if (this.gizmos.containsKey(new Vect((int)this.selX, (int)this.selY))) {
        	throw new PositionOverlapException();
        }
        else {
        	this.gizmos.put(new Vect((int)this.selX, (int)this.selY), gizmo);
        }
    }

    @Override
    public void rotateGizmo() {
        Gizmo gizmo = this.getGizmoAt(this.selX, this.selY);
        if (gizmo != null) {
            gizmo.rotate();
        }
    }

    @Override
    public void addBall(double velocityX, double velocityY) throws PositionOverlapException {
        
    	Ball ball = new Ball();
    	ball.setVelocity(new Vect(velocityX, velocityY));
    	if(this.balls.containsKey(new Vect(this.selX, this.selY))) {
    		throw new PositionOverlapException();
    	} 
    	else {
    		Set<Ball> newBallSet = new HashSet<>();
    		newBallSet.add(ball);
    		this.balls.put(new Vect(this.selX, this.selY), newBallSet);
    	}
    }

    @Override
    public void setBallVelocity(double vX, double vY) {
        Ball ball = this.balls.get(new Vect(this.selX, this.selY)).iterator().next();
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
    public void triggerOnGizmo(ReadGizmo gizmo) {
        Gizmo destination = this.gizmos.get(new Vect(this.selX, this.selY));
        Gizmo source = (Gizmo)gizmo;
        
        if (source != null && destination != null) {
            this.gizmoMap.computeIfAbsent(source, s -> new HashSet<>()).add(destination);
        }
    }

    @Override
    public Set<Vect> getBallPositions() {
        return this.balls.keySet();
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
  
    public Map<Vect, ReadGizmo> getPositionToGizmoMap() {
    	return new HashMap<>(this.gizmos);
    }

	@Override
	public Map<Vect, Set<ReadBall>> getPositionToBallMap() {
		Map<Vect, Set<ReadBall>> positionToBall = new HashMap<>();
		
		for (Map.Entry<Vect, Set<Ball>> entry: this.balls.entrySet()) {
			positionToBall.put(entry.getKey(), new HashSet<ReadBall>(entry.getValue()));
		}
		
		return positionToBall;
	}
}

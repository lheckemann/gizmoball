package gizmoball.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
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
import gizmoball.model.gizmos.ReadGizmo.GizmoType;
import gizmoball.model.gizmos.ReadGizmo.Rotation;

public class Model implements BuildModel, RunModel {
    private static final Set<String> DEPENDENT = new HashSet<>(Arrays.asList(
                "Rotate", "Delete", "Move", "Connect", "KeyConnect"));
    
    //At the moment there is only one non rotating gizmo but since we might want to add more
    //in the future, decided to add a Set to declare which Gizmo cannot be rotated 
    //via a Rotate command.
    private static final Set<GizmoType> NON_ROTATING_GIZMOS = new HashSet<>(Arrays.asList(
    		     GizmoType.ABSORBER));

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
    private Map<Integer, Set<Gizmo>> keyPressMap;
    //A map from Key Id to Gizmo
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
        
        scanner.close();
        
        for (List<String> tokens : dependent) {
            this.loadCommand(tokens);
        }
    }

    private void loadCommand(List<String> tokens) throws SyntaxError {
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
                case "Ball":
                	//TODO: Need to add in parsing for Ball Velocity
                    SYNTAX_ERROR = "Ball <identifier> <float> <float>";
                    if (tokens.size() != 3) {
                        throw new SyntaxError(SYNTAX_ERROR);
                    }
                    this.select(Double.parseDouble(tokens.get(2)),
                                Double.parseDouble(tokens.get(3)));
                    this.addBall(tokens.get(1));
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
                            throw new SyntaxError(SYNTAX_ERROR);
                    }
            }
        } catch (NumberFormatException|IndexOutOfBoundsException e) {
            throw new SyntaxError(SYNTAX_ERROR);
        }
    }

    public OutputStream save() {
		
        String modelString = "";
        
       	//Write Gizmo declarations to output stream 
        modelString += this.dumpGizmoDeclarations();
		//Write Ball declarations
        modelString += this.dumpBallDeclarations();
		//Write Rotate commands
        modelString += this.dumpRotateCommands();
		//Write Connect commands
        modelString += this.dumpConnectCommands();
		//Write KeyConnect commands	
        modelString += this.dumpKeyConnectCommands();
        //Write friction and gravity
        modelString += this.dumpFrictionGravityDeclarations();
        
        OutputStream modelStream = new ByteArrayOutputStream();
        
        try {
			modelStream.write(modelString.getBytes(StandardCharsets.UTF_8));
		} catch (IOException e) {
			//Should never reach here
		}
        
        return modelStream;

    }
    
    //Returns an output stream containing all of 
    //the Gizmo declarations for Gizmos in this model
    private String dumpGizmoDeclarations() {
        String gizmoDeclarations = "";
        for(Map.Entry<String, Gizmo> currEntry: this.gizmos.entrySet()) {
    	    String gizmoId = currEntry.getKey();
    	    Gizmo currGizmo = currEntry.getValue();
            
    	    if(currGizmo.getType() == GizmoType.ABSORBER) {
    	        gizmoDeclarations += String.format("%s %s %d %d %d %d\n", 
                        this.convertGizmoTypeToString(currGizmo.getType()),
                        gizmoId,
                        currGizmo.getX(),
                        currGizmo.getY(),
                        currGizmo.getX() + currGizmo.getWidth(),
                        currGizmo.getY() + currGizmo.getHeight());	
    	    }
    	    else {
    	        gizmoDeclarations += String.format("%s %s %d %d\n", 
                        this.convertGizmoTypeToString(currGizmo.getType()),
                        gizmoId,
                        currGizmo.getX(),
                        currGizmo.getY());
    	    }
        }
        
        return gizmoDeclarations;
    }
    
    private String convertGizmoTypeToString(GizmoType type) {
        switch(type) {
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
    
    private String dumpBallDeclarations() {
    	String ballDeclarations = "";
        for(Map.Entry<String, Ball> currEntry: this.balls.entrySet()) {
    	    String ballId = currEntry.getKey();
    	    Ball currBall = currEntry.getValue();
            
    	    ballDeclarations += String.format("Ball %s %f %f %f %f\n",
    	    		                          ballId,
    	    		                          currBall.getX(),
    	    		                          currBall.getY(),
    	    		                          currBall.getVelocityX(),
    	    		                          currBall.getVelocityY()
    	    		                          );
        }
        
        return ballDeclarations;
    }
    
    private String dumpRotateCommands() {
        String rotateCommands = "";
        for(Map.Entry<String, Gizmo> currEntry: this.gizmos.entrySet()) {
    	    String gizmoId = currEntry.getKey();
    	    Gizmo currGizmo = currEntry.getValue();
            
    	    //If the current gizmo can be rotated
    	    if(false == NON_ROTATING_GIZMOS.contains(currGizmo.getType())) {
    	    	//Get the number of rotation commands required
    	        int numberOfRotations = convertRotationToNumOfRotations(currGizmo.getRotation());
    	        //Create all of the necessary rotate commands
    	        for(int i=0; i < numberOfRotations; i++) {
    	        	rotateCommands += String.format("Rotate %s\n", gizmoId);
    	        }
    	    }
        }
        
        return rotateCommands;	
    }
    
    //Used to convert a Rotation (North, East, South West)
    //to the number of 90 degree rotations needed to get from 
    //North to the given Rotation
    private int convertRotationToNumOfRotations(Rotation rotation) {
    	switch(rotation) {
    	    case N:
    	        return 0;
    	    case E:
    	        return 1;
    	    case S:
    	        return 2;
    	    case W:
    	        return 3;
    	    default:
    	        return 0;
    	}
    }

    private String dumpConnectCommands() {
    	String connectCommands = "";
    	for(Map.Entry<Gizmo, Set<Gizmo>> currConnection: this.gizmoMap.entrySet()) {
    		String fromGizmoId = this.getGizmoId(currConnection.getKey());
    		Set<String> toGizmoIdCollection = this.getGizmoIdSet(currConnection.getValue());
    		
    		for(String toGizmoId: toGizmoIdCollection) {
    			connectCommands += String.format("Connect %s %s\n", fromGizmoId, toGizmoId);
    		}
    	}
    	
    	return connectCommands;
    }
    
    private String dumpKeyConnectCommands() {
    	String keyConnectCommands = "";
  
    	//Add down commands
    	for(Map.Entry<Integer, Set<Gizmo>> currKeyConnection: this.keyPressMap.entrySet()) {
    		Integer fromKeyId = currKeyConnection.getKey();
    		Set<String> toGizmoIdCollection = this.getGizmoIdSet(currKeyConnection.getValue());
    		
    		for(String toGizmoId: toGizmoIdCollection) {
    			keyConnectCommands += String.format("KeyConnect key %d down %s\n", 
    					              fromKeyId,
    					              toGizmoId);
    		}
    	}
    	
    	//Add up commands
    	for(Map.Entry<Integer, Set<Gizmo>> currKeyConnection: this.keyPressMap.entrySet()) {
    		Integer fromKeyId = currKeyConnection.getKey();
    		Set<String> toGizmoIdCollection = this.getGizmoIdSet(currKeyConnection.getValue());
    		
    		for(String toGizmoId: toGizmoIdCollection) {
    			keyConnectCommands += String.format("KeyConnect key %d up %s\n", 
    					              fromKeyId,
    					              toGizmoId);
    		}
    	}
    	
    	return keyConnectCommands;
    }
    
    private String getGizmoId(Gizmo gizmo) {
    	for(Map.Entry<String, Gizmo> currGizmoEntry: this.gizmos.entrySet()) {
    		if(currGizmoEntry.getValue().equals(gizmo)) {
    			return currGizmoEntry.getKey();
    		}
    	}
    	
    	return "";
    }
    
    private String dumpFrictionGravityDeclarations() {
    	String gravityDeclaration = String.format("Gravity %f\n", this.gravity);
    	String frictionDeclaration = String.format("Friction %f %f\n", this.getFrictionMu(), this.getFrictionMu2());
    	
    	return gravityDeclaration + frictionDeclaration;
    }
    
    private Set<String> getGizmoIdSet(Set<Gizmo> gizmoSet) {
    	Set<String> gizmoIdSet = new HashSet<>();
    	for(Gizmo gizmo: gizmoSet) {
    		gizmoIdSet.add(getGizmoId(gizmo));
    	}
    	
    	return gizmoIdSet;
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

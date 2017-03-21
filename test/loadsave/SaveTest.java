package loadsave;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import java.awt.event.KeyEvent;

import gizmoball.controller.save.Saver;
import gizmoball.controller.save.StandardSaver;
import gizmoball.main.Main;
import gizmoball.model.Model;
import gizmoball.model.PositionOutOfBoundsException;
import gizmoball.model.PositionOverlapException;
import gizmoball.model.ReadModel;
import gizmoball.model.SyntaxError;
import gizmoball.model.gizmos.Absorber;
import gizmoball.model.gizmos.Circle;
import gizmoball.model.gizmos.Flipper;
import gizmoball.model.gizmos.GizmoType;
import gizmoball.model.gizmos.InvalidAbsorberWidthHeight;
import gizmoball.model.gizmos.NonRotatableException;
import gizmoball.model.gizmos.Square;
import gizmoball.model.gizmos.StandardFlipper;
import gizmoball.model.gizmos.Triangle;
import gizmoball.model.gizmos.SpinningFlipper;
import gizmoball.model.gizmos.Spawner;
import gizmoball.model.gizmos.Sink;

public class SaveTest {

    private Model model;
    private Saver saver;
    private Model standardModel;
    private Model extendedModel;
    private Model emptyModel;
    private List<String> standardTokens;
    private List<String> extendedTokens;
    private List<String> emptyTokens;
    
    double ballX = 1;
    double ballY = 1;
    double ballVelX = 2.0;
    double ballVelY = -3.0;
    int squareX = 2;
    int squareY = 2;
    int circleX = 3;
    int circleY = 3;
    int triangleX = 4;
    int triangleY = 4;
    int numberOfTriangleRotations = 3;
    int leftFlipperX = 5;
    int leftFlipperY = 5;
    int rightFlipperX = 7;
    int rightFlipperY = 7;
    int absorberX = 0;
    int absorberY = 19;
    int absorberWidth = 5;
    int absorberHeight = 1;
    
    int leftSpinningFlipperX = 12;
    int leftSpinningFlipperY = 12;
    int rightSpinningFlipperX = 14;
    int rightSpinningFlipperY = 14;
    int numberOfRightSpinningFlipperRotations = 2;
    int spawnerX = 16;
    int spawnerY = 16;
    int sinkX = 18;
    int sinkY = 18;
    
    double gravity = 0.025;
    double mu = 0.25;
    double mu2 = 0.25;
    
    @Before
    public void setup() {
        this.model = new Model(20, 20);
        this.standardModel = new Model(20, 20);
        this.extendedModel = new Model(20, 20);
        this.emptyModel = new Model(20, 20);
        
        this.standardTokens = new ArrayList<>();
        this.extendedTokens = new ArrayList<>();
        this.emptyTokens = new ArrayList<>();
        
        initEmptyModel(emptyModel);
        initStandardModel(standardModel);
        initExtendedModel(extendedModel);
        initEmptyTokens(emptyTokens);
        initStandardTokens(standardTokens);
        initExtendedTokens(extendedTokens);
    }
    
    private void initEmptyTokens(List<String> emptyTokens) {
        emptyTokens.add(String.format("Gravity %f", this.gravity));
        emptyTokens.add(String.format("Friction %f %f", this.mu, this.mu2));
        Collections.sort(emptyTokens);
    }
    
    private void initStandardTokens(List<String> standardTokens) {
        initEmptyTokens(standardTokens);
        
        String ballId = "B0";
        standardTokens.add(String.format("Ball %s %f %f %f %f", ballId, this.ballX, this.ballY, this.ballVelX, this.ballVelY));
        String circleId = "Circle0";
        standardTokens.add(String.format("Circle %s %d %d", circleId, this.circleX, this.circleY));
        String squareId = "Square0";
        standardTokens.add(String.format("Square %s %d %d", squareId, this.squareX, this.squareY));
        String triangleId = "Triangle0";
        standardTokens.add(String.format("Triangle %s %d %d", triangleId, this.triangleX, this.triangleY));
        String leftFlipperId = "LeftFlipper0";
        standardTokens.add(String.format("LeftFlipper %s %d %d", leftFlipperId, this.leftFlipperX, this.leftFlipperY));
        String rightFlipperId = "RightFlipper0";
        standardTokens.add(String.format("RightFlipper %s %d %d", rightFlipperId, this.rightFlipperX, this.rightFlipperY));
        String absorberId = "Absorber0";
        standardTokens.add(String.format("Absorber %s %d %d %d %d", absorberId, this.absorberX, this.absorberY, this.absorberX + this.absorberWidth, this.absorberY + this.absorberHeight));
        
        for (int i=0; i < this.numberOfTriangleRotations; i++) {
            standardTokens.add(String.format("Rotate %s", triangleId));
        }
        
        standardTokens.add(String.format("Connect %s %s", squareId, rightFlipperId));
        standardTokens.add(String.format("Connect OuterWalls %s", absorberId));
        standardTokens.add(String.format("KeyConnect key %d down %s", KeyEvent.VK_SPACE, leftFlipperId));
        standardTokens.add(String.format("KeyConnect key %d up %s", KeyEvent.VK_SPACE, leftFlipperId));
        Collections.sort(standardTokens);
    }
    
    private void initExtendedTokens(List<String> extendedTokens) {
        initStandardTokens(extendedTokens);
        
        String leftSpinningFlipperId = "LeftSpinningFlipper0";
        extendedTokens.add(String.format("LeftSpinningFlipper %s %d %d", leftSpinningFlipperId, this.leftSpinningFlipperX, this.leftSpinningFlipperY));
        String rightSpinningFlipperId = "RightSpinningFlipper0";
        extendedTokens.add(String.format("RightSpinningFlipper %s %d %d", rightSpinningFlipperId, this.rightSpinningFlipperX,this.rightSpinningFlipperY));
        String spawnerId = "Spawner0";
        extendedTokens.add(String.format("Spawner %s %d %d", spawnerId, this.spawnerX, this.spawnerY));
        String sinkId = "Sink0";
        extendedTokens.add(String.format("Sink %s %d %d", sinkId, this.sinkX, this.sinkY));
        
        for (int i=0; i < this.numberOfRightSpinningFlipperRotations; i++) {
            extendedTokens.add(String.format("Rotate %s", rightSpinningFlipperId));
        }
        extendedTokens.add(String.format("Connect %s %s", sinkId, spawnerId));
        extendedTokens.add(String.format("KeyConnect key %d down %s", KeyEvent.VK_Q, spawnerId));
        extendedTokens.add(String.format("Connect OuterWalls %s", spawnerId));
        
        Collections.sort(extendedTokens);
    }
    
    private void initEmptyModel(Model emptyModel) {
        emptyModel.setGravity(this.gravity);
        emptyModel.setFriction(this.mu, this.mu2);
    }
    
    private void initStandardModel(Model standardModel) {
        initEmptyModel(standardModel);
        try {
            standardModel.select(ballX, ballY);
            standardModel.addBall(ballVelX, ballVelY);
            standardModel.select(squareX, squareY);
            standardModel.addGizmo(new Square());
            standardModel.select(triangleX, triangleY);
            standardModel.addGizmo(new Triangle());
            for (int i=0; i < numberOfTriangleRotations; i++) {
                try {
                    standardModel.rotateGizmo();
                } catch (NonRotatableException e) {
                }
            }
            standardModel.select(circleX, circleY);
            standardModel.addGizmo(new Circle());
            standardModel.select(leftFlipperX, leftFlipperY);
            standardModel.addGizmo(new StandardFlipper(true));
            standardModel.select(rightFlipperX, rightFlipperY);
            standardModel.addGizmo(new StandardFlipper(false));
            standardModel.select(absorberX, absorberY);
            try {
                standardModel.addGizmo(new Absorber(absorberWidth, absorberHeight));
            } catch (InvalidAbsorberWidthHeight e) {
            }
            
            standardModel.select(rightFlipperX, rightFlipperY);
            standardModel.triggerOnGizmoAt(squareX, squareY);
            standardModel.select(absorberX, absorberY);
            standardModel.triggerOnOuterWalls();
            standardModel.select(leftFlipperY, leftFlipperY);
            standardModel.triggerOnKeyPress(KeyEvent.VK_SPACE);
            standardModel.triggerOnKeyRelease(KeyEvent.VK_SPACE);
        } catch (PositionOverlapException | PositionOutOfBoundsException e) {
        }
    }
    
    private void initExtendedModel(Model extendedModel) {
        initStandardModel(extendedModel);
        extendedModel.select(leftSpinningFlipperX, leftSpinningFlipperY);
        try {
            extendedModel.addGizmo(new SpinningFlipper(true));
        } catch (PositionOverlapException e) {
        } catch (PositionOutOfBoundsException e) {
        }
        extendedModel.select(rightSpinningFlipperX, rightSpinningFlipperY);
        try {
            extendedModel.addGizmo(new SpinningFlipper(false));
            for (int i=0; i<this.numberOfRightSpinningFlipperRotations; i++) {
                try {
                    extendedModel.rotateGizmo();
                } catch (NonRotatableException e) {
                }
            }
        } catch (PositionOverlapException e) {
        } catch (PositionOutOfBoundsException e) {
        }
        extendedModel.select(spawnerX, spawnerY);
        try {
            extendedModel.addGizmo(new Spawner());
        } catch (PositionOverlapException e) {
        } catch (PositionOutOfBoundsException e) {
        }
        extendedModel.select(sinkX, sinkY);
        try {
            extendedModel.addGizmo(new Sink());
        } catch (PositionOverlapException e) {
        } catch (PositionOutOfBoundsException e) {
        }
        
        extendedModel.select(spawnerX, spawnerY);
        extendedModel.triggerOnGizmoAt(sinkX, sinkY);
        extendedModel.triggerOnKeyPress(KeyEvent.VK_Q);
        extendedModel.triggerOnOuterWalls();
    }
    
    private void displayActualAndExpectedTokens(List<String> actualTokens, List<String> expectedTokens) {
        if (actualTokens.size() == expectedTokens.size()) {
            for (int i=0; i < actualTokens.size(); i++) {
                if ( ! actualTokens.get(i).equals(expectedTokens.get(i))) {
                    System.out.println("\nExpected value: " + expectedTokens.get(i));
                    System.out.println("Actual value: " + actualTokens.get(i));
                }
            }
        } else {
            //The tokens that aren't shared between actual tokens & expected tokens
            List<String> nonUnionTokens = new ArrayList<>();
            if (actualTokens.size() < expectedTokens.size()) {
                System.out.println("Expected output had these lines: ");
                expectedTokens.removeAll(actualTokens);
                nonUnionTokens = expectedTokens;
            } else {
                System.out.print("Actual output had these lines: ");
                actualTokens.removeAll(expectedTokens);
                nonUnionTokens = actualTokens;
            }
            System.out.println("\n");
            for (String token: nonUnionTokens) {
                System.out.println(token);
            }
        }
    }
    
    @Test
    public void testSavingEmptyModelWithStandardSaver() {
        Saver standardSaver = new StandardSaver(this.emptyModel);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        standardSaver.save(outStream);
        String saveString = new String(outStream.toByteArray(), StandardCharsets.UTF_8);
        try {
            outStream.toString(saveString);
        } catch (UnsupportedEncodingException e1) {
        }
        List<String> tokens = new LinkedList<>(Arrays.asList(saveString.split("\n")));
        Collections.sort(tokens);
        
        if (tokens.equals(this.emptyTokens)) {
            
        }
        else {
            displayActualAndExpectedTokens(tokens, this.emptyTokens); 
            fail();
        }
        
    }
    
    @Test
    public void testSavingEmptyModelWithExtendedSaver() {
        Saver standardSaver = new Saver(this.emptyModel);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        standardSaver.save(outStream);
        String saveString = new String(outStream.toByteArray(), StandardCharsets.UTF_8);
        try {
            outStream.toString(saveString);
        } catch (UnsupportedEncodingException e1) {
        }
        List<String> tokens = new LinkedList<>(Arrays.asList(saveString.split("\n")));
        Collections.sort(tokens);
        if (tokens.equals(this.emptyTokens)) {
            
        }
        else {
            displayActualAndExpectedTokens(tokens, this.emptyTokens); 
            fail();
        }
        
    }
    
    @Test
    public void testSavingStandardModelWithStandardSaver() {
        Saver standardSaver = new StandardSaver(this.standardModel);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        standardSaver.save(outStream);
        String saveString = new String(outStream.toByteArray(), StandardCharsets.UTF_8);
        try {
            outStream.toString(saveString);
        } catch (UnsupportedEncodingException e1) {
        }

        List<String> tokens = new LinkedList<>(Arrays.asList(saveString.split("\n")));
        Collections.sort(tokens);
        if (tokens.equals(this.standardTokens)) {
            
        }
        else {
            displayActualAndExpectedTokens(tokens, this.standardTokens); 
            fail();
        }
    }
    
    @Test
    public void testSavingStandardModelWithExtendedSaver() {
        Saver standardSaver = new Saver(this.standardModel);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        standardSaver.save(outStream);
        String saveString = new String(outStream.toByteArray(), StandardCharsets.UTF_8);
        try {
            outStream.toString(saveString);
        } catch (UnsupportedEncodingException e1) {
        }
        
        List<String> tokens = new LinkedList<>(Arrays.asList(saveString.split("\n")));
        Collections.sort(tokens);
        if (tokens.equals(this.standardTokens)) {
            
        }
        else {
            displayActualAndExpectedTokens(tokens, this.standardTokens); 
            fail();
        }
    }
    
    @Test
    public void testSavingExtendedModelWithStandardSaver() {
        Saver standardSaver = new StandardSaver(this.extendedModel);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        standardSaver.save(outStream);
        String saveString = new String(outStream.toByteArray(), StandardCharsets.UTF_8);
        try {
            outStream.toString(saveString);
        } catch (UnsupportedEncodingException e1) {
        }
        
        List<String> tokens = new LinkedList<>(Arrays.asList(saveString.split("\n")));
        Collections.sort(tokens);
        if (tokens.equals(this.standardTokens)) {
            
        }
        else {
            displayActualAndExpectedTokens(tokens, this.standardTokens); 
            fail();
        }
    }
    
    @Test
    public void testSavingExtendedModelWithExtendedSaver() {
        Saver standardSaver = new Saver(this.extendedModel);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        standardSaver.save(outStream);
        String saveString = new String(outStream.toByteArray(), StandardCharsets.UTF_8);
        try {
            outStream.toString(saveString);
        } catch (UnsupportedEncodingException e1) {
        }
        
        List<String> tokens = new LinkedList<>(Arrays.asList(saveString.split("\n")));
        Collections.sort(tokens);
        if (tokens.equals(this.extendedTokens)) {
            
        }
        else {
            displayActualAndExpectedTokens(tokens, this.extendedTokens); 
            fail();
        }
    }
       
}

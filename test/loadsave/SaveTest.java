package loadsave;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import java.awt.event.KeyEvent;

import gizmoball.controller.save.Saver;
import gizmoball.controller.save.StandardSaver;
import gizmoball.model.*;
import gizmoball.model.gizmos.*;

public class SaveTest {
    private Model standardModel;
    private Model extendedModel;
    private Model emptyModel;
    private List<String> standardTokens;
    private List<String> extendedTokens;
    private List<String> emptyTokens;

    private double ballX = 1;
    private double ballY = 1;
    private double ballVelX = 2.0;
    private double ballVelY = -3.0;
    private int squareX = 2;
    private int squareY = 2;
    private int circleX = 3;
    private int circleY = 3;
    private int triangleX = 4;
    private int triangleY = 4;
    private int numberOfTriangleRotations = 3;
    private int leftFlipperX = 5;
    private int leftFlipperY = 5;
    private int rightFlipperX = 7;
    private int rightFlipperY = 7;
    private int absorberX = 0;
    private int absorberY = 19;
    private int absorberWidth = 5;
    private int absorberHeight = 1;

    private int leftSpinningFlipperX = 12;
    private int leftSpinningFlipperY = 12;
    private int rightSpinningFlipperX = 14;
    private int rightSpinningFlipperY = 14;
    private int numberOfRightSpinningFlipperRotations = 2;
    private int spawnerX = 16;
    private int spawnerY = 16;
    private int sinkX = 18;
    private int sinkY = 18;

    private double gravity = 0.025;
    private double mu = 0.25;
    private double mu2 = 0.25;

    @Before
    public void setup() {
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

        for (int i = 0; i < this.numberOfTriangleRotations; i++) {
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
        extendedTokens.add(String.format("RightSpinningFlipper %s %d %d", rightSpinningFlipperId, this.rightSpinningFlipperX, this.rightSpinningFlipperY));
        String spawnerId = "Spawner0";
        extendedTokens.add(String.format("Spawner %s %d %d", spawnerId, this.spawnerX, this.spawnerY));
        String sinkId = "Sink0";
        extendedTokens.add(String.format("Sink %s %d %d", sinkId, this.sinkX, this.sinkY));

        for (int i = 0; i < this.numberOfRightSpinningFlipperRotations; i++) {
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
            standardModel.addBall(new Ball(ballVelX, ballVelY));
            standardModel.select(squareX, squareY);
            standardModel.addGizmo(new Square());
            standardModel.select(triangleX, triangleY);
            standardModel.addGizmo(new Triangle());
            for (int i = 0; i < numberOfTriangleRotations; i++) {
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
            extendedModel.select(rightSpinningFlipperX, rightSpinningFlipperY);
            extendedModel.addGizmo(new SpinningFlipper(false));
            for (int i = 0; i < this.numberOfRightSpinningFlipperRotations; i++) {
                try {
                    extendedModel.rotateGizmo();
                } catch (NonRotatableException e) {
                }
            }
            extendedModel.select(spawnerX, spawnerY);
            extendedModel.addGizmo(new Spawner());
            extendedModel.select(sinkX, sinkY);
            extendedModel.addGizmo(new Sink());
            extendedModel.select(spawnerX, spawnerY);
            extendedModel.triggerOnGizmoAt(sinkX, sinkY);
            extendedModel.triggerOnKeyPress(KeyEvent.VK_Q);
            extendedModel.triggerOnOuterWalls();

        } catch (PositionOutOfBoundsException | PositionOverlapException e) {
        }
    }

    private void displayActualAndExpectedTokens(List<String> actualTokens, List<String> expectedTokens) {
        if (actualTokens.size() == expectedTokens.size()) {
            for (int i = 0; i < actualTokens.size(); i++) {
                if (!actualTokens.get(i).equals(expectedTokens.get(i))) {
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
            for (String token : nonUnionTokens) {
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

        } else {
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

        } else {
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

        } else {
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

        } else {
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

        } else {
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

        } else {
            displayActualAndExpectedTokens(tokens, this.extendedTokens);
            fail();
        }
    }
}
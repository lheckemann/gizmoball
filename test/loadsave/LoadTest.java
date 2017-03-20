package test.loadsave;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;

import org.junit.*;

import gizmoball.controller.load.StandardLoader;
import gizmoball.model.Model;
import gizmoball.model.ReadBall;
import gizmoball.model.SyntaxError;
import gizmoball.model.gizmos.GizmoType;
import gizmoball.model.gizmos.ReadGizmo;
import gizmoball.model.gizmos.Rotation;

public class LoadTest {

    private Model model;
   
    @Before
    public void setUp() {
        this.model = new Model(20, 20);
    }
    
    @Test
    public void loadExistingFileWithIncorrectBallDeclarationSyntax() {
        try {
            String testString = "Ball 0.1 0 3 fhdjfhdjfhdf\\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void loadFileWithIncorrectFlipperDeclarationSyntax() {
        try {
            String testString = "LeftFlipper RF0 3\\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void loadFileWithIncorrectSquareDeclarationSyntax() {
        try {
            String testString = "Square 0.1 0 3 fhdjfhdjfhdf\\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void loadFileWithIncorrectTriangleDeclarationSyntax() {
        try {
            String testString = "Triangle S0 0.1 0\\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void loadFileWithIncorrectCircleDeclarationSyntax() {
        try {
            String testString = "Circle C0 fkfjdf jfkdjf\\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void loadFileWithIncorrectAbsorberDeclarationSyntax() {
        try {
            String testString = "Absorber 0.1 0 3 3\\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void loadValidFileCheckAllDeclarationCommandsArePerformed() {
        try {
            String testString = "Ball B0 15 15 0 0\n"
                    + "Square S0 1 1\n"
                    + "Circle C0 2 2\n"
                    + "Triangle T0 3 3\n"
                    + "LeftFlipper LF0 4 4\n"
                    + "RightFlipper RF0 6 6\n"
                    + "Absorber A0 0 19 20 20";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            if (this.model.getGizmos().size() != 6) {
                fail();
            }
            for (ReadGizmo gizmo: this.model.getGizmos()) {
                switch (gizmo.getType()) {
                    case TRIANGLE:
                        if (gizmo.getX() == 3 && gizmo.getY() == 3 && gizmo.getWidth() == 1 && gizmo.getHeight() == 1) {
                           
                        }
                        else {
                            fail();
                        }
                        break;
                    case SQUARE:
                        if (gizmo.getX() == 1 && gizmo.getY() == 1 && gizmo.getWidth() == 1 && gizmo.getHeight() == 1) {
                            
                        }
                        else {
                            fail();
                        }
                        break;
                    case CIRCLE:
                        if (gizmo.getX() == 2 && gizmo.getY() == 2) {
                            
                        }
                        else {
                            fail();
                        }
                        break;
                    case LEFT_FLIPPER:
                        if (gizmo.getX() == 4 && gizmo.getY() == 4 && gizmo.getWidth() == 2 && gizmo.getHeight() == 2) {
                    
                        }
                        else {
                            fail();
                        }
                        break;
                    case RIGHT_FLIPPER:
                        if (gizmo.getX() == 6 && gizmo.getY() == 6 && gizmo.getWidth() == 2 && gizmo.getHeight() == 2) {
                           
                        }
                        else {
                            fail();
                        }
                        break;
                    case ABSORBER:
                        if (gizmo.getX() == 0 && gizmo.getY() == 19 && gizmo.getWidth() == 20 && gizmo.getHeight() == 1) {
                          
                        }
                        else {
                            fail();
                        }
                        break;
                    default:
                        fail();
                }
            }
          
            if (this.model.getBalls().size() != 1) {
                fail();
            }
            
            for (ReadBall ball: this.model.getBalls()) {
                if (ball.getX() == 15.0 && ball.getY() == 15.0 && ball.getVelocityX() == 0 && ball.getVelocityY() == 0) {
                }
                else {
                    fail();
                }
            }
            
            assertTrue(true);
        } catch (SyntaxError e) {
            fail();
        }
    }
    
    @Test
    public void loadFileWithIncorrectDeleteSyntax() {
        try {
            String testString = "Square S0 0 0\n"
                    + "Delete S0 fdfkjdf";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void loadFileWithIncorrectMoveSyntax() {
        try {
            String testString = "Square S0 0 0\n"
                    + "Move S0 fjdffjk";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void loadFileWithIncorrectConnectSyntax() {
        try {
            String testString = "Square S0 0 0\n"
                    + "Connect S0 S0 jfkdjff\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void loadFileWithIncorrectKeyConnectSyntax() {
        try {
            String testString = "Square S0 0 0\n"
                    + "KeyConnect S0 djksjf fjkfjf jf";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void loadFileWithIncorrectGravitySyntax() {
        try {
            String testString = "Gravity fjdfhdf\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void loadFileWithIncorrectFrictionSyntax() {
        try {
            String testString = "Fricition fdjfhdf jfjdhfd\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void checkDeleteCommandWithUndeclaredObjectThrowsError() {
        try {
            String testString = "Delete S0\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void checkRotateCommandWithUndeclaredObjectThrowsError() {
        try {
            String testString = "Rotate S0\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void checkMoveCommandWithUndeclaredObjectThrowsError() {
        try {
            String testString = "Move C0\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    //Test a connect command where both objects don't exist
    @Test
    public void checkConnectCommandWithBothUndeclaredObjectsThrowsError() {
        try {
            String testString = "Connect C0 T1\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    //Test a connect command where one object exists and the other one doesn't exist
    @Test
    public void checkConnectCommandWithOneUndeclaredObjectThrowsError() {
        try {
            String testString = "Square S0\n"
                    + "Connect S0 LF5";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    
    //Test a delete where only one object should be left
    @Test
    public void checkAllDeleteCommandsPerformedOneObjectLeft() {
        try {
            String testString = "Ball B0 15 15 0 0\n"
                    + "Square S0 1 1\n"
                    + "Triangle T1 5 5\n"
                    + "Delete B0\n"
                    + "Delete T1\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            if (this.model.getGizmos().size() != 1 || this.model.getBalls().size() != 0) {
                fail();
            }
            
            if (this.model.getGizmos().iterator().next().getType().equals(GizmoType.SQUARE)) {
                assertTrue(true);
            } else {
                fail();
            }
        } catch (SyntaxError e) {
            fail();
        }
    }
    
    @Test
    public void checkLoadWhereAllDeleteCommandsResultInNoObjects() {
        try {
            String testString = "Ball B0 15.0 15.0 0.0 0.0\n"
                    + "Square S0 1 1\n"
                    + "Triangle T1 5 5\n"
                    + "Delete B0\n"
                    + "Delete T1\n"
                    + "Delete S0";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            if (this.model.getGizmos().size() != 0 || this.model.getBalls().size() != 0) {
                fail();
            }
            else {
                assertTrue(true);
            }
        } catch (SyntaxError e) {
            System.out.println(e.getMessage());
            fail();
        }
    }
    
    @Test
    public void checkValidMoveCommandWorksCorrectly() {
        try {
            String testString = "Triangle T0 0 0\n"
                    + "Move T0 1 1";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            
            int numberOfGizmos = model.getGizmos().size();
            ReadGizmo gizmo = model.getGizmos().iterator().next();
            if (numberOfGizmos == 1  && gizmo.getType().equals(GizmoType.TRIANGLE) && gizmo.getX() == 1 && gizmo.getY() == 1)  {
                assertTrue(true);
            }
            else {
                fail();
            }
        } catch (SyntaxError e) {
            fail();
        }
    }
    
    @Test
    public void checkRotateCommandsOnNonAbsorberGizmoWorksCorrectly() {
        try {
            String testString = "Triangle T0 0 19\n"
                    + "Rotate T0";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            
            int numberOfGizmos = model.getGizmos().size();
            ReadGizmo gizmo = model.getGizmos().iterator().next();
            if (numberOfGizmos == 1  && gizmo.getType().equals(GizmoType.TRIANGLE) && gizmo.getRotation().equals(Rotation.EAST))  {
                assertTrue(true);
            }
            else {
                fail();
            }
        } catch (SyntaxError e) {
            fail();
        }
    }
    
    @Test 
    public void checkRotateCommandsOnAbsorberThrowsError() {
        try {
            String testString = "Absorber A0 0 19 20 20\n"
                    + "Rotate A0";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
           
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void checkRotateCommandsOnBallThrowsError() {
        try {
            String testString = "Ball B0 0 19 0 0\n"
                    + "Rotate B0";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
   
    @Test
    public void loadValidFileCheckAllGizmoToGizmoConnectionsLoaded() {
       try {
           String testString = "Triangle T0 3 3\n"
                   + "LeftFlipper LF0 4 4\n"
                   + "Absorber A0 0 19 20 20\n"
                   + "Connect A0 A0\n"
                   + "Connect T0 A0\n"
                   + "Connect T0 LF0\n"
                   + "Connect LF0 T0\n";
           InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
           this.model.load(new StandardLoader(), testStream);
            if (model.getGizmoToGizmoMap().size() != 3) {
                fail();
            }
            for (Map.Entry<ReadGizmo, Set<ReadGizmo>> entry : model.getGizmoToGizmoMap().entrySet()) {
                switch (entry.getKey().getType()) {
                    case TRIANGLE:
                        if (entry.getValue().size() != 2) {
                            fail();
                        }
                        ReadGizmo[] gizmos = new ReadGizmo[2];
                        entry.getValue().toArray(gizmos);
                        if ( ! ((gizmos[0].getType().equals(GizmoType.ABSORBER) && gizmos[1].getType().equals(GizmoType.LEFT_FLIPPER)) || 
                              (gizmos[0].getType().equals(GizmoType.LEFT_FLIPPER) && gizmos[1].getType().equals(GizmoType.ABSORBER)))){
                            fail();
                        }
                        break;
                    case LEFT_FLIPPER:
                        if (entry.getValue().size() != 1) {
                            fail();
                        }
                        if (entry.getValue().iterator().next().getType() != GizmoType.TRIANGLE) {
                            fail();
                        }
                        break;
                    case ABSORBER:
                        if (entry.getValue().size() != 1) {
                            fail();
                        }
                        if (entry.getValue().iterator().next().getType() != GizmoType.ABSORBER) {
                            fail();
                        }
                        break;
                    default:
                        fail();
                        break;
                }
            }
            
            assertTrue(true);
           
        } catch (SyntaxError e) {
            fail();
        }
    }
    
    @Test
    public void loadValidFileCheckAllKeyConnectionsLoaded() {
        try {
            String testString = "LeftFlipper LF0 0 0\n"
                    + "KeyConnect key 32 up LF0\n"
                    + "KeyConnect key 128 down LF0";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            if (model.getKeyPressToGizmoMap().size() != 1) {
                fail();
            }
            Map.Entry<Integer, Set<ReadGizmo>> keyMapping = model.getKeyPressToGizmoMap().entrySet().iterator().next();
            if (keyMapping.getKey() != 128) {
                fail();
            }
            ReadGizmo gizmo = keyMapping.getValue().iterator().next();
            if (gizmo.getType() != GizmoType.LEFT_FLIPPER) {
                fail();
            }
            if (model.getKeyReleaseToGizmoMap().size() != 1) {
                fail();
            }
            Map.Entry<Integer, Set<ReadGizmo>> keyReleaseMapping = model.getKeyReleaseToGizmoMap().entrySet().iterator().next();
            if (keyReleaseMapping.getKey() != 32) {
                fail();
            }
            gizmo = keyReleaseMapping.getValue().iterator().next();
            if (gizmo.getType() != GizmoType.LEFT_FLIPPER) {
                fail();
            }
            
            assertTrue(true);
        } catch (SyntaxError e) {
            System.out.println(e.getMessage());
            fail();
        }
    }
    
    @Test
    public void checkBallVelocityIsCorrect() {

        try {
            String testString = "Ball B0 15 15 50 -345\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            ReadBall ball = model.getBalls().iterator().next();
            if (ball.getVelocityX() == 50 && ball.getVelocityY() == -345) {
                assertTrue(true);
            } else {
                fail();
            }
        } catch (SyntaxError e) {
            fail();
        }
    }
    
    @Test
    public void checkOverlappingElementsThrowsError() {
        try {
            String testString = "Triangle T0 0 0\n"
                    + "Square S0 0 0";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void checkInvalidMoveThrowsException() {
        try {
            String testString = "Triangle T0 5 5\n"
                    + "Square S0 0 0\n"
                    + "Move S0 5 5";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void checkInvalidAbsorberDimensionsThrowsException() {
        try {
            String testString = "Absorber A0 0 19 0 19\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void loadValidFileCheckLastGravityAndFrictionAreUsed() {
        try {
            String testString = "Gravity 5\n"
                    + "Friction 3 5\n"
                    + "Gravity 20\n"
                    + "Friction 100 -90";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            if (this.model.getGravity() == 20 && this.model.getFrictionMu() == 100 && this.model.getFrictionMu2() == -90) {
                assertTrue(true);
            }
            else {
                fail();
            }
        } catch (SyntaxError e) {
            fail();
        }
    }

}


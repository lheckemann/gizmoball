package test.loadsave;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;

import org.junit.*;

import gizmoball.model.Model;
import gizmoball.model.ReadBall;
import gizmoball.model.SyntaxError;
import gizmoball.model.gizmos.ReadGizmo;
import gizmoball.model.gizmos.Rotation;
import gizmoball.model.gizmos.ReadGizmo.GizmoType;

public class LoadTest {

    private Model model;
    private final String TEST_FILE_FOLDER_PREFIX = "test/loadsave/testFiles/";
    private InputStream fileIncorrectBallSyntax = loadFile(TEST_FILE_FOLDER_PREFIX + "fileIncorrectBallSyntax");
    private InputStream fileIncorrectFlipperSyntax = loadFile(TEST_FILE_FOLDER_PREFIX + "fileIncorrectFlipperSyntax");
    private InputStream fileIncorrectSquareSyntax = loadFile(TEST_FILE_FOLDER_PREFIX + "fileIncorrectSquareSyntax");
    private InputStream fileIncorrectTriangleSyntax = loadFile(TEST_FILE_FOLDER_PREFIX + "fileIncorrectTriangleSyntax");
    private InputStream fileIncorrectCircleSyntax = loadFile(TEST_FILE_FOLDER_PREFIX + "fileIncorrectCircleSyntax");
    private InputStream fileIncorrectAbsorberSyntax = loadFile(TEST_FILE_FOLDER_PREFIX + "fileIncorrectAbsorberSyntax");
    private InputStream fileIncorrectDeleteSyntax = loadFile(TEST_FILE_FOLDER_PREFIX + "fileIncorrectDeleteSyntax");
    private InputStream fileIncorrectMoveSyntax = loadFile(TEST_FILE_FOLDER_PREFIX + "fileIncorrectMoveSyntax");
    private InputStream fileIncorrectConnectSyntax = loadFile(TEST_FILE_FOLDER_PREFIX + "fileIncorrectConnectSyntax");
    private InputStream fileIncorrectKeyConnectSyntax = loadFile(TEST_FILE_FOLDER_PREFIX + "fileIncorrectKeyConnectSyntax");
    private InputStream fileIncorrectGravitySyntax = loadFile(TEST_FILE_FOLDER_PREFIX + "fileIncorrectGravitySyntax");
    private InputStream fileIncorrectFrictionSyntax = loadFile(TEST_FILE_FOLDER_PREFIX + "fileIncorrectFrictionSyntax");
    
    private InputStream fileValidGizmoDeclarations = loadFile(TEST_FILE_FOLDER_PREFIX + "fileValidGizmoDeclarations");
    private InputStream fileCheckLastGravityAndFrictionUsed = loadFile(TEST_FILE_FOLDER_PREFIX + "fileCheckLastGravityAndFrictionUsed");
    private InputStream fileDeleteCommandUndeclaredObject = loadFile(TEST_FILE_FOLDER_PREFIX + "fileDeleteCommandUndeclaredObject");
    private InputStream fileRotateCommandUndeclaredObject = loadFile(TEST_FILE_FOLDER_PREFIX + "fileRotateCommandUndeclaredObject");
    private InputStream fileMoveCommandUndeclaredObject = loadFile(TEST_FILE_FOLDER_PREFIX + "fileMoveCommandUndeclaredObject");
    private InputStream fileConnectCommandBothUndeclaredObject = loadFile(TEST_FILE_FOLDER_PREFIX + "fileConnectCommandBothUndeclaredObject");
    private InputStream fileConnectCommandOneUndeclaredObject = loadFile(TEST_FILE_FOLDER_PREFIX + "fileConnectCommandOneUndeclaredObject");
    private InputStream fileCheckAllDeleteCommandsPerformedOneObjectLeft = loadFile(TEST_FILE_FOLDER_PREFIX + "fileCheckAllDeleteCommandsPerformedOneObjectLeft");
    private InputStream fileCheckAllDeleteCommandsResultInNoObjects = loadFile(TEST_FILE_FOLDER_PREFIX + "fileCheckAllDeleteCommandsResultInNoObjects");
    
    private InputStream fileCheckValidMoveCommandWorksCorrectly = loadFile(TEST_FILE_FOLDER_PREFIX + "fileCheckValidMoveCommandWorksCorrectly");
    private InputStream fileCheckRotateCommandsOnNonAbsorberGizmoWorksCorrectly = loadFile(TEST_FILE_FOLDER_PREFIX + "fileCheckRotateCommandsOnNonAbsorberGizmosWorksCorrectly");
    private InputStream fileCheckRotateCommandOnAsborberThrowsError = loadFile(TEST_FILE_FOLDER_PREFIX + "fileCheckRotateCommandOnAbsorberThrowsError");
    private InputStream fileCheckRotateCommandOnBallThrowsError = loadFile(TEST_FILE_FOLDER_PREFIX + "fileCheckRotateCommandOnBallThrowsError");
    @Before
    public void setUp() {
        this.model = new Model(20, 20);
    }
    
    private FileInputStream loadFile(String fileName) {
        File chosenFile = new File(fileName);
        try {
            return new FileInputStream(chosenFile);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
    
    @Test
    public void loadExistingFileWithIncorrectBallDeclarationSyntax() {
        try {
            this.model.load(fileIncorrectBallSyntax);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void loadFileWithIncorrectFlipperDeclarationSyntax() {
        try {
            this.model.load(fileIncorrectFlipperSyntax);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void loadFileWithIncorrectSquareDeclarationSyntax() {
        try {
            this.model.load(fileIncorrectSquareSyntax);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void loadFileWithIncorrectTriangleDeclarationSyntax() {
        try {
            this.model.load(fileIncorrectTriangleSyntax);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void loadFileWithIncorrectCircleDeclarationSyntax() {
        try {
            this.model.load(fileIncorrectCircleSyntax);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void loadFileWithIncorrectAbsorberDeclarationSyntax() {
        try {
            this.model.load(fileIncorrectAbsorberSyntax);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void loadValidFileCheckAllDeclarationCommandsArePerformed() {
        try {
            this.model.load(fileValidGizmoDeclarations);
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
                if (ball.getX() == 0.0 && ball.getY() == 0.0 && ball.getVelocityX() == 0 && ball.getVelocityY() == 0) {
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
            this.model.load(fileIncorrectDeleteSyntax);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void loadFileWithIncorrectMoveSyntax() {
        try {
            this.model.load(fileIncorrectMoveSyntax);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void loadFileWithIncorrectConnectSyntax() {
        try {
            this.model.load(fileIncorrectConnectSyntax);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void loadFileWithIncorrectKeyConnectSyntax() {
        try {
            this.model.load(fileIncorrectKeyConnectSyntax);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void loadFileWithIncorrectGravitySyntax() {
        try {
            this.model.load(fileIncorrectGravitySyntax);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void loadFileWithIncorrectFrictionSyntax() {
        try {
            this.model.load(fileIncorrectFrictionSyntax);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void checkDeleteCommandWithUndeclaredObjectThrowsError() {
        try {
            this.model.load(fileDeleteCommandUndeclaredObject);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void checkRotateCommandWithUndeclaredObjectThrowsError() {
        try {
            this.model.load(fileRotateCommandUndeclaredObject);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void checkMoveCommandWithUndeclaredObjectThrowsError() {
        try {
            this.model.load(fileMoveCommandUndeclaredObject);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    //Test a connect command where both objects don't exist
    @Test
    public void checkConnectCommandWithBothUndeclaredObjectsThrowsError() {
        try {
            this.model.load(fileConnectCommandBothUndeclaredObject);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    //Test a connect command where one object exists and the other one doesn't exist
    @Test
    public void checkConnectCommandWithOneUndeclaredObjectThrowsError() {
        try {
            this.model.load(fileConnectCommandOneUndeclaredObject);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    
    //Test a delete where only one object should be left
    @Test
    public void checkAllDeleteCommandsPerformedOneObjectLeft() {
        try {
            this.model.load(fileCheckAllDeleteCommandsPerformedOneObjectLeft);
            if (this.model.getGizmos().size() != 1 || this.model.getBalls().size() != 0) {
                fail();
            }
            
            if (this.model.getGizmos().iterator().next().getType().equals(GizmoType.CIRCLE)) {
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
            this.model.load(fileCheckAllDeleteCommandsResultInNoObjects);
            if (this.model.getGizmos().size() != 0 || this.model.getBalls().size() != 0) {
                fail();
            }
            else {
                assertTrue(true);
            }
        } catch (SyntaxError e) {
            fail();
        }
    }
    
    @Test
    public void checkValidMoveCommandWorksCorrectly() {
        try {
            this.model.load(fileCheckValidMoveCommandWorksCorrectly);
            if (this.model.getGizmos().size() != 6) {
                fail();
            }
            for (ReadGizmo gizmo: this.model.getGizmos()) {
                switch (gizmo.getType()) {
                    case TRIANGLE:
                        if (gizmo.getX() == 3 && gizmo.getY() == 4) {
                           
                        }
                        else {
                            fail();
                        }
                        break;
                    case SQUARE:
                        if (gizmo.getX() == 0 && gizmo.getY() == 0) {
                            
                        }
                        else {
                            fail();
                        }
                        break;
                    case CIRCLE:
                        if (gizmo.getX() == 6 && gizmo.getY() == 5) {
                            
                        }
                        else {
                            fail();
                        }
                        break;
                    case LEFT_FLIPPER:
                        if (gizmo.getX() == 7 && gizmo.getY() == 4) {
                    
                        }
                        else {
                            fail();
                        }
                        break;
                    case RIGHT_FLIPPER:
                        if (gizmo.getX() == 0 && gizmo.getY() == 6) {
                           
                        }
                        else {
                            fail();
                        }
                        break;
                    case ABSORBER:
                        if (gizmo.getX() == 0 && gizmo.getY() == 9 && gizmo.getWidth() == 20 && gizmo.getHeight() == 1) {
                          
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
                if (ball.getX() == 6.5 && ball.getY() == 0.0 && ball.getVelocityX() == 0 && ball.getVelocityY() == 0) {
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
    public void checkRotateCommandsOnNonAbsorberGizmoWorksCorrectly() {
        try {
            this.model.load(this.fileCheckRotateCommandsOnNonAbsorberGizmoWorksCorrectly);
            if (this.model.getGizmos().size() != 5) {
                fail();
            }
            for (ReadGizmo gizmo: this.model.getGizmos()) {
                switch (gizmo.getType()) {
                    case TRIANGLE:
                        if (gizmo.getRotation() == Rotation.SOUTH) {
                           
                        }
                        else {
                            fail();
                        }
                        break;
                    case SQUARE:
                        if (gizmo.getRotation() == Rotation.EAST) {
                            
                        }
                        else {
                            fail();
                        }
                        break;
                    case CIRCLE:
                        if (gizmo.getRotation() == Rotation.SOUTH) {
                            
                        }
                        else {
                            fail();
                        }
                        break;
                    case LEFT_FLIPPER:
                        if (gizmo.getRotation() == Rotation.WEST) {
                    
                        }
                        else {
                            fail();
                        }
                        break;
                    case RIGHT_FLIPPER:
                        if (gizmo.getRotation() == Rotation.EAST) {
                           
                        }
                        else {
                            fail();
                        }
                        break;
                    default:
                        fail();
                }
            }
            
            assertTrue(true);
        } catch (SyntaxError e) {
            fail();
        }
    }
    
    @Test 
    public void checkRotateCommandsOnAbsorberThrowsError() {
        try {
            model.load(fileCheckRotateCommandOnAsborberThrowsError);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void checkRotateCommandsOnBallThrowsError() {
        try {
            model.load(fileCheckRotateCommandOnBallThrowsError);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
   
    InputStream fileCheckAllGizmoToGizmoConnectionsLoaded = loadFile(TEST_FILE_FOLDER_PREFIX + "fileCheckAllGizmoToGizmoConnectionsLoaded");
    @Test
    public void loadValidFileCheckAllGizmoToGizmoConnectionsLoaded() {
       try {
            model.load(fileCheckAllGizmoToGizmoConnectionsLoaded);
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
    
    InputStream fileCheckAllKeyConnectionsLoaded = this.loadFile(TEST_FILE_FOLDER_PREFIX + "fileCheckAllKeyConnectionsLoaded");
    @Test
    public void loadValidFileCheckAllKeyConnectionsLoaded() {
        try {
            model.load(fileCheckAllKeyConnectionsLoaded);
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
            fail();
        }
    }
    
    InputStream fileCheckBallVelocityIsCorrect = this.loadFile(TEST_FILE_FOLDER_PREFIX + "fileCheckBallVelocityIsCorrect");
    @Test
    public void checkBallVelocityIsCorrect() {
        try {
            model.load(fileCheckBallVelocityIsCorrect);
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
    
    InputStream fileCheckOverlappingElementsThrowsError = this.loadFile(TEST_FILE_FOLDER_PREFIX + "fileCheckOverlappingElementsThrowsError");
    @Test
    public void checkOverlappingElementsThrowsError() {
        try {
            model.load(fileCheckOverlappingElementsThrowsError);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    InputStream fileCheckInvalidMoveThrowsError = this.loadFile(TEST_FILE_FOLDER_PREFIX + "fileCheckInvalidMoveThrowsError");
    @Test
    public void checkInvalidMoveThrowsException() {
        try {
            model.load(fileCheckInvalidMoveThrowsError);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    InputStream fileCheckInvalidAbsorberDimensionsThrowsError = this.loadFile(TEST_FILE_FOLDER_PREFIX + "fileCheckInvalidAbsorberDimensionsThrowsError");
    @Test
    public void checkInvalidAbsorberDimensionsThrowsException() {
        try {
            model.load(fileCheckInvalidAbsorberDimensionsThrowsError);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void loadValidFileCheckLastGravityAndFrictionAreUsed() {
        try {
            this.model.load(fileCheckLastGravityAndFrictionUsed);
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


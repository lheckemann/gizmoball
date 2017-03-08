package test.loadsave;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.*;

import gizmoball.model.Model;
import gizmoball.model.ReadBall;
import gizmoball.model.SyntaxError;
import gizmoball.model.gizmos.ReadGizmo;
import gizmoball.model.gizmos.ReadGizmo.GizmoType;

public class LoadTest {

    private Model model;
    private final String TEST_FILE_FOLDER_PREFIX = "test/loadsave/testFiles/";
    private FileInputStream fileIncorrectBallSyntax = loadFile(TEST_FILE_FOLDER_PREFIX + "fileIncorrectBallSyntax");
    private FileInputStream fileIncorrectFlipperSyntax = loadFile(TEST_FILE_FOLDER_PREFIX + "fileIncorrectFlipperSyntax");
    private FileInputStream fileIncorrectSquareSyntax = loadFile(TEST_FILE_FOLDER_PREFIX + "fileIncorrectSquareSyntax");
    private FileInputStream fileIncorrectTriangleSyntax = loadFile(TEST_FILE_FOLDER_PREFIX + "fileIncorrectTriangleSyntax");
    private FileInputStream fileIncorrectCircleSyntax = loadFile(TEST_FILE_FOLDER_PREFIX + "fileIncorrectCircleSyntax");
    private FileInputStream fileIncorrectAbsorberSyntax = loadFile(TEST_FILE_FOLDER_PREFIX + "fileIncorrectAbsorberSyntax");
    private FileInputStream fileIncorrectDeleteSyntax = loadFile(TEST_FILE_FOLDER_PREFIX + "fileIncorrectDeleteSyntax");
    private FileInputStream fileIncorrectMoveSyntax = loadFile(TEST_FILE_FOLDER_PREFIX + "fileIncorrectMoveSyntax");
    private FileInputStream fileIncorrectConnectSyntax = loadFile(TEST_FILE_FOLDER_PREFIX + "fileIncorrectConnectSyntax");
    private FileInputStream fileIncorrectKeyConnectSyntax = loadFile(TEST_FILE_FOLDER_PREFIX + "fileIncorrectKeyConnectSyntax");
    private FileInputStream fileIncorrectGravitySyntax = loadFile(TEST_FILE_FOLDER_PREFIX + "fileIncorrectGravitySyntax");
    private FileInputStream fileIncorrectFrictionSyntax = loadFile(TEST_FILE_FOLDER_PREFIX + "fileIncorrectFrictionSyntax");
    
    private FileInputStream fileValidGizmoDeclarations = loadFile(TEST_FILE_FOLDER_PREFIX + "fileValidGizmoDeclarations");
    private FileInputStream fileCheckLastGravityAndFrictionUsed = loadFile(TEST_FILE_FOLDER_PREFIX + "fileCheckLastGravityAndFrictionUsed");
    private FileInputStream fileDeleteCommandUndeclaredObject = loadFile(TEST_FILE_FOLDER_PREFIX + "fileDeleteCommandUndeclaredObject");
    private FileInputStream fileRotateCommandUndeclaredObject = loadFile(TEST_FILE_FOLDER_PREFIX + "fileRotateCommandUndeclaredObject");
    private FileInputStream fileMoveCommandUndeclaredObject = loadFile(TEST_FILE_FOLDER_PREFIX + "fileMoveCommandUndeclaredObject");
    private FileInputStream fileConnectCommandBothUndeclaredObject = loadFile(TEST_FILE_FOLDER_PREFIX + "fileConnectCommandBothUndeclaredObject");
    private FileInputStream fileConnectCommandOneUndeclaredObject = loadFile(TEST_FILE_FOLDER_PREFIX + "fileConnectCommandOneUndeclaredObject");
    private FileInputStream fileCheckAllDeleteCommandsPerformedOneObjectLeft = loadFile(TEST_FILE_FOLDER_PREFIX + "fileCheckAllDeleteCommandsPerformedOneObjectLeft");
    private FileInputStream fileCheckAllDeleteCommandsResultInNoObjects = loadFile(TEST_FILE_FOLDER_PREFIX + "fileCheckAllDeleteCommandsResultInNoObjects");
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
    
    /*@Test
    public void loadExistingFileWithIncorrectBallDeclarationSyntax() {
        try {
            this.model.load(fileIncorrectBallSyntax);
            assertTrue(false);
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void loadFileWithIncorrectFlipperDeclarationSyntax() {
        try {
            this.model.load(fileIncorrectFlipperSyntax);
            assertTrue(false);
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void loadFileWithIncorrectSquareDeclarationSyntax() {
        try {
            this.model.load(fileIncorrectSquareSyntax);
            assertTrue(false);
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void loadFileWithIncorrectTriangleDeclarationSyntax() {
        try {
            this.model.load(fileIncorrectTriangleSyntax);
            assertTrue(false);
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void loadFileWithIncorrectCircleDeclarationSyntax() {
        try {
            this.model.load(fileIncorrectCircleSyntax);
            assertTrue(false);
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void loadFileWithIncorrectAbsorberDeclarationSyntax() {
        try {
            this.model.load(fileIncorrectAbsorberSyntax);
            assertTrue(false);
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void loadValidFileCheckAllDeclarationCommandsArePerformed() {
        try {
            this.model.load(fileValidGizmoDeclarations);
            if (this.model.getGizmos().size() != 6) {
                assertFalse(true);
            }
            for (ReadGizmo gizmo: this.model.getGizmos()) {
                switch (gizmo.getType()) {
                    case TRIANGLE:
                        if (gizmo.getX() == 3 && gizmo.getY() == 3 && gizmo.getWidth() == 1 && gizmo.getHeight() == 1) {
                           
                        }
                        else {
                            assertTrue(false);
                        }
                        break;
                    case SQUARE:
                        if (gizmo.getX() == 1 && gizmo.getY() == 1 && gizmo.getWidth() == 1 && gizmo.getHeight() == 1) {
                            
                        }
                        else {
                            assertTrue(false);
                        }
                        break;
                    case CIRCLE:
                        if (gizmo.getX() == 2 && gizmo.getY() == 2) {
                            
                        }
                        else {
                            assertTrue(false);
                        }
                        break;
                    case LEFT_FLIPPER:
                        if (gizmo.getX() == 4 && gizmo.getY() == 4 && gizmo.getWidth() == 2 && gizmo.getHeight() == 2) {
                    
                        }
                        else {
                            assertTrue(false);
                        }
                        break;
                    case RIGHT_FLIPPER:
                        if (gizmo.getX() == 6 && gizmo.getY() == 6 && gizmo.getWidth() == 2 && gizmo.getHeight() == 2) {
                           
                        }
                        else {
                            assertTrue(false);
                        }
                        break;
                    case ABSORBER:
                        if (gizmo.getX() == 0 && gizmo.getY() == 19 && gizmo.getWidth() == 20 && gizmo.getHeight() == 1) {
                          
                        }
                        else {
                            assertTrue(false);
                        }
                        break;
                    default:
                        assertTrue(false);
                }
            }
          
            if (this.model.getBalls().size() != 1) {
                assertTrue(false);
            }
            
            for (ReadBall ball: this.model.getBalls()) {
                if (ball.getX() == 0.0 && ball.getY() == 0.0 && ball.getVelocityX() == 0 && ball.getVelocityY() == 0) {
                }
                else {
                    assertTrue(false);
                }
            }
            
            assertTrue(true);
        } catch (SyntaxError e) {
            assertTrue(false);
        }
    }
    
    @Test
    public void loadFileWithIncorrectDeleteSyntax() {
        try {
            this.model.load(fileIncorrectDeleteSyntax);
            assertTrue(false);
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void loadFileWithIncorrectMoveSyntax() {
        try {
            this.model.load(fileIncorrectMoveSyntax);
            assertTrue(false);
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void loadFileWithIncorrectConnectSyntax() {
        try {
            this.model.load(fileIncorrectConnectSyntax);
            assertTrue(false);
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void loadFileWithIncorrectKeyConnectSyntax() {
        try {
            this.model.load(fileIncorrectKeyConnectSyntax);
            assertTrue(false);
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void loadFileWithIncorrectGravitySyntax() {
        try {
            this.model.load(fileIncorrectGravitySyntax);
            assertTrue(false);
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void loadFileWithIncorrectFrictionSyntax() {
        try {
            this.model.load(fileIncorrectFrictionSyntax);
            assertTrue(false);
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void checkDeleteCommandWithUndeclaredObjectThrowsError() {
        try {
            this.model.load(fileDeleteCommandUndeclaredObject);
            assertTrue(false);
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void checkRotateCommandWithUndeclaredObjectThrowsError() {
        try {
            this.model.load(fileRotateCommandUndeclaredObject);
            assertTrue(false);
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void checkMoveCommandWithUndeclaredObjectThrowsError() {
        try {
            this.model.load(fileMoveCommandUndeclaredObject);
            assertTrue(false);
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    //Test a connect command where both objects don't exist
    @Test
    public void checkConnectCommandWithBothUndeclaredObjectsThrowsError() {
        try {
            this.model.load(fileConnectCommandBothUndeclaredObject);
            assertTrue(false);
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    //Test a connect command where one object exists and the other one doesn't exist
    @Test
    public void checkConnectCommandWithOneUndeclaredObjectThrowsError() {
        try {
            this.model.load(fileConnectCommandOneUndeclaredObject);
            assertTrue(false);
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
                assertTrue(false);
            }
            
            if (this.model.getGizmos().iterator().next().getType().equals(GizmoType.CIRCLE)) {
                assertTrue(true);
            } else {
                assertTrue(false);
            }
        } catch (SyntaxError e) {
            assertTrue(false);
        }
    }
    
    @Test
    public void checkLoadWhereAllDeleteCommandsResultInNoObjects() {
        try {
            this.model.load(fileCheckAllDeleteCommandsResultInNoObjects);
            if (this.model.getGizmos().size() != 0 || this.model.getBalls().size() != 0) {
                assertTrue(false);
            }
            else {
                assertTrue(true);
            }
        } catch (SyntaxError e) {
            assertTrue(false);
        }
    }
    
    @Test
    public void checkValidMoveCommandWorksCorrectly() {
        try {
            this.model.load(fileCheckValidMoveCommandWorksCorrectly);
        } catch (SyntaxError e) {
            assertTrue(false);
        }
    }
    
    @Test
    public void checkRotateCommandsOnNonAbsorberGizmoWorksCorrectly() {
        
    }
    
    @Test 
    public void checkRotateCommandsOnAbsorberOrBallThrowsError() {
        
    }
   
    @Test
    public void loadValidFileCheckAllConnectionsLoaded() {
        
    }
    
    
    @Test
    public void loadValidFileCheckAllKeyConnectionsLoaded() {
        
    }
    
    @Test
    public void checkBallVelocityIsCorrect() {
        
    }
    
    @Test
    public void checkOverlappingElementsThrowsError() {
        
    }
    
    @Test
    public void checkInvalidMoveThrowsException() {
        
    }
    
    @Test
    public void checkInvalidAbsorberDimensionsThrowsException() {
        
    }
    
    @Test
    public void loadValidFileCheckLastGravityAndFrictionAreUsed() {
        try {
            this.model.load(fileCheckLastGravityAndFrictionUsed);
            if (this.model.getGravity() == 20 && this.model.getFrictionMu() == 100 && this.model.getFrictionMu2() == -90) {
                assertTrue(true);
            }
            else {
                assertTrue(false);
            }
        } catch (SyntaxError e) {
            assertTrue(false);
        }
    }*/

}


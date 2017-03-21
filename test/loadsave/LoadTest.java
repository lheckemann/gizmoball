package loadsave;

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
    public void addGizmoOutOfBounds() {
        try {
            String testString = "Absorber A0 25 25 3 3";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void addBallOutOfBounds() {
        try {
            String testString = "Ball B0 -5 -5 3 7";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void addGizmoOverGizmo() {
        try {
            String testString = "Absorber A0 5 5 8 8\n"
                               + "Triangle T0 6 6" ;
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void addBallOverAbsorber() {
        try {
            String testString = "Absorber A0 5 5 8 8\n"
                               + "Ball B0 6 6 -4 -6";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
        } catch (SyntaxError e) {
            fail();
        }
    }
    
    @Test
    public void addBallOverNonAbsorberGizmo() {
        try {
            String testString = "Triangle T0 6 6\n"
                               + "Ball B0 6 6 -4 -6";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void addGizmoOverBall() {
        try {
            String testString = "Ball B0 6 6 -4 -6\n" 
                               + "Triangle T0 6 6";
                             
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void ballDeclarationIncorrentSyntax() {
        try {
            String testString = "Ball B0 0.1 0 3 fhdjfhdjfhdf\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void ballDeclarationTooLong() {
        try {
            String testString = "Ball B0 0.1 0 3 0 8\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    
    @Test
    public void ballDeclarationTooShort() {
        try {
            String testString = "Ball\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void ballDeclarationCorrect() {
        try {
            String testString = "Ball B0 3 3 2 50\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            if (this.model.getBalls().size() == 1) {
                ReadBall b = this.model.getBalls().iterator().next();
                if (b.getX() == 3 && b.getY() == 3 && b.getVelocityX() == 2 && b.getVelocityY() == 50) { 

                }
                else {
                    fail();
                }
            } else {
                fail();
            }
        } catch (SyntaxError e) {
            System.out.println(e.getMessage());
            fail();
        }
    }
    
    @Test 
    public void leftFlipperDeclarationWrongSyntax() {
        try {
            String testString = "LeftFlipper LF0 3 jfkdjfkdjf\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void leftFlipperDeclarationTooLong() {
        try {
            String testString = "LeftFlipper LF0 3 3 7\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void leftFlipperDeclarationTooShort() {
        try {
            String testString = "LeftFlipper LF0\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void leftFlipperDeclarationCorrect() {
        try {
            String testString = "LeftFlipper C0 3 3\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            if (this.model.getGizmos().size() == 1) {
                ReadGizmo g = this.model.getGizmos().iterator().next();
                if (g.getType().equals(GizmoType.LEFT_FLIPPER) && g.getX() == 3 && g.getY() == 3) { 
                }
                else {
                    fail();
                }
            } else {
                fail();
            }
        } catch (SyntaxError e) {
            fail();
        }
    }
    
    @Test 
    public void rightFlipperDeclarationSyntaxWrong() {
        try {
            String testString = "RightFlipper RF0 3 jfkdjfkdjf\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void rightFlipperDeclarationTooLong() {
        try {
            String testString = "RightFlipper RF0 3 3 7\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void rightFlipperDeclarationTooShort() {
        try {
            String testString = "RightFlipper RF0\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void rightFlipperDeclarationCorrect() {
        try {
            String testString = "RightFlipper RF0 3 3\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            if (this.model.getGizmos().size() == 1) {
                ReadGizmo g = this.model.getGizmos().iterator().next();
                if (g.getType().equals(GizmoType.RIGHT_FLIPPER) && g.getX() == 3 && g.getY() == 3) { 
                }
                else {
                    fail();
                }
            } else {
                fail();
            }
        } catch (SyntaxError e) {
            fail();
        }
    }
    
    @Test 
    public void squareDeclarationSyntaxWrong() {
        try {
            String testString = "Square S0 3 jfkdjfkdjf\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void squareDeclarationTooLong() {
        try {
            String testString = "Square S0 3 3 7\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void squareDeclarationTooShort() {
        try {
            String testString = "Square S0\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void squareDeclarationCorrect() {
        try {
            String testString = "Square S0 3 3\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            if (this.model.getGizmos().size() == 1) {
                ReadGizmo g = this.model.getGizmos().iterator().next();
                if (g.getType().equals(GizmoType.SQUARE) && g.getX() == 3 && g.getY() == 3) { 
                }
                else {
                    fail();
                }
            } else {
                fail();
            }
        } catch (SyntaxError e) {
            fail();
        }
    }
    
    @Test 
    public void triangleDeclarationSyntaxWrong() {
        try {
            String testString = "Triangle T0 3 jfkdjfkdjf\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void triangleDeclarationTooLong() {
        try {
            String testString = "Triangle T0 3 3 7\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void triangleDeclarationTooShort() {
        try {
            String testString = "Triangle T0\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void triangleDeclarationCorrect() {
        try {
            String testString = "Triangle T0 3 3\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            if (this.model.getGizmos().size() == 1) {
                ReadGizmo g = this.model.getGizmos().iterator().next();
                if (g.getType().equals(GizmoType.TRIANGLE) && g.getX() == 3 && g.getY() == 3) { 
                }
                else {
                    fail();
                }
            } else {
                fail();
            }
        } catch (SyntaxError e) {
            fail();
        }
    }
    
    @Test 
    public void circleDeclarationSyntaxWrong() {
        try {
            String testString = "Circle C0 3 jfkdjfkdjf\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void circleDeclarationTooLong() {
        try {
            String testString = "Circle C0 3 3 7\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void circleDeclarationTooShort() {
        try {
            String testString = "Circle C0\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    

    @Test 
    public void circleDeclarationCorrect() {
        try {
            String testString = "Circle C0 3 3\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            if (this.model.getGizmos().size() == 1) {
                ReadGizmo g = this.model.getGizmos().iterator().next();
                if (g.getType().equals(GizmoType.CIRCLE) && g.getX() == 3 && g.getY() == 3) { 
                }
                else {
                    fail();
                }
            } else {
                fail();
            }
        } catch (SyntaxError e) {
            fail();
        }
    }
    
    @Test 
    public void absorberDeclarationSyntaxWrong() {
        try {
            String testString = "Absorber A0 3 jfkdjfkdjf 4 4\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void absorberDeclarationTooLong() {
        try {
            String testString = "Absorber A0 0 19 20 20 16\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void absorberDeclarationTooShort() {
        try {
            String testString = "Absorber A0\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }

    @Test 
    public void absorberDeclarationCorrect() {
        try {
            String testString = "Absorber A0 0 19 20 20\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            if (this.model.getGizmos().size() == 1) {
                ReadGizmo g = this.model.getGizmos().iterator().next();
                if (g.getType().equals(GizmoType.ABSORBER) && g.getX() == 0 && g.getY() == 19 && g.getWidth() == 20 && g.getHeight() == 1) { 
                }
                else {
                    fail();
                }
            } else {
                fail();
            }
        } catch (SyntaxError e) {
            fail();
        }
    }
    
    @Test
    public void deleteTooLong() {
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
    public void deleteTooShort() {
        try {
            String testString = "Square S0 0 0\n"
                    + "Delete";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void deleteCorrectWithReferencedGizmo() {
        try {
            String testString = "Square S0 0 0\n"
                    + "Delete S0";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            if (this.model.getGizmos().size() != 0) {
                fail();  
            } 
        } catch (SyntaxError e) {
            fail();
        }
    }
    
    @Test
    public void deleteCorrectWithReferencedBall() {
        try {
            String testString = "Ball B0 4 4 3 -7\n"
                    + "Delete B0";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            if (this.model.getBalls().size() != 0) {
                fail();  
            } 
        } catch (SyntaxError e) {
            fail();
        }
    }
    
    @Test
    public void deleteCorrectWithUnreferencedObject() {
        try {
            String testString = "Square S0 0 0\n"
                    + "Delete fdifjdf";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
          
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void moveSyntaxTooLong() {
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
    public void moveSyntaxTooShort() {
        try {
            String testString = "Square S0 0 0\n"
                    + "Move";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void moveSyntaxWrong() {
        try {
            String testString = "Square S0 0 0\n"
                    + "Move S0 fdkfjdf jfkdjf";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void moveCorrectReferencedGizmo() {
        try {
            String testString = "Square S0 0 0\n"
                    + "Move S0 5 5";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            if (this.model.getGizmos().size() == 1) {
                ReadGizmo g = this.model.getGizmos().iterator().next();
                if (g.getType().equals(GizmoType.SQUARE) && g.getX() == 5 && g.getY() == 5) {
                    
                } else {
                    fail();
                }
            } else {
                fail();
            }
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void moveCorrectBallOverNonAbsorberGizmo() {
        try {
            String testString = "Ball B0 3 3 4 4\n"
                    + "Triangle T0 5 5\n"
                    + "Move B0 5 5";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void moveCorrectBallOverAbsorberGizmo() {
        try {
            String testString = "Ball B0 3 3 4 4\n"
                    + "Absorber A0 5 5\n"
                    + "Move B0 5 5";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void moveCorrectGizmoOverGizmo() {
        try {
            String testString = "Square S0 3 3\n"
                    + "Triangle T0 5 5\n"
                    + "Move S0 5 5";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void moveCorrectBallOutOfBounds() {
        try {
            String testString = "Ball B0 3 3 4 4\n"
                    + "Move B0 -5 -5";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void moveCorrecGizmoOutOfBounds() {
        try {
            String testString = "Triangle T0 4 4\n"
                    + "Move T0 28 28";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void moveCorrectReferencedBall() {
        try {
            String testString = "Ball B0 3 3 4 4\n"
                    + "Move B0 5 5";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            if (this.model.getBalls().size() == 1) {
                ReadBall b = this.model.getBalls().iterator().next();
                if (b.getX() != 5 || b.getY() != 5) {
                    fail();
                }
            } else {
                fail();
            }
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void moveCorrectUnreferencedObject() {
        try {
            String testString = "Square S0 0 0\n"
                    + "Move fjdkfjd 5 5";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
          
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void rotateTooLong() {
        try {
            String testString = "Square S0 0 0\n"
                    + "Rotate S0 fjdffjk";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void rotateTooShort() {
        try {
            String testString = "Square S0 0 0\n"
                    + "Rotate";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    
    @Test
    public void rotateOnceWithReferencedObject() {
        try {
            String testString = "Triangle S0 0 0\n"
                    + "Rotate S0";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            if (this.model.getGizmos().size() == 1) {
                ReadGizmo g = this.model.getGizmos().iterator().next();
                if (g.getType().equals(GizmoType.TRIANGLE) && g.getRotation().equals(Rotation.EAST)) {
                    
                } else {
                    fail();
                }
            } else {
                fail();
            }
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void rotateTwiceReferencedObject() {
        try {
            String testString = "Triangle S0 0 0\n"
                    + "Rotate S0\n" 
                    + "Rotate S0";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            if (this.model.getGizmos().size() == 1) {
                ReadGizmo g = this.model.getGizmos().iterator().next();
                if (g.getType().equals(GizmoType.TRIANGLE) && g.getRotation().equals(Rotation.SOUTH)) {
                    
                } else {
                    fail();
                }
            } else {
                fail();
            }
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void rotateThriceReferencedObject() {
        try {
            String testString = "Triangle S0 0 0\n"
                    + "Rotate S0\n"
                    + "Rotate S0\n" 
                    + "Rotate S0";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            if (this.model.getGizmos().size() == 1) {
                ReadGizmo g = this.model.getGizmos().iterator().next();
                if (g.getType().equals(GizmoType.TRIANGLE) && g.getRotation().equals(Rotation.WEST)) {
                    
                } else {
                    fail();
                }
            } else {
                fail();
            }
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void rotateCorrectUnreferencedObject() {
        try {
            String testString = "Square S0 0 0\n"
                    + "Rotate fjdkfjd";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
          
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    //This is a special case, since absorbers cannot be rotated
    @Test 
    public void rotateCorrectAbsorberThrowsError() {
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
    
    //This is a special case, since balls cannot be rotated
    @Test
    public void rotateCorrectBallThrowsError() {
        try {
            String testString = "Ball B0 0 19 20 20\n"
                    + "Rotate B0";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
           
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    //This is a special case, since outerwalls cannot be rotated
    @Test
    public void rotateCorrectOuterWallsThrowsError() {
        try {
            String testString = "Rotate OuterWalls";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
           
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void gravitySyntaxTooShort() {
        try {
            String testString = "Gravity";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
           
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void gravitySyntaxTooLong() {
        try {
            String testString = "Gravity 25 fjdjfdf";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
           
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void gravitySyntaxWrong() {
        try {
            String testString = "Gravity fhdff";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
           
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void gravitySyntaxCorrectSetsGravity() {
        try {
            String testString = "Gravity 25";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
           
            if (this.model.getGravity() != 25) {
                fail();
            }
        } catch (SyntaxError e) {
            fail();
        }
    }
    
    @Test
    public void gravitySyntaxCorrectLastGravityUsed() {
        try {
            String testString = "Gravity 25\n" + 
                                "Gravity -97";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
           
            if (this.model.getGravity() != -97) {
                fail();
            }
        } catch (SyntaxError e) {
            fail();
        }
    }
    
    @Test
    public void frictionSyntaxTooShort() {
        try {
            String testString = "Friction";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
           
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void frictionSyntaxTooLong() {
        try {
            String testString = "Friction 1 3 fjdjfdf";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
           
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void frictionSyntaxWrong() {
        try {
            String testString = "Friction fhdff 5";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
           
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void frictionSyntaxCorrectSetsFriction() {
        try {
            String testString = "Friction 3 7";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
           
            if (this.model.getFrictionMu() != 3 || this.model.getFrictionMu2() != 7) {
                fail();
            }
        } catch (SyntaxError e) {
            fail();
        }
    }
    
    @Test
    public void frictionSyntaxCorrectLastFrictionUsed() {
        try {
            String testString = "Friction 24 4\n" + 
                                "Friction 5 -7";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
           
            if (this.model.getFrictionMu() != 5 || this.model.getFrictionMu2() != -7) {
                fail();
            }
        } catch (SyntaxError e) {
            fail();
        }
    }
    
    @Test 
    public void connectCommandTooLongSyntax() {
        try {
            String testString = "Connect S0 T0 jfkfjkdjf";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void connectCommandTooShortSyntax() {
        try {
            String testString = "Connect";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void connectCommandCorrectSyntaxBothUndeclaredDeclarations() {
        try {
            String testString = "Square S0 4 4\n" 
                              + "Connect fdjfhdj jskdjskd";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void connectCommandCorrectSyntaxFirstGizmoUndeclared() {
        try {
            String testString = "Square S0 4 4\n" 
                              + "Connect fjkdff S0";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void connectCommandCorrectSyntaxSecondGizmoUndeclared() {
        try {
            String testString = "Square S0 4 4\n" 
                              + "Connect S0 jskdjskd";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void connectCommandCorrectSyntaxOuterwallToValidGizmo() {
        try {
            String testString = "Square S0 4 4\n" 
                              + "Connect OuterWalls S0";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
        } catch (SyntaxError e) {
            fail();
        }
    }
    
    @Test 
    public void connectCommandCorrectSyntaxValidGizmoToValidGizmo() {
        try {
            String testString = "Square S0 4 4\n"
                              + "LeftFlipper LF0 2 2\n"
                              + "Connect S0 LF0";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
        } catch (SyntaxError e) {
            fail();
        }
    }
   
    @Test 
    public void keyConnectCommandTooLongSyntax() {
        try {
            String testString = "KeyConnect key 32 down A0 fkdfjkfj";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void keyConnectCommandTooShortSyntax() {
        try {
            String testString = "KeyConnect";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void keyConnectCommandUndeclaredGizmoSyntax() {
        try {
            String testString = "KeyConnect key 32 down fkdjfkdf";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void keyConnectCommandUpCorrectSyntax() {
        try {
            String testString = "LeftFlipper LF0 3 3\n"
                              + "KeyConnect key 32 up LF0";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
        } catch (SyntaxError e) {
            fail();
        }
    }
    
    @Test
    public void keyConnectCommandDownCorrectSyntax() {
        try {
            String testString = "LeftFlipper LF0 3 3\n"
                              + "KeyConnect key 32 down LF0";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
        } catch (SyntaxError e) {
            fail();
        }
    }
    
    @Test
    public void keyConnectCommandNotUpOrDown() {
        try {
            String testString = "LeftFlipper LF0 3 3\n"
                              + "KeyConnect key 32 Left LF0";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void keyConnectCommandSecondTokenNotKey() {
        try {
            String testString = "LeftFlipper LF0 3 3\n"
                              + "KeyConnect apple 32 down LF0";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    
    @Test 
    public void leftSpinningFlipperDeclarationSyntaxWrong() {
        try {
            String testString = "LeftSpinningFlipper S0 3 jfkdjfkdjf\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void leftSpinningFlipperDeclarationTooLong() {
        try {
            String testString = "LeftSpinningFlipper C0 3 3 7\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void leftSpinningFlipperDeclarationTooShort() {
        try {
            String testString = "LeftSpinningFlipper C0\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    

    @Test 
    public void leftSpinningFlipperDeclarationCorrect() {
        try {
            String testString = "LeftSpinningFlipper C0 3 3\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            if (this.model.getGizmos().size() == 1) {
                ReadGizmo g = this.model.getGizmos().iterator().next();
                if (g.getType().equals(GizmoType.LEFT_SPINNING_FLIPPER) && g.getX() == 3 && g.getY() == 3) { 
                }
                else {
                    fail();
                }
            } else {
                fail();
            }
        } catch (SyntaxError e) {
            fail();
        }
    }
    
    @Test 
    public void rightSpinningFlipperDeclarationSyntaxWrong() {
        try {
            String testString = "RightSpinningFlipper C0 3 jfkdjfkdjf\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void rightSpinningFlipperDeclarationTooLong() {
        try {
            String testString = "RightSpinningFlipper C0 3 3 7\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void rightSpinningFlipperDeclarationTooShort() {
        try {
            String testString = "RightSpinningFlipper C0\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    

    @Test 
    public void rightSpinningFlipperDeclarationCorrect() {
        try {
            String testString = "RightSpinningFlipper C0 3 3\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            if (this.model.getGizmos().size() == 1) {
                ReadGizmo g = this.model.getGizmos().iterator().next();
                if (g.getType().equals(GizmoType.RIGHT_SPINNING_FLIPPER) && g.getX() == 3 && g.getY() == 3) { 
                }
                else {
                    fail();
                }
            } else {
                fail();
            }
        } catch (SyntaxError e) {
            fail();
        }
    }
    
    @Test 
    public void spawnerDeclarationSyntaxWrong() {
        try {
            String testString = "Spawner C0 3 jfkdjfkdjf\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void spawnerDeclarationTooLong() {
        try {
            String testString = "Spawner C0 3 3 7\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void spawnerDeclarationTooShort() {
        try {
            String testString = "Spawner C0\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    

    @Test 
    public void spawnerDeclarationCorrect() {
        try {
            String testString = "Spawner C0 3 3\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            if (this.model.getGizmos().size() == 1) {
                ReadGizmo g = this.model.getGizmos().iterator().next();
                if (g.getType().equals(GizmoType.SPAWNER) && g.getX() == 3 && g.getY() == 3) { 
                }
                else {
                    fail();
                }
            } else {
                fail();
            }
        } catch (SyntaxError e) {
            fail();
        }
    }
    
    @Test 
    public void sinkDeclarationSyntaxWrong() {
        try {
            String testString = "Sink C0 3 jfkdjfkdjf\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void sinkDeclarationTooLong() {
        try {
            String testString = "Sink C0 3 3 7\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    
    @Test 
    public void sinkDeclarationTooShort() {
        try {
            String testString = "Sink C0\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
    

    @Test 
    public void sinkDeclarationCorrect() {
        try {
            String testString = "Sink C0 3 3\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            if (this.model.getGizmos().size() == 1) {
                ReadGizmo g = this.model.getGizmos().iterator().next();
                if (g.getType().equals(GizmoType.SINK) && g.getX() == 3 && g.getY() == 3) { 
                }
                else {
                    fail();
                }
            } else {
                fail();
            }
        } catch (SyntaxError e) {
            fail();
        }
    }
    
    @Test
    public void emptyFile() {
        try {
            String testString = "\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            if (! (this.model.getBalls().size() == 0 && this.model.getGizmos().size() == 0)) {
                fail();
            }
        } catch (SyntaxError e) {
            fail();
        }
    }
    
    @Test
    public void garbageFile() {
        try {
            String testString = "hfjahdjfhasdf\n"
                    + "jfjsdhfjhsdfsdf\n";
            InputStream testStream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
            this.model.load(new StandardLoader(), testStream);
            fail();
        } catch (SyntaxError e) {
            assertTrue(true);
        }
    }
}


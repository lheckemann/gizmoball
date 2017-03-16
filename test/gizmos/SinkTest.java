package gizmos;

import gizmoball.model.Ball;
import gizmoball.model.gizmos.Gizmo;
import gizmoball.model.gizmos.GizmoType;
import gizmoball.model.gizmos.Sink;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SinkTest {
    private Gizmo mySink;

    @Before
    public void setUp() {
        mySink = new Sink();
    }

    @Test
    public void getType() {
        Assert.assertEquals(mySink.getType(), GizmoType.SINK);
    }

    @Test
    public void ballHit() {
        Ball newBall = new Ball();
        assertNull(mySink.ballHit(newBall));
    }
}
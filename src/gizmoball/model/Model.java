package gizmoball.model;

import java.util.*;

public class Model implements RunModel, BuildModel {
    public static final int TICKS_PER_SECOND = 30;
    public static final int L_TO_PIXELS = 32;
    private Set<Gizmo> gizmos = new HashSet<>();
    private Map<Integer, Set<Gizmo>> keyPressMap = new HashMap<>();
    private Map<Integer, Set<Gizmo>> keyReleaseMap = new HashMap<>();
    private Gizmo selection = null;

    // This is not nice, but we want the Observable methods exposed on the interface...
    private class ModelObservable extends Observable {
        public void ticked() {
            setChanged();
        }
    }
    private ModelObservable observable = new ModelObservable();

    @Override
    public void addObserver(Observer target) {
        observable.addObserver(target);
    }

    @Override
    public void removeObserver(Observer target) {
        observable.deleteObserver(target);
    }

    @Override
    public void tick() {
        gizmos.stream().forEach(Gizmo::tick);
        observable.ticked();
    }

    @Override
    public void keyPressed(int code) {
        keyPressMap.get(code).stream().forEach(Gizmo::trigger);
    }

    @Override
    public void keyReleased(int code) {
        keyReleaseMap.get(code).stream().forEach(Gizmo::trigger);
    }

    public Model() {
    }

    public static Model prototype1Example() {
        Model model = new Model();
        int x = 1;
        for (Rotation rot : Rotation.values()) {
            Flipper flipper = new Flipper(true);
            flipper.setRotation(rot);
            flipper = new Flipper(false);
            flipper.setRotation(rot);
            flipper.setPosition(x, 2);
            model.gizmos.add(flipper);
            x += 3;
        }
        return model;
    }

    @Override
    public void connectKeyPress(int code) {
        if (selection == null)
            return;

        Set<Gizmo> connectedSet = keyPressMap.get(code);
        if (connectedSet == null) {
            connectedSet = new HashSet<>();
            keyPressMap.put(code, connectedSet);
        }
        connectedSet.add(selection);
    }

    @Override
    public void connectKeyRelease(int code) {
        if (selection == null)
            return;

        Set<Gizmo> connectedSet = keyReleaseMap.get(code);
        if (connectedSet == null) {
            connectedSet = new HashSet<>();
            keyReleaseMap.put(code, connectedSet);
        }
        connectedSet.add(selection);
    }

    @Override
    public void select(double x, double y) {
        selection = gizmos.stream()
                .filter(gizmo ->
                           gizmo.getX() <= x
                        && gizmo.getY() <= y
                        && gizmo.getX() + gizmo.getWidth() > x
                        && gizmo.getY() + gizmo.getHeight() > y)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Set<ReadGizmo> getGizmos() {
        return Collections.unmodifiableSet(gizmos);
    }
}

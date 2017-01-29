package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Model {
    private Map<Integer, Set<Gizmo>> keyMap;
    public void keyPressed(int key) {
        keyMap.get(key).forEach(Gizmo::keyPressed);
    }
    public void keyReleased(int key) {
        keyMap.get(key).forEach(Gizmo::keyReleased);
    }
    public Model() {
        keyMap = new HashMap<>();
    }
}

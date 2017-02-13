package gizmoball.view;

import javax.swing.*;
import java.awt.event.ActionListener;

import gizmoball.model.BuildModel;
import gizmoball.view.BuildBoardView;

public class BuildView {
    private final Box impl;
    private final BuildModel model;

    public BuildView(BuildModel model) {
        this.model = model;
        impl = new Box(BoxLayout.Y_AXIS);
        BuildBoardView board = new BuildBoardView(model);
        impl.add(board);
    }

    public JComponent getComponent() {
        return impl;
    }
}

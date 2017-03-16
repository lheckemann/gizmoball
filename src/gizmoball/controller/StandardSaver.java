package gizmoball.controller;

import java.util.stream.Collectors;

import gizmoball.model.BuildModel;

public class StandardSaver extends Saver {
    public StandardSaver(BuildModel model) {
        super(model);
        this.taggedGizmos = this.getTaggedGizmos(model.getGizmos()
                .stream()
                .filter(g -> g.getType().isSpecified())
                .collect(Collectors.toList()));
    }
}

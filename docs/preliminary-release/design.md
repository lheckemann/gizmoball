% Design
% Group JS8

We stuck with most of the design decisions we made for the preliminary design.

The model kept its `ReadModel` interface, which allows read-only access to
`ReadGizmo` and `ReadBall`, which allow read-only access to gizmos and balls,
respectively. It also kept both the `RunModel` and the `BuildModel` interfaces,
which extend the `ReadModel` interface.

While `RunModel` remained mostly unaltered, `BuildModel` underwent some
changes. The model has no longer any notion of identifiers. Instead, identifiers
are dynamically generated on saves and translated into gizmo instances on load.
The saving and the loading was made external to the model, and now only
interacts with it through its `BuildModel` API.

For collision detection purposes, gizmos expose their form through
`getLineSegments` and `getCircles`. This forms do not account for any changes in
the position, rotation, or pivoting of the gizmo. Instead, this transformations
are carried out by applying an affine transformation matrix (obtained with
`getTransform`) to these line segments and circles. The affine transformation
matrices are also used for drawing.

Gizmos do not define their type by their class, but rather by their `GizmoType`
-- an enum of all the possible gizmo types. This allows a single `Gizmo`
subclass to be of several types, and perform type pattern matching instead of
relying on `instanceof`.

The design of views and controllers has not seen many changes either. The main
`GizmoBallView` alternates between `RunView` and `BuildView`. Both of these
comprise the board and a control panel. They both delegate the rendering of the
board to a subview: `RunBoardView` and `BuildBoardView`, respectively. Both of
these extend `BoardView`, which is the view responsible for rendering the
gizmos. The "run" views hold a reference to a `RunModel` model, while the
"build" views hold a reference to a `BuildModel` model. These views handle the
`BoardView` a `ReadModel` model.

![Class diagram](design.png)

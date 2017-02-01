# Design

The `GizmoBallView` is the top level view and the entry point of the program. It
maintains a reference to the model instance, which is kept throughout the entire
life of the program. The `SwitchModeListener` listens for the "Build/Run" button
being pressed and sets the `GizmoBallView` in the correct state.

The `GizmoBallView` holds two sub-views representing the building and running
modes. The `BuildView` is given a reference to the model through a `BuildModel`
interface, which restricts the operations that can be executed on it to the ones
used to build a game and to draw it. In the case of the `RunView`, it is given a
reference to the model through a `RunModel` interface.

Both mode views have sub-views responsible of drawing the board on screen. These
sub-views are observers that subscribe to the model through its `ReadModel`
interface and are notified when the board needs to be redrawn.

The model through its `BuildModel` interface modifies the board by selecting a
location using `select()` and then running operations on that location.

The `ReadModel` interface offers read-only access to the board and its
constituent gizmos. This interface is used to draw the board in both building
and running modes.

The `RunModel` interface offers a `tick()` method -- used by the `TickListener`
to make time advance -- and `keyPressed()` and `keyReleased()` methods used by
the `KeyTriggerListener`

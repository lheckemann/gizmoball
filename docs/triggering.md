# Triggering

The model keeps track of gizmo triggering connections through several `Map`
instances and a `Set`:

 - Key press map, `Map<Integer, Set<Gizmo>>`
 - Key release map, `Map<Integer, Set<Gizmo>>`
 - Gizmo triggering map, `Map<Gizmo, Set<Gizmo>>`
 - Wall-triggered gizmo set, `Set<Gizmo>` — because there is only one instance
   of the outer walls.

The `Gizmo` abstract class provides a `trigger()` method which causes the gizmo
to perform its triggering action. This method is called whenever an event that
is connected to the gizmo takes place:

 - If a key is pressed or released, the `KeyTriggerListener` passes the event on
   to the Model, which in turn looks up the gizmos connected to the key event in
   the appropriate map and triggers them.
 - If the ball collides with a gizmo during a tick, the model will look up the
   gizmo's trigger set and trigger each element.
 - If the ball collides with the outer walls during a tick, the model will
   trigger each element of the wall-triggered set.


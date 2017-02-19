# Physics loop
The physics loop will be running in Swing. A controller class (`TickListener`)
will be registered to receive `ActionEvent`; these will be emitted either by the
Tick button in the user interface (for single-stepping simulation) or by a Swing
`Timer` that emits the events at a regular interval (once per tick interval).

The `TickListener`, upon receiving a tick, will advance the physics simulation
by calling `tick()` on the model. This is where all the actual physics
simulation happens.

 - All gizmos have their `tick()` method called, allowing them to perform their
   specific tick-wise changes; to our current understanding, only the flippers
   will have any such changes, so this will be a no-op for most gizmos.
 - Each ball will have its simulation advanced. For this, the Model class
   goes through all of the gizmos and feeds their physics representations into
   the `CollisionFinder`, which will find the next collision the ball is
   expected to participate in. If this collision is within the timeframe of the
   current tick, the ball is advanced to the position where it collides (to
   comply with the “touching collisions” part of the specification) and its
   velocity is updated according to the physics library's reflection formula.


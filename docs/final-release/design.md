---
title: Test validation
author: JS8
documentclass: scrartcl
...

The general design and component interaction remained largely unaltered.

- We shielded the views from having knowledge of which concrete controllers are
used by introducing a `Controller` factory class. This class gets instantiated
at the top level main class and is passed down to the the views. It consists of
a set of methods each of which constructs a controller. It should be trivial to
inherit from `Controller` and return custom controllers.

- We made `Gizmo` -- which was an abstract class -- into an interface, and added
`BaseGizmo` as the abstract class implementing much of the common functionality
of `Gizmo`. This allowed us to refer to `Gizmo`s in the `BuildModel` interface,
making all the per-gizmo-type `add*()` methods unnecessary. It also means that
the user can more easily implement their own gizmos.

- We moved all the read-only methods from `Gizmo` to `ReadGizmo`.

- We added the extra `Sink`, `Spawner` and `SpinningFlipper` gizmos. This last one
shares common functionality with `StandardFlipper` through the common inherited
`Flipper` class.

- Each tick processes now a variable amount of time in the range
`(0, MINIMUM_FRAMERATE]`. The game timer is now variable, each tick being
scheduled for `processed_time - time_spend_processing`.

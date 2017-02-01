# Specification

## General

A game can be set up either by using the GUI's build mode or by loading a file
describing the game in a standard format. Once the game is set up, it can be
ran through the physics engine. It can be paused at any time, and saved into the
standard format. The user can quit the program at any time.

## Game description

The standard format defines a series of sequential instructions which are
analogous to executing a series of build actions using the GUI's build mode. The
allowed operations are:

- Create and place a (gizmo | ball).
- Rotate a (bumper | flipper) 90 degrees clockwise.
- Move a (gizmo | ball).
- Delete a (gizmo | ball).
- Connect a (gizmo | outer wall) to a gizmo.
- Connect a key to a gizmo.
- Define a global gravity.
- Define a global friction.

The game might have a variable size, but it has to be at least 20x20.

Multiple balls might exist.

Gizmos are located on a discrete grid. The bounding boxes of gizmos cannot
overlap with each other. The balls are located anywhere inside the continuum of
the arena.

An instruction with a reference to a gizmo that is defined at a later
instruction is not considered invalid.

### Elements

- Gizmos (*discrete* position)
    - Bumpers
        - `Square`, `Circle` or `Triangle`
        - Size 1x1
        - Can be rotated
    - Flippers
        - `LeftFlipper` or `RightFlipper`
        - Size 2x2
        - Can be rotated
    - `Absorber`
        - Variable size
- `Ball` (*continuous* position)
    - Fixed size
- `OuterWalls` (*outside* of the arena)
    - Fixed size
    - Fixed position

## Game execution

Once the game is running, the user interacts with it through the defined key
triggers. The user might pause the game at any time or go into build mode.

The velocity of the balls is affected by friction, gravity, and interactions
with gizmos.

### Triggering

The OuterWalls and all the gizmos generate a trigger event when hit by a ball.
Keys might generate trigger events when pressed or released.

Flippers respond to trigger events pivoting. This pivoting always happens within
their bounding boxes. If a flipper receives a trigger event while pivoting, the
flipper pivots back in the opposite direction.

When a ball hits an absorber, the absorber absorbs the ball. Absorbers respond
to trigger events by shooting these balls out. An absorber might trigger itself.


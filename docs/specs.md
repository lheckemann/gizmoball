# Elements

- Bumpers
    - Square, Circular or Triangular
    - Discrete position
    - Fixed size
    - Can be rotated
- Flippers
    - Discrete position
    - Fixed size
    - Pivot when triggered
    - Can be rotated
- Absorber
    - Discrete position
- Outer Walls
    - Discrete position
    - Fixed size
    - Fixed position
- Ball
    - Continuous position
    - Fixed size


# Dynamics

- Gravity and Friction are globally defined.
- A trigger can either be an element being hit by a ball or a key up or key down
  event.

# Game configuration

- Handled by sequentially executing a series of instructions.
- Elements can be created, rotated, moved, deleted, or connected.

# Observations

By translating the interactive building mode to a series of instructions we get
a common language for both the building mode and the configuration loading.

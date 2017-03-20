# Regression tests

All of these files should load correctly.

## flipper-pivot

Regression test for issue #43.

### Instructions
1. Load file
2. Start simulation (run)
3. Press space

### Expected outcome

Each of the 4 spawners on the left and right should spawn one ball. This ball
should fall along a straight line to the sink below it within 10 seconds.


## absorber-side

Regression test for issue #32

### Instructions

1. Load file
2. Run simulation

### Expected outcome

All 4 balls should disappear within a second.


## boxed

Regression test for issue #38

### Instructions

1. Load file
2. Run simulation

### Expected outcome

Ball that starts inside the box remains inside box for at least 20 seconds.

## ball-collisions

### Instructions

1. Load file
2. Run simulation

## Expected outcome

All balls collide with each other at the same time in the centre of the arena
and perpendicularly hit the outer walls repeatedly.

## still-ball-collision

### Instructions

1. Load file
2. Run simulation
3. Wait for lower ball to stop trembling
4. Press space

### Expected outcome

The falling ball hits the lower ball, which does not change position.

## rolling-ball

### Instructions

1. Load file
2. Run simulation

### Expected outcome

The ball rolls over the floor and repeatedly bounces against the two side walls.

## sliding

### Instructions

1. Load file
2. Run simulation

### Expected outcome

The balls slide against the squares and triangles without hitting any of their
lines and circles.

## highspeed

### Instructions

1. Load file
2. Run simulation

### Expected outcome

The ball forever bounces inside the arena at high speed. Ticks only display
collisions with the outer walls.

## speed

### Instructions

1. Load file
2. Run simulation

### Expected outcome

The ball should hit a side wall every 1s.


# Testing strategy

We have decided to support JUnit testing with a pool of regression tests (scenarios).
This decision was made due to the diffculty to test a running simulation with the JUnit
framework.

We have a total of 338 JUnit tests that target our Model, with a coverage percentage of:
- Absorber        - 100%
- BaseGizmo       - 100%
- Circle          - 100%
- Flipper         - 100%
- GizmoType       - 87.3%
- Rotation        - 92.6%
- Sink            - 100%
- Spawner         - 100%
- SpinningFlipper - 100%
- Square          - 100%
- StandardFlipper - 100%
- Triangle        - 100%
- Ball            - 100%
- CollisionFinder - Regression tests
- Geometry        - Regression tests
- Model           - 73.4% + Regression tests
- Saver           - 96.8%
- StandardLoader  - 97.6%

This is not as high as we wanted due to the previously mentioned issue.
Below are are all regression tests we currently have:

## Flipper-pivot

    Purpose: Regression test for issue #43
    Input:   Load -> "scenarios/regression/flipper-pivot" -> Start simulation if not already

    Expected: Each of the 4 spawners on the left and right should spawn one ball.
              This ball should fall along a straight line to the sink below it within 10 seconds.
    Actual:   Each of the 4 spawners on the left and right spawn one ball.
              This ball should fall along a straight line to the sink below it within 10 seconds.
    Result:   PASS
    Checked by "Linus Heckemann" on "c23f20c7d61b657aa6bc5ba0b8c66fbdc9d92d9f" at "22/03/2017"

## Absorber-side

    Purpose: Regression test for issue #32
    Input:   Load -> "scenarios/regression/absorber-side" -> Start simulation if not already

    Expected: All 4 balls should disappear within a second.
    Actual:   All 4 balls should disappear within a second.
    Result:   PASS
    Checked by "Linus Heckemann" on "c23f20c7d61b657aa6bc5ba0b8c66fbdc9d92d9f" at "22/03/2017"

## Boxed

    Purpose: Regression test for issue #38
    Input:   Load -> "scenarios/regression/boxed" -> Start simulation if not already

    Expected: Ball that starts inside the box remains inside box for at least 20 seconds.
    Actual:   Ball that starts inside the box exits box after approx. 11 seconds.
    Result:   FAIL
    Checked by "Linus Heckemann" on "c23f20c7d61b657aa6bc5ba0b8c66fbdc9d92d9f" at "22/03/2017"

## Ball-collisions

    Purpose:
    Input:   Load -> "scenarios/regression/ball-collisions" -> Start simulation if not already

    Expected: All balls collide with each other at the same time in the centre of the arena
              and perpendicularly hit the outer walls repeatedly.
    Actual:   All balls collide with each other at the same time in the centre of the arena
              and perpendicularly hit the outer walls repeatedly.
    Result:   PASS
    Checked by "Linus Heckemann" on "c23f20c7d61b657aa6bc5ba0b8c66fbdc9d92d9f" at "22/03/2017"

## Still-ball-collision

    Purpose: Regression test for issue #38
    Input:   Load -> "scenarios/regression/still-ball-collision"
                  -> Start simulation if not already
                  -> Wait for lower ball to stop trembling
                  -> Press space

    Expected: The falling ball hits the lower ball, which does not change position.
    Actual:   The falling ball hits the lower ball, which does not change position.
    Result:   PASS
    Checked by "Linus Heckemann" on "c23f20c7d61b657aa6bc5ba0b8c66fbdc9d92d9f" at "22/03/2017"

## Rolling-ball

    Purpose:
    Input:   Load -> "scenarios/regression/rolling-ball" -> Start simulation if not already

    Expected: The ball rolls over the floor and repeatedly bounces against the two side walls.
    Actual:   The ball rolls over the floor and repeatedly bounces against the two side walls.
    Result:   PASS
    Checked by "Linus Heckemann" on "c23f20c7d61b657aa6bc5ba0b8c66fbdc9d92d9f" at "22/03/2017"

## Sliding

    Purpose:
    Input:   Load -> "scenarios/regression/sliding" -> Start simulation if not already

    Expected: The balls slide against the squares and triangles without hitting any of their
              lines and circles.
    Actual:   The balls slide against the squares and triangles without hitting any of their
              lines and circles.
    Result:   PASS
    Checked by "Linus Heckemann" on "c23f20c7d61b657aa6bc5ba0b8c66fbdc9d92d9f" at "22/03/2017"

## Highspeed

    Purpose:
    Input:   Load -> "scenarios/regression/highspeed" -> Start simulation if not already

    Expected: The ball forever bounces inside the arena at high speed. Ticks only display
              collisions with the outer walls.
    Actual:   The ball forever bounces inside the arena at high speed. Ticks only display
              collisions with the outer walls.
    Result:   PASS
    Checked by "Linus Heckemann" on "c23f20c7d61b657aa6bc5ba0b8c66fbdc9d92d9f" at "22/03/2017"

## Speed

    Purpose:
    Input:   Load -> "scenarios/regression/speed" -> Start simulation if not already

    Expected: The ball should hit a side wall every 1s.
    Actual:   The ball should hit a side wall every 1s.
    Result:   PASS
    Checked by "Linus Heckemann" on "c23f20c7d61b657aa6bc5ba0b8c66fbdc9d92d9f" at "22/03/2017"

## Absorber-straight

    Purpose:
    Input:   Load -> "scenarios/regression/absorber-straight"
                  -> Start simulation if not already

    Expected: All balls should bounce straight up and down.
    Actual:   All balls should bounce straight up and down.
    Result:   PASS
    Checked by "Linus Heckemann" on "c23f20c7d61b657aa6bc5ba0b8c66fbdc9d92d9f" at "22/03/2017"

## Flipper-edge

    Purpose:
    Input:   Load -> "scenarios/regression/flipper-edge" -> Start simulation if not already

    Expected: Both balls should bounce off the edge of the flipper and not fall straight down.
    Actual:   Both balls should bounce off the edge of the flipper and not fall straight down.
    Result:   PASS
    Checked by "Linus Heckemann" on "c23f20c7d61b657aa6bc5ba0b8c66fbdc9d92d9f" at "22/03/2017"

## Nostack

    Purpose:
    Input:   Load -> "scenarios/regression/nostack"
                  -> Start simulation if not already
                  -> Press space twice

    Expected: The spawned balls should bounce apart horizontally after several collisions,
not form a stack.
    Actual:   The spawned balls bounce apart horizontally after several collisions, and do not form a stack.
    Result:   PASS
    Checked by "Linus Heckemann" on "c23f20c7d61b657aa6bc5ba0b8c66fbdc9d92d9f" at "22/03/2017"

## Predictable-path

    Purpose:
    Input:   Load -> "scenarios/regression/predictable-path"
                  -> Start simulation if not already
                  -> Wait 1 minute

    Expected: All three balls spawned at the beginning should still be in the arena.
    Actual:   All three balls spawned at the beginning should still be in the arena.
    Result:   PASS
    Checked by "Linus Heckemann" on "c23f20c7d61b657aa6bc5ba0b8c66fbdc9d92d9f" at "22/03/2017"

## Flipper-coll

    Purpose:
    Input:   Load -> "scenarios/regression/flipper-coll" -> Start simulation if not already

    Expected: Ball should collide correctly with the circles on the end of the flipper.
    Actual:   Ball collides correctly with the circles on the end of the flipper.
    Result:   PASS
    Checked by "Linus Heckemann" on "c23f20c7d61b657aa6bc5ba0b8c66fbdc9d92d9f" at "22/03/2017"

## Transmission

    Purpose:
    Input:   Load -> "scenarios/regression/transmission" -> Start simulation if not already

    Expected: The side balls trasmit force to one another through the ball in the middle,
which stays stationary.
    Actual:   The side balls trasmit force to one another through the ball in the middle,
which stays stationary.
    Result:   PASS
    Checked by "Linus Heckemann" on "c23f20c7d61b657aa6bc5ba0b8c66fbdc9d92d9f" at "22/03/2017"

## Settle

    Purpose:
    Input:   Load -> "scenarios/regression/settle" -> Start simulation if not already

    Expected: Each ball should settle and stop moving in the space below it.
    Actual:   Some balls do not settle.
    Result:   FAIL
    Checked by "Linus Heckemann" on "c23f20c7d61b657aa6bc5ba0b8c66fbdc9d92d9f" at "22/03/2017"


# Validation Testing Strategy Results

## MOVE
## Number:  Test 1

    Purpose: Move gizmo or Ball - Gizmo as source and empty cell as destination
    Input:   Build Mode -> Move -> Click on the board
                                -> Click on destination position

    Expected: Selected Gizmo/Ball is now on the new position
    Actual:   Selected Gizmo/Ball is now on the new position
    Result:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 2

    Purpose: Move gizmo or Ball - Gizmo as source and Gizmo as destination
    Input:   Build Mode -> Move -> Click on the board
                                -> Click on destination position

    Expected: Error - "Can't move on top of another gizmo or ball"
    Actual:   Error - "Can't move on top of another gizmo or ball"
    Result:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 3

    Purpose: Move gizmo or Ball - Empty cell as source and empty cell as destination
    Input:   Build Mode -> Move -> Click on the board
                                -> Click on destination position

    Expected: No changes
    Actual:   No changes
    Result:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 4

    Purpose: Move gizmo or Ball - Empty cell as source and Gizmo as destination
    Input:   Build Mode -> Move -> Click on the board
                                -> Click on destination position

    Expected: Destination Gizmo is waiting for a move operation
    Actual:   Destination Gizmo is waiting for a move operation
    Result:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## DELETE
## Number:  Test 5

    Purpose: Delete Gizmos or Ball
    Input:   Build Mode -> Delete -> Click on the a Gizmo on the board

    Expected: Selected Gizmo/Ball is removed from the game
    Actual:   Selected Gizmo/Ball is removed from the game
    Result:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 6

    Purpose: Delete Gizmos or Ball - Empty cell
    Input:   Build Mode -> Delete -> Click on an emtpy area on the board

    Expected: No changes
    Actual:   No changes
    Result:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## ROTATE
## Number:  Test 7

    Purpose: Rotate Absorber
    Input:   Build Mode -> Rotate -> Click on an Absorber on the board

    Expected: Error - "Absorbers cannot be rotated"
    Actual:   Error - "Absorbers cannot be rotated"
    Result:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 8

    Purpose: Rotate Ball
    Input:   Build Mode -> Rotate -> Click on a Ball on the board

    Expected: Error - "Ball cannot be rotated"
    Actual:   Error - "Ball cannot be rotated"
    Result:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 9

    Purpose: Rotate Triangle and Flipper
    Input:   Build Mode -> Rotate -> Click on a Triangle or Flipper on the board

    Expected: Selected gizmo rotates
    Actual:   Selected gizmo rotates
    Result:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 10

    Purpose: Rotate any other Gizmo
    Input:   Build Mode -> Rotate -> Click on any other gizmo on the board

    Expected: Selected gizmo does not rotate
    Actual:   Selected gizmo does not rotate
    Result:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## ADD
## Number:  Test 11

    Purpose: Add absorber
    Input:   Build Mode -> Add absorber
                 -> Select starting position for the absorber
                 -> Drag mouse to specify absorber size
                 -> Mouse release to place absorber

    Expected: Absorber appears on the selected area on the board.
    Actual:   Absorber appears on the selected area on the board.
    Result:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 12

    Purpose: Add absorber - Overlapping other Gizmos
    Input:   Build Mode -> Add absorber
                 -> Select starting position for the absorber
                 -> Drag mouse to specify absorber size
                    and to try overlapping already placed gizmos

    Expected: You are not allowed to drag occupied areas.
    Actual:   You are not allowed to drag occupied areas.
    Result:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 13

    Purpose: Add other Gizmos
    Input:   Build Mode -> Add {any gizmo} -> Click on the board

    Expected: Correct gizmo appears on the selected area on the board.
    Actual:   Correct gizmo appears on the selected area on the board.
    Result:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 14

    Purpose: Add other Gizmos - Overlapping other Gizmos
    Input:   Build Mode -> Add {any gizmo} -> Click on the board
             where another gizmo is already present

    Expected: Error - "Can't place a gizmo on top of another gizmo or ball"
    Actual:   Error - "Can't place a gizmo on top of another gizmo or ball"
    Result:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 15

    Purpose: Add ball
    Input:   Build Mode -> Add -> Ball -> Click on the board -> Specify
                 initial velocity on displayed message box

    Expected: Ball appears on the selected area on the board.
    Actual:   Ball appears on the selected area on the board.
    Result:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 16

    Purpose: Add ball - Overlapping non absorber Gizmos
    Input:   Build Mode -> Add -> Ball -> Click on non absorber gizmo
    Expected: Error - "Can't place a ball on top of another gizmo or ball"
    Actual:   Error - "Can't place a ball on top of another gizmo or ball"
    Result:   PASS
    Checked by "William Macdonald" on "f8e539d704d151b3120185a1a1950a8624ed3213" at "22/03/2017"


## Number:  Test 17

    Purpose: Add ball - Cancel action
    Input:   Build Mode -> Add -> Ball -> Click on the board -> Close
             "set ball velocity" dialog

    Expected: The board is the same with ball not placed
    Actual:   The board is the same with ball not placed
    Result:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Triggers
## Number:  Test 18

    Purpose: Trigger on gizmo - Gizmo as source and empty cell (or Ball) as destination
    Input:   Build Mode -> Trigger on gizmo -> Click on the board
                                            -> Click on destination position

    Expected: Nothing changes and no connection has been made
    Actual:   Nothing changes and no connection has been made
    Result:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 19

    Purpose: Trigger on gizmo - Gizmo as source and Gizmo as destination
    Input:   Build Mode -> Trigger on gizmo -> Click on the board
                                            -> Click on destination position

    Expected: Connection has been added (run game to check)
    Actual:   Connection has been added (run game to check)
    Result:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 20

    Purpose: Trigger on gizmo - Empty cell (or Ball) as source and empty cell (or Ball)
                                as destination
    Input:   Build Mode -> Trigger on gizmo -> Click on the board
                                            -> Click on destination position

    Expected: Nothing changes and no connection has been made
    Actual:   Nothing changes and no connection has been made
    Result:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 21

    Purpose: Trigger on gizmo - Empty cell (or Ball) as source and Gizmo as destination
    Input:   Build Mode -> Trigger on gizmo -> Click on the board
                                            -> Click on destination position

    Expected: Destination Gizmo is waiting for a trigger operation
    Actual:   Destination Gizmo is waiting for a trigger operation
    Result:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 22

    Purpose: Trigger on keypress - Gizmo as source and key as destination
    Input:   Build Mode -> Trigger on keypress -> Click on the board
                                            -> Click on destination position

    Expected: Connection has been added (run game to check)
    Actual:   Connection has been added (run game to check)
    Result:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 23

    Purpose: Trigger on keypress - Empty cell as source (or Ball) and key as destination
    Input:   Build Mode -> Trigger on keypress -> Click on the board
                                               -> Click on destination position

    Expected: Nothing happens
    Actual:   Nothing happens
    Result:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 24

    Purpose: Trigger on outer wall - Gizmo as source
    Input:   Build Mode -> Trigger on outer wall -> Click on the board

    Expected: Connection has been added (run game to check)
    Actual:   Connection has been added (run game to check)
    Result:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 25

    Purpose: Trigger on outer wall - Empty cell as source (or Ball)
    Input:   Build Mode -> Trigger on outer wall -> Click on the board

    Expected: Nothing happens
    Actual:   Nothing happens
    Result:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Physics - Friction mu, friction mu2 and gravity
## Number:  Test 26

    Purpose: Modify friction MU
    Input:   Build Mode -> Modify input field next to label "Friction mu"

    Expected: New friction mu value is applied to the game
    Actual:   New friction mu value is applied to the game
    Result:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 27

    Purpose: Modify friction MU with wrong type (e.g. string) or out of range
    Input:   Build Mode -> Modify input field next to label "Friction mu"

    Expected: Old friction mu value is still applied to the game
    Actual:   Old friction mu value is still applied to the game
    Result:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 28

    Purpose: Modify gravity
    Input:   Build Mode -> Modify input field next to label "Gravity"

    Expected: New gravity value is applied to the game
    Actual:   New gravity value is applied to the game
    Result:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 29

    Purpose: Modify gravity with wrong type (e.g. string) or out of range
    Input:   Build Mode -> Modify input field next to label "Gravity"

    Expected: Old gravity value is still applied to the game
    Actual:   Old gravity value is still applied to the game
    Result:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 30

    Purpose: Modify friction MU2
    Input:   Build Mode -> Modify input field next to label "Friction mu2"

    Expected: New friction mu2 value is applied to the game
    Actual:   New friction mu2 value is applied to the game
    Result:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 31

    Purpose: Modify friction MU2 with wrong type (e.g. string) or out of range
    Input:   Build Mode -> Modify input field next to label "Friction mu2"

    Expected: Old friction mu2 value is still applied to the game
    Actual:   Old friction mu2 value is still applied to the game
    Result:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Switch between modes
## Number:  Test 32

    Purpose: Switch to run mode with a blank scenario
    Input:   Build Mode -> Run

    Expected: No gizmos are present and game is empty
    Actual:   No gizmos are present and game is empty
    Result:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 33

    Purpose: Switch to run mode with a scenario
    Input:   Build Mode -> Run

    Expected: Scenario is still present
    Actual:   Scenario is still present
    Result:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 34

    Purpose: Switch to build mode with a blank scenario
    Input:   Run Mode -> Build

    Expected: No gizmos are present and game is empty
    Actual:   No gizmos are present and game is empty
    Result:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 35

    Purpose: Switch to build mode with a scenario
    Input:   Run Mode -> Build

    Expected: Scenario is still present
    Actual:   Scenario is still present
    Result:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Run game
## Number:  Test 36

    Purpose: Press stop button
    Input:   Run Mode -> Run -> Stop
                                OR
                                default state when switching to Run Mode

    Expected: The game stops (i.e. no balls are moving and triggers are disabled)
    Actual:   The game stops (i.e. no balls are moving and triggers are disabled)
    RESULT:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 37

    Purpose: Press run button
    Input:   Run Mode -> Run

    Expected: The game runs (i.e. balls are moving and triggers are enabled)
    Actual:   The game stops (i.e. balls are moving and triggers are enabled)
    RESULT:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 38

    Purpose: Press tick button
    Input:   Run Mode -> Stop (default state when switching to Run Mode)
                      -> Tick

    Expected: The game runs for only one tick
    Actual:   The game runs for only one tick
    RESULT:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 39

    Purpose: Press/Release key to trigger connected gizmo
    Input:   Run Mode -> Run -> Press/Release Key

    Expected: Any gizmos connected to the key are triggered.
    Actual:   Any gizmos connected to the key are triggered.
    RESULT:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 40

    Purpose: Press/Release key to trigger connected gizmo
    Input:   Run Mode -> Run -> Press/Release a key not connected to any Gizmos

    Expected: No Gizmos are being triggered by the key press/release
    Actual:   No Gizmos are being triggered by the key press/release
    RESULT:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 41

    Purpose: Hit ball(s) with flipper
    Input:   Run Mode -> Run -> Press/Release Key to trigger flippers

    Expected: Collision between flipper and ball(s) and change of their velocity
    Actual:   Collision between flipper and ball(s) and change of their velocity
    RESULT:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Other functionalities
## Number:  Test 42

    Purpose: Load game board from file - Valid file
    Input:   Load -> Select file

    Expected: Scenario is fully and correctly loaded
    Actual:   Scenario is fully and correctly loaded
    RESULT:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 43

    Purpose: Load game board from file - Invalid file
    Input:   Load -> Select file

    Expected: Error with meaninful description of the issue
    Actual:   Error with meaninful description of the issue
    RESULT:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 44

    Purpose: Load game board from file - Abort operation
    Input:   Load -> Select file -> Close dialog to abort

    Expected: Nothing changes
    Actual:   Nothing changes
    RESULT:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 45

    Purpose: Load game board from file - Game runs in the background
    Input:   Run Mode (and on Run state)-> Load -> Select file

    Expected: In the background, the game normally runs
    Actual:   In the background, the game normally runs
    RESULT:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 46

    Purpose: Save game board to file - Standard - New file
    Input:   Save -> Standard ->
             Select location and name

    Expected: File is saved (load to double check)
    Actual:   File is saved (load to double check)
    RESULT:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 47

    Purpose: Save game board to file - Standard - Existing file
    Input:   Save -> Standard ->
             Select location and name

    Expected: File is saved and overwrite occured (load to double check)
    Actual:   File is saved and overwrite occured (load to double check)
    RESULT:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 48

    Purpose: Save game board to file - Extended - New file
    Input:   Save -> Extended ->
             Select location and name

    Expected: File is saved (load to double check)
    Actual:   File is saved (load to double check)
    RESULT:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 49

    Purpose: Save game board to file - Extended - Existing file
    Input:   Save -> Extended ->
             Select location and name

    Expected: File is saved and overwrite occured (load to double check)
    Actual:   File is saved and overwrite occured (load to double check)
    RESULT:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 50

    Purpose: Save game board to file - Abort
    Input:   Save -> Cancel or "X"
             OR
             Save -> Standard/Extended -> Cancel or "X"

    Expected: No saving occured
    Actual:   No saving occured
    RESULT:   PASS
    Checked by "William Macdonald" on "f8e539d704d151b3120185a1a1950a8624ed3213" at "22/03/2017"

## Number:  Test 51

    Purpose: Save game board to file - Game runs in the background
    Input:   Run Mode (and on Run state)-> Save -> Select saver, location and name

    Expected: In the background, the game normally runs
    Actual:   In the background, the game normally runs
    RESULT:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 52

    Purpose: New game
    Input:   New

    Expected: It creates a new empty board
    Actual:   It creates a new empty board
    RESULT:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number:  Test 53

    Purpose: Quit system
    Input:   Exit

    Expected: It exits the system
    Actual:   It exits the system
    RESULT:   PASS
    Checked by "Francesco Meggetto" on "3efd86a2d250a8be8e0c65b5bc39ca01cd67e6dd" at "22/03/2017"

## Number: Test 54

    Purpose: Add ball - Inside absorber
    Input:   BuildMode -> Add Ball -> Click on absorber -> Specify initial velocity
    Expected: Ball is consumed by absorber & can be fired by absorber trigger
    Actual: Ball is consumed by absorber & can be fired by absorber trigger (run game to check)
    Result: PASS
    Checked by "William Macdonald" on "f8e539d704d151b3120185a1a1950a8624ed3213" at "22/03/2017"

## Number: Test 55
    Purpose: Add ball - Enter non numeric velocity values
    Input:   BuildMode -> Add Ball -> Click on empty location ->
             Enter alphanumeric strings into velocity prompt
    Expected: Error - "Velocity value error - value must be numeric"
    Actual: Error - "Velocity value error - Value must be numeric"
    Result: PASS
    Checked by "William Macdonald" on "f8e539d704d151b3120185a1a1950a8624ed3213" at 22/03/2017

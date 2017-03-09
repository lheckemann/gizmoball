
# Validation Testing Strategy

## Number:  Test 1

    Purpose: Add gizmo (bumper or flipper)
    Input:   Build Mode -> Add -> L/R Flipper -> Click on the board
    Output:  UI Changes     - L/R Flipper (angle 0 degrees) appears
                              at the selected position on the board.
             Error messages - if Gizmo already present, "A Gizmo at
                              selected position already present"
                              error message.

## Number:  Test 2

    Purpose: Add ball
    Input:   Build Mode -> Add -> Ball -> Click on the board -> Specify
                 initial velocity on displayed message box
    Output:  UI Changes     - Ball appears at the selected position
                              on the board with specified velocity
             Error messages - if Gizmo already present (not absorber),
                              "A Gizmo at selected position already present"
                              error message
                              OR
                              if velocity is not a whole, decimal number or
                              not within range, "Velocity must be a whole or
                              decimal number within range {x1} and {x2}".

## Number:  Test 3

    Purpose: Add absorber
    Input:   Build Mode -> Add -> Absorber
                 -> Select starting position for the absorber
                 -> Drag mouse to specify absorber size
                 -> Mouse release to place absorber
    Output:  UI Changes     - Absorber appears on the selected
                              area on the board.
             Error messages - if Gizmo already present on any position
                              within its area, "A Gizmo at
                              selected position already present"
                              error message.
## Number:  Test 4

    Purpose: Delete gizmo (Bumper, Flipper, Ball, Absorber)
    Input:   Build Mode -> Delete -> Click on the board
    Output:  UI Changes     - if Gizmo present, it is removed
                              from the board.

## Number:  Test 5

    Purpose: Move gizmo (bumper, flipper, ball, absorber)
    Input:   Build Mode -> Move -> Click on a gizmo on the board
                                -> Drag on destination position
    Output:  UI Changes     - Gizmo is repositioned and is now in
                              the new position on the board.
             Error messages - if destination position is occupied, "A Gizmo at
                              selected position already present"
                              error message.

## Number:  Test 6

    Purpose: Rotate gizmo (bumper, flipper)
    Input:   Build Mode -> Rotate -> Click on a gizmo on the board
    Output:  UI Changes     - Gizmo is rotated and displayed on the board
             Error messages - if Gizmo type is not rotatable, "The {type}
                              gizmo cannot be rotated" error message.

## Number:  Test 7

    Purpose: Modify gravity
    Input:   Build Mode -> Modify input field next to label "Gravity: "
    Output:  UI Changes     - Text field now shows the new gravity
             Error messages - if value is not a whole, decimal number or
                              not within range, "Gravity must be a whole
                              or decimal number within range {x1} and {x2}"
                              error message.

## Number:  Test 8

    Purpose: Modify friction MU
    Input:   Build Mode -> Modify input field next to label "Friction MU: "
    Output:  UI Changes     - Text field now shows the new friction MU
             Error messages - if value is not a number, whole, decimal number
                              or not within range, "Friction MU must be a
                              whole or decimal number within range {x1} and {x2}"
                              error message.

## Number:  Test 9

    Purpose: Modify friction MU2
    Input:   Build Mode -> Modify input field next to label "Friction MU2: "
    Output:  UI Changes     - Text field now shows the new friction MU2
             Error messages - if value is not a number, whole, decimal number or
                              not within range,
                              "Friction MU2 must be a whole or decimal number
                              within range {x1} and {x2}" error message.

## Number:  Test 10

    Purpose: Connect key press to gizmo action
    Input:   Build Mode -> Connect -> List of connections displayed to user
                                   -> Click on "Add connection"
                                   -> Press to-be-triggered Key
                                   -> Click on gizmo(s) on the board
                                   -> Click "Done" to add connections
                                      OR
                                   -> Decline changes
    Output:  UI Changes - success message displayed to user.

## Number:  Test 11

    Purpose: Connect gizmo to gizmo action
    Input:   Build Mode -> Connect -> List of connections displayed to user
                                   -> Click on "Add connection"
                                   -> Click on to-be-triggered Gizmo on the board
                                   -> Click on gizmo(s) on the board
                                   -> Click "Done" to add connections
                                      OR
                                   -> Decline changes
    Output:  UI Changes - success message displayed to user.

## Number:  Test 12

    Purpose: Remove connection
    Input:   Build Mode -> Connect -> List of connections displayed to user
                                   -> Click on "Remove connection"
                                   -> Click on desired connection
                                   -> Click "Yes" on System's "Are you sure you
                                      want to remove that connection?" message
                                      OR
                                   -> Decline changes
    Output:  UI Changes - success message displayed to user.

## Number:  Test 13

    Purpose: Switch to run mode
    Input:   Build Mode -> Run
    Output:  UI Changes - the system switches to Run mode.

## Number:  Test 14

    Purpose: Switch to build mode
    Input:   Run Mode -> Build
    Output:  UI Changes - the system switches to Build mode.

## Number:  Test 15

    Purpose: Press key to active gizmo
    Input:   Run Mode -> Run -> Press Key
    Output:  UI Changes - any gizmos connected to the key are activated.

## Number:  Test 16

    Purpose: Hit ball(s) with flipper
    Input:   Run Mode -> Run -> Press Key to trigger flippers
    Output:  UI Changes - flippers hit ball(s) and their velocity changes.

## Number:  Test 17

    Purpose: Press stop button
    Input:   Run Mode -> Run -> Stop
                                OR
                                default state when switching to Run Mode
    Output:  UI Changes - game pauses (i.e. ball(s) stops moving).

## Number:  Test 18

    Purpose: Press run button
    Input:   Run Mode -> Run
    Output:  UI Changes - game starts running (i.e. ball(s) start moving).

## Number:  Test 19

    Purpose: Press tick button
    Input:   Run Mode -> Stop (default state when switching to Run Mode)
                      -> Tick
    Output:  UI Changes - Ball(s) moves for the duration of one tick.

## Number:  Test 20

    Purpose: Load game board from file
    Input:   Build/Run Mode -> Load -> Select file
    Output:  UI Changes     - game board is loaded
             Error messages - if file is not valid, "File not found -
                              Invalid commands" error message.

## Number:  Test 21

    Purpose: Save game board to file
    Input:   Build/Run Mode -> Save -> Select location and name
    Output:  UI Changes     - "Game board is saved successfully" message.
             File changes   - new file containing current game has been created.
             Error messages - if file already exists, ask user for overwriting or
                              different name.

## Number:  Test 22

    Purpose: Quit system
    Input:   Build Mode -> Exit -> Decide whether to save or not current game board
                                   (system's message)
             Run Mode -> Exit -> Click "Yes" on the "Are you sure you want to quit
                                 the game" message
    Output:  UI Changes - it exits the system.
                          If wanted to save (Build Mode), decide location and name
                          to save game board.
                          If click "No" on the "Are you sure you want to quit
                          the game" message, no GUI changes.

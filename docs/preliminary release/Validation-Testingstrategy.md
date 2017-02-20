# Validation Testing Strategy

---- From Preliminary Design feedback ---
Missed Switch to Build Mode from Run Mode
Add Ball should specify initial velocity
Alternative path for saving on top of existing file
----

## Number:  Test 1
   Purpose: Add gizmo (bumper or flipper)
   Input:   Build Mode -> Add -> L/R Flipper -> Click on the board
   Output:  UI Changes     - L/R Flipper (angle 0 degrees) appears
			                 at the selected position on the board
            Error messages - if Gizmo already present, "A Gizmo at
                             selected position already present"
                             error message

## Number:  Test 2
   Purpose: Add ball
   Input:   Build Mode -> Add -> Ball -> Click on the board
   Output:  UI Changes     - Ball appears at the selected position
                             on the board
            Error messages - if Gizmo already present, "A Gizmo at
                             selected position already present"
                             error message

## Number:  Test 3
   Purpose: Add absorber
   Input:   Build Mode -> Add -> Absorber
                -> Selected starting position for the absorber
                -> Drag mouse to specify absorber size
                -> Mouse release to place absorber
   Output:  UI Changes     - Absorber appears on the selected
                             area on the board
            Error messages - if Gizmo already present on any position
                             within its area, "A Gizmo at
                             selected position already present"
                             error message
## Number:  Test 4
   Purpose: Delete gizmo (Bumper, Flipper, Ball, Absorber)
   Input:   Build Mode -> Delete -> Click on the board
   Output:  UI Changes     - If Gizmo present, it is removed
                             from the board

## Number:  Test 5
   Purpose: Move gizmo (bumper, flipper, ball, absorber)
   Input:   Build Mode -> Move -> Click on a gizmo on the board
                               -> Drag on destination position
   Output:  UI Changes     - Gizmo is repositioned and is now in
                             the new position on the board
            Error messages - if destination position is occupied, "A Gizmo at
                             selected position already present"
                             error message
							 
## Number:  Test 6
   Purpose: Rotate gizmo (bumper, flipper)
   Input:   Build Mode -> Rotate -> Click on a gizmo on the board
   Output:  UI Changes     - Gizmo is rotated and displayed on the board
            Error messages - if Gizmo type is not rotatable, "The {type}
                             gizmo cannot be rotated" error message

## Number:  Test 7
   Purpose: Modify gravity
   Input:   Build Mode -> Rotate -> Click on a gizmo on the board
   Output:  UI Changes     - Gizmo is rotated and displayed on the board
            Error messages - if Gizmo type is not rotatable, "The {type}
                             gizmo cannot be rotated" error message

??????????????????????
- Preconditions: User must be in build mode
- Triggers: User clicks on gravity parameter field
- Basic course of events:
    1. User enters a value for gravity
    2. Value user entered is valid
    3. Gravity is successfully modified
- Alternate paths:
     a)
        2. Value user entered is non numeric
        3. System informs user that gravity must be a numeric value
        4. Go to step 1 (Main path)
     b)
        2. Value user entered is out of range
        3. System informs user that gravity must be within a specific range
        4. Go to step 1 (Main path)

## Modify friction

- Preconditions: User must be in build mode
- Triggers: User clicks on friction parameter field
- Basic course of events:
    1. User enters a value for friction
    2. Value user entered is valid
    3. Friction is successfully modified
- Alternate paths:
    a)
        2. Value user entered is non numeric
        3. System informs user that friction must be a numeric value
        4. Go to step 1 (Main path)
    b)
        2. Value user entered is out of range
        3. System informs user that friction must be within a specific range
        4. Go to step 1 (Main path)

## Connect key press to gizmo action

- Preconditions: User must be in build mode
- Triggers: User chooses connect option
- Basic course of events:
    1. System displays connections to user
    2. User chooses to add a connection
    3. User chooses the key they want to use
    4. User chooses all of the gizmos to connect the key press to
    5. User selects "Done" button to choose they are finished adding connections
    6. Connections are successfully added

## Connect gizmo to gizmo action

- Preconditions: User must be in build mode
- Triggers: User chooses connect option
- Basic course of events:
    1. System displays connections to user
    2. User chooses to add a connection
    3. User chooses the gizmo they want to be the trigger
    4. User chooses all of the gizmos to connect the chosen gizmo to
    5. User selects "Done" button to choose they are finished adding connections
    6. Connections are successfully added

## Remove connection

- Preconditions: User must be in build mode
- Triggers: User chooses connect option
- Basic course of events:
    1. System displays connections to user
    2. User chooses to remove a connection
    3. User chooses the connection they would like to remove
    4. System prompts user to confirm that they wish to proceed with the removal of the connection
    5. User confirms their choice
    6. Connection is removed
- Alternate path:
    5. User declines their choice
    6. Go to step 1 (Main path)

## Switch to run mode

- Preconditions: User must be in build mode
- Basic course of events:
    1. User selects "Run" mode button
    2. System switches from "Build" mode to "Run" mode

## Press key to active gizmo

- Preconditions: User must be in run mode
- Basic course of events:
    1. User presses their chosen key
    2. Any gizmos connected to the key are activated

## Hit ball with flipper

- Preconditions: User must be in run mode
- Basic course of events:
    1. User presses key to active flipper
    2. User hits ball with flipper
    3. Ball's velocity changes

## Press pause button

- Preconditions: User must be in run mode and currently have the game running
- Basic course of events:
    1. User presses pause button
    2. Game pauses (i.e. ball(s) stops moving)

## Press play button

- Preconditions: User must be in run mode and currently have the game paused
- Basic course of events:
    1. User presses play button
    2. Game starts running (i.e. ball(s) start moving)

## Press tick button

- Preconditions: User must be in run mode and currently have the game paused
- Basic course of events:
    1. User presses tick button
    2. Ball(s) moves for the duration of one tick

## Load game board from file

- Triggers: User chooses load option
- Basic course of events:
    1. User chooses file to load game from
    2. File user chose is a valid file
    3. Game board is loaded successfully
- Alternative paths:
    2. File user chose is invalid
    3. System informs user the chosen file was invalid
    4. Go to step 1 (Main path)

## Save game board to file

- Triggers: User chooses save option
- Basic course of events:
    1. User chooses location to save game
    2. Game board is saved successfully

## Quit system

- Triggers: User chooses quit option
- Basic course of events:
    1. User is in run mode
    2. System prompts user to confirm quit
    3. User confirms quit
    4. System is terminated
- Alternate paths:
    a)
        1. User is in build mode
        2. System prompts user to save their game board
        3. User chooses location to save game board
        4. Go to step 2 (Main path)
    b)
        3. User declines quit


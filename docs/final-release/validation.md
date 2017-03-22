
# Validation Testing Strategy Results

## MOVE
## Number:  Test 1

    Purpose: Move gizmo or Ball - Gizmo as source and empty cell as destination
    Input:   Build Mode -> Move -> Click on the board
                                -> Click on destination position

    Expected: Selected Gizmo/Ball is now on the new position
    Actual:   Selected Gizmo/Ball is now on the new position
    Result:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 2

    Purpose: Move gizmo or Ball - Gizmo as source and Gizmo as destination
    Input:   Build Mode -> Move -> Click on the board
                                -> Click on destination position

    Expected: Error - "Can't move on top of another gizmo or ball"
    Actual:   Error - "Can't move on top of another gizmo or ball"
    Result:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 3

    Purpose: Move gizmo or Ball - Empty cell as source and empty cell as destination
    Input:   Build Mode -> Move -> Click on the board
                                -> Click on destination position

    Expected: No changes
    Actual:   No changes
    Result:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 4

    Purpose: Move gizmo or Ball - Empty cell as source and Gizmo as destination
    Input:   Build Mode -> Move -> Click on the board
                                -> Click on destination position

    Expected: Destination Gizmo is waiting for a move operation
    Actual:   Destination Gizmo is waiting for a move operation
    Result:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## DELETE    
## Number:  Test 5

    Purpose: Delete Gizmos or Ball
    Input:   Build Mode -> Delete -> Click on the a Gizmo on the board

    Expected: Selected Gizmo/Ball is removed from the game
    Actual:   Selected Gizmo/Ball is removed from the game
    Result:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 6

    Purpose: Delete Gizmos or Ball - Empty cell
    Input:   Build Mode -> Delete -> Click on an emtpy area on the board

    Expected: No changes
    Actual:   No changes
    Result:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## ROTATE   
## Number:  Test 7

    Purpose: Rotate Absorber
    Input:   Build Mode -> Rotate -> Click on an Absorber on the board

    Expected: Error - "Absorbers cannot be rotated"
    Actual:   Error - "Absorbers cannot be rotated"
    Result:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 8

    Purpose: Rotate Ball
    Input:   Build Mode -> Rotate -> Click on an Ball on the board

    Expected: Error - "Ball cannot be rotated"
    Actual:   Error - "Ball cannot be rotated"
    Result:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 9

    Purpose: Rotate any other Gizmo
    Input:   Build Mode -> Rotate -> Click on any other Gizmo on the board

    Expected: Gzmo rotates, if possible
    Actual:   Gzmo rotates, if possible
    Result:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## ADD
## Number:  Test 10

    Purpose: Add absorber
    Input:   Build Mode -> Add absorber
                 -> Select starting position for the absorber
                 -> Drag mouse to specify absorber size
                 -> Mouse release to place absorber

    Expected: Absorber appears on the selected area on the board.
    Actual:   Absorber appears on the selected area on the board.
    Result:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 11

    Purpose: Add absorber - Overlapping other Gizmos
    Input:   Build Mode -> Add absorber
                 -> Select starting position for the absorber
                 -> Drag mouse to specify absorber size
                    and to try overlapping already placed gizmos

    Expected: You are not allowed to drag occupied areas.
    Actual:   You are not allowed to drag occupied areas.
    Result:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 12

    Purpose: Add other Gizmos
    Input:   Build Mode -> Add {any gizmo} -> Click on the board

    Expected: Correct gizmo appears on the selected area on the board.
    Actual:   Correct gizmo appears on the selected area on the board.
    Result:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 13

    Purpose: Add other Gizmos - Overlapping other Gizmos
    Input:   Build Mode -> Add {any gizmo} -> Click on the board
             where another gizmo is already present

    Expected: Error - "Can't place a gizmo on top of another gizmo or ball"
    Actual:   Error - "Can't place a gizmo on top of another gizmo or ball"
    Result:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 14

    Purpose: Add ball
    Input:   Build Mode -> Add -> Ball -> Click on the board -> Specify
                 initial velocity on displayed message box

    Expected: Ball appears on the selected area on the board.
    Actual:   Ball appears on the selected area on the board.
    Result:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 15

    Purpose: Add ball - Overlapping other Gizmos
    Input:   Build Mode -> Add -> Ball -> Click on the board -> Specify
                 initial velocity on displayed message box

    Expected: Error - "Can't place a ball on top of another gizmo or ball"
    Actual:   Error - "Can't place a ball on top of another gizmo or ball"
    Result:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 16

    Purpose: Add ball - Cancel action
    Input:   Build Mode -> Add -> Ball -> Click on the board -> Close
             "set ball vleocity" dialog

    Expected: The board is the same with ball not placed
    Actual:   The board is the same with ball not placed
    Result:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Triggers
## Number:  Test 17

    Purpose: Trigger on gizmo - Gizmo as source and empty cell (or Ball) as destination
    Input:   Build Mode -> Trigger on gizmo -> Click on the board
                                            -> Click on destination position

    Expected: Nothing changes and no connection has been made
    Actual:   Nothing changes and no connection has been made
    Result:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"
 
## Number:  Test 18

    Purpose: Trigger on gizmo - Gizmo as source and Gizmo as destination
    Input:   Build Mode -> Trigger on gizmo -> Click on the board
                                            -> Click on destination position

    Expected: Connection has been added (run game to check)
    Actual:   Connection has been added (run game to check)
    Result:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 19

    Purpose: Trigger on gizmo - Empty cell (or Ball) as source and empty cell (or Ball) as destination
    Input:   Build Mode -> Trigger on gizmo -> Click on the board
                                            -> Click on destination position

    Expected: Nothing changes and no connection has been made
    Actual:   Nothing changes and no connection has been made
    Result:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 20

    Purpose: Trigger on gizmo - Empty cell (or Ball) as source and Gizmo as destination
    Input:   Build Mode -> Trigger on gizmo -> Click on the board
                                            -> Click on destination position

    Expected: Destination Gizmo is waiting for a trigger operation
    Actual:   Destination Gizmo is waiting for a trigger operation
    Result:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 21

    Purpose: Trigger on keypress - Gizmo as source and key as destination
    Input:   Build Mode -> Trigger on keypress -> Click on the board
                                            -> Click on destination position

    Expected: Connection has been added (run game to check)
    Actual:   Connection has been added (run game to check)
    Result:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 22

    Purpose: Trigger on keypress - Empty cell as source (or Ball) and key as destination
    Input:   Build Mode -> Trigger on keypress -> Click on the board
                                               -> Click on destination position

    Expected: Nothing happens
    Actual:   Nothing happens
    Result:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 23

    Purpose: Trigger on outer wall - Gizmo as source
    Input:   Build Mode -> Trigger on outer wall -> Click on the board

    Expected: Connection has been added (run game to check)
    Actual:   Connection has been added (run game to check)
    Result:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 24

    Purpose: Trigger on outer wall - Empty cell as source (or Ball)
    Input:   Build Mode -> Trigger on outer wall -> Click on the board

    Expected: Nothing happens
    Actual:   Nothing happens
    Result:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Physics - Friction mu, friction mu2 and gravity
## Number:  Test 25

    Purpose: Modify friction MU
    Input:   Build Mode -> Modify input field next to label "Friction mu"

    Expected: New friction mu value is applied to the game
    Actual:   New friction mu value is applied to the game
    Result:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 26

    Purpose: Modify friction MU with wrong type (e.g. string) or out of range
    Input:   Build Mode -> Modify input field next to label "Friction mu"

    Expected: Old friction mu value is still applied to the game
    Actual:   Old friction mu value is still applied to the game
    Result:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 27

    Purpose: Modify gravity
    Input:   Build Mode -> Modify input field next to label "Gravity"

    Expected: New gravity value is applied to the game
    Actual:   New gravity value is applied to the game
    Result:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 28

    Purpose: Modify gravity with wrong type (e.g. string) or out of range
    Input:   Build Mode -> Modify input field next to label "Gravity"

    Expected: Old gravity value is still applied to the game
    Actual:   Old gravity value is still applied to the game
    Result:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 29

    Purpose: Modify friction MU2
    Input:   Build Mode -> Modify input field next to label "Friction mu2"

    Expected: New friction mu2 value is applied to the game
    Actual:   New friction mu2 value is applied to the game
    Result:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 30

    Purpose: Modify friction MU2 with wrong type (e.g. string) or out of range
    Input:   Build Mode -> Modify input field next to label "Friction mu2"

    Expected: Old friction mu2 value is still applied to the game
    Actual:   Old friction mu2 value is still applied to the game
    Result:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Switch between modes
## Number:  Test 31

    Purpose: Switch to run mode with a blank scenario
    Input:   Build Mode -> Run

    Expected: No gizmos are present and game is empty
    Actual:   No gizmos are present and game is empty
    Result:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 32

    Purpose: Switch to run mode with a scenario
    Input:   Build Mode -> Run

    Expected: Scenario is still present
    Actual:   Scenario is still present
    Result:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 33

    Purpose: Switch to build mode with a blank scenario
    Input:   Run Mode -> Build

    Expected: No gizmos are present and game is empty
    Actual:   No gizmos are present and game is empty
    Result:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 34

    Purpose: Switch to build mode with a scenario
    Input:   Run Mode -> Build

    Expected: Scenario is still present
    Actual:   Scenario is still present
    Result:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Run game
## Number:  Test 35

    Purpose: Press stop button
    Input:   Run Mode -> Run -> Stop
                                OR
                                default state when switching to Run Mode

    Expected: The game stops (i.e. no balls are moving and triggers are disabled)
    Actual:   The game stops (i.e. no balls are moving and triggers are disabled)
    RESULT:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 36

    Purpose: Press run button
    Input:   Run Mode -> Run

    Expected: The game runs (i.e. balls are moving and triggers are enabled)
    Actual:   The game stops (i.e. balls are moving and triggers are enabled)
    RESULT:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 37

    Purpose: Press tick button
    Input:   Run Mode -> Stop (default state when switching to Run Mode)
                      -> Tick

    Expected: The game runs for only one tick
    Actual:   The game runs for only one tick
    RESULT:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 38

    Purpose: Press/Release key to trigger connected gizmo
    Input:   Run Mode -> Run -> Press/Release Key

    Expected: Any gizmos connected to the key are triggered.
    Actual:   Any gizmos connected to the key are triggered.
    RESULT:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 39

    Purpose: Press/Release key to trigger connected gizmo
    Input:   Run Mode -> Run -> Press/Release a key not connected to any Gizmos

    Expected: No Gizmos are being triggered by the key press/release
    Actual:   No Gizmos are being triggered by the key press/release
    RESULT:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 40

    Purpose: Hit ball(s) with flipper
    Input:   Run Mode -> Run -> Press/Release Key to trigger flippers

    Expected: Collision between flipper and ball(s) and change of their velocity
    Actual:   Collision between flipper and ball(s) and change of their velocity
    RESULT:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Other functionalities
## Number:  Test 41

    Purpose: Load game board from file - Valid file
    Input:   Load -> Select file

    Expected: Scenario is fully and correctly loaded
    Actual:   Scenario is fully and correctly loaded
    RESULT:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 42

    Purpose: Load game board from file - Invalid file
    Input:   Load -> Select file

    Expected: Error with meaninful description of the issue
    Actual:   Error with meaninful description of the issue
    RESULT:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 43

    Purpose: Load game board from file - Abort operation
    Input:   Load -> Select file -> Close dialog to abort

    Expected: Nothing changes
    Actual:   Nothing changes
    RESULT:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 44

    Purpose: Load game board from file - Game runs in the background
    Input:   Run Mode (and on Run state)-> Load -> Select file

    Expected: In the background, the game normally runs
    Actual:   In the background, the game normally runs
    RESULT:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 45

    Purpose: Save game board to file - Standard - New file
    Input:   Save -> Standard ->
             Select location and name

    Expected: File is saved (load to double check)
    Actual:   File is saved (load to double check)
    RESULT:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 46

    Purpose: Save game board to file - Standard - Existing file
    Input:   Save -> Standard ->
             Select location and name
    
    Expected: File is saved and overwrite occured (load to double check)
    Actual:   File is saved and overwrite occured (load to double check)
    RESULT:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 47

    Purpose: Save game board to file - Extended - New file
    Input:   Save -> Extended ->
             Select location and name

    Expected: File is saved (load to double check)
    Actual:   File is saved (load to double check)
    RESULT:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 48

    Purpose: Save game board to file - Extended - Existing file
    Input:   Save -> Extended ->
             Select location and name

    Expected: File is saved and overwrite occured (load to double check)
    Actual:   File is saved and overwrite occured (load to double check)
    RESULT:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 49

    Purpose: Save game board to file - Abort
    Input:   Save -> Cancel or "X"
             OR
             Save -> Standard/Extended -> Cancel or "X"

    Expected: No saving occured and scenario still loaded
    Actual:   No saving occured and scenario still loaded
    RESULT:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 50

    Purpose: Save game board to file - Game runs in the background
    Input:   Run Mode (and on Run state)-> Save -> Select saver, location and name

    Expected: In the background, the game normally runs
    Actual:   In the background, the game normally runs
    RESULT:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 51

    Purpose: New game
    Input:   New

    Expected: It creates a new empty board
    Actual:   It creates a new empty board
    RESULT:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

## Number:  Test 52

    Purpose: Quit system
    Input:   Exit

    Expected: It exits the system
    Actual:   It exits the system
    RESULT:   PASS
    Checked by "Francesco Meggetto" on "7f592778" at "22/03/2017"

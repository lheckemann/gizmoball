Use Cases
==========

#### Use Case Name:
Add gizmo (bumper or flipper)
#### Preconditions:
User must be in build mode
#### Triggers:
User selects add gizmo option
#### Basic course of events:
1. User chooses their gizmo (either bumper or flipper)
2. User chooses a place on the board to place the gizmo
3. The place the user chose was unoccupied
4. The chosen gizmo is placed at the chosen location

### Alternate path(s)
3. The place the user chose was occupied by another gizmo
4. System informs user to choose an unoccupied space
5. Go to step 2 (Main path)

#### Use Case Name:
Add ball
#### Preconditions:
User must be in build mode
#### Triggers:
User selects add gizmo option & chose ball
#### Basic course of events:
1. User chooses a place on the board to place the ball
2. The place the user chose was unoccupied
3. Ball is placed at the chosen location

### Alternate path(s)
2. The place the user chose was occupied
3. System informs user to choose an unoccupied space
4. Go to step 1 (Main path)

#### Use Case Name:
Add absorber
#### Preconditions:
User must be in build mode
#### Triggers:
User selects add gizmo option & chose absorber
#### Basic course of events:
1. User chooses the starting position for the absorber
2. The place the user chose was unoccupied
3. User drags mouse to specify absorber size
4. User releases mouse in an unoccupied space
5. Absorber does not go over any occupied spaces
6. Absorber is placed in places chosen by the user

### Alternate path(s)
2. The starting place the user chose was occupied
3. System informs user to choose an unoccupied space
4. Go to step 1 (Main path)

4. User releases mouse in an occupied space
5. System informs user to choose an unoccupied space
6. Go to step 1 (Main path)

5. Absorber goes over occupied spaces
6. System informs user that the absorber must not go over any occupied spaces
7. Go to step 1 (Main path)

#### Use Case Name:
Delete gizmo (Bumper, Flipper, Ball, Absorber)
#### Preconditions:
User must be in build mode
#### Triggers:
User selects delete option
#### Basic course of events:
1. User selects the gizmo they would like to delete
2. System prompts user to confirm if they wish to proceed with deleting their selected gizmo
3. User confirms their decisions to delete the gizmo
4. The selected gizmo is removed from the board
5. If the gizmo is a bumper, flipper or absorber, all connections involving that gizmo are removed as well

### Alternate path(s)
3. User declines their decision to delete the gizmo
4. Go to step 1 (Main path)

#### Use Case Name:
Move gizmo (bumper, flipper, ball, absorber)
#### Preconditions:
User must be in build mode
#### Triggers:
User selects move option
#### Basic course of events:
1. User chooses the gizmo they would like to move
2. User drags chosen gizmo to an unoccupied space
3. Gizmo is repositioned successfully and is now in the new position chosen by the user

### Alternate path(s)
2. User drags gizmo into an occupied space
3. System informs user that a gizmo cannot be moved into an occupied space, prompting them to move the gizmo again
4. Go to step 1 (Main path)

#### Use Case Name:
Rotate gizmo
#### Preconditions:
User must be in build mode
#### Triggers:
User selects rotate option
#### Basic course of events:
1. User chooses the gizmo they would like to rotate
2. Gizmo is rotated successfully

### Alternate path(s)
1. User chooses a ball or absorber
2. System informs user that balls and absorbers cannot be rotated
3. Go to step 1 (Main path)

#### Use Case Name:
Modify gravity
#### Preconditions:
User must be in build mode
#### Triggers:
User clicks on gravity parameter field
#### Basic course of events:
1. User enters a value for gravity
2. Value user entered is valid
3. Gravity is successfully modified

### Alternate path(s)
2. Value user entered is non numeric
3. System informs user that gravity must be a numeric value
4. Go to step 1 (Main path)

2. Value user entered is out of range
3. System informs user that gravity must be within a specific range
4. Go to step 1 (Main path)

#### Use Case Name:
Modify friction
#### Preconditions:
User must be in build mode
#### Triggers:
User clicks on friction parameter field
#### Basic course of events:
1. User enters a value for friction
2. Value user entered is valid
3. Friction is successfully modified

### Alternate path(s)
2. Value user entered is non numeric
3. System informs user that friction must be a numeric value
4. Go to step 1 (Main path)

2. Value user entered is out of range
3. System informs user that friction must be within a specific range
4. Go to step 1 (Main path)

#### Use Case Name:
Connect key press to gizmo action
#### Preconditions:
User must be in build mode
#### Triggers:
User chooses connect option
#### Basic course of events:
1. System displays connections to user
2. User chooses to add a connection
3. User chooses the key they want to use
4. User chooses all of the gizmos to connect the key press to
5. User selects "Done" button to choose they are finished adding connections
6. Connections are successfully added

#### Use Case Name:
Connect gizmo to gizmo action
#### Preconditions:
User must be in build mode
#### Triggers:
User chooses connect option
#### Basic course of events:
1. System displays connections to user
2. User chooses to add a connection
3. User chooses the gizmo they want to be the trigger
4. User chooses all of the gizmos to connect the chosen gizmo to
5. User selects "Done" button to choose they are finished adding connections
6. Connections are successfully added

#### Use Case Name:
Remove connection
#### Preconditions:
User must be in build mode
#### Triggers:
User chooses connect option
#### Basic course of events:
1. System displays connections to user
2. User chooses to remove a connection
3. User chooses the connection they would like to remove
4. System prompts user to confirm that they wish to proceed with the removal of the connection
5. User confirms their choice
6. Connection is removed

### Alternate paths(s)
5. User declines their choice
6. Go to step 1 (Main path)

#### Use Case Name:
Switch to run mode
#### Preconditions:
User must be in build mode
#### Basic course of events:
1. User selects "Run" mode button
2. System switches from "Build" mode to "Run" mode

#### Use Case Name:
Press key to active gizmo
#### Preconditions:
User must be in run mode
#### Basic course of events:
1. User presses their chosen key
2. Any gizmos connected to the key are activated

### Use Case Name:
Hit ball with flipper
### Preconditions:
User must be in run mode
### Basic course of events:
1. User presses key to active flipper
2. User hits ball with flipper
3. Ball's velocity changes

### Use Case Name:
Load game board from file
### Triggers:
User chooses load option
### Basic course of events:
1. User chooses file to load game from
2. File user chose is a valid file
3. Game board is loaded successfully
### Alternative paths:
2. File user chose is invalid
3. System informs user the chosen file was invalid
4. Go to step 1 (Main path)

### Use Case Name:
Save game board to file
### Triggers:
User chooses save option
### Basic course of events:
1. User chooses location to save game
2. Game board is saved successfully

### Use Case Name:
Quit system
### Triggers:
User chooses quit option
### Basic course of events:
1. User is in run mode
2. System prompts user to confirm quit
3. User confirms quit
4. System is terminated
### Alternate paths:
1. User is in build mode
2. System prompts user to save their game board
3. User chooses location to save game board
4. Go to step 2 (Main path)

3. User declines quit

# project-catan (requires javaSE-13)

## Iteration 1: Basic info

- What user stories were completed this iteration?
  * Andy worked on the main User Story, the visual game board
  * Jakob set up all the data structures needed to run the game. These will likely evolve over time
  * Evan set up our testing system so we can validadate the functionality of our datastructures and eventually other functions

- What user stories do you intend to complete next iteration?
  * Making the game board interactive, which requires connecting the datastructures to the game board class
  * Implementing game logic
 
- Is there anything that you implemented but doesn't currently work?
  * the datastructures are implemented and work, but havent been connected to the game board
  * The visuals for the gameboard are in place, but nothing is interactive yet
  
## Iteration 2: Building Game Board Functionality

- What user stories were completed this iteration?
  * Andy adjusted the game board so it was represented by a hexagonal 2-D Array of Tiles
  * Jakob implemented logic that determines which tiles share a settlement placed on a tile corner
  * Evan added in arrays as tile member variables: these arrays will keep track of roads and settlements that are located on each tile.     Evan also added in logic so the game can determine if a road is shared by any two tiles
  
## Testing instructions

- If you're not familiar with Catan, check out the rules here: https://www.wired.co.uk/article/beginners-guide-to-settlers-of-catan

- Currently have a runGame.sh script that can be executed using "./runGame.sh" in command line. However, this does not seem to be working for some reason (must be debugged). Instead, please run our code through Eclipse:

- Open project in Eclipse --> src --> catan --> Board --> GameBoard.java

- Run GameBoard.java!

- Tests can be found in project --> tests and tests are available for each class/group


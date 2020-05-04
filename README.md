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
  * Andy adjusted the game board so it was represented by a hexagonal 2-D Array of Tiles as well as laid groundwork for final iteration by allowing tiles to be aware of the tiles adjacent to them.
  * Jakob implemented logic that handles paying out resources. Made GameBoard use data structures built in iteration 1
  * Evan added in arrays as tile member variables: these arrays will keep track of roads and settlements that are located on each tile.     Evan also added in logic so the game can determine if a road is shared by any two tiles
  
  - What user stories do you intend to complete next iteration?
 *  This iteration we finalized the groundwork necessary for the game logic to be created.  With our objects nearly complelety finished and our classes connect we plan on focusing on game and player logic in our final iteration.  This includes tasks such handling player turns, finalizing road and structure visualization on gameboard, and win conditions.
  
  
- Is there anything that you implemented but doesn't currently work?
* Roads and Structures are not yet drawn on the board.  Our game is also not currently functional as all our pieces are built but the game rules have not been impleted yet.
* Adjancy tests dont quite work yet 


Note From Andy: At the end of this iteration I ran into a lot of issues with Eclipse. I spent several hours on stack overflow trying to fix it but was unable to.  The temporary solution to this was copying relevant code to a new project and working from their and sending over my code manually to be merged. I will get this fixed for the next iteration.

## Testing instructions

- If you're not familiar with Catan, check out the rules here: https://www.wired.co.uk/article/beginners-guide-to-settlers-of-catan

- Currently have a runGame.sh script that can be executed using "./runGame.sh" in command line. However, this does not seem to be working for some reason (must be debugged). Instead, please run our code through Eclipse:

- Open project in Eclipse --> src --> catan --> Board --> GameBoard.java

- Run GameBoard.java!

- Tests can be found in project --> tests and tests are available for each class/group


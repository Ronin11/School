Patterns:
I used the facade pattern, because it made more sense that the GUI would interact with a sudoku solver, and not each of the individual parts.
I used the iterator pattern to make it easy to iterate through the sudoku boards, and find the next empty space.
I also used the strategy pattern, running whichever solver algorithm that is selected at the moment.

Usage:
For the difficulty test, please use the brute force solver. It will solve every solvable board you can throw at it.
It's not the most efficient but it took less a couple seconds to solve Puzzle-9x9-0401.txt located in the SamplePuzzle directory.

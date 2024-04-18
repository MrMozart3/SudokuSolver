# Sudoku Solver
##### Java Apllication for solving Sudoku quickly and reliably.
# Installation
1. Download using git clone or as zip from github
2. Install Java 22 jdk
3. Run the SudokuSolver.jar file in the main folder

# How To Use
Click on a cell to select it, then press a number from 1 to 9. Repeat this process for each cell you want to fill. Once you've entered all your numbers, click the "Solve" button.

# Features
#### MultiSolve
SudokuSolver offers MultiSolving capability. If the Sudoku board you've entered has multiple valid solutions, the application will display all of them.
> MultiSolve feature is limited to displaying up to 100 solutions.

#### Undo changes
If you make an error while entering a Sudoku puzzle and accidentally hit the "solve" button, you can revert to the previous state using the "back" button.
>  SudokuSolver is limited to remember up to 50 historical board states.

#### Clearing
If you want to clear the board, there's no need to remove numbers individually. Just click the "clear" button, and the board will reset instantly.
> Clearing board doesnt clear history, you can still go back to the state before clearing

# Images
<img src="assets/sudoku_solver_1.png?raw=true" width="400">
<img src="assets/sudoku_solver_2.png?raw=true" width="400">

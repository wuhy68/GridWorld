# MazeBug

## Introduction
This MazeBug can find a path to the endpoint in a maze. The endpoint is a red rock, which is unique in the grid.

## DFS
This using searching strategy helps us to find a path. We use *Stack<ArrayList<Locaiton>>* to mark the maze's path. If the bug can not move, then it will return to the last locaiton. Ant we have to pop the invalid location.

By this way, we always find a valid locaiton to move to and we return to the last location if the present locaiton is a dead road. Finally the bug can reach the end point.

### Add Probability
If we want to reduce the number of moves of the bug, we have to choose the **right** position as much as possible. An easy way to implement this is to store the times of directions that the bug has formely chosed. That means if the bug tend to move to the North, then it more likely for me to choose North as the next locaiton if 4 locaitons are available.  Noted that this is a probabilyt problem, that is, I am more likely to choose North, but it is also possible to choose another direction.

This method can efficiently reduce the move steps of the bug. However it does not always works. If the grid has no any **direction tendency**, this method may increase the steps unexpectedly.

### How to Run
You have to place this code file under the **projects** folder and run it as a project.

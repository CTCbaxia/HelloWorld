/*
HARD
489. Robot Room Cleaner

*/

/*
DFS(backtracking)
-When to finish?
-When we move back to the initial position and have tried all direction at that position

这题是遍历问题，首选DFS / BFS
由于robot只能一步一步移动，所以只能用DFS
要 DFS 遍历，需要有backtracking。在每一个 position 都走过了所有的 path之后，backtracking会带回到最初的起点

Note:
1. Set<String> visited 记录已经 visit 过的点，当到达已经 visit 过的点，直接返回
2. 要跟踪现在的 direction，robot 自己会移动，但是你要能够知道 如果 make move，new position是哪里

Time: O(4^N) 每个点都有四种选择，N 为所有有效点
Space: O(1)

*/
class Solution {
    public void cleanRoom(Robot robot) {
        int[] pos = {0, 0};
        Set<String> visited = new HashSet<>();
        backtracking(robot, pos, 0, visited);
        return;
    }
    private void backtracking(Robot robot, int[] pos, int iniDir, Set<String> visited){
        
        int[][] directions = {{0, -1},{-1, 0},{0, 1},{1, 0}};
        String posString = pos[0] + "," + pos[1];
        if(visited.contains(posString)){
            return;
        }else{
            visited.add(posString);
            robot.clean();
            
            for(int i = 0; i < 4; i++){
                int curDir = (iniDir + i) % 4;
                int[] dir = directions[curDir];//face to dir
                
                if(!robot.move()){
                    robot.turnRight();
                    continue;
                }else{
                    int[] newPos = {pos[0] + dir[0], pos[1] + dir[1]};
                    backtracking(robot, newPos, curDir, visited);
                    //need to move back to pos
                    robot.turnRight();
                    robot.turnRight();
                    robot.move();//move back to pos
                    robot.turnLeft();//turn to the next direction
                }
            }
        }
        
    }
}

/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */



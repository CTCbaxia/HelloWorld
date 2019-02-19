/*
353. Design Snake Game
*/
/*
Deque<Integer> snake: 方便 O(1) 顺移 node
Set<Integer> set: 方便 check 是否 crash

Time: O(1)
Space: O(n)
*/
class SnakeGame {
    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height 
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    Deque<Integer> snake;
    Set<Integer> set;
    int fi;
    int[][] food;
    int width;
    int height;
    Map<String, int[]> map;
    
    public SnakeGame(int width, int height, int[][] food) {
        fi = 0;//foodindex
        snake = new LinkedList<>();
        snake.offerFirst(0);
        set = new HashSet<>();
        set.add(0);
        
        this.food = food;
        this.width = width;
        this.height = height;
        
        map = new HashMap<>();
        map.put("U", new int[]{-1, 0});
        map.put("L", new int[]{0, -1});
        map.put("R", new int[]{0, 1});
        map.put("D", new int[]{1, 0});
    }
    
    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
        @return The game's score after the move. Return -1 if game over. 
        Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        int headx = snake.peekFirst() / width;
        int heady = snake.peekFirst() % width;
        
        headx += map.get(direction)[0];
        heady += map.get(direction)[1];
        
        int head = headx * width + heady;
        
        if(headx < 0 || headx >= height || heady < 0 || heady >= width){//outside
            return -1;
        }else if(fi < food.length && headx == food[fi][0] && heady == food[fi][1]){//eat food
            fi++;
        }else{//crash itself
            int tail = snake.peekLast();
            set.remove(tail);
            if(set.contains(head)) return -1;
        }
        snake.offerFirst(head);
        return fi;
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */



/*
Deque<Integer> snake: 方便 O(1) 顺移 node
Set<Integer> set: 方便 check 是否 crash

Time: O(1)
Space: O(n)
*/
class SnakeGame {
    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height 
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    Deque<Integer> snake;
    Set<Integer> set;
    int fi;
    int[][] food;
    int width;
    int height;
    Map<String, int[]> map;
    
    public SnakeGame(int width, int height, int[][] food) {
        fi = 0;//foodindex
        snake = new LinkedList<>();
        snake.offerFirst(0);
        set = new HashSet<>();
        set.add(0);
        
        this.food = food;
        this.width = width;
        this.height = height;
        
        map = new HashMap<>();
        map.put("U", new int[]{-1, 0});
        map.put("L", new int[]{0, -1});
        map.put("R", new int[]{0, 1});
        map.put("D", new int[]{1, 0});
    }
    
    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
        @return The game's score after the move. Return -1 if game over. 
        Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        int headx = snake.peekFirst() / width;
        int heady = snake.peekFirst() % width;
        
        headx += map.get(direction)[0];
        heady += map.get(direction)[1];
        
        int head = headx * width + heady;
        
        if(headx < 0 || headx >= height || heady < 0 || heady >= width){//outside
            return -1;
        }else if(fi < food.length && headx == food[fi][0] && heady == food[fi][1]){//eat food
            fi++;
        }else{//crash itself
            set.remove(snake.pollLast());//no food, need remove the tail from snake and set
            if(set.contains(head)) return -1;
        }
        snake.offerFirst(head);
        set.add(head);
        return fi;
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */










/*
List<int[]> 方便整体前后移动 body part
*/
class SnakeGame {
    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height 
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    int fi;
    List<int[]> snake;
    int[][] food;
    int width;
    int height;
    Map<String, int[]> map;
    
    public SnakeGame(int width, int height, int[][] food) {
        fi = 0;//foodindex
        snake = new ArrayList<>();
        snake.add(new int[]{0,0});
        
        this.food = food;
        this.width = width;
        this.height = height;
        map = new HashMap<>();
        map.put("U", new int[]{-1, 0});
        map.put("L", new int[]{0, -1});
        map.put("R", new int[]{0, 1});
        map.put("D", new int[]{1, 0});
    }
    
    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
        @return The game's score after the move. Return -1 if game over. 
        Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        int[] head = snake.get(0);
        int[] newHead = new int[2];
        newHead[0] = head[0] + map.get(direction)[0];
        newHead[1] = head[1] + map.get(direction)[1];
        
        if(newHead[0] < 0 || newHead[0] >= height || newHead[1] < 0 || newHead[1] >= width){//outside
            return -1;
        }else if(fi < food.length && newHead[0] == food[fi][0] && newHead[1] == food[fi][1]){//eat food
            fi++;
        }else{
            for(int i = 0; i < snake.size() - 1; i++){//crash itself
                if(newHead[0] == snake.get(i)[0] && newHead[1] == snake.get(i)[1])
                    return -1;
            }    
            snake.remove(snake.size() - 1);
        }
        snake.add(0, newHead);
        return fi;
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */






class SnakeGame {

    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height 
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    int fi;
    Snake snake;
    int[][] food;
    int width;
    int height;
    Map<String, int[]> map;
    public class Snake{
        int[] head;
        List<int[]> body;
        public Snake(int[] _head, List<int[]> _body){
            head = _head;
            body = _body;
        }
    }
    public SnakeGame(int width, int height, int[][] food) {
        fi = 0;//foodindex
        snake = new Snake(new int[]{0,0}, new ArrayList<int[]>());
        this.food = food;
        this.width = width;
        this.height = height;
        map = new HashMap<>();
        map.put("U", new int[]{-1, 0});
        map.put("L", new int[]{0, -1});
        map.put("R", new int[]{0, 1});
        map.put("D", new int[]{1, 0});
    }
    
    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
        @return The game's score after the move. Return -1 if game over. 
        Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        int[] dir = map.get(direction);
        
        //move head and body
        int[] tail = fi > 0 ? 
                        new int[]{snake.body.get(fi - 1)[0], snake.body.get(fi - 1)[1]} : 
                        new int[]{snake.head[0], snake.head[1]};
        for(int i = fi - 1; i >= 1; i--){
            snake.body.set(i, snake.body.get(i - 1));
        }
        if(snake.body.size() > 0)  snake.body.set(0, new int[]{snake.head[0], snake.head[1]});
        
        snake.head[0] += dir[0];
        snake.head[1] += dir[1];
        
        //check if crash itself
        for(int i = fi - 1; i >= 0; i--){
            
            if(snake.head[0] == snake.body.get(i)[0] && snake.head[1] == snake.body.get(i)[1])
                return -1;
        }
        
        if(snake.head[0] < 0 || snake.head[0] >= height || snake.head[1] < 0 || snake.head[1] >= width){
            return -1;
        }else if(fi < food.length && food[fi][0] == snake.head[0] && food[fi][1] == snake.head[1]){//eat food
            snake.body.add(tail);
            fi++;
        }
        return fi;
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */
/*
M
1138. Alphabet Board Path
*/
/*
用偏移量来算坐标
用移动优先级来对付 z 的特殊位置

Time: O(n)
Space: O(26)
*/
class Solution {
    public String alphabetBoardPath(String target) {
        int preX = 0, preY = 0;
        StringBuilder sb = new StringBuilder();
        for(char c : target.toCharArray()){
            int index = c - 'a';
            int curX = index / 5;
            int curY = index % 5;
            // check move left before move down
            // check move up before move right

            if(preY > curY){//move left
                for(int i = 0; i < preY - curY; i++){
                    sb.append('L');
                }
            }            
            if(curX > preX){//move down
                for(int i = 0; i < curX - preX; i++){
                    sb.append('D');
                }
            }
            if(preX > curX){//move up
                for(int i = 0; i < preX - curX; i++){
                    sb.append('U');
                }
            }
            if(curY > preY){//move right
                for(int i = 0; i < curY - preY; i++){
                    sb.append('R');
                }
            }
            sb.append('!');
            preX = curX;
            preY = curY;
        }
        return sb.toString();
    }

}



/*
找到diff
NOTE ❌：
1. 注意 edge case ("zdz")，注意处理 edge case 后的 edge case ("zz")

Time: O(n)
Space: O(26)
*/
class Solution {
    public String alphabetBoardPath(String target) {
        //build alphabet map
        Map<Character, int[]> map = new HashMap<>();
        String[] board = new String[]{"abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"};
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length(); j++){
                map.put(board[i].charAt(j), new int[]{i,j});
            }
        }
        
        //find target
        StringBuilder sb = new StringBuilder();
        char pre = 'a';
        for(char next : target.toCharArray()){
            if(next == 'z' && pre != 'z'){
                sb.append(findRoute(pre, 'u', map)).deleteCharAt(sb.length() - 1);//get path to 'u' and delete last '!'
                pre = 'u';
            }
            sb.append(findRoute(pre, next, map));
            pre = next;
        }
        return sb.toString();
        
    }
    private String findRoute(char from, char to, Map<Character, int[]> map){
        int xDiff = map.get(to)[0] - map.get(from)[0];
        int yDiff = map.get(to)[1] - map.get(from)[1];
        
        StringBuilder sb = new StringBuilder();
        
        char xMove = xDiff >= 0 ? 'D' : 'U';
        xDiff = Math.abs(xDiff);
        while(xDiff-- > 0){
            sb.append(xMove);
        }
        
        char yMove = yDiff >= 0 ? 'R' : 'L';
        yDiff = Math.abs(yDiff);
        while(yDiff-- > 0){
            sb.append(yMove);
        }
        
        return sb.append('!').toString();
    }
}

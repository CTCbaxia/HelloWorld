/*
Two sign to indicate level
1) start sign
2) end sign
    - if end sign == start sign, in the same level, the starter sign remains the same, do another sb
    - if end sign > start sign, go to the next level, may also return back to this path
    - if end sign < start sign, go to the previous level (don't go further with this path)
NOTE:
\t or \n is escaped characters in String to represent tab or new line. So length of \t only counts as 1.
\n 是一个 char，打印出来就是下一行的效果
\t 是一个 char，打印出来就是 tab 的效果
【NOTE】单独一个 \n 代表的是 root，不应该 count，在那之后的才是路径。只有带 \t 的才是文件路径

Time: O(n) only one pass the input
Space: O(1) or stack space O(n^2) for worst case need to stack input for all recursion
*/
class Solution {
    int max = 0;
    int index = 0;
    public int lengthLongestPath(String input) {
        findPath(input, "", "");
        return max;
    }
    // 需要 return 当前的 ender，这样回到上一级的时候人家才知道是不是在这一级继续下去，还是回到上一级
    private String findPath(String input, String preDir, String starter){//return ender
        StringBuilder sb = new StringBuilder();
        boolean isFile = false;
        while(index < input.length()){
            char c = input.charAt(index);
            if(c == '\n'){
                index++;//skip \n
                
                String curDir = preDir + sb.toString();
                //update max length directory
                if(isFile){
                    max = Math.max(max, curDir.length());
                    System.out.println(curDir);
                }
                //record next starter, which is also the ender for this dir
                StringBuilder enderSb = new StringBuilder();
                while(index < input.length() && input.charAt(index) == '\t'){
                    enderSb.append(input.charAt(index++));
                }

                if(starter.length() > enderSb.length()){//in the pre level ("\n" should go all the way to root)
                    return enderSb.toString();
                }else if (starter.length() < enderSb.length()){//in the next level
                    String ender = findPath(input, curDir + '/', enderSb.toString());
                    if(ender.length() < starter.length()) return ender;
                }  
                //if in the same level (starter.length() == enderSb.length()) or start a new 
                sb = new StringBuilder();
                isFile = false;
                
            }else{
                sb.append(c);
                if(c == '.') isFile = true;
                index++;
            }
        }
        String curDir = preDir + sb.toString();
        if(isFile){
            max = Math.max(max, curDir.length());
            System.out.println(curDir);
        }
        return starter;
    }
}

/*
752. Open the Lock

 */
/*
BFS 单向
Time: O(10^4 + Dead)
Space: O(10^4 + Dead)
*/
class Solution {
    public int openLock(String[] deadends, String target) {
        Set<String> dead = new HashSet<>();
        for(String s : deadends) dead.add(s);
        
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        if(dead.contains("0000")) return -1;
        
        int res = 0;
        q.offer("0000");
        while(!q.isEmpty()){
            res++;
            int size = q.size();
            for(int i = 0; i < size; i++){
                String s = q.poll();
                for(int j = 0; j < 4; j++){
                    int digit = Integer.parseInt(String.valueOf(s.charAt(j)));
                    int newDigitPos = (digit + 1 + 10) % 10;
                    int newDigitNeg = (digit - 1 + 10) % 10;
                    String newSPos = s.substring(0,j) + String.valueOf(newDigitPos) + s.substring(j + 1, 4);
                    String newSNeg = s.substring(0,j) + String.valueOf(newDigitNeg) + s.substring(j + 1, 4);
                    
                    if(newSPos.equals(target) || newSNeg.equals(target)) return res;
                    if(!dead.contains(newSPos) && !visited.contains(newSPos)){
                        q.offer(newSPos);
                        visited.add(newSPos);
                    } 
                    if(!dead.contains(newSNeg) && !visited.contains(newSNeg)){
                        q.offer(newSNeg);
                        visited.add(newSNeg);
                    } 
                   
                }
            }
        }
        return -1;
    }
}
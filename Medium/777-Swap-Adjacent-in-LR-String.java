/*
M
777. Swap Adjacent in LR String
*/
/*
😢TLE
BFS + visited

Time: O(n * n^2) if every letter changed
Space: O(n * n)
*/
class Solution {
    public boolean canTransform(String start, String end) {
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        q.offer(start);
        visited.add(start);
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                String s = q.poll();
                if(s.equals(end)) return true;
                replace("XL", "LX", s, q, visited);//replace XL - O(n^2)
                replace("RX", "XR", s, q, visited);//replace RX - O(n^2)
            }
        }
        return false;
    }
    private void replace(String from, String to, String s, Queue<String> q, Set<String> visited){
        int index = 0;
        while(index < s.length()){
            index = s.indexOf(from, index);
            if(index < 0) break;
            String newS = s.substring(0, index) + to + s.substring(index + 2);
            if(!visited.contains(newS)){
                q.offer(newS);
                visited.add(newS);
            }
            index++;
        }
        return;
    }

}

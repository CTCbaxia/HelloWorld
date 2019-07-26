/*
M
1087. Brace Expansion
*/
/*
Backtracking

Time: O(nlogn)
Space: O(n)
*/
class Solution {
    public String[] expand(String S) {
        List<String> list = new ArrayList<>();
        dfs(S, 0, "", list);
        String[] res = new String[list.size()];
        
        for(int i = 0 ; i < res.length; i++) res[i] = list.get(i);
        return res;
    }
    private void dfs(String S, int index, String prefix, List<String> list){
        if(index == S.length()){
            list.add(prefix);
            return;
        } 
    
        char c = S.charAt(index);
        if(c != '{') dfs(S, index + 1, prefix + c, list);
        else{
            PriorityQueue<Character> pq = new PriorityQueue<>();
            while(index < S.length() && S.charAt(index) != '}'){
                c = S.charAt(index++);
                if(c == '{' || c == ',') continue;
                pq.offer(c);
            }
            index++;
            while(!pq.isEmpty()){
                dfs(S, index, prefix + pq.poll(), list);
            }
        }
        return;
    }
}

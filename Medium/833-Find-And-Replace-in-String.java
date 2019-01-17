/*
MEDIUM
833. Find And Replace in String

*/
/*
HashMap for check

Time: O(S + N) 
Space: O(M)

Edge case: 
"abcd"
[0, 2]
["", "cd"]
["eee", "ffff"]

output: eeeabfff
*/
class Solution {
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        Map<Integer, Integer> map = new HashMap<>();
        //build map
        for(int i = 0; i < indexes.length; i++){
            map.put(indexes[i], i);//只需要匹配 S 里面的 index，以及 indexes 里面的index 就好
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < S.length(); i++){
            if(!map.containsKey(i)) sb.append(S.charAt(i));
            else{
                StringBuilder tmp = new StringBuilder();
                String source = sources[map.get(i)];
                String target = targets[map.get(i)];

                for(int j = 0; j < source.length(); j++, i++){
                    if(i >= S.length()) break;
                    tmp.append(S.charAt(i));
                }
                i--;
                if(tmp.toString().equals(source)){//if matched
                    sb.append(target);//replace by target
                }else{
                    sb.append(tmp.toString());//add original string
                }
            }
        }
        return sb.toString();
    }
}



class Solution {
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        Map<Integer, String[]> map = new HashMap<>();
        //build map
        for(int i = 0; i < indexes.length; i++){
            map.put(indexes[i], new String[]{sources[i], targets[i]});
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < S.length(); i++){
            if(!map.containsKey(i)) sb.append(S.charAt(i));
            else{
                StringBuilder tmp = new StringBuilder();
                String source = map.get(i)[0];
                String target = map.get(i)[1];
                map.remove(i);//如果有 "" -> "xxx"，则会一直停留在这里匹配，故要删除
                    
                for(int j = 0; j < source.length(); j++){
                    if(i >= S.length()) break;
                    tmp.append(S.charAt(i++));
                }
                i--;//因为外部会自增 1，所以这里要减 1 使得可以衔接上
                
                if(tmp.toString().equals(source)){//if matched
                    sb.append(target);//replace by target
                }else{
                    sb.append(tmp.toString());//add original string
                }
            }
        }
        return sb.toString();
    }
}
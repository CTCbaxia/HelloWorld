/*
HARD
316. Remove Duplicate Letters
https://leetcode.com/problems/remove-duplicate-letters/description/

TIME: 0716
RESULT: 
NOTES:
1. Map.put(k,v) 注意输入的顺序，如果有相同的 k ，则会在末尾输入新的 (k,v)，然后移除原有的 (k,v)
2. 输出的时候取最小位置的："abacb"
METHOD：

REFERENCE:
1.https://leetcode.com/problems/remove-duplicate-letters/discuss/76766/Easy-to-understand-iterative-Java-solution
*/
class Solution {
    public String removeDuplicateLetters(String s) {
        Map<Character, Integer> last = new HashMap<Character, Integer>();
        for(int i = 0; i < s.length(); i++){
            last.put(s.charAt(i), i);
        }
        int all = last.size();
        if(all == 0) return s;
        if(all == 1) return s.substring(0,1);

        int[] lastPos = new int[all];//2,3,4 (3 char in total)
        int j = 0;
        for(int i : last.values()){
            lastPos[j] = i;
            j++; 
        }
        Arrays.sort(lastPos);
        
        j = 0;//loop all different chars
        int i = 0;//loop the String s
        int nextPos = 0;//next loop to find the smallest
        String res = "";
        while(j < all){
            char aim = s.charAt(lastPos[j]);
            char minChar = aim;
            i = nextPos;
            System.out.println("nextPos: "+nextPos+"  lastPos[j]:" + lastPos[j]);
            while(i <= lastPos[j]){
                
                char pre = s.charAt(i);
                System.out.println("aim: " + aim + "   minChar:" + minChar + "   pre: "+pre);
                
                if(last.containsKey(pre) && pre < minChar){
                    minChar = pre;
                    nextPos = i;
                    //System.out.println("nextPos: "+nextPos);
                }
                if(pre == aim){
                    if(i < lastPos[j]) nextPos = Math.min(nextPos, i);
                    else nextPos = i;
                }
                i++;
            }
            //System.out.println("minChar:" + minChar);
            //add the minChar if res doesn't contain it
            if(last.containsKey(minChar)){
                res = res + minChar;
                last.remove(minChar);
            }
            //System.out.println("last:" + last);
            //move the pointer to the next substring
            if(minChar == aim) {
                j++;
                
            }
            nextPos++;

        }
        return res;
    }

}


"cbacdcbc"

public class Solution {

    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() <= 1) return s;

        Map<Character, Integer> lastPosMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            lastPosMap.put(s.charAt(i), i);
        }

        char[] result = new char[lastPosMap.size()];
        int begin = 0, end = findMinLastPos(lastPosMap);

        for (int i = 0; i < result.length; i++) {
            char minChar = 'z' + 1;
            for (int k = begin; k <= end; k++) {
                if (lastPosMap.containsKey(s.charAt(k)) && s.charAt(k) < minChar) {
                    minChar = s.charAt(k);
                    begin = k+1;
                }
            }

            result[i] = minChar;
            if (i == result.length-1) break;

            lastPosMap.remove(minChar);
            if (s.charAt(end) == minChar) end = findMinLastPos(lastPosMap);
        }

        return new String(result);
    }

    private int findMinLastPos(Map<Character, Integer> lastPosMap) {
        if (lastPosMap == null || lastPosMap.isEmpty()) return -1;
        int minLastPos = Integer.MAX_VALUE;
        for (int lastPos : lastPosMap.values()) {
             minLastPos = Math.min(minLastPos, lastPos);
        }
        return minLastPos;
    }

}
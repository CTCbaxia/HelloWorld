/*
MEDIUM
767. Reorganize String

TIME: 1110
RESULT: 
NOTES:
*/

/*
Greedy + PriorityQueue + StringBuilder
Similar to Task Scheduler, here is a 2-char slot without repeating
Greedy: Always put the character with most frequency to the 2-char slot
PriorityQueue: provide the two most frequent char
But if two are the same priority, put them using alphabetic order

Time: O(nlog26) = O(n)
Space: O(26)
*/
class Solution {
    public class wordFreq{
        char c;
        int freq;
        public wordFreq(char _c, int _freq){
            c = _c;
            freq = _freq;
        }
    }
    public String reorganizeString(String S) {
        PriorityQueue<wordFreq> pq = new PriorityQueue<wordFreq>(new Comparator<wordFreq>(){
            public int compare(wordFreq w1, wordFreq w2){
                if(w2.freq == w1.freq) return w1.c - w2.c;//if two are the same priority, put them using alphabetic order
                else return w2.freq - w1.freq;
            }
        });
        StringBuilder sb = new StringBuilder();
        char[] counter = new char[26];
        
        //count the frequency
        for(char c : S.toCharArray()){
            counter[c - 'a']++;
        }
        
        //push into pq sorted by freq
        for(char c = 'a'; c <= 'z'; c++){
            if(counter[c - 'a'] != 0){//important! : need to put existing letter
                pq.offer(new wordFreq(c, counter[c - 'a']));
            }
        }
        
        //build the string
        while(!pq.isEmpty()){
            //first char
            wordFreq w1 = pq.poll();
            sb.append(w1.c);
            w1.freq--;
            
            //second char
            if(!pq.isEmpty()){//need one more check because you poll one before
                wordFreq w2 = pq.poll();
                sb.append(w2.c);
                w2.freq--;
                if(w2.freq > 0) pq.offer(w2);
            }else{
                //if we only have one word left
                if(w1.freq > 0) return "";
            }
            if(w1.freq > 0) pq.offer(w1);//push back first char
        }
        return sb.toString();
    }
}
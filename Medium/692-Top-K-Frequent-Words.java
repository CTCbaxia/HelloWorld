/*
MEDIUM
692. Top K Frequent Words
https://leetcode.com/problems/top-k-frequent-words/description/

TIME: 0819 - 2h
RESULT: 51% - 25ms
NOTES: 
1. map.values() 的 inherit 特性：http://javatutorialhq.com/java/util/hashmap-class/values-method-example/
2. how to sort collections: https://stackoverflow.com/questions/2477261/how-to-sort-a-collectiont
*/
/*
Map + Bucket Sort

Time: O(n)
Space: O(n)
*/
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        List<String>[] bucket = new ArrayList[words.length + 1];
        Map<String, Integer> map = new HashMap<>();
        //build map
        for(String s : words){
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        //put bucket
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            int f = entry.getValue();
            if(bucket[f] == null) bucket[f] = new ArrayList<>();
            bucket[f].add(entry.getKey());
        }
        //add res
        List<String> res = new ArrayList<>();
        for(int f = words.length; f >= 0; f--){
            if(bucket[f] == null) continue;
            List<String> w = bucket[f];
            Collections.sort(w);
            for(int i = 0; i < w.size() && k > 0; i++, k--){
                res.add(w.get(i));
            }
        }
        return res;
    }
}
/*
Map + PQ
Time: O(nlogk)
Space: O(n)
*/
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        //build map
        for(String s : words){
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        //put PQ, frequent ascending, string descending
        PriorityQueue<String> pq = new PriorityQueue<String>(new Comparator<String>(){
            public int compare(String s1, String s2){
                if(map.get(s1) != map.get(s2)) return map.get(s1) - map.get(s2);
                else return s2.compareTo(s1);// > 0 则 s2 > s1
            }
        });
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            pq.offer(entry.getKey());
            if(pq.size() > k) pq.poll();
        }
        List<String> res = new ArrayList<>();
        while(!pq.isEmpty()){
            res.add(pq.poll());
        }
        Collections.reverse(res);//当前的答案是反的
        return res;
    }
}




















/*
PriorityQueue

Time: O(n) + O(nlogk) (worst case #key = n)
Space: O(n)
*/
class Solution {
    public class wordFreq{
        String word;
        int freq;
        public wordFreq(String _word, int _freq){
            word = _word;
            freq = _freq;
        }
    }
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        PriorityQueue<wordFreq> pq = new PriorityQueue<>(new Comparator<wordFreq>(){
            public int compare(wordFreq wf1, wordFreq wf2){
                if(wf1.freq == wf2.freq){   
                    return wf2.word.compareTo(wf1.word);//want the smaller to be poll later(小的字母相当于有更大的freq权重)
                }else{
                    return wf1.freq - wf2.freq;//should be min heap
                }
            }
        });
        for(String word : words){
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        for(String s : map.keySet()){
            pq.offer(new wordFreq(s, map.get(s)));
            if(pq.size() > k) pq.poll();
        }
        List<String> res = new ArrayList<>();
        while(!pq.isEmpty()){
            res.add(0, pq.poll().word);//这个操作是 O(n^2)
        }
        return res;
    }
}







//string comparison can be String1.compareTo(String2)
class Solution {
    public class wordFreq{
        String word;
        int freq;
        public wordFreq(String _word, int _freq){
            word = _word;
            freq = _freq;
        }
    }
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        PriorityQueue<wordFreq> pq = new PriorityQueue<>(new Comparator<wordFreq>(){
            public int compare(wordFreq wf1, wordFreq wf2){
                if(wf1.freq == wf2.freq){
                    int i = 0;
                    String w1 = wf1.word;
                    String w2 = wf2.word;
                    while(i < w1.length() && i < w2.length()){
                        if(w1.charAt(i) == w2.charAt(i)) i++;
                        else{
                            return w2.charAt(i) - w1.charAt(i);
                        } 
                    }
                    return w1.length() - w2.length();
                }else{
                    return wf1.freq - wf2.freq;//should be min heap
                }
            }
        });
        for(String word : words){
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        for(String s : map.keySet()){
            pq.offer(new wordFreq(s, map.get(s)));
            if(pq.size() > k) pq.poll();
        }
        List<String> res = new ArrayList<>();
        while(!pq.isEmpty()){
            res.add(0, pq.poll().word);
        }
        return res;
    }
}








/*
TIME: 0819 - 2h
RESULT: 51% - 25ms

THOUGHTS:
在第一次遍历中用 map 把每个词的频率存下来，根据 map 存储的目前频率图，判断目前该词应该在 List 的那一个频度层次上
然后对于 List 从频率最高值遍历，先排序该频度的 String，然后输出 result
*/
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        List<List<String>> freqList = new ArrayList<List<String>>();
        List<String> result = new ArrayList<String>();
        freqList.add(new ArrayList<String>());
        
        for(String s : words){
            if(map.containsKey(s)){
                int freq = map.get(s);
                freqList.get(freq).remove(s);
                if(freq == freqList.size() - 1){
                    freqList.add(new ArrayList<String>());
                }
                freqList.get(freq + 1).add(s);
            }else{
                freqList.get(0).add(s);
            }
            map.put(s, map.getOrDefault(s, -1) + 1);
        }
     
        int mostFreq = freqList.size() - 1;
        while(k > 0){
            Collections.sort(freqList.get(mostFreq));
            int wordNum = freqList.get(mostFreq).size();
            int count = 0;
            while(count < wordNum && count < k){
                result.add(freqList.get(mostFreq).get(count++));
            }
            mostFreq--;
            k = k - count;
        }
        return result;
    }
}
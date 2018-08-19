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
TIME: 0819 - 2h
RESULT: 51% - 25ms

THOUGHTS:
在第一次遍历中用 map 把每个词的频率存下来，根据 map 存储的目前频率图，判断目前该词应该在 List 的那一个频度层次上
然后对于 List 从频率最高值遍历，先排序该频度的 String，然后输出 result
*/
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        List<List> freqList = new ArrayList<List>();
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
                result.add((String) freqList.get(mostFreq).get(count++));
            }
            mostFreq--;
            k = k - count;
        }
        return result;
    }
}
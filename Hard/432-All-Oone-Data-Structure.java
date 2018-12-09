/*
HARD
432. All O`one Data Structure

TIME: 
RESULT: 
*/
/*
Frequency Bucket + Doubly Linked BucketNode

用 addBucket + removeBuckets 来负责list操作
剩下的就是分情况讨论：
1. inc
    1) 如果本来就有这个 key，更新 map， 更新 buckets 里面的位置（移除当前 bucket 里面的，移除注意check bucket）
    2) 如果本来没有，放入 map，放入 buckets
2. dec
    1) 如果之前有这个key
        a.如果 -1 之后还在 map 里，更新map, 更新 buckets 里面的位置（移除当前 bucket 里面的，移除注意check bucket）
        b.如果 -1 之后不再 map 里，移除map，移除当前 bucket 里面的，移除注意check bucket

Time: O(1)
Space: O(n)
*/
class AllOne {
    public class Bucket{
        int freq;
        Set<String> set;
        Bucket pre;
        Bucket next;
        public Bucket(int _freq){
            freq = _freq;
            set = new HashSet<String>();
        }
    }
    
    Map<String, Integer> map;
    Map<Integer, Bucket> buckets;
    Bucket head;
    Bucket tail;
    /** Initialize your data structure here. */
    public AllOne() {
        map = new HashMap<>();
        buckets = new HashMap<>();
        head = new Bucket(Integer.MIN_VALUE);
        tail = new Bucket(Integer.MAX_VALUE);
        head.next = tail;
        tail.pre = head;
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if(map.containsKey(key)){
            int freq = map.get(key);
            //update map
            map.put(key, freq + 1);
            
            //update bucket
            Bucket current = buckets.get(freq);
            Bucket next;
            if(buckets.containsKey(freq + 1)){//add to next bucket
                next = buckets.get(freq + 1);
            }else{
                next = addBucket(freq + 1, current);
            }
            next.set.add(key);
            current.set.remove(key);//remove from current bucket
            if(current.set.size() == 0) removeBucket(current);
        }else{
            //update map
            map.put(key, 1);
            
            //update bucket
            Bucket next;
            if(buckets.containsKey(1)){
                next = buckets.get(1);
            }else{
                next = addBucket(1, head);
            }
            next.set.add(key);
        }
    }
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if(map.containsKey(key)){
            int freq = map.get(key);
            Bucket current = buckets.get(freq);
            if(freq - 1 > 0){
                //update map
                map.put(key, freq - 1);
                //update bucket
                Bucket previous;
                if(buckets.containsKey(freq - 1)){
                    previous = buckets.get(freq - 1);
                }else{
                    previous = addBucket(freq - 1, current.pre);
                }
                previous.set.add(key);
                current.set.remove(key);
                if(current.set.size() == 0) removeBucket(current);
            }else{
                //update map
                map.remove(key);
                //update bucket
                current.set.remove(key);
                if(current.set.size() == 0) removeBucket(current);
            }
        }
    }

    private Bucket addBucket(int freq, Bucket previous){//create new bucket and update buckets map and buckets list
        Bucket b = new Bucket(freq);
        //add to Buckets map
        buckets.put(freq, b);
        //add to double list
        b.next = previous.next;
        previous.next = b;
        b.pre = previous;
        b.next.pre = b;
        return b;
    }
    private void removeBucket(Bucket b){//remove bucket and update buckets map and buckets list
        //remove from Buckets map
        buckets.remove(b.freq);
        //remove from double list
        b.pre.next = b.next;
        b.next.pre = b.pre;
        b.pre = null;
        b.next = null;
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        Bucket b = tail.pre;
        if(b == head) return "";
        else return b.set.iterator().next();
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        Bucket b = head.next;
        if(b == tail) return "";
        else return b.set.iterator().next();
    }
}                  
/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */













//bucket 思路不错，但是list还不够，需要用listnode那种，类似于LRU
class AllOne{
    Map<String, Integer> map;
    List<List<String>> freq;
    int min = 1;
    
    /** Initialize your data structure here. */
    public AllOne() {
        map = new HashMap<>();
        freq = new ArrayList<>();
        freq.add(null);
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        int f = map.getOrDefault(key, 0);
        //update map
        map.put(key, f + 1);
        
        //update freq
        if(f != 0){
            freq.get(f).remove(key);
            if(freq.get(f).size() == 0) min = f + 1;
        } 
        if(freq.size() == f + 1) freq.add(new ArrayList<String>());
        freq.get(f + 1).add(key);
        
        if(f + 1 < min) min = f + 1;
        return;
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if(!map.containsKey(key)) return;
        
        int f = map.get(key);
        //update map
        if(f - 1 > 0) map.put(key, f - 1);
        else map.remove(key);
        
        //update freq
        freq.get(f).remove(key);
        if(freq.get(f).size() == 0) freq.remove(f);//if no string at the freq, remove
        if(f - 1 > 0) freq.get(f - 1).add(key);
        
        if(min > f - 1) min = f - 1;
        return;
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if(freq.size() == 1) return "";
        return freq.get(freq.size() - 1).get(0);
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if(freq.size() == 1) return "";
        return freq.get(min).get(0);
    }    
}     

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
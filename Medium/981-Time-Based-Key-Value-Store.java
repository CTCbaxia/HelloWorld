/*
MEDIUM
981. Time Based Key-Value Store

*/
//assume that timestamp can only increase
/*
Map<Key, List<Timestamp, Value>>

Time: 
    - set: O(1)
    - get: O(n)
Space: 
    - set: O(n) (map)
    - get: O(n)
*/
class TimeMap {
    public class TimeValue{
        int timestamp;
        String value;
        public TimeValue(int t, String v){
            timestamp = t;
            value = v;
        }
    }
    /** Initialize your data structure here. */
    Map<String, List<TimeValue>> map;
    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if(!map.containsKey(key)) map.put(key, new ArrayList<>());
        map.get(key).add(new TimeValue(timestamp, value));
    }
    
    public String get(String key, int timestamp) {
        if(!map.containsKey(key)) return "";
        List<TimeValue> list = map.get(key);
        for(int i = list.size() - 1; i >= 0; i--){
            if(list.get(i).timestamp <= timestamp) return list.get(i).value;
        }
        return "";
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
/*
EASY
170. Two Sum III - Data structure design

RESULT: 
NOTES: 
*/
/*
Map

if less find, more add --- care more about add complexity

Time: O(n)
Space: O(n)
*/
class TwoSum {
    public Map<Integer, Integer> map;
    /** Initialize your data structure here. */
    public TwoSum() {
        map = new HashMap<Integer, Integer>();
    }
    
    /** Add the number to an internal data structure.. */
    public void add(int number) {
        if(map.containsKey(number)) map.put(number, 2);
        else map.put(number, 1);
    }
    
    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        
        for(int i : map.keySet()){
            if(value == 2 * i){
                if(map.get(i) == 2) return true;
            }else if(map.containsKey(value - i)){
                return true;
            } 
        }
        return false;
    }
}
/*
HashSet

if less find, more add --- care more about add complexity

Time: O(n)
Space: O(n)
*/

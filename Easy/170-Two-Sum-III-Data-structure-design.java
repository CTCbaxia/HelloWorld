/*
EASY
170. Two Sum III - Data structure design

RESULT: 
NOTES: 
*/
/*
Map : Quick Add

if less find, more add --- care more about add complexity

Time: Add: O(1) | Find O(n)
Space: O(n)
*/
class TwoSum {
    Map<Integer, Integer> map;
    /** Initialize your data structure here. */
    public TwoSum() {
        map = new HashMap<>();
    }
    
    /** Add the number to an internal data structure.. */
    public void add(int number) {
        map.put(number, map.getOrDefault(number, 0) + 1);
        
    }
    
    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        for(int n : map.keySet()){
            if(map.containsKey(value - n)){
                if(value - n == n && map.get(value - n) < 2) continue;
                else return true;
            }
        }
        return false;
    }
}
/*
HashSet: Quick Find - put every sum into the set

if less add, more find --- care more about find complexity

Time: add : O(n)  | find O(1)
Space: O(n)
*/

class TwoSum {
    Set<Integer> num;
    Set<Integer> sum;
    /** Initialize your data structure here. */
    public TwoSum() {
        num = new HashSet<>();
        sum = new HashSet<>();
    }
    
    /** Add the number to an internal data structure.. */
    public void add(int number) {
        Iterator<Integer> it = num.iterator();
        while(it.hasNext()){
            sum.add(number + it.next());
        }

        num.add(number);
    }
    
    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        return sum.contains(value);
    }
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */
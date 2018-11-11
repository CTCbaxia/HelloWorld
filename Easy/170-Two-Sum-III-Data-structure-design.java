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
HashSet: Quick Find

if less add, more find --- care more about find complexity

Time: add : O(n)  | find O(1)
Space: O(n)
*/
class TwoSum {
    public Set<Integer> num;
    public Set<Integer> sum;
    /** Initialize your data structure here. */
    public TwoSum() {
        num = new HashSet<Integer>();
        sum = new HashSet<Integer>();
    }
    
    /** Add the number to an internal data structure.. */
    public void add(int number) {
        Iterator<Integer> it = num.iterator();
        while(it.hasNext()){
            int tmp = it.next();
            sum.add(tmp + number);
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
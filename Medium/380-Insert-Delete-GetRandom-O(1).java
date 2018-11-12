/*
MEDIUM
380. Insert Delete GetRandom O(1)

TIME: 
RESULT: 
NOTES:

*/
/*
Map + List
Map: <Integer, Position in List> remember to update
List: to get random index number O(1)

Time:O(1)
Space: O(1)
*/
class RandomizedSet {
    Map<Integer, Integer> map;
    List<Integer> list;
    Random rdm;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<Integer, Integer>();
        list = new ArrayList<Integer>();
        rdm = new Random();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(map.containsKey(val)) return false;

        map.put(val, list.size());//update map
        list.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val)) return false;
        
        //move last one to where the index to be deleted is
        int index = map.get(val);
        int lastNum = list.get(list.size() - 1);
        list.set(index, lastNum);//update list
        map.put(lastNum, index);//update map
        
        //delete
        list.remove(list.size() - 1);
        map.remove(val);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int random = rdm.nextInt(list.size());
        return list.get(random);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
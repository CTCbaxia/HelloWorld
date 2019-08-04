/*
M
1146. Snapshot Array
Google
*/
/*
Using TreeMap<snapTime, value> for each index
so when given a snapTime, we can floor to the closet smallOrEqual snapTime its value get changed

Time: 
    Initialization: O(n)
    set: O(logn)
    snap: O(1)
    get: O(logn)
    
Space: O(n)
*/
class SnapshotArray {
    List<TreeMap<Integer, Integer>> snaps;//for every index, only update map when there is a value change
    int curSnapTime;
    public SnapshotArray(int length) {
        snaps = new ArrayList<>();
        curSnapTime = 0;
        for(int i = 0; i < length; i++){
            snaps.add(new TreeMap<Integer, Integer>());
            snaps.get(i).put(-1, 0);
        }
    }
    
    public void set(int index, int val) {
        snaps.get(index).put(curSnapTime, val);
    }
    
    public int snap() {
        curSnapTime++;
        return curSnapTime - 1; 
    }
    
    public int get(int index, int snap_id) {
        int snapTime = snaps.get(index).floorKey(snap_id);
        return snaps.get(index).get(snapTime);
    }
}

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */
 
 
 
 
 
/*
每次真的存所有的值
memory limit exceed
*/
class SnapshotArray {
    List<Integer> curVal;
    List<List<Integer>> snaps;
    public SnapshotArray(int length) {
        snaps = new ArrayList<>();
        curVal = new ArrayList<>();
        for(int i = 0; i < length; i++){
            curVal.add(0);
        }
    }
    
    public void set(int index, int val) {
        curVal.set(index,val);
    }
    
    public int snap() {
        snaps.add(new ArrayList<>(curVal));
        return snaps.size() - 1; 
    }
    
    public int get(int index, int snap_id) {
        return snaps.get(snap_id).get(index);
    }
}

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */

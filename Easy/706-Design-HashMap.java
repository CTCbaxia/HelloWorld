/*
EASY
706. Design HashMap

*/
/*
Java HashMap 的实现使用 ListNode[] 实现 -- 类似于 key 的 bucket
根据 key % size 得到 index，根据 index 得到 pending ListNode 的 dummy head
从该 dummy head 开始顺着 ListNode 走，直到找到真正的 key。返回 pre 以便后续操作（删除，增加）

Time: O(1 ~ n)
Space: O(10000)

*/
class MyHashMap {
    ListNode[] nodes;
    int size;
    /** Initialize your data structure here. */
    public MyHashMap() {
        size = 10000;
        nodes = new ListNode[size];
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        int i = key % size;
        if(nodes[i] == null) nodes[i] = new ListNode(-1,-1);
        
        ListNode pre = findPre(nodes[i], key);//find the key position for this node[i]
        if(pre.next == null) pre.next = new ListNode(key, value);
        else pre.next.val = value;
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int i = key % size;
        if(nodes[i] == null) return -1;
        
        ListNode pre = findPre(nodes[i], key);
        if(pre.next == null) return -1;
        else return pre.next.val;
        
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int i = key % size;
        if(nodes[i] == null) return;
        
        ListNode pre = findPre(nodes[i], key);
        if(pre.next == null) return;
        else pre.next = pre.next.next;
    }
    
    private ListNode findPre(ListNode head, int key){
        ListNode pre = null;
        ListNode node = head;
        while(node != null && node.key != key){
            pre = node;
            node = node.next;
        }
        return pre;
    }
    private class ListNode{
        int key;
        int val;
        ListNode next;
        public ListNode(int _key, int _val){
            key = _key;
            val = _val;
        }
    }
    
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */


 
/*
List

Time:
put: O(n)
get: O(n)
remove: O(n)
*/
class MyHashMap {
    List<Integer> keys;
    List<Integer> values;
    /** Initialize your data structure here. */
    public MyHashMap() {
        keys = new ArrayList<>();
        values = new ArrayList<>();
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        for(int i = 0; i < keys.size(); i++){
            if(keys.get(i) == key){
                values.set(i, value);
                return;
            } 
        }
        keys.add(key);
        values.add(value);
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        for(int i = 0; i < keys.size(); i++){
            if(keys.get(i) == key) return values.get(i);
        }
        return -1;
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        for(int i = 0; i < keys.size(); i++){
            if(keys.get(i) == key){
                keys.remove(i);
                values.remove(i);
            }
        }
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
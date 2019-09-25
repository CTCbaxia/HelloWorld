/*
HARD
146. LRU Cache

TIME: 
RESULT: 
*/
/*
DoublelyLinkedList

Time: O(1)
Space: O(capacity)
*/
class LRUCache {
    
    class DLinkedNode{
        DLinkedNode pre;
        DLinkedNode next;
        int key;
        int val;
        public DLinkedNode(int _key, int _val){
            val = _val;
            key = _key;
        }
    }
    private void addToHead(DLinkedNode node){
        head.next.pre = node;
        node.next = head.next;
        
        head.next = node;
        node.pre = head;
    }
    private void removeNode(DLinkedNode node){
        node.pre.next = node.next;
        node.next.pre = node.pre;
        
        node.pre = null;
        node.next = null;
    }
    
    
    DLinkedNode head, tail;
    Map<Integer, DLinkedNode> map;
    int cap;
    public LRUCache(int capacity) {
        head = new DLinkedNode(0,0);
        tail = new DLinkedNode(0,0);
        head.next = tail;
        tail.pre = head;
        
        map = new HashMap<>();
        cap = capacity;
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            //get val
            DLinkedNode node = map.get(key);
            //update node
            removeNode(node);
            addToHead(node);
            return node.val;
        } 
        else return -1;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            //update val
            DLinkedNode node = map.get(key);
            node.val = value;
            //update node
            removeNode(node);
            addToHead(node); 
        }else{
            DLinkedNode node = new DLinkedNode(key, value);
            map.put(key, node);//remember to add to map
            addToHead(node);
            
            //check capacity
            if(map.size() > cap){
                DLinkedNode last = tail.pre;
                map.remove(last.key);
                removeNode(last);
            }
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

 
/*
Double Linked Node + HashMap
重点是每次能够很快将一个中间的点，移到最前。
就用 map 帮我们 get 那个点，然后用 Double Linked Node 的操作快速添加和删除
Double Linked Node : 实现移除，加到顶点 O(1)
Map<Integer, DLinkedNode>: 实现 get O(1)

这题用 Double Linked Node 而不用 List<Node> 的原因是 list 的 remove(node) 和 add(0,node) 操作是 O(n) 的
如果就用 Double Linked Node 想要 O(n) 遍历，就去掉 map


Time: O(1)
Space: O(1)
*/
class LRUCache {
    private class DLinkedList{
        int key;
        int value;
        DLinkedList pre;
        DLinkedList next;
        public DLinkedList(int _key, int _value){
            key = _key;
            value = _value;
        }
    }
    private void addToHead(DLinkedList node){
        node.next = head.next;
        head.next.pre = node;
        
        head.next = node;
        node.pre = head;
    }
    private void remove(DLinkedList node){
        node.pre.next = node.next;
        node.next.pre = node.pre;        
    }
        
    DLinkedList head;
    DLinkedList tail;
    Map<Integer, DLinkedList> map;//可以将 put,get 变成 O(1) 不让还是要 O(n) 遍历 DLinkedList
    int maxSize;
    public LRUCache(int capacity) {
        head = new DLinkedList(0,0);
        tail = new DLinkedList(0,0);
        head.next = tail;
        tail.pre = head;
        map = new HashMap<>();
        maxSize = capacity;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        DLinkedList node = map.get(key);
        remove(node);
        addToHead(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            DLinkedList node = map.get(key);
            node.value = value;
            remove(node);
            addToHead(node);
        }else{
            DLinkedList node = new DLinkedList(key, value);
            map.put(key, node);
            addToHead(node);
            
            if(map.size() > maxSize){
                map.remove(tail.pre.key);
                remove(tail.pre);
            }
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */









 
/*
Double Linked Node + HashMap
重点是每次能够很快将一个中间的点，移到最前。
就用 map 帮我们 get 那个点，然后用 Double Linked Node 的操作快速添加和删除
Double Linked Node : 实现移除，加到顶点 O(1)
Map<Integer, DLinkedNode>: 实现 get O(1)

这题用 Double Linked Node 而不用 List<Node> 的原因是 list 的 remove(node) 和 add(0,node) 操作是 O(n) 的

Time: O(1)
Space: O(1)
*/
class LRUCache {
    //create the DLinkedNode class
    public class DLinkedNode{
        int key;
        int value;
        DLinkedNode pre;
        DLinkedNode next;
        public DLinkedNode(int _key, int _value){
            key = _key;
            value = _value;
        }

    }
    public void addToHead(DLinkedNode node){
        node.next = head.next;
        node.next.pre = node;
        head.next = node;
        node.pre = head;
    }
    public void deleteNode(DLinkedNode node){
        node.next.pre = node.pre;
        node.pre.next = node.next;
    }
    
    //start the LRU cache
    DLinkedNode head;
    DLinkedNode tail;
    Map<Integer, DLinkedNode> map;
    int capacity;
    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        head = new DLinkedNode(0, 0);
        tail = new DLinkedNode(0, 0);
        head.next = tail;
        head.pre = null;
        tail.pre = head;
        tail.next = null;
    }
      
    public int get(int key) {
        if(map.containsKey(key)){
            DLinkedNode node = map.get(key);
            deleteNode(node);
            addToHead(node);
            return node.value;
        }else{
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            DLinkedNode node = map.get(key);
            node.value = value;
            deleteNode(node);
            addToHead(node);            
        }else{
            DLinkedNode node = new DLinkedNode(key, value);
            map.put(key, node);
            if(map.size() <= capacity){
                addToHead(node);
            }else{
                map.remove(tail.pre.key);
                deleteNode(tail.pre);
                addToHead(node);
            }
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
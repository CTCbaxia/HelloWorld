/*
HARD
146. LRU Cache

TIME: 
RESULT: 
*/
/*
Double Linked Node + HashMap
重点是每次能够很快将一个中间的点，移到最前。
就用 map 帮我们 get 那个点，然后用 Double Linked Node 的操作快速添加和删除
Double Linked Node : 实现移除，加到顶点 O(1)
Map<Integer, DLinkedNode>: 实现 get O(1)

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
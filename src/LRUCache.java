import java.util.HashMap;
import java.util.LinkedList;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 *
 * The cache is initialized with a positive capacity.
 */
class LRUCache {
    //需要使用key value存储。map中获取到的key
    class Node{
        int key ;
        int value;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    private LinkedList<Node> list = new LinkedList();
    private HashMap<Integer,Node> map = new HashMap();
    private int capacity;

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }else {
            Node node = map.get(key);
            //链表中放到首位
            list.remove(node);
            list.addFirst(node);
            return node.value;
        }
    }

    public void put(int key, int value) {
        //如果存在，链表放置首位，否则，链表不满直接添加，如果满了，链表删除最后一个，map也清空，再添加新数据。
        Node node = new Node(key, value);
        if (!map.containsKey(key)) {
            if (list.size() == capacity) {
                //如果满了，链表删除最后一个，map也清空
                Node last = list.getLast();
                list.removeLast();
                map.remove(last.key);
            }
            //添加新数据
            list.addFirst(node);
            map.put(key, node);
        }else {
            //如果存在，链表放置首位
            //注意：这里避免hashcode不一致
            list.remove(map.get(key));
            list.addFirst(node);
            map.put(key, node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
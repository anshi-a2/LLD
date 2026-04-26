import java.util.HashMap;
import java.util.Map;

class LRUCache {
    private int cap;
    private Map<Integer, Node> cacheMap;
    private Node oldest;
    private Node newest;

    public LRUCache(int cap) {
        this.cap = cap;
        this.cacheMap = new HashMap<>();
        oldest = new Node(0,0);
        newest = new Node(0, 0);
        oldest.next=newest;
        newest.prev=oldest;
    }

    public int get(int key) {
        if(!cacheMap.containsKey(key)) {
            return -1;

        }
        Node n = cacheMap.get(key);
        remove(n);
        insert(n);
        return n.val;
    }

    public void put(int key, int val) {
        if (cacheMap.containsKey(key)) {
            Node existingNode = cacheMap.get(key);
            remove(existingNode);
        }
        Node newNode = new Node(key, val);
        cacheMap.put(key, newNode);
        insert(newNode);
        if(cacheMap.size()>cap){
            Node lru = oldest.next;
            remove(lru);
            cacheMap.remove(lru.key);
        }
    }

    public void insert(Node n) {
        Node mru = newest.prev;
        mru.next = n;
        n.prev = mru;
        n.next = newest;
        newest.prev = n;
    }

    public void remove(Node n) {
        Node prv = n.prev;
        Node nxt = n.next;
        prv.next = nxt;
        nxt.prev = prv;
    }

}
class LRUCache {

    private int cap;                          // Maximum capacity
    private Map<Integer, Node> cache;         // Key → Node
    private Node oldest;                      // Dummy head (LRU end)
    private Node latest;                      // Dummy tail (MRU end)

    // Constructor
    public LRUCache(int capacity) {
        this.cap = capacity;
        this.cache = new HashMap<>();

        oldest = new Node(0, 0);  // Dummy head
        latest = new Node(0, 0);  // Dummy tail

        oldest.next = latest;
        latest.prev = oldest;
    }

    // Get value by key
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }

        Node node = cache.get(key);
        remove(node);   // Remove from current position
        insert(node);   // Move to MRU
        return node.val;
    }

    // Put key-value pair
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node oldNode = cache.get(key);
            remove(oldNode);
        }

        Node newNode = new Node(key, value);
        cache.put(key, newNode);
        insert(newNode);

        // Evict LRU if capacity exceeded
        if (cache.size() > cap) {
            Node lru = oldest.next;   // First real node
            remove(lru);
            cache.remove(lru.key);
        }
    }

    // Insert node before dummy tail (MRU)
    private void insert(Node node) {
        Node prev = latest.prev;

        prev.next = node;
        node.prev = prev;

        node.next = latest;
        latest.prev = node;
    }

    // Remove node from list
    private void remove(Node node) {
        Node prev = node.prev;
        Node next = node.next;

        prev.next = next;
        next.prev = prev;
    }
}
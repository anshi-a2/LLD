class Node {
    int key;
    int value;
    int freq;
    Node prev;
    Node next;

    Node(int key, int value) {
        this.key = key;
        this.value = value;
        this.freq = 1;
    }
}

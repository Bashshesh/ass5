import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinarySearchTree<K extends Comparable<K>, V> implements Iterable<Entry<K, V>> {

    private Node root;
    private int size;

    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public void put(K key, V value) {
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null.");

        root = put(root, key, value);
    }

    private Node put(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0)
            node.left = put(node.left, key, value);
        else if (cmp > 0)
            node.right = put(node.right, key, value);
        else
            node.value = value;

        return node;
    }

    public V get(K key) {
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null.");

        Node node = get(root, key);
        return node != null ? node.value : null;
    }

    private Node get(Node node, K key) {
        if (node == null)
            return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0)
            return get(node.left, key);
        else if (cmp > 0)
            return get(node.right, key);
        else
            return node;
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    public int size() {
        return size;
    }

    public Iterator<Entry<K, V>> iterator() {
        return new InOrderIterator();
    }

    private class InOrderIterator implements Iterator<Entry<K, V>> {
        private Stack<Node> stack;

        public InOrderIterator() {
            stack = new Stack<>();
            pushAll(root);
        }

        private void pushAll(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public Entry<K, V> next() {
            if (!hasNext())
                throw new NoSuchElementException();

            Node node = stack.pop();
            pushAll(node.right);

            return new Entry<>(node.key, node.value);
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        BinarySearchTree<Integer, String> tree = new BinarySearchTree<>();

        public void showAll(){
            for (Entry<Integer, String> entry : tree) {
                System.out.println("Key:" + entry.key + " Value:" + entry.value);
            }
        }
    }


}

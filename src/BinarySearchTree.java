import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinarySearchTree<K extends Comparable<K>, V> implements Iterable<BinarySearchTree.elem<K, V>> {
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
        }
    }

    public static class elem<K, V> {
        public K key;
        public V value;

        public elem(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public void put(K key, V value) {
        if (root == null) {
            root = new Node(key, value);
            size = 1;
        } else {
            put(root, key, value);
        }
    }

    private void put(Node node, K key, V value) {
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            if (node.left == null) {
                node.left = new Node(key, value);
                size++;
            } else {
                put(node.left, key, value);
            }
        } else if (cmp > 0) {
            if (node.right == null) {
                node.right = new Node(key, value);
                size++;
            } else {
                put(node.right, key, value);
            }
        } else {
            // Update the value if the key already exists
            node.value = value;
        }
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return (node != null) ? node.value : null;
    }

    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return getNode(node.left, key);
        } else if (cmp > 0) {
            return getNode(node.right, key);
        } else {
            return node;
        }
    }
    public void remove(K key) {
        root = deleteNode(root, key);
    }

    private Node deleteNode(Node node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = deleteNode(node.left, key);
        } else if (cmp > 0) {
            node.right = deleteNode(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            Node successor = findMinNode(node.right);
            node.key = successor.key;
            node.value = successor.value;
            node.right = deleteNode(node.right, successor.key);
        }
        return node;
    }
    private Node findMinNode(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<elem<K, V>> iterator() {
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<elem<K, V>> {
        private Node current;
        private Stack<Node> stack;

        public BSTIterator() {
            stack = new Stack<>();
            current = root;
        }

        @Override
        public boolean hasNext() {
            return (current != null || !stack.isEmpty());
        }

        @Override
        public elem<K, V> next() {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            if (stack.isEmpty()) {
                throw new NoSuchElementException();
            }

            Node node = stack.pop();
            current = node.right;

            return new elem<>(node.key, node.value);
        }
    }
}

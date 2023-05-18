import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree <K extends Comparable<K>, V> implements Iterable<K>{
    private class Node <K extends Comparable<K>, V>{
        private K key;
        private V value;
        private Node left, right;
        public Node(K key, V value){
            this.key=key;
            this.value=value;
        }
    }

    private Node<K, V> root;
    private int length;

    public void put(K key, V value){
        Node<K, V> newNode=new Node(key, value);
        root=put(root, newNode);
    }

    private Node<K, V> put(Node<K, V> current, Node<K, V> node){
        if(current==null){
            return node;
        }
        if(node.key.compareTo(current.key) > 0){
            current.right=put(current.right, node);
        }
        else if(node.key.compareTo(current.key) < 0){
            current.left=put(current.left, node);
        }
        length++;
        return current;
    }

    public V get(K key){
        return get(root, key);
    }

    private V get(Node<K, V> current, K key){
        if (current==null) return null;
        if(key.compareTo(current.key) > 0){
            current.value = (V) get(current.right, key);
        }else if(key.compareTo(current.key) < 0){
            current.value = (V) get(current.left, key);
        }
        return current.value;
    }

    public void remove(K key){
        root=remove(root, key);
    }

    private Node<K, V> remove(Node<K, V> current, K key) {
        if(current == null) return current;
        if(key.compareTo(current.key)<0) {
            current.left = remove(current.left, key);
        } else if(key.compareTo(current.key)>0) {
            current.right = remove(current.right, key);
        } else {
            if(current.left == null || current.right == null) {
                Node<K, V> temp = current.left != null ? current.left : current.right;
                if(temp == null) {
                    return null;
                } else {
                    return temp;
                }
            } else {
                Node successor = getSuccessor(current);
                current.value = (V) successor.value;

                current.right = remove(current.right, (K) successor.key);
                return current;
            }
        }
        return current;
    }

    private Node<K, V> getSuccessor(Node<K, V> node) {
        if(node == null) {
            return null;
        }
        Node temp = node.right;
        while(temp.left != null) {
            temp = temp.left;
        }
        return temp;
    }

    @Override
    public Iterator<K> iterator() {
        Queue<K> q= new LinkedList<>();
        inorder(root, q);
        return q.iterator();
    }

    private void inorder(Node<K, V> x, Queue<K> q){
        if(x==null) return;
        inorder(x.left, q);
        q.add(x.key);
        inorder(x.right, q);
    }
}
public class Main {
    public static void main(String[] args) {
        /*BinaryTree tree = new BinaryTree();
        tree.insert(3);
        tree.insert(4);
        tree.insert(1);
        tree.insert(15);
        tree.insert(5);
        tree.insert(2);
        tree.insert(8);
        tree.insert(7);
        tree.inOrder();
        tree.getValue(7);*/
        // ignore it, it is just default BST without iterators, but i do that task which you give in a class

        BinarySearchTree<Integer, Integer> tree=new BinarySearchTree<>();
        tree.put(1, 1);
        tree.put(4, 2);
        tree.put(7, 3);
        tree.put(2, 4);


    }
}
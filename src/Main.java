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

        BinarySearchTree<Integer, String> tree = new BinarySearchTree<>();

        tree.put(5, "1");
        tree.put(2, "2");
        tree.put(8, "3");
        tree.put(1, "4");
        tree.put(3, "5");
        tree.put(7, "6");
        tree.put(9, "7");

        for (BinarySearchTree.elem<Integer, String> elem : tree) {
            System.out.println("key is " + elem.key + " and value is " + elem.value);
        }
    }
}
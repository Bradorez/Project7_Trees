import java.util.Iterator;
import java.util.Stack;

public class BinaryTree<T> implements Iterable<T>
{
    public class Node<T> {
        T data;
        Node<T> left;
        Node<T> right;

        Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    public Integer d;

    private Node<T> root;

    BinaryTree() {
        root = null;
    }

    BinaryTree(T rootData) {
        root = new Node<>(rootData);
    }

    BinaryTree(T rootData, BinaryTree<T> left, BinaryTree<T> right) {
        root = new Node<>(rootData);
        if (left!= null){
        root.left = left.root;}
        if (right!= null){
        root.right = right.root;}
    }
    /*BinaryTree<Integer> sampleTree = new BinaryTree<>(
            1,
            new BinaryTree<>(0),
            new BinaryTree<>(2)
    );*/
    public interface Visitor<T> {
        void visit(T data);
    }

    public void inorderTraverse(Visitor<T> visitor) {
        inorderTraverse(root, visitor);
    }

    private void inorderTraverse(Node<T> node, Visitor<T> visitor) {
        if(node != null) {
            inorderTraverse(node.left, visitor);
            visitor.visit(node.data);
            inorderTraverse(node.right, visitor);
        }
    }
    public Iterator<T> iterator() {
        return new InOrderIterator(root);
    }
    private class InOrderIterator implements Iterator<T> {


        Stack<Node<T>> stack = new Stack<>();

        public InOrderIterator(Node<T> root) {
            pushAll(root);
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public T next() {
            Node<T> node = stack.pop();
            pushAll(node.right);
            return node.data;
        }

        private void pushAll(Node<T> node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
    }
    public int getSize(){
        return getSizeOfNode(root);
    }

    private int getSizeOfNode(Node<T> node){
        if (node == null) return 0;
        else return(getSizeOfNode(node.left) + 1 + getSizeOfNode(node.right));
    }
    public static void main(String[] args)
    {
        BinaryTree<Integer> tree = new BinaryTree<>(
                50,
                new BinaryTree<>(
                        17,
                        new BinaryTree<>(12,
                                new BinaryTree<>(9),
                                new BinaryTree<>(14)
                        ),
                        new BinaryTree<>(23,
                                new BinaryTree<>(19),
                                null)
                ),
                new BinaryTree<>(
                        72,
                        new BinaryTree<>(54,
                                null,
                                new BinaryTree<>(67)
                        ),
                        new BinaryTree<>(76)
                )
        );
        for (Integer a : tree) {
            System.out.println(a);
        }


    }




}

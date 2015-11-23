public class Node<E> {
    private E element;
    private Node link;

    public Node(E elem) {
        this.element = elem;
        this.link = null;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public Node getLink() {
        return link;
    }


    public void setLink(Node link) {
        this.link = link;
    }
}

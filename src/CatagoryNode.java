/**
 * Created by shuai on 10/25/2015.
 */
public class CatagoryNode<E> {
    private E element;
    private String Catagory;
    private Node link;

    public CatagoryNode(E elem) {
        this.element = elem;
        this.link = null;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public String getCatagory() {
        return Catagory;
    }

    public void setCatagory(String catagory) {
        this.Catagory = catagory;
    }

    public Node getLink() {
        return link;
    }

    public void setLink(Node link) {
        this.link = link;
    }
}

import javax.swing.table.TableColumn;

public class FoodCategoryLinkedList<T> {
    private Node head, tail;
    private int numCategory;
    private T element;
    private String Catagory = "";

    public FoodCategoryLinkedList() {
        head = null;
        tail = null;
        numCategory = 0;
    }

    public FoodCategoryLinkedList(T element) {
        head = null;
        tail = null;
        element = this.element;
        numCategory = 0;
    }

    public void insert(T element) {
        Node newNode = new Node(element);
        if (head == null)
            head = newNode;
        else {
            tail.setLink(newNode);
        }
        tail = newNode;
        numCategory++;
    }

    public void update(T element, T newElement) {
        Node current = head;
        while (current != null) {
            if (current.getElement().equals(element)) {
                current.setElement(newElement);
            }
        }
    }

    public boolean search(T element) {
        boolean exists = false;
        Node current = head;
        while (current != null) {
            if (current.getElement().equals(element)) {
                exists = true;
                return exists;
            }
            current = current.getLink();
        }
        return exists;
    }

    public boolean searchCatagory(String catagory) {
        boolean exists = false;
        Node current = head;
        while (current != null) {
            CatagoryNode temp = (CatagoryNode)current.getElement();
            if (temp.getCatagory().equals(catagory)){
                exists = true;
                System.out.println(temp.getElement().getClass());
                Object tempnode = temp.getElement();

                return exists;
            }
            current = current.getLink();
        }
        return exists;
    }

    public void delete(T element) {
        Node current = head;
        Node previous = head;
        while (current != null) {
            if (current.getElement().equals(element)) {
                previous.setLink(current.getLink());
            }
            previous = current;
            current = current.getLink();
        }
        numCategory--;
    }

     public boolean isEmpty() {
        try {
            if (head.getLink().equals(null)) {
                return true;
            }
        } catch (NullPointerException e) {
        }
        return false;
    }

    public String getCatagory() {
        return Catagory;
    }


    public String toString() {
        String list = "";
        Node current = head;
        while (current != null) {
            list += current.getElement() + " ";
            current = current.getLink();
        }
        return list;
    }

}
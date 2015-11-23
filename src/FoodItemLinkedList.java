
public class FoodItemLinkedList<T> {
    private Node head, tail;
    private int numElement;


    public FoodItemLinkedList() {
        head = null;
        tail = null;
        numElement = 0;
    }

    public void insert(T element) {
        Node newNode = new Node(element);
        if (head == null)
            head = newNode;
        else {
            tail.setLink(newNode);
        }
        tail = newNode;
        numElement++;
    }
    public void update(T element, T newElement){
    Node current = head;
        while (current != null){
            if (current.getElement().equals(element)){
                current.setElement(newElement);
            }
        }
    }
    public boolean search (T element){
        boolean exists = false;
        Node current = head;
        while (current != null){
            if (current.getElement().equals(element)){
                exists = true;
                return exists;
            }
            current = current.getLink();
        }
        return exists;
    }

    public void delete(T element){
        Node current = head;
        Node previous = head;
        while (current != null) {
            if (current.getElement().equals(element)){
                previous.setLink(current.getLink());
            }
            previous = current;
            current = current.getLink();
        }
        numElement--;
    }

    public boolean isEmpty(){
        if (head.getLink()==null){
            return true;
        }
        return false;
    }

    public String toString(){
        String list ="";
        Node current = head;
        while (current != null){
            list += current.getElement() + " ";
            current = current.getLink();
        }
        return list;
    }
}


/*
@author-1: Rishab Katta
@author-2:Akhil Karrothu
 */

public class Storage<E> {

    private int counter = 0;
    private Node<E> head;

    /*
    This method adds the data at the end of the list
     */
    public boolean add(E data) {
        if (head == null) {
            head = new Node(data);
        } else if (head != null) {
            Node<E> tosetnode = new Node(data);
            Node<E> currentnode = head;
            if (currentnode != null) {
                while (currentnode.getNext() != null) {
                    currentnode = currentnode.getNext();
                }
                currentnode.setNext(tosetnode);
            }
        }
        incrementcounter();
        return true;
    }
    /*/
    This method returns the counter
     */
    private int getcounter() {
        int c = 0;
        Node current = this.head;
        if (current != null){
            while (current.next != null) {
                c++;
                current = current.next;
            }
            c++;
        }
        return c;
    }
    /*
    This method increments the counter
     */
    private void incrementcounter() {
        counter++;
    }
    /*
    This method decrements the counter
     */
    private void decrementcounter() {
        counter--;
    }
    /*
    This method resets the counter variable.
     */
    private void resetcounter() {
        counter = 0;
    }

    /*
    This method adds the element type at the the specified index position
     */
    public void add(int index, E data) {
        Node<E> tosetnode = new Node(data);
        Node<E> currentnode = head;
        if (currentnode != null) {
            for (int i = 0; i < index; i++) {
                if (currentnode.getNext() != null) {
                    currentnode = currentnode.getNext();
                }
            }
            tosetnode.setNext(currentnode.getNext());
            currentnode.setNext(tosetnode);
        } else if (currentnode == null) {
            addFirst(data);
            return;
        }
        incrementcounter();
    }

    /*
    This method add the element type variable data at the first position.
     */
    public void addFirst(E data) {
        Node<E> newnode = new Node<E>(data);
        newnode.next = head;
        head = newnode;
        incrementcounter();
    }

    public boolean contains(Object o) {
        Node<E> element = new Node(o);
        Node<E> currentnode = head;
        while(currentnode!=null){
            if (currentnode.getData().equals(element.getData())){
                return true;
            }
            currentnode = currentnode.getNext();
        }
        return false;
    }

    public boolean remove(Object o) throws NullPointerException {
        Node<E> element = new Node(o);
        Node<E> currentnode = head;
        if(contains(o)){
            if(head.getData().equals(element.getData())){
                remove();
                decrementcounter();
                return true;
            }
            while (currentnode.getNext()!=null){
                if(currentnode.getNext().getData().equals(element.getData())){
                    currentnode.setNext(currentnode.getNext().getNext());
                    decrementcounter();
                    return true;
                }
                currentnode = currentnode.getNext();
            }
        }
        return false;
    }

    /*/
    The addLast method adds the element type variable data at the end of the list.
     */
    public void addLast(E data) {
        add(counter, data);
    }

    /*
    Clear method sets the head pointer to null. when the JVM sees that the values are unused they ultimately get garbage
    collected.
     */
    public void clear() {
        head = null;
        resetcounter();
    }

    /*
    Element method returns the value at the Head position.
     */
    public E element() {
        Node<E> currentnode = head;
        return currentnode.getData();
    }

    /*/
    This method removes the E type data at the specified index position.
     */
   /* public E remove(int index) throws NullPointerException {

        if (index < 0 || index > size())
            return null;
        Node<E> currentnode = head;
        E currentvalue = head.getData();
        if (head != null) {
            if (index == 0) {
                remove();
            } else {
                for (int i = 1; i < index; i++) {
                    if (currentnode.getNext() == null)
                        return null;
                    currentnode = currentnode.getNext();
                }
                currentvalue = currentnode.getNext().getData();
                currentnode.setNext(currentnode.getNext().getNext());
                decrementcounter();
            }
            return currentvalue;
        }
        return null;
    }

    /*
    This method retrieves and removes the head (first element) of this list.
     */
    public E remove() {

        if (head != null) {
            Node<E> currentnode = head;
            E headvalue = head.getData();
            head = currentnode.getNext();
            decrementcounter();
            return headvalue;
        } else {
            return null;
        }

    }
    /*
    Returns the number of elements in this list.
     */
    public int size() {
        return getcounter();
    }
    /*
    This method returns the list elements with spaces in between.
     */
    public String toString() {
        String output = "";

        if (head != null) {
            Node<E> currentnode = head;
            while (currentnode != null) {
                output += currentnode.data + " ";
                currentnode = currentnode.next;
            }
        }
        return ("[" +output + "]");
    }


}

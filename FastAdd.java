/*
@author-1: Rishab Katta
@author-2:Akhil Karrothu
 */

public class FastAdd<E extends Comparable> implements StorageI<E> {

    private int counter = 0;
    private Node<E> head;
    private Node<E> tail;

/*
The add method adds an element to storage system. It has a time complexity of O(1)
 */
    @Override
    public boolean add(E o) {
        Node<E> currentnode = new Node(o);
        if(size()==0){
            head = currentnode;
            tail = head;
            incrementcounter();
        }
        else {
            tail.setNext(currentnode);
            tail=currentnode;
            incrementcounter();
        }
        return true;
    }
    /*
    The get method gets the first element from the storage and moves the counter to the right
     */
    @Override
    public E get() {
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
    The Clear method points the head to null after which the elements in the storage get garbage collected
     */
    @Override
    public void clear() {
        head = null;
        resetcounter();
    }
    /*
    The contains method returns true if element passed as argument is present in the storage system otherwise returns false.
     */
    @Override
    public boolean contains(E o) {
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
    /*
    The isEmpty method returns true if the storage system is empty else returns false.
     */
    @Override
    public boolean isEmpty() {
        if (size()==0){
            return true;
        }
        else
            return false;
    }
/*
The sort method uses bubble sort to the elements of the storage system. It has a time complexity of O(n2)
 */
    @Override
    public void sort() {
        Node<E> currentnode;
//        E tempvalue = null;

        if (size() > 1) {
            for (int i = 0; i < size(); i++ ) {
                currentnode = head;
                Node<E> next = head.getNext();
                for (int j = 0; j < size() - 1; j++) {
                    if (currentnode.data.compareTo(next.data)>0) {
                        E temp = currentnode.data;
                        currentnode.data = next.data;
                        next.data = temp;
                    }
                    currentnode = next;
                    next = next.getNext();
                }
            }
        }
    }

    /*
    size method returns the size of the storage system
     */
    @Override
    public int size() {
        return getcounter();
    }
    /*
    The getClassName method returns the class name
     */
    @Override
    public String getClassName() {
        return this.getClass().getName();
    }

/*
    This method returns the counter
 */
    private int getcounter() {
        return counter;
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
    The toString returns the elements present in the storage in a string.
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




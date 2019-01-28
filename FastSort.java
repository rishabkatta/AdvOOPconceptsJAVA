/*
@author-1: Rishab Katta
@author-2:Akhil Karrothu
 */
public class FastSort<E extends Comparable> implements StorageI<E> {

    private int counter = 0;
    private Node<E> head;

/*
The add method adds an element in the sorted way into the storage.
 */
    @Override
    public boolean add(E o) {
        Node<E> newnode = new Node(o);
        if (head==null || (head.data.compareTo(newnode.data)>0)){
            newnode.next=head;
            head = newnode;
            incrementcounter();
            return true;
        }
        Node<E> currentnode = head;
        Node<E> previousnode = null;
        while (currentnode!=null){
            if (currentnode.data.compareTo(newnode.data)>0){
                previousnode.next = newnode;
                newnode.next=currentnode;
                incrementcounter();
                return true;
            }
            previousnode = currentnode;
            currentnode = currentnode.next;
        }
        previousnode.next=newnode;
        incrementcounter();
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
    The Sort method is empty because the elements will added in a sorted way in the add function only so there's no need
    to sort the elements again.
     */
    @Override
    public void sort() {
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




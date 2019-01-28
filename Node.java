/*
@Author-1: Rishab Katta
@Author-2: Akhil Karrothu
 */

class Node<T> {
    /*
    next variable is of the class type. It hold the reference to next object.
     */
    Node<T> next;
    T data;
    /*
    This contructor is used to initialize the next and data variables
     */
    public Node(T dataValue) {
        next = null;
        data = dataValue;
    }
    /*
    This constructor is used to set next and data variables to the specified argument values.
     */
    public Node(T dataValue, Node<T> nextValue) {
        next = nextValue;
        data = dataValue;
    }
    /*
    this method returns the data value
     */
    public T getData() {
        return data;
    }
    /*
    this method sets the data value
     */
    public void setData(T dataValue) {
        data = dataValue;
    }
    /*
    this method returns the next value
     */
    public Node<T> getNext() {
        return next;
    }
/*
this method sets the next value
 */

    public void setNext(Node<T> nextValue) {
        next = nextValue;
    }

}


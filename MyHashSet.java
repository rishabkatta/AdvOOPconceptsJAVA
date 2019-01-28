/*
@author-name: Rishab katta
@author-name:Akhil Karrothu
 */


public class MyHashSet<E> implements SetI<E> {
    private int counter = 0;
    private Node<E> head;
    private Node<E> tail;
    public Object[] objarray = new Object[10];

    /*
    This method calculates the hashcode of an element and adds the element to the set based on that hashcode.
     */
    @Override
    public boolean add(E e) {
        try {
            if(!contains(e)) {
                Node<E> tosetnode = new Node<E>(e);
                int hc = myHashCode(e);
                int hcmod = hc % 10;
                if (objarray[hcmod] == null) {
                    objarray[hcmod] = tosetnode;
                    head = tosetnode;
                    incrementcounter();
                } else {
                    Node<E> currentnode = (Node<E>) objarray[hcmod];
                    if (currentnode != null) {
                        while (currentnode.getNext() != null) {
                            currentnode = currentnode.getNext();
                        }
                        currentnode.setNext(tosetnode);
                    }
                    incrementcounter();
                }
                return true;
            }

        } catch (NullPointerException e1) {

        }
        return false;
    }
    /*
    This method calculates the hashcode of an object by converting it to a string and adding the ascii values of the
    individual characters.
     */
    public int myHashCode(Object o){
        if (o==null){
            return 0;
        }
        String aString = o.toString();
        int sumofascii=0;
        for (char c : aString.toCharArray()){
            sumofascii=sumofascii+ ((int)c);
        }
        sumofascii = sumofascii*31;
        return sumofascii;
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
    This method takes another set as the argument and adds the elements of that set to calling object's set if they are
    not already present
     */
    @Override
    public boolean addAll(SetI<? extends E> c) {
        Object[] cArray = c.toArray();
        int count=0;
        for(int i=0; i<cArray.length; i++){
            if(!contains(cArray[i])){
                add((E)cArray[i]);
                count +=1;
            }
        }
        if (count>=1) {
            return true;
        }
        else
            return false;
    }
    /*
    This method takes another set as the argument and checks to see if all the elements in that set are present in
    the calling object's set or not.
     */
    @Override
    public boolean containsAll(SetI<?> c) {
        Object[] cArray = c.toArray();
        for (int i=0; i<cArray.length;i++){
            if(contains(cArray[i])){
                continue;
            }
            else
                return false;
        }
        return true;
    }
    /*
    This method takes another set as an argument and removes all the elements of that set from the calling object's set.
     */
    @Override
    public boolean removeAll(SetI<?> c) {
        Object[] cArray = c.toArray();
        int count=0;
        for(int i=0; i<cArray.length; i++){
            if(contains(cArray[i])){
                remove((E)cArray[i]);
                count +=1;
            }
        }
        if (count>=1) {
            return true;
        }
        else
            return false;
    }
    /*
    this method clears out the storage and any remaining values will be garbage collected.
     */
    @Override
    public void clear() {
        for(int i=0; i<objarray.length;i++){
            objarray[i] = null;
        }
        resetcounter();
    }
    /*
    This method checks to see if the Object o mentioned as the argument is present in our hashset or not.
     */
    @Override
    public boolean contains(Object o) {
        try {
            Node<E> element = new Node(o);
            int hc = myHashCode(o);
            int hcmod = hc%10;

            Node<E> currentnode = (Node<E>)objarray[hcmod];
            if(currentnode==null){
                return false;
            }
            while(currentnode!=null){
                if (element.getData()==null) {
                    if (currentnode.getData() == element.getData()) {
                        return true;
                    }
                }
                else if (currentnode.getData().equals(element.getData())){
                    return true;
                }
                currentnode = currentnode.getNext();
            }
        } catch (NullPointerException e) {
        }
        return false;
    }
    /*
    This method checks if the object given as the argument is a set or not, if it is then if it contains all the elements
     present in the calling object's set and if both of them have same sizes. Returns true or false accordingly
     */
    @Override
    public boolean equals(Object obj) {
        if (containsAll((SetI<?>) obj) && ((SetI<?>) obj).size()==this.size()){
            return true;
        }
        return false;
    }
    /*
    This method calculates and returns the sum of hashcodes of all the elements present in the set.
     */
    @Override
    public int hashCode() {
        Object[] thisarray = this.toArray();
        int sumofhc=0;
        for(int i=0; i<thisarray.length;i++){
            int thisobjhc = myHashCode(thisarray[i]);
            sumofhc = sumofhc + thisobjhc;
        }
        return sumofhc;
    }
    /*
    This method checks to see if the hashset is empty or not.
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
    This method removes the Object o from our hashset
     */
    @Override
    public boolean remove(Object o) {
        try {
            if(contains(o)){
                Node<E> element = new Node(o);
                int hc = myHashCode(o);
                int hcmod = hc%10;
                Node<E> currentnode = (Node<E>)objarray[hcmod];
                if (currentnode.getData()==null){
                    objarray[hcmod] = currentnode.getNext();
                    decrementcounter();
                    return true;
                }
                else if (element.getData()!=null) {
                    if (element.getData().equals(currentnode.getData())) {
                        objarray[hcmod] = currentnode.getNext();
                        decrementcounter();
                        return true;
                    }
                }
                while (currentnode.getNext()!=null){
                    if (element.getData()==null){
                        if(currentnode.getNext().getData()==element.getData()){
                            currentnode.setNext(currentnode.getNext().getNext());
                            decrementcounter();
                            return true;
                        }
                    }
                    else if(currentnode.getNext().getData().equals(element.getData())){
                        currentnode.setNext(currentnode.getNext().getNext());
                        decrementcounter();
                        return true;
                    }
                    currentnode = currentnode.getNext();
                }
            }
        } catch (NullPointerException e) {
        }
        return false;
    }
    /*
    This method returns the size of our hashset.
     */
    @Override
    public int size() {
        return getcounter();
    }
    /*
    This method gets all the elements from our hashset and puts them into an Object array and returns that array.
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];
        int j=0;
        for(int i=0; i<objarray.length; i++){
            Node<E> currentnode = (Node<E>)objarray[i];
            while (currentnode != null) {
                array[j++] = currentnode.data;
                currentnode = currentnode.next;
            }
        }
        return array;

    }
    /*
    This method gets all the elements from our hashset and puts them into a String and returns that String.
     */
    public String toString() {
        String output = "";
        int count = 0;
        for(int i=0; i<objarray.length; i++){
            output += "" + i + ": ";
            Node<E> currentnode = (Node<E>)objarray[i];
            count=0;
            while (currentnode != null) {
                output += currentnode.data + "-> ";
                currentnode = currentnode.next;
                count += 1;
            }
            if (currentnode==null){
                output += "" + count + "/" + "null";
            }
            output += "\n";
        }
        return output;

    }
}

/*
 *  CSC-223 FA 2018 PROJECT:
 *  Programmer: Quang Bui
 *  Due Date: Tuesday, October 16th, 2018
 *  Description: ArrayList represents an array implementation of a list.
 * The front of the list is kept at array index 0. This class will 
 * be extended to create a specific kind of list.
 */

package mazetake3;

import exceptions.ElementNotFoundException;
import exceptions.EmptyCollectionException;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Programmed by: Quang Bui
 * Due Date: Tuesday, October 16th, 2018
 * Description: ArrayList represents an array implementation of a list.
 * The front of the list is kept at array index 0. This class will 
 * be extended to create a specific kind of list.
 */
public abstract class ArrayList<T> implements ListADT<T>, Iterable<T>{
    private final static int DEFAULT_CAPACITY = 100;
    private final static int NOT_FOUND = -1;
	
    protected int rear; //next available slot in the array
    protected T[] list; 
    protected int size; // physical/actual size of array
    //protected int count; //actual(logical) number of elements
    /**
     * modCount variable
     * keep track of the number of modification made through the iterator
     * modCount must stay in synch with the iterator's stored value
     */
    protected int modCount; 
    
    /**
     * Creates an empty list using the default capacity.
     */
    public ArrayList(){
        this(DEFAULT_CAPACITY);
    }
    
    /**
     * Creates an empty list using the specified capacity.
     * @param initialCapacity the integer value of the size of the 
     * array list
     */
    public ArrayList(int initialCapacity){
        size = initialCapacity;
        rear = 0; //count = 0; // logical size; actual number of elements
        list = (T[])(new Object[initialCapacity]);
	modCount = 0;
    }
    
    /**
     * Creates a new array to store the contents of this list with
     * twice the capacity of the old one. Called by descendant classes
     * that add elements to the list.
     */
    protected void expandCapacity(){
        /*
        1) get bigger space
        2) move copy old to new
        3) change your address
        */
        int newSize = size * 2;
        T[] newList = (T[])(new Object[newSize]);
        for(int i = 0; i < size; i++){
            newList[i] = list[i];
        }
        size = newSize;
        list = newList;
    }
    
    /**
     * Mutator: shrinkCapacity()
     * Pre-condition: nothing
     * Post-condition: shrink the capacity of the array if the physics
     * size do not below 5 and the number of element is always <= the 
     * physics size. If the physics size is less than 5, do not shrink it.
     */
    public void shrinkCapacity(){
        if(size < 5){
            return;
        }
        if(size >= 5){
            int getOneHalfSize = size / 2;
            if(getOneHalfSize >= size()){
                int newSize = getOneHalfSize;
                T[] newList = (T[])(new Object[newSize]);
                for(int i = 0; i < size(); i++){
                    newList[i] = list[i];
                }
                size = newSize;
                list = newList;
                return;
            }else if(getOneHalfSize < size()){
                // do nothing
                return;
            }
        }
    }
    
    /**
     * Accessor: findElement(int position)
     * @param position the position of the element (is calculated from the
     * beginning at the position 0 to size() - 1)
     * @return the content of that position
     * Pre-condition: nothing
     * Post-condition: the position of the element (is calculated from the
     * beginning at the position 0 to size() - 1) is passed as the 
     * parameter. And then base on the position, return the content of
     * that position. This method is used in the subList method.
     */
    public T findElement(int position)
    {
        T result = null;
        int scan = 0;
        
        if (!isEmpty()){
            while (scan < rear){
                if (position == scan){
                    result = list[scan];
                }
                scan++;   
            }
        }

        return result;
    }
    
    /**
     * Accessor: subList(int start, int fin)
     * @param start the beginning of the index of subList
     * @param fin the ending of the index of subList
     * @return the newList is a subList of the original List
     * Pre-condition: findElement(position), addToRear(element)
     * Post-condition: return the newList is a subList of the original List
     * if the position start is always less than or equal the position
     * of the fin of subList. The fin and start parameters of the Sublist
     * are discover by the findElement(position) method is defined before.
     * If one of them is not satisfy the condition, this method
     * return null
     */
    public ArrayUnorderedList<T> subList(int start, int fin){
        if(((fin - start) == 0) || (fin < start) 
                || (start < 0) || (fin > size())){
            return null;
        }
        
        T head = findElement(start);
        T tail = null;
        if(fin == size()){
            /**
             * the tail of the subList is last position of the original
             * List if fin position is the last position of the original
             * List.
             */
            tail = findElement(rear - 1);
        }else if(fin < size()){
            /**
             * the tail of the subList is fin position if fin position 
             * is NOT the last position of the original List.
             */
            tail = findElement(fin);
        }
        
        /**
         * if head or tail, one of them is not exist in the ArrayList
         */
        if(contains(head) == false || contains(tail) == false){
            return null;
        }
        
        /**
         * BOTH head and tail are existing in the ArrayList
         * create a new ArrayUnorderedList with the number 
         * of element from start to fin
         */
        int numElement = (fin + 1 - start);
        ArrayUnorderedList<T> newList = new 
                        ArrayUnorderedList<T>(numElement);
        
        if(fin == size()){
            for(int i = start; i < rear; i++){
                T current = findElement(i);
                newList.addToRear(current);
            }
        }else if(fin < size()){
            for(int i = start; i < fin; i++){
                T current = findElement(i);
                newList.addToRear(current);
            }
        }
        
        return newList;
    }
    
    /**
     * Removes and returns the last element in this list.
     * @return the last element in the list
     * @throws EmptyCollectionException if the element is not in the list
     */
    public T removeLast() throws EmptyCollectionException
    {
        if(isEmpty()){
            throw new EmptyCollectionException("ArrayList");
        }else{
            shrinkCapacity();
            return (list[--rear]);
        }
    }
    
    /**
     * Removes and returns the first element in this list.
     * @return the first element in the list
     * @throws EmptyCollectionException if the element is not in the list
     */
    public T removeFirst() throws EmptyCollectionException
    {
        T result;
        if(isEmpty())
            throw new EmptyCollectionException("ArrayList");
        
        result = list[0];
        // shifting the element to left
        for(int i = 1; i < rear; i++){
            list[i - 1] = list[i];
        }
        rear--;
        shrinkCapacity();
        return result;
    }
    
    /**
     * Accessor: clearList()
     * Pre-condition: none
     * Post-condition: delete all element of the List :D
     */
    public void clearList(){
        if(isEmpty()){
            throw new EmptyCollectionException("ArrayList");
        }else{
            int temp = rear;
            for(int i = 0; i < temp; i++){
                rear--;
            }
        }
    }
    
    /**
     * Accessor: getPhysicsSize()
     * @return the physics size of List
     * Pre-condition: none
     * Post-condition: return the physics size of List
     */
    public int getPhysicsSize(){
        return size;
    }

    /**
     * Removes and returns the specified element.
     * @param  element the element to be removed and returned from the list
     * @return the removed elememt
     * @throws ElementNotFoundException if the element is not in the list
     */
    public T remove(T element)
    {
        T result;
        int index = find(element);

        if (index == NOT_FOUND)
            throw new ElementNotFoundException("ArrayList");

        result = list[index];
        rear--;
		
        // shift the appropriate elements 
        for (int scan=index; scan < rear; scan++)
            list[scan] = list[scan+1];
 
        list[rear] = null;
        modCount++;
        shrinkCapacity();
        return result;
    }
    
    /**
     * Returns a reference to the element at the front of this list.
     * The element is not removed from the list.  Throws an
     * EmptyCollectionException if the list is empty.  
     * @return a reference to the first element in the list
     * @throws EmptyCollectionException if the list is empty
     */
    public T first() throws EmptyCollectionException
    {
        if(isEmpty())
            throw new EmptyCollectionException("ArrayList");
        return (list[0]);
    }

    /**
     * Returns a reference to the element at the rear of this list.
     * The element is not removed from the list. Throws an
     * EmptyCollectionException if the list is empty.  
     * @return a reference to the last element of this list
     * @throws EmptyCollectionException if the list is empty
     */
    public T last() throws EmptyCollectionException
    {
        if(isEmpty())
            throw new EmptyCollectionException("ArrayList");
        return list[rear - 1];
    }

    /**
     * Returns true if this list contains the specified element.
     * @param target the target element
     * @return true if the target is in the list, false otherwise 
     */
    public boolean contains(T target)
    {
        return (find(target) != NOT_FOUND);
    }
    
    /**
     * Returns the array index of the specified element, or the
     * constant NOT_FOUND if it is not found.
     * @param target the target element
     * @return the index of the target element, or the 
     *         NOT_FOUND constant
     */
    private int find(T target)
    {
        int scan = 0;
        int result = NOT_FOUND;
        
        if(!isEmpty()){
            while(result == NOT_FOUND && scan < rear){
                if(target.equals(list[scan])){
                    result = scan;
                }else{
                    scan++;
                }
            }
        }
        
        return result;
    }

    /**
     * Returns true if this list is empty and false otherwise. 
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty()
    {
        return (rear == 0);
    }
 
    /**
     * Returns the number of elements currently in this list.
     * @return the number of elements in the list
     */
    public int size()
    {
        return rear;
    }

    /**
     * Returns a string representation of this list. 
     * @return the string representation of the list
     */
    public String toString()
    {
        String out = "";
        if(isEmpty()){
            out += "Empty List";
            return out;
        }
        for (int i = 0; i < rear; i++)
            out+= list[i] + " ";
        return out;
    }
	
    /**
     * Returns an iterator for the elements currently in this list.
     * @return an iterator for the elements in the list
     */
    public Iterator<T> iterator()
    {
        return new ArrayListIterator();
    }
    
    /**
     * ArrayListIterator iterator over the elements of an ArrayList.
     */	
    private class ArrayListIterator implements Iterator<T>{
        int iteratorModCount;
        int current;
        
        /**
         * Sets up this iterator using the specified modCount.
         * @param modCount the current modification count for the ArrayList
         */
        public ArrayListIterator(){
            iteratorModCount = modCount;
            current = 0;
        }
        
        /**
         * Returns true if this iterator has at least one more element
         * to deliver in the iteration.
         * @return  true if this iterator has at least one 
         * more element to deliver in the iteration
         * @throws  ConcurrentModificationException if the 
         * collection has changed while the iterator is in use
         */
        public boolean hasNext() throws ConcurrentModificationException{
            if(iteratorModCount != modCount){
                throw new ConcurrentModificationException();
            }
            return (current < rear);
        }
        
        /**
         * Returns the next element in the iteration. If there are no
         * more elements in this iteration, a NoSuchElementException is
         * thrown.
         * @return  the next element in the iteration
         * @throws  NoSuchElementException if an element 
         * not found exception occurs
         * @throws  ConcurrentModificationException 
         * if the collection has changed
         */
        public T next() throws ConcurrentModificationException{
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            current++;
            return list[current - 1];
        }
        
        /**
         * The remove operation is not supported in this collection.
         * @throws UnsupportedOperationException if 
         * the remove method is called
         */
        public void remove() throws UnsupportedOperationException{
            throw new UnsupportedOperationException();
        }
    }
}
/*
 *  CSC-223 FA 2018 PROJECT:
 *  Programmer: Quang Bui
 *  Due Date: Tuesday, October 16th, 2018
 *  Description: ArrayUnorderedList represents an array implementation 
 * of an unordered list.
 */

package mazetake3;

import exceptions.ElementNotFoundException;

/**
 * Programmed by: Quang Bui
 * Due Date: Tuesday, October 16th, 2018
 * Description: ArrayUnorderedList represents an array implementation 
 * of an unordered list.
 */
public class ArrayUnorderedList<T> extends ArrayList<T> 
        implements UnorderedListADT<T> {
    /**
     * Creates an empty list using the default capacity.
     */
    public ArrayUnorderedList()
    {
        super();
    }

    /**
     * Creates an empty list using the specified capacity.
     * @param initialCapacity the intial size of the list
     */
    public ArrayUnorderedList(int initialCapacity)
    {
        super(initialCapacity);
    }

    /**
     * Adds the specified element to the front of this list.
     * @param element the element to be added to the front of the list
     */
    public void addToFront(T element)
    {
        if(size == rear)
            expandCapacity();
        for(int i = rear; i > 0; i--){
            list[i] = list[i - 1];
        }
        rear++;
        list[0] = element;
    }

    /**
     * Adds the specified element to the rear of this list.
     * @param element the element to be added to the list
     */
    public void addToRear(T element)
    {
        if(size == rear)
            expandCapacity();
        list[rear++] = element;
    }

    /**
     * Adds the specified element after the specified target element.
     * Throws an ElementNotFoundException if the target is not found.
     * @param element the element to be added after the target element
     * @param target  the target that the element is to be added after
     */
    public void addAfter(T element, T target)
    {
        if (size() == list.length)
            expandCapacity();

        int scan = 0;

        // find the insertion point
        while (scan < rear && !target.equals(list[scan])) 
            scan++;
      
        if (scan == rear)//ElementNotFoundException
            throw new ElementNotFoundException("UnorderedList");
    
        scan++;
		
	// shift elements up one
        for (int shift=rear; shift > scan; shift--)
            list[shift] = list[shift - 1];

        // insert element
        list[scan] = element;
        rear++;
        modCount++;
    }
}
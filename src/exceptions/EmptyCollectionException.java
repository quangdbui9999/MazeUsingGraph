/*
 *  CSC-223 FA 2018 PROJECT:
 *  Programmer: Quang Bui
 *  Due Date: 
 *  Description: 
 */

package exceptions;

/**
 * Programmed by: Quang Bui
 * Due Date: 
 * Description: 
 */
public class EmptyCollectionException extends RuntimeException{
    /**
     * Sets up this exception with an appropriate message.
     * @param collection the name of the collection
     */
    public EmptyCollectionException(String collection){
        super("The " + collection + " is empty.");
    }
}
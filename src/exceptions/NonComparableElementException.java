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
public class NonComparableElementException extends RuntimeException{
    /**
     * Sets up this exception with an appropriate message.
     * @param collection the exception message to deliver
     */
    public NonComparableElementException(String collection){
        super("The " + collection + " requires comparable elements.");
    }
}
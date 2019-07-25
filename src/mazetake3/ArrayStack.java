

/**
 * CSC-223 ASSIGNMENT NAME * DUE DATE:
 * DATE SUBMITTED:
 * PROGRAMMED BY: A. Wright
 
 *
 */
 
package mazetake3;
//import java.Exceptions
/**
 *
 * @author A. Wright
 * CLASS DESCRIPTION:
 */
public class ArrayStack<Type> implements Stack<Type> {
   private final static int MAXSIZE = 100;
   private int top;
   private Type[] stack;

   /**
    *
    */
   public ArrayStack() {
      top = -1;
      stack = (Type[]) (new Object[MAXSIZE]); // casting an array of Objects
   }                                          // to type TYPE required/requested

   /**
    *
    * @param howBig
    */
   public ArrayStack(int howBig) {
      top = -1;
      stack = (Type[]) (new Object[howBig]); // casting an array of Objects
   }                                        // to type TYPE required/requested
   /**
    * ACCESSOR method
    *
    * @return true if stack is empty... top = -1
    */
   public boolean isEmpty() {
      if (top < 0) {
         return true;
      } else {
         return false;
      }
   }
   /**
    * ACCESSOR method
    *
    * @return number of Stack elements (0 if empty)
    */
   public int size() {
      return (top + 1);
   }
   /**
    * MUTATOR method
    *
    * @param element
    */
   public void push(Type element) {
      if (top < MAXSIZE) {
         stack[++top] = element;
      }
    //else

   }
   /**
    * MUTATOR method
    *
    * @return the top element from the top of the stack
    * @throws IndexOutOfBoundsException
    */
   public Type pop() throws IndexOutOfBoundsException {
      IndexOutOfBoundsException empty
              = new IndexOutOfBoundsException("Stack can't be popped, "
                      + "it is an Empty Stack");

      if (isEmpty())
         throw empty;
      else {
         return stack[top--];
      }

   }

   /**
    * ACCESSOR method
    *
    * @return a copy of the element at the top of the stack
    * @throws IndexOutOfBoundsException
    */
   public Type peek() throws IndexOutOfBoundsException {
      IndexOutOfBoundsException empty
              = new IndexOutOfBoundsException("Stack can't be popped, "
                      + "it is an Empty Stack");

      if (isEmpty()) {
         throw empty;
      } else {
         return stack[top];
      }

   }

   /**
    * ACCESSOR method
    *
    * @return displays contents of the stack
    */
   public String toString() {
      String out = "Stack Contents\n";
      if (isEmpty()) {
         out += "\nEMPTY!\n";
      } else {
         for (Type each : stack) {
            out += (each + "\t");
         }
      }
      return out;
   }

}

/*
 * A class that represents an exception where there are too many dice in the hand.
 * 
 * CPSC224-02, Spring 2018
 * Programming Assignment #6
 * 
 * @author Cole deSilva
 * @version v2.0 3/22/2018
 */

public class OutOfHandException extends Exception 
{
	/*
	 * Default constructor.
	 */
	public OutOfHandException()
	{
		super();
	}
	
	/*
	 * Constructor which takes a message.
	 */
	public OutOfHandException(String message)
	{
		super(message);
	}
}

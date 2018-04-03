/*
 * A class that represents an exception where there are too many dice in the hand.
 * 
 * CPSC224-02, Spring 2018
 * Final Assignment
 * 
 * @authors Isak Bjornson, Kevin Shaw, & Cole deSilva
 * @version v1.0
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

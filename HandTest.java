import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HandTest {

	@Test
	void testAdd() 
	{
		Hand newHand = new Hand();
		Dice newDice = new Dice();
		try 
		{
			newHand.add(newDice);
		} 
		catch (OutOfHandException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(newHand.get(0).getRoll() == 0);
	}

	@Test
	void testSet()
	{
		Hand newHand = new Hand();
		Dice newDice = new Dice();
		Dice newDice2 = new Dice();
		newDice2.rollDice();
		try 
		{
			newHand.add(newDice);
		} 
		catch (OutOfHandException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		newHand.set(0, newDice2);
		assertTrue(newHand.get(0).getRoll() > 0);
	}

	@Test
	void testGet() 
	{
		Hand newHand = new Hand();
		Dice newDice = new Dice();
		try 
		{
			newHand.add(newDice);
		} 
		catch (OutOfHandException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(newHand.get(0).getRoll() == 0);
	}

	@Test
	void testSort()
	{
		Hand hand = new Hand();
		Dice d1 = new Dice();
		Dice d2 = new Dice();
		Dice d3 = new Dice();
		Dice d4 = new Dice();
		Dice d5 = new Dice();
		d1.rollDice();
		d2.rollDice();
		d3.rollDice();
		d4.rollDice();
		d5.rollDice();
		
		try 
		{
			hand.add(d1);
			hand.add(d2);
			hand.add(d3);
			hand.add(d4);
			hand.add(d5);
		} 
		catch (OutOfHandException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hand.sort();
		for(int i = 1; i < 5; i++)
		{
			assertTrue(hand.get(i - 1).getRoll() <= hand.get(i).getRoll());
		}
		
	}

	@Test
	void testTotalAllDice()
	{
		Hand hand = new Hand();
		Dice d1 = new Dice();
		d1.set(1);
		Dice d2 = new Dice();
		d2.set(1);
		Dice d3 = new Dice();
		d3.set(1);
		Dice d4 = new Dice();
		d4.set(1);
		Dice d5 = new Dice();
		d5.set(1);
		
		try {
			hand.add(d1);
			hand.add(d2);
			hand.add(d3);
			hand.add(d4);
			hand.add(d5);
		} catch (OutOfHandException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(5, hand.totalAllDice());
	}

	@Test
	void testMaxOfAKindFound() 
	{
		Hand hand = new Hand();
		Dice d1 = new Dice();
		d1.set(1);
		Dice d2 = new Dice();
		d2.set(1);
		Dice d3 = new Dice();
		d3.set(1);
		Dice d4 = new Dice();
		d4.set(1);
		Dice d5 = new Dice();
		d5.set(1);
		
		try {
			hand.add(d1);
			hand.add(d2);
			hand.add(d3);
			hand.add(d4);
			hand.add(d5);
		} catch (OutOfHandException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(5, hand.maxOfAKindFound());
	}
	

	@Test
	void testMaxStraightFound() 
	{
		Hand hand = new Hand();
		Dice d1 = new Dice();
		d1.set(1);
		Dice d2 = new Dice();
		d2.set(2);
		Dice d3 = new Dice();
		d3.set(3);
		Dice d4 = new Dice();
		d4.set(7);
		Dice d5 = new Dice();
		d5.set(8);
		
		try {
			hand.add(d1);
			hand.add(d2);
			hand.add(d3);
			hand.add(d4);
			hand.add(d5);
		} catch (OutOfHandException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(3, hand.maxStraightFound());
	}

	@Test
	void testFullHouseFound() 
	{
		Hand hand = new Hand();
		Dice d1 = new Dice();
		d1.set(1);
		Dice d2 = new Dice();
		d2.set(1);
		Dice d3 = new Dice();
		d3.set(3);
		Dice d4 = new Dice();
		d4.set(3);
		Dice d5 = new Dice();
		d5.set(3);
		
		try {
			hand.add(d1);
			hand.add(d2);
			hand.add(d3);
			hand.add(d4);
			hand.add(d5);
		} catch (OutOfHandException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(hand.fullHouseFound());
	}

	@Test
	void testReturnCount()
	{
		Hand hand = new Hand();
		Dice d1 = new Dice();
		d1.set(1);
		Dice d2 = new Dice();
		d2.set(1);
		Dice d3 = new Dice();
		d3.set(3);
		Dice d4 = new Dice();
		d4.set(3);
		Dice d5 = new Dice();
		d5.set(3);
		
		try {
			hand.add(d1);
			hand.add(d2);
			hand.add(d3);
			hand.add(d4);
			hand.add(d5);
		} catch (OutOfHandException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(2, hand.returnCount(1));		
		assertEquals(3, hand.returnCount(3));
	}

	@Test
	void testVersatileVictor() 
	{
		Hand hand = new Hand();
		Dice d1 = new Dice();
		d1.set(9);
		Dice d2 = new Dice();
		d2.set(9);
		Dice d3 = new Dice();
		d3.set(6);
		Dice d4 = new Dice();
		d4.set(7);
		Dice d5 = new Dice();
		d5.set(8);
		
		try {
			hand.add(d1);
			hand.add(d2);
			hand.add(d3);
			hand.add(d4);
			hand.add(d5);
		} catch (OutOfHandException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(hand.versatileVictor());
		d5.set(5);
		hand.set(4, d5);
		assertTrue(hand.versatileVictor());
	}

	@Test
	void testHavingABlast() 
	{
		Hand hand = new Hand();
		Dice d1 = new Dice();
		d1.set(5);
		Dice d2 = new Dice();
		d2.set(5);
		Dice d3 = new Dice();
		d3.set(8);
		Dice d4 = new Dice();
		d4.set(8);
		Dice d5 = new Dice();
		d5.set(3);
		
		try {
			hand.add(d1);
			hand.add(d2);
			hand.add(d3);
			hand.add(d4);
			hand.add(d5);
		} catch (OutOfHandException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(hand.havingABlast());
	}

	@Test
	void testMasterfulMarksman()
	{
		Hand hand = new Hand();
		Dice d1 = new Dice();
		d1.set(7);
		Dice d2 = new Dice();
		d2.set(7);
		Dice d3 = new Dice();
		d3.set(2);
		Dice d4 = new Dice();
		d4.set(2);
		Dice d5 = new Dice();
		d5.set(9);
		
		try {
			hand.add(d1);
			hand.add(d2);
			hand.add(d3);
			hand.add(d4);
			hand.add(d5);
		} catch (OutOfHandException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(hand.masterfulMarksman());
	}

	@Test
	void testRunninAndGunnin() 
	{
		Hand hand = new Hand();
		Dice d1 = new Dice();
		d1.set(4);
		Dice d2 = new Dice();
		d2.set(4);
		Dice d3 = new Dice();
		d3.set(6);
		Dice d4 = new Dice();
		d4.set(6);
		Dice d5 = new Dice();
		d5.set(3);
		
		try {
			hand.add(d1);
			hand.add(d2);
			hand.add(d3);
			hand.add(d4);
			hand.add(d5);
		} catch (OutOfHandException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(hand.runninAndGunnin());
	}

}

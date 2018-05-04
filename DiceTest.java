import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DiceTest 
{

	@Test
	void testGetRoll() 
	{
		Dice newDice = new Dice();
		assertEquals(0, newDice.getRoll());
	}

	@Test
	void testGetWeapon()
	{
		Dice newDice = new Dice();
		assertEquals(null, newDice.getWeapon());
	}

	@Test
	void testRollDice() 
	{
		Dice newDice = new Dice();
		newDice.rollDice();
		assertTrue(newDice.getRoll() > 0 && newDice.getRoll() < 10);
	}

}

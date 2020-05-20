package dice;

import java.util.Random;

public class RegularDice implements Dice {

	protected int diceResult;
	private static int MAX_DICE_VALUE = 6;
	
	public RegularDice() {
		diceResult = 1;
	}
	
	@Override
	public int getDiceResult() {
		setDiceResult();
		return diceResult;
	}
	@Override
	public void setDiceResult() {
		Random r = new Random();
		diceResult = r.nextInt(MAX_DICE_VALUE) + 1;
		return;
	}
}

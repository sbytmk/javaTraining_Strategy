import java.util.Random;

public class ProbStrategy implements Strategy {
	private Random random;
	private int prevHandValue = 0;
	private int currentHandvalue = 0;
	private int[][] history = { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 }, };

	public ProbStrategy(int seed) {
		random = new Random(seed);
	}

	public Hand nextHand() {
		int bet = random.nextInt(getSum(currentHandvalue));
		int handvalue = 0;
		if (bet < history[currentHandvalue][0]) {
			handvalue = 0;
		} else if (bet < history[currentHandvalue][0]
				+ history[currentHandvalue][1]) {
			handvalue = 1;
		} else {
			handvalue = 2;
		}
		prevHandValue = currentHandvalue;
		currentHandvalue = handvalue;
		return Hand.getHand(handvalue);
	}

	private int getSum(int hv) {
		int sum = 0;
		for (int i = 0; i < 3; i++) {
			sum += history[hv][i];
		}
		return sum;
	}

	public void study(boolean win) {
		if (win) {
			history[prevHandValue][currentHandvalue]++;
		} else {
			history[prevHandValue][(currentHandvalue + 1) % 3]++;
			history[prevHandValue][(currentHandvalue + 2) % 3]++;
		}
	}
}

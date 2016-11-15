package algData1;

import java.util.Random;

public class PrimTest {

	public boolean test(int i) {
		Random r = new Random();
		for (int k = 0; k < i / 2; k++) {
			if (i % r.nextInt(i) == 0)
				return false;
		}
		return true;
	}

}

public class Matcher {
	//unnecessary constructor
	//if was placed on the first place
	//unified for loop
	public boolean match(int[] expected, int[] actual, int clipLimit, int delta) {

		if (actual.length != expected.length)
			return false;

		// Clip "too-large" values
		for (int i = 0; i < actual.length; i++) {
			if (actual[i] > clipLimit)
				actual[i] = clipLimit;

			// Check that each entry within expected +/- delta
			if (Math.abs(expected[i] - actual[i]) > delta)
				return false;
		}
		return true;
	}
}
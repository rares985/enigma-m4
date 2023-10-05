
public class Utilities {
	
	private static final int ASCII_OFFSET = 65;

	public static int toInt(char letter) {
		return (letter - ASCII_OFFSET);
	}

	public static char toChar(int pos) {
		return EnigmaMachine.alphabet.getLetter(pos);
	}

	public static char moveInAsciiLimits(char letter) {
		int len = EnigmaMachine.alphabet.getLength();
		int pos = (((letter - ASCII_OFFSET) % len) + len) % len;
		return toChar(pos);
	}
}

/**
 * <p>Class which represents the plugboard</p>
 * @author Rares-Andrei Mateizer
 *
 */
public class Plugboard extends Component {
	
	/**
	 * <p>The empty constructor, creates a new plugboard with an
	 * empty mapping</p>
	 */
	public Plugboard() {
		super(new char[EnigmaMachine.alphabet.getLength()]);
	}
	/**
	 * <p>Constructor which creates a new instance of this class,
	 * with the specified connections between letters
	 * @param cableConnections The connections between letters
	 */
	public Plugboard(String[] cableConnections) {
		super(new char[EnigmaMachine.alphabet.getLength()]);
		if (cableConnections == null) {
			for (int i = 0; i < mappedAlphabet.getLength(); ++i) {
				mappedAlphabet.setLetter(i, Utilities.toChar(i));
			}
			return;
		}
		for (String pair : cableConnections) {
			addCable(pair.toCharArray()[0], pair.toCharArray()[1]);
		}
		for (int i = 0; i < mappedAlphabet.getLength(); ++i) {
			if (mappedAlphabet.getLetter(i) == '\0') {
				mappedAlphabet.setLetter(i, Utilities.toChar(i));
			}
		}
	}
	/**
	 * <p>Creates a new connection(cable) between the specified letters
	 * This means that each letter will be mapped as the other</p>
	 * @param x The first letter
	 * @param y The second letter
	 */
	private void addCable(char x, char y) {
		if (x == y) {
			return;
		}
		mappedAlphabet.setLetter(Utilities.toInt(x), y);
		mappedAlphabet.setLetter(Utilities.toInt(y), x);
	}
	@Override
	public char mapLetter(char letter) {
		return mappedAlphabet.getLetter(Utilities.toInt(letter));
	}

}

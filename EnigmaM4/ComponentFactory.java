/**
 * 
 * @author Rares-Andrei Mateizer
 * <p> a Factory class which returns various components of the Enigma(e.g. Rotor, Reflector, Plugboard)</p>
 *
 */
public class ComponentFactory implements IComponentFactory {

	private static final ComponentFactory INSTANCE = new ComponentFactory();

	public static ComponentFactory getInstance() {
		return INSTANCE;
	}
	/**
	 * This method creates a new Rotor of the specified type, having
	 * a mapping corresponding to the Enigma technical details
	 */
	@Override
	public Rotor createRotor(int rotorType) {
		switch (rotorType) {
		case 1:
			return new Rotor(1, "EKMFLGDQVZNTOWYHXUSPAIBRCJ".toCharArray(), "R".toCharArray());
		case 2:
			return new Rotor(2, "AJDKSIRUXBLHWTMCQGZNPYFVOE".toCharArray(), "F".toCharArray());
		case 3:
			return new Rotor(3, "BDFHJLCPRTXVZNYEIWGAKMUSQO".toCharArray(), "W".toCharArray());
		case 4:
			return new Rotor(4, "ESOVPZJAYQUIRHXLNFTGKDCMWB".toCharArray(), "K".toCharArray());
		case 5:
			return new Rotor(5, "VZBRGITYUPSDNHLXAWMJQOFECK".toCharArray(), "A".toCharArray());
		case 6:
			return new Rotor(6, "JPGVOUMFYQBENHZRDKASXLICTW".toCharArray(), "AN".toCharArray());
		case 7:
			return new Rotor(7, "NZJHGRCXMYSWBOUFAIVLPEKQDT".toCharArray(), "AN".toCharArray());
		case 8:
			return new Rotor(8, "FKQHTLXOCBJSPDZRAMEWNIUYGV".toCharArray(), "AN".toCharArray());
		default:
			return null;
		}
	}

	@Override
	public Reflector createReflector(char reflectorType) {
		switch (reflectorType) {
		case 'B':
			return new Reflector('B', "YRUHQSLDPXNGOKMIEBFZCWVJAT".toCharArray());
		case 'C':
			return new Reflector('C', "FVPJIAOYEDRZXWGCTKUQSBNMHL".toCharArray());
		default:
			return null;
		}
	}

	@Override
	public Plugboard createPlugboard(String[] cableConnections) {
		return new Plugboard(cableConnections);
	}

}

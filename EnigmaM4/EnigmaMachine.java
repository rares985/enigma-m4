/**
 * <p> Class which represents the Enigma machine, containing it's
 * components(the rotors, the plugboard and the reflector), as well as
 * methods for their initialisation and encoding messages</p>
 * @author Rares-Andrei Mateizer
 *
 */
public class EnigmaMachine {
	/**
	 * The standard english alphabet 
	 */
	public static final char[] STANDARD_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	/*
	 * The machine's alphabet, can differ from the standard english alphabet
	 */
	public static Alphabet alphabet;
	/**
	 * The machine's components
	 */
	private Reflector reflector;
	private Rotor leftRotor;
	private Rotor middleRotor;
	private Rotor rightRotor;
	private Plugboard plugboard;
	/**
	 * <p>Empty constructor, creates a new machine which has
	 * the standard alphabet</p>
	 */
	public EnigmaMachine() {
		this(STANDARD_ALPHABET);
	}
	/**
	 * <p>Constructor of the class, creates a new machine with
	 * the specified alphabet</p>
	 * @param alphabetToSet the alphabet we want the machine to have
	 */
	public EnigmaMachine(char[] alphabetToSet) {
		alphabet = new Alphabet(alphabetToSet);
	}
	
	public static void setAlphabet(char[] alphabetToSet) {
		alphabet = new Alphabet(alphabetToSet);
	}
	/**
	 * <p>Sets the initial offsets of the rotors to the specified values</p>
	 * @param leftRotorOffset The left rotor's offset
	 * @param middleRotorOffset The middle rotor's offset
	 * @param rightRotorOffset The right rotor's offset
	 */
	public void setOffset(char leftRotorOffset, char middleRotorOffset, char rightRotorOffset) {
		leftRotor.setOffset(leftRotorOffset);
		middleRotor.setOffset(middleRotorOffset);
		rightRotor.setOffset(rightRotorOffset);
	}
	/**
	 * <p>Sets the ring settings of the rotors to the specified values</p>
	 * @param leftRotorRingSetting The left rotor's ring setting
	 * @param middleRotorRingSetting The middle rotor's ring setting
	 * @param rightRotorRingSetting The right rotor's ring setting
	 */
	public void setRings(char leftRotorRingSetting, char middleRotorRingSetting, char rightRotorRingSetting) {
		leftRotor.setRing(leftRotorRingSetting);
		middleRotor.setRing(middleRotorRingSetting);
		rightRotor.setRing(rightRotorRingSetting);
	}
	/**
	 * <p>Creates new rotors, of the specified type</p>
	 * @param leftRotorType The left rotor's type
	 * @param middleRotorType The middle rotor's type
	 * @param rightRotorType The right rotor's type
	 */
	public void initialiseRotors(int leftRotorType, int middleRotorType, int rightRotorType) {
		leftRotor = ComponentFactory.getInstance().createRotor(leftRotorType);
		middleRotor = ComponentFactory.getInstance().createRotor(middleRotorType);
		rightRotor = ComponentFactory.getInstance().createRotor(rightRotorType);
	}
	/**
	 * <p>Creates a new reflector of the specified type</p>
	 * @param reflectorType The reflector's type(B or C)
	 */
	public void initialiseReflector(char reflectorType) {
		reflector = ComponentFactory.getInstance().createReflector(reflectorType);
	}
	/**
	 * Creates a new plugboard with the specified cable connections
	 * @param cableConnections The connections(permutations) between the letters
	 */
	public void initialisePlugboard(String[] cableConnections) {
		plugboard = ComponentFactory.getInstance().createPlugboard(cableConnections);
	}
	/**
	 * Spins the rotors according to the notch mechanism
	 */
	private void spinRotors() {
		rightRotor.spin();
		if (middleRotor.isNextStepNotch()) {
			middleRotor.spin();
			leftRotor.spin();
		}
		if (rightRotor.notchReached()) {
			middleRotor.spin();
		}
	}
	/**
	 * Encodes a letter, by passing it through the machine
	 * @param letter The letter we want to encode
	 * @return The encoded letter
	 */
	public char encode(char letter) {
		if (!alphabet.hasLetter(letter)) {
			return '\0';
		}
		spinRotors();
		char aux = plugboard.mapLetter(letter);
		aux = rightRotor.mapLetter(aux);
		aux = middleRotor.mapLetter(aux);
		aux = leftRotor.mapLetter(aux);
		aux = reflector.mapLetter(aux);
		aux = leftRotor.mapInverse(aux);
		aux = middleRotor.mapInverse(aux);
		aux = rightRotor.mapInverse(aux);
		aux = plugboard.mapLetter(aux);
		if (!alphabet.hasLetter(aux)) {
			return alphabet.getLetter(Utilities.toInt(aux));
		}
		return aux;
	}
	/**
	 * Encodes a message, letter by letter
	 * @param message The message we want to encode
	 * @return The encoded message
	 */
	public String encode(String message) {
		StringBuilder outputMessage = new StringBuilder();
		for (int i = 0; i < message.length(); ++i) {
			outputMessage.append(encode(message.toCharArray()[i]));
		}
		return new String(outputMessage);
	}

}

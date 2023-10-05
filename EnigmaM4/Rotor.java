/**
 * <p>Class which represents an implementation of the Enigma rotors</p>
 * @author rares
 *
 */


public class Rotor extends Component {

	
  private int type;
  private char[] notches;
  private char rotorOffset;
  private char ringSetting;

  /**
   * <p>Creates a new rotor of the specified type.Should never be called directly,
   * but through the <code>ComponentFactory</code> class</p>
   * @param type The type of the rotor 
   * @param mapping The mapping of the rotor(predefined values taken from the Factory class)
   * @param notches The notches of the rotor(predefined values taken from the Factory class)
   */
  public Rotor(int type, char[] mapping, char[] notches) {
    super(mapping);
    this.type = type;
    this.notches = notches;
    rotorOffset = 'A';
    ringSetting = 'A';
  }
  /**
   * Computes whether at the next spin, the rotor will reach its notch.
   * Used for implementing the 
   * @return
   */
  public boolean isNextStepNotch() {
    return (rotorOffset + 1 == notches[0]);
  }
  /**
   * Sets the rotor offset to the specified position
   * @param offset The value to set the offset to
   */
  public void setOffset(char offset) {
    if (mappedAlphabet.hasLetter(offset)) {
      rotorOffset = offset;
    } else {
      rotorOffset = Utilities.toChar(Utilities.toInt(offset));
    }
  }
  /**
   * Sets the rotor ring to the specified position
   * @param setting The value to set the ring to
   */
  public void setRing(char setting) {
    if (mappedAlphabet.hasLetter(setting)) {
      ringSetting = setting;
    } else {
      ringSetting = mappedAlphabet.getLetter(setting - 65);
    }
  }
  /**
   * Spins the rotor. Explicitly, increases the rotor's offset by 1, and
   * keeps it in the machine's  alphabet limits
   */
  public void spin() {
	  rotorOffset ++;
	  rotorOffset = Utilities.moveInAsciiLimits(rotorOffset);
	  rotorOffset = Utilities.toChar(Utilities.toInt(rotorOffset));
  }
  /**
   * Returns the mapping of the letter, if the offset was 0
   * Used in computing the mapping of the letter at various offsets
   * @param letter The letter to encode
   * @return The encoded letter
   */
  public char mapWithoutOffset(char letter) {
    return mappedAlphabet.getLetter(Utilities.toInt(letter));
  }
  /**
   * Returns the inverse mapping of the letter, if the offset was 0
   * Used in computing the inverse mapping of the letter at various offsets
   * @param letter The letter to encode
   * @return The inversely-encoded letter
   */

  public char mapInverseWithoutOffset(char letter) {
    char aux = letter;
    for (int i = 0; i < mappedAlphabet.getLength(); ++i) {
      if (mapWithoutOffset(mappedAlphabet.getLetter(i)) == aux) {
        aux = mappedAlphabet.getLetter(i);
        break;
      }
    }
    return aux;
  }
    
  /**
   * Encodes a letter, keeping it in the machine's alphabet
   */
  @Override
  public char mapLetter(char letter) {
    int diff = rotorOffset - ringSetting;
    char aux = letter;
    aux+= diff;
    aux = Utilities.moveInAsciiLimits(aux);
    aux = mapWithoutOffset(aux);
    aux -= diff;
    aux = Utilities.moveInAsciiLimits(aux);
    return EnigmaMachine.alphabet.getLetter(aux-65);
  }
  /**
   * Says whether this rotor's notch has been reached or not
   * @return true if it was reached, false if not
   */
  public boolean notchReached() {
    return (type <= 5) ? (rotorOffset == notches[0])
        : (rotorOffset == notches[0] || rotorOffset == notches[1]);
  }
  /**
   * Returns the inverse mapping of a letter
   * @param letter The letter we want to encode
   * @return The inversely-encoded letter
   */
  public char mapInverse(char letter) {
    int diff = rotorOffset - ringSetting;
    char aux = letter;
    aux += diff;
    aux = Utilities.moveInAsciiLimits(aux);
    aux = mapInverseWithoutOffset(aux);
    aux -= diff;
    aux = Utilities.moveInAsciiLimits(aux);
    return EnigmaMachine.alphabet.getLetter(Utilities.toInt(aux));
  }

}

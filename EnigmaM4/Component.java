/**
 * <p> Abstract class which has the most general characteristics of
 * an Enigma component. It should never be instantiated, therefore it is abstract, and
 * it's constructors should only be available to the classes that extend it </p>
 * @author Rares-Andrei Mateizer
 *
 */
public abstract class Component {
  
  protected Alphabet mappedAlphabet;
  
  protected Component() {
    
  }
  protected Component(char[] alphabetToSet) {
    mappedAlphabet = new Alphabet(alphabetToSet);
  }
  
  /**
   * <p>Maps a letter according to each component's specification</p>
   * @param letter the letter which will be mapped
   * @return the mapped letter
   */
  public abstract char mapLetter(char letter);
}

public class Reflector extends Component {
  
  
  private char type;
  
  public Reflector(char type, char[] mapping) {
    super(mapping);
    this.type = type;
  }

  @Override
  public char mapLetter(char letter) {
   return mappedAlphabet.getLetter(Utilities.toInt(letter));
  }

}

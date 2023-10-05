
public class Alphabet {
  
  private char[] alphabet;
  
    
  public Alphabet(char[] alphabetToSet) {
    alphabet = new char[alphabetToSet.length];
    alphabet = alphabetToSet;
  }
  public int getLength() {
    return alphabet.length;
  }
  public char getLetter(int pos) {
    int aux = (pos % alphabet.length+alphabet.length)%alphabet.length;
    return alphabet[aux];
  }
  public boolean hasLetter(char letter) {
    for(int i = 0; i < getLength(); ++ i) {
      if(getLetter(i) == letter) {
        return true;
      }
    }
    return false;
  }
  public void setLetter(int pos, char value) {
  	if (pos < 0 || pos > alphabet.length) {
  		int len = alphabet.length;
  		int auxPos = (pos%len+len)%len;
  		alphabet[auxPos] = value;
  		return;
  	}
  	alphabet[pos] = value;
  }
}

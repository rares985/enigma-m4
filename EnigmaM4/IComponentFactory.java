/**
 * 
 * @author Rares-Andrei Mateizer
 * 
 * <p>Interface which describes the behaviour of the ComponentFactory class </p>
 *
 */
public interface IComponentFactory {
  
  /**
   * 
   * @param rotorType the type of the Rotor we want to create
   * @return a new Rotor of the specified type
   */
  public Rotor createRotor(int rotorType);
  /**
   * 
   * @param reflectorType the type of the Reflector we want to create(can be either B or C)
   * @return a new Reflector of the specified type
   */
  public Reflector createReflector(char reflectorType);
  /**
   * 
   * @param cableConnections a String array with the setting of the plugboard
   * @return a new Plugboard with the specified setting
   */
  
  public Plugboard createPlugboard(String[] cableConnections);

}

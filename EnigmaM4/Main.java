/**
 * <p>
 * The main class, which reads the input from a file specified as a command-line
 * parameter and prints the encoded message to System.out
 * </p>
 * 
 * @author Rares-Andrei Mateizer
 *
 */
public class Main {
	public static void main(String[] args) {

		FileParser inputFile = new FileParser(args[0]);
		inputFile.open();

		// Initialisation of the alphabet of the machine

		EnigmaMachine.setAlphabet(inputFile.parseNextLine().trim().toCharArray());
		EnigmaMachine machine = new EnigmaMachine();

		// Initialisation of the plugboard(can also be empty)

		String line = inputFile.parseNextLine();
		if (line.length() == 0) {
			machine.initialisePlugboard(null);
		} else {
			String[] plugboardSetting = line.trim().split("\\s+");
			for (int i = 0; i < plugboardSetting.length; ++i) {
				plugboardSetting[i] = plugboardSetting[i].substring(1, 3);
			}
			machine.initialisePlugboard(plugboardSetting);
		}

		// Reflector and Rotor initialisation

		line = inputFile.parseNextLine().trim();
		String[] aux = line.split("\\s+");
		machine.initialiseReflector(aux[0].charAt(0));
		machine.initialiseRotors(Integer.parseInt(aux[1]), Integer.parseInt(aux[2]), Integer.parseInt(aux[3]));

		// Ring settings(Ringstellungen)

		line = inputFile.parseNextLine();
		line = line.trim();
		aux = line.split("\\s+");
		machine.setRings(aux[0].charAt(0), aux[0].charAt(1), aux[0].charAt(2));

		// Offset settings (Grundstellungen)

		line = inputFile.parseNextLine();
		line = line.trim();
		aux = line.split("\\s+");
		machine.setOffset(aux[0].charAt(0), aux[0].charAt(1), aux[0].charAt(2));

		// Output

		line = inputFile.parseNextLine(); // c/d
		StringBuilder output = new StringBuilder();
		while ((line = inputFile.parseNextLine()) != null) {
			output.append(machine.encode(line.trim()));
		}
		System.out.println(new String(output));
	}
}

/* CYBER SECURITY ASSIGNMENT ENIGMA_SIMULATOR */
public class Reflector {
    private final int[] rotorOut = new int[26]; // Array to store the mapping of input wires to output wires in the reflector

    // Constructor to initialize the reflector with a specified reflector configuration
    protected Reflector(String reflector) {
        setReflector(reflector); // Call the method to set the reflector configuration
    }

    // Method to get the output wire corresponding to the input wire position
    protected int getOutputOf(int pos) {
        return (pos + rotorOut[pos]) % 26; // Calculate the output wire position based on the input position and reflector configuration
    }

    // Method to get the output wire corresponding to the input wire position (alternative method)
    public int getAnOutWire(int pos) {
        return (rotorOut[pos] + pos) % 26; // Calculate the output wire position based on the input position and reflector configuration (alternative method)
    }

    // Method to set the reflector configuration
    public void setReflector(String reflector) {
        for (int i = 0; i < 26; i++) {
            int from = (char) ('A' + i); // Input wire position (character)
            int to = reflector.charAt(i); // Output wire position (character)
            rotorOut[i] = from < to ? to - from : (26 - (from - to)) % 26; // Calculate the mapping from input wire to output wire
        }
    }
}


/* CYBER SECURITY ASSIGNMENT ENIGMA_SIMULATOR */
public class Rotor {
    private final int[] rotorOut = new int[26]; // Array to store the mapping of input wires to output wires in the forward direction
    private final int[] rotorIn = new int[26]; // Array to store the mapping of output wires to input wires in the reverse direction
    private int rotorHead; // Current position of the rotor head (offset from 'A')
    private int ringHead; // Position of the ring head (offset from 'A')
    private char notch; // Notch position of the rotor
    private int rotate; // Number of rotations applied to the rotor

    // Constructor to initialize the rotor with a specified rotor configuration and notch position
    protected Rotor(String rotor, char notch) {
        setRotor(new String[]{rotor, notch + ""}); // Call the method to set the rotor configuration
    }

    // Method to get the output wire corresponding to the input wire position
    protected int getOutputOf(int pos) {
        // Calculate the output wire position based on the input position, rotor configuration, rotor head, and ring head
        int rotorRingDiff = rotorHead >= ringHead ? rotorHead - ringHead : 26 - ringHead + rotorHead;
        return (pos + rotorOut[(pos + rotate + rotorRingDiff) % 26]) % 26;
    }

    // Method to get the input wire corresponding to the output wire position
    protected int getInputOf(int pos) {
        // Calculate the input wire position based on the output position, rotor configuration, rotor head, and ring head
        int rotorRingDiff = rotorHead >= ringHead ? rotorHead - ringHead : 26 - ringHead + rotorHead;
        int posJump = pos - rotorIn[(pos + rotate + rotorRingDiff) % 26];
        return posJump > 0 ? (posJump % 26) : (26 + posJump) % 26;
    }

    // Getter method to retrieve the notch position of the rotor
    public char getNotch() {
        return notch;
    }

    // Getter method to retrieve the current position of the rotor head
    public char getRotorHead() {
        return (char) ('A' + (rotorHead + rotate) % 26);
    }

    // Getter method to retrieve the position of the ring head
    public char getRingHead() {
        return (char) ('A' + (ringHead + rotate) % 26);
    }

    // Method to rotate the rotor by one position
    protected void rotate() {
        rotate = (rotate + 1) % 26; // Update the rotation count
    }

    // Method to set the position of the rotor head
    public void setRotorHead(char c) {
        if (c < 'A' || c > 'Z')
            throw new RuntimeException("Only upper case letters allowed!");
        rotorHead = c - 'A'; // Calculate the offset from 'A' and set it as the rotor head
        rotate = 0; // Reset the rotation count
    }

    // Method to set the position of the ring head
    public void setRingHead(char c) {
        if (c < 'A' || c > 'Z')
            throw new RuntimeException("Only upper case letters allowed!");
        ringHead = c - 'A'; // Calculate the offset from 'A' and set it as the ring head
    }

    // Method to set the rotor configuration
    public void setRotor(String[] rotor) {
        this.notch = rotor[1].charAt(0); // Set the notch position
        for (int i = 0; i < 26; i++) {
            int from = (char) ('A' + i); // Input wire position (character)
            int to = rotor[0].charAt(i); // Output wire position (character)
            // Calculate the mapping from input wire to output wire
            rotorOut[i] = from < to ? to - from : (26 - (from - to)) % 26;
            // Calculate the mapping from output wire to input wire (reverse mapping)
            rotorIn[(i + rotorOut[i]) % 26] = rotorOut[i];
        }
    }

    // Method to get the output wire corresponding to the input wire position (alternative method)
    public int getAnOutWire(int pos) {
        return getOutputOf(pos);
    }

    // Method to get the input wire corresponding to the output wire position (alternative method)
    public int getAnInWire(int pos) {
        return getInputOf(pos);
    }

    // Method to reset the rotor rotation count
    public void reset() {
        rotate = 0; // Reset the rotation count to zero
    }
}

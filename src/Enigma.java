
/* CYBER SECURITY ASSIGNMENT ENIGMA_SIMULATOR */

public class Enigma {
    // Rotors and reflector components of the Enigma machine
    private final Rotor rightRotor; // Right rotor component
    private final Rotor centerRotor; // Center rotor component
    private final Rotor leftRotor; // Left rotor component
    private final Reflector reflector; // Reflector component

    // Plug board settings to map letters
    private final int[] plugboard; // Array to hold plug-board settings

    // Available rotors to choose from with their notch positions
    public static final String[] I = {"EKMFLGDQVZNTOWYHXUSPAIBRCJ", "Q"}; // Rotor I configuration and notch position
    public static final String[] II = {"AJDKSIRUXBLHWTMCQGZNPYFVOE", "E"}; // Rotor II configuration and notch position
    public static final String[] III = {"BDFHJLCPRTXVZNYEIWGAKMUSQO", "V"}; // Rotor III configuration and notch position
    public static final String[] IV = {"ESOVPZJAYQUIRHXLNFTGKDCMWB", "J"}; // Rotor IV configuration and notch position
    public static final String[] V = {"VZBRGITYUPSDNHLXAWMJQOFECK", "Z"}; // Rotor V configuration and notch position

    // Available reflectors to choose from YOU CAN CHOOSE ANY OF THEM A B OR C.
    public static final String A = "EJMZALYXVBWFCRQUONTSPIKHGD"; // Reflector A configuration
    public static final String B = "YRUHQSLDPXNGOKMIEBFZCWVJAT"; // Reflector B configuration
    public static final String C = "FVPJIAOYEDRZXWGCTKUQSBNMHL"; // Reflector C configuration

    // Constructor to initialize the Enigma machine with specified settings
    public Enigma(String[] left, String[] center, String[] right, String ref) {
        // Check if the provided rotor configurations and reflector are valid
        if (correctRotor(left) || correctRotor(center) || correctRotor(right))
            throw new RuntimeException("Please choose a correct Rotor"); // Throw exception if rotor configuration is invalid
        if (!correctReflector(ref))
            throw new RuntimeException("Please choose a correct Reflector"); // Throw exception if reflector configuration is invalid

        // Initialize rotors and reflector based on the provided settings
        this.leftRotor = new Rotor(left[0], left[1].charAt(0)); // Initialize left rotor with configuration and notch position
        this.centerRotor = new Rotor(center[0], center[1].charAt(0)); // Initialize center rotor with configuration and notch position
        this.rightRotor = new Rotor(right[0], right[1].charAt(0)); // Initialize right rotor with configuration and notch position
        this.reflector = new Reflector(ref); // Initialize reflector

        // Initialize plugboard settings
        plugboard = new int[26]; // Create plug-board array to hold mappings
        resetPlugboard(); // Initialize plug-board mappings
    }

    // Method to check if a rotor configuration is valid
    private boolean correctRotor(String[] rotor) {
        return rotor != I && rotor != II && rotor != III && rotor != IV && rotor != V;
        // Returns true if rotor configuration is not one of the predefined configurations
    }

    // Method to check if a reflector configuration is valid
    private boolean correctReflector(String reflector) {
        return reflector.equals(A) || reflector.equals(B) || reflector.equals(C);
        // Returns true if reflector configuration matches one of the predefined configurations
    }
    // Method to encrypt plaintext
    public String type(String text) {
        StringBuilder output = new StringBuilder(text.length()); // Initialize StringBuilder with initial capacity
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i); // Store the current character
            if (c >= 'A' && c <= 'Z') { // Check if character is uppercase letter
                output.append(rotorsEncryption(c)); // Encrypt the character using rotor settings
            } else if (c == ' ' || c == '\n') { // Preserve spaces and newlines
                output.append(c);
            } else {
                throw new RuntimeException("Only upper case letters allowed!"); // Throw exception for non-uppercase letters
            }
        }
        return output.toString(); // Return the encrypted text
    }

    // Method to decrypt ciphertext
    public String decrypt(String text) {
        StringBuilder output = new StringBuilder(); // Initialize a StringBuilder to store the decrypted text

        // Reset rotor positions before decryption
        leftRotor.reset(); // Reset left rotor position
        centerRotor.reset(); // Reset center rotor position
        rightRotor.reset(); // Reset right rotor position

        // Perform decryption for each character in the ciphertext
        for (int i = 0; i < text.length(); i++) {
            char encryptedChar = text.charAt(i); // Get the current character from the ciphertext
            if (encryptedChar >= 'A' && encryptedChar <= 'Z') { // Check if character is uppercase letter
                char decryptedChar = rotorsEncryption(encryptedChar); // Decrypt the character using rotor settings
                output.append(decryptedChar); // Append the decrypted character to the output
            } else if (encryptedChar == ' ' || encryptedChar == '\n') { // Preserve spaces and newlines
                output.append(encryptedChar);
            } else {
                throw new RuntimeException("Only upper case letters allowed!"); // Throw exception for non-uppercase letters
            }
        }

        // Reset rotor positions after decryption
        leftRotor.reset(); // Reset left rotor position
        centerRotor.reset(); // Reset center rotor position
        rightRotor.reset(); // Reset right rotor position

        return output.toString(); // Return the decrypted text
    }

    // Method to encrypt/decrypt a single character using rotor settings
    private char rotorsEncryption(char inputC) {
        // Rotate rotors based on notch positions
        if (centerRotor.getNotch() == centerRotor.getRotorHead()) { // If the center rotor's notch is at the rotor head position
            leftRotor.rotate(); // Rotate the left rotor
            centerRotor.rotate(); // Rotate the center rotor
        }
        if (rightRotor.getNotch() == rightRotor.getRotorHead()) // If the right rotor's notch is at the rotor head position
            centerRotor.rotate(); // Rotate the center rotor
        rightRotor.rotate(); // Rotate the right rotor

        int input = inputC - 'A'; // Convert character to integer representation

        // Apply plugboard settings
        if (plugboard[input] != -1) // If there is a mapping in the plugboard for the input character
            input = plugboard[input]; // Apply the plugboard mapping

        // Pass through rotors and reflector
        int outOfRightRotor = rightRotor.getOutputOf(input); // Get output from right rotor
        int outOfCenterRotor = centerRotor.getOutputOf(outOfRightRotor); // Get output from center rotor
        int outOfLeftRotor = leftRotor.getOutputOf(outOfCenterRotor); // Get output from left rotor
        int outOfReflector = reflector.getOutputOf(outOfLeftRotor); // Get output from reflector
        int inOfLeftRotor = leftRotor.getInputOf(outOfReflector); // Get input to left rotor
        int inOfCenterRotor = centerRotor.getInputOf(inOfLeftRotor); // Get input to center rotor
        int inOfRightRotor = rightRotor.getInputOf(inOfCenterRotor); // Get input to right rotor

        // Apply plug-board settings
        if (plugboard[inOfRightRotor] != -1) // If there is a mapping in the plug-board for the output character
            inOfRightRotor = plugboard[inOfRightRotor]; // Apply the plug-board mapping

        return (char) (inOfRightRotor + 'A'); // Convert integer back to character representation
    }

    // Getter methods for rotors and reflector
    public Rotor getLeftRotor() {
        return leftRotor; // Return left rotor component
    }

    public Rotor getCenterRotor() {
        return centerRotor; // Return center rotor component
    }

    public Rotor getRightRotor() {
        return rightRotor; // Return right rotor component
    }
    public void resetPlugboard() {
        for (int wire = 0; wire < 26; wire++)
            this.plugboard[wire] = -1; // Reset plugboard mappings
    }

    // Method to check if a character is affected by plug-board settings
    public boolean isPlugged(char c) {
        return plugboard[c - 'A'] != -1; // Check if character 'c' is mapped in plugboard
    }

    // Method to reset rotor positions
    public void resetRotation() {
        leftRotor.reset(); // Reset left rotor position
        centerRotor.reset(); // Reset center rotor position
        rightRotor.reset(); // Reset right rotor position
    }
}


/* CYBER SECURITY ASSIGNMENT ENIGMA_SIMULATOR */

import java.util.Scanner;

public class EnigmaTerminal {

    // Method to initialize Enigma with default settings
    private static Enigma initializeEnigma() {
        // Default rotor configurations and reflector type for Enigma machine
        String[] leftRotor = Enigma.I;
        String[] centerRotor = Enigma.II;
        String[] rightRotor = Enigma.III;
        String reflector = Enigma.C;

        // Create Enigma machine with default settings
        Enigma enigma = new Enigma(leftRotor, centerRotor, rightRotor, reflector);

        // Set initial rotor positions to 'A'
        enigma.getLeftRotor().setRotorHead('A');
        enigma.getCenterRotor().setRotorHead('A');
        enigma.getRightRotor().setRotorHead('A');

        // Set Initial Ring Head
        enigma.getLeftRotor().setRingHead('A');
        enigma.getCenterRotor().setRingHead('A');
        enigma.getRightRotor().setRingHead('A');

        return enigma;


    }

    // Main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Main loop for the EnigmaTerminal program
        while (true) {
            // Initialize a new Enigma machine
            Enigma enigma = initializeEnigma();

            // Display options for the user
            System.out.println("Select an option:");
            System.out.println("PRESS 1 to Encrypt your message");
            System.out.println("PRESS 2 TO Decrypt your message");

            // Read user's choice
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline character after reading integer input

            // Process user's choice
            if (option == 1) { // Encryption option
                System.out.println("Enter PLAINTEXT in capital letters to encrypt (press Enter twice to finish):");
                StringBuilder messageBuilder = new StringBuilder();
                String line;
                // Read input lines until an empty line is encountered
                while (!(line = scanner.nextLine()).isEmpty()) {
                    messageBuilder.append(line);
                }
                // Encrypt the accumulated plaintext message
                String message = messageBuilder.toString();
                String encryptedMessage = enigma.type(message);
                System.out.println("\nCiphertext is: " + encryptedMessage);
            } else if (option == 2) { // Decryption option
                System.out.println("Enter CIPHERTEXT in capital letters to decrypt (press Enter twice to finish):");
                StringBuilder messageBuilder = new StringBuilder();
                String line;
                // Read input lines until an empty line is encountered
                while (!(line = scanner.nextLine()).isEmpty()) {
                    messageBuilder.append(line);
                }
                // Decrypt the accumulated ciphertext message
                String message = messageBuilder.toString();
                String decryptedMessage = enigma.decrypt(message);
                System.out.println("Plaintext is: " + decryptedMessage);
            } else { // Invalid option
                System.out.println("Invalid option. Please enter 1 to encrypt or 2 to decrypt.");
                continue; // Continue to the next iteration of the loop
            }

            // Ask if the user wants to perform another operation
            System.out.println("Do you want to perform another operation? (yes/no):");
            String continueOption = scanner.nextLine().toLowerCase();
            // If the user enters "no", exit the loop and end the program
            if (continueOption.equalsIgnoreCase("no")) {
                break;
            }
        }

        // Close the scanner to release system resources
        scanner.close();
    }
}

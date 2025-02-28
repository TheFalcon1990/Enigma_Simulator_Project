# Enigma Machine Simulator

## Overview
This project is an Enigma Machine Simulator that replicates the functionality of the famous cipher machine used during World War II. It allows users to encrypt and decrypt messages using the same rotor-based encryption system as the historical Enigma.

## Features
- Fully functional Enigma encryption and decryption
- Customizable rotors and reflector settings
- Plugboard for additional security
- User-friendly command-line interface
- Realistic historical accuracy

## Installation
### Prerequisites
Ensure you have the following installed on your system:
- Java Development Kit (JDK) 8 or later

### Steps
1. Clone this repository:
   ```sh
   git clone https://github.com/TheFalcon1990/Enigma_Simulator_Project.git
   ```
2. Navigate to the project directory:
   ```sh
   cd enigma-simulator
   ```
3. Compile the Java files:
   ```sh
   javac src/*.java -d bin
   ```
4. Run the simulator:
   ```sh
   java -cp bin EnigmaSimulator
   ```

## Usage
1. Set up the Enigma machine by configuring rotors, reflectors, and plugboard settings.
2. Enter a message to encrypt or decrypt.
3. View the output message.

Example usage:
```sh
java -cp bin EnigmaSimulator --rotors I II III --reflector B --plugboard AB CD EF
```

## Configuration
- **Rotors**: Choose from historical rotors (I, II, III, IV, V, etc.)
- **Reflector**: Select from available reflectors (A, B, C)
- **Plugboard**: Specify letter pairs for additional scrambling

## License
This project is licensed under the MIT License.

## Contributing
Contributions are welcome! Feel free to submit issues or pull requests.

## Contact
For any questions or feedback, reach out at [your email] or open an issue on GitHub.


import java.util.Random;
import java.util.Scanner;

public class PasswordGenerator {
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()-_=+[]{}|;:'\",.<>/?";

    public static String generatePassword(int length, boolean useLowercase, boolean useUppercase, boolean useNumbers, boolean useSymbols) {
        StringBuilder passwordCharacters = new StringBuilder();

        if (useLowercase) {
            passwordCharacters.append(LOWERCASE);
        }
        if (useUppercase) {
            passwordCharacters.append(UPPERCASE);
        }
        if (useNumbers) {
            passwordCharacters.append(DIGITS);
        }
        if (useSymbols) {
            passwordCharacters.append(SYMBOLS);
        }

        if (passwordCharacters.length() == 0) {
            throw new IllegalArgumentException("At least one character type must be selected.");
        }

        Random random = new Random();
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(passwordCharacters.length());
            char randomCharacter = passwordCharacters.charAt(randomIndex);
            password.append(randomCharacter);
        }

        return password.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int length;
        do {
            System.out.print("Enter the desired password length (greater than 0): ");
            length = scanner.nextInt();
        } while (length <= 0);

        boolean useLowercase = false;
        boolean useUppercase = false;
        boolean useNumbers = false;
        boolean useSymbols = false;

        while (!useLowercase && !useUppercase && !useNumbers && !useSymbols) {
            System.out.print("Include lowercase letters? (true/false): ");
            useLowercase = getBooleanInput(scanner);

            System.out.print("Include uppercase letters? (true/false): ");
            useUppercase = getBooleanInput(scanner);

            System.out.print("Include numbers? (true/false): ");
            useNumbers = getBooleanInput(scanner);

            System.out.print("Include symbols? (true/false): ");
            useSymbols = getBooleanInput(scanner);

            if (!useLowercase && !useUppercase && !useNumbers && !useSymbols) {
                System.out.println("Invalid input. Please select at least one character type.");
            }
        }

        String password = generatePassword(length, useLowercase, useUppercase, useNumbers, useSymbols);
        System.out.println("Generated Password: " + password);

        scanner.close();
    }

    private static boolean getBooleanInput(Scanner scanner) {
        while (true) {
            String input = scanner.next().toLowerCase();
            if (input.equals("true")) {
                return true;
            } else if (input.equals("false")) {
                return false;
            } else {
                System.out.print("Invalid input. Please enter 'true' or 'false': ");
            }
        }
    }
}

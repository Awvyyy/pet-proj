package main;

import java.io.*;
import java.util.Scanner;

public class AuthService {

    private static final String USERS_FILE = "users.txt";

    public static int readMenuOptions(Scanner scanner) {
        int op;
        while (true) {
            while (!scanner.hasNextInt()) {
                System.err.println("Enter a number!");
                scanner.nextLine();
            }
            op = scanner.nextInt();
            if (op < 1 || op > 4) {
                System.err.println("Number must be between 1 and 4!");
            } else {
                break;
            }
        }
        return op;
    }

    public static void handleLogin(Scanner scanner) {
        System.out.println();
        System.out.print("Enter name: ");
        String username = scanner.nextLine().trim();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.println();

        String stored = findStoredPasswordByUsername(username);

        if (stored == null) {
            System.err.println("User not found!");
            return;
        }

        if (stored.equals(password)) {
            System.out.println("Logging in");
        } else {
            System.err.println("Password is incorrect!");
        }
    }

    public static void handleRegister(Scanner scanner) {
        System.out.print("Enter name: ");
        String username = scanner.nextLine().trim();

        // guard 1: username validation
        if (username.isEmpty()) {
            System.err.println("Username can't be blank.");
            return;
        }
        if (username.contains(":")) {
            System.err.println("':' is prohibited in username.");
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // guard 2: password validation
        if (password.trim().isEmpty()) {
            System.err.println("Password can't be blank.");
            return;
        }
        if (password.contains(":")) {
            System.err.println("':' is prohibited in password.");
            return;
        }

        System.out.println();

        // guard 3: unique username
        if (findStoredPasswordByUsername(username) != null) {
            System.err.println("User exists");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE, true))) {
            writer.write(username + ":" + password);
            writer.newLine();
            System.out.println("Registered successfully!");
        } catch (IOException e) {
            System.err.println("Error writing file!");
        }
    }

    public static String findStoredPasswordByUsername(String username) {
        String inputUsername = username.trim();
        if (inputUsername.isEmpty()) return null;

        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] part = line.split(":", 2);
                if (part.length < 2) continue;

                String storedUsername = part[0].trim();
                if (storedUsername.isEmpty()) continue;

                if (storedUsername.equals(inputUsername)) {
                    return part[1]; //todo HASH
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file!");
        }
        return null;
    }
}
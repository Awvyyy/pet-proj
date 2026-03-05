package main;

import java.io.*;
import java.util.Scanner;

import static main.FileUserRepository.saveUser;
import static main.PasswordHasher.hash;
import static main.PasswordHasher.verify;
import static main.UserRepository.findHashByUsername;

public class AuthService {

    public static int readMenuOptions(Scanner scanner) {
        int op;
        while (true) {
            while (!scanner.hasNextInt()) {
                System.err.println("Enter a number!");
                scanner.nextLine();
            }
            op = scanner.nextInt();
            if (op < 1 || op > 6) {
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

        String stored = findHashByUsername(username);

        if (stored == null) {
            System.err.println("User not found!");
            return;
        }

        if (verify(password, stored)) {
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
        if (findHashByUsername(username) != null){
            System.err.println("User exists.");
            System.out.println();
            return;
        }
        saveUser(username, hash(password));
        System.out.println("Registered successfully!");
    }
}
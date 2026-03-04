package main;
import java.io.*;
import java.util.Scanner;
import static main.AuthService.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("1 - Login");
            System.out.println("2 - Register");
            System.out.println("3 - Check user's existence");
            System.out.println("4 - Exit");
            System.out.print("Choose option: ");
            int operation = AuthService.readMenuOptions(scanner);
            scanner.nextLine();
            switch (operation) {
                case 1: {
                    handleLogin(scanner);
                }
                break;
                case 2: {
                    handleRegister(scanner);
                }
                break;
                case 3: {
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    String storedUsername = findStoredPasswordByUsername(username);
                    if (storedUsername == null) System.err.println("User not found");
                    else System.err.println("User exists");
                }
                break;
                case 4: {
                        System.out.println();
                        System.out.println("Exiting...");
                        running = false;
                        break;
                    }
                default: {
                    System.err.println("Error!");
                    System.out.println();
                }
                }
            }
        }
    }
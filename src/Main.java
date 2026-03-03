import java.io.*;
import java.util.Scanner;
import java.io.BufferedWriter;

public class Main {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String name;
        String pass;
        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("1 - Login");
            System.out.println("2 - Register");
            System.out.println("3 - Exit");


            System.out.print("Choose option: ");
            int op = scanner.nextInt();
            scanner.nextLine();
            switch (op) {
                case 1: {
                    // login
                    System.out.println();
                    System.out.print("Enter name: ");
                    String nameTemp = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String passTemp = scanner.nextLine();
                    String target = nameTemp + ":" + passTemp;
                    System.out.println();

                    boolean userFound = false;
                    boolean passCorrect = false;

                    // reader
                    try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            String[] part = line.split(":");
                            // check if user exists
                            if (nameTemp.equals(part[0])) {
                                userFound = true;

                                if (passTemp.equals(part[1])) {
                                    passCorrect = true;
                                    System.out.println("Logging in...");
                                }
                                break;
                            }
                            //check if user exists
                        }
                    } catch (IOException e) {
                        System.out.println("Error reading file!");
                    }
                    if (!userFound) {
                        System.out.println("User not found!");
                    }

                    else if (!passCorrect) {
                        System.out.println("Password is incorrect!");
                    }
                    // reader

                }
                break;

                case 2: {
                    // register
                    System.out.print("Enter name: ");
                    String nameTemp = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String passTemp = scanner.nextLine();
                    System.out.println();
                    boolean userExists = false;

                    try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
                        String line;

                        while ((line = reader.readLine()) != null) {

                            String[] part = line.split(":");
                            if (part[0].equals(nameTemp)) {
                                userExists = true;
                                System.out.println("User exists!");
                                break;
                            }
                        }
                    }
                        catch(IOException e){
                            System.out.println("Error reading file!");
                        }

                    // writer
                    if (!userExists) {
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
                            writer.write(nameTemp + ":" + passTemp);
                            writer.newLine();
                            System.out.println("Registered successfully!");
                            break;

                        } catch (IOException e) {
                            System.out.println("Error writing file!");
                        }
                    }
                    break;
                    // writer
                    }
                    case 3: {
                        System.out.println();
                        System.out.println("Exiting...");
                        running = false;
                        break;
                    }
                default: {
                    System.out.println("Error! Enter number 1 - 3.");
                    System.out.println();
                }
                }
            }
        }
    }
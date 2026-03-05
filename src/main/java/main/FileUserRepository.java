package main;

import java.io.*;

public class FileUserRepository {

    private static final String USERS_FILE = "user.txt";

    public static void saveUser(String username, String hash) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE, true))) {
            writer.write(username + ":" + hash);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing file!");
        }
    }

    public static String readUser(String username) {
        String inputUsername = username.trim();
        if (inputUsername.isEmpty()) return null;

        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] part = line.split(":", 2);
                if (part.length < 2) continue;

                String storedUsername = part[0].trim();
                String storedHash = part[1];
                if (storedUsername.isEmpty()) continue;

                if (storedUsername.equals(inputUsername)) {
                    return storedHash;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file!");
        }
        return null;
    }
}



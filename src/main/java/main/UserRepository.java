package main;

import java.io.*;

import static main.FileUserRepository.readUser;
import static main.FileUserRepository.saveUser;
import static main.PasswordHasher.hash;

public class UserRepository {


    public static String findHashByUsername(String username) {
        return readUser(username);
    }

    public static void userExists(String username, String password){

        if (findHashByUsername(username) != null) {
            System.err.println("User exists");
            return;
        }
        else
            saveUser(username, hash(password));

    }
}


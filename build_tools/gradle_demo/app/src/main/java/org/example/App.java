/*
 * Author: Luís Nogueira (lmn@isep.ipp.pt)
 * Created on: April 2026
 */

package org.example;

/**
 * A simple app that launches a chat room server and two clients.
 * Provides instructions for running the multi-user chat application.
 */
public class App {
    /**
     * Gets a welcome greeting message for the chat application.
     * 
     * @return a greeting message string for the Multi-User Chat Application
     */
    public String getGreeting() {
        return "\nWelcome to \"Multi-User Chat Application\"!\n";
    }

    /**
     * Main entry point for the application.
     * Displays instructions for running the chat server and client applications.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println(new App().getGreeting());

        System.out.println("You can run the server by entering the following command:\n");
        System.out.println("java -cp \"app/build/libs/chat-server-1.0.jar:app/build/libs/lib/*\" org.example.ChatServerApp <port number>");

        System.out.println("\nYou can run the client by entering the following command:\n");
        System.out.println("java -cp \"app/build/libs/chat-server-1.0.jar:app/build/libs/lib/*\" org.example.ChatClientApp <server IP> <server port number>");

        System.out.println("\nTry this application by first running the server and then several clients.");
    }
}

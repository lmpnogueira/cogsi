/*
 * Author: Luís Nogueira (lmn@isep.ipp.pt)
 * Created on: April 2026
 */

package org.example;

/**
 * A launcher application for the multithreaded chat room server.
 * Accepts the server port number as a command line argument.
 */
public class ChatServerApp{

    /**
     * Main entry point for the chat server application.
     * Creates and starts the chat server on the specified port.
     * 
     * @param args command line arguments: [0] server port number (required)
     * @throws Exception if an error occurs during server initialization
     */
    public static void main(String[] args) throws Exception {

        if (args.length != 1) {
            System.err.println("Pass the server port as the sole command line argument");
            return;
        }

        int serverPort = Integer.parseInt(args[0]);

        ChatServer chatServer = new ChatServer(serverPort);
        Thread t = new Thread(chatServer,"Chat Server Main Thread");        
        t.start();

        System.out.println("The chat server is running...");
    }

}
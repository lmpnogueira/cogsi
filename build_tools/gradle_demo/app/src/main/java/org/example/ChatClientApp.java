/*
 * Author: Luís Nogueira (lmn@isep.ipp.pt)
 * Created on: April 2026
 */

package org.example;

/**
 * A launcher application for the chat client that connects to a chat room server.
 * Accepts server IP address and port number as command line arguments.
 */
public class ChatClientApp {

    /**
     * Main entry point for the chat client application.
     * Creates and starts a chat client thread connected to the specified server.
     * 
     * @param args command line arguments: [0] server IP address, [1] server port number
     *             Both arguments are required
     */
    public static void main(String[] args) {
        
        if (args.length != 2) {
            System.err.println("Pass the server IP and Port as command line arguments");
            return;
        }

        int serverPort = Integer.parseInt(args[1]);
        ChatClient client = new ChatClient(args[0], serverPort);

        Thread t = new Thread(client,"Chat client thread");
        t.start();
     }
    
}
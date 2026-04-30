/*
 * Author: Luís Nogueira (lmn@isep.ipp.pt)
 * Created on: April 2026
 */

package org.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * A multithreaded chat room server that manages multiple client connections.
 * When a client connects, the server requests a unique screen name. Once a name
 * is accepted, all messages from the client are broadcast to all other connected
 * clients. This implementation uses a thread pool to handle multiple concurrent clients.
 */
public class ChatServer implements Runnable{

    /** All client names, used to check for duplicates upon registration. */
    private static Set<String> names;

    /** The set of all print writers for all clients, used for message broadcasting. */
    private static Set<PrintWriter> writers;

    /** The port number on which the server listens for client connections. */
    private int serverPort;

    /**
     * Constructs a chat server that listens on the specified port.
     * Initializes the sets for managing client names and output writers.
     * 
     * @param serverPort the port number to listen on for client connections
     */
    public ChatServer(int serverPort){
        names = new HashSet<String>();
        writers = new HashSet<PrintWriter>();
        this.serverPort = serverPort;
    }

    /**
     * Runs the server by creating a thread pool and accepting client connections.
     * Each client connection is handled by a separate Handler thread.
     */
    public void run() {
        ExecutorService pool = Executors.newFixedThreadPool(500);
        ServerSocket listener;
        try {
            listener = new ServerSocket(serverPort);
            while (true) {
                pool.execute(new Handler(listener.accept()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }

    /**
     * Inner class that handles a single client connection.
     * Manages name submission, name uniqueness validation, and message broadcasting.
     */
    private static class Handler implements Runnable {
        private String name;
        private Socket socket;
        private Scanner in;
        private PrintWriter out;

        /** Logger for client connection and disconnection events. */
        private final static Logger LOGGER = LogManager.getLogger(Handler.class);

        /**
         * Constructs a handler thread for a client connection.
         * The socket is stored for later use in the run method.
         * 
         * @param socket the client socket connection
         */
        public Handler(Socket socket) {
            this.socket = socket;
        }

        /**
         * Services this thread's client by repeatedly requesting a screen name until a
         * unique one has been submitted, then acknowledges the name and registers the
         * output stream for the client in a global set, then repeatedly gets inputs and
         * broadcasts them.
         */
        public void run() {
            try {
                in = new Scanner(socket.getInputStream());
                out = new PrintWriter(socket.getOutputStream(), true);

                // Keep requesting a name until we get a unique one.
                while (true) {
                    out.println("SUBMITNAME");
                    name = in.nextLine();
                    if (name == null) {
                        return;
                    }
                    synchronized (names) {
                        if (!name.isEmpty() && !names.contains(name)) {
                            names.add(name);
                            LOGGER.info("A new user has joined: " + name);
                            break;
                        }
                    }
                }

                // Now that a successful name has been chosen, add the socket's print writer
                // to the set of all writers so this client can receive broadcast messages.
                // But BEFORE THAT, let everyone else know that the new person has joined!
                out.println("NAMEACCEPTED " + name);
                for (PrintWriter writer : writers) {
                    writer.println("MESSAGE " + name + " has joined");
                }
                synchronized(writers){
                    writers.add(out);
                }
 
                // Accept messages from this client and broadcast them.
                while (true) {
                    String input = in.nextLine();
                    if (input.toLowerCase().startsWith("/quit")) {
                        return;
                    }
                    for (PrintWriter writer : writers) {
                        writer.println("MESSAGE " + name + ": " + input);
                    }
                }
            } catch (NoSuchElementException e){
                return;
            }
            catch (Exception e) {
                System.out.println(e);
            } finally {
                if (out != null) {
                    synchronized(writers){
                       writers.remove(out);
                    }
                }
                if (name != null) {
                    LOGGER.info(name + " has left the chat");
                    synchronized(names){
                        names.remove(name);
                    }
                    for (PrintWriter writer : writers) {
                        writer.println("MESSAGE " + name + " has left");
                    }
                }
                try { socket.close(); } catch (IOException e) {}
            }
        }
    }
}
/*
 * Author: Luís Nogueira (lmn@isep.ipp.pt)
 * Created on: July 2024
 */

package org.example;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A Swing-based client for connecting to a chat server.
 * Provides a graphical interface with a text field for entering messages and a text area
 * to display the chat dialog. Implements the chat protocol with SUBMITNAME, NAMEACCEPTED,
 * and MESSAGE commands.
 */

public class ChatClient implements Runnable{

    private String serverAddress;
    private int serverPort;
    private Scanner in;
    private PrintWriter out;
    private JFrame frame = new JFrame("Chatter");
    private JTextField textField = new JTextField(50);
    private JTextArea messageArea = new JTextArea(16, 50);

    /**
     * Constructs the client by laying out the GUI and registering a listener with the
     * textfield so that pressing Return in the listener sends the textfield contents
     * to the server. Note however that the textfield is initially NOT editable, and
     * only becomes editable AFTER the client receives the NAMEACCEPTED message from
     * the server.
     */
    public ChatClient(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;

        textField.setEditable(false);
        messageArea.setEditable(false);
        frame.getContentPane().add(textField, BorderLayout.SOUTH);
        frame.getContentPane().add(new JScrollPane(messageArea), BorderLayout.CENTER);
        frame.pack();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Send on enter then clear to prepare for next message
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                out.println(textField.getText());
                textField.setText("");
            }
        });
    }

    /**
     * Prompts the user to enter their screen name via a dialog box.
     * 
     * @return the screen name entered by the user
     */
    private String getName() {
        return JOptionPane.showInputDialog(
            frame,
            "Choose a screen name:",
            "Screen name selection",
            JOptionPane.PLAIN_MESSAGE
        );
    }

    /**
     * Runs the client thread. Establishes connection to the server and handles
     * incoming messages according to the chat protocol. Processes SUBMITNAME,
     * NAMEACCEPTED, and MESSAGE commands from the server.
     */
    public void run() {
        try {
            Socket socket = new Socket(serverAddress, serverPort);
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);

            while (in.hasNextLine()) {
                String line = in.nextLine();
                if (line.startsWith("SUBMITNAME")) {
                    out.println(getName());
                } else if (line.startsWith("NAMEACCEPTED")) {
                    this.frame.setTitle("Chatter - " + line.substring(13));
                    textField.setEditable(true);
                } else if (line.startsWith("MESSAGE")) {
                    messageArea.append(line.substring(8) + "\n");
                }
            }

            socket.close();
        }
        catch(IOException e){
            e.printStackTrace();
        } finally {
            frame.setVisible(false);
            frame.dispose();
        }
    }

    
}
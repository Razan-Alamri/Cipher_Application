/*
 * Nmae: Razan Arif Alamri
 * ID:  
 * Section: IAR
 * Course Name: Information Security
 * Course ID: CPCS 425
 * Term: Fall 2023
 * Course Instructor: Dr. Reemah Alhebshi
 * Project: Cipher Application
 */

/**
 *
 * @author Rz 
 */
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class CipherApp {
    private JFrame frame;
    private JButton encryptFilesButton;
    private JButton decryptFilesButton;
    private JButton terminateButton;

    public CipherApp() {
        // Create the main application frame
        frame = new JFrame("Encrypted Files Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.decode("#4F71BE")); // Set frame background color
        Dimension frameSize = new Dimension(600, 200);
        frame.setMinimumSize(frameSize);

        // Create the app title label
        JLabel appTitleLabel = new JLabel("Encrypted Files Application");
        appTitleLabel.setFont(new Font("Calibri (Body)", Font.PLAIN, 30));
        appTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        appTitleLabel.setForeground(Color.WHITE);
        Dimension appTitleLabelSize = new Dimension(500, 40);
        appTitleLabel.setMaximumSize(appTitleLabelSize);
        frame.add(appTitleLabel);

        // Add space before Label
        frame.add(Box.createVerticalStrut(20));
        frame.add(Box.createHorizontalStrut(20));

        // Create the choose action label
        JLabel chooseActionLabel = new JLabel("Select Buttons");
        chooseActionLabel.setFont(new Font("Calibri (Body)", Font.PLAIN, 25));
        chooseActionLabel.setForeground(Color.WHITE);
        chooseActionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(chooseActionLabel);

        // Create the buttons
        encryptFilesButton = new JButton("Encrypt Files");
        decryptFilesButton = new JButton("Decrypt Files");
        terminateButton = new JButton("Exit");

        Font buttonFont = new Font("Calibri (Body)", Font.PLAIN, 20);
        Color buttonColor = Color.decode("#F5C342"); // Set button background color
        Dimension buttonSize = new Dimension(200, 40);

        // Set button properties
        encryptFilesButton.setFont(buttonFont);
        encryptFilesButton.setForeground(Color.WHITE);
        encryptFilesButton.setBackground(buttonColor);
        encryptFilesButton.setPreferredSize(buttonSize);
        encryptFilesButton.setMaximumSize(buttonSize); // Set maximum button size

        decryptFilesButton.setFont(buttonFont);
        decryptFilesButton.setForeground(Color.WHITE);
        decryptFilesButton.setBackground(buttonColor);
        decryptFilesButton.setPreferredSize(buttonSize);
        decryptFilesButton.setMaximumSize(buttonSize); // Set maximum button size

        terminateButton.setFont(buttonFont);
        terminateButton.setForeground(Color.WHITE);
        terminateButton.setBackground(buttonColor);
        terminateButton.setPreferredSize(buttonSize);
        terminateButton.setMaximumSize(buttonSize); // Set maximum button size

        // Add action listeners to the buttons
        encryptFilesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleEncryptFiles();
            }
        });

        decryptFilesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDecryptFiles();
            }
        });

        terminateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Add buttons to the frame
        frame.add(Box.createVerticalStrut(20)); // Add vertical space before the first button
        frame.add(encryptFilesButton);
        frame.add(Box.createVerticalStrut(20)); // Add vertical space between buttons
        frame.add(decryptFilesButton);
        frame.add(Box.createVerticalStrut(20)); // Add vertical space between buttons
        frame.add(terminateButton);
        frame.add(Box.createVerticalStrut(20)); // Add vertical space after the last button
        frame.pack();
        frame.setVisible(true);
    }

    // Method to handle encrypting files
    private void handleEncryptFiles() {
        JFileChooser filePicker = new JFileChooser();
        int selectionResult = filePicker.showOpenDialog(frame);

        if (selectionResult == JFileChooser.APPROVE_OPTION) {
            File selectedFile = filePicker.getSelectedFile();
            String destination = showSaveDialog();

            if (destination != null) {
                try {
                    BufferedReader inputFileReader = new BufferedReader(new FileReader(selectedFile));
                    PrintWriter encryptedFileWriter = new PrintWriter(new FileWriter(destination));

                    Cipher cipher = new Cipher();
                    cipher.encrypt(inputFileReader, encryptedFileWriter);

                    inputFileReader.close();
                    encryptedFileWriter.close();

                    // Show a dialog indicating successful encryption
                    showMessageDialog("The file was encrypted.", "Encryption Complete",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    // Show a dialog indicating an error occurred during encryption
                    showMessageDialog("An error occurred: " + e.getMessage(), "Encryption Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    // Method to handle decrypting files
    private void handleDecryptFiles() {
        JFileChooser filePicker = new JFileChooser();
        int selectionResult = filePicker.showOpenDialog(frame);

        if (selectionResult == JFileChooser.APPROVE_OPTION) {
            File selectedFile = filePicker.getSelectedFile();
            String destination = showSaveDialog();

            if (destination != null) {
                try {
                    BufferedReader encryptedFileReader = new BufferedReader(new FileReader(selectedFile));
                    PrintWriter decryptedFileWriter = new PrintWriter(new FileWriter(destination));

                    Cipher cipher = new Cipher();
                    cipher.decrypt(encryptedFileReader, decryptedFileWriter);

                    encryptedFileReader.close();
                    decryptedFileWriter.close();

                    // Show a dialog indicating successful decryption
                    showMessageDialog("The file was decrypted.", "Decryption Complete",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    // Show a dialog indicating an error occurred during decryption
                    showMessageDialog("An error occurred: " + e.getMessage(), "Decryption Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    // Method to show a save dialog and return the selected file path
    private String showSaveDialog() {
        JFileChooser filePicker = new JFileChooser();
        int selectionResult = filePicker.showSaveDialog(frame);

        if (selectionResult == JFileChooser.APPROVE_OPTION) {
            File selectedFile = filePicker.getSelectedFile();
            return selectedFile.getAbsolutePath();
        }

        return null;
    }

    // Method to show a message dialog with the specified message and title
    // Custom dialog with frame style
    private void showMessageDialog(String message, String title, int messageType) {
        JDialog dialog = new JDialog(frame, title, true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(frame);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.decode("#4F71BE"));

        JLabel messageLabel = new JLabel(message);
        messageLabel.setFont(new Font("Calibri (Body)", Font.PLAIN, 18));
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(messageLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setOpaque(false);

        JButton closeButton = new JButton("OK");
        closeButton.setFont(new Font("Calibri (Body)", Font.PLAIN, 16));
        closeButton.setForeground(Color.WHITE);
        closeButton.setBackground(Color.decode("#E5813E"));
        closeButton.setPreferredSize(new Dimension(120, 40));
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        buttonPanel.add(closeButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        dialog.add(panel);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CipherApp();
            }
        });
    }
}
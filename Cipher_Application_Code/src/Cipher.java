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

import java.io.*;

public class Cipher {
    /*
     * The Cipher.encrypt() method must correctly translate all of the input
     * characters from the provided
     * message inStream, and produce the corresponding encrypted output on outStream
     */
    public void encrypt(BufferedReader inStream, PrintWriter outStream) throws Exception {
        String readPlainText;
        // Read each line from the input stream until the end of the file is reached
        while ((readPlainText = inStream.readLine()) != null) {
            // Check if the line length is at least 4 characters
            if (readPlainText.length() >= 4) {
                // Perform the encryption operation on the plain text

                // Step 1: Remove any leading or trailing whitespace from the line.
                readPlainText = readPlainText.trim();

                // Step 2: Convert all letters in the string to UPPERCASE.
                readPlainText = readPlainText.toUpperCase();

                // Step 3: Move the first half of the string to be the last half.
                // (Note: for lines of odd length the line must be divided such that the first
                // half being moved contains one more character than the last half.)
                int PT_length = readPlainText.length();
                int halfPT = PT_length / 2;
                String firsthalfPT = readPlainText.substring(0, halfPT + (PT_length % 2));
                String lasthalfPT = readPlainText.substring(halfPT + (PT_length % 2));
                readPlainText = lasthalfPT + firsthalfPT;

                // Step 4: Swap the first 2 characters of the line with the last two characters.
                char[] charactersPT = readPlainText.toCharArray();
                char temp = charactersPT[0];
                charactersPT[0] = charactersPT[PT_length - 2];
                charactersPT[PT_length - 2] = temp;
                temp = charactersPT[1];
                charactersPT[1] = charactersPT[PT_length - 1];
                charactersPT[PT_length - 1] = temp;
                readPlainText = new String(charactersPT);

                // Step 5: Swap the two characters immediately to the left of the middle of
                // the string with the two characters that immediately follow them
                int middlePT = PT_length / 2;
                temp = charactersPT[middlePT - 1];
                charactersPT[middlePT - 1] = charactersPT[middlePT + 1];
                charactersPT[middlePT + 1] = temp;
                readPlainText = new String(charactersPT);

                // Step 6: Perform character substitutions
                readPlainText = readPlainText.replace("A", "@")
                        .replace("E", "=")
                        .replace("I", "!")
                        .replace("J", "?")
                        .replace("O", "*")
                        .replace("P", "#")
                        .replace("R", "&")
                        .replace("S", "$")
                        .replace("T", "+")
                        .replace("V", "^")
                        .replace("X", "%")
                        .replace(" ", "_");

                // Write encrypted plain text to the output stream
                outStream.println(readPlainText);
            } else {
                // If the line length is less than 4, leave it unchanged
                outStream.println(readPlainText);
            }
        }
    }

    /*
     * The Cipher.decrypt() method must correctly translate all of the input
     * characters from the provided
     * encrypted message inStream, and produce the corresponding decrypted output on
     * outStream
     */
    public void decrypt(BufferedReader inStream, PrintWriter outStream) throws Exception {
        String readCiphirText;
        while ((readCiphirText = inStream.readLine()) != null) {
            // Step 6: Reverse character substitutions
            readCiphirText = readCiphirText.replace("@", "A")
                    .replace("=", "E")
                    .replace("!", "I")
                    .replace("?", "J")
                    .replace("*", "O")
                    .replace("#", "P")
                    .replace("&", "R")
                    .replace("$", "S")
                    .replace("+", "T")
                    .replace("^", "V")
                    .replace("%", "X")
                    .replace("_", " ");

            // Step 5: Remove any leading or trailing whitespace from the line.
            readCiphirText = readCiphirText.trim();

            // Step 4: Swap the two characters immediately to the right of the middle of
            // the string with the two characters that immediately precede them.
            // (Note: for lines of odd length the line must be divided such that the
            // first half contains one more character than the last half.)
            int CT_length = readCiphirText.length();
            int middlePT = CT_length / 2;
            char[] charactersPT = readCiphirText.toCharArray();
            char temp = charactersPT[middlePT - 1];
            charactersPT[middlePT - 1] = charactersPT[middlePT + 1];
            charactersPT[middlePT + 1] = temp;
            readCiphirText = new String(charactersPT);

            // Step 3: Swap the first 2 characters of the line with the last two characters
            temp = charactersPT[0];
            charactersPT[0] = charactersPT[CT_length - 2];
            charactersPT[CT_length - 2] = temp;
            temp = charactersPT[1];
            charactersPT[1] = charactersPT[CT_length - 1];
            charactersPT[CT_length - 1] = temp;
            readCiphirText = new String(charactersPT);

            // Step 2: Move the first half of the string to be the last half.
            // (Note: in this step for lines of odd length the line must be divided
            // such that the first half contains one less character than the last half.)
            String lasthalfPT = readCiphirText.substring(0, middlePT + (CT_length % 2));
            String firsthalfPT = readCiphirText.substring(middlePT + (CT_length % 2));
            readCiphirText = firsthalfPT + lasthalfPT;

            // Step 1: Convert all letters in the string to lowercase
            readCiphirText = readCiphirText.toLowerCase();

            // Write decrypted Ciphir Text to the output stream
            outStream.println(readCiphirText);
        }
    }
}
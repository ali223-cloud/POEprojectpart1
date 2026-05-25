/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poepart2;

/**
 *
 * @author Ali2h
 */
import java.util.*;

public class PoePart2two {

    static Scanner sc = new Scanner(System.in);

    // declaration 
    static String regUsername = "", regPassword = "", firstName = "";
    static boolean loggedIn = false;

    // messages array
    static String[] sentIDs       = new String[100];
    static String[] sentHashes    = new String[100];
    static String[] sentRecips    = new String[100];
    static String[] sentBodies    = new String[100];
    static int sentCount = 0;

    static String[] storedIDs     = new String[100];
    static String[] storedHashes  = new String[100];
    static String[] storedRecips  = new String[100];
    static String[] storedBodies  = new String[100];
    static int storedCount = 0;

    static int totalMessagesSent = 0;

    // checking password and username 

    static boolean checkUserName(String u) {
        return u.contains("_") && u.length() <= 5;
    }

    static boolean checkPasswordComplexity(String p) {
        if (p.length() < 8) return false;
        boolean up = false, dig = false, sp = false;
        for (char c : p.toCharArray()) {
            if (Character.isUpperCase(c)) up = true;
            if (Character.isDigit(c))     dig = true;
            if ("!@#$%^&*()_+-=[]{}|;':\",./<>?".indexOf(c) >= 0) sp = true;
        }
        return up && dig && sp;
    }

    static String registerUser(String u, String p) {
        if (!checkUserName(u))
            return " username does not follow rules please try again";
        if (!checkPasswordComplexity(p))
            return " password does not follow rules please try again";
        regUsername = u;
        regPassword = p;
        return "password or username is incorrect please try again  .";
    }

    static boolean loginUser(String u, String p) {
        loggedIn = u.equals(regUsername) && p.equals(regPassword);
        return loggedIn;
    }

    static String loginMessage(boolean ok) {
        return ok ? "happy to see you again " + firstName 
                  : "incorrect password or username please try again .";
    }

    //message methods 

    static String generateMessageID() {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) sb.append(r.nextInt(10));
        return sb.toString();
    }

    static boolean checkMessageID(String id) {
        return id != null && id.length() <= 10;
    }

    static String checkRecipientCell(String recip) {
        if (recip != null && recip.length() <= 10 && recip.startsWith("+"))
            return "cellphone number is correct";
        return "cellphone number in wrong try again.";
    }

    static String createMessageHash(String id, int numSent, String body) {
        String[] words = body.trim().split("\\s+");
        String first = words[0];
        String last  = words.length > 1 ? words[words.length - 1] : first;
        return (id.substring(0, 2) + ":" + numSent + ":" + first + last).toUpperCase();
    }

    // [choosing to send, delete or store the message 
    static String sentMessage(String id, String hash, String recip, String body) {
        System.out.println("\n1) Send Message\n2) Disregard Message\n3) Store Message");
        System.out.print("Choice: ");
        String c = sc.nextLine().trim();
        switch (c) {
            case "1":
                sentIDs[sentCount]    = id;
                sentHashes[sentCount] = hash;
                sentRecips[sentCount] = recip;
                sentBodies[sentCount] = body;
                sentCount++;
                totalMessagesSent++;
                return "Message successfully sent.";
            case "2":
                System.out.println("Press 0 to delete the message.");
                sc.nextLine();
                return "Press 0 to delete the message.";
            case "3":
                storedIDs[storedCount]    = id;
                storedHashes[storedCount] = hash;
                storedRecips[storedCount] = recip;
                storedBodies[storedCount] = body;
                storedCount++;
                return "Message successfully stored.";
            default:
                return "Invalid option.";
        }
    }

    static String printMessages() {
        if (sentCount == 0) return "havent sent message yet";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sentCount; i++) {
            sb.append("Message ID: ").append(sentIDs[i])
              .append("\nHash: ").append(sentHashes[i])
              .append("\nRecipient: ").append(sentRecips[i])
              .append("\nMessage: ").append(sentBodies[i])
              .append("\n---\n");
        }
        return sb.toString();
    }

    static int returnTotalMessages() {
        return totalMessagesSent;
    }
    public static void main(String[] args) {

        // register your detailes
        System.out.println("=== QuickChat – Register ===");
        System.out.print("First name: "); firstName = sc.nextLine().trim();
        System.out.print("Last name: ");  sc.nextLine();

        while (true) {
            System.out.print("Username (must contain _ and ≤5 chars): ");
            String u = sc.nextLine().trim();
            System.out.print("Password (≥8 chars, uppercase, number, special): ");
            String p = sc.nextLine().trim();
            String res = registerUser(u, p);
            System.out.println(res);
            if (res.startsWith("Username successfully")) break;
        }

        // login 
        System.out.println("\n=== Login ===");
        while (!loggedIn) {
            System.out.print("Username: "); String u = sc.nextLine().trim();
            System.out.print("Password: "); String p = sc.nextLine().trim();
            System.out.println(loginMessage(loginUser(u, p)));
        }

        // menu
        System.out.println("Welcome to QuickChat.");
        boolean running = true;
        while (running) {
            System.out.println(" Send Messages Show sent messages Quit");
            System.out.print("Choice: ");
            switch (sc.nextLine().trim()) {
                case "1": sendFlow();  break;
                case "2": System.out.println("set to come later."); break;
                case "3": running = false; System.out.println("bye have a good day"); break;
                default:  System.out.println("not a valid option.");
            }
        }
        System.out.println("\n" + printMessages());
        System.out.println("Total messages sent: " + returnTotalMessages());
    }

    // message detailes
    static void sendFlow() {
        System.out.print("How many messages woul you like to send ? ");
        int n;
        try { n = Integer.parseInt(sc.nextLine().trim()); }
        catch (NumberFormatException e) { System.out.println("wrong number."); return; }

        for (int i = 0; i < n; i++) {
            System.out.println(" Message " + (i + 1) + " of " + n + " --");

            // check recipient
            String recip;
            while (true) {
                System.out.print("Recipient (e.g. +271234567): ");
                recip = sc.nextLine().trim();
                String check = checkRecipientCell(recip);
                System.out.println(check);
                if (check.equals("Cell phone number is correct")) break;
            }

            // string body
            String body;
            while (true) {
                System.out.print("Message (max 250 chars): ");
                body = sc.nextLine().trim();
                if (body.length() <= 250) { System.out.println("Message ready to send."); break; }
                System.out.println("Message is more than 250 characters by " + (body.length() - 250) + "; please make it smaller.");
            }

            // output of message
            String id   = generateMessageID();
            String hash = createMessageHash(id, totalMessagesSent + 1, body);
            System.out.println("Message ID number : " + id);
            System.out.println("Message Hash: " + hash);
            System.out.println(sentMessage(id, hash, recip, body));
        }
    }
}


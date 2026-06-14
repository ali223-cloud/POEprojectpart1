/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poepart3;

/**
 *
 * @author Ali2h
 */
import java.util.*;
import java.io.*;


public class POEpart3 {
    
    // Arrays 
    static ArrayList<String[]> sentMessages = new ArrayList<>();
    static ArrayList<String[]> disregardedMessages = new ArrayList<>();
    static ArrayList<String[]> storedMessages = new ArrayList<>();
    static ArrayList<String> messageHashes = new ArrayList<>();
    static ArrayList<String> messageIDs = new ArrayList<>();
    
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        populateTestData();
        mainMenu();
    }
    
    // test data
    public static void populateTestData() {
        // Format: {recipient/developer, message, flag, hash, ID}
        addMessage("+27834557896", "Did you get the cake?", "Sent");
        addMessage("+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored");
        addMessage("+27834484567", "Yohoooo, I am at your gate.", "Disregard");
        addMessage("0838884567",   "It is dinner time!",           "Sent");
        addMessage("+27838884567", "Ok, I am leaving without you.", "Stored");
    }
    
    public static void addMessage(String recipient, String message, String flag) {
        String hash = generateHash(message);
        String id   = generateID(recipient);
        String[] msg = {recipient, message, flag, hash, id};
        
        if (flag.equalsIgnoreCase("Sent"))      sentMessages.add(msg);
        else if (flag.equalsIgnoreCase("Stored"))    storedMessages.add(msg);
        else if (flag.equalsIgnoreCase("Disregard")) disregardedMessages.add(msg);
        
        messageHashes.add(hash);
        messageIDs.add(id);
    }
    
    public static String generateHash(String message) {
        return "HASH" + Math.abs(message.hashCode() % 10000);
    }
    
    public static String generateID(String recipient) {
        return "ID" + Math.abs(recipient.hashCode() % 10000);
    }
    
    // main manu
    public static void mainMenu() {
        int choice = 0;
        while (choice != 4) {
            System.out.println("\n=== QuickChat Main Menu ===");
            System.out.println("1. Sent Messages");
            System.out.println("2. Stored Messages");
            System.out.println("3. Disregarded Messages");
            System.out.println("4. Quit");
            System.out.print("Choose: ");
            choice = scanner.nextInt();
            
            switch (choice) {
                case 1 -> sentMessagesMenu();
                case 2 -> storedMessagesMenu();
                case 3 -> System.out.println("Disregarded messages: " + disregardedMessages.size());
                case 4 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid option.");
            }
        }
    }
    
    // stored messeage menu
    public static void storedMessagesMenu() {
        int choice = 0;
        while (choice != 7) {
            System.out.println("\n=== Stored Messages Menu ===");
            System.out.println("1. Display sender & recipient of all stored messages");
            System.out.println("2. Display the longest stored message");
            System.out.println("3. Search by Message ID");
            System.out.println("4. Search by Recipient");
            System.out.println("5. Delete message by hash");
            System.out.println("6. Display full report");
            System.out.println("7. Back");
            System.out.print("Choose: ");
            choice = scanner.nextInt();
            
            switch (choice) {
                case 1 -> displaySenderRecipient();
                case 2 -> displayLongestMessage();
                case 3 -> searchByID();
                case 4 -> searchByRecipient();
                case 5 -> deleteByHash();
                case 6 -> displayReport();
                case 7 -> System.out.println("Going back...");
                default -> System.out.println("Invalid option.");
            }
        }
    }
    
    // messeage menu
    public static void sentMessagesMenu() {
        System.out.println("\n=== Sent Messages ===");
        for (String[] msg : sentMessages) {
            System.out.println("Recipient: " + msg[0]);
            System.out.println("Message:   " + msg[1]);
            System.out.println("Hash:      " + msg[3]);
            System.out.println("ID:        " + msg[4]);
            System.out.println("----------");
        }
    }
    
   //Display sender & recipient 
    public static void displaySenderRecipient() {
        System.out.println("\n=== Stored Messages - Sender & Recipient ===");
        for (String[] msg : storedMessages) {
            System.out.println("Recipient: " + msg[0] + " | Message: " + msg[1]);
        }
    }
    
    
    public static String displayLongestMessage() {
        String longest = "";
        for (String[] msg : storedMessages) {
            if (msg[1].length() > longest.length()) {
                longest = msg[1];
            }
        }
        System.out.println("\nLongest Message: " + longest);
        return longest;
    }
    
    // search by id 
    public static String searchByID() {
        System.out.print("Enter Message ID: ");
        scanner.nextLine(); // flush
        String searchID = scanner.nextLine();
        return searchByIDLogic(searchID);
    }
    
    public static String searchByIDLogic(String searchID) {
        // Search ALL arrays
        ArrayList<String[]> all = new ArrayList<>();
        all.addAll(sentMessages);
        all.addAll(storedMessages);
        all.addAll(disregardedMessages);
        
        for (String[] msg : all) {
            if (msg[4].equalsIgnoreCase(searchID)) {
                System.out.println("Recipient: " + msg[0]);
                System.out.println("Message:   " + msg[1]);
                return msg[1];
            }
        }
        System.out.println("Message ID not found.");
        return "Not found";
    }
    
    // search by respitent
    public static void searchByRecipient() {
        System.out.print("Enter recipient number: ");
        scanner.nextLine();
        String recipient = scanner.nextLine();
        searchByRecipientLogic(recipient);
    }
    
    public static ArrayList<String> searchByRecipientLogic(String recipient) {
        ArrayList<String> results = new ArrayList<>();
        ArrayList<String[]> all = new ArrayList<>();
        all.addAll(sentMessages);
        all.addAll(storedMessages);
        all.addAll(disregardedMessages);
        
        System.out.println("\nMessages for " + recipient + ":");
        for (String[] msg : all) {
            if (msg[0].equalsIgnoreCase(recipient)) {
                System.out.println("- " + msg[1]);
                results.add(msg[1]);
            }
        }
        if (results.isEmpty()) System.out.println("No messages found.");
        return results;
    }
    
   
    public static void deleteByHash() {
        System.out.print("Enter message hash to delete: ");
        scanner.nextLine();
        String hash = scanner.nextLine();
        deleteByHashLogic(hash);
    }
    
    public static boolean deleteByHashLogic(String hash) {
        Iterator<String[]> it = storedMessages.iterator();
        while (it.hasNext()) {
            String[] msg = it.next();
            if (msg[3].equalsIgnoreCase(hash)) {
                System.out.println("Message \"" + msg[1] + "\" successfully deleted.");
                it.remove();
                messageHashes.remove(hash);
                return true;
            }
        }
        System.out.println("Hash not found.");
        return false;
    }
    
    //  Display report 
    public static void displayReport() {
        System.out.println("\n===== FULL STORED MESSAGES REPORT =====");
        System.out.printf("%-15s %-55s %-10s%n", "Hash", "Message", "Recipient");
        System.out.println("-".repeat(80));
        for (String[] msg : storedMessages) {
            System.out.printf("%-15s %-55s %-10s%n", msg[3], msg[1], msg[0]);
        }
    }
}


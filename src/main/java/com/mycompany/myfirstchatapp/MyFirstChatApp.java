
package com.mycompany.myfirstchatapp;

import java.util.Scanner;
import java.util.ArrayList;

public class MyFirstChatApp {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        MessageManager manager = new MessageManager();

        System.out.println("=== Chat App Registration ===");

        System.out.print("Enter your first name: ");
        String firstName = input.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = input.nextLine();

        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        System.out.print("Enter cellphone number: ");
        String cellPhoneNumber = input.nextLine();

        Login user = new Login(
                username, password, cellPhoneNumber,
                firstName, lastName
        );

        System.out.println();
        System.out.println(user.registerUser());

        System.out.println();
        System.out.println("=== Login ===");

        System.out.print("Enter username: ");
        String loginUsername = input.nextLine();

        System.out.print("Enter password: ");
        String loginPassword = input.nextLine();

        System.out.println();

        if (user.loginUser(loginUsername, loginPassword)) {

            System.out.println(user.returnLoginStatus(
                    loginUsername, loginPassword));
            System.out.println("Welcome to QuickChat.");

            System.out.print("How many messages would you like to enter? ");
            int numberOfMessages = input.nextInt();
            input.nextLine();

            int choice = 0;

            while (choice != 4) {

                System.out.println();
                System.out.println("1. Send Messages");
                System.out.println("2. Show recently sent messages");
                System.out.println("3. Stored Messages");
                System.out.println("4. Quit");
                System.out.print("Enter your choice: ");

                choice = input.nextInt();
                input.nextLine();

                switch (choice) {

                    case 1:

                        for (int i = 0; i < numberOfMessages; i++) {

                            System.out.println("Message " + (i + 1));

                            System.out.print(
                                    "Enter recipient cellphone number: ");
                            String recipient = input.nextLine();

                            System.out.print("Enter your message: ");
                            String messageText = input.nextLine();

                            Message newMessage = new Message(
                                    user.getCellPhoneNumber(),
                                    recipient,
                                    messageText,
                                    i
                            );

                            newMessage.createMessageID();

                            if (!newMessage.checkRecipientCell()) {
                                System.out.println(
                                        newMessage.validateRecipientCell());
                                i--;
                                continue;
                            }

                            if (!newMessage.checkMessageLength()) {
                                System.out.println(
                                        newMessage.validateMessageLength());
                                i--;
                                continue;
                            }

                            newMessage.createMessageHash();

                            System.out.println("Choose an option:");
                            System.out.println("1. Send Message");
                            System.out.println("2. Disregard Message");
                            System.out.println("3. Store Message");
                            System.out.print("Enter your choice: ");

                            int messageChoice = input.nextInt();
                            input.nextLine();

                            if (messageChoice < 1 || messageChoice > 3) {
                                System.out.println("Invalid choice.");
                                i--;
                                continue;
                            }

                            String messageResult =
                                    newMessage.SentMessage(messageChoice);

                            System.out.println(messageResult);

                             manager.addMessage(newMessage);

                      if (messageChoice == 1) {
                          System.out.println(newMessage.printMessages());

                        System.out.println(
                           "Total messages sent: "
                        + newMessage.returnTotalMessagess());
}
                        }

                        numberOfMessages = 0;
                        break;

                    case 2:
                        System.out.println("Coming Soon.");
                        break;

                    case 3:

                        int storedChoice = 0;

                        while (storedChoice != 7) {

                            System.out.println("\n=== Stored Messages ===");
                            System.out.println("1. Display sender and recipient");
                            System.out.println("2. Display longest message");
                            System.out.println("3. Search by message ID");
                            System.out.println("4. Search by recipient");
                            System.out.println("5. Delete message by hash");
                            System.out.println("6. Display full report");
                            System.out.println("7. Back to main menu");
                            System.out.print("Enter your choice: ");

                            storedChoice = input.nextInt();
                            input.nextLine();

                            switch (storedChoice) {

                                case 1:
                                    System.out.println(
                                            manager.displaySenderRecipient());
                                    break;

                                case 2:
                                    System.out.println(
                                            "Longest stored message:");
                                    System.out.println(
                                            manager.getLongestStoredMessage());
                                    break;

                                case 3:
                                    System.out.print("Enter the message ID: ");
                                    String searchID = input.nextLine();

                                    String foundMessage =
                                            manager.searchByMessageID(searchID);

                                    if (foundMessage == null) {
                                        System.out.println(
                                                "Message ID not found.");
                                    } else {
                                        System.out.println(
                                                "Message found: " + foundMessage);
                                    }
                                    break;

                                case 4:
                                    System.out.print(
                                            "Enter the recipient cellphone number: ");
                                    String searchRecipient = input.nextLine();

                                    ArrayList<Message> results =
                                            manager.searchByRecipient(
                                                    searchRecipient);

                                    if (results.isEmpty()) {
                                        System.out.println(
                                                "No messages found for this recipient.");
                                    } else {
                                        for (Message msg : results) {
                                            System.out.println(
                                                    "Message: " + msg.getMessage());
                                            System.out.println(
                                                    "Status: " + msg.getMessageStatus());
                                        }
                                    }
                                    break;

                                case 5:
                                    System.out.print(
                                            "Enter the message hash to delete: ");
                                    String searchHash = input.nextLine();

                                    if (manager.deleteMessageByHash(searchHash)) {
                                        System.out.println(
                                                "Message successfully deleted.");
                                    } else {
                                        System.out.println(
                                                "Message hash not found.");
                                    }
                                    break;

                                case 6:
                                    System.out.println(
                                            "========== MESSAGE REPORT ==========");
                                    System.out.println(
                                            manager.displaySentMessagesReport());
                                    break;

                                case 7:
                                    System.out.println(
                                            "Returning to main menu.");
                                    break;

                                default:
                                    System.out.println("Invalid choice.");
                            }
                        }
                        break;

                    case 4:
                        System.out.println("Goodbye!");
                        break;

                    default:
                        System.out.println(
                                "Invalid choice. Please try again.");
                }
            }

        } else {
            System.out.println(
                    "Username or password incorrect, please try again.");
        }

        input.close();
    }
}

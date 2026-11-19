/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.myfirstchatapp;

import java.util.ArrayList;

public class MessageManager {

    private ArrayList<Message> messages = new ArrayList<>();
    private ArrayList<Message> sentMessages = new ArrayList<>();
    private ArrayList<Message> disregardedMessages = new ArrayList<>();
    private ArrayList<Message> storedMessages = new ArrayList<>();
    private ArrayList<String> messageHashes = new ArrayList<>();
    private ArrayList<String> messageIDs = new ArrayList<>();

    public void addMessage(Message msg) {
        messages.add(msg);

        switch (msg.getMessageStatus()) {
            case "Sent":
                sentMessages.add(msg);
                break;
            case "Disregarded":
                disregardedMessages.add(msg);
                break;
            case "Stored":
                storedMessages.add(msg);
                break;
        }

        messageHashes.add(msg.getMessageHash());
        messageIDs.add(msg.getMessageID());
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public ArrayList<Message> getSentMessages() {
        return sentMessages;
    }

    public ArrayList<Message> getDisregardedMessages() {
        return disregardedMessages;
    }

    public ArrayList<Message> getStoredMessages() {
        return storedMessages;
    }

    public ArrayList<String> getMessageHashes() {
        return messageHashes;
    }

    public ArrayList<String> getMessageIDs() {
        return messageIDs;
    }

    public String displaySenderRecipient() {
        String result = "";

        for (Message msg : storedMessages) {
            result += "Sender: " + msg.getSenderCell()
                    + "\nRecipient: " + msg.getRecipientCell()
                    + "\n";
        }

        return result;
    }

    public String getLongestStoredMessage() {
        if (storedMessages.isEmpty()) {
            return "";
        }

        Message longest = storedMessages.get(0);

        for (Message msg : storedMessages) {
            if (msg.getMessage().length() > longest.getMessage().length()) {
                longest = msg;
            }
        }

        return longest.getMessage();
    }

    public String searchByMessageID(String id) {
        for (Message msg : messages) {
            if (msg.getMessageID().equals(id)) {
                return msg.getMessage();
            }
        }

        return null;
    }

    public ArrayList<Message> searchByRecipient(String recipient) {
        ArrayList<Message> results = new ArrayList<>();

        for (Message msg : messages) {
            if (msg.getRecipientCell().equals(recipient)
                    && (msg.getMessageStatus().equals("Sent")
                    || msg.getMessageStatus().equals("Stored"))) {
                results.add(msg);
            }
        }

        return results;
    }

    public boolean deleteMessageByHash(String hash) {
        for (int i = 0; i < storedMessages.size(); i++) {
            if (storedMessages.get(i).getMessageHash().equals(hash)) {
                Message deleted = storedMessages.remove(i);
                messages.remove(deleted);
                messageHashes.remove(hash);
                messageIDs.remove(deleted.getMessageID());
                return true;
            }
        }

        return false;
    }

    public String displaySentMessagesReport() {
        String report = "";

        for (Message msg : sentMessages) {
            report += "Message Hash: " + msg.getMessageHash()
                    + "\nRecipient: " + msg.getRecipientCell()
                    + "\nMessage: " + msg.getMessage()
                    + "\n--------------------------\n";
        }

        return report;
    }
}

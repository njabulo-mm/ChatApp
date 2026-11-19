
package com.mycompany.myfirstchatapp;

import org.junit.Test;
import static org.junit.Assert.*;

public class MyFirstChatAppTest {

    // Creates all five messages from the assignment test data.
    private MessageManager createAssignmentMessages() {
        MessageManager manager = new MessageManager();

        Message msg1 = new Message("+27820000001", "+27834557895",
                "Did you get the cake?", 0);
        msg1.setMessageID("1111111111");
        msg1.createMessageHash();
        msg1.SentMessage(1);
        manager.addMessage(msg1);

        Message msg2 = new Message("+27820000001", "+27838884567",
                "Where are you? You are late. I have asked you to be on time.", 1);
        msg2.setMessageID("2222222222");
        msg2.createMessageHash();
        msg2.SentMessage(3);
        manager.addMessage(msg2);

        Message msg3 = new Message("+27820000001", "+27834484567",
                "Yoooooo, I am at your gate.", 2);
        msg3.setMessageID("3333333333");
        msg3.createMessageHash();
        msg3.SentMessage(2);
        manager.addMessage(msg3);

        Message msg4 = new Message("+27820000001", "+27838884567",
                "It is dinner time.", 3);
        msg4.setMessageID("4444444444");
        msg4.createMessageHash();
        msg4.SentMessage(1);
        manager.addMessage(msg4);

        Message msg5 = new Message("+27820000001", "+27838884567",
                "Ok, I am leaving without you.", 4);
        msg5.setMessageID("5555555555");
        msg5.createMessageHash();
        msg5.SentMessage(3);
        manager.addMessage(msg5);

        return manager;
    }

    @Test
    public void testApplicationClassExists() {
        assertNotNull(MyFirstChatApp.class);
    }

    @Test
    public void testPopulateSentMessages() {
        MessageManager manager = createAssignmentMessages();

        assertEquals(2, manager.getSentMessages().size());
        assertEquals("Did you get the cake?",
                manager.getSentMessages().get(0).getMessage());
        assertEquals("It is dinner time.",
                manager.getSentMessages().get(1).getMessage());
    }

    @Test
    public void testLongestStoredMessage() {
        MessageManager manager = createAssignmentMessages();

        assertEquals(
                "Where are you? You are late. I have asked you to be on time.",
                manager.getLongestStoredMessage());
    }

    @Test
    public void testSearchMessageByID() {
        MessageManager manager = createAssignmentMessages();

        assertEquals("It is dinner time.",
                manager.searchByMessageID("4444444444"));
    }

    @Test
    public void testSearchMessagesByRecipient() {
        MessageManager manager = createAssignmentMessages();

        assertEquals(3,
                manager.searchByRecipient("+27838884567").size());
    }

    @Test
    public void testDeleteMessageByHash() {
        MessageManager manager = createAssignmentMessages();

        String messageTwoHash =
                manager.getStoredMessages().get(0).getMessageHash();

        assertTrue(manager.deleteMessageByHash(messageTwoHash));
        assertEquals(1, manager.getStoredMessages().size());
        assertFalse(manager.getMessages().stream()
                .anyMatch(msg -> msg.getMessageID().equals("2222222222")));
    }

    @Test
    public void testDisplaySentMessagesReport() {
        MessageManager manager = createAssignmentMessages();

        String report = manager.displaySentMessagesReport();

        assertTrue(report.contains("Message Hash:"));
        assertTrue(report.contains("Recipient:"));
        assertTrue(report.contains("Did you get the cake?"));
        assertTrue(report.contains("It is dinner time."));
    }
}

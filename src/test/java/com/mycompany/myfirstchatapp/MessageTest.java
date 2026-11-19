
package com.mycompany.myfirstchatapp;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    @Test
    public void testCreateMessageID() {
        Message msg = new Message(
                "+27820000001", "+27823960540", "Hello mom", 1);

        String id = msg.createMessageID();

        assertEquals(10, id.length());
        assertTrue(msg.checkMessageID());
    }

    @Test
    public void testCheckMessageID() {
        Message msg = new Message(
                "+27820000001", "+27823960540", "Hello mom", 1);

        msg.setMessageID("1234567890");

        assertTrue(msg.checkMessageID());
    }

    @Test
    public void testCheckRecipientCellValid() {
        Message msg = new Message(
                "+27820000001", "+27823960540", "Hello mom", 1);

        assertTrue(msg.checkRecipientCell());
    }

    @Test
    public void testCheckRecipientCellInvalid() {
        Message msg = new Message(
                "+27820000001", "0823960540", "Hello mom", 1);

        assertFalse(msg.checkRecipientCell());
    }

    @Test
    public void testValidateRecipientCell() {
        Message msg = new Message(
                "+27820000001", "+27823960540", "Hello mom", 1);

        assertEquals("Cell phone number successfully captured.",
                msg.validateRecipientCell());
    }

    @Test
    public void testCheckMessageLengthValid() {
        Message msg = new Message(
                "+27820000001", "+27823960540", "Hello mom", 1);

        assertTrue(msg.checkMessageLength());
    }

    @Test
    public void testCheckMessageLengthInvalid() {
        String longMessage = "A".repeat(251);

        Message msg = new Message(
                "+27820000001", "+27823960540", longMessage, 1);

        assertFalse(msg.checkMessageLength());
    }

    @Test
    public void testCreateMessageHash() {
        Message msg = new Message(
                "+27820000001", "+27823960540", "Hello mom", 1);

        msg.setMessageID("1234567890");

        assertEquals("12:1:HELLOMOM", msg.createMessageHash());
    }

    @Test
    public void testSentMessage() {
        Message msg = new Message(
                "+27820000001", "+27823960540", "Hello mom", 1);

        assertEquals("Message successfully sent.", msg.SentMessage(1));
        assertEquals("Sent", msg.getMessageStatus());
    }

    @Test
    public void testDisregardMessage() {
        Message msg = new Message(
                "+27820000001", "+27823960540", "Hello mom", 1);

        assertEquals("Press 0 to delete the message.",
                msg.SentMessage(2));

        assertEquals("Disregarded", msg.getMessageStatus());
    }

    @Test
    public void testGetMessage() {
        Message msg = new Message(
                "+27820000001", "+27823960540", "Hello mom", 1);

        assertEquals("Hello mom", msg.getMessage());
        assertEquals("+27823960540", msg.getRecipientCell());
        assertEquals("+27820000001", msg.getSenderCell());
        assertEquals(1, msg.getMessageCount());
    }

    @Test
    public void testMessageLengthExactly250() {
        String message = "A".repeat(250);

        Message msg = new Message(
                "+27820000001", "+27823960540", message, 1);

        assertTrue(msg.checkMessageLength());
    }

    @Test
    public void testStoreMessage() {
        Message msg = new Message(
                "+27820000001", "+27823960540", "Hello mom", 1);

        msg.setMessageID("1234567890");
        msg.createMessageHash();

        assertEquals("Message successfully stored.",
                msg.SentMessage(3));

        assertEquals("Stored", msg.getMessageStatus());
    }

    @Test
    public void testInvalidMessageChoice() {
        Message msg = new Message(
                "+27820000001", "+27823960540", "Hello mom", 1);

        assertEquals(
                "Invalid choice. Please select 1, 2, or 3.",
                msg.SentMessage(4));
    }

    @Test
    public void testExactMessageHash() {
        Message msg = new Message(
                "+27820000001", "+27823960540", "Hi tonight", 0);

        msg.setMessageID("0012345678");

        assertEquals("00:0:HITONIGHT", msg.createMessageHash());
    }
}

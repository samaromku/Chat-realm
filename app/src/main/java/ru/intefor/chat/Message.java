package ru.intefor.chat;

public class Message {
    private String sender;
    private long created;
    private String body;

    public String getSender() {
        return sender;
    }

    public long getCreated() {
        return created;
    }

    public String getBody() {
        return body;
    }

    public Message(String sender, long created, String body) {
        this.sender = sender;
        this.created = created;
        this.body = body;
    }
}

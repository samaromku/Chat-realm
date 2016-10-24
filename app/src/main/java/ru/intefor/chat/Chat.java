package ru.intefor.chat;

public class Chat {
    private String title;
    private String lastMessage;
    private long updated;

    public String getTitle() {
        return title;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public long getUpdated() {
        return updated;
    }

    public Chat(String title, String lastMessage, long updated) {
        this.title = title;
        this.lastMessage = lastMessage;
        this.updated = updated;
    }
}
